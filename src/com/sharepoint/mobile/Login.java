package com.sharepoint.mobile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sharepoint.model.User;
import com.sharepoint.services.CheckLogin;

public class Login extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	HttpServletRequest request;
	HttpServletResponse response;
	User user;
	
	public String checkLogin()
	{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = new User();
		user.setEmail(email);
		CheckLogin cl = new CheckLogin();
		if(cl.authenticate(user,password)==true)
			{
				setUser(cl.getUserob());
			}
		else
			{
				User temp = new User();
				temp.setUserid("null");
				setUser(user);
			}
		
		return "success";
	}
	
	@Override
	public void setServletResponse(HttpServletResponse res) {
		this.response = res;
	}
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
