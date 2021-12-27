package in.co.sunrays.proj4.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import java.sql.Connection;

import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;

public class RoleModel {
	// private static Logger log = Logger.getLogger(RoleModel.class);
	public Integer nextPK() throws DatabaseException {
		// log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_ROLE");
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

	public long add(RoleBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		RoleBean duplicataRole = findByName(bean.getName());
		// Check if create Role already exist
		if (duplicataRole != null) {
			throw new DuplicateRecordException("Role already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("Insert into st_role values(?,?,?,?,?,?,?)");
			ps.setInt(1, pk);
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getDescription());
			ps.setString(4, bean.getCreatedBy());
			ps.setString(5, bean.getModifiedBy());
			ps.setTimestamp(6, bean.getCreatedDatetime());
			ps.setTimestamp(7, bean.getModifiedDatetime());
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
			throw new ApplicationException("Exception : Exception in add Role");

		} finally

		{
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model add End");
		return pk;

	}

	public void delete(RoleBean bean) throws ApplicationException {
		// log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("DELETE FROM ST_ROLE WHERE ID = ?");
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
			throw new ApplicationException("Exception : Exception in delete Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model delete Started");
	}

	public RoleBean findByName(String Name) throws ApplicationException {
		RoleBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ST_ROLE WHERE NAME = ?");
			ps.setString(1, Name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));

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

	public RoleBean findByPK(long PK) throws ApplicationException {
		// log.debug("Model findByPK Started");
		Connection conn = null;
		RoleBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("SELCET * FROM ST_ROLE WHERE ID =?");
			ps.setLong(1, PK);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));

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

	public void update(RoleBean bean) throws ApplicationException, DuplicateRecordException {
		// log.debug("Model update Started");
		Connection conn = null;
		RoleBean duplicateRole = findByName(bean.getName());
		if (duplicateRole != null && duplicateRole.getId() != bean.getId()) {
			throw new DuplicateRecordException("Role already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE ST_ROLE SET NAME = ? , DESCRIPTION = ? , CREATED_BY =? , MODIFIED_BY=? , CREATED_DATETIME =? , MODIFIED_DATETIME=? WHERE ID = ?");
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getDescription());
			ps.setString(3, bean.getCreatedBy());
			ps.setString(4, bean.getModifiedBy());
			ps.setTimestamp(5, bean.getCreatedDatetime());
			ps.setTimestamp(6, bean.getModifiedDatetime());
			ps.setLong(7, bean.getId());
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
			throw new ApplicationException("Exception in updating Role ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Model update End");
	}
	 public List search(RoleBean bean) throws ApplicationException {
	        return search(bean, 0, 0);
	    }


	    public List search(RoleBean bean, int pageNo, int pageSize)
	            throws ApplicationException {
//	        log.debug("Model search Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM ST_ROLE WHERE 1=1");

	        if (bean != null) {
	            if (bean.getId() > 0) {
	                sql.append(" AND id = " + bean.getId());
	            }
	            if (bean.getName() != null && bean.getName().length() > 0) {
	                sql.append(" AND NAME like '" + bean.getName() + "%'");
	            }
	            if (bean.getDescription() != null
	                    && bean.getDescription().length() > 0) {
	                sql.append(" AND DESCRIPTION like '" + bean.getDescription()
	                        + "%'");
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
	                bean = new RoleBean();
	                bean.setId(rs.getLong(1));
	                bean.setName(rs.getString(2));
	                bean.setDescription(rs.getString(3));
	                bean.setCreatedBy(rs.getString(4));
	                bean.setModifiedBy(rs.getString(5));
	                bean.setCreatedDatetime(rs.getTimestamp(6));
	                bean.setModifiedDatetime(rs.getTimestamp(7));
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
	        StringBuffer sql = new StringBuffer("select * from ST_ROLE");
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
	                RoleBean bean = new RoleBean();
	                bean.setId(rs.getLong(1));
	                bean.setName(rs.getString(2));
	                bean.setDescription(rs.getString(3));
	                bean.setCreatedBy(rs.getString(4));
	                bean.setModifiedBy(rs.getString(5));
	                bean.setCreatedDatetime(rs.getTimestamp(6));
	                bean.setModifiedDatetime(rs.getTimestamp(7));
	                list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	 //           log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting list of Role");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }

	       // log.debug("Model list End");
	        return list;

	    }

}
