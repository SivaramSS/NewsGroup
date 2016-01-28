<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="user.fname"/></title>
</head>
<body style="background: #d3d3d3;">
	<div align="left" style="margin : 10px;">
		<br/>
		<s:property value="user.fname" /> <s:property value="user.lname"/>
     	<br/>
     	<br/>
     	<s:property value="user.dob" />
     	<br/>
     	<br/>
     	<s:if test="%{user.email==\"\"}">
     	</s:if> 
     	<s:else>
     		<s:property value="user.email" />
     		<br/>
     		<br/>
     	</s:else>
     	Contribution : <s:property value="user.postsCount"/> posts
	</div>
	
</body>
</html>