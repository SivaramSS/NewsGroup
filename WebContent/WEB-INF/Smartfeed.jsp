<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=2.0, user-scalable=yes" />
<link media="Screen" href="styles.css" type="text/css" rel="stylesheet" />
<link media="handheld, only screen and (max-width: 720px), only screen and (max-device-width: 720px)" href="mobile.css" type="text/css" rel="stylesheet" />

<title>SharePoint</title>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="/NewsGroup/CSS/styles.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js">
</script>

<script src="/NewsGroup/scripts/Comments.js" type="text/javascript"></script>
<script src="/NewsGroup/scripts/Feed.js" type="text/javascript"></script>
<script src="/NewsGroup/scripts/Likes.js" type="text/javascript"></script>
<script src="/NewsGroup/scripts/Profile.js" type="text/javascript"></script>
<script src="/NewsGroup/scripts/Share.js" type="text/javascript"></script>

<script>
  $(document).ready( function() {
	  loadFeed();
  });
</script>
</head>
<body style="background: #d3d3d3;margin:0px; border:0px; padding:0px;">
	
	<% 
	  if(session.getAttribute("user")!=null)
	  {
	%>
	
	<s:url var="logout" action="Logout">
		<s:param name="logout">true</s:param>
	</s:url>
     	
	<div id="Header" class="Header" style="background: #003366; padding:2px">
	  <!--  <p align="left"  style="display:inline-block; color:#ffffff; margin-left:70px; margin-top:10px; margin-bottom:10px; font-size:20px; font-family:Lucida Console;">Sharepoint</p>
	  -->
	  <div style="display:inline-block; color:#ffffff; margin-left:70px; margin-top:10px; margin-bottom:10px; font-size:20px; font-family:Lucida Console;">
			<a href="javascript:;" onclick="loadFeed();" style="display:inline-block; color:#ffffff; margin-top:10px; margin-bottom:10px; font-size:12px; font-family:Lucida Console; text-decoration:none;">Feed </a>
			<a href="javascript:;" onclick="loadProfile('own');" style="display:inline-block; color:#ffffff; margin-top:10px; margin-left:10px; font-size:12px; font-family:Lucida Console; text-decoration:none;">Profile</a>
			<s:a href="%{logout}" style="display:inline-block; color:#ffffff; margin-top:10px; margin-left:10px; margin-right:20px; font-size:12px; font-family:Lucida Console; text-decoration:none;">Logout</s:a>	  
	  </div>
	</div>
	
	<center>
		<div id="sharebox" class="sharebox" style="margin-top:20px; visibility : hidden;">
     		<input type="text" id="shareurl" name="shareurl" value="copy and paste url of article here" style="width:50%; height:25px; text-align:center; " onfocus="if(this.value == 'copy and paste url of article here') {this.value=''}" onblur="if(this.value == '') {this.value='copy and paste url of article here'}" />
     		<input type="button" value="Share" style="height: 25px;" onclick="shareArticle();"/>  	
		</div>
	</center>
	
	<div id="loading" class="loading" align="center">
		<p id="caption" class="caption"></p>
		<br/>
		<center><img id="loading-image" src="loading.gif" alt="Loading..." /></center>
	</div>
	
	<div id="container" class="container" style="display:block; ">
	</div>	
	
	<%
	  }
	  else
	  {
	%>
	   <s:action name="Login" namespace="/" executeResult="true"></s:action>
	<%
	  }
	%>
</body>
</html>