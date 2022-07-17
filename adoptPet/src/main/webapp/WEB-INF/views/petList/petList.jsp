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
	입양동물 소개 게시판
	<c:if test="${empty petList}">등록된 게시물이 없습니다.</c:if>
	<c:if test="${not empty petList}">
			<c:forEach var="list" items="${petList}">
	           	<ul>
					<li>${list.petListNo}</li>
					<li>${list.boardId}</li>
					<li><a href="petListUpdateForm.do?petListNo="+${list.petListNo}> ${list.petListTitle}</a></li>
					<li>${list.petListState}</li>
					<li>${list.petListType}</li>
					<li>${list.filesPath1}</li>
					<img src = "${list.filesPath1}"> <!-- 이미지가 엑박뜸 학원에서 다시 확인! -->
				</ul>
				<div>
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
				</div>
			</c:forEach>
	</c:if>

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
		function heartCheckFnc(petListNo,heartNum) {
			console.log(petListNo)
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
					console.log(result);
					if(result.heartColor == 'Red'){
						document.getElementById('heartNum'+result.petListNo).innerHTML=result.heartCount;
						console.log(document.getElementById('img'+result.petListNo));
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