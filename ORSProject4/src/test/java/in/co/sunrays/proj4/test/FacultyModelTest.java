package in.co.sunrays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.FacultyModel;

public class FacultyModelTest {
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		//testAdd();
		//testDelete();
		//testUpdate();
		//testList();
		testSearch();
		
	}

	public static void testAdd() throws ApplicationException, DuplicateRecordException {
		FacultyBean bean = new FacultyBean();
		FacultyModel model = new FacultyModel();
		bean.setFirstName("Nayna");
		bean.setLastName("Sahu");
		bean.setEmail("sahu.nayna2001@gmail.com");
		bean.setGender("female");
		bean.setCollegeId(1);
		bean.setSubjectId(1);
		bean.setCourseId(1);
		model.add(bean);
		
	}

	public static void testDelete() throws ApplicationException {
		FacultyBean bean = new FacultyBean();
		FacultyModel model = new FacultyModel();
		bean.setId(2);
		model.delete(bean);
		
	}

	public static void testUpdate() throws ApplicationException, DuplicateRecordException {
		FacultyBean bean = new FacultyBean();
		FacultyModel model = new FacultyModel();
		bean.setId(1);
		bean.setFirstName("Nayna");
		bean.setLastName("Sahu");
		bean.setEmail("sahu11.nayna2001@gmail.com");
		bean.setGender("female");
		bean.setCollegeId(1);
		bean.setSubjectId(1);
		bean.setCourseId(1);
		model.update(bean);;
		
	}

	public static void testList() throws ApplicationException {
		FacultyBean bean = new FacultyBean();
		FacultyModel model = new FacultyModel();
		List list = new ArrayList();
		list = model.list();
		Iterator<FacultyBean> it = list.iterator();
		while (it.hasNext()) {
			FacultyBean bean1 = (FacultyBean) it.next();
			System.out.println(bean1.getFirstName());
			System.out.println(bean1.getLastName());
			System.out.println(bean1.getCollegeName());
			System.out.println(bean1.getCourseName());
			System.out.println(bean1.getEmail());
			System.out.println(bean1.getGender());
			System.out.println(bean1.getMobileNo());
			System.out.println(bean1.getQualification());
			System.out.println(bean1.getSubjectName());
			System.out.println("..............................................");
			
		}
		
		
		
	}

	public static void testSearch() throws ApplicationException {
		FacultyBean bean = new FacultyBean();
		FacultyModel model = new FacultyModel();
		List list = new ArrayList();
		list = model.search(bean);
		Iterator<FacultyBean> it = list.iterator();
		while (it.hasNext()) {
			FacultyBean bean1 = (FacultyBean) it.next();
			System.out.println(bean1.getFirstName());
			System.out.println(bean1.getLastName());
			System.out.println(bean1.getCollegeName());
			System.out.println(bean1.getCourseName());// is null
			System.out.println(bean1.getEmail());
			System.out.println(bean1.getGender());
			System.out.println(bean1.getMobileNo());
			System.out.println(bean1.getQualification());
			System.out.println(bean1.getSubjectName());
			System.out.println("..............................................");
			
		
		
	}

	}
}
