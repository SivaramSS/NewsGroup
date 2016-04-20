<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="sharepoint">
<head>
<title><tiles:insertAttribute name="title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=2.0, user-scalable=yes" />
<link media="Screen" href="styles.css" type="text/css" rel="stylesheet" />
<link media="handheld, only screen and (max-width: 720px), only screen and (max-device-width: 720px)" href="mobile.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="/CSS/ui-bootstrap-custom-1.2.5-csp.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>

<!-- Angular Libs -->
  <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.1/angular-aria.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.1/angular.min.js"></script>
  <!-- Bootstrap tooltip -->
  <script type="text/javascript" src="/NewsGroup/scripts/tooltip.js"></script>
  
<link rel="stylesheet" href="/NewsGroup/CSS/styles.css" />
<script type="text/javascript" src="/NewsGroup/scripts/stylechange.js" ></script>
<script type="text/javascript" src="/NewsGroup/scripts/app.js"></script>

<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip(); 
});
</script>
</head>
<body style="background: #d3d3d3;margin:0px; border:0px; padding:0px;">
	<tiles:insertAttribute name="header"/>
	<tiles:insertAttribute name="body" />
</body>
</html>