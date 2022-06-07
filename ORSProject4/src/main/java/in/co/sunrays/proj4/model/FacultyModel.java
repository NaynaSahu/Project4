package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;


/**
 * @author Nayna
 *
 */
public class FacultyModel {
	/** The log. */
	// public static Logger log = Logger.getLogger(FacultyModel.class);

	/**
	 * Find next PK of Faculty.
	 *
	 */
	public Integer nextPK() throws DatabaseException { // applicationException
		// log.debug("Faculty Model nextPK Started");
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
			e.printStackTrace();
			// log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Faculty Model nextPK End");
		return pk + 1;
	}

	/**
	 * Add a Faculty.
	 *
	 */
	public long add(FacultyBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		CollegeModel collegeModel = new CollegeModel();
		CollegeBean collegeBean = collegeModel.findByPK(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		CourseModel CourseModel = new CourseModel();
		CourseBean CourseBean = CourseModel.findByPK(bean.getCourseId());
		bean.setCourseName(CourseBean.getCourseName());

		SubjectModel subjectModel = new SubjectModel();
		SubjectBean subjectBean = subjectModel.findByPK(bean.getSubjectId());
		bean.setSubjectName(subjectBean.getSubjectName());

		FacultyBean beanExist = findByEmail(bean.getEmail());
		if (beanExist != null) {
			throw new DuplicateRecordException("Email already exists");
		}

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
			ps.setString(3, bean.getLastName()) ;
			ps.setString(4, bean.getGender());
			ps.setDate(5, new java.sql.Date(bean.getDOJ().getTime())); // how to convert the data type
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
			// log.error("Database Exception..", e);
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

	/**
	 * Delete a Faculty.
	 *
	 */
	public void delete(FacultyBean bean) throws ApplicationException {
		// log.debug("Faculty Model delete Started");
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
		// log.debug("Faculty Model delete Started");
	}

	/**
	 * Find User by Faculty name.
	 *
	 */

	public FacultyBean findByEmail(String EmailId) throws ApplicationException {
		System.out.println("faculty add find by name");
		// log.debug("Faculty Model findByName method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY WHERE EMAIL=?");
		FacultyBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, EmailId);
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
			e.printStackTrace();
			// log.error("database exception ..." , e);
			throw new ApplicationException("Exception : Exception in faculty model in findbyName method");
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		// log.debug("Faculty Model findBy EmailId End");
		return bean;

	}

	/**
	 * Find User by Faculty PK.
	 *
	 */

	public FacultyBean findByPK(long PK) throws ApplicationException {
		// log.debug("Faculty Model findByPK method Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY WHERE ID=?");
		Connection conn = null;
		FacultyBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql.toString());
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
			e.printStackTrace();
			// log.error("database exception ..." , e);
			// throw new ApplicationException("Exception : Exception in findByPK in faculty
			// model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Faculty Model findByPK End");
		return bean;

	}

	/**
	 * Update a Faculty.
	 *
	 */
	public void update(FacultyBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("Faculty Model update Started");
		Connection conn = null;
		CollegeModel collegeModel = new CollegeModel();
		CollegeBean collegeBean = collegeModel.findByPK(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		CourseModel CourseModel = new CourseModel();
		CourseBean CourseBean = CourseModel.findByPK(bean.getCourseId());
		bean.setCourseName(CourseBean.getCourseName());

		SubjectModel subjectModel = new SubjectModel();
		SubjectBean subjectBean = subjectModel.findByPK(bean.getSubjectId());
		bean.setSubjectName(subjectBean.getSubjectName());

		FacultyBean beanExist = findByEmail(bean.getEmail());
		// Check if updated EmailId already exist
		if (beanExist != null && !(beanExist.getId() == bean.getId())) {
			throw new DuplicateRecordException("Given Email Id already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE ST_Faculty SET First_NAME = ? ,Last_NAME = ? ,Gender = ? ,Date_Of_Joining = ? ,Qualification = ? ,Email = ? ,Mobile_No=?, College_Id = ? , Course_Id = ? ,Subject_Id = ? ,Subject_Name = ? ,College_Name = ? , CREATED_BY =? , MODIFIED_BY=? , CREATED_DATETIME =? , MODIFIED_DATETIME=? WHERE ID = ?");
			ps.setString(1, bean.getFirstName());
			ps.setString(2, bean.getLastName());
			ps.setString(3, bean.getGender());
			ps.setDate(4, new java.sql.Date(bean.getDOJ().getTime())); // how to convert the data type
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
		// log.debug("Faculty Model update End");
	}

	/**
	 * Search Faculty.
	 *
	 */

	public List search(FacultyBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search Faculty with pagination.
	 *
	 */
	public List search(FacultyBean bean, int pageNo, int pageSize) throws ApplicationException {
//		        log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_Faculty WHERE 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getCourseId() > 0) {
				sql.append(" AND college_Id = " + bean.getCourseId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().trim().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().trim().length() > 0) {
				sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
			}

			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" AND Email like '" + bean.getEmail() + "%'");
			}

			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" AND Gender like '" + bean.getGender() + "%'");
			}

			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND Mobile_No like '" + bean.getMobileNo() + "%'");
			}

			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
				sql.append(" AND college_Name like '" + bean.getCollegeName() + "%'");
			}
			if (bean.getCourseId() > 0) {
				sql.append(" AND Course_Id = " + bean.getCourseId());
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" AND Course_Name like '" + bean.getCourseName() + "%'");
			}
			if (bean.getSubjectId() > 0) {
				sql.append(" AND Subject_Id = " + bean.getSubjectId());
			}
			if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
				sql.append(" AND subject_Name like '" + bean.getSubjectName() + "%'");
			}
		}

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
			e.printStackTrace();

			throw new ApplicationException("Exception : Exception in search Faculty");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		// log.debug("Faculty Model search End");
		return list;
	}

	/**
	 * Get List of Faculty.
	 *
	 */

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Faculty with pagination.
	 *
	 */

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

		// log.debug("Faculty Model list End");
		return list;

	}

}
