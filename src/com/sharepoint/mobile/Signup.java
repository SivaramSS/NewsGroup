package com.sharepoint.mobile;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sharepoint.model.User;
import com.sharepoint.services.CreateAccount;
import com.sharepoint.services.FetchProfile;

public class Signup extends ActionSupport implements ServletRequestAware,ServletResponseAware {
    
	HttpServletRequest request;
	HttpServletResponse response;
	User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
	public String createAccount()
	{   
	   	System.out.println("Signup Mobile: Creating Account");
	   	user = new User();
	   	user.setEmail(request.getParameter("email"));
	   	user.setFname(request.getParameter("fname"));
	   	user.setLname(request.getParameter("lname"));
	   	user.setPassword(request.getParameter("password"));
	   	user.setDob(request.getParameter("dob"));
	   	if(new CreateAccount().create(user)==true)
	   		setUser(new FetchProfile().getProfileByEmail(user.getEmail()));
	   	return "created";
	}

	@Override
	public void setServletResponse(HttpServletResponse res) {
		response = res;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		request = req;
	}
}
