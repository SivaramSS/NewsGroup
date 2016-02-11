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
			var sharebox = document.getElementById("sharebox");
			sharebox.style.visibility = "hidden";
		});	
	}