package com.revature.web;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.beans.Type;
import com.revature.beans.User;
import com.revature.database.ERSDAO;
import com.revature.jbcrypt.BCrypt;


public class RequestHelper {
	ERSDAO dao = new ERSDAO();
public synchronized void process(HttpServletRequest req,HttpServletResponse resp) {
	HttpSession session = req.getSession(true);
	
	List<Type> types = new ArrayList<Type>();
	List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
	List<User> users = new ArrayList<User>();
	List<Status> statuses = new ArrayList<Status>();
	statuses = dao.getAllStatuses();
	types =dao.getAllTypes();
	for(Type t: types){
		System.out.println(t.getName());
	}
	session.getServletContext().setAttribute("statuses", statuses);
	session.getServletContext().setAttribute("types", types);
	
	switch (req.getRequestURI()) {
		
	
		case "/ExpenseReimbursement/login.do":{
			String user = req.getParameter("username");
			String pass = req.getParameter("password");
			
			
			if(dao.getUserByUsername(user) != null){
				User toAuthenticate = dao.getUserByUsername(user);
				
				if (BCrypt.checkpw(pass, toAuthenticate.getPassword())){
					
					session.setAttribute("loggedIn", toAuthenticate);
					session.setAttribute("username", toAuthenticate);
					try {
						User auth =(User)session.getAttribute("username");
						if(auth.getRole()==21){
							reimbursements = dao.getAllReimbursementsForEmployee(auth.getId());
						}
						else if(auth.getRole()==22){
							reimbursements = dao.getAllReimbursements(1);
						}
						else{
							System.err.println("inproper Id");
						}
		
						session.getServletContext().setAttribute("reimbursements", reimbursements);
						users=dao.getAllUsers();
						session.getServletContext().setAttribute("users", users);
						resp.sendRedirect("/ExpenseReimbursement/secure/home.jsp");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}else{
					req.setAttribute("loginFail", "Login failed, please try again.");
					try {
						req.getRequestDispatcher("index.jsp").forward(req, resp);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{
				req.setAttribute("loginFail", "Login failed, please try again.");
				try {
					req.getRequestDispatcher("index.jsp").forward(req, resp);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		}
		case "/ExpenseReimbursement/secure/logout.do":{
			req.getSession().invalidate();
			
			try {
				resp.sendRedirect("/ExpenseReimbursement/index.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "/ExpenseReimbursement/secure/approve.do":{
			Date resolved = new Date(Calendar.getInstance().getTime().getTime());
			User user = (User)session.getAttribute("username");
			int id = Integer.parseInt(req.getParameter("approve"));
			int userid = user.getId();
			dao.updateStatus(22, id);
			dao.updateResolved(resolved, id);
			dao.updateResolver(userid,id );
			System.err.println(id);
			try {
				reimbursements = dao.getAllReimbursements(1);
				session.getServletContext().setAttribute("reimbursements", reimbursements);
				resp.sendRedirect("/ExpenseReimbursement/secure/home.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "/ExpenseReimbursement/secure/deny.do":{
			
			Date resolved = new Date(Calendar.getInstance().getTime().getTime());
			User user = (User)session.getAttribute("username");
			int id = Integer.parseInt(req.getParameter("deny"));
			int userid = user.getId();
			dao.updateStatus(23, id);
			dao.updateResolved(resolved, id);
			dao.updateResolver(userid,id );
			System.err.println(id);
			try {
				reimbursements = dao.getAllReimbursements(1);
				session.getServletContext().setAttribute("reimbursements", reimbursements);
				resp.sendRedirect("/ExpenseReimbursement/secure/home.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
		case "/ExpenseReimbursement/secure/create.do":{
			InputStream inputStream = null;
			double amount = Double.parseDouble(req.getParameter("amount"));
			
			if(amount<0||Double.isNaN(amount)==true){
				try {
					resp.sendRedirect("/ExpenseReimbursement/secure/employeeTools/CreateReimbursement.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			int type =Integer.parseInt(req.getParameter("type"));
			
			String desc = req.getParameter("description");
			if(desc.equals(null)||desc.length()>250){
				try {
					resp.sendRedirect("/ExpenseReimbursement/secure/employeeTools/CreateReimbursement.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			String file =req.getParameter("file");
			try {
				 inputStream = new FileInputStream(file);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Date submitted = new Date(Calendar.getInstance().getTime().getTime());
			   Object author = new User();
			   author =  (User)session.getAttribute("username");
			   int authorID = ((User) author).getId();
			  
			   
			Reimbursement reimbursement = new Reimbursement(0, amount, submitted, null, desc, (Blob) inputStream, authorID, 0, 21, type);
			
			dao.createReimbursement(reimbursement);
			try {
				
				reimbursements = dao.getAllReimbursementsForEmployee(authorID);
				session.getServletContext().setAttribute("reimbursements", reimbursements);
				
				
				resp.sendRedirect("/ExpenseReimbursement/secure/home.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  	
			break;
		}
		
		default:
			throw new IllegalArgumentException("Invalid URI");
		}
	//return null;
	}}
	

