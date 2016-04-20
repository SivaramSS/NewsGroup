package com.sharepoint.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sharepoint.services.FetchArticles;
import com.sharepoint.services.FetchProfile;

public class ProfileAction extends ActionSupport implements SessionAware, UserAware, ServletRequestAware, ModelDriven {
    
	String username;
	User user;
	SessionMap<String,Object> sessionMap;
	HttpServletRequest request;
	List<Article> articlelist;
	
	public List<Article> getArticlelist() {
		return articlelist;
	}

	public void setArticlelist(List<Article> articlelist) {
		this.articlelist = articlelist;
	}

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
		userid = sessionMap.get("user").toString();
		FetchProfile profile = new FetchProfile();
		profile.getProfile(userid);
		user = profile.getUser();
		if(!username.equals(sessionMap.get("user").toString()))
			user.setEmail("");
		articlelist = FetchArticles.fetchArticlesByUserId(username,userid);
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
