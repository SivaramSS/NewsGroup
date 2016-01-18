package org.sivaram.newsgroup.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.sivaram.newsgroup.models.User;

public class ProfileAction extends Action implements SessionAware, ServletRequestAware{
    
	String username;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	SessionMap<String,Object> sessionMap;
	HttpServletRequest request;
	
	public String getProfile()
	{   
		System.out.println("In Profile Action");
		System.out.println(sessionMap.get("user"));
		setUsername("Username");
		//if(request.getParameter("id"))
		System.out.println(request.getParameter("id"));
		return "success";
	}

	@Override
	public void setSession(Map<String, Object> map) {
	    this.sessionMap = (SessionMap<String, Object>) map;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}
}
