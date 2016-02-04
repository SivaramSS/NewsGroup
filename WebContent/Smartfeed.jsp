<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NewsGroup</title>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">

<style type="text/css">

a:hover {
  cursor:pointer; 
  cursor:hand;
}

#loading {
   width: 100%;
   height: 10%;
   top: 100px;
   left : auto;
   right : auto;
   position: fixed;
   display: block;
   background-color: #d3d3d3;
   opacity: 1;
   z-index: 99;
   text-align: center;
}

#loading-image {
  top: 20px;
  display : block;
  z-index:100;
  left: 200;
  right : auto;
}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js">
</script>
<script>
	
  $(document).ready( function() {
	  loadFeed();
  });
	
    function loadFeed()
    {	
    	 $(".container").empty();
    	 $(".caption").empty().text("Populating your feed...");
    	 $.ajax( {
 			type : "POST",
 			url : "/NewsGroup/Feed.action",
 			data : { "dummy" : "dummy"} ,
 			beforeSend : function ()
 							{ 
 								console.log("In before send function");
 								var loading = document.getElementById("loading");
 								loading.style.visibility = "visible";
 							} ,
 			success : function (result)
 						{
 								console.log("Action class executed");
 								var loading = document.getElementById("loading");
 								loading.style.visibility = "hidden";
 								$(".container").html(result);
 						} ,
 						
 			error : function (req, status, err)
 						{
 								console.log("Error in Document ready function Smartfeed.jsp ");
 						}
 		} ).done(function(data){
 				
 				var loading = document.getElementById("loading");
 				loading.style.visibility = "hidden";
 				console.log("In done function");	
 	  });	
    }
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
		console.log("Aid "+aid+ " Com "+com);
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
			  			input = input + ",\'"+obj+"\',\'"+aid+ "\',\'" +com+ "\');\" onfocus='if(this.value == 'Enter your comment') {this.value=''}' onblur='if(this.value == '') {this.value='Enter your comment'}'/></form>";	
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
						divclass.style.display = "block";
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
	
	function loadProfile(data)
	{
		console.log("Profile id" + data);
		$(".container").empty();
		$(".caption").empty().text("Fetching profile...");
		$.ajax({
			type : "POST" ,
			url : "/NewsGroup/Profile.action" ,
			data : {"id" : data} ,
			beforeSend : function()
							{
								var loading = document.getElementById("loading");
								loading.style.visibility = "visible";
							} ,
							
			success : function (result)
						{
							var loading = document.getElementById("loading");
							loading.style.visibility = "hidden";
							$(".container").empty().html(result);
						} ,
						
			error : function(req, status, err)
						{
							console.log("Error in Loading Profile"+req+ " " + status +" "+err);
						}
		}).done(function(result){
			var loading = document.getElementById("loading");
			loading.style.visibility = "hidden";
		});	
	}
</script>
</head>
<body style="background: #d3d3d3;margin:0px; border:0px; padding:0px;">
	
	<% 
	  if(session.getAttribute("user")!=null)
	  {
	%>
	
	<s:url var="logout" action="Logout">
		<s:param name="logout">true</s:param>
	</s:url>
     	
	<div id="Header" class="Header" style="background: #003366; padding:2px">
	  <p align="left"  style="display:inline-block; color:#ffffff; margin-left:70px; margin-top:10px; margin-bottom:10px; font-size:20px; font-family:Lucida Console;">NewsGroup</p>
	  <div style="display:inline-block; float:right;">
			<a href="#" onclick="loadFeed();" style="display:inline-block; color:#ffffff; margin-top:10px; margin-bottom:10px; font-size:12px; font-family:Lucida Console; text-decoration:none;">Feed </a>
			<a href="#" onclick="loadProfile('own');" style="display:inline-block; color:#ffffff; margin-top:10px; margin-left:10px; font-size:12px; font-family:Lucida Console; text-decoration:none;">Profile</a>
			<s:a href="%{logout}" style="display:inline-block; color:#ffffff; margin-top:10px; margin-left:10px; margin-right:20px; font-size:12px; font-family:Lucida Console; text-decoration:none;">Logout</s:a>	  
	  </div>
	</div>
	
	<div id="loading" class="loading" align="center">
		<p id="caption" class="caption"></p>
		<br/>
		<center><img id="loading-image" src="loading.gif" alt="Loading..." /></center>
	</div>
	
	<div id="container" class="container" style="display:block; ">
	</div>	
	
	<%
	  }
	  else
	  {
	%>
	   <s:action name="Login" namespace="/" executeResult="true"></s:action>
	<%
	  }
	%>
</body>
</html>