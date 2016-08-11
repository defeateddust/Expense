package com.revature.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.revature.beans.Reimbursement;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.beans.Type;
import com.revature.beans.User;
import com.revature.service.ServiceLocator;

public class ERSDAO {
	private static Properties reimColumns;
	private static Properties userColumns;
	public Connection conn;
	NumberFormat format = new DecimalFormat("#0.00"); 
	
	static{
		reimColumns = new Properties();
		userColumns = new Properties();
		try {
			reimColumns.load(ERSDAO.class.getClassLoader().getResourceAsStream("reimbursementColumns.properties"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		try {
			userColumns.load(ERSDAO.class.getClassLoader().getResourceAsStream("userColumns.properties"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	public ERSDAO(){
		
	try{
		
		conn = ServiceLocator.getERSDatabase().getConnection();
		conn.setAutoCommit(false);//enables transactions
		System.err.println(conn);
		System.err.println("pdhfksdj");
				
	}catch(Exception e){
		System.err.println("hello");
	}
	}

	protected void finalize() throws Throwable{
		
		if(conn !=null)
			if(!conn.isClosed()) conn.close();
	}
	
	public List<Reimbursement> getAllReimbursements(int order){
		String orderby = "";
		switch(order){
		case 1:{
			orderby =" ORDER BY "+ reimColumns.getProperty("id");
			break;
		}
		case 2:{
			orderby =" ORDER BY "+ reimColumns.getProperty("amount");
			break;
		}
		case 3:{
			orderby =" ORDER BY "+ reimColumns.getProperty("submitted");
			break;
		}
		case 4:{
			orderby =" ORDER BY "+ reimColumns.getProperty("resolved");
			break;
		}
		case 5:{
			orderby =" ORDER BY "+ reimColumns.getProperty("description");
			break;
		}
		case 6:{
			orderby =" ORDER BY "+ reimColumns.getProperty("receibt");
			break;
		}
		case 7:{
			orderby =" ORDER BY "+ reimColumns.getProperty("author");
			break;
		}
		case 8:{
			orderby =" ORDER BY "+ reimColumns.getProperty("resolver");
			break;
		}
		case 9:{
			orderby =" ORDER BY "+ reimColumns.getProperty("status");
			break;
		}
		case 10:{
			orderby =" ORDER BY "+ reimColumns.getProperty("type");
			break;
		}
		default:{
			
		}
		}
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try{
		String query = "SELECT * FROM ERS_REIMBURSEMENT "+ orderby;
		ResultSet rs;
		java.sql.Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		
		while(rs.next()){
			Reimbursement obj = new Reimbursement(
					rs.getInt(reimColumns.getProperty("id")),
					rs.getDouble(reimColumns.getProperty("amount")),
					rs.getDate(reimColumns.getProperty("submitted")),
					rs.getDate(reimColumns.getProperty("resolved")),
					rs.getString(reimColumns.getProperty("description")),
					rs.getBlob(reimColumns.getProperty("receipt")),
					rs.getInt(reimColumns.getProperty("author")),
					rs.getInt(reimColumns.getProperty("resolver")),
					rs.getInt(reimColumns.getProperty("status")),
					rs.getInt(reimColumns.getProperty("type"))
					);
			reimbursements.add(obj);
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return reimbursements;
		}	
		
	public List<Reimbursement> getAllReimbursementsForEmployee(int id){
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try{
		String query = "SELECT * FROM ERS_REIMBURSEMENT WHERE "+reimColumns.getProperty("author")+ " = " + id;
		ResultSet rs;
		java.sql.Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		
		while(rs.next()){
			Reimbursement obj = new Reimbursement(
					rs.getInt(reimColumns.getProperty("id")),
					rs.getDouble(reimColumns.getProperty("amount")),
					rs.getDate(reimColumns.getProperty("submitted")),
					rs.getDate(reimColumns.getProperty("resolved")),
					rs.getString(reimColumns.getProperty("description")),
					rs.getBlob(reimColumns.getProperty("receipt")),
					rs.getInt(reimColumns.getProperty("author")),
					rs.getInt(reimColumns.getProperty("resolver")),
					rs.getInt(reimColumns.getProperty("status")),
					rs.getInt(reimColumns.getProperty("type"))
					);
			reimbursements.add(obj);
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return reimbursements;
		}	
	public List<User> getAllUsers(){
		List<User> users = new ArrayList<User>();
		try{
		String query = "SELECT * FROM ERS_USERS";
		ResultSet rs;
		java.sql.Statement stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		
		while(rs.next()){
			User obj = new User(
					rs.getInt(userColumns.getProperty("id")),
					rs.getString(userColumns.getProperty("username")),
					rs.getString(userColumns.getProperty("password")),
					rs.getString(userColumns.getProperty("firstname")),
					rs.getString(userColumns.getProperty("lastname")),
					rs.getString(userColumns.getProperty("email")),
					rs.getInt(userColumns.getProperty("role"))
					);
			users.add(obj);
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return users;
		}	
	
	public User	getUserByUsername(String username){
		

		User obj = null;
		String query = ("SELECT "
				+ userColumns.getProperty("id")+", "+ 
				userColumns.getProperty("username")+", "+ 
				userColumns.getProperty("password")+", "+ 
				userColumns.getProperty("firstname")+", "+ 
				userColumns.getProperty("lastname")+", "+ 
				userColumns.getProperty("email")+", "+ 
				userColumns.getProperty("role")+  
				" FROM ERS_USERS WHERE " + 
				userColumns.getProperty("username") + " =?");
		
		try{
			System.err.println(conn);
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1,username);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				obj = new User(
						rs.getInt(userColumns.getProperty("id")),
						rs.getString(userColumns.getProperty("username")),
						rs.getString(userColumns.getProperty("password")),
						rs.getString(userColumns.getProperty("firstname")),
						rs.getString(userColumns.getProperty("lastname")),
						rs.getString(userColumns.getProperty("email")),
						rs.getInt(userColumns.getProperty("role"))
						);
			
			}
			//conn.close();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			return obj;
			}
		
		
		
	
	
	public Role getRoleByID(int id){
		String query = "SELECT REIMB_STATUS_ID, REIMB_STATUS FROM ERS_REIMBURSEMENT_STATUS WHERE REIMB_STATUS_ID = ?";
		Role obj = null;
		
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				obj = new Role(
						rs.getInt(1),
						rs.getString(2)
						);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
		return obj;
	}
	
	
	public List<Type> getAllTypes(){
		String query = "SELECT * FROM ERS_REIMBURSEMENT_TYPE";
		Type obj = null;
		PreparedStatement stmt;
		List<Type> types = new ArrayList<Type>();
		try {
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			//conn.close();
			while(rs.next()){
				obj = new Type(
						rs.getInt(1),
						rs.getString(2)
						);
				types.add(obj);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return types;
		
	}
	
	public Type getTypeByID(int id){
		String query = "SELECT REIMB_TYPE_ID, REIMB_TYPE FROM ERS_REIMBURSEMENT_TYPE WHERE REIMB_TYPE_ID = ?";
		Type obj = null;
		
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				obj = new Type(
						rs.getInt(1),
						rs.getString(2)
						);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	public Type getTypeByName(String name){
		String query = "SELECT REIMB_TYPE_ID, REIMB_TYPE FROM ERS_REIMBURSEMENT_TYPE WHERE REIMB_TYPE = ?";
		Type obj = null;
		
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				obj = new Type(
						rs.getInt(1),
						rs.getString(2)
						);
			}
			//conn.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
		
	}
	public Status getStatusByID(int id){
		String query = "SELECT REIMB_STATUS_ID, REIMB_STATUS FROM ERS_REIMBURSEMENT_STATUS WHERE REIMB_STATUS_ID = ?";
		Status obj = null;
		
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				obj = new Status(
						rs.getInt(1),
						rs.getString(2)
						);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	public List<Status> getAllStatuses(){
		String query = "SELECT * FROM ERS_REIMBURSEMENT_STATUS";
		Status obj = null;
		PreparedStatement stmt;
		List<Status> statuses = new ArrayList<Status>();
		try {
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			//conn.close();
			while(rs.next()){
				obj = new Status(
						rs.getInt(1),
						rs.getString(2)
						);
				statuses.add(obj);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statuses;
		
	}
	public void updateStatus(int set,int where ){
		String query = "UPDATE ERS_REIMBURSEMENT SET REIMB_STATUS_ID =? WHERE "
				+ "REIMB_ID =?  ";
		System.out.println(query);
				PreparedStatement stmt;
				try {
					stmt = conn.prepareStatement(query);
				
				stmt.setInt(1, set);
				stmt.setInt(2, where);
				
				stmt.executeUpdate();
				conn.commit();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					try {
						conn.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
	}
	
		
	
	public void updateResolver(int userid,int where ){
		String query = "UPDATE ERS_REIMBURSEMENT SET REIMB_RESOLVER =? WHERE "
				+ "REIMB_ID =?  ";
		System.out.println(query);
				PreparedStatement stmt;
				try {
					stmt = conn.prepareStatement(query);
				
				stmt.setInt(1, userid);
				stmt.setInt(2, where);
				
				stmt.executeUpdate();
				conn.commit();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					try {
						conn.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
	}
	public void updateResolved(Date set,int where ){
		String query = "UPDATE ERS_REIMBURSEMENT SET REIMB_RESOLVED =? WHERE "
				+ "REIMB_ID =?  ";
		System.out.println(query);
				PreparedStatement stmt;
				try {
					stmt = conn.prepareStatement(query);
				
				stmt.setDate(1, set);
				stmt.setInt(2, where);
				
				stmt.executeUpdate();
				conn.commit();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					try {
						conn.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
	}
	public void createReimbursement(Reimbursement obj){
		try{
			String query = "INSERT INTO ERS_REIMBURSEMENT("+
					reimColumns.getProperty("id") + "," +
					reimColumns.getProperty("amount")+ "," +
					reimColumns.getProperty("submitted")+ "," +
					reimColumns.getProperty("resolved")+ "," +
					reimColumns.getProperty("description")+ "," +
					reimColumns.getProperty("receipt")+ "," +
					reimColumns.getProperty("author")+ "," +
					reimColumns.getProperty("resolver")+ "," +
					reimColumns.getProperty("status")+ "," +
					reimColumns.getProperty("type")
					+") VALUES(?,?,?,?,?,?,?,NULL,?,?)";
					System.out.println(query);
			PreparedStatement smt = conn.prepareStatement(query);
			
			smt.setInt(1, obj.getId());
			smt.setDouble(2, obj.getAmount());
			smt.setDate(3, (java.sql.Date) obj.getSubmitted());
			smt.setDate(4,  (java.sql.Date) obj.getResolved());
			smt.setString(5, obj.getDescription());
			smt.setBlob(6, obj.getReceipt());
			smt.setInt(7, obj.getAuthor());
			//smt.setInt(8, obj.getResolver());
			smt.setInt(8, obj.getStatus());
			smt.setInt(9, obj.getType());
			smt.executeUpdate();
			
			conn.commit();
			//conn.close();
		}catch(SQLException e1) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			e1.printStackTrace();
		}
	}
}


