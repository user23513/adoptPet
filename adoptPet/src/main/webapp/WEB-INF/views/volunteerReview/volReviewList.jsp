<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>봉사활동 후기 리스트</title>
</head>
<body>
	<div align="center">
		<div>봉사활동후기 목록</div>
		<div>
			<form id="vFrm">
				<select id="key" name="vol">
					<option value="board_title">제목</option>
					<option value="board_subject">내용</option>
					<option value="board_writer">작성자</option>
				</select>&nbsp; <input type="text" id="val" name="val">&nbsp;&nbsp; <input
					type="button" value="검색" onclick="volReviewSelectOne()">
			</form>
		</div>
		<div>
			<table border="1">
				<thead>
					<tr>
						<th width="50">순번</th>
						<th width="50">유형</th>
						<th width="100">작성자</th>
						<th width="250">제목</th>
						<th width="70">작성일자</th>
						<th width="50">조회수</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${not empty volReviewList }">
						<c:forEach items="${volReviewList }" var="b">
							<tr>
								<td>${b.boardNo }</td>
								<td>${b.boardId }</td>
								<td>${b.boardWriter }</td>
								<td><a href="volReviewSelectOne.do"></a> ${b.boardTitle }</td>
								<td>${b.boardDate }</td>
								<%-- 	<td>${b.boardAttech }</td> --%>
								<td>${b.boardHit }</td>
							</tr>
						</c:forEach>
					</c:when>                           
					<c:otherwise>
				<tr>
					<td colspan="6" align="center">게시글이 존재하지 않습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
			</tbody>
		</table>
</div>
<br>
<div>
	<c:if test="${author == 'ADMIN' }"><!-- 접근권한  -->
	<button type="button" onclick="location.href='noticeForm.do'">글등록</button>
	</c:if>
</div>
</div>
	<div>
		<% 	int pageCount = (int)request.getAttribute("pageCount");
			int pageBlock = (int)request.getAttribute("pageBlock");
			int startPage = (int)request.getAttribute("startPage"); //게시글이 하나도 없을때 0이다.
			int endPage = (int)request.getAttribute("endPage");
			
			for (int i = startPage; i<=endPage; i++) { %>
				<a href="volReviewList.do?pageNum=<%=i%>"><%=i %></a>
			<% } %>
	</div>	

		
</body>
</html>