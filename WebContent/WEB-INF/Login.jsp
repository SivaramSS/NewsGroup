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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js">
</script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#fname").after("<p id='fnameerr' style='color:#ff0000;'/>");
		$("#lname").after("<p id='lnameerr' style='color:#ff0000;'/>");
		$("#dob").after("<p id='doberr' style='color:#ff0000;'/>");
		$("#password").after("<p id='passworderr' style='color:#ff0000;'/>");
		$("#cp").after("<p id='cperr' style='color:#ff0000;'/>");
		$("#email").after("<p id='emailerr' style='color:#ff0000;'/>");
		
		$("#Login_user_email").after("<p id='loginemailerr' style='color:#ff0000'/>");
		$("#Login_user_password").after("<p id='loginpassworderr' style='color:#ff0000'/>");
		
		$("#cp").keyup(checkPassword);
		$("#dob").keyup(dateValidation);
	});
	
	function dateValidation()
	{
		var date = $("#dob").val();
		var matches = /^(\d{4})[-\/](\d{2})[-\/](\d{2})$/.exec(date);
		if (matches == null) 
		    $("#doberr").text("Invalid format");
		else
		    $("#doberr").text("");
	}
	
	function checkPassword()
	{
	   var password = $("#password").val();
	   var cp = $("#cp").val();
	   
	   if(cp!= null  && password !=null)
	   {
		   if( password === cp)
			   {
			   		$("#cperr").text("Password match");
		 	   }
	   	   else
		   	   {
		   		 	$("#cperr").text("Passwords do not match");
		   	   }
	   }	   
	}
	
	function validate()
	{
		var fname = document.forms["createacc"]["fname"].value;
		var lname = document.forms["createacc"]["lname"].value;
		var email = document.forms["createacc"]["email"].value;
		var password = document.forms["createacc"]["password"].value;
		var cp = document.forms["createacc"]["cp"].value;
		var dob = document.forms["createacc"]["dob"].value;
		var result = true;
		
		if(fname == null || fname == "")
			{
				document.getElementById("fnameerr").innerHTML = "First name cannot be blank";
				result = false;
			}
		if(lname == null || lname == "")
			{
				document.getElementById("lnameerr").innerHTML = "Last name cannot be blank";
				result = false;
			}
		if(email == null || email=="")
			{
				document.getElementById("emailerr").innerHTML = "Email cannot be blank";
				result = false;
			}
		if(password == null || password == "")
			{
				document.getElementById("passworderr").innerHTML = "Password cannot be blank";
				result = false;
			}
		if(cp == null || cp == "")
			{
				document.getElementById("cperr").innerHTML = "Password does not match";
				result = false;
			}
		
		return result;
	}
	
	function loginValidation()
	{
		var email = document.forms["getin"]["Login_user_email"].value;
		var password = document.forms["getin"]["Login_user_password"].value;
		var result = true;
		
		if(email == null || email == "")
			{
				document.getElementById("loginemailerr").innerHTML = "Email cannot be blank";
				result = false;
			}
		if(password == null || password == "")
			{
				document.getElementById("loginpassworderr").innerHTML = "Password cannot be blank";
				result = false;
			}
		
		return result;
	}
	
</script>
</head>

<body style="background: #d3d3d3; border:0px; padding:0px;">
	<div id="Header" class="Header" style="background: #003366; padding:2px; ">
			<p align="left"  style="display:inline-block; color:#ffffff; margin-left:70px; margin-top:10px; margin-bottom:10px; font-size:20px; font-family:Lucida Console;">Welcome to NewsGroup</p>
	</div>
	
    <div id="cover">
    		<h3>Login</h3>
    		<s:form name="getin" method = "POST" action = "/Login">
       			<s:textfield key="user.email" label="Email Id" />
       			<s:password key="user.password" label="Password"/>
       			<s:hidden key="loginflag" value="true"/>
       			<s:submit value="Login" />
    		</s:form>
    		<div>
    		<h3>Or Create a New Account </h3>
    		</div>
    		<s:form name="createacc" method = "POST" action = "/Signup" onsubmit="return validate();">
    			<s:textfield key="fname" label="Enter first name" value="" name="fname" id="fname"/>
    			<s:textfield key="lname" label="Enter last name" value="" name="lname" id="lname"/>
    			<s:textfield key="email" label="Enter email id" value="" name="email" id="email"/>
    			<s:password key="password" label="Enter password" value="" name="password" id="password"/>
    			<s:password key="cp" label="Confirm Password" value="" name="cp" id="cp"/>
    			<s:textfield key="dob" label="Birthday (yyyy-MM-dd)" value="1990-12-31" name="dob" id="dob"/>
    			<s:submit value="Sign Up"/>
    		</s:form>
  </div>
</body>
</html>