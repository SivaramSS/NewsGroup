<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
		<s:url var="logout" action="Logout">
			<s:param name="logout">true</s:param>
		</s:url>
		
		<span style="margin-top:1%; margin-bottom:1%; margin-left:3%; white-space:nowrap; float:left;">
			<a href="/NewsGroup/" style="color:#ffffff; font-size:20px; font-family:Lucida Console; text-decoration:none;">Sharepoint</a>
		</span>
		
    	<span style="margin-top:1%; margin-bottom:1%; margin-right:5%; white-space:nowrap; float:right;">
    	
			<span onmouseover="bgchange(this,'#415E9B');" onmouseout="bgchange(this,'transparent');">
				<a href="javascript:;" onclick="loadFeed();" style="color:#ffffff; margin-top:10px; margin-left:10px; margin-bottom:10px; font-size:14px; font-family:Lucida Console; text-decoration:none;">Feed </a>
			</span>
			
			<span onmouseover="bgchange(this,'#415E9B');" onmouseout="bgchange(this,'transparent');">
				<a href="javascript:;" onclick="loadProfile('own');" style="color:#ffffff; margin-top:10px; margin-left:10px; margin-bottom:10px; font-size:14px; font-family:Lucida Console; text-decoration:none;">Profile</a>
			</span>
			
			<span onmouseover="bgchange(this,'#415E9B');" onmouseout="bgchange(this,'transparent');">
				<a href="<s:property value="%{logout}"/>" style="color:#ffffff; margin-top:10px; margin-left:10px; margin-right:20px; font-size:14px; margin-bottom:10px; font-family:Lucida Console; text-decoration:none;">Logout</a>	  
			</span>
			
		</span>
