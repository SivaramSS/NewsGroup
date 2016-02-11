package com.sharepoint.model;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sharepoint.services.FetchArticles;
import com.sharepoint.services.FetchProfile;

public class GenerateFeed extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {
    
	private SessionMap<String,Object> sessionMap;
	String fname;
	List<Article> articlelist;
	HttpServletRequest request;
	HttpServletResponse response;
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public List<Article> getArticlelist() {
		return articlelist;
	}

	public void setArticlelist(List<Article> articlelist) {
		this.articlelist = articlelist;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.sessionMap = (SessionMap<String, Object>) map;
	}
	
	public String getFeed()
	{   
		
	   if(sessionMap.get("user")!=null)
		{
		   FetchProfile profile = new FetchProfile();
		   profile.getProfile(sessionMap.get("user").toString());
		   setFname( profile.getUser().getFname());
		   
		   setArticlelist( FetchArticles.fetch(sessionMap.get("user").toString()) );
		   System.out.println(articlelist.size()+ " articles present");
		   if(articlelist.size()==0)
		   {
			   Article a = new Article();
			   a.setUrl("No Articles to show");
			   articlelist.add(a);
		   }
		   
		   System.out.println("In GenerateFeed Class - result : Generated");
		   return "generated";
	    }
	   else
	   {
		   System.out.println("In GenerateFeed Class - result : Login ");
		   return "login";
	   }
		   
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
	  this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
	  this.request = request;
	}
}
