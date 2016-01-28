package org.sivaram.newsgroup.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.sivaram.newsgroup.models.Article;
import org.sivaram.newsgroup.service.InsertArticle;

import com.opensymphony.xwork2.ActionSupport;

public class ShareAction extends ActionSupport implements SessionAware {

	SessionMap<String, Object> sessionMap;
	String shareUrl;
	
	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
	}
	
	public String shareArticle()
	{
		System.out.println("Url to be added : "+getShareUrl());
		Article a = new Article();
		a.setUserid(sessionMap.get("user").toString());
		a.setUrl(shareUrl);
		InsertArticle.insert(a);
		//System.out.println( InsertArticle.insert(a) );
		return "generated";
	}

}
