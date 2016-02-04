package org.sivaram.newsgroup.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sivaram.newsgroup.models.Article;

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
	    	
	    	PreparedStatement ps = con.prepareStatement("select aid,a.userid,url,(select count(*) from likes l where l.aid=a.aid) as countlikes, (select count(*) from comments c where c.aid=a.aid) as countcom,fname,lname,uldatetime, (select count(*) from likes l where l.userid=? and l.aid=a.aid) as liked from article a, profile p where a.userid=p.userid order by uldatetime desc");
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
	    		a.setLiked(rs.getInt("liked"));
	    		if(a.isLiked()==1)
	    			{
	    				System.out.println("article : "+a.getAid() +" Liked : "+rs.getInt("liked"));
	    			}
	    		HTMLParser hp = new HTMLParser();
	    		if(hp.parse(a.getUrl())==true)
	    		 {
	    			a.setTitle(hp.getTitle());
	    			a.setContent(hp.getContent());
	    			articlelist.add(a);
	    		 }
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
	    PreparedStatement ps = con.prepareStatement("select a.userid,url,uldatetime,(select count(*) from likes where aid=?) as countlikes, (select count(*) from comments where aid=?) as countcomments,fname,lname, (select count(*) from likes l where l.userid=? and l.aid = a.aid) as liked from article a,profile p where a.aid=? and a.userid = p.userid");
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
	    	  a.setLiked(rs.getInt("liked"));
	    	  a.setFname(rs.getString("fname"));
	    	  a.setLname(rs.getString("lname"));
	    	  a.setCount_likes(rs.getInt("countlikes"));
	    	  a.setCount_comments(rs.getInt("countcomments"));
	    	  
	    	  HTMLParser hp = new HTMLParser();
	    	  if(hp.parseFullPage(a.getUrl())==true)
	    	  	{
	    		  a.setTitle(hp.getTitle());
	    		  a.setContent(hp.getContent());
	    	  	}
	    	  else
	    		  a.setTitle("Article failed to load");
	    	}
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return a;
	}
	
	public static List<Article> fetchArticlesByUserId(String userid)
	{
		List<Article> articlelist = new ArrayList<Article>();
		Connection con = null;
		ResultSet rs = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb","root","axess");
			PreparedStatement ps = con.prepareStatement("select aid,url,fname,lname,uldatetime,(select count(*) from likes l where l.aid = a.aid) as countlikes, (select count(*) from comments c where c.aid=a.aid) as countcomments, (select count(*) from likes l where l.userid=? and l.aid=a.aid) as liked from profile p, article a where p.userid=a.userid and a.userid=?");
			ps.setString(1, userid);
			ps.setString(2, userid);
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
				a.setLiked(rs.getInt("liked"));
				Timestamp ts = rs.getTimestamp("uldatetime");
				Date temp = new Date(ts.getTime());
				a.setUldatetime(temp);
				HTMLParser hp = new HTMLParser();
				if( hp.parse(a.getUrl())==true )
					{
						System.out.println(hp.getTitle());
						a.setTitle(hp.getTitle());
						a.setContent(hp.getContent());
					}
				else
					{
						a.setTitle("Article failed to load");
					}
				articlelist.add(a);
			}	
		}
		
		catch(Exception e)
		{
		   e.printStackTrace();
		}
		
		return articlelist;
	}
}
