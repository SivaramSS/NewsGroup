package org.sivaram.newsgroup.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.sivaram.newsgroup.models.User;
import org.sivaram.newsgroup.service.CheckLogin;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ValidationAware;

public class LoginAction extends ActionSupport implements SessionAware, ServletRequestAware {

	SessionMap<String,Object> sessionMap;
	private User user = new User();
	private static String SUCCESS = "success", LOGIN = "login";
    private boolean logoutval;
    HttpServletRequest request;
    String loginflag;
    
	public String getLoginflag() {
		return loginflag;
	}

	public void setLoginflag(String loginflag) {
		this.loginflag = loginflag;
	}

	public boolean isLogoutval() {
		return logoutval;
	}

	public void setLogoutval(boolean logoutval) {
		this.logoutval = logoutval;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void validate()
	{
		System.out.println("In validate Login action : "+ loginflag + " ");
	 if(!sessionMap.containsKey("user") && loginflag!=null && loginflag.contains("true") )
	  {
		 if(StringUtils.isEmpty(user.getEmail()))
			 addFieldError("user.email", "Email id cannot be blank");
		 
		 if(StringUtils.isEmpty(user.getPassword()))
			 addFieldError("user.password","Password cannot be blank");
		 
		 //user.setEmail( user.getEmail().replaceAll("[-+^=;]", "") );
		 //user.setPassword(user.getPassword().replaceAll("[-+^=;]", ""));
	  }
	 
	}
	
	public String execute()
	{   
		if(sessionMap.containsKey("user"))
			return SUCCESS;
		else
		{
			System.out.println("In LoginAction : "+user.getEmail() +" " + user.getPassword());
			CheckLogin cl = new CheckLogin();
		
			if(cl.authenticate(user)==true) 
				{
			  		user = cl.getUserob();
			  		sessionMap.put("user", user.getUserid()+"");
			  		return SUCCESS;
				}
			else
				return LOGIN;
	   }
	}

	public String logout()
	{
     sessionMap.remove("user");
	 if(sessionMap.containsKey("user"))
		 return "feed";
	 
	 return SUCCESS;	
	}
	
	@Override
	public void setSession(Map<String, Object> map) {
		this.sessionMap = (SessionMap<String, Object>) map;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

}
