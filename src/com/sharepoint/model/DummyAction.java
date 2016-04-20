package com.sharepoint.model;

import com.opensymphony.xwork2.ActionSupport;

public class DummyAction extends ActionSupport implements UserAware {
	User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String execute(){
		return "success";
	}
}
