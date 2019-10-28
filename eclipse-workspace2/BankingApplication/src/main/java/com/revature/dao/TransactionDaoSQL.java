package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.util.AuthUtil;
import com.revature.util.ConnectionUtil;

public class TransactionDaoSQL implements TransactionDao {

	private AuthUtil auth = AuthUtil.instance;
	private AccountDao accountDao = AccountDao.currentImplementation;
	
	public Transaction transactionFromSQL(ResultSet rs) throws SQLException {
		int id = rs.getInt("transaction_id");
		int accountId = rs.getInt("account_id");
		Account account = accountDao.viewOne(accountId);
		double amount = rs.getDouble("amount");
		Timestamp timestamp = rs.getTimestamp("time_date");
		
		return new Transaction(id, account, amount, timestamp);
	}
	
	@Override
	public Transaction viewTransaction(int id) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_transactions WHERE transaction_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return transactionFromSQL(rs);
			}
			else {
				return null;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Transaction> viewAll() {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_transactions ORDER BY time_date";
			PreparedStatement ps = c.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			List<Transaction> transList = new ArrayList<Transaction>();
			while(rs.next()) {
				transList.add(transactionFromSQL(rs));
			}
			return transList;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Transaction> viewOwn() {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_transactions t "
					+ "LEFT JOIN bank_accounts a "
					+ "ON (t.account_id = a.account_id) "
					+ "WHERE a.owner_id = ? "
					+ "ORDER BY time_date";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, auth.getCurrentUser().getId());
			
			ResultSet rs = ps.executeQuery();
			
			List<Transaction> ownList = new ArrayList<Transaction>();
			while(rs.next()) {
				ownList.add(transactionFromSQL(rs));
			}
			
			return ownList;
		
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int addTransaction(int accountId, double amount) {
		//no input checking, only use if withdraw/deposit are successful
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO bank_transactions (transaction_id, account_id, amount, time_date) "
					+ "VALUES (BANK_TRANSACTIONS_ID_SEQ.nextval, ?, ?, CURRENT_TIMESTAMP)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, accountId);
			ps.setDouble(2, amount);
			
			return ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}


}
