package com.sharepoint.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SignupAction extends ActionSupport implements SessionAware, ModelDriven {
    
	private User user = new User();
	String pass,cp;
	
	SessionMap<String,Object> sessionMap;
	private String SUCCESS="success", LOGIN="login";
	
	public String createAccount()
	{   
	   	System.out.println("SignupAction : Creating Account");
	   	System.out.println("Date entered : "+user.getDob2().toString());
	   	int flag=0;
	   	Connection con = null;
	   	
	   	try
	   	{
	   	  Class.forName("com.mysql.jdbc.Driver");
	   	  con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupDB","root","axess");
	   	  PreparedStatement ps = con.prepareStatement("INSERT into profile(userid,email,password,fname,lname,dob) values(null,?,?,?,?,?)" );
	   	  ps.setString(1, user.getEmail());
	   	  ps.setString(2, pass);
	   	  ps.setString(3, user.getFname());
	   	  ps.setString(4, user.getLname());
	   	  java.sql.Date d = new Date(user.getDob2().getTime());
	   	  ps.setDate(5, d);
	   	  flag = ps.executeUpdate();
	   	}
	   	
	   	catch(Exception e)
	   	{
	   	  e.printStackTrace();
	   	}
	   	
	   	if(flag==1)
	   		return SUCCESS;
	   	else
	   		return LOGIN;
	}


	@Override
	public User getModel() {
		return user;
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		 sessionMap = (SessionMap<String,Object>) map;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getCp() {
		return cp;
	}
	
	public void setCp(String cp) {
		this.cp = cp;
	}
}
