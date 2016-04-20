package com.sharepoint.interceptors;

import com.opensymphony.xwork2.ActionInvocation;

public interface AbstractInterceptor {
	
	void init();
	String intercept(ActionInvocation invocation) throws Exception;
	void destroy();
}
