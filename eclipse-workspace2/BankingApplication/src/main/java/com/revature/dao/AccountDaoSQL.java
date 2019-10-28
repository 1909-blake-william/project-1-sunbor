package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.util.AuthUtil;
import com.revature.util.ConnectionUtil;

public class AccountDaoSQL implements AccountDao {

	private AuthUtil auth = AuthUtil.instance;
	private UserDao userDao = UserDao.currentImplementation;
	
	public Account accountFromSQL(ResultSet rs) throws SQLException {
		//get fields
		int id = rs.getInt("account_id");
		String accountName = rs.getString("account_name");
		int ownerId = rs.getInt("owner_id");
		User owner = userDao.findById(ownerId);
		double balance = rs.getFloat("balance");
		boolean opened = rs.getInt("opened") == 0 ? false : true;
				
		//return new account object
		return new Account(id, accountName, owner, balance, opened);
	}
	
	public boolean validateAccount(int id) {
		//validate selection
		try(Connection c = ConnectionUtil.getConnection()){
			//check that account belongs to user and is not closed
			String sql1 = "SELECT * FROM bank_accounts WHERE account_id = ? AND owner_id = ? AND opened = 1";
			PreparedStatement ps1 = c.prepareStatement(sql1);
			ps1.setInt(1, id);
			ps1.setInt(2, auth.getCurrentUser().getId());
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next()) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public int add(Account a) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO bank_accounts (account_id, account_name, owner_id, balance, opened) "
					+ "VALUES (BANK_ACCOUNTS_ID_SEQ.nextval, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, a.getAccountName());
			ps.setInt(2, a.getOwner().getId());
			ps.setFloat(3, (float) a.getBalance());
			ps.setInt(4, 1);
			
			return ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int remove(int id) {
		//close not delete
		//validate selection
		if(!validateAccount(id)) {
			System.out.println("Invalid crime");
			return 0;
		}
		//just change the boolean
		try(Connection c = ConnectionUtil.getConnection()){
			//perform update
			String sql = "UPDATE bank_accounts SET opened = 0 WHERE account_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			
			//returns 0 if failed for whatever reason
			return ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Account viewOne(int id) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_accounts WHERE account_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return accountFromSQL(rs);
			}
			else {
				return null;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Account> viewOwned() {

		//get id of current user
		int currentId = auth.getCurrentUser().getId();
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_accounts WHERE owner_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, currentId);
			ResultSet rs = ps.executeQuery();
			
			List<Account> accountList = new ArrayList<Account>();
			while(rs.next()) {
				accountList.add(accountFromSQL(rs));
			}
			return accountList;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int deposit(int id, double amount) {
		//prevent user from accessing unowned account
		//prevent user from accessing closed account
		if(!validateAccount(id)) {
			System.out.println("Invalid crime");
			return 0;
		}
		if(amount < 0) {
			System.out.println("Invalid time");
			return 0;
		}
		try(Connection c = ConnectionUtil.getConnection()){
			//get original balance
			//modify balance
			//update
			//add to transaction history
			String sql = "SELECT balance FROM bank_accounts WHERE account_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			
			double balance = 0;
			ResultSet rs = ps.executeQuery();
			//should get the only square
			if(rs.next()) {
				balance = rs.getDouble(1);
			}
			
			//change value
			balance += amount;
			
			//update
			sql = "UPDATE bank_accounts SET balance = ? WHERE account_id = ?";
			ps = c.prepareStatement(sql);
			ps.setDouble(1, balance);
			ps.setInt(2, id);
			
			return ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int withdraw(int id, double amount) {
		//prevent user from accessing unowned account
		//prevent user from accessing closed account
		//prevent negative numbers
		if(!validateAccount(id)) {
			System.out.println("Invalid crime");
			return 0;
		}
		if(amount < 0) {
			System.out.println("Invalid time");
			return 0;
		}
		try(Connection c = ConnectionUtil.getConnection()){
			//get original balance
			//modify balance
			//update
			//add to transaction history
			String sql = "SELECT balance FROM bank_accounts WHERE account_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			
			double balance = 0;
			ResultSet rs = ps.executeQuery();
			//should get the only square
			if(rs.next()) {
				balance = rs.getDouble(1);
			}
			
			//change value
			balance -= amount;
			
			//update
			sql = "UPDATE bank_accounts SET balance = ? WHERE account_id = ?";
			ps = c.prepareStatement(sql);
			ps.setDouble(1, balance);
			ps.setInt(2, id);
			
			return ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Account> viewAll() {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_accounts";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<Account> accountList = new ArrayList<Account>();
			while(rs.next()) {
				accountList.add(accountFromSQL(rs));
			}
			return accountList;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Account> initializeList() {
		// TODO Auto-generated method stub
		return null;
	}

}
