package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.UserRole;
import com.revature.util.ConnectionUtil;

public class LookupDaoSQL implements LookupDao {

	public UserRole getRoleFromSQL(ResultSet rs) throws SQLException{
		int roleId = rs.getInt("user_role_id");
		String role = rs.getString("user_role");
		
		return new UserRole(roleId, role);
	}
	
	public ReimbursementStatus getStatusFromSQL(ResultSet rs) throws SQLException{
		int statusId = rs.getInt("reimb_status_id");
		String status = rs.getString("reimb_status");
		
		return new ReimbursementStatus(statusId, status);
	}
	
	public ReimbursementType getTypeFromSQL(ResultSet rs) throws SQLException{
		int typeId = rs.getInt("reimb_type_id");
		String type = rs.getString("reimb_type");
		
		return new ReimbursementType(typeId, type);
	}
	
	@Override
	public List<UserRole> getAllRoles() {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM user_roles";
			PreparedStatement ps = c.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			List<UserRole> roleList = new ArrayList<UserRole>();
			while(rs.next()) {
				roleList.add(getRoleFromSQL(rs));
			}
			
			return roleList;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReimbursementStatus> getAllStatus() {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursement_status";
			PreparedStatement ps = c.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			List<ReimbursementStatus> statusList = new ArrayList<ReimbursementStatus>();
			while(rs.next()) {
				statusList.add(getStatusFromSQL(rs));
			}
			
			return statusList;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReimbursementType> getAllTypes() {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursement_type";
			PreparedStatement ps = c.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			List<ReimbursementType> typeList = new ArrayList<ReimbursementType>();
			while(rs.next()) {
				typeList.add(getTypeFromSQL(rs));
			}
			
			return typeList;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

}
