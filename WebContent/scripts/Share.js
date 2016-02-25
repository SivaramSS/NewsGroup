
	function shareArticle()
	{
		var data = document.getElementById("shareurl").value;
		console.log(data);
		
		$.ajax({
			type : "POST",
			url : "/NewsGroup/Share.action" ,
			data : { "shareurl" : data} ,
			
			beforeSend : function()
						{	console.log("In before send of share");
							var sharebox = document.getElementById("shareurl");
							sharebox.value = "";
							var loading = document.getElementById("loading");
							loading.style.visibility = "visible";
						} ,
			success : function (result)
						{
							$(".container").prepend(result);
						} ,
			error : function (req,status,err)
						{
							console.log("Error in ShareArticle function");
						}
		}).done(function(data){
			
			var loading = document.getElementById("loading");
			loading.style.visibility="hidden";
			
		});
	}