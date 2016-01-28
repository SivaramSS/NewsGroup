package org.sivaram.newsgroup.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.sivaram.newsgroup.models.Article;
import org.sivaram.newsgroup.service.FetchArticles;

import com.opensymphony.xwork2.ActionSupport;

public class ViewPostAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {

	HttpServletRequest request;
	HttpServletResponse response;
	SessionMap<String,Object> sessionMap;
	Article post;
	
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
	
	public String viewPost()
	{
		String aid = request.getParameter("id");
		String userid = sessionMap.get("user").toString();
		
		setPost(FetchArticles.fetchPost(aid, userid));
		return "show";
	}
	
	
	
}
