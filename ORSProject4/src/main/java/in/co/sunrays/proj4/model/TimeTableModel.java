package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.bean.TimeTableBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;

public class TimeTableModel {
	public Integer nextPK() throws DatabaseException {
		// log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_TimeTable");
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

	public long add(TimeTableBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("Insert into st_TimeTable values(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, pk);
			ps.setString(2, bean.getCourseName());
			ps.setFloat(3, bean.getCourseId());
			ps.setString(4, bean.getSubjectName());
			ps.setFloat(5, bean.getSubjectId());
			ps.setDate(6, (Date) bean.getExamDate());
			ps.setDate(7, (Date) bean.getExamTime());
			ps.setString(8, bean.getSemester());
			ps.setString(9, bean.getCreatedBy());
			ps.setString(10, bean.getModifiedBy());
			ps.setTimestamp(11, bean.getCreatedDatetime());
			ps.setTimestamp(12, bean.getModifiedDatetime());
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
			throw new ApplicationException("Exception : Exception in add TimeTable");

		} finally

		{
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model add End");
		return pk;

	}

	public void delete(TimeTableBean bean) throws ApplicationException {
		// log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("DELETE FROM ST_TimeTable WHERE ID = ?");
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
			throw new ApplicationException("Exception : Exception in delete TimeTable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model delete Started");
	}

	public TimeTableBean findByExamDate(String Date) throws ApplicationException {
		TimeTableBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ST_TimeTable WHERE Exam_Date = ?");
			ps.setString(1, Date);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setCourseId(rs.getInt(3));
				bean.setSubjectName(rs.getString(4));
				bean.setSubjectId(rs.getInt(5));
				bean.setExamDate(rs.getDate(6));
				bean.setExamTime(rs.getDate(7));
				bean.setSemester(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

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

	public TimeTableBean findByPK(long PK) throws ApplicationException {
		// log.debug("Model findByPK Started");
		Connection conn = null;
		TimeTableBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELCET * FROM ST_TimeTable WHERE ID =?");
			ps.setLong(1, PK);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setCourseId(rs.getInt(3));
				bean.setSubjectName(rs.getString(4));
				bean.setSubjectId(rs.getInt(5));
				bean.setExamDate(rs.getDate(6));
				bean.setExamTime(rs.getDate(7));
				bean.setSemester(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

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

	public void update(TimeTableBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("Model update Started");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE ST_TimeTable SET Course_NAME = ? ,Course_Id=?, Subject_Name=?,Subject_Id=?,Exam_Date=?,Exam_Time=?,Semester=?, CREATED_BY =? , MODIFIED_BY=? , CREATED_DATETIME =? , MODIFIED_DATETIME=? WHERE ID = ?");
			ps.setString(1, bean.getCourseName());
			ps.setFloat(2, bean.getCourseId());
			ps.setString(3, bean.getSubjectName());
			ps.setFloat(4, bean.getSubjectId());
			ps.setDate(5, (Date) bean.getExamDate());
			ps.setDate(6, (Date) bean.getExamTime());
			ps.setString(7, bean.getSemester());
			ps.setString(8, bean.getCreatedBy());
			ps.setString(9, bean.getModifiedBy());
			ps.setTimestamp(10, bean.getCreatedDatetime());
			ps.setTimestamp(11, bean.getModifiedDatetime());
			ps.setLong(12, bean.getId());
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
			throw new ApplicationException("Exception in updating COURSE ");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model update End");
	}

	public List search(TimeTableBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	public List search(TimeTableBean bean, int pageNo, int pageSize) throws ApplicationException {
//	        log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TimeTable WHERE 1=1");

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
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setSubjectName(rs.getString(4));
				bean.setSubjectId(rs.getLong(5));
				bean.setExamDate(rs.getDate(6));
				bean.setExamTime(rs.getDate(7));
				bean.setSemester(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
//	            log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search TimeTable");
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
//	        log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from ST_TimeTable");
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
				TimeTableBean bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setSubjectName(rs.getString(4));
				bean.setSubjectId(rs.getLong(5));
				bean.setExamDate(rs.getDate(6));
				bean.setExamTime(rs.getDate(7));
				bean.setSemester(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Time Table");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		// log.debug("Model list End");
		return list;

	}

}
