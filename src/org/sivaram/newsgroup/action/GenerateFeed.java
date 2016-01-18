package org.sivaram.newsgroup.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.sivaram.newsgroup.models.Article;
import org.sivaram.newsgroup.service.FetchArticles;

import com.opensymphony.xwork2.ActionSupport;

public class GenerateFeed extends ActionSupport implements SessionAware {
    
	private SessionMap<String,Object> sessionMap;
	List<Article> articlelist;
	
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
		   setArticlelist( FetchArticles.fetch() );
		   System.out.println(articlelist.size()+ " articles present");
		   if(articlelist.size()==0)
		   {
			   Article a = new Article();
			   a.setUrl("No Articles to show");
			   articlelist.add(a);
		   }
		   
		   return "generated";
	    }
	   else
		   return "login";
	}

}
