package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoSQL implements ReimbursementDao {

	public Reimbursement getReimbFromSQL(ResultSet rs) throws SQLException{
		int id = rs.getInt("reimb_id");
		double amount = rs.getFloat("reimb_amount");
		Timestamp submitted = rs.getTimestamp("reimb_submitted");
		Timestamp resolved = rs.getTimestamp("reimb_resolved");
		String description = rs.getString("reimb_description");
		String receipt = rs.getString("reimb_receipt");
		int author = rs.getInt("reimb_author");
		int resolver = rs.getInt("reimb_resolver");
		int statusId = rs.getInt("reimb_status_id");
		int typeId = rs.getInt("reimb_type_id");
		
		return new Reimbursement(id, amount, submitted, resolved, description, receipt, author, resolver, statusId, typeId);
	}
	
	@Override
	public Reimbursement getReimbById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getAllReimb() {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursement";
			PreparedStatement ps = c.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
			while(rs.next()) {
				reimbList.add(getReimbFromSQL(rs));
			}
			
			return reimbList;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveReimb(Reimbursement newReimb) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO reimbursement (reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) " + 
					"VALUES (REIMB_ID_SEQ.nextval, ?, CURRENT_TIMESTAMP, null, ?, null, ?, null, 1, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setDouble(1, newReimb.getAmount());
			ps.setString(2, newReimb.getDescription());
			ps.setInt(3, newReimb.getAuthor());;
			ps.setInt(4, newReimb.getTypeId());
			
			return ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Reimbursement> getReimbByUser(int userId) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursement WHERE reimb_author = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			
			List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
			while(rs.next()) {
				reimbList.add(getReimbFromSQL(rs));
			}
			return reimbList;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getReimbByStatus(int statusId) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursement WHERE reimb_status_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, statusId);
			
			ResultSet rs = ps.executeQuery();
			
			List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
			while(rs.next()) {
				reimbList.add(getReimbFromSQL(rs));
			}
			return reimbList;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int approve(int reimbId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deny(int reimbId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setStatus(int reimbId, int statusId, int resolverId) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "UPDATE reimbursement SET "
					+ "reimb_resolved = CURRENT_TIMESTAMP, "
					+ "reimb_resolver = ?, "
					+ "reimb_status_id = ? WHERE reimb_id = ?";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, resolverId);
			ps.setInt(2, statusId);
			ps.setInt(3, reimbId);
			
			return ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return 0;
	}
	

}
