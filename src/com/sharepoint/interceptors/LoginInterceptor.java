package com.sharepoint.interceptors;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sharepoint.model.LoginAction;
import com.sharepoint.model.User;
import com.sharepoint.model.UserAware;

public class LoginInterceptor implements SessionAware, Interceptor{
	SessionMap<String,Object> sessionMap;
	User user = new User();
	
	public String intercept(ActionInvocation invocation) throws Exception {
		
		sessionMap = (SessionMap<String,Object>) invocation.getInvocationContext().getSession();
		Action action = (Action) invocation.getAction();
		
		if(sessionMap.get("user")==null){
			if(action.getClass().getName().toString().equalsIgnoreCase("com.sharepoint.model.LoginAction"))
				return invocation.invoke();
			System.out.println("login first");
			return "login";
		}
		else {
			setUser(user.getUserObject(sessionMap.get("user").toString()));
			System.out.println("you shall pass "+user.getFname() );
			System.out.println("Session exists "+action.getClass().getName());
			
			if(action.getClass().getName().equals("com.sharepoint.model.LoginAction"))
				return "success";
				
			if(action instanceof UserAware){
				System.out.println("YES "+action.getClass() );
				((UserAware) action).setUser(user);
			}
			return invocation.invoke();
		}
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.sessionMap = (SessionMap<String,Object>) map;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public void init(){
	}
	
	@Override
	public void destroy(){
	}
}
