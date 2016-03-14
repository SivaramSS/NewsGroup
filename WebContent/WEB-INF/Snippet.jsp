<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=2.0, user-scalable=yes" />
<link media="Screen" href="styles.css" type="text/css" rel="stylesheet" />
<link media="handheld, only screen and (max-width: 720px), only screen and (max-device-width: 720px)" href="mobile.css" type="text/css" rel="stylesheet" />

<style type="text/css">
	input[type=button] {
    padding:5px 15px; 
    background:#003366; 
    border:0 none;
    color: #ffffff;
    cursor:pointer;
    -webkit-border-radius: 5px;
    border-radius: 5px; 
}
</style>
</head>
<s:if test="%{articlecount==0}">
	<div>
		<h1 style="color:#d3d3d3">No articles to show</h1>
	</div>
</s:if>
<s:else>
<s:iterator value="articlelist" var="article" status="incr"> 
		
    		  <div style="background: #ffffff; margin-left:2%; margin-right: 2%; margin-top:4%; border-radius:2%;">
    		    <div align="left" style="padding:20px;">
    		    	
    				<s:set var="uid" value="%{#article.userid}"/>
    				
    				<s:a href="#" onclick="loadProfile('%{#uid}');">
    	 				<s:property value="#article.fname" /> 
    				</s:a>
    			</div>
    			
    			<div style="margin-left : 5%; margin-right :5%;" >
    				<h4><s:property value="#article.title"/></h4>
    				<br/>
    				<s:property value="#article.content"/>
    				<a href="<s:property value="#article.url"/>" target="_blank">...Read more</a>
    				<br/>
    			</div>
    			
    			<div align="left" style="margin-left:6%; padding-bottom:2%;">
    				<s:set var="liked" value="%{#article.liked}"/>
    				<s:set var="userid" value="%{#article.userid}"/>
    				<s:set var="aid" value="%{#article.aid}" />
    				<s:set var="countlikes" value="%{#article.count_likes}" />
    				<s:set var="countcom" value="%{#article.count_comments}" />
    				<s:set var="comsec">comsection<s:property value="#article.aid"/></s:set>
    				<s:set var="likesec">likesection<s:property value="#article.aid"/></s:set>
    				<s:set var="comcount">comcount<s:property value="#article.aid"/></s:set>
    				<s:set var="wholike">wholikesection<s:property value="#article.aid"/></s:set>
    				
    				<div align="right" style="padding-right:2%;">
    					Shared on <s:a href="javascript:;" onclick="viewFullPost('%{#aid}');"><s:property value="#article.uldatetime"/></s:a>
    				</div>
    				
    				<s:if test="%{#countlikes>0}">
    					<div align="left" id="<s:property value="%{#likesec}"/>" class="<s:property value="%{#likesec}"/>" style="display:block;" >
    						<s:a href="javascript:;" onmouseout="hide('%{#wholike}');" onmouseover="showwholikes('%{#aid}','%{#wholike}');"> <s:property value="#article.count_likes"/> like this</s:a> 
    						<span id="<s:property value="%{wholike}"/>" class="<s:property value="%{wholike}"/>"></span>
    					</div>
    				</s:if><s:else>
    					<div align="left" id="<s:property value="%{#likesec}"/>" class="<s:property value="%{#likesec}"/>" style="display:none;" >
    						<s:a href="javascript:;" onmouseout="hide('%{#wholike}');" onmouseover="showwholikes('%{#aid}','%{#wholike}');"> <s:property value="#article.count_likes"/> like this</s:a> 
    						<span id="<s:property value="%{wholike}"/>" class="<s:property value="%{wholike}"/>"></span>
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
    					<s:a id="%{#comcount}" href="javascript:;" onclick=" comment( '%{#aid}', '%{#comsec}'); "><s:property value="#article.count_comments" /> comment</s:a> 
    				</s:elseif> <s:else>
    					<s:a id="%{#comcount}" href="javascript:;" onclick=" comment( '%{#aid}', '%{#comsec}'); "><s:property value="#article.count_comments"/> comments</s:a> 
    				</s:else>
    				
     <div class="<s:property value="%{#comsec}"/>" id="<s:property value="%{#comsec}" />" align="center" style="display:none;">
    </div>
   </div>
 </div>
</s:iterator>
</s:else>