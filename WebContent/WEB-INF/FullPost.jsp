<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="post.fname"/>'s Post <s:property value="post.uldatetime"/></title>
</head>

<body style="background: #d3d3d3">
	<div align="center" style="background: #ffffff; margin-top:40dp; margin-left:200px; margin-right:200px;border-radius:10px;" >
	 <div align="left" style="padding:20px;margin-top:20px;">
	 	<a href="#" onclick="loadProfile('<s:property value="%{post.userid}"/>');"><s:property value="post.fname"/> <s:property value="post.lname"/></a> 
	 </div>
	 <div style="margin-left:40px;margin-right:40px" align="left">
	 	<h4><s:property value="post.title"/></h4>
	 	<p style="">
	 		<s:property value="post.content" />
	 	</p> 
	</div>
	
	<s:set var="liked" value="%{post.liked}"/>
    <s:set var="userid" value="%{post.userid}"/>
    <s:set var="aid" value="%{post.aid}" />
    <s:set var="countlikes" value="%{post.count_likes}" />
    <s:set var="countcom" value="%{post.count_comments}" />
    <s:set var="comsec">comsection<s:property value="%{post.aid}"/></s:set>
    <s:set var="likesec">likesection<s:property value="%{post.aid}"/></s:set>
    <s:set var="comcount">comcount<s:property value="%{post.aid}"/></s:set>	
    				
    <div align="right" style="padding-right:15px;">
    	Shared on <s:a href="javascript:;" onclick="viewFullPost('%{#aid}');"><s:property value="post.uldatetime"/></s:a>
    </div>
    
    <div align="left" style="padding:10px;margin-left:20px;">
    <s:if test="%{#countlikes>0}">
    	<div align="left" id="<s:property value="%{#likesec}"/>" class="<s:property value="%{#likesec}"/>" style="display:block;" >
    		<s:property value="%{post.count_likes}"/> like this 
    	</div>
    </s:if><s:else>
    	<div align="left" id="<s:property value="%{#likesec}"/>" class="<s:property value="%{#likesec}"/>" style="display:none;" >
    		<s:property value="%{post.count_likes}"/> like this 
    	</div>
    </s:else>
    				
    <s:if test="%{#liked==1}">
    	<s:a id="likebtn" href="javascript:;" onclick="change(this, '%{#aid}', '%{#userid}', '%{#likesec}');" >Unlike</s:a>
    </s:if> <s:else>
    	<s:a id="likebtn" href="javascript:;" onclick="change(this, '%{#aid}', '%{#userid}', '%{#likesec}');">Like</s:a>
    </s:else>
    				
    <s:if test="%{#countcom==0}">
    	<s:a id="%{#comcount}" href="javascript:;" onclick=" comment( '%{#aid}', '%{#comsec}'); " >comment</s:a>
    </s:if> <s:elseif test="%{#countcom==1}">
    	<s:a id="%{#comcount}" href="javascript:;" onclick=" comment( '%{#aid}', '%{#comsec}'); "><s:property value="%{post.count_comments}" /> comment</s:a> 
    </s:elseif> <s:else>
    	<s:a id="%{#comcount}" href="javascript:;" onclick=" comment( '%{#aid}', '%{#comsec}'); "><s:property value="%{post.count_comments}"/> comments</s:a> 
    </s:else>
    				
    <div class="<s:property value="%{#comsec}"/>" id="<s:property value="%{#comsec}" />" align="center" style="display:none;">
    </div>
    </div>
  </div>
</body>
</html>