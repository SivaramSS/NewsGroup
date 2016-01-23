package org.sivaram.newsgroup.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CommentsAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {

	SessionMap<String,Object> sessionMap;
	HttpServletRequest request;
	HttpServletResponse response;
	
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
		this.sessionMap = (SessionMap<String,Object>) map;
	}
	
	public String getComments()
	{
		String aid = request.getParameter("aid").toString();
		System.out.println("In comments Action");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		try {
			response.getWriter().write("Working properly");
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return ActionSupport.SUCCESS;
	}
}
