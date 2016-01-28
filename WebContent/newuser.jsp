<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to NewsGroup</title>
</head>
<body style="background: #d3d3d3;">
    	 <div align="center">
    	 	<s:set value="user" var="user" />
    	 	Welcome <s:property value="fname"/>! Your account has been created. Please click <s:a href="/NewsGroup/Feed">Here</s:a> to Login. 
    	 </div>
</body>
</html>