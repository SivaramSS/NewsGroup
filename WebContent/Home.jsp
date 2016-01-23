<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NewsGroup</title>

<script type="text/javascript">
	function change(obj, aid, userid)
	{
		var xhttp;
		if(obj.innerHTML=="Like")
			obj.innerHTML="Unlike";
		else
			obj.innerHTML="Like";
		
		if(window.XMLHttpRequest)
			{
			 xhttp = new XMLHttpRequest();
			}
		else
			{
				xhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		
		xhttp.onreadystatechange = function () 
		{
			if(xhttp.readyState == 4 && xhttp.status==200)
				{
				}
		};
		xhttp.open("POST","LikeUnlike.action",true);
		xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var data = "aid="+aid+"&userid="+userid;
		xhttp.send(data);
	}
	
	function comment(obj, aid)
	{
		alert(aid);
		$.ajax ({
	 		  
	 		  type: "POST" ,
	 		  url: "/NewsGroup/Comments.action" ,
	 		  data : { "aid" : aid } ,
	 		  success :
	 			   function (result)
	 		  		{
	 			  		console.log(result);
	 		  		} ,
	 		  error :
	 			   function (req, status, error)
	 		  		{
	 			  		
	 		  		}
		});
	}
</script>

</head>

<body>   

    <h2>NewsGroup</h2> 
    <div align="right">
        <s:url action="Feed" var="feed"/> 
    	<s:url action="Profile" var="ownprof" method="POST">
     		<s:param name="id" value="\"own\"" />
   		</s:url>
   		
   		<s:url action="Logout" var="signout">
   		 	<s:param name="logout" value="true"/>
   		</s:url>
   		 
    	<a href="<s:property value="%{#feed}"/>">Home</a> |
    	
    	<a href="<s:property value="%{#ownprof}"/>">Profile</a>|
    	
    	<a href="<s:property value="%{#signout}"/>">Log out</a>
    </div>
    <hr/>
    
    <div align="center">
    	
    	<form method="post" action="/NewsGroup/Share.action">
     	<input type="text" id="shareurl" name="shareUrl" value="copy and paste url of article here" style="width:300px;" />
     	<input type="submit" value="Share"/>  	
     	</form>
    
    	<div style="margin:5px;">
    		
    		<s:iterator value="articlelist" var="article" status="incr"> 
    		
    		    <div align="left">
    		    
    		    	<s:url action="Profile" var="url">
    	    			 <s:param name="id" value="%{#article.userid}" />
    				</s:url>
    			
    				<a href="<s:property value="%{#url}"/>" >
    	 				<s:property value="#article.fname" /> 
    				</a>
    				
    			</div>
    			
    			<br/>
    			<s:property value="#article.url" />
    			<br/>
    			
    			<div align="left" style="margin:10px;">
    				<s:set name="liked" value="%{#article.liked}"/>
    				<s:set name="userid" value="%{#article.userid}"/>
    				<s:set name="aid" value="%{#article.aid}" />
    				
    				<s:if test="%{#liked==1}">
    					<s:a id="likebtn" href="#" onclick="change(this,%{aid}, %{userid});" >Unlike</s:a>
    				</s:if> <s:else>
    					<s:a id="likebtn" href="#" onclick="change(this,%{aid}, %{userid});">Like</s:a>
    				</s:else>
    				
    				<s:a id="commentbtn" href="#" onclick="comment(this, %{#aid});" >comment</s:a>
    			</div>
    			
    		</s:iterator>
    	</div>
    </div>
    
</body>
</html>