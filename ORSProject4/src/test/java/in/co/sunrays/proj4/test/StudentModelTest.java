package in.co.sunrays.proj4.test;

import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.StudentModel;

public class StudentModelTest {
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		//TestAdd();
		//TestSearch();
	//	TestDelete();
		TestUpdate();
		
	}

	private static void TestUpdate() throws ApplicationException, DuplicateRecordException {
	StudentBean bean = new StudentBean();
	StudentModel model = new StudentModel();
bean.setId(1);
	bean.setCollegeName("Narsee Monji");
	bean.setCollegeId(1l);
	bean.setDob(null);
	bean.setEmail("sah2u.nayna2001@gmail.com");
	bean.setFirstName("Nayna");
	bean.setLastName("Sahu");
	bean.setMobileNo("8720027764");
	model.update(bean);
	
		
	}

	private static void TestDelete() {
		// TODO Auto-generated method stub
		
	}

	private static void TestSearch() {
		// TODO Auto-generated method stub
		
	}

	private static void TestAdd() throws ApplicationException, DuplicateRecordException {
		StudentBean bean = new StudentBean();
		StudentModel model = new StudentModel();
		long pk ;
		bean.setCollegeName("nmims");
		bean.setCollegeId(1l);
		bean.setDob(null);
		bean.setEmail("sahu.nayna2001@gmail.com");
		bean.setFirstName("Nayna");
		bean.setLastName("Sahu");
		bean.setMobileNo("8720027764");
		pk = model.add(bean);
	//	bean = model.findByPK(pk);
		// TODO Auto-generated method stub
		
	}

}
