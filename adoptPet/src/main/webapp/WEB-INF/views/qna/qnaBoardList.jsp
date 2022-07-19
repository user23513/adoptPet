<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의게시판 목록</title>
<script type="js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
<style>
	option,table{
		border: 1px solid black;
		border-radius: 10px;
	}
</style>
</head>

<body>
<div align="center">
<div>문의 게시판</div>
	<div>
		<form id = "frm">
		<select id = "key" name = "cars">
			<option value="board_tilte">제목</option>
			<option value="board_content">내용</option>
			<option value="board_writer">작성자</option>
		</select>
			<input type="text" id="val" name="val">
			<input type="button" value="검색" onclick="qnaBoardSearch()">
		</form>
	</div>
	<div>
	<table border="1">
		<thead>
			<tr>
				<th width="50">no.</th>
				<th width="50">유형</th>
				<th width="100">작성자</th>
				<th width="250">제목</th>
				<th width="70">작성일</th>
				<th width="50">조회수</th>
			</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${not empty list }">
				<c:forEach items="${list }" var="b">
					<tr>
						<td>${b.boardNo }</td>
						<td>${b.boardId }</td>
						<td>${b.boardWriter }</td>
						<td><a href="qnaBoardSelect.do">${b.boardTitle }</a></td>
						<td>${b.boardContent }</td>
						<td>${b.boardDate }</td>
						<td>${b.boardHit }</td>
					</tr>
				</c:forEach>
			</c:when>
			
			<c:otherwise>
				<tr>
					<td colspan="6" algin="center">게시글이 존재하지 않습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>
	</div>
	<br>
		
</div>
</body>
</html>