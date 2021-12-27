package in.co.sunrays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.UserModel;

public class UserModelTest {
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		//testAdd();
		//testUpdate();
		//testDelete();
		//testSearch();
		//testList();
		//testFindByLogin();
		testAuthentication();
	
	}

	public static void testAuthentication() throws ApplicationException {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		bean = model.authenticate("nayna001", "12341");
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getGender());
		System.out.println(bean.getPassword());
		System.out.println("...............................");
		
	}

	public static void testFindByLogin() throws ApplicationException {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		bean = model.findByLogin("nayna001");
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getGender());
		System.out.println("......................");
		
		
	}

	public static void testAdd() throws ApplicationException, DuplicateRecordException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		bean.setFirstName("Nayna");
		bean.setGender("female");
		bean.setMobileNo("8720027764");
		model.add(bean);
		
	}

	public static void testList() throws ApplicationException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		List list =new ArrayList();
		list = model.list();
		Iterator<UserBean> it = list.iterator();
		while (it.hasNext()) {
			UserBean bean1 = (UserBean) it.next();
			System.out.println(bean1.getFirstName());
			System.out.println(bean1.getGender());
			System.out.println(bean1.getLastName());
			System.out.println(bean1.getMobileNo());
			System.out.println(bean.getPassword());
			System.out.println(".............................");
		}
		
		
	}

	public static void testSearch() throws ApplicationException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		List list =new ArrayList();
		list = model.search(bean);
		Iterator<UserBean> it = list.iterator();
		while (it.hasNext()) {
			UserBean bean1 = (UserBean) it.next();
			System.out.println(bean1.getFirstName());
			System.out.println(bean1.getGender());
			System.out.println(bean1.getLastName());
			System.out.println(bean1.getMobileNo());
			System.out.println(bean.getPassword());
			System.out.println(".............................");
		}
		
	}

	public static void testDelete() throws ApplicationException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		bean.setId(2);
		model.delete(bean);
		
	}

	private static void testUpdate() throws ApplicationException, DuplicateRecordException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		bean.setId(2);
		bean.setFirstName("priya");
		bean.setGender("female");
		bean.setMobileNo("123456789");
		model.update(bean);
		
	}

}
