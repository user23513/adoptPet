<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입양동물 소개 게시판</title>
</head>
<body>
	
	<table border="1">
		<thead>
			<tr>
				<th>게시물번호</th>
				<th>이미지</th>
				<th>제목</th>
				<th>입양여부</th>
				<th>동물유형</th>
				<th>첫번째파일 경로</th>
				<th>좋아요</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${not empty petList}">
				<c:forEach var="list" items="${petList}">
					<tr>
						<td>${list.petListNo}</td>
						<c:if test="${not empty list.filesPath1}">
							<td><img width="50" height="50" src="fileup/${list.filesPath1}"></td>
						</c:if>
						<c:if test="${empty list.filesPath1}">
							<td></td>
						</c:if>
						<td><a href="petListView.do?petListNo=${list.petListNo}"> ${list.petListTitle}</a></td>
						<td>${list.petListState}</td>
						<td>${list.petListType}</td>
						<td>${list.filesPath1}</td>
						<td><div>
						<button id="heartBtn" type="button" onclick="heartCheckFnc(${list.petListNo},${list.heartNum })">
							<c:choose>
								<c:when test="${list.heartCheck == 1}">
									<img id="img${list.petListNo}" width="15" height="15" src="images/redHeart.png">
								</c:when>
								<c:otherwise>
									<img id="img${list.petListNo}" width="15" height="15" src="images/whiteHeart.png">
								</c:otherwise>
							</c:choose>
						</button>
							<span id="heartNum${list.petListNo}">${list.heartNum }</span> 
						</div></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<c:if test="${empty petList}">등록된 게시물이 없습니다.</c:if>
	<div>
		<% 	int pageCount = (int)request.getAttribute("pageCount");
			int pageBlock = (int)request.getAttribute("pageBlock");
			int startPage = (int)request.getAttribute("startPage"); //게시글이 하나도 없을때 0이다.
			int endPage = (int)request.getAttribute("endPage");
			
			for (int i = startPage; i<=endPage; i++) { %>
				<a href="petList.do?pageNum=<%=i%>"><%=i %></a>
			<% } %>
	</div>	
	<button type="button" onclick="location.href='petListForm.do'">글쓰기</button>
	
	<script>
		//하트버튼 눌렀을때
		function heartCheckFnc(petListNo,heartNum) {
			// get: url, post: 추가정보지정.
			fetch('heartCheck.do', {
				method: 'post',
				headers: {
					'Content-type': 'application/x-www-form-urlencoded'
				},  
				body: "memberId=lee"+"&petListNo="+petListNo
			})
				.then(function(result){
					return result.json();
				})
				.then(function(result){
					if(result.heartColor == 'Red'){
						document.getElementById('heartNum'+result.petListNo).innerHTML=result.heartCount;
						document.getElementById('img'+result.petListNo).src = 'images/redHeart.png';
					} else {
						document.getElementById('heartNum'+result.petListNo).innerHTML=result.heartCount;
						document.getElementById('img'+result.petListNo).src = 'images/whiteHeart.png';
					}
				})
				.catch(function(err){
					console.error(err);
				})
				}
	</script>
</body>
</html>