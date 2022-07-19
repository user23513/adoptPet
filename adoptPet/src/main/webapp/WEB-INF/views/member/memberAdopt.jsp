<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"/></script>
</head>
<body>
<div align="center">
나의입양신청리스트<br>
<c:choose>
	<c:when test="${not empty list }">
	<c:forEach var="b" items="${list }">
		${b.memberId }<br>
		${b.petAddNo }<br>
		${b.adoptSubscriptionOk }<br>
		${b.adoptSubscriptionReason }<br>
	</c:forEach>
	${pvo.petAddNo }
	${pvo.petAddName }
	${pvo.petAddHealth }
	${pvo.petAddGender }
	${pvo.petAddWeight }
	</c:when>
	<c:otherwise>
	
	</c:otherwise>
</c:choose>
</div>
</body>
</html>