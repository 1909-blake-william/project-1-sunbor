package com.revature.driver;

import java.util.List;

import com.revature.dao.UserDao;
import com.revature.model.User;

public class TestDriver {
	public static void main(String[] args) {
		UserDao userDao = UserDao.currentImplementation;
		
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
		
		System.out.println(userDao.findByUsername("admin"));
		System.out.println(userDao.findByUsernameAndPassword("admin", "pass"));
	}
}
