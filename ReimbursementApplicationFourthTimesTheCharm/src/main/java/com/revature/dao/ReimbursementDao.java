package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.User;

public interface ReimbursementDao {
	
	ReimbursementDao currentImplementation = new ReimbursementDaoSQL();
	
	Reimbursement getReimbById(int id);
	
	List<Reimbursement> getAllReimb();
	
	int saveReimb(Reimbursement newReimb);
	
	List<Reimbursement> getReimbByUser(User u);
	
	List<Reimbursement> getReimbByStatus(int statusId);
	
	int approve(Reimbursement r);
	
	int deny(Reimbursement r);
	
	int setStatus(Reimbursement r, int statusId);

}
