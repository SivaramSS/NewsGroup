package com.sharepoint.model;

import java.util.Date;

public class Article {

	String userid, fname, lname, url, title, content, aid;
	int count_likes, count_comments;
	Date uldatetime;
	int liked;
	
	   public int getLiked() {
		return liked;
	}
	public void setLiked(int liked) {
		this.liked = liked;
	}
	public String getTitle() {
		return title;
	}
	public String getUldatetime() {
		return uldatetime.toString();
	}
	public void setUldatetime(Date uldatetime) {
		System.out.println("In ul datetime: "+uldatetime.toString());
		this.uldatetime = uldatetime;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public int getCount_likes() {
		return count_likes;
	}
	public void setCount_likes(int count_likes) {
		this.count_likes = count_likes;
	}
	public int getCount_comments() {
		return count_comments;
	}
	public void setCount_comments(int count_comments) {
		this.count_comments = count_comments;
	}
	   
}
