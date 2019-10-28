package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.model.Account;
import com.revature.util.AuthUtil;
import com.revature.util.Constants;

public class AddAccountPrompt implements Prompt {

	private Scanner scan = new Scanner(System.in);
	private AccountDao accountDao = AccountDao.currentImplementation;
	private AuthUtil auth = AuthUtil.instance;
	
	@Override
	public Prompt run() {
		//get account name
		System.out.println("What are you in for?");
		String accountName = scan.nextLine();
		//check if too long
		if(accountName.length() > Constants.maxTextLength) {
			System.out.println("Sorry, I stopped paying attention");
			return new MainMenuPrompt();
		}
		//get starting balance
		System.out.println("Enter your sentence in months");
		double startingBalance = 0;
		if(scan.hasNextDouble()) {
			startingBalance = scan.nextDouble();
			scan.nextLine();
		}
		else {
			System.out.println("Enter a valid number");
			return new MainMenuPrompt();
		}
		//make account object
		Account newAccount = new Account(1, accountName, auth.getCurrentUser(), startingBalance, true);
		//add new account object to dao
		accountDao.add(newAccount);
		//TODO maybe add starting balance to transaction history

		return new MainMenuPrompt();
	}

}
