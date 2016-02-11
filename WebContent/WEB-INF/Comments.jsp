<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<s:iterator value="commentlist" var="comment"> 
   <div style="background: #ffffff;">
   			<div style="display:inline-block;"> 
   				<a href="#" onclick="loadProfile('<s:property value="%{#comment.userid}"/>');">
   					<s:property value="#comment.fname"/>
   				</a>
   				<s:property value="#comment.content" />  on
   				<p style="font-size:10; display:inline-block"><s:property value="#comment.cdate"/></p>
   			</div>		    
   </div>
</s:iterator>
<form>
	<s:set var="comcount">comcount<s:property value="%{aid}"/></s:set>
	<s:set var="comsec">comsection<s:property value="%{aid}"/></s:set>
	<input type="text" name="usercomment" value="Enter your comment" onfocus="if(this.value == 'Enter your comment') {this.value=''}" onblur="if(this.value == '') {this.value='Enter your comment'}"/>
	<input type="button" value="Comment" onclick="insert(this.form.usercomment.value, '<s:property value="%{aid}"/>', '<s:property value="%{#comsec}"/>', '<s:property value="%{#comcount}"/>');"/>
</form>	
