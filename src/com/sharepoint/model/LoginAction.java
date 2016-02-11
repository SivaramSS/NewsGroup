package com.sharepoint.model;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sharepoint.services.CheckLogin;

public class LoginAction extends ActionSupport implements SessionAware, ServletRequestAware {

	SessionMap<String,Object> sessionMap;
	private User user = new User();
	private static String SUCCESS = "success", LOGIN = "login";
    private boolean logoutval;
    HttpServletRequest request;
    String loginflag;
    
	public String execute()
	{   
		if(sessionMap.containsKey("user"))
			return SUCCESS;
		else
		{
			System.out.println("In LoginAction : "+user.getEmail() +" " + user.getPassword());
			CheckLogin cl = new CheckLogin();
		
			if(cl.authenticate(user)==true) 
				{
			  		user = cl.getUserob();
			  		sessionMap.put("user", user.getUserid()+"");
			  		return SUCCESS;
				}
			else
				return LOGIN;
	   }
	}

	public String logout()
	{
     sessionMap.remove("user");
	 if(sessionMap.containsKey("user"))
		 return "feed";
	 
	 return SUCCESS;	
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

	public boolean isLogoutval() {
		return logoutval;
	}

	public void setLogoutval(boolean logoutval) {
		this.logoutval = logoutval;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
