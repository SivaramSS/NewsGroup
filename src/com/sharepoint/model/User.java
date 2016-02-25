package com.sharepoint.model;

import java.util.Date;


public class User {

	String userid, email, password, cp, fname, lname, dob,postsCount;
	Date dob2;
	
	public Date getDob2() {
		return dob2;
	}
	public void setDob2(Date dob2) {
		this.dob2 = dob2;
	}
	
	public String getPostsCount() {
		return postsCount;
	}
	public void setPostsCount(String postsCount) {
		this.postsCount = postsCount;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
}
