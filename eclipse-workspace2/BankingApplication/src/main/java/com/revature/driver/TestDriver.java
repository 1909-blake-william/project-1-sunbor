package com.revature.driver;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.dao.TransactionDao;
import com.revature.dao.UserDao;
import com.revature.model.Transaction;
import com.revature.util.AuthUtil;


public class TestDriver {
	private static Logger log = Logger.getRootLogger();
	//private AuthUtil auth = AuthUtil.instance;
	
	public static void main(String[] args) {

		
		UserDao userDao = UserDao.currentImplementation;
		AccountDao accountDao = AccountDao.currentImplementation;
		TransactionDao transactionDao = TransactionDao.currentImplementation;
		

//		log.info("testing userList");
//		List<User> userList = userDao.findAll();
//		userList.forEach(user -> {
//			System.out.println(user);
//		});
		
//		log.info("testing findByUserName");
//		System.out.println(userDao.findByUsername("joe"));
		
//		log.info("testing findByUsernameAndPassword");
//		System.out.println(userDao.findByUsernameAndPassword("john", "uncool"));
		
//		log.info("testing save");
//		User testU = new User(5, "jake", "chill", "customer");
//		System.out.println(userDao.save(testU));
//		userList = userDao.findAll();
//		userList.forEach(user -> {
//			System.out.println(user);
//		});
		
//		log.info("testing findByUserId");
//		System.out.println(userDao.findById(63));
		

//		log.info("testing viewAll accounts");
//		List<Account> accountList = accountDao.viewAll();
//		accountList.forEach(account -> {
//			System.out.println(account);
//		});
		
//		log.info("testing add account");
//		Account testA = new Account(1, "bye", userDao.findById(1), 150, true);
//		System.out.println(accountDao.add(testA));
//		accountList = accountDao.viewAll();
//		accountList.forEach(account -> {
//			System.out.println(account);
//		});
		
//		log.info("testing close account");
//		System.out.println(accountDao.remove(8));
//		accountList = accountDao.viewAll();
//		accountList.forEach(account -> {
//			System.out.println(account);
//		});
		
//		log.info("testing viewOne account");
//		System.out.println(accountDao.viewOne(1));
		
		log.info("logging in");
		AuthUtil auth = AuthUtil.instance;
		auth.login("joe", "cool");
		System.out.println(auth.getCurrentUser());
		
//		log.info("testing viewOwned accounts");
//		List<Account> ownedAccounts = accountDao.viewOwned();
//		ownedAccounts.forEach(account -> {
//			System.out.println(account);
//		});
		
//		log.info("testing close account with user validation");
//		System.out.println(accountDao.remove(1));
//		accountList = accountDao.viewAll();
//		accountList.forEach(account -> {
//			System.out.println(account);
//		});
		
//		log.info("testing deposit");
//		System.out.println(accountDao.deposit(21, 20));
//		accountList = accountDao.viewAll();
//		accountList.forEach(account -> {
//			System.out.println(account);
//		});

//		Timestamp ohno = new Timestamp(System.currentTimeMillis());
//		System.out.println(ohno);
//		int blargh = 0;
//		try(Connection c = ConnectionUtil.getConnection()){
//			String sql = "SELECT * FROM test WHERE test_num = 1";
//			PreparedStatement ps = c.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			if(rs.next()) {
//				blargh = rs.getInt(1);
//				ohno = rs.getTimestamp(2);
//			}
//		}
//		catch(SQLException e) {
//			e.printStackTrace();
//		}
//		System.out.println(blargh + " " + ohno);
		
		log.info("testing viewTransaction");
		System.out.println(transactionDao.viewTransaction(1));
		
		log.info("testing viewAllTransactions");
		List<Transaction> transList = transactionDao.viewAll();
		transList.forEach(trans -> {
			System.out.println(trans);
		});
		
//		log.info("testing addTransaction");
//		System.out.println(transactionDao.addTransaction(1, -100));
//		transList = transactionDao.viewAll();
//		transList.forEach(trans -> {
//			System.out.println(trans);
//		});
		
		log.info("testing viewOwned");
		transList = transactionDao.viewOwn();
		transList.forEach(trans -> {
			System.out.println(trans);
		});
	}
}
