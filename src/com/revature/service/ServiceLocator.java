package com.revature.service;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.revature.database.ERSDAO;


//does all lookups
//JNDI, CORBA, SOAP, REST, RMI, RPC, etc.
public class ServiceLocator {

	
	public static DataSource getERSDatabase(){
		Properties environmentProps = new Properties();
		
			try {
				environmentProps.load(
						ERSDAO.class.getClassLoader()
							.getResourceAsStream("jndi.properties")
				);
				
				Context ctxt = new InitialContext(environmentProps);
				
				DataSource project1 = (DataSource) ctxt.lookup("db/project1");
				
				return project1;
			} catch (Exception e) {
				System.err.println("numbers");
				e.printStackTrace();
				return null;
			}
			
	}

//	public synchronized static DataSource getERSDatabase(){
//		if(project1 == null){
//			try{
//				project1 = (DataSource) ctxt.lookup("db/project1");
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//		return project1;
//	}
}