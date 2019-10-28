/*

package com.revature.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.User;

public class UserDaoSerialization implements UserDao {

	public int save(User u) {
		//get list of all users from file
		List<User> allUsers = findAll();
		
		//make output object with file
		try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/users.txt"))){
			//generate random id
			int randomId = (int) Math.floor(Math.random() * 1000000) + 1;
			u.setId(randomId);
			allUsers.add(u);
			
			//save user back to file
			outStream.writeObject(allUsers);
			return randomId;
		}
		catch (IOException e) {
			System.out.println("failed to write new user to file");
			e.printStackTrace();
		}
		
		
		// TODO Auto-generated method stub
		return 0;
	}

	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public User findByUsername(String username) {
		//get list of users
		List<User> userList = findAll();
				
		//go through list of users
		//if username matches
		//return user
		for(User u: userList) {
			if(u.getUsername().equals(username)) {
				return u;
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

	public User findByUsernameAndPassword(String username, String password) {
		//get user with that username
		User u = findByUsername(username);
		//if no user found, return null
		if(u == null) {
			return null;
		}
		//if password matches
		//return user
		else if(u.getPassword().equals(password)) {
			return u;
		}
		//other possibilities: username matches, password doesnt
		return null;
	}
	
	@Override
	public User modifyOne(User u) {
		//get list
		List<User> userList = findAll();
		
		//variable to check if match found
		boolean found = false;
		
		//find user in list with same id
		for(int i=0; i<userList.size(); i++) {
			if(u.getId() == userList.get(i).getId()) {
				//replace user in list with input user
				userList.set(i, u);
				found = true;
			}
		}
		//save list
		//make output object with file
		try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/users.txt"))){
			//save user back to file
			outStream.writeObject(userList);
		}
		catch (IOException e) {
			System.out.println("failed to update file with user change");
			e.printStackTrace();
		}
		// return user saved, or null if no match found
		return found ? u : null;
	}

	
	public List<User> findAll() {
		//make input object from file
		try(ObjectInputStream inStream = new ObjectInputStream(new FileInputStream("src/main/resources/users.txt"))){
			//make list of users from file
			List <User> users = (List<User>) inStream.readObject();
			return users;
		}
		catch(IOException | ClassNotFoundException e) {
			System.out.println("failed to find list of users");
			e.printStackTrace();
		}
		//if list not found, return empty list
		List<User> sadFailList = new ArrayList<User>();
		return sadFailList;
	}

	@Override
	public List<User> initializeList() {
		//make list
		List<User> allUsers = new ArrayList<User>();
		//make new user
		User me = new User(666, "me", "stillme", "admin", null);
		//add one user to list
		allUsers.add(me);
		
		//make file object?
		File newFile = new File("src/main/resources/users.txt");
		//see if file exists
		//if not, make new file and add list of single user to it
		if(!newFile.exists()) {
			//write list to file
			try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(newFile))){
				outStream.writeObject(allUsers);
			}
			catch(IOException e) {
				System.out.println("failed to write default list to file");
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		return allUsers;
	}


}

*/
