package com.revature.dao;

import java.util.List;

import com.revature.model.Transaction;

public interface TransactionDao {
	
	TransactionDao currentImplementation = new TransactionDaoSQL();
	
	Transaction viewTransaction(int id);
	
	List<Transaction> viewAll();
	
	List<Transaction> viewOwn();
	
	int addTransaction(int accountId, double amount);

}
