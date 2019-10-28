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
import java.util.Map;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.util.AuthUtil;

public class AccountDaoSerialization implements AccountDao {

	private AuthUtil auth = AuthUtil.instance;
	private UserDao userDao = UserDao.currentImplementation;
	
	@Override
	public int add() {
		//get account owner
		User owner = auth.getCurrentUser();
		//make new account
		Account newAccount = new Account(1, owner, 0, null);
		//add account to user's account list
		owner.addAccount(newAccount);
		//add account to dao
		//get account list from dao
		//generate random id
		//add to list
		//save list to dao
		List<Account> accountList = viewAll();
		int randomId = (int) Math.floor(Math.random() * 1000000) + 1;
		newAccount.setId(randomId);
		accountList.add(newAccount);
		try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/accounts.txt"))){
			outStream.writeObject(accountList);
			return randomId;
		}
		catch(IOException e) {
			System.out.println("failed to write new account to file");
			e.printStackTrace();
		}
		
		//save user dao
		userDao.modifyOne(owner);
		
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(int id) {
		//
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Account viewOne(int id) {
		//get list of all accounts
		//find account with this id
		//return this account
		List<Account> accountList = viewAll();
		for(Account a: accountList) {
			if(a.getId() == id) {
				return a;
			}
		}
		//if account not found
		return null;
	}

	@Override
	public List<Account> viewOwned() {
		//get list of all accounts
		//make new list of owned accounts
		//for each account in full list
		//if user is current user
		//add account to list
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deposit(double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw(double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Integer, Double> viewHistory(Account viewThis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> viewAll() {
		//make input object from file
		try(ObjectInputStream inStream = new ObjectInputStream(new FileInputStream("src/main/resources/accounts.txt"))){
			//make list of users from file
			List <Account> accountList = (List<Account>) inStream.readObject();
			return accountList;
		}
		catch(IOException | ClassNotFoundException e) {
			System.out.println("failed to find list of accounts");
			e.printStackTrace();
		}
		//if list not found, return empty list
		List<Account> badList2 = new ArrayList<Account>();
		return badList2;
	}

	@Override
	public List<Account> initializeList() {
		//make list
		List<Account> allAccounts = new ArrayList<Account>();
		//make new account
		//get account owner
		User owner = userDao.findByUsername("me");
		Account secretNaziGold = new Account(666, owner, 1000000000000000.00, null);
		//add one user to list
		allAccounts.add(secretNaziGold);
		
		//make file object?
		File newFile = new File("src/main/resources/accounts.txt");
		//see if file exists
		//if not, make new file and add list of single user to it
		if(!newFile.exists()) {
			//write list to file
			try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(newFile))){
				outStream.writeObject(allAccounts);
			}
			catch(IOException e) {
				System.out.println("failed to write default account list to file");
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		return allAccounts;
	}

}

*/
