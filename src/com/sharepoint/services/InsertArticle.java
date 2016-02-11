package com.sharepoint.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.sharepoint.model.Article;

public class InsertArticle {

	Article article;
	
	public boolean insert(Article a)
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
			if(result==1)
			{
				System.out.println("Inserted article url");
				ps.clearBatch();
				ps.clearParameters();
				ps = con.prepareStatement("Insert into articlecontent(aid,title,content) values( (select aid from article where url=?),?,?) ");
				ps.setString(1, a.getUrl());
				ps.setString(2, a.getTitle());
				ps.setString(3, a.getContent());
				result = ps.executeUpdate();
					
				if(result==1)
					{
						System.out.println("Inserted content");
						ps.clearBatch();
						ps.clearParameters();
						ps = con.prepareStatement("select aid from article where url = ?");
						ps.setString(1,a.getUrl());
						ResultSet rs = ps.executeQuery();
						if(rs.next())
							a.setAid(rs.getInt("aid")+"");
					}
				else
					{
						System.out.println("Insert content failure");
						ps.clearBatch();
						ps.clearParameters();
						ps = con.prepareStatement("delete from article where url = ?");
						ps.setString(1,a.getUrl());
						result = ps.executeUpdate();
						System.out.println("Removing article inserted result : "+result);
					}
				}
			else
				System.out.println("Insert url failure");
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			PreparedStatement preparedStatement;
			
			try {
				preparedStatement = con.prepareStatement("delete from article where url = ?");
				preparedStatement.setString(1,a.getUrl());
				result = preparedStatement.executeUpdate();
				System.out.println("Removing article inserted result : "+result);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
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
			{
				setArticle(a);
				return true;
			}
		return false;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
	

}
