package org.sivaram.newsgroup.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.sivaram.newsgroup.models.Article;

public class FetchArticles {
    
	public static ArrayList<Article> fetch()
	{   
		ArrayList<Article> articlelist = new ArrayList<Article>();
	    Connection con = null;
	    ResultSet rs = null;
	    
	    try
	    {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb","root","axess");
	    	
	    	PreparedStatement ps = con.prepareStatement("select aid,a.userid,url,countlikes,countcom,fname,lname from article a, profile p where a.userid=p.userid");
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
	    		
	    		System.out.println(a.getFname() + " : " + a.getUrl() );
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
			} 
	    	catch (SQLException e) {
				e.printStackTrace();
			}
	    	
	    }
	    
		return articlelist;
	}
}
