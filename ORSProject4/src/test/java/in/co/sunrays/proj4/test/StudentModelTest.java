package in.co.sunrays.proj4.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.StudentModel;
import in.co.sunrays.proj4.model.StudentModel;

public class StudentModelTest {
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		TestAdd();
		//TestSearch();
		//TestDelete();
		//TestUpdate();
		//TestList();
		
		
	}
	public static void TestList() throws ApplicationException {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();
		List list = new ArrayList();
		 list = model.list();
		 Iterator<StudentBean> it =  list.iterator();
		 while (it.hasNext()) {
			StudentBean studentBean = (StudentBean) it.next();
			System.out.println(studentBean.getId());
			System.out.println(studentBean.getCollegeName());
			System.out.println(studentBean.getEmail());
			System.out.println(studentBean.getFirstName());
			System.out.println(studentBean.getKey());
		 }
			
			
		}

	private static void TestUpdate() throws ApplicationException, DuplicateRecordException {
	StudentBean bean = new StudentBean();
	StudentModel model = new StudentModel();
bean.setId(1);
	bean.setCollegeName("Narsee Monji");
	bean.setCollegeId(1);
	bean.setDob(null);
	bean.setEmail("sah2u.nayna2001@gmail.com");
	bean.setFirstName("Nayna");
	bean.setLastName("Sahu");
	bean.setMobileNo("8720027764");
	model.update(bean);
	
		
	}

	private static void TestDelete() throws ApplicationException {
		StudentModel model = new StudentModel();
		StudentBean bean = new StudentBean();
		 bean.setId(4);
		model.delete(bean);
		System.out.println("the data of ID = "+bean.getId()+" is deleted");
		
	}

	private static void TestSearch() throws ApplicationException {
		StudentModel model = new StudentModel();
		StudentBean bean1 = new StudentBean();
		 List list = new ArrayList<StudentBean>();
		 list = model.search(bean1);
		 Iterator<StudentBean> it = list.iterator();
		 while(it.hasNext()) {
			 StudentBean studentBean = (StudentBean) it.next();
			 System.out.println(studentBean.getCollegeName());
			 System.out.println(studentBean.getEmail());
			 System.out.println(studentBean.getFirstName());
			 System.out.println(studentBean.getLastName());
			 System.out.println(studentBean.getMobileNo());
			 System.out.println("---------------------------------------");
			 
		 }
		 
		
	}

	private static void TestAdd() throws ApplicationException, DuplicateRecordException {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();
		long pk ;
		
		Date u = new Date();
		bean.setCollegeName("nmims");
		bean.setCollegeId(1);
		bean.setDob(u);
		bean.setEmail("sahu.nayna2001@gmail.com");
		bean.setFirstName("Nayna");
		bean.setLastName("Sahu");
		bean.setMobileNo("8720027764");
		pk = model.add(bean);
	//	bean = model.findByPK(pk);
		// TODO Auto-generated method stub
		
	}

}
