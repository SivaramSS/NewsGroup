function showwholikes(data,obj)
{
	console.log("In showwholikes function id "+obj);
	$.ajax({
		type:"POST",
		url : "/NewsGroup/Wholikes.action",
		data : {"data":data},
		success : function(result)
					{	
						var element = document.getElementById(obj);
						element.style.visibility="visible";
						element.style.display="block";
						obj = "." + obj;
						console.log("Result"+result);
						$(obj).empty().html(result);
					},
		error : function(req,status,err)
					{
						console.log("Error in showing who likes");
					}
	});
}
function hide(obj)
{
	var element = document.getElementById(obj);
	element.style.visibility="hidden";
	element.style.display = "none";
}