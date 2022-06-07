package in.co.sunrays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CollegeModel;

public class CollegeModelTest {
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		//TestAdd();
		TestUpdate();
		//TestDelete();
		//TestSearch();
		//TestFindByPk();
		//TestFindByFname();
		//TestList();
		
	}
	private static void TestFindByFname() throws ApplicationException {
		CollegeBean bean = new CollegeBean();
		CollegeModel model =new CollegeModel();
		bean = model.findByName("Abhay");
		System.out.println(bean.getAddress());
		System.out.println(bean.getCity());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getName());
		System.out.println(bean.getPhoneNo());
		System.out.println(bean.getState());
		System.out.println(".............................");
	
		
	}
	private static void TestFindByPk() throws ApplicationException {
		CollegeBean bean = new CollegeBean();
		CollegeModel model =new CollegeModel();
		bean = model.findByPK(1);
		System.out.println(bean.getAddress());
		System.out.println(bean.getCity());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getName());
		System.out.println(bean.getPhoneNo());
		System.out.println(bean.getState());
		System.out.println(".............................");
		
		
		
	}
	public static void TestSearch() throws ApplicationException {
		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel();
		List list = new ArrayList();
		list = model.search(bean);
		
		Iterator<CollegeBean> it = list.iterator();
		while (it.hasNext()) {
			CollegeBean bean1 = (CollegeBean) it.next();
			System.out.println(bean1.getAddress());
			System.out.println(bean1.getCity());
			System.out.println(bean1.getCreatedBy());
			System.out.println(bean1.getModifiedBy());
			System.out.println(bean1.getName());
			System.out.println(bean1.getPhoneNo());
			System.out.println(bean1.getState());
			System.out.println(".............................");
			
		}
			
	}
	public static void TestList() throws ApplicationException {
		CollegeModel model =new CollegeModel();
		CollegeBean bean = new CollegeBean();
		
		List list = model.list();
		Iterator<CollegeBean> it = list.iterator();
		while(it.hasNext()) {
			CollegeBean bean1 = (CollegeBean) it.next();
			System.out.println(bean1.getAddress());
			System.out.println(bean1.getCity());
			System.out.println(bean1.getName());
			System.out.println(bean1.getPhoneNo());
			System.out.println("-------------------------------------------------------------");
			
		}
	}
	public static void TestDelete() throws ApplicationException {
		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel();
		bean.setId(1);
		model.delete(bean);
		// TODO Auto-generated method stub
		
	
	}
	private static void TestUpdate() throws ApplicationException, DuplicateRecordException {
		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel();
		bean.setId(1);
		bean.setPhoneNo("8720027764");
		bean.setCreatedBy("Admin");
		model.update(bean);
		
	}
	public static void TestAdd() throws ApplicationException, DuplicateRecordException {
		CollegeBean bean = new CollegeBean();
		CollegeModel model = new CollegeModel();
		bean.setAddress("Airport Road");
		bean.setCity("Indore");
		bean.setName("Nayna");
		bean.setPhoneNo("8720027764");
		bean.setState("M.P");
		bean.setCreatedBy("Student");
		model.add(bean);
		
	}

}
