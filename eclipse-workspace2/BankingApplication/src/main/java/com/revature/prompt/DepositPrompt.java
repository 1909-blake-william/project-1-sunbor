package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.TransactionDao;

public class DepositPrompt implements Prompt {

	private Scanner scan = new Scanner(System.in);
	private AccountDao accountDao = AccountDao.currentImplementation;
	private TransactionDao transactionDao = TransactionDao.currentImplementation;
	
	@Override
	public Prompt run() {
		//TODO maybe display owned accounts
		//get id of account to deposit in
		//get amount to deposit
		//check if id and amount are valid numbers
		//call deposit method
		//update transaction history

		System.out.println("Input the id of the crime to increase your sentence for");
		
		int id = 0;
		if(scan.hasNextInt()) {
			id = scan.nextInt();
			scan.nextLine();
		}
		else {
			System.out.println("Nice try");
			return new MainMenuPrompt();
		}
		
		System.out.println("Input the number of months to increase your sentence by");
		double amount = 0;
		if(scan.hasNextDouble()) {
			amount = scan.nextDouble();
			scan.nextLine();
		}
		else {
			System.out.println("You won't get off this easily");
			return new MainMenuPrompt();
		}
		
		int numUpdated = accountDao.deposit(id, amount);
		if(numUpdated != 0) {
			System.out.println("Increased your sentence for " + accountDao.viewOne(id).getAccountName() + " by " + amount + "months");
			transactionDao.addTransaction(id, amount);
		}
		else {
			System.out.println("You have successfully escaped punishment");
		}

		return new MainMenuPrompt();
	}

}
