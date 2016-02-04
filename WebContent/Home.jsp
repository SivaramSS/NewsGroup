<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NewsGroup</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js">
</script>

<script>
	function disnooflikes(aid,likesec)
	{
	   console.log("In disnooflikes "+aid);
	   $.ajax({
		   type : "POST",
		   url : "/NewsGroup/GetLikesCount.action" ,
		   data : {"aid" : aid} ,
		   success : function(result)
		   				{ 
			   				if(result!="0")
			   					{ 
			   						console.log(result);
				   					var likediv = document.getElementById(likesec);
				   					likediv.style.display = "block";
				   					likesec = "."+likesec;
				   					$(likesec).empty().append(result+" like this");
			   					}
			   				else
			   					{
			   						var likediv = document.getElementById(likesec);
			   						likediv.style.display = "none";
			   					};
		   				} ,
		   				
		   error : function(error,req,status)
		   				{   
			   				console.log("Error in disnooflikes "+error+" "+req+" "+status);
		   				}
	   });
	}
	
	function disnoofcomments(aid, obj)
	{
		$.ajax({
			type : "POST" ,
			url : "/NewsGroup/GetCommentsCount.action" ,
			data : {"aid" : aid} ,
			success : function (result)
						{
						  var ahrefcomment = document.getElementById(obj);
						  if(result=="0")
							  ahrefcomment.innerText = "comment";
						  else if(result=="1")
							  ahrefcomment.innerText = "1 comment";
						  else
							  ahrefcomment.innerText = result + " comments";
						} ,
		    error : function (error,req,status)
		    			{
		    				  console.log("Error "+error+ " "+" "+req+ " "+status);
		    			}
			});
	}
	
	function change(obj, aid, userid, likesec)
	{
		if(obj.innerHTML=="Like")
			obj.innerHTML="Unlike";
		else
			obj.innerHTML="Like";
		
		$.ajax({
			type : "POST" ,
			url : "/NewsGroup/LikeUnlike.action" ,
			data : { "aid" : aid} ,
			success : function (res)
						{
				 			console.log("In change function " + res);
				 			disnooflikes(aid,likesec);
						} ,
						
			error : function(error,req,status)
						{
							alert("Error "+error+ " "+req+ " "+ status);
						}
		});
	}
	
	function comment(obj, aid, com)
	{
		var divclass = document.getElementById(com);
		if(divclass.style.display=="none")
			divclass.style.display="block";
		else
			divclass.style.display="none";
		
		$.ajax ({

			  type: "POST" ,
	 		  url: "/NewsGroup/Comments.action" ,
	 		  data : { "aid" : aid } ,
	 		  datatype : "json" ,
	 		  success : function (result)
	 		  		{
	 			  		console.log(aid+" "+com);
	 			 		var input = "<form><input type=\"text\" name=\"usercomment\" value=\"Enter your comment\"/> <input type=\"button\" value=\"Comment\" onclick=\"insert(this.form.usercomment.value";
			  			input = input + ",\'"+obj+"\',\'"+aid+ "\',\'" +com+ "\');\" /></form>";	
	 			  		com = "\."+com+"";
	 			  		$(com).empty();
	 			  		
	 			  		$.each(result, function(){
	 			  			console.log(this.cdate);
	 			  			 var temp = "<div> <a href=\"Profile?id="+this.userid+" \" >"+this.fname+"</a> "+this.content+" on :"+this.cdate+"</div> <br/>";
	 			  			 $(com).append(temp);
	 			  		});
	 			  		
	 			  		$(com).append(input);
	 			  		return false;
	 		  		} ,
	 		  error :
	 			   function (req, status, error)
	 		  		{
	 			  		console.log("error "+ req + status + error);
	 		  		}
		});
		
	}
	
	function insert(value,obj,aid,com)
	{
		$.ajax({
			type : "POST" ,
			url : "/NewsGroup/InsertComment.action" ,
			data : { "aid": aid, "comment" : value} ,
			datatype : "text" ,
			success : function (result)
					  {
						console.log(result);
						var divclass = document.getElementById(com);
						divclass.style.display = "none";
					    if(result=="done")
					    	{
					    		comment(obj,aid,com);
					    		disnoofcomments(aid, obj);
					    	}
					    
					  } ,
			error : 
				   	  function(req,status,error)
				   	  {
						console.log("Error "+req+" "+status+" "+error);
				   	  }
		});
	}
	
</script>

</head>

<body style="background : #d3d3d3;">   

    <h2>NewsGroup</h2> 
    <div align="right">
        <s:url action="Feed" var="feed"/> 
    	<s:url action="Profile" var="ownprof">
     		<s:param name="id" value="\"own\"" />
   		</s:url>
   		
   		<s:url action="Logout" var="signout">
   		 	<s:param name="logout" value="true"/>
   		</s:url>
   		 
    	<a href="<s:property value="%{#feed}"/>">Feed</a> |
    	
    	<a href="<s:property value="%{#ownprof}"/>"><s:property value="fname"/></a>|
    	
    	<a href="<s:property value="%{#signout}"/>">Log out</a>
    </div>
    <hr/>
    <div align="center">
    	
    	<form method="post" action="/NewsGroup/Share.action">
     	<input type="text" id="shareurl" name="shareUrl" value="copy and paste url of article here" style="width:300px; height:20px;" onfocus="if(this.value == 'copy and paste url of article here') {this.value=''}" onblur="if(this.value == '') {this.value='copy and paste url of article here'}" />
     	<input type="submit" value="Share" style="height: 15px; "/>  	
     	</form>
    	<div align="center" class="Loading" style="display : block;margin-top:30px;">
    	</div>
    	<div style="margin:5px;">
    		
    		<s:iterator value="articlelist" var="article" status="incr"> 
    		  <div style="background: #ffffff; margin-left:5px; margin-top:30px; border-radius:10px;">
    		    <div align="left" style="padding:20px;">
    		    	<s:url action="Profile" var="url">
    	    			 <s:param name="id" value="%{#article.userid}" />
    				</s:url>
    			
    				<a href="<s:property value="%{#url}"/>" >
    	 				<s:property value="#article.fname" /> 
    				</a>
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
    	</div>
    </div>
</body>
</html>