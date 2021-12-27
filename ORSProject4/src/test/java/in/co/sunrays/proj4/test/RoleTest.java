package in.co.sunrays.proj4.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.RoleModel;

public class RoleTest {
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		//TestAdd();
		// TestUpdate();
		// TestDelete();
		//TestFindByName();
		//TestSearch();
		TestList();
	}

	public static void TestList() throws ApplicationException {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();
		List list = new ArrayList();
		list = model.list();
		
		Iterator<RoleBean> it = list.iterator();
		while (it.hasNext()) {
			RoleBean roleBean = (RoleBean) it.next();
			System.out.println(roleBean.getId());
			System.out.println(roleBean.getName());
			System.out.println(roleBean.getDescription());
			System.out.println(roleBean.getCreatedBy());
			System.out.println(roleBean.getModifiedBy());
			System.out.println(roleBean.getCreatedDatetime());
			System.out.println(roleBean.getModifiedDatetime());

			
			
		}
		
		
	}

	public static void TestAdd() throws ApplicationException, DuplicateRecordException {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();
		bean.setName("Abhay");
		bean.setDescription("Admin");
		bean.setCreatedBy("nayna");
		bean.setModifiedBy("nayna");
		bean.setCreatedDatetime(null);
		bean.setModifiedDatetime(null);

		model.add(bean);
	}

	public static void TestUpdate() throws ApplicationException, DuplicateRecordException {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();
		bean.setId(1);
		bean.setName("Nayna");
		bean.setDescription("Student");
		bean.setCreatedBy("nayna");
		bean.setModifiedBy("nayna");
		bean.setCreatedDatetime(null);
		bean.setModifiedDatetime(null);

		model.update(bean);
	}

	public static void TestDelete() throws ApplicationException, DuplicateRecordException {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();
		bean.setId(1);

		model.delete(bean);
	}

	public static void TestFindByName() throws ApplicationException {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();
		bean = model.findByName("Nayna");
		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());

	}
	public static void TestSearch() throws ApplicationException {
		RoleBean bean = new RoleBean();
		RoleModel model = new RoleModel();
		List list = new ArrayList();
		list = model.search(bean);
		
		Iterator it = list.iterator();
		while (it.hasNext()) {
			RoleBean bean1 = (RoleBean) it.next();
			System.out.println(bean1.getId());
			System.out.println(bean1.getName());
			System.out.println(bean1.getDescription());
			System.out.println(bean1.getCreatedBy());
			System.out.println(bean1.getModifiedBy());
			System.out.println(bean1.getCreatedDatetime());
			System.out.println(bean1.getModifiedDatetime());
			System.out.println("................................");
			
		}
				
	}

}
