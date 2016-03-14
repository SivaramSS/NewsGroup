package com.sharepoint.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sharepoint.services.LikeService;

public class LikeAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {
	HttpServletRequest request;
	SessionMap<String,Object> sessionMap;
	HttpServletResponse response;
	private InputStream inputStream;
	private List<String> likelist;
	
	public String setLiked()
	{
		String userid = sessionMap.get("user").toString();
		String aid = request.getParameter("aid");
		if(LikeService.likeIt(aid,userid)==1)
	 	{ 
		  	InputStream temp = new ByteArrayInputStream("Success".getBytes(StandardCharsets.UTF_8));
		  	setInputStream(temp);
	 	}
		else
		{
			InputStream temp = new ByteArrayInputStream("Operation not performed".getBytes(StandardCharsets.UTF_8));
			setInputStream(temp);
		}
		
		return "success";
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getLikesCount()
	{
	  String aid = request.getParameter("aid");
	  System.out.println("In getLikesCount() "+aid);
	  String nooflikes = LikeService.getLikesCount(aid);
	  InputStream temp = new ByteArrayInputStream(nooflikes.getBytes(StandardCharsets.UTF_8));
	  setInputStream(temp);
	  
	  return "success";
	}
	
	public String getLikeslist()
	{	
		String aid = request.getParameter("data");
		setLikelist(LikeService.getNamesofLikers(aid));
		return "show";
	}
	
	public List<String> getLikelist() {
		return likelist;
	}

	public void setLikelist(List<String> likelist) {
		this.likelist = likelist;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		request = req;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String, Object>) map;
	}
}
