package org.sivaram.newsgroup.action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ShareAction extends ActionSupport implements SessionAware {

	SessionMap<String, Object> sessionMap;
	
	@Override
	public void setSession(Map<String, Object> map) {
		
		sessionMap = (SessionMap<String, Object>) map;
	}
	
	public String shareUrl()
	{
		return "generated";
	}

}
