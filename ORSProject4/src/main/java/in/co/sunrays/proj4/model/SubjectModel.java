package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;

public class SubjectModel {
	public Integer nextPK() throws DatabaseException {
		// log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_Subject");
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
	public long add(SubjectBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("Insert into st_subject values(?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, pk);
			ps.setString(2, bean.getSubjectName());
			ps.setFloat(3, bean.getCourseId());
			ps.setString(4, bean.getCourseName());
			ps.setString(5, bean.getDescription());
			ps.setString(6, bean.getCreatedBy());
			ps.setString(7, bean.getModifiedBy());
			ps.setTimestamp(8, bean.getCreatedDatetime());
			ps.setTimestamp(9, bean.getModifiedDatetime());
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
			throw new ApplicationException("Exception : Exception in add Subject");

		} finally

		{
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model add End");
		return pk;

	}

	public void delete(SubjectBean bean) throws ApplicationException {
		// log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("DELETE FROM ST_Subject WHERE ID = ?");
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
			throw new ApplicationException("Exception : Exception in delete Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model delete Started");
	}

	public SubjectBean findBySubjectName(String SubjectName) throws ApplicationException {
		SubjectBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ST_Subject WHERE Subject_Name = ?");
			ps.setString(1, SubjectName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setSubjectName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setDscription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));

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

	public SubjectBean findByPK(long PK) throws ApplicationException {
		// log.debug("Model findByPK Started");
		Connection conn = null;
		SubjectBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELCET * FROM ST_Subject WHERE ID =?");
			ps.setLong(1, PK);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setSubjectName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setDscription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));

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

	public void update(SubjectBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("Model update Started");
		Connection conn = null;
		
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE ST_Subject SET Subject_Name = ?,Course_Id =?,Course_Name=? , DESCRIPTION = ? , CREATED_BY =? , MODIFIED_BY=? , CREATED_DATETIME =? , MODIFIED_DATETIME=? WHERE ID = ?");
			ps.setString(1, bean.getSubjectName());
			ps.setFloat(2,bean.getCourseId());
			ps.setString(3, bean.getCourseName());
			ps.setString(4, bean.getDescription());
			ps.setString(5, bean.getCreatedBy());
			ps.setString(6, bean.getModifiedBy());
			ps.setTimestamp(7, bean.getCreatedDatetime());
			ps.setTimestamp(8, bean.getModifiedDatetime());
			ps.setLong(9, bean.getId());
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
			throw new ApplicationException("Exception in updating Subject ");
			
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model update End");
	}
	 public List search(SubjectBean bean) throws ApplicationException {
	        return search(bean, 0, 0);
	    }


	    public List search(SubjectBean bean, int pageNo, int pageSize)
	            throws ApplicationException {
//	        log.debug("Model search Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM ST_Subject WHERE 1=1");

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
	                bean = new SubjectBean();
	        		bean.setId(rs.getLong(1));
					bean.setSubjectName(rs.getString(2));
					bean.setCourseId(rs.getLong(3));
					bean.setCourseName(rs.getString(4));
					bean.setDscription(rs.getString(5));
					bean.setCreatedBy(rs.getString(6));
					bean.setModifiedBy(rs.getString(7));
					bean.setCreatedDatetime(rs.getTimestamp(8));
					bean.setModifiedDatetime(rs.getTimestamp(9));

	                list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
//	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in search Role");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }

	     //   log.debug("Model search End");
	        return list;
	    }

	   
	    public List list() throws ApplicationException {
	        return list(0, 0);
	    }


	    public List list(int pageNo, int pageSize) throws ApplicationException {
//	        log.debug("Model list Started");
	        ArrayList list = new ArrayList();
	        StringBuffer sql = new StringBuffer("select * from ST_Subject");
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
	               SubjectBean bean = new SubjectBean();
	       		bean.setId(rs.getLong(1));
				bean.setSubjectName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setDscription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
				list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	 //           log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting list of subject");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }

	       // log.debug("Model list End");
	        return list;

	    }







}
