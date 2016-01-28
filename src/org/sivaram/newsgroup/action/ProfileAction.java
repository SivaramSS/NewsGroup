package org.sivaram.newsgroup.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.Action;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.sivaram.newsgroup.models.User;
import org.sivaram.newsgroup.service.FetchProfile;

import com.opensymphony.xwork2.ModelDriven;

public class ProfileAction extends Action implements SessionAware, ServletRequestAware, ModelDriven {
    
	String username;
	User user;
	SessionMap<String,Object> sessionMap;
	HttpServletRequest request;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfile()
	{   
		System.out.println("In Profile Action");
		System.out.println(sessionMap.get("user"));
		String userid = new String();
		if(request.getParameter("id").toString().equals("own"))
			userid = sessionMap.get("user").toString();
		else
			userid = request.getParameter("id").toString();
		
		FetchProfile profile = new FetchProfile();
		profile.getProfile(userid);
		user = profile.getUser();
		if(!request.getParameter("id").toString().equals("own"))
			user.setEmail("");
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

	@Override
	public User getModel() {
		return user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
