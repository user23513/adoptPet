<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><tiles:getAsString name="title"/></title>
<style type="text/css">
	#test{
	width: 100%;
	height: 75px;
	background-color: #f4623a;
	}
</style>
</head>
<body>
	<tiles:insertAttribute name="header"/>
	<div id="test"></div>
	<div id="bd">
	<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="footer"/>
</body>
</html>