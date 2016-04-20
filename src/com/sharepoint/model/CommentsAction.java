package com.sharepoint.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import com.sharepoint.services.CommentsService;

public class CommentsAction extends ActionSupport implements SessionAware, UserAware, ServletRequestAware, ServletResponseAware, Serializable {

	SessionMap<String,Object> sessionMap;
	HttpServletRequest request;
	HttpServletResponse response;
	InputStream inputStream;
	User user;
	
	List<Comment> commentlist = new ArrayList<Comment>();
	String aid;

	public String getComments()
	{
		aid = request.getParameter("aid").toString();
		System.out.println("In comments Action aid : "+aid);
		CommentsService ss = new CommentsService();
		setCommentlist(ss.getCommentlist(aid));
		return "success";
	}
	
	public String insertComment()
	{
		
	  String userid = sessionMap.get("user").toString();
	  String aid = request.getParameter("aid");
	  String comment = request.getParameter("comment");
	  boolean inserted = CommentsService.insertComment(aid, userid, comment);
	  setInputStream(new ByteArrayInputStream("done".getBytes(StandardCharsets.UTF_8)) );
	  
	  if(inserted)
		return "done";
	  return "error";
	}
    
	public String getCommentsCount()
	{
		Connection con = null;
		ResultSet rs = null;
		String aid = request.getParameter("aid");
		String countcom; 
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb","root","axess");
			PreparedStatement ps = con.prepareStatement("select count(*) as countcom from comments where aid=?");
			ps.setString(1, aid);
			rs = ps.executeQuery();
			if(rs.next())
				{
					int temp = rs.getInt("countcom");
					if(temp==0)
						countcom = "comment";
					else if(temp==1)
						countcom = "1 comment";
					else
						countcom = temp + " comments";
					setInputStream(new ByteArrayInputStream(countcom.getBytes(StandardCharsets.UTF_8)) );
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "done";
	}
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}
	
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
	
	public List<Comment> getCommentlist() {
		return commentlist;
	}

	public void setCommentlist(List<Comment> commentlist) {
		this.commentlist = commentlist;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	
	
}
