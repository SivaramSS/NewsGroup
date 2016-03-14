package com.sharepoint.mobile;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sharepoint.model.Article;
import com.sharepoint.services.FetchArticles;

public class FullPost extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	HttpServletRequest request;
	HttpServletResponse response;
	Article post;
	
	public String viewPost()
	{
		String aid = request.getParameter("aid");
		String userid = request.getParameter("userid");
		System.out.println("Full post : aid : "+aid+" userid : "+userid);
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
}
