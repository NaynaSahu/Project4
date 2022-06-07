package in.co.sunrays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CourseModel;

public class CourseModelTest {
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		// testAdd();
		// testUpdate();
		// testDelete();
		//testSearch();
		// testList();
		TestFindByPK();
	}

	public static void testList() throws ApplicationException {
		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();
		List list = new ArrayList();
		list = model.list();
		Iterator<CourseBean> it = list.iterator();
		while (it.hasNext()) {
			CourseBean bean1 = (CourseBean) it.next();
			System.out.println(bean1.getCourseName());
			System.out.println(bean1.getDescription());
			System.out.println(bean1.getModifiedBy());
			System.out.println("..................................");
			
		}
		

	} 
	public static void TestFindByPK() throws ApplicationException {
		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();
		bean = model.findByPK(1);
		System.out.println(bean.getCourseName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getDuration());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getId());
		
		
	}

	public static void testSearch() throws ApplicationException {
		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();
		List list = new ArrayList();
		list = model.search(bean);
		Iterator<CourseBean> it  = list.iterator();
		while (it.hasNext()) {
			CourseBean bean1 = (CourseBean) it.next();
			System.out.println(bean1.getCourseName());
			System.out.println(bean1.getDescription());
			System.out.println(bean1.getModifiedBy());
			System.out.println("..................................");
			
		}
		
	}

	public static void testDelete() throws ApplicationException {
		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();
		bean.setId(3);
		model.delete(bean);

	}

	public static void testUpdate() throws ApplicationException, DuplicateRecordException {
		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();
		bean.setCourseName("BBA");
		bean.setDescription("Management");
		bean.setId(3);
		model.update(bean);

	}

	public static void testAdd() throws ApplicationException, DuplicateRecordException {
		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();
		bean.setCourseName("BBA");
		bean.setDescription("CSBS");
		model.add(bean);

	}

}
