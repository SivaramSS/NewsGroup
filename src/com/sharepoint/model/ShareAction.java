package com.sharepoint.model;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sharepoint.services.FetchArticles;
import com.sharepoint.services.HTMLParser;
import com.sharepoint.services.InsertArticle;

public class ShareAction extends ActionSupport implements SessionAware, ServletRequestAware {

	SessionMap<String, Object> sessionMap;
	String shareUrl;
	Article article = new Article();
	HttpServletRequest request;
	User user;
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
	}
	
	public String shareArticle()
	{
		
		Article a = new Article();
		String userid = sessionMap.get("user").toString();
		a.setUserid(userid);
		
		shareUrl = request.getParameter("shareurl").toString();
		System.out.println("Url to be added : "+ shareUrl);
		
		a.setUrl(shareUrl);
		a.setLiked(false);
		a.setCount_comments(0);
		a.setCount_likes(0);
		
		HTMLParser hp = new HTMLParser();
		InsertArticle temp = new InsertArticle();
		if(hp.parse(shareUrl)==true)
			{ 
			  a.setTitle(hp.getTitle());
			  a.setContent(hp.getContent());
			  if(temp.insert(a)==true)
			  	{
				  	a = FetchArticles.fetchPostByUrl(shareUrl, userid);
			  	}
			}
		else
			a.setTitle("Article failed to parse");
		
		setArticle(a);
		//System.out.println( InsertArticle.insert(a) );
		return "generated";
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
