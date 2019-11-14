package com.revature.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDao;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public class ReimbursementServlet extends HttpServlet {
	
	private ReimbursementDao reimbDao = ReimbursementDao.currentImplementation;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:5500");
		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		resp.addHeader("Access-Control-Allow-Headers",
				"Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Reimbursement> reimbList;
		
		User currentUser = (User) req.getSession().getAttribute("user");
		System.out.println(currentUser);
		
		reimbList = reimbDao.getReimbByUser(currentUser.getId());
		
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(reimbList);
		
		resp.addHeader("content-type", "application/json");
		resp.getWriter().write(json);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper om = new ObjectMapper();
		Reimbursement newReimb = (Reimbursement) om.readValue(req.getReader(), Reimbursement.class);
		
		int id = reimbDao.saveReimb(newReimb);
		newReimb.setId(id);
		
		String json = om.writeValueAsString(newReimb);
		
		resp.getWriter().write(json);
		resp.setStatus(201);
		
	}
}
