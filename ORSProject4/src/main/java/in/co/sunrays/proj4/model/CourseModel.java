package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;

/**
 * @author Nayna
 *
 */
public class CourseModel {
	/** The log. */
	// private static Logger log = Logger.getLogger(CourseModel.class);

	/**
	 * Find next PK of Course.
	 *
	 */

	public Integer nextPK() throws DatabaseException { // aplicationException
		// log.debug("CourseModel nextPK method started");

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_Course");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
			// log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model nextPK End");
		return pk + 1;
	}

	/**
	 * Add a Course.
	 *
	 */

	public long add(CourseBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		CourseBean duplicateCourseName = findByCourseName(bean.getCourseName());
		if (duplicateCourseName != null) {
			throw new DuplicateRecordException("Course Name already Exist");

		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("Insert into st_course values(?,?,?,?,?,?,?,?)");
			ps.setInt(1, pk);
			ps.setString(2, bean.getCourseName());
			ps.setString(3, bean.getDuration());
			ps.setString(4, bean.getDescription());
			ps.setString(5, bean.getCreatedBy());
			ps.setString(6, bean.getModifiedBy());
			ps.setTimestamp(7, bean.getCreatedDatetime());
			ps.setTimestamp(8, bean.getModifiedDatetime());
			ps.executeUpdate();
			conn.commit();
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			// log.debug("EXception in Database...", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Course");

		} finally

		{
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("CourseModel Add method End");

		return pk;
	}

	/**
	 * Delete a Course.
	 *
	 */

	public void delete(CourseBean bean) throws ApplicationException {
		// log.debug("CourseModel Delete Method Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("DELETE FROM ST_Course WHERE ID = ?");
			ps.setLong(1, bean.getId());
			ps.executeUpdate();
			conn.commit();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("Database Exception..", e);
			try {
				conn.rollback();

			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Course");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("CourseModel Delete Method End");
	}

	/**
	 * Find User by Course.
	 *
	 */

	public CourseBean findByCourseName(String CourseName) throws ApplicationException {
		// log.debug("Course Model FindByName method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE COURSE_NAME=?");
		CourseBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, CourseName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
			// log.debug("Database Exception ..", e);
			// throw new ApplicationException("Exception in Course Model FindByName Method
			// ");
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		// log.debug("Course Model FindByName method End");
		return bean;

	}

	/**
	 * Find User by Course.
	 *
	 */

	public CourseBean findByPK(long PK) throws ApplicationException {
		// log.debug("CourseModel FindbyPK method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE ID=?");
		Connection conn = null;
		CourseBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, PK);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

			}
			rs.close();

		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("CourseModel findByPK End");
		return bean;

	}

	/**
	 * Update a Course.
	 *
	 */

	public void update(CourseBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("CourseModel update Started");
		Connection conn = null;
		CourseBean beanExist = findByCourseName(bean.getCourseName());
		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Course Already Exist");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE ST_Course SET Course_NAME = ?,DURATION=? , DESCRIPTION = ? , CREATED_BY =? , MODIFIED_BY=? , CREATED_DATETIME =? , MODIFIED_DATETIME=? WHERE ID = ?");
			ps.setString(1, bean.getCourseName());
			ps.setString(2, bean.getDuration());
			ps.setString(3, bean.getDescription());
			ps.setString(4, bean.getCreatedBy());
			ps.setString(5, bean.getModifiedBy());
			ps.setTimestamp(6, bean.getCreatedDatetime());
			ps.setTimestamp(7, bean.getModifiedDatetime());
			ps.setLong(8, bean.getId());
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
		// log.debug("CourseModel update End");
	}

	/**
	 * Search Course.
	 *
	 */
	public List search(CourseBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search Course with pagination.
	 *
	 */
	public List search(CourseBean bean, int pageNo, int pageSize) throws ApplicationException {
//	        log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_Course WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" AND Course_Name like '" + bean.getCourseName() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION like '" + bean.getDescription() + "%'");
			}
			if (bean.getDuration() != null && bean.getDuration().length() > 0) {
				sql.append(" AND Duration like '" + bean.getDuration().length() + "%'");
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}
		System.out.println(sql);

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				 bean = new CourseBean();
				bean.setId(rs.getInt(1));
				bean.setCourseName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
//	           e.printStackTrace();
			// log.error("Database Exception ,,,," , e);
			throw new ApplicationException("Exception in the Search Method" + e.getMessage());

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		// log.debug("CourseModel search End");
		return list;
	}

	/**
	 * Get List of Course.
	 *
	 */

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
//	        log.debug("CourseModel list Started");

		StringBuffer sql = new StringBuffer("select * from ST_Course");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}
		ArrayList list = new ArrayList();
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CourseBean bean = new CourseBean();
				bean.setId(rs.getInt(1));
				bean.setCourseName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("Database Exception in list ...",e);
			throw new ApplicationException("Exception : Exception in CourseModel List method " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		// log.debug("CourseModel list End");
		return list;

	}

}
