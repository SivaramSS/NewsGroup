<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NewsGroup</title>
</head>
<body>   
    <h2>NewsGroup</h2> 
    <div align="right">
    <s:url value="Profile" var="ownprof">
     	<s:param name="id" value="\"own\"" />
    </s:url>
    <a href="Feed">Home</a>|<a href="<s:property value="%{#ownprof}"/>">Profile</a>|<a href="">Log out</a>
    </div>
    <hr/>
    
    <div align="center">
    	<s:form method="post" action="/Share">
     	<s:textfield name="shareurl" value="copy and paste url of article here" />
     	<s:submit value="Share"/>  	
     	</s:form>
    </div>
    
    <s:iterator value="articlelist" var="article"> 
    
    <s:url value="Profile" var="url">
         <s:param name="id" value="%{#article.userid}" />
    </s:url>
    		
    <a href="<s:property value="%{#url}"/>" >
    	 <s:property value="#article.fname" /> 
    </a>
    
    <br/>
    <s:property value="#article.url" />
    <br/>
    
    </s:iterator>
    
    
</body>
</html>