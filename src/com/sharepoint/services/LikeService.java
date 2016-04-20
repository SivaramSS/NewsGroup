package com.sharepoint.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sharepoint.model.User;

public class LikeService {
	
	public static ArrayList<String> getNamesofLikers(String aid)
	{
		ArrayList<String> likerlist = new ArrayList<String>();
		Connection con = null;
		ResultSet rs = null;
		System.out.println("Aid : "+aid);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb","root","axess");
			PreparedStatement ps = con.prepareStatement("select p.fname from profile p,article a,likes l where l.aid = ? and l.aid=a.aid and l.userid=p.userid");
			ps.setString(1, aid);
			rs = ps.executeQuery();
			while(rs.next()) 
			{
				likerlist.add(rs.getString("fname"));
			}
			System.out.println("Count of likerlist : "+likerlist.size());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try{ if(con!=null) {con.close(); rs.close();}}
			catch(Exception e) {e.printStackTrace();}
		}
		return likerlist;
	}
	
	public static int likeIt(String aid,String userid)
	{
		int result = 0;
		System.out.println("In LikeService setLiked userid : "+userid+" aid :"+aid);
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
		}
		
		return result;
	}
	
	public static String getLikesCount(String aid)
	{
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
		  
		  
		return nooflikes;
	}
	
	public static List<User> getNamesofLikersWithUserid(String aid)
	{
		List<User> likerlist = new ArrayList<User>();
		Connection con = null;
		ResultSet rs = null;
		System.out.println("Aid : "+aid);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb","root","axess");
			PreparedStatement ps = con.prepareStatement("select p.fname,l.userid from profile p,article a,likes l where l.aid = ? and l.aid=a.aid and l.userid=p.userid");
			ps.setString(1, aid);
			rs = ps.executeQuery();
			while(rs.next()) 
			{
				User user = new User();
				user.setUserid(rs.getInt("userid")+"");
				user.setFname(rs.getString("fname"));
				likerlist.add(user);
			}
			System.out.println("Count of likerlist : "+likerlist.size());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally {
			  try{
				  if(con!=null) {con.close();rs.close();}
			  }
			  catch(Exception eas) {eas.printStackTrace();}
		  }
		return likerlist;
	}
}
