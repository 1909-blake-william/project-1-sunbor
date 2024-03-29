package com.revature.driver;

import java.util.List;

import com.revature.dao.LookupDao;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.UserRole;

public class TestDriver {
	public static void main(String[] args) {
		UserDao userDao = UserDao.currentImplementation;
		ReimbursementDao reimbDao = ReimbursementDao.currentImplementation;
		LookupDao lookupDao = LookupDao.currentImplementation;
		
//		List<User> userList = userDao.getAllUsers();
//		userList.forEach(user -> {
//			System.out.println(user);
//		});
		
//		System.out.println(userDao.getUserById(1));
		
//		User newUser = new User(0, "jerk2", "pass", "santa", "claus", "santa@northpole.com", 2);
//		userDao.saveUser(newUser);
//		List<User> userList = userDao.getAllUsers();
//		userList.forEach(user -> {
//			System.out.println(user);
//		});
		
//		System.out.println(userDao.findByUsername("admin"));
//		System.out.println(userDao.findByUsernameAndPassword("admin", "pass"));
		
//		List<Reimbursement> reimbList = reimbDao.getReimbByUser(2);
//		reimbList.forEach(reimb -> {
//			System.out.println(reimb);
//		});
		
//		Reimbursement newReimb = new Reimbursement(10, 40, null, null, "oh no", null, 2, 0, 1, 3);
//		reimbDao.saveReimb(newReimb);
//		reimbList.forEach(reimb -> {
//			System.out.println(reimb);
//		});
		
		//reimbDao.setStatus(1, 2, 1);
//		reimbList = reimbDao.getReimbByStatus(1);
//		reimbList.forEach(reimb -> {
//		System.out.println(reimb);
//		});
		
		List<UserRole> roleList = lookupDao.getAllRoles();
		List<ReimbursementStatus> statusList = lookupDao.getAllStatus();
		List<ReimbursementType> typeList = lookupDao.getAllTypes();
		roleList.forEach(role -> {
			System.out.println(role);
		});
		statusList.forEach(status -> {
			System.out.println(status);
		});
		typeList.forEach(type -> {
			System.out.println(type);
		});
	}
}
