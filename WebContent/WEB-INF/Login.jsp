<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<style tyep="text/css">
#cover {
	margin-left : 1%;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js">
</script>

<sx:head/>
</head>

<body style="background: #d3d3d3; border:0px; padding:0px;">
	<div id="Header" class="Header" style="background: #003366; padding:2px; ">
			<p align="left"  style="display:inline-block; color:#ffffff; margin-left:70px; margin-top:10px; margin-bottom:10px; font-size:20px; font-family:Lucida Console;">Welcome to SharePoint</p>
	</div>
	
    <div id="cover">
    		<h3>Login</h3>
    		<s:form name="getin" method = "POST" namespace="/" action = "Login" validate="true">
       			<s:textfield key="user.email" label="Email Id" />
       			<s:password key="user.password" label="Password"/>
       			<s:hidden key="loginflag" value="true"/>
       			<s:submit value="Login" />
    		</s:form>
    		<div>
    		<h3>Or Create a New Account </h3>
    		</div>
    		<s:form name="createacc" method = "POST" namespace="/" action = "Signup" validate="true">
    			<s:textfield key="fname" label="Enter first name" value=""/>
    			<s:textfield key="lname" label="Enter last name" value=""/>
    			<s:textfield key="email" label="Enter email id" value=""/>
    			<s:password key="password" label="Enter password" value=""/>
    			<s:password key="cp" label="Confirm Password" value=""/>
    			<sx:datetimepicker key="dob2" label="Birthday (yyyy/MM/dd)" displayFormat="yyyy-MM-dd" value="%{'1995-07-26'}"/>
    			<s:submit value="Sign Up"/>
    		</s:form>
  </div>
</body>
</html>