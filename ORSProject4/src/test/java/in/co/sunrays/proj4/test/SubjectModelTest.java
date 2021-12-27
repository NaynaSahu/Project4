package in.co.sunrays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.SubjectModel;

public class SubjectModelTest {
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		//testAdd();
		//testDelete();
		//testUpdate();
		//testSearch();
		//testList();
	}

	public static void testList() throws ApplicationException {
		SubjectBean bean  = new SubjectBean();
		SubjectModel model = new SubjectModel();
		List list = new ArrayList();
		list = model.list();
		Iterator<SubjectBean> it = list.iterator();
		while (it.hasNext()) {
			SubjectBean bean1 = (SubjectBean) it.next();
			System.out.println(bean1.getCourseId());
			System.out.println(bean1.getCourseName());
			System.out.println(bean1.getDescription());
			System.out.println(bean1.getSubjectName());
			System.out.println(bean1.getCreatedDatetime());
			System.out.println(".................................");
			

		
	}
	}

	public static void testSearch() throws ApplicationException {
		SubjectBean bean  = new SubjectBean();
		SubjectModel model = new SubjectModel();
		List list = new ArrayList();
		list = model.search(bean);
		Iterator<SubjectBean> it = list.iterator();
		while (it.hasNext()) {
			SubjectBean bean1 = (SubjectBean) it.next();
			System.out.println(bean1.getCourseId());
			System.out.println(bean1.getCourseName());
			System.out.println(bean1.getDescription());
			System.out.println(bean1.getSubjectName());
			System.out.println(bean1.getCreatedDatetime());
			System.out.println(".................................");
			
		}
		 
		
		
		
	}

	public static void testUpdate() throws ApplicationException, DuplicateRecordException {
		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();
		bean.setCourseId(1);
		bean.setCourseName("B.E");
		bean.setId(2);
		bean.setDscription("very good");
		bean.setSubjectName("Physics");
		model.update(bean);
		
	}

	public static void testDelete() throws ApplicationException {
		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();
		bean.setId(2);
		model.delete(bean);
		
	}

	public static void testAdd() throws ApplicationException, DuplicateRecordException {
		SubjectBean bean = new SubjectBean();
		SubjectModel model = new SubjectModel();
		bean.setSubjectName("maths");
		bean.setCourseName("B.tech");
		bean.setCourseId(1);
		bean.setDscription("c.e");
		model.add(bean);
		
		
	}

}
