<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
회원리스트
<c:if test="${empty memberList}">회원이 없습니다.</c:if>	
<c:if test="${not empty memberList }">
	<table border="1">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>email</th>
				<th>성별</th>
				<th>직업</th>
				<th>전화번호</th>
				<th>권한</th>
				<th>수정</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="list" items="${memberList }">
			<tr>
				<td>${list.memberId }</td>
				<td>${list.memberName }</td>
				<td>${list.memberEmail }</td>
				<td>${list.memberGender }</td>
				<td>${list.memberJob }</td>
				<td>${list.memberTel }</td>
				<td>${list.memberAuthor }</td>
				<td><input type="button" value="수정"></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</c:if>
	<div>
		<% 	int pageCount = (int)request.getAttribute("pageCount");
			int pageBlock = (int)request.getAttribute("pageBlock");
			int startPage = (int)request.getAttribute("startPage");
			int endPage = (int)request.getAttribute("endPage");
			
			for (int i = startPage; i<=endPage; i++) { %>
				<a href="memberList.do?pageNum=<%=i%>"><%=i %></a>
			<% } %>
	</div>
</body>
</html>