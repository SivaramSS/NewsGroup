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
						alert("Error in change function"+error+ " "+req+ " "+ status);
					}
	});
}