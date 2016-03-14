<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=2.0, user-scalable=yes" />
<link media="Screen" href="styles.css" type="text/css" rel="stylesheet" />
<link media="handheld, only screen and (max-width: 720px), only screen and (max-device-width: 720px)" href="mobile.css" type="text/css" rel="stylesheet" />
</head>
<div style="z-index: 2; position: relative; bottom: 2%; background-color: #b3b3b3; padding: 5px; color: black; display:inline-block; ">
<s:iterator value="likelist" status="name"> 
	<div><s:property/></div>	
</s:iterator>
</div>
