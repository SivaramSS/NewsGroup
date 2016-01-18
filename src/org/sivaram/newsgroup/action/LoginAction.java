package org.sivaram.newsgroup.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.sivaram.newsgroup.models.User;
import org.sivaram.newsgroup.service.CheckLogin;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements SessionAware {

	SessionMap<String,Object> sessionMap;
	private User user = new User();
	private static String SUCCESS = "success", LOGIN = "login";
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void validate()
	{
	 	
	 if(StringUtils.isEmpty(user.getEmail()))
		 addFieldError("user.email", "Email id cannot be blank");
	 
	 if(StringUtils.isEmpty(user.getPassword()))
		 addFieldError("user.password","Password cannot be blank");
	 
	}
	
	public String execute()
	{   
		System.out.println("In LoginAction : "+user.getEmail() +" " + user.getPassword());
		CheckLogin cl = new CheckLogin();
		//if( sessionMap.containsKey("login") )
		//	return SUCCESS;
		//else
		if(cl.authenticate(user)==true) 
			{
			  	user = cl.getUserob();
			  	sessionMap.put("user", user.getUserid()+"");
			  	return SUCCESS;
			}
		else
			return LOGIN;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.sessionMap = (SessionMap<String, Object>) map;
	}

}
