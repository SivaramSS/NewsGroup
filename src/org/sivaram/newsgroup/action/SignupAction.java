package org.sivaram.newsgroup.action;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.sivaram.newsgroup.models.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SignupAction extends ActionSupport implements SessionAware, ModelDriven {
    
	private User user = new User();
	SessionMap<String,String> sessionMap;
	private String SUCCESS="success", LOGIN="login";
	
	@Override
	public void setSession(Map<String, Object> map) {
		 sessionMap = (SessionMap) map;
	}


	public User getuser() {
		return user;
	}

	public void setuser(User user) {
		this.user = user;
	}
    
	public void validate()
	{
		if(StringUtils.isEmpty(user.getEmail()))
			addFieldError("email", " id cannot be blank");
		if(StringUtils.isEmpty(user.getPassword()))
			addFieldError("password", "Password cannot be blank");
		if(StringUtils.isEmpty(user.getCp()))
			addFieldError("cp", "Please Confirm password");
		if(StringUtils.isEmpty(user.getFname()))
			addFieldError("fname", "First name cannot be blank");
		if(StringUtils.isEmpty(user.getLname()))
			addFieldError("lname", "Last name cannot be blank");
		//if(user.getDob().toString().isEmpty())
			//addFieldError("dob", "Birthday cannot be blank");
	}
	
	public String createAccount()
	{   
	   	System.out.println("SignupAction : Creating Account");
	   	Date d = Date.valueOf(user.getDob());
	   	int flag=0;
	   	Connection con = null;
	   	
	   	try
	   	{
	   	  Class.forName("com.mysql.jdbc.Driver");
	   	  con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupDB","root","axess");
	   	  PreparedStatement ps = con.prepareStatement("INSERT into profile(userid,email,password,fname,lname,dob) values(null,?,?,?,?,?)" );
	   	  ps.setString(1, user.getEmail());
	   	  ps.setString(2, user.getPassword());
	   	  ps.setString(3, user.getFname());
	   	  ps.setString(4, user.getLname());
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
	
}
