package com.sharepoint.model;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;
import com.sharepoint.services.CheckLogin;

public class LoginAction extends ActionSupport implements SessionAware, ServletRequestAware {

	SessionMap<String,Object> sessionMap;
	private User user = new User();
	String password;
	private static String SUCCESS = "success", LOGIN = "login";
    HttpServletRequest request;
    String loginflag;
    
	public String execute()
	{   
		System.out.println("In Login Action");
		if(sessionMap.containsKey("user"))
			return SUCCESS;
		else
		{
			CheckLogin cl = new CheckLogin();
		
			if(cl.authenticate(user,password)==true) 
				{
			  		user = cl.getUserob();
			  		sessionMap.put("user", user.getUserid()+"");
			  		return SUCCESS;
				}
			else {
				addFieldError("user.email","Invalid Email or Password");
				return INPUT;
			}
	   }
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		this.sessionMap = (SessionMap<String, Object>) map;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

	public String getLoginflag() {
		return loginflag;
	}

	public void setLoginflag(String loginflag) {
		this.loginflag = loginflag;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String pass) {
		this.password = pass;
	}
	
}
