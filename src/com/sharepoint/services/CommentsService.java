package com.sharepoint.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sharepoint.model.Comment;

public class CommentsService {
	
	private List<Comment> commentlist;

	public List<Comment> getCommentlist(String aid) {
		
		commentlist = new ArrayList<Comment>();
		Connection con = null;
		ResultSet rs = null;
		try {
		  Class.forName("com.mysql.jdbc.Driver");
		  con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb","root","axess");
		  PreparedStatement ps = con.prepareStatement("select cid,userid,(select fname from profile p where p.userid=c.userid) as fname,comment,uldatetime from comments c where aid=?");
		  ps.setString(1, aid);
		  rs = ps.executeQuery();
		  while(rs.next())
		  {
			  Comment c = new Comment();
			  c.setCid(rs.getInt("cid")+"");
			  java.sql.Timestamp ts = rs.getTimestamp("uldatetime");
			  Date temp = new Date(ts.getTime());
			  c.setCdate(temp);
			  c.setUserid(rs.getInt("userid")+"");
			  c.setContent(rs.getString("comment"));
			  c.setFname(rs.getString("fname"));
			  c.setProfileurl("/NewsGroup/user/"+c.getUserid());
			  commentlist.add(c);
		  }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		  try {
			rs.close();
			con.close();
			System.out.println("Connection closed");
		  } 
		  catch (SQLException e) 
		  {
			  e.printStackTrace();
		  }
		}
		return commentlist;
	}

	public void setCommentlist(List<Comment> commentlist) {
		this.commentlist = commentlist;
	}

	public static boolean insertComment(String aid,String userid,String comment)
	{
		Date dt = new Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ct = sdf.format(dt);
		boolean inserted = false;
		Connection con = null;
		
		try
		{
	       Class.forName("com.mysql.jdbc.Driver");
	       con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb","root","axess");
	       PreparedStatement ps = con.prepareStatement("Insert into comments values(null,?,?,?,?)");
	       ps.setString(1, aid);
	       ps.setString(2, userid);
	       ps.setString(3, comment);
	       ps.setString(4, ct);
	       int result = ps.executeUpdate();
	       if(result==1)
	       {  
	    	   System.out.println("Inserted comment");
	    	   inserted = true;
	       }
	       
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try {
				con.close();
				System.out.println("Connection closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return inserted;
	}
}
