package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorHandler extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
			      Integer statusCode = (Integer)req.getAttribute("javax.servlet.error.status_code");
			     
			     
			      // Set response content type
			      if ((Integer)statusCode == 404)
			      {
			    resp.sendRedirect("/ExpenseReimbursement/notFoundErr.jsp");
			      }
			      else{
			    	  resp.sendRedirect("/ExpenseReimbursement/Error.jsp");
			      }
			      }
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
