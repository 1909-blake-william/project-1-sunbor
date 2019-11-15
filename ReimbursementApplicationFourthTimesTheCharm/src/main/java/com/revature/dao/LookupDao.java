package com.revature.dao;

import java.util.List;

import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.UserRole;

public interface LookupDao {
	
	LookupDao currentImplementation = new LookupDaoSQL();
	
	List<UserRole> getAllRoles();
	
	List<ReimbursementStatus> getAllStatus();
	
	List<ReimbursementType> getAllTypes();

}
