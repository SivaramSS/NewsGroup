 function loadFeedsd()
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
 								var sharebox = document.getElementById("sharebox");
 								sharebox.style.display = "block";
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
 				var sharebox = document.getElementById("sharebox");
 				sharebox.style.visibility = "visible";
 	  });	
    }
 
 function viewFullPost(data)
	{
		$.ajax({
			type : "POST" ,
			url : "/NewsGroup/ViewPost.action" ,
			data : {"data" : data} ,
			success : function(result)
						{
							$(".container").empty().html(result);
							var sharebox = document.getElementById("sharebox");
							sharebox.style.display = "none";
						} ,
			error : function(req,status,err)
						{
							console.log("Error in ViewFullPost : "+req+status+err);
						}
		});
	}