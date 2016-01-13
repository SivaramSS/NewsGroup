package org.sivaram.newsgroup.action;

import java.sql.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.sivaram.newsgroup.models.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SignupAction extends ActionSupport implements SessionAware, ModelDriven {
    
	private User nuser = new User();
	SessionMap<String,String> sessionMap;
	private String SUCCESS="success", LOGIN="login";
	
	@Override
	public void setSession(Map<String, Object> map) {
		 sessionMap = (SessionMap) map;
	}


	public User getnuser() {
		return nuser;
	}

	public void setnuser(User nuser) {
		this.nuser = nuser;
	}
    
	public void validate()
	{
		if(StringUtils.isEmpty(nuser.getEmail()))
			addFieldError("email", " id cannot be blank");
		if(StringUtils.isEmpty(nuser.getPassword()))
			addFieldError("password", "Password cannot be blank");
		if(StringUtils.isEmpty(nuser.getCp()))
			addFieldError("cp", "Please Confirm password");
		if(StringUtils.isEmpty(nuser.getFname()))
			addFieldError("fname", "First name cannot be blank");
		if(StringUtils.isEmpty(nuser.getLname()))
			addFieldError("lname", "Last name cannot be blank");
		//if(nuser.getDob().toString().isEmpty())
			//addFieldError("dob", "Birthday cannot be blank");
	}
	
	public String createAccount()
	{ 
	   	System.out.println(nuser.getEmail());
	   	Date d = Date.valueOf(nuser.getDob());
	   	System.out.println("Dob : "+ d.toString());
	  return SUCCESS;   
	}


	@Override
	public Object getModel() {
		return nuser;
	}
	
}
