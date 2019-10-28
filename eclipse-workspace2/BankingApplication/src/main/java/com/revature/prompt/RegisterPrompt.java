package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.UserDao;
import com.revature.model.User;
import com.revature.util.Constants;

public class RegisterPrompt implements Prompt { 

	private Scanner scan = new Scanner(System.in);
	private UserDao userDao = UserDao.currentImplementation;
	
	@Override
	public Prompt run() {
		//get username from user
		System.out.println("Enter your new clown name");
		String newName = scan.nextLine();
		//check to see that size is not over limit
		if(newName.length() > Constants.maxTextLength) {
			System.out.println("Get a nickname");
			return new LoginPrompt();
		}
		
		//check if username is already taken
		if(userDao.findByUsername(newName) != null) {
			//berate user and go back to menu
			System.out.println("Be more creative");
			return new LoginPrompt();
		}
		
		//get password
		System.out.println("Enter new password"); 
		String newPass = scan.nextLine();
		if(newPass.length() > Constants.maxTextLength) {
			System.out.println("No need to be so paranoid");
			return new LoginPrompt();
		}
		
		//ask if admin
		System.out.println("Is your refrigerator running? (y/n)");
		String rankInput = scan.nextLine();
		String newRank;
		switch (rankInput) {
		case "y":
			newRank = "admin";
			System.out.println("Well you better go catch it then!");
			break;
		case "n":
			newRank = "client";
			System.out.println("Oh");
			break;
		default:
			System.out.println("That's not funny");
			return new LoginPrompt();
		}
		
		//make new user object
		User newUser = new User(1, newName, newPass, newRank);
		//save user to dao
		userDao.save(newUser);
		
		// TODO Auto-generated method stub
		return new LoginPrompt();
	}

}
