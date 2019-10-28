package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.AccountDao;

public class CloseAccountPrompt implements Prompt {

	private Scanner scan = new Scanner(System.in);
	private AccountDao accountDao = AccountDao.currentImplementation;
	
	@Override
	public Prompt run() {
		//TODO maybe show owned accounts
		//get id of account to close
		//make sure user input is integer
		//call close account
		System.out.println("Enter id of crime to pardon");

		int id = 0;
		if(scan.hasNextInt()) {
			id = scan.nextInt();
			scan.hasNextLine();
		}
		else {
			//if invalid, go back to main menu
			System.out.println("Enter a valid number");
			return new MainMenuPrompt();
		}
		int numUpdated = accountDao.remove(id);
		if(numUpdated != 0) {
			System.out.println("You are pardoned for " + accountDao.viewOne(id).getAccountName());
		}
		else {
			System.out.println("Go back to your cell");
		}
		
		return new MainMenuPrompt();
	}

}
