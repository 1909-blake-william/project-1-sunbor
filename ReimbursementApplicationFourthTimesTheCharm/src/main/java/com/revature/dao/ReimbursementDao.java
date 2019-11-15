package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.User;

public interface ReimbursementDao {
	
	ReimbursementDao currentImplementation = new ReimbursementDaoSQL();
	
	Reimbursement getReimbById(int id);
	
	List<Reimbursement> getAllReimb();
	
	int saveReimb(Reimbursement newReimb);
	
	List<Reimbursement> getReimbByUser(int userId);
	
	List<Reimbursement> getReimbByStatus(int statusId);
	
	int approve(int reimbId);
	
	int deny(int reimbId);
	
	int setStatus(int reimbId, int statusId, int resolverId);

}
