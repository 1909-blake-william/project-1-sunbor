package com.revature.dao;

import java.util.List;

import com.revature.model.User;

public interface UserDao {
	
	UserDao currentImplementation = new UserDaoSQL();
	
	User getUserById(int id);
	
	List<User> getAllUsers();
	
	int saveUser(User newUser);
	
	User findByUsername(String username);
	
	User findByUsernameAndPassword(String username, String password);

}
