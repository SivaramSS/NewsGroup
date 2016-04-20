<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="headerdiv" class="headerdiv">
		<s:url var="logout" action="Logout">
			<s:param name="logout">true</s:param>
		</s:url>
		<span style="margin-top:1%; margin-bottom:1%; margin-left:3%; white-space:nowrap; float:left;">
			<a href="/NewsGroup/" style="color:#ffffff; font-size:20px; font-family:Lucida Console; text-decoration:none;">Sharepoint</a>
		</span>
		
    	<span style="margin-top:1%; margin-bottom:1%; margin-right:5%; white-space:nowrap; float:right;">
    	
			<span onmouseover="bgchange(this,'#415E9B');" onmouseout="bgchange(this,'transparent');">
				<a ng-href="<s:property value="user.profileurl"/>" onmouseover="this.style.color='#ffde00'" onmouseout="this.style.color='#ffffff'" style="color:#ffffff; margin-top:10px; margin-left:10px; margin-bottom:10px; font-size:14px; font-family:Lucida Console; text-decoration:none;"><s:property value="user.fname"/></a>
			</span>
			
			<span onmouseover="bgchange(this,'#415E9B');" onmouseout="bgchange(this,'transparent');">
				<a ng-href="/NewsGroup/home" ng-click="loadFeed();" onmouseover="this.style.color='#ffde00'" onmouseout="this.style.color='#ffffff'" style="color:#ffffff; margin-top:10px; margin-left:10px; margin-bottom:10px; font-size:14px; font-family:Lucida Console; text-decoration:none;">Home</a>
			</span>
			
			<span onmouseover="bgchange(this,'#415E9B');" onmouseout="bgchange(this,'transparent');">
				<a href="<s:property value="%{logout}"/>" onmouseover="this.style.color='#ffde00'" onmouseout="this.style.color='#ffffff'" style="color:#ffffff; margin-top:10px; margin-left:10px; margin-right:20px; font-size:14px; margin-bottom:10px; font-family:Lucida Console; text-decoration:none;">Logout</a>	  
			</span>
			
		</span>
</div>