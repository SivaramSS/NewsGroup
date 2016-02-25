package com.sharepoint.model;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware,ServletRequestAware,ServletResponseAware {
	SessionMap<String,Object> sessionMap;
	HttpServletRequest request;
	HttpServletResponse response;
	boolean logoutval;
	public String logout()
	{
		String logout = request.getParameter("logout").toString();
		if(sessionMap.containsKey("user") && logout.equals("true"))
		{
			sessionMap.remove("user");
			sessionMap.invalidate();
		}
		else
			return "input";
		if(!sessionMap.containsKey("user"))
			return "success";
		else
			return "feed";
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		this.sessionMap = (SessionMap<String,Object>) map;
	}
	
	public boolean isLogoutval() {
		return logoutval;
	}
	
	public void setLogoutval(boolean logoutval) {
		this.logoutval = logoutval;
	}
}
