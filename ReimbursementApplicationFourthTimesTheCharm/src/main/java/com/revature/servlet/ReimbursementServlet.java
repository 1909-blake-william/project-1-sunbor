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
import com.revature.model.UpdateData;
import com.revature.model.User;

public class ReimbursementServlet extends HttpServlet {
	
	private ReimbursementDao reimbDao = ReimbursementDao.currentImplementation;
	private final ObjectMapper om = new ObjectMapper();
	
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
		System.out.println("uri = " + req.getRequestURI());
		if ("/ReimbursementApplicationFourthTimesTheCharm/reimbursement/employee".equals(req.getRequestURI())) {
			List<Reimbursement> reimbList;
			
			User currentUser = (User) req.getSession().getAttribute("user");
			System.out.println(currentUser);
			
			reimbList = reimbDao.getReimbByUser(currentUser.getId());
			
			//ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(reimbList);
			
			resp.addHeader("content-type", "application/json");
			resp.getWriter().write(json);
		}
		else if("/ReimbursementApplicationFourthTimesTheCharm/reimbursement/manager".equals(req.getRequestURI())) {
			List<Reimbursement> reimbList;
			
			reimbList = reimbDao.getAllReimb();
			
			//ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(reimbList);
			
			resp.addHeader("content-type", "application/json");
			resp.getWriter().write(json);
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//ObjectMapper om = new ObjectMapper();

		if ("/ReimbursementApplicationFourthTimesTheCharm/reimbursement/employee".equals(req.getRequestURI())) {
			Reimbursement newReimb = (Reimbursement) om.readValue(req.getReader(), Reimbursement.class);
			System.out.println(newReimb);
			int id = reimbDao.saveReimb(newReimb);
			newReimb.setId(id);
			
			String json = om.writeValueAsString(newReimb);
			
			resp.getWriter().write(json);
			resp.setStatus(201);
		}
		else if("/ReimbursementApplicationFourthTimesTheCharm/reimbursement/manager/filter".equals(req.getRequestURI())) {
			UpdateData upData = om.readValue(req.getReader(), UpdateData.class);
			
			List<Reimbursement> reimbList;
			
			reimbList = reimbDao.getReimbByStatus(upData.getStatusId());
			
			String json = om.writeValueAsString(reimbList);
			resp.addHeader("content-type", "application/json");
			resp.getWriter().write(json);
		}
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("patch request");
		
		//ObjectMapper om = new ObjectMapper();
		
		UpdateData upData = om.readValue(req.getReader(), UpdateData.class);
		reimbDao.setStatus(upData.getReimbId(), upData.getStatusId(), upData.getResolverId());
		
		
	}
}
