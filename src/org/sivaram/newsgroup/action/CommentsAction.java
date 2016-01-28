package org.sivaram.newsgroup.action;

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
import org.sivaram.newsgroup.models.Comment;
import org.sivaram.newsgroup.service.CommentsService;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

public class CommentsAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware, Serializable {

	SessionMap<String,Object> sessionMap;
	HttpServletRequest request;
	HttpServletResponse response;
	InputStream inputStream;
	
	List<Comment> commentlist = new ArrayList<Comment>();
	
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

	public String getComments()
	{
		String aid = request.getParameter("aid").toString();
		System.out.println("In comments Action aid : "+aid);
		CommentsService ss = new CommentsService();
		setCommentlist(ss.getCommentlist(aid));
		//response.setContentType("text/html");
		/*response.setHeader("Cache-Control", "no-cache");
		try {
			response.getWriter().write("Working properly");
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		*/
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
					countcom = rs.getInt("countcom")+"";
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
}
