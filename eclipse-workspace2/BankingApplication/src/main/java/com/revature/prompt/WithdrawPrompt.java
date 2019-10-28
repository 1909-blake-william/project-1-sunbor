package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.TransactionDao;

public class WithdrawPrompt implements Prompt {

	private Scanner scan = new Scanner(System.in);
	private AccountDao accountDao = AccountDao.currentImplementation;
	private TransactionDao transactionDao = TransactionDao.currentImplementation;
	
	@Override
	public Prompt run() {
		//TODO maybe display owned accounts
		//get id of account to withdraw from
		//get amount to withdraw
		//check if id and amount are valid numbers
		//call deposit method

		System.out.println("Input the id of the crime to reduce your sentence for");

		int id = 0;
		if(scan.hasNextInt()) {
			id = scan.nextInt();
			scan.nextLine();
		}
		else {
			System.out.println("This is bad behavior and will cancel out your reward");
			return new MainMenuPrompt();
		}
		
		System.out.println("Input the number of months to reduce your sentence by");
		double amount = 0;
		if(scan.hasNextDouble()) {
			amount = scan.nextDouble();
			scan.nextLine();
		}
		else {
			System.out.println("This is bad behavior and will cancel out your reward");
			return new MainMenuPrompt();
		}
		
		int numUpdated = accountDao.withdraw(id, amount);
		if(numUpdated != 0) {
			System.out.println("Reduced your sentence for " + accountDao.viewOne(id).getAccountName() + " by " + amount + "months");

			transactionDao.addTransaction(id, -amount);
		}
		else {
			System.out.println("This is bad behavior and will cancel out your reward");
		}

		//TODO possibly close account if balance goes below 0
		
		return new MainMenuPrompt();
	}

}
