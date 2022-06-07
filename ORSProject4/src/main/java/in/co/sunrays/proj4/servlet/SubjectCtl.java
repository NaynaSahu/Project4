package in.co.sunrays.proj4.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * @author Nayna
 *
 */
@WebServlet(name = "SubjectCtl", urlPatterns = { "/ctl/SubjectCtl" })
public class SubjectCtl extends BaseCtl{
	private static final long serialVersionUID = 1L;

	//private static Logger log = Logger.getLogger(SubjectCtl.class);

	protected void preload(HttpServletRequest request) {

		System.out.println("Subject preload is enter");

		CourseModel cmodel = new CourseModel();
		// List<CourseBean> cList = new ArrayList<CourseBean>();

		try {
			List cList = cmodel.list();
			System.out.println("empty = "+cList.isEmpty());
			request.setAttribute("CourseList", cList);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		System.out.println("subject preload out");
	}

	protected boolean validate(HttpServletRequest request) {
	//	log.debug("validate Method of Subject Ctl start");
		System.out.println("validate inn");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.name", "Subject Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}else if (!DataValidator.isName(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.name", "Description"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("coursename"))) {
			request.setAttribute("coursename", PropertyReader.getValue("error.require", "Course Name"));
			pass = false;
		}
	//	log.debug("validate Method of Subject Ctl  End");
		System.out.println("validate out");
		return pass;
	}

	protected SubjectBean populateBean(HttpServletRequest request) {
//		log.debug("Populate bean Method of Subject Ctl start");

		System.out.println("Subject Ctl populate bean enter");

		SubjectBean bean = new SubjectBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setSubjectName(DataUtility.getString(request.getParameter("name")));
		bean.setDscription(DataUtility.getString(request.getParameter("description")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("coursename")));
System.out.println(bean.getCourseId());
		System.out.println(request.getParameter("coursename"));
		System.out.println("ksdlklds " +bean.getCourseId()+" "+bean.getDescription()+"   "+bean.getSubjectName() );
		populateDTO(bean, request);

	//	log.debug("PopulateBean Method of Subject Ctl End");
		System.out.println("populate bean out");
		return bean;

	}

	/**
	 * Contains Display logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	//	log.debug("Do get Method of Subject Ctl start ");
		System.out.println("Subject Ctl do get in ");
		String op = DataUtility.getString(request.getParameter("operation"));

		SubjectModel model = new SubjectModel();
		SubjectBean bean = null;
		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0 || op != null) {
			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
			//	log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		System.out.println("do get out");
	//	log.debug("Do get Method of Subject Ctl End");
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Contains Submit logics
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	//	log.debug("Do post Method of Subject Ctl start");
		System.out.println(" Subject Ctl inside do post");
		String op = DataUtility.getString(request.getParameter("operation"));
		int id = DataUtility.getInt(request.getParameter("id"));
		

		SubjectModel model = new SubjectModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			System.out.println("inside save  op");
			SubjectBean bean = (SubjectBean) populateBean(request);
			
			
			
		 try {
				if (id > 0) {
					model.update(bean);
					ServletUtility.setSuccessMessage("Succesfully Updated ", request);

				} else {
				long pk = model.add(bean);
					ServletUtility.setSuccessMessage("Succesfully Added ", request);

					// bean.setId(pk);
				}
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
		//		log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Subject name already Exsist", request);
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);
			return;
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
			return;
		}
		/*
		 * else if (OP_DELETE.equalsIgnoreCase(op)) { SubjectBean bean =
		 * populateBean(request); try { model.delete(bean);
		 * ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response); return; }
		 * catch (ApplicationException e) { log.error(e);
		 * ServletUtility.handleException(e, request, response); return ; } }
		 */

		ServletUtility.forward(getView(), request, response);
	//	log.debug("Do post Method of Subject Ctl End");
	}

	@Override
	protected String getView() {
		return ORSView.SUBJECT_VIEW;
	}

}
