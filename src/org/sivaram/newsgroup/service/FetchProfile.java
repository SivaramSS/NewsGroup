package org.sivaram.newsgroup.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.sivaram.newsgroup.models.User;


public class FetchProfile {
	User user;
	String countposts;
	
	public String getCountposts() {
		return countposts;
	}

	public void setCountposts(String countposts) {
		this.countposts = countposts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FetchProfile()
	{
	}
	
	public void getProfile(String userid)
	{
	  Connection con = null;
	  ResultSet rs = null;
	  user = new User();
	  try
	  {
		  Class.forName("com.mysql.jdbc.Driver");
		  con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb","root","axess");
		  PreparedStatement ps = con.prepareStatement("select dob,email,fname,lname from profile where userid=?");
		  ps.setString(1, userid);
		  rs = ps.executeQuery();
		  
		  if(rs.next())
		  	{
			  System.out.println(rs.getString("fname"));
			  user.setDob(rs.getDate("dob").toString());
			  user.setEmail(rs.getString("email").toString());
			  user.setFname(rs.getString("fname").toString());
			  user.setLname(rs.getString("lname").toString());
			  user.setUserid(userid);
		  	}
		  ps = con.prepareStatement("select count(*) as countposts from article where userid=?");
		  ps.setString(1,userid);
		  rs = ps.executeQuery();
		  if(rs.next())
		  	{
			  setCountposts(rs.getInt("countposts") + "");
			  user.setPostsCount(countposts);
		  	}
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	}

}
