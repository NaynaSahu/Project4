package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;

public class FacultyModel {
	public Integer nextPK() throws DatabaseException {
		// log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM St_Faculty");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model nextPK End");
		return pk + 1;
	}

	public long add(FacultyBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); 
			PreparedStatement ps = conn
					.prepareStatement("Insert into st_faculty values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, pk);
			ps.setString(2, bean.getFirstName());
			System.out.println(bean.getFirstName());
			ps.setString(3, bean.getLastName());
			ps.setString(4, bean.getGender());
			ps.setDate(5, (Date) bean.getDOJ());
			ps.setString(6, bean.getQualification());
			ps.setString(7, bean.getEmail());
			ps.setString(8, bean.getMobileNo());
			ps.setFloat(9, bean.getCollegeId());
			ps.setFloat(10, bean.getCourseId());
			ps.setFloat(11, bean.getSubjectId());
			ps.setString(12, bean.getSubjectName());
			ps.setString(13, bean.getCollegeName());
			ps.setString(14, bean.getCreatedBy());
			ps.setString(15, bean.getModifiedBy());
			ps.setTimestamp(16, bean.getCreatedDatetime());
			ps.setTimestamp(17, bean.getModifiedDatetime());
			ps.executeUpdate();
			conn.commit();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Faculty");

		} finally

		{
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model add End");
		return pk;

	}

	public void delete(FacultyBean bean) throws ApplicationException {
		// log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("DELETE FROM ST_Faculty WHERE ID = ?");
			ps.setLong(1, bean.getId());
			ps.executeUpdate();
			conn.commit();
			ps.close();

		} catch (Exception e) {
			// log.error("Database Exception..", e);
			try {
				conn.rollback();

			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model delete Started");
	}

	public FacultyBean findByFirstName(String Name) throws ApplicationException {
		FacultyBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ST_Course WHERE First_Name = ?");
			ps.setString(1, Name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setDOJ(rs.getDate(5));
				bean.setQualification(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setMobileNo(rs.getString(8));
				bean.setCollegeId(rs.getLong(9));
				bean.setCourseId(rs.getLong(10));
				bean.setSubjectId(rs.getLong(11));
				bean.setSubjectName(rs.getString(12));
				bean.setCollegeName(rs.getString(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));

			}
			rs.close();

		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by emailId");
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		// log.debug("Model findBy EmailId End");
		return bean;

	}

	public FacultyBean findByPK(long PK) throws ApplicationException {
		// log.debug("Model findByPK Started");
		Connection conn = null;
		FacultyBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELCET * FROM ST_Faculty WHERE ID =?");
			ps.setLong(1, PK);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setDOJ(rs.getDate(5));
				bean.setQualification(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setMobileNo(rs.getString(8));
				bean.setCollegeId(rs.getLong(9));
				bean.setCourseId(rs.getLong(10));
				bean.setSubjectId(rs.getLong(11));
				bean.setSubjectName(rs.getString(12));
				bean.setCollegeName(rs.getString(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));

			}
			rs.close();

		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model findByPK End");
		return bean;

	}

	public void update(FacultyBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("Model update Started");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE ST_Faculty SET First_NAME = ? ,Last_NAME = ? ,Gender = ? ,Date_Of_Joining = ? ,Qualification = ? ,Email = ? ,Mobile_No=?, College_Id = ? , Course_Id = ? ,Subject_Id = ? ,Subject_Name = ? ,College_Name = ? , CREATED_BY =? , MODIFIED_BY=? , CREATED_DATETIME =? , MODIFIED_DATETIME=? WHERE ID = ?");
			ps.setString(1, bean.getFirstName());
			ps.setString(2, bean.getLastName());
			ps.setString(3, bean.getGender());
			// ps.setDate(5, (Date) bean.getDOJ());
			ps.setString(4, null);
			ps.setString(5, bean.getQualification());
			ps.setString(6, bean.getEmail());
			ps.setString(7, bean.getMobileNo());
			ps.setFloat(8, bean.getCollegeId());
			ps.setFloat(9, bean.getCourseId());
			ps.setFloat(10, bean.getSubjectId());
			ps.setString(11, bean.getSubjectName());
			ps.setString(12, bean.getCollegeName());
			ps.setString(13, bean.getCreatedBy());
			ps.setString(14, bean.getModifiedBy());
			ps.setTimestamp(15, bean.getCreatedDatetime());
			ps.setTimestamp(16, bean.getModifiedDatetime());
			ps.setLong(17, bean.getId());
			ps.executeUpdate();
			conn.commit();
			ps.close();

		} catch (Exception e) {
			// log.error("Database Exception..", e);
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			e.printStackTrace();
			throw new ApplicationException("Exception in updating FACULTY ");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model update End");
	}

	public List search(FacultyBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	public List search(FacultyBean bean, int pageNo, int pageSize) throws ApplicationException {
//		        log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_Faculty WHERE 1=1");

		/*
		 * if (bean != null) { if (bean.getId() > 0) { sql.append(" AND id = " +
		 * bean.getId()); } if (bean.getCourseName() != null &&
		 * bean.getCourseName().length() > 0) { sql.append(" AND NAME like '" +
		 * bean.getCourseName() + "%'"); } if (bean.getDescription() != null &&
		 * bean.getDescription().length() > 0) { sql.append(" AND DESCRIPTION like '" +
		 * bean.getDescription() + "%'"); }
		 * 
		 * }
		 */
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setDOJ(rs.getDate(5));
				bean.setQualification(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setMobileNo(rs.getString(8));
				bean.setCollegeId(rs.getLong(9));
				bean.setCourseId(rs.getLong(10));
				bean.setSubjectId(rs.getLong(11));
				bean.setSubjectName(rs.getString(12));
				bean.setCollegeName(rs.getString(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
//		            log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		// log.debug("Model search End");
		return list;
	}

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
//		        log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from ST_Faculty");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FacultyBean bean = new FacultyBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setDOJ(rs.getDate(5));
				bean.setQualification(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setMobileNo(rs.getString(8));
				bean.setCollegeId(rs.getLong(9));
				bean.setCourseId(rs.getLong(10));
				bean.setSubjectId(rs.getLong(11));
				bean.setSubjectName(rs.getString(12));
				bean.setCollegeName(rs.getString(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		// log.debug("Model list End");
		return list;

	}

}
