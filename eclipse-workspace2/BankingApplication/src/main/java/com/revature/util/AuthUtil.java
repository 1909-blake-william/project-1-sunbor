package com.revature.util;

import com.revature.dao.UserDao;
import com.revature.model.User;

public class AuthUtil {
	//the one instance of the class
	public static final AuthUtil instance = new AuthUtil();

	//dao
	private UserDao userDao = UserDao.currentImplementation;
	private User currentUser = null;
	
	//private constructor
	private AuthUtil() {
		super();
	}
	
	//performs action of logging in when credentials are correct
	//works as a setter method
	public User login(String username, String password) {
		User u = userDao.findByUsernameAndPassword(username, password);
		currentUser = u;
		return u;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	//logout
	public User logout() {
		currentUser = null;
		return null;
	}


}
