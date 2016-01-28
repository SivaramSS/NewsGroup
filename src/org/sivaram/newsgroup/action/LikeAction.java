package org.sivaram.newsgroup.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LikeAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {
	HttpServletRequest request;
	SessionMap<String,Object> sessionMap;
	HttpServletResponse response;
	private InputStream inputStream;
	
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
	
	public String setLiked()
	{
		String userid = sessionMap.get("user").toString();
		String aid = request.getParameter("aid");
		int result = 0;
		System.out.println("In LikeAction setLiked userid : "+userid+" aid :"+aid);
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb","root","axess");
			PreparedStatement ps = con.prepareStatement("call setlike(?,?);");
			ps.setString(1, aid);
			ps.setString(2,userid);
			result = ps.executeUpdate();
			if(result==1)
			System.out.println("Success");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(result==1)
			 	{ 
				  	InputStream temp = new ByteArrayInputStream("Success".getBytes(StandardCharsets.UTF_8));
				  	setInputStream(temp);
			 	}
			else
				{
					InputStream temp = new ByteArrayInputStream("Operation not performed".getBytes(StandardCharsets.UTF_8));
					setInputStream(temp);
				}
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
	  Connection con = null;
	  ResultSet rs = null;
	  String nooflikes = null;
	  try
	  	{
		  Class.forName("com.mysql.jdbc.Driver");
		  con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb","root","axess");
		  PreparedStatement ps = con.prepareStatement("select count(*) as countlikes from likes where aid=?");
		  ps.setString(1,aid);
		  rs = ps.executeQuery();
		  if(rs.next())
		  	{ 
			  nooflikes = rs.getString("countLikes");
			  System.out.println("In countlikes nooflikes = "+nooflikes);
			  
			  InputStream temp = new ByteArrayInputStream(nooflikes.getBytes(StandardCharsets.UTF_8));
			  setInputStream(temp);
		  	}
		  else
			  System.out.println("Sql error on execution of query");
	  	}
	  catch(Exception e)
	  	{
		  e.printStackTrace();  
	  	}
	  finally 
	  {
		  try 
		  	{
			  rs.close();
			  con.close();
		  	} 
		  catch (SQLException e) 
		  	{
			  e.printStackTrace();
		  	}
		  
	  }
	  
	  return "success";
	}
}
