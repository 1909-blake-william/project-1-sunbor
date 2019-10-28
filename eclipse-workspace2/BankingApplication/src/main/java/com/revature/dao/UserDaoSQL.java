package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserDaoSQL implements UserDao {
	private static Logger log = Logger.getRootLogger();

	public User userFromSQL(ResultSet rs) throws SQLException {
		//get fields
		int id = rs.getInt("user_id");
		String username = rs.getString("username");
		String password = rs.getString("password");
		String role = rs.getString("role");
		
		//return new user object
		return new User(id, username, password, role);
	}
	
	@Override
	public int save(User u) {
		try(Connection c = ConnectionUtil.getConnection()){
			//make sql string
			String sql = "INSERT INTO bank_users (user_id, username, password, role) "
					+ "VALUES (BANK_USERS_ID_SEQ.nextval, ?, ?, ?)";
			//make statement object
			PreparedStatement ps = c.prepareStatement(sql);
			//change values
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getRole());
			
			//returns id maybe
			return ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public User findById(int id) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_users WHERE user_id = ?";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return userFromSQL(rs);
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
	public User findByUsername(String username) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_users WHERE username = ?";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return userFromSQL(rs);
			}
			else {
				return null;
			}
		}
		catch(SQLException e) {
			log.error("failed to find user with username");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_users WHERE username = ? AND password = ?";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return userFromSQL(rs);
			}
			else {
				return null;
			}
		}
		catch(SQLException e) {
			log.error("failed to find user with username");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User modifyOne(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		try(Connection c = ConnectionUtil.getConnection()){
//			log.info("making sql statement");
			String sql = "SELECT * FROM bank_users";
//			log.info("making prepared statement");
			PreparedStatement ps = c.prepareStatement(sql);
//			log.info("executing statement");
			ResultSet rs = ps.executeQuery();
//			log.info("making user list");
			List<User> userList = new ArrayList<User>();
//			log.info("adding to user list");
			while(rs.next()) {
				userList.add(userFromSQL(rs));
			}
			return userList;
		}
		catch(SQLException e){
			log.error("failed to find users from database");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<User> initializeList() {
		// TODO Auto-generated method stub
		return null;
	}

}
