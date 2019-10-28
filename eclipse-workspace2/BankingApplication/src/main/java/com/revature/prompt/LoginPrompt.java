package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.UserDao;
import com.revature.model.User;
import com.revature.util.AuthUtil;

public class LoginPrompt implements Prompt{

	private Scanner scan = new Scanner(System.in);
	private UserDao userDao = UserDao.currentImplementation;
	private AuthUtil auth = AuthUtil.instance;
	
	public Prompt run() {
		
		//give prompt
		//save choice
		System.out.println("Enter 1 to get locked up");
		System.out.println("Enter 2 to register as a criminal clown");
		String choice = scan.nextLine();
		
		//enter 1 to login
		//enter 2 to create new account?
		switch(choice) {
		case "1":{
			//get username and password
			System.out.println("Enter your name");
			String username = scan.nextLine();
			System.out.println("Enter your password");
			String password = scan.nextLine();
			
			//find user with the username and password
			User u = userDao.findByUsernameAndPassword(username, password);
			
			//if correct, go to main menu
			//if incorrect, to back to login
			if(u == null) {
				System.out.println("Incorrect name or password");
				break;
			}
			else {
				//set current user in AuthUtil
				auth.login(username, password);
				//go to main menu
				return new MainMenuPrompt();
			}
			
		}
		case "2":{
			//go to registration prompt
			return new RegisterPrompt();
		}
		}
		
		
		//prompt for username
		//prompt for password
		
		//if incorrect, return this prompt
		//if correct, go to main menu prompt
		//pass in user object?
		
		// TODO Auto-generated method stub
		return this;
	}

}
