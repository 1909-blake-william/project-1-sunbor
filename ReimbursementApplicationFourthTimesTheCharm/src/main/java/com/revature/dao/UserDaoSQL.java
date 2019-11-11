package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserDaoSQL implements UserDao {

	public User getUserFromSQL(ResultSet rs) throws SQLException {
		int id = rs.getInt("users_id");
		String username = rs.getString("username");
		String password = rs.getString("password");
		String first_name = rs.getString("first_name");
		String last_name = rs.getString("last_name");
		String email = rs.getString("email");
		int role_id = rs.getInt("role_id");
		
		return new User(id, username, password, first_name, last_name, email, role_id);
	}
	
	public User getUserById(int id) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM users WHERE users_id = ?";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return getUserFromSQL(rs);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsers() {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM users";
			
			PreparedStatement ps = c.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			List<User> userList = new ArrayList<User>();
			while(rs.next()) {
				userList.add(getUserFromSQL(rs));
			}
			return userList;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	public int saveUser(User newUser) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO users (users_id, username, password, first_name, last_name, email, role_id) " + 
					"    VALUES (REIMB_USER_SEQ.nextval, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, newUser.getUsername());
			ps.setString(2, newUser.getPassword());
			ps.setString(3,  newUser.getFirst_name());
			ps.setString(4, newUser.getLast_name());
			ps.setString(5, newUser.getEmail());
			ps.setInt(6, newUser.getRole_id());
			
			return ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return 0;
	}

	public User findByUsername(String username) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM users WHERE username = ?";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return getUserFromSQL(rs);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	public User findByUsernameAndPassword(String username, String password) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return getUserFromSQL(rs);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

}
