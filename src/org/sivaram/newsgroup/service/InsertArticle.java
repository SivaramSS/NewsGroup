package org.sivaram.newsgroup.service;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.jsoup.Jsoup;
import org.sivaram.newsgroup.models.Article;

public class InsertArticle {

	
	public static boolean insert(Article a)
	{
		Connection con = null;
		int result = 0;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb", "root", "axess");
			PreparedStatement ps = con.prepareStatement("Insert into article(aid,userid,url,countlikes,countcom,uldatetime) values(null,?,?,0,0,?) ");
			ps.setString(1, a.getUserid());
			ps.setString(2, a.getUrl());
			Date dt = new Date();
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ct = sdf.format(dt);
			ps.setString(3, ct);
			result = ps.executeUpdate();
			
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
		if(result==1)
			return true;
		return false;
		
	}
	
}
