package com.sharepoint.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.sharepoint.model.Article;
import com.sharepoint.services.FetchArticles;
import com.sharepoint.services.HTMLParser;
import com.sharepoint.services.InsertArticle;

public class Share implements ServletRequestAware, ServletResponseAware {
	
	HttpServletRequest request;
	HttpServletResponse response;
	Article article = new Article();
	
	public String share()
	{
		String url = request.getParameter("url");
		String userid = request.getParameter("userid");
		
		Article a = new Article();
		a.setUserid(userid);
		a.setUrl(url);
		a.setCount_comments(0);
		a.setCount_likes(0);
		HTMLParser hp = new HTMLParser();
		InsertArticle temp = new InsertArticle();
		if(hp.parse(url)==true) 
		{
			a.setTitle(hp.getTitle());
			a.setContent(hp.getContent());
			if(temp.insert(a)==true)
			{
				a = FetchArticles.fetchPostByUrl(url, userid);
			}
		}
		else
			a.setTitle("Article failed to parse");
		
		setArticle(a);
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

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
}
