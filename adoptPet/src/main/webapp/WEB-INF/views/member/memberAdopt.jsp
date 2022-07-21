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
   <section class="page-section" id="contact">
            <div class="container px-4 px-lg-5">
  			<div class="row gx-4 gx-lg-5 justify-content-center">
         		<div class="col-lg-8 col-xl-6 text-center">
              		 <h2 class="mt-0">나의 입양 신청 현황!</h2>
                		<hr class="divider" />
         		</div>
 			</div>
 			</div>
 	</section>
<table>            
	<thead>
		<tr>
			<th>회원아이디</th>  
			<th>동물번호</th>
			<th>입양승인 여부</th>
			<th>입양 이유</th>
		</tr>
	</thead>
	
	<tbody>
		<c:choose>
			<c:when test="${not empty list }">
				<c:forEach var="b" items="${list }">
				<tr>
					<td>${b.memberId }</td>
					<td>${b.petAddNo }</td>
					<td>${b.adoptSubscriptionOk }</td>
					<td>${b.adoptSubscriptionReason }</td> 
				</tr>
	</c:forEach>
	</c:when>
	<c:otherwise>
	
	</c:otherwise>
</c:choose>
</tbody>
</table>
</body>
</html>