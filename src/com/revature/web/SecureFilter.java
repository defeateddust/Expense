package com.revature.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecureFilter implements Filter {
	private FilterConfig config;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.err.println("filter destroyed: coffee grinds everywhere");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		if(req.getSession().getAttribute("loggedIn") != null){
			chain.doFilter(request, response);
		}else{
			//config.getInitParameter("redirect")
				
				//req.getRequestDispatcher("../index.jsp").forward(req, response);
				resp.sendRedirect("../index.jsp");
			}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config = filterConfig;
	}

}
