package com.sharepoint.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sharepoint.model.User;

public class CreateAccount {
	
	public boolean create(User user)
	{
		boolean flag=false;
	   	Connection con = null;
	   	try
	   	{
	   	  Class.forName("com.mysql.jdbc.Driver");
	   	  con = DriverManager.getConnection("jdbc:mysql://localhost/newsgroupDB","root","axess");
	   	  PreparedStatement ps = con.prepareStatement("INSERT into profile(userid,email,password,fname,lname,dob) values(null,?,?,?,?,?)" );
	   	  ps.setString(1, user.getEmail());
	   	  ps.setString(2, user.getPassword());
	   	  ps.setString(3, user.getFname());
	   	  ps.setString(4, user.getLname());
	   	  java.sql.Date d = Date.valueOf(user.getDob());
	   	  ps.setDate(5, d);
	   	  int result = ps.executeUpdate();
	   	  if(result == 1)
	   		  flag = true;
	   	}
	   	
	   	catch(Exception e)
	   	{
	   	  e.printStackTrace();
	   	}
	   	finally {
	   		try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	   	}
	   return flag;
	}
	
}
