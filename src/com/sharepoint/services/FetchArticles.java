package com.sharepoint.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sharepoint.model.Article;

public class FetchArticles {
    
	public static ArrayList<Article> fetch(String userid)
	{   
		ArrayList<Article> articlelist = new ArrayList<Article>();
	    Connection con = null;
	    ResultSet rs = null;
	    
	    try
	    {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb","root","axess");
	    	
	    	PreparedStatement ps = con.prepareStatement("select a.aid,a.userid,url,(select count(*) from likes l where l.aid=a.aid) as countlikes, (select count(*) from comments c where c.aid=a.aid) as countcom,fname,lname,uldatetime, (select count(*) from likes l where l.userid=? and l.aid=a.aid) as liked, ac.title, ac.content from article a, profile p, articlecontent ac where a.userid=p.userid and a.aid = ac.aid order by uldatetime desc");
	    	ps.setString(1, userid);
	    	rs = ps.executeQuery();
	    	
	    	while(rs.next())
	    	{   
	    		Article a = new Article();
	    		
	    		a.setAid(rs.getInt("aid")+"");
	    		a.setUserid(rs.getInt("userid")+"");
	    		a.setUrl(rs.getString("url"));
	    		a.setCount_likes(rs.getInt("countlikes"));
	    		a.setCount_comments(rs.getInt("countcom"));
	    		a.setFname(rs.getString("fname"));
	    		a.setLname(rs.getString("lname"));
	    		java.sql.Timestamp ts = rs.getTimestamp("uldatetime");
				Date temp = new Date(ts.getTime());
				a.setUldatetime(temp);
	    		a.setLiked(rs.getInt("liked")==1? true:false);
	    		
	    		if(a.getLiked())
	    			{
	    				System.out.println("article : "+a.getAid() +" Liked : "+rs.getInt("liked"));
	    			}
	    		
	    		a.setTitle(rs.getString("title"));
	    		String content = rs.getString("content");
	    		content = content.substring(0, 800);
	    		a.setContent(content);
	    		a.setProfileurl("user/"+a.getUserid());
	    		a.setLikerlist(LikeService.getNamesofLikers(a.getAid()));
	    		articlelist.add(a);
	    	}
	    
	    }
		
	    catch(Exception e)
	    {
	     e.printStackTrace();	
	    }
	    
	    finally {
	    	
	    	try {
				rs.close();
				con.close();
				System.out.println("connection closed");
			} 
	    	catch (SQLException e) {
				e.printStackTrace();
			}
	    	
	    }
		return articlelist;
	}
	
	public static Article fetchPost(String aid, String userid)
	{
	  Connection con = null;
	  ResultSet rs = null;
	  Article a = new Article();
	  
	  try
	  {
	    Class.forName("com.mysql.jdbc.Driver");
	    con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb","root","axess");
	    PreparedStatement ps = con.prepareStatement("select a.userid,url,uldatetime,(select count(*) from likes where aid=?) as countlikes, (select count(*) from comments where aid=?) as countcomments,fname,lname, (select count(*) from likes l where l.userid=? and l.aid = a.aid) as liked, ac.title, ac.content from article a,profile p, articlecontent ac where a.aid = ac.aid and a.aid=? and a.userid = p.userid");
	    ps.setString(1, aid);
	    ps.setString(2, aid);
	    ps.setString(3, userid);
	    ps.setString(4, aid);
	    rs = ps.executeQuery();
	    if(rs.next())
	    	{
	    	  
	    	  a.setAid(aid);
	    	  a.setUserid(rs.getInt("userid")+"");
	    	  a.setUrl(rs.getString("url"));
	    	  Timestamp ts = rs.getTimestamp("uldatetime");
	    	  Date temp = new Date(ts.getTime());
	    	  a.setUldatetime(temp);
	    	  a.setLiked(rs.getInt("liked")==1?true:false);
	    	  a.setFname(rs.getString("fname"));
	    	  a.setLname(rs.getString("lname"));
	    	  a.setCount_likes(rs.getInt("countlikes"));
	    	  a.setCount_comments(rs.getInt("countcomments"));
	    	  a.setTitle(rs.getString("title"));
	    	  a.setContent(rs.getString("content"));
	    	}
	  }
	  catch(Exception e)
	  {		
		  e.printStackTrace();
	  }
	  
	  finally {
		  try{
			  if(con!=null) {con.close();rs.close();System.out.println("Connection closed");}
		  }
		  catch(Exception eas) {eas.printStackTrace();}
	  }
	  return a;
	}
	
	public static List<Article> fetchArticlesByUserId(String id,String userid)
	{
		List<Article> articlelist = new ArrayList<Article>();
		Connection con = null;
		ResultSet rs = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb","root","axess");
			PreparedStatement ps = con.prepareStatement("select a.aid,url,fname,lname,uldatetime,(select count(*) from likes l where l.aid = a.aid) as countlikes, (select count(*) from comments c where c.aid=a.aid) as countcomments, (select count(*) from likes l where l.userid=? and l.aid=a.aid) as liked, ac.title, ac.content from profile p, article a, articlecontent ac where p.userid=a.userid and a.userid=? and a.aid = ac.aid");
			ps.setString(1, userid);
			ps.setString(2, id);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Article a = new Article();
				a.setUserid(userid);
				a.setAid(rs.getString("aid"));
				a.setFname(rs.getString("fname"));
				a.setLname(rs.getString("lname"));
				a.setUrl(rs.getString("url"));
				a.setCount_likes(rs.getInt("countlikes"));
				a.setCount_comments(rs.getInt("countcomments"));
				a.setLiked(rs.getInt("liked")==1?true:false);
				Timestamp ts = rs.getTimestamp("uldatetime");
				Date temp = new Date(ts.getTime());
				a.setUldatetime(temp);
				a.setTitle(rs.getString("title"));
				String content = rs.getString("content");
				content = content.substring(0,600);
				a.setContent(content);
				articlelist.add(a);
			}	
		}
		
		catch(Exception e)
		{	
		   e.printStackTrace();
		}
		
		finally {
			  try{
				  if(con!=null) {con.close();rs.close();System.out.println("Connection closed");}
			  }
			  catch(Exception eas) {eas.printStackTrace();}
		  }
		
		return articlelist;
	}
	
	public static Article fetchPostByUrl(String url, String userid)
	{   
	    Connection con = null;
	    ResultSet rs = null;
	    Article a = new Article();
	    try
	    {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb","root","axess");
	    	
	    	PreparedStatement ps = con.prepareStatement("select a.aid,a.userid,url,(select count(*) from likes l where l.aid=a.aid) as countlikes, (select count(*) from comments c where c.aid=a.aid) as countcom,fname,lname,uldatetime, (select count(*) from likes l where l.userid=? and l.aid=a.aid) as liked, ac.title, ac.content from article a, profile p, articlecontent ac where a.userid=p.userid and a.aid = ac.aid and a.url=? order by uldatetime desc");
	    	ps.setString(1, userid);
	    	ps.setString(2, url);
	    	rs = ps.executeQuery();
	    	
	    	while(rs.next())
	    	{   
	    		a.setAid(rs.getInt("aid")+"");
	    		a.setUserid(rs.getInt("userid")+"");
	    		a.setUrl(rs.getString("url"));
	    		a.setCount_likes(rs.getInt("countlikes"));
	    		a.setCount_comments(rs.getInt("countcom"));
	    		a.setFname(rs.getString("fname"));
	    		a.setLname(rs.getString("lname"));
	    		java.sql.Timestamp ts = rs.getTimestamp("uldatetime");
				Date temp = new Date(ts.getTime());
				a.setUldatetime(temp);
	    		a.setLiked(rs.getInt("liked")==1?true:false);
	    		if(a.getLiked())
	    			{
	    				System.out.println("article : "+a.getAid() +" Liked : "+rs.getInt("liked"));
	    			}
	    		a.setTitle(rs.getString("title"));
	    		String content = rs.getString("content");
	    		content = content.substring(0, 600);
	    		a.setContent(content);
	    		a.setLikerlist(LikeService.getNamesofLikers(a.getAid()));
	    	}
	    
	    }
	    catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	    
	    finally {
			  try{
				  if(con!=null) {con.close();rs.close();System.out.println("Connection closed");}
			  }
			  catch(Exception eas) {eas.printStackTrace();}
		  }
		  return a;
	}
}
