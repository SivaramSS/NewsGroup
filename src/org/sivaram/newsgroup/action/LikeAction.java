package org.sivaram.newsgroup.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		String userid = request.getParameter("userid");
		String aid = request.getParameter("aid");
		int result = 0;
		System.out.println("In LikeAction userid : "+userid+" aid :"+aid);
		Connection con = null;
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb","root","axess");
			PreparedStatement ps = con.prepareStatement("call setlike(?,?);");
			ps.setString(1, aid);
			ps.setString(2,userid);
			result = ps.executeUpdate();
			if(result==1)
			System.out.println("Liked");
			
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
			 { try {
					response.getWriter().write("Unlike");
				} catch (IOException e) {
					e.printStackTrace();
				}
			 }
			else
				{
				 	try {
						response.getWriter().write("Like");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}
		return ActionSupport.NONE;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	
}
