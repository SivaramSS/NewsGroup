package com.sharepoint.mobile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.sharepoint.model.Article;
import com.sharepoint.model.User;
import com.sharepoint.services.FetchArticles;
import com.sharepoint.services.FetchProfile;

public class Profile extends Action implements ServletRequestAware,ServletResponseAware {
    
	String username;
	User user;
	HttpServletRequest request;
	HttpServletResponse response;
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
		String userid = request.getParameter("userid");
		FetchProfile profile = new FetchProfile();
		profile.getProfile(userid);
		user = profile.getUser();
		articlelist = FetchArticles.fetchArticlesByUserId(userid);
		return "success";
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setServletResponse(HttpServletResponse res) {
		response = res;
	}
	
}
