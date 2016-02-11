function comment(aid, com)
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
						com = "." + com + "";	
						$(com).empty().append(result);
	 		  		} ,
	 		  error :
	 			   function (req, status, error)
	 		  		{
	 			  		console.log("Error in displaying comments"+ req + status + error);
	 		  		}
		});
		
	}

function insert(value,aid,com,thisid)
{
	console.log("Value : "+value+ " Aid : "+aid+ " Comsec : "+ com + " thisid : "+thisid);
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
				    		comment(aid,com);
				    		disnoofcomments(aid, thisid);
				    		var divclass = document.getElementById(com);
				    		if(divclass.style.display=="none")
				    			divclass.style.display="block";
				    	}
				    
				  } ,
		error : 
			   	  function(req,status,error)
			   	  {
					console.log("Error in inserting comment "+req+" "+status+" "+error);
			   	  }
	});
}

function disnoofcomments(aid,thisid)
{
	$.ajax({
		type : "POST" ,
		url : "/NewsGroup/GetCommentsCount.action" ,
		data : {"aid" : aid} ,
		success : function (result)
					{
					  var ahrefcomment = document.getElementById(thisid);
					  ahrefcomment.innerText = result;
					} ,
	    error : function (error,req,status)
	    			{
	    				  console.log("Error "+error+ " "+" "+req+ " "+status);
	    			}
		});
}