package com.sharepoint.model;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sharepoint.services.FetchProfile;


public class User implements SessionAware{

	String userid, email,fname, lname, dob,postsCount,profileurl;
	SessionMap<String,Object> sessionMap;
	Date dob2;
	User instance;
	
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
	public String getProfileurl() {
		return profileurl;
	}
	public void setProfileurl(String profileurl) {
		this.profileurl = profileurl;
	}
	
	public User getInstance() {
		return instance;
	}
	
	public void setInstance(User instance) {
		this.instance = instance;
	}
	
	public User getUserObject(String userid)
	{
		FetchProfile fp = new FetchProfile();
		fp.getProfile(userid);
		User temp = fp.getUser();
		return temp;
	}
	
	public String execute()
	{
		String userid = sessionMap.get("user").toString();
		setInstance(getUserObject(userid));
		System.out.println("In User getting instance "+instance.getEmail());
		return "success";
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap = (SessionMap<String,Object>) map;
	}
}
