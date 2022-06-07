package in.co.sunrays.proj4.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.CollegeModel;
import in.co.sunrays.proj4.model.CollegeModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;


/**
 * @author Nayna
 *
 */
@ WebServlet(name="CollegeListCtl",urlPatterns={"/ctl/CollegeListCtl"})
public class CollegeListCtl extends BaseCtl {
//	private static Logger log = Logger.getLogger(CollegeListCtl.class);
	protected void preload(HttpServletRequest request) {
		CollegeModel cmodel = new CollegeModel();
		
		List<CollegeBean> name = new ArrayList<CollegeBean>();
		
		try {
			name = cmodel.list();
			
			request.setAttribute("namee", name);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        CollegeBean bean = new CollegeBean();

        bean.setId(DataUtility.getLong(request.getParameter("name")));
        bean.setCity(DataUtility.getString(request.getParameter("city")));
        System.out.println(bean.getCity()+ "searech  "+ bean.getName());

        return bean;
    }

    /**
     * Contains display logic
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        int pageNo = 1;
        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

        CollegeBean bean = (CollegeBean) populateBean(request);
        CollegeModel model = new CollegeModel();

        List list = null;
        System.out.println("collegelist ctl get");

        try {
            list = model.search(bean, pageNo, pageSize);
            ServletUtility.setBean(bean, request);
        } catch (ApplicationException e) {
        //    log.error(e);
        	e.printStackTrace();
			/*
			 * ServletUtility.handleException(e, request, response); return;
			 */
        }

        if (list == null || list.size() == 0) {
            ServletUtility.setErrorMessage("No record found ", request);
        }

        ServletUtility.setList(list, request);
        ServletUtility.setPageNo(pageNo, request);
        ServletUtility.setPageSize(pageSize, request);
        ServletUtility.forward(getView(), request, response);
    }

    /**
     * Contains submit logic
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

    //    log.debug("CollegeListCtl doPost Start");

        List list = null;
        System.out.println("college list ctl post");
        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

        pageNo = (pageNo == 0) ? 1 : pageNo;

        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
                .getValue("page.size")) : pageSize;

        CollegeBean bean = (CollegeBean) populateBean(request);

        String op = DataUtility.getString(request.getParameter("operation"));

        CollegeModel model = new CollegeModel();
        
        String[] ids = request.getParameterValues("ids");//

        try {

            if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
                    || "Previous".equalsIgnoreCase(op)) {

                if (OP_SEARCH.equalsIgnoreCase(op)) {
                    pageNo = 1;
                } else if (OP_NEXT.equalsIgnoreCase(op)) {
                    pageNo++;
                } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
                    pageNo--;
                }
                ///////////////////////////////////
                
                else if (OP_NEW.equalsIgnoreCase(op)) 
				{
					ServletUtility.redirect(ORSView.COLLEGE_CTL, request, response);
					return ;
				}
				
            } else if (OP_RESET.equalsIgnoreCase(op) || OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
				return ;
			}else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					CollegeBean deletebean = new CollegeBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
						ServletUtility.setSuccessMessage("Record Deleted Successfully", request);

					}
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			}

             //////////////////////////////////////////////////////////////   

            System.out.println(bean.getCity()+"  dto "+ bean.getName());
            list = model.search(bean, pageNo, pageSize);
            ServletUtility.setList(list, request);
            if (list == null || list.size() == 0) {
                ServletUtility.setErrorMessage("No record found ", request);
            }
            ServletUtility.setList(list, request);
            ServletUtility.setBean(bean, request);
            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.forward(getView(), request, response);

        } catch (ApplicationException e) {
     //       log.error(e);
        	e.printStackTrace();
           // ServletUtility.handleException(e, request, response);
            return;
        }
    //    log.debug("CollegeListCtl doGet End");
    }

    @Override
    protected String getView() {
        return ORSView.COLLEGE_LIST_VIEW;
    }

}
