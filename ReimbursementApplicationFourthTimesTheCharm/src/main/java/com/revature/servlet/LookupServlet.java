package com.revature.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.LookupDao;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.UserRole;

public class LookupServlet extends HttpServlet {
	private LookupDao lookupDao = LookupDao.currentImplementation;
	private final ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ("/ReimbursementApplicationFourthTimesTheCharm/lookup/role".equals(req.getRequestURI())) {
			List<UserRole> roleList;
			
			roleList = lookupDao.getAllRoles();
			
			String json = om.writeValueAsString(roleList);
			
			resp.addHeader("content-type", "application/json");
			resp.getWriter().write(json);
		}
		else if("/ReimbursementApplicationFourthTimesTheCharm/lookup/status".equals(req.getRequestURI())) {
			List<ReimbursementStatus> statusList;
			
			statusList = lookupDao.getAllStatus();
			
			String json = om.writeValueAsString(statusList);
			
			resp.addHeader("content-type", "application/json");
			resp.getWriter().write(json);
		}
		else if("/ReimbursementApplicationFourthTimesTheCharm/lookup/type".equals(req.getRequestURI())) {
			List<ReimbursementType> typeList;
			
			typeList = lookupDao.getAllTypes();
			
			String json = om.writeValueAsString(typeList);
						
			resp.addHeader("content-type", "application/json");
			resp.getWriter().write(json);
		}
	}
}
