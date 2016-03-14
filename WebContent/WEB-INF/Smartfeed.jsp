<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
<script>
  $(document).ready( function() {
	  loadFeed();
  });
</script>
</head>
	<center>
		<div id="sharebox" class="sharebox" style="margin-top:20px; visibility : hidden;">
     		<input type="text" id="shareurl" name="shareurl" value="copy and paste url of article here" style="width:50%; height:25px; text-align:center; " onfocus="if(this.value == 'copy and paste url of article here') {this.value=''}" onblur="if(this.value == '') {this.value='copy and paste url of article here'}" />
     		<input type="button" value="Share" style="height: 25px;" onclick="shareArticle();"/>  	
		</div>
	</center>
	
	<div id="loading" class="loading" align="center">
		<p id="caption" class="caption"></p>
		<br/>
		<center><img id="loading-image" src="loading.gif" alt="Loading..." /></center>
	</div>
	
	<div id="container" class="container" style="display:block; ">
	</div>	
	
