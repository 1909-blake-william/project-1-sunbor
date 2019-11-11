package com.revature.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDao;
import com.revature.model.User;

public class UserServlet extends HttpServlet {
	
	private UserDao userDao = UserDao.currentImplementation;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<User> userList;
		
		userList = userDao.getAllUsers();
		
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(userList);
		
		resp.addHeader("content-type", "application/json");
		resp.getWriter().write(json);
	}

}
