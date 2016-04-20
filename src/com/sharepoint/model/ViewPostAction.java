package com.sharepoint.model;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sharepoint.services.FetchArticles;

public class ViewPostAction extends ActionSupport implements SessionAware,UserAware, ServletRequestAware, ServletResponseAware {

	HttpServletRequest request;
	HttpServletResponse response;
	SessionMap<String,Object> sessionMap;
	Article post;
	User user;
	
	public String viewPost()
	{
		String aid = request.getParameter("data");
		String userid = sessionMap.get("user").toString();
		
		setPost(FetchArticles.fetchPost(aid, userid));
		return "show";
	}

	public Article getPost() {
		return post;
	}

	public void setPost(Article post) {
		this.post = post;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
	   this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
	   this.request = req;
	}

	@Override
	public void setSession(Map<String, Object> map) {
	   sessionMap = (SessionMap<String, Object>) map;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
