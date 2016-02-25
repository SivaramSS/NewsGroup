package com.sharepoint.model;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class SessionAction extends ActionSupport implements SessionAware {
	SessionMap<String,Object> sessionMap;

	public String execute()
	{
		if(sessionMap.containsKey("user"))
			return "feed";
		else
			return "login";
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String,Object>) map;
	}
	
}
