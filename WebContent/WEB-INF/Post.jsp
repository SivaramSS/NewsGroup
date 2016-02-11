<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<div style="background: #ffffff; margin-left:2%; margin-right: 2%; margin-top:4%; border-radius:2%;">
    		    <div align="left" style="padding:20px;">
    		    	
    				<s:set var="uid" value="%{article.userid}"/>
    				
    				<s:a href="#" onclick="loadProfile('%{#uid}');">
    	 				<s:property value="%{article.fname}" /> 
    				</s:a>
    			</div>
    			
    			<div style="margin-left : 5%; margin-right :5%;" >
    				<h4><s:property value="%{article.title}"/></h4>
    				<br/>
    				<s:property value="%{article.content}"/>
    				<a href="<s:property value="%{article.url}"/>" target="_blank">...Read more</a>
    				<br/>
    			</div>
    			
    			<div align="left" style="margin-left:6%; padding-bottom:2%;">
    				<s:set var="liked" value="%{article.liked}"/>
    				<s:set var="userid" value="%{article.userid}"/>
    				<s:set var="aid" value="%{article.aid}" />
    				<s:set var="countlikes" value="%{article.count_likes}" />
    				<s:set var="countcom" value="%{article.count_comments}" />
    				<s:set var="comsec">comsection<s:property value="%{aritcle.aid}"/></s:set>
    				<s:set var="likesec">likesection<s:property value="%{article.aid}"/></s:set>
    				<s:set var="comcount">comcount<s:property value="%{article.aid}"/></s:set>
    				
    				<s:url action="ViewPost" var="ViewPost">
    					<s:param name="id" value="%{article.aid}" />
    				</s:url>
    				
    				<div align="right" style="padding-right:2%;">
    					Shared on <s:a href="ViewPost" target="_blank"><s:property value="%{article.uldatetime}"/></s:a>
    				</div>
    				
    				<s:if test="%{#countlikes>0}">
    					<div align="left" id="<s:property value="%{#likesec}"/>" class="<s:property value="%{#likesec}"/>" style="display:block;" >
    						<s:property value="%{article.count_likes}"/> like this 
    					</div>
    				</s:if><s:else>
    					<div align="left" id="<s:property value="%{#likesec}"/>" class="<s:property value="%{#likesec}"/>" style="display:none;" >
    						<s:property value="%{article.count_likes}"/> like this 
    					</div>
    				</s:else>
    				
    				<s:if test="%{#liked==1}">
    					<s:a id="likebtn" href="#" onclick="change(this, '%{#aid}', '%{#userid}', '%{#likesec}');" >Unlike</s:a>
    				</s:if> <s:else>
    					<s:a id="likebtn" href="#" onclick="change(this, '%{#aid}', '%{#userid}', '%{#likesec}');">Like</s:a>
    				</s:else>
    				
    				<s:if test="%{#countcom==0}">
    					<s:a id="%{#comcount}" href="#" onclick=" comment( '%{#comcount}','%{#aid}', '%{#comsec}'); " >comment</s:a>
    				</s:if> <s:elseif test="%{#countcom==1}">
    					<s:a id="%{#comcount}" href="#" onclick=" comment( '%{#comcount}','%{#aid}', '%{#comsec}'); "><s:property value="%{article.count_comments}" /> comment</s:a> 
    				</s:elseif> <s:else>
    					<s:a id="%{#comcount}" href="#" onclick=" comment( '%{#comcount}','%{#aid}', '%{#comsec}'); "><s:property value="%{article.count_comments}"/> comments</s:a> 
    				</s:else>
    				
     <div class="<s:property value="%{#comsec}"/>" id="<s:property value="%{#comsec}" />" align="center" style="display:none;">
    </div>
   </div>
 </div>