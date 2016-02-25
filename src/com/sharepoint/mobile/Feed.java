package com.sharepoint.mobile;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sharepoint.model.Article;
import com.sharepoint.services.FetchArticles;
import com.sharepoint.services.FetchProfile;

public class Feed extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	
	HttpServletRequest request;
	HttpServletResponse response;
	List<Article> articlelist = new ArrayList<Article>();
	
	public String getFeed()
	{   
		String userid = request.getParameter("userid");
		System.out.print("Userid is " + userid);
		
		if(userid!=null)
		{	setArticlelist( FetchArticles.fetch(userid) );
			System.out.println(articlelist.size()+ " articles present");
			if(articlelist.size()==0)
				{	
					Article a = new Article();
					a.setUrl("No Articles to show");
					articlelist.add(a);
				}
		   
			System.out.println("In GenerateFeed Class - result : Generated");
		}	
		
		return "generated";
		   
	}
	

	@Override
	public void setServletResponse(HttpServletResponse res) {
		response = res;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		request = req;
	}


	public List<Article> getArticlelist() {
		return articlelist;
	}


	public void setArticlelist(List<Article> articlelist) {
		this.articlelist = articlelist;
	}
	
}
