package in.co.sunrays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.MarksheetBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.MarksheetModel;

public class MarksheetModelTest {
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
		//TestAdd();
		//TestUpdate();
		//TestDelete();
		//TestSearch();
		//TestList();
	}
	public static void TestList() throws ApplicationException {
	MarksheetBean bean = new MarksheetBean();
	MarksheetModel model = new MarksheetModel();
	List list = new ArrayList();
	 list = model.list();
	 Iterator<MarksheetBean> it =  list.iterator();
	 while (it.hasNext()) {
		MarksheetBean marksheetBean = (MarksheetBean) it.next();
		System.out.println(marksheetBean.getId());
		System.out.println(marksheetBean.getName());
		System.out.println(marksheetBean.getRollNo());
		System.out.println(marksheetBean.getChemistry());
		System.out.println(marksheetBean.getMaths());
		System.out.println(marksheetBean.getPhysics());
		
	}
	 
		
	}
	public static void TestSearch() throws ApplicationException {
		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		List list = new ArrayList();
		 list = model.list();
		 Iterator<MarksheetBean> it =  list.iterator();
		 while (it.hasNext()) {
			MarksheetBean marksheetBean = (MarksheetBean) it.next();
			System.out.println(marksheetBean.getId());
			System.out.println(marksheetBean.getName());
			System.out.println(marksheetBean.getRollNo());
			System.out.println(marksheetBean.getChemistry());
			System.out.println(marksheetBean.getMaths());
			System.out.println(marksheetBean.getPhysics());
			System.out.println("....................................");
		 }
			
		
	}

	public static void TestDelete() throws ApplicationException {
		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		bean.setId(1);
		model.delete(bean);;
		
		
		
	}
	public static void TestUpdate() throws ApplicationException, DuplicateRecordException {
		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		bean.setChemistry(5);
		bean.setMaths(1);
		bean.setCreatedBy("bhagvan2");
		bean.setPhysics(0);
		bean.setModifiedBy("bhagvan3");
		bean.setRollNo("101");
		bean.setName("Nayna");
		bean.setStudentId(1l);
		bean.setId(1);
		
		model.update(bean);
		
		
	}
	public static void TestAdd() throws ApplicationException, DuplicateRecordException {
		MarksheetBean bean = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();
		bean.setChemistry(5);
		bean.setMaths(1);
		bean.setCreatedBy("bhagvan2");
		bean.setPhysics(0);
		bean.setModifiedBy("bhagvan3");
		bean.setRollNo("101");
		bean.setName("Nayna");
		bean.setStudentId(1l);
		
		model.add(bean);
		
	}

}
