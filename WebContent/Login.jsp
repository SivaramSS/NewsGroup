<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
    <h3>Login</h3>
    <br/>
    <s:form name="getin" method = "POST" action = "/Login" >
       <s:textfield key="user.email" label="Email Id" />
       <s:password key="user.password" label="Password" />
       
       <s:submit value="Login" />
    </s:form>
    <br/>
    <h3>Or Create an account</h3>
    
    <s:form name="createacc" method = "POST" action = "/Signup">
    	<s:textfield key="fname" label="Enter first name" value=""/>
    	<s:textfield key="lname" label="Enter last name" value=""/>
    	<s:textfield key="email" label="Enter email id" value=""/>
    	<s:password key="password" label="Enter password" value=""/>
    	<s:password key="cp" label="Confirm Password" value=""/>
    	<s:textfield key="dob" label="Birthday" value="1990-12-31"/>
    	<s:submit value="Sign Up"/>
    </s:form>
</body>
</html>