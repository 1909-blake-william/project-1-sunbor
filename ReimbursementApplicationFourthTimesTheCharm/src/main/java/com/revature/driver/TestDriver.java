package com.revature.driver;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.model.Reimbursement;

public class TestDriver {
	public static void main(String[] args) {
		UserDao userDao = UserDao.currentImplementation;
		ReimbursementDao reimbDao = ReimbursementDao.currentImplementation;
		
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
		
		List<Reimbursement> reimbList = reimbDao.getAllReimb();
		reimbList.forEach(reimb -> {
			System.out.println(reimb);
		});
	}
}
