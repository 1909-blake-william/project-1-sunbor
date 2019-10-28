package com.revature.prompt;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.TransactionDao;
import com.revature.dao.UserDao;
import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.util.AuthUtil;

public class MainMenuPrompt implements Prompt {

	private Scanner scan = new Scanner(System.in);
	private UserDao userDao = UserDao.currentImplementation;
	private AccountDao accountDao = AccountDao.currentImplementation;
	private TransactionDao transactionDao = TransactionDao.currentImplementation;
	private AuthUtil auth = AuthUtil.instance;
	
	public Prompt run() {
		//get user info
		//see if user is admin
		String userRole = auth.getCurrentUser().getRole();
		
		//1 to add account
		//2 to remove account
		//3 to view accounts
		//4 to deposit
		//5 to withdraw
		//6 to view transaction history
		//7 to logout
		
		//super secret methods!!!!!!
		//8 to view all users
		//9 to view all accounts
		//10 to view all transactions
		
		System.out.println("Press 1 to commit a crime");
		System.out.println("Press 2 to get pardoned");
		System.out.println("Press 3 to view your crimal record");
		System.out.println("Press 4 to perform bad behavior");
		System.out.println("Press 5 to perform good behavior");
		System.out.println("Press 6 to view your behavior records");
		System.out.println("Press 7 to escape");
		
		if(userRole.equals("admin")) {
			System.out.println("Super secret warden privileges");
			System.out.println("Press 8 to view all inmates");
			System.out.println("Press 9 to view all criminal records");
			System.out.println("Press 10 to view all prison behavior records");
		}
		
		//get user input
		String input = scan.nextLine();
		switch (input) {
		case "1":
			//add account
			return new AddAccountPrompt();
		case "2":
			//remove account
			//new prompt
			return new CloseAccountPrompt();
		case "3":
			//view accounts
			List<Account> accountList1 = accountDao.viewOwned();
			if(accountList1.size() == 0) {
				System.out.println("No crimes committed");
			}
			for(Account a : accountList1) {
				System.out.println(a);
			}
			break;
		case "4":
			//make deposit
			//new prompt
			return new DepositPrompt();
		case "5":
			//make withdrawal
			//new prompt
			return new WithdrawPrompt();
		case "6":
			//view transaction history
			List<Transaction> transList1 = transactionDao.viewOwn();
			if(transList1.size() == 0) {
				System.out.println("No prison behavior found");
			}
			for(Transaction t : transList1) {
				System.out.println(t);
			}
			break;
		case "7":
			//logout
			System.out.println("You grab a bundle of balloons and float over the wall");
			auth.logout();
			return new LoginPrompt();
		case "8":
			//view all users
			//only if admin
			if(userRole.equals("admin")) {
				List<User> userList = userDao.findAll();
				for(User u: userList) {
					System.out.println(u);
				}
			}
			else {
				System.out.println("You don't have a circus ticket");
			}
			break;
		case "9":
			//view all accounts	
			//only if admin
			if(userRole.equals("admin")) {
				List<Account> accountList2 = accountDao.viewAll();
				if(accountList2.size() == 0) {
					System.out.println("No crimes committed");
				}
				for(Account a: accountList2) {
					System.out.println(a);
				}
			}
			else {
				System.out.println("You don't have a circus ticket");
			}
			break;
		case "10":
			//view all transactions
			//only if admin
			if(userRole.equals("admin")) {
				List<Transaction> transList2 = transactionDao.viewAll();
				if(transList2.size() == 0) {
					System.out.println("No prison behavior found");
				}
				for(Transaction t: transList2) {
					System.out.println(t);
				}
			}
			else {
				System.out.println("You don't have a circus ticket");
			}
			break;
		default:
			System.out.println("That's not the number I'm thinking of");
			break;
		}
		return new MainMenuPrompt();
	}

}
