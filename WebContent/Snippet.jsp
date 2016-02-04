<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
<style type="text/css">
	input[type=submit] {
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

<center>
<form method="post" action="/NewsGroup/Share.action" style="margin-top:20px;">
     		<input type="text" id="shareurl" name="shareUrl" value="copy and paste url of article here" style="width:50%; height:25px; text-align:center; " onfocus="if(this.value == 'copy and paste url of article here') {this.value=''}" onblur="if(this.value == '') {this.value='copy and paste url of article here'}" />
     		<input type="submit" value="Share" style="height: 25px; "/>  	
</form>
</center>
<s:iterator value="articlelist" var="article" status="incr"> 
    		  <div style="background: #ffffff; margin-left:30px; margin-right: 30px; margin-top:30px; border-radius:10px;">
    		    <div align="left" style="padding:20px;">
    		    	
    				<s:set var="uid" value="%{#article.userid}"/>
    				
    				<s:a href="#" onclick="loadProfile('%{#uid}');">
    	 				<s:property value="#article.fname" /> 
    				</s:a>
    			</div>
    			
    			<div style="margin-left : 100px; margin-right :100px;" >
    				<h4><s:property value="#article.title"/></h4>
    				<br/>
    				<s:property value="#article.content"/>
    				<a href="<s:property value="#article.url"/>" target="_blank">...Read more</a>
    				<br/>
    			</div>
    			
    			<div align="left" style="margin-left:40px; padding-bottom:15px;">
    				<s:set var="liked" value="%{#article.liked}"/>
    				<s:set var="userid" value="%{#article.userid}"/>
    				<s:set var="aid" value="%{#article.aid}" />
    				<s:set var="countlikes" value="%{#article.count_likes}" />
    				<s:set var="countcom" value="%{#article.count_comments}" />
    				<s:set var="comsec">comsection<s:property value="#incr.index"/></s:set>
    				<s:set var="likesec">likesection<s:property value="#incr.index"/></s:set>
    				<s:set var="comcount">comcount<s:property value="#incr.index"/></s:set>
    				
    				<s:url action="ViewPost" var="ViewPost">
    					<s:param name="id" value="%{#article.aid}" />
    				</s:url>
    				
    				<div align="right" style="padding-right:15px;">
    					Shared on <s:a href="%{#ViewPost}" target="_blank"><s:property value="#article.uldatetime"/></s:a>
    				</div>
    				
    				<s:if test="%{#countlikes>0}">
    					<div align="left" id="<s:property value="%{#likesec}"/>" class="<s:property value="%{#likesec}"/>" style="display:block;" >
    						<s:property value="#article.count_likes"/> like this 
    					</div>
    				</s:if><s:else>
    					<div align="left" id="<s:property value="%{#likesec}"/>" class="<s:property value="%{#likesec}"/>" style="display:none;" >
    						<s:property value="#article.count_likes"/> like this 
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
    					<s:a id="%{#comcount}" href="#" onclick=" comment( '%{#comcount}','%{#aid}', '%{#comsec}'); "><s:property value="#article.count_comments" /> comment</s:a> 
    				</s:elseif> <s:else>
    					<s:a id="%{#comcount}" href="#" onclick=" comment( '%{#comcount}','%{#aid}', '%{#comsec}'); "><s:property value="#article.count_comments"/> comments</s:a> 
    				</s:else>
    				
     <div class="<s:property value="%{#comsec}"/>" id="<s:property value="%{#comsec}" />" align="center" style="display:none;">
    </div>
   </div>
 </div>
</s:iterator>
