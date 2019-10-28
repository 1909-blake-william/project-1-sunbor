package com.revature.dao;

import java.util.List;

import com.revature.model.Account;

public interface AccountDao {
	
	AccountDao currentImplementation = new AccountDaoSQL();
	
	//add account
	int add(Account a);
	
	//remove account
	int remove(int id);
	
	//view this account
	Account viewOne(int id);
	
	//view all accounts from this user
	List<Account> viewOwned();
	
	//deposit
	int deposit(int id, double amount);
	
	//withdraw
	int withdraw(int id, double amount);
		
	//ADMIN ONLY!!!!!

	//view all accounts
	List<Account> viewAll();
	
	//initialize list so it doesnt break at login
	List<Account> initializeList();
}
