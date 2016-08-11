package com.revature.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.Type;
import com.revature.database.ERSDAO;

public class MasterServlet extends HttpServlet{	
//	@Override
//	public void init() throws ServletException {
//		ERSDAO dao = new ERSDAO();
//		List<Type> types = new ArrayList<Type>();
//		types =dao.getAllTypes();
//		for(Type t: types){
//			System.out.println(t.getName());
//		}
//		
//		this.getServletContext().setAttribute("types", types);
//		
//	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String nextPage = new 
				RequestHelper RH = new RequestHelper();
				RH.process(req, resp);
		//req.getRequestDispatcher(nextPage).forward(req, resp);
	}
		
		
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

}
