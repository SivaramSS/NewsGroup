package com.sharepoint.mobile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sharepoint.model.Comment;
import com.sharepoint.services.CommentsService;

public class Comments extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {
	
	SessionMap<String,Object> sessionMap;
	HttpServletRequest request;
	HttpServletResponse response;
	InputStream inputStream;
	
	List<Comment> commentlist = new ArrayList<Comment>();
	String aid;

	public String getComments()
	{
		aid = request.getParameter("aid").toString();
		if(aid!=null)
		{
			System.out.println("In comments Action aid : "+aid);
			CommentsService ss = new CommentsService();
			setCommentlist(ss.getCommentlist(aid));
		}
		else
			System.out.println("Aid is missing");
		
		return "success";
	}
	
	public String insertComment()
	{
		
	  String userid = request.getParameter("userid");
	  String aid = request.getParameter("aid");
	  String comment = request.getParameter("comment");
	  boolean inserted = CommentsService.insertComment(aid, userid, comment);
	  setInputStream(new ByteArrayInputStream("done".getBytes(StandardCharsets.UTF_8)) );
	  
	  if(inserted)
		return "done";
	  return "error";
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public List<Comment> getCommentlist() {
		return commentlist;
	}

	public void setCommentlist(List<Comment> commentlist) {
		this.commentlist = commentlist;
	}

	@Override
	public void setServletResponse(HttpServletResponse res) {
		this.response = res;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String,Object>) map;
	}
	
	

}
