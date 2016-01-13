package org.sivaram.newsgroup.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sivaram.newsgroup.models.User;



public class CheckLogin {
   
	private String userid = null;
	private User userob = new User();
	
	public User getUserob() {
		return userob;
	}

	public void setUserob(User userob) {
		this.userob = userob;
	}

	public boolean authenticate(User user)
	{ 
	    boolean ret  = false;
	    Connection conn = null;
	    ResultSet rs = null;
	    System.out.println("Checking Login" + user.getEmail() +" "+user.getPassword());
	    
	    try
	    {
	      
	      Class.forName("com.mysql.jdbc.Driver").newInstance();
	      
	      conn = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupdb", "root", "axess");
	      
	      PreparedStatement ps = conn.prepareStatement("SELECT userid,fname,lname,dob from profile where email=? and password=?");
	      
	      ps.setString(1,user.getEmail());
	      
	      ps.setString(2, user.getPassword());
	      
	      rs = ps.executeQuery();
	      
	      if(rs.next())
	      	{	
	    	  System.out.println("Result set exists!");
	    	  
	    	  //while(rs.next())
	            {
	    			user.setUserid(rs.getString(1));
	    			user.setFname(rs.getString(2));
	    			user.setLname(rs.getString(3));
	    			user.setDob(rs.getDate(4).toString());
	    			System.out.println("Userid" + user.getUserid());
	      		}
	    	  userob = user;
	    	  ret = true;
	      	}
	      
	      else
	    	  
	    	ret = false;
	      
	    }
	    
	    catch (Exception e)
	    {
	      ret = false;
	    }
	    
	    finally {
	    	
	    	try {
	    			if(conn!=null)
	    				{
	    					conn.close();
	    					rs.close();
	    				}	
			} 
	    	
	    	catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    
		return ret;
	}
	
	public String getUserid() {
		
		return userid;
	
	}
	
	public void setUserid(String userid) {
		
		this.userid = userid;
	
	}
}
