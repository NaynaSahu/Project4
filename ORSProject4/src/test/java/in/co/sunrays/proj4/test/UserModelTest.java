package in.co.sunrays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.exception.RecordNotFoundException;
import in.co.sunrays.proj4.model.UserModel;
import in.co.sunrays.proj4.model.UserModel;

public class UserModelTest {
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException, ParseException, RecordNotFoundException {
		//testAdd();
		testUpdate();
		//testDelete();
		//testSearch();
		//testList();
		//testFindByLogin();
		//testAuthentication();
		//testChangePassword();
		//testFindByPK();
	
	}

	private static void testFindByPK() throws ApplicationException {
		
			UserBean bean = new UserBean();
			UserModel model =new UserModel();
			bean = model.findByPK(4);
			
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			
			System.out.println(".............................");
			
			
			
		}
		
	

	private static void testChangePassword() throws RecordNotFoundException, ApplicationException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		bean = model.findByLogin("Bapee@gmail.com");
		String OP ="Bapee@1234";
		bean.setId(3l);
		//bean.setPassword("666");
		String NP = "666";
		model.changePassword(3l, OP, NP);
		System.out.println("password has been changed successfully");
		
		
	}

	public static void testAuthentication() throws ApplicationException {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		bean = model.authenticate("abhay@gmail.com", "Abhay@Sahu1234");
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getGender());
		System.out.println(bean.getPassword());
		System.out.println("...............................");
		
	}

	public static void testFindByLogin() throws ApplicationException {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		bean = model.findByLogin("nayna@gmail.com");
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getGender());
		System.out.println("......................");
		
		
	}

	public static void testAdd() throws ApplicationException, DuplicateRecordException, ParseException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyy");
		bean.setFirstName("Nivedi");
        bean.setLastName("Khandelwal");
        bean.setLogin("Nivedi@gmail.com");
        bean.setPassword("Nivedi@1234");
        bean.setDob(sdf.parse("02-12-1990"));
        bean.setRoleId(1L);
        bean.setGender("Female");
        bean.setLock("Yes");
        bean.setMobileNo("4565525515");
        bean.setConfirmPassword("Nivedi@1234");
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
		bean.setId(4);
	
		bean.setFirstName("kjs");
		bean.setGender("female");
		bean.setMobileNo("123456789");
		model.update(bean);
		//UserBean updatedbean = model.findByPK(1L);
	}

}
