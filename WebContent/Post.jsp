<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title><s:property value="post.fname"/>'s Post <s:property value="post.uldatetime"/></title>
</head>

<body style="background: #d3d3d3">
	<div align="center" style="background: #ffffff; margin-top:40dp; margin-left:200px; margin-right:200px;border-radius:10px;" >
	 <s:url action="Profile" var="profile">
	 	<s:param name="id" value="post.userid"/>
	 </s:url>
	 
	 <div align="left" style="padding:20px;margin-top:20px;">
	 	<s:a href="%{#profile}"><s:property value="post.fname"/> <s:property value="post.lname"/></s:a> 
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
    <s:set var="comsec">comsection</s:set>
    <s:set var="likesec">likesection</s:set>
    <s:set var="comcount">comcount</s:set>	
    
    <s:url action="ViewPost" var="ViewPost">
    	<s:param name="id" value="%{post.aid}" />
    </s:url>
    				
    <div align="right" style="padding-right:15px;">
    	Shared on <s:a href="%{#ViewPost}" target="_blank"><s:property value="post.uldatetime"/></s:a>
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
    	<s:a id="likebtn" href="#" onclick="change(this, '%{#aid}', '%{#userid}', '%{#likesec}');" >Unlike</s:a>
    </s:if> <s:else>
    	<s:a id="likebtn" href="#" onclick="change(this, '%{#aid}', '%{#userid}', '%{#likesec}');">Like</s:a>
    </s:else>
    				
    <s:if test="%{#countcom==0}">
    	<s:a id="%{#comcount}" href="#" onclick=" comment( '%{#comcount}','%{#aid}', '%{#comsec}'); " >comment</s:a>
    </s:if> <s:elseif test="%{#countcom==1}">
    	<s:a id="%{#comcount}" href="#" onclick=" comment( '%{#comcount}','%{#aid}', '%{#comsec}'); "><s:property value="%{post.count_comments}" /> comment</s:a> 
    </s:elseif> <s:else>
    	<s:a id="%{#comcount}" href="#" onclick=" comment( '%{#comcount}','%{#aid}', '%{#comsec}'); "><s:property value="%{post.count_comments}"/> comments</s:a> 
    </s:else>
    				
    <div class="<s:property value="%{#comsec}"/>" id="<s:property value="%{#comsec}" />" align="center" style="display:none;">
    </div>
    </div>
  </div>
</body>
</html>