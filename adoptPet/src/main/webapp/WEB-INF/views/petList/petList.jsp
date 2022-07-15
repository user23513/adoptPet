<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입양동물 소개 게시판</title>
</head>
<body>
	입양동물 소개 게시판
	<c:if test="${empty petList}">등록된 게시물이 없습니다.</c:if>
	<c:if test="${not empty petList}">
			<c:forEach var="list" items="${petList}">
	           	<ul>
					<li>${list.petListNo}</li>
					<li>${list.boardId}</li>
					<li>${list.petListTitle}</li>
					<li>${list.petListState}</li>
					<li>${list.petListType}</li>
					<li>${list.filesPath1}</li>
				</ul>
			</c:forEach>
	</c:if>

	<div>
		<% 	int pageCount = (int)request.getAttribute("pageCount");
			int pageBlock = (int)request.getAttribute("pageBlock");
			int startPage = (int)request.getAttribute("startPage");
			int endPage = (int)request.getAttribute("endPage");
			
			for (int i = startPage; i<=endPage; i++) { %>
				<a href="petList.do?pageNum=<%=i%>"><%=i %></a>
			<% } %>
	</div>	
	<button type="button" onclick="location.href='petListForm.do'">글쓰기</button>
	
	
</body>
</html>