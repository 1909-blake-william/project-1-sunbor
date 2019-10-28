package com.revature.dao;

import java.util.List;

import com.revature.model.User;

public interface UserDao {
	
	UserDao currentImplementation = new UserDaoSQL();
	
	//create user
	int save(User u);
	
	//get user
	//find by id
	User findById(int id);
	//find by username
	User findByUsername(String username);
	//find by username and password
	User findByUsernameAndPassword(String username, String password);
	
	//modify user
	User modifyOne(User u);
	
	//ADMIN ONLY!!!!!!!
	
	//view all users
	List<User> findAll();
	
	//initialize list so it doesnt break at login
	List<User> initializeList();

}
