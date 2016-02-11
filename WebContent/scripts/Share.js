
	function shareArticle()
	{
		var data = document.getElementById("shareurl").value;
		console.log(data);
		
		$.ajax({
			type : "POST",
			url : "/NewsGroup/Share.action" ,
			data : { "shareurl" : data} ,
			success : function (result)
						{
							$(".container").prepend(result);
						} ,
			error : function (req,status,err)
						{
							console.log("Error in ShareArticle function");
						}
		});
	}