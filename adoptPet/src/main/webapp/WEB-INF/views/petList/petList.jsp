<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입양동물 소개 게시판</title>
<script src="js/jquery-3.6.0.min.js"></script> <!-- 제이쿼리 라이브러리 쓰겠다. -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/adoptpet.css" rel="stylesheet" />   
<style>
	#search {
		display: inline-block;
		float: right;
	}
	
	#heartBtn {
		padding: 0;
		border: none;
		background: none;
	}
	
	.avatar {
	  vertical-align: middle;
	  width: 80px;
	  height: 80px;
	  border-radius: 50%;
	}
	
</style>

</head>
<body>

<div>
	<div id="list">
	
		<section class="notice">
  		<div class="page-title">
        	<div class="container">
            	<h3>입양동물 소개</h3>
        	</div>
    	</div>
    	<c:if test="${author eq 'ADMIN' }">
		<button type="button" class="btn btn-primary btn-xl"  style="float: left; margin-bottom: 20px;" onclick="location.href='petAddList.do'">입양등록</button>
	</c:if>
    	<div id="search" align="right">
			<form id="frm" action="petListSearch.do" method="post">
				<select id="key" name="key" >
					<option value="pet_list_title">제목</option>
					<option value="pet_list_content">내용</option>
				</select>&nbsp;
				<input type="text" id="val" name="val" placeholder="검색어를 입력해주세요.">&nbsp;&nbsp;
				<input type="submit" value="검색"  >
			</form>
		</div>
		<table>
			<thead>
				  <tr>
				  	<th>No</th>
				 	<th>이미지</th>
					<th>제목</th>
					<th>입양여부</th>
					<th>동물유형</th>
					<th>좋아요</th>
				  </tr>
			 </thead>
			 <tbody>
			 	<c:choose>
				<c:when test="${not empty petList}">
						<c:forEach var="list" items="${petList}" varStatus="i">
							<tr>
								<td>${cnt-(pageNum-1)*pageSize - i.index }</td>
								<c:if test="${not empty list.filesPath1}">
									<td><img class="avatar" alt="Avatar" src="fileup/${list.filesPath1}"></td>
								</c:if>
								<c:if test="${empty list.filesPath1}">
									<td></td>
								</c:if>
								<td><a href="petListView.do?petListNo=${list.petListNo}&petAddNo=${list.petAddNo}"> ${list.petListTitle}</a></td>
								<td>${list.petListState}</td>
								<td>${list.petListType}</td>
								<td><div>
								<button id="heartBtn" type="button" class="btn btn-primary btn-xl" onclick="heartCheckFnc(${list.petListNo},${list.heartNum })">
									<c:choose>
										<c:when test="${list.heartCheck == 1}">
											<img id="img${list.petListNo}" width="20" height="20" src="images/redHeart.png">
										</c:when>
										<c:otherwise>
											<img id="img${list.petListNo}" width="20" height="20" src="images/whiteHeart.png">
										</c:otherwise>
									</c:choose>
								</button>
									<span id="heartNum${list.petListNo}">${list.heartNum }</span> 
								</div></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="7" align="center">게시글이 존재하지 않습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			 </tbody>
		</table>
		
	</div>
	<div align="center">
			<% 	int pageCount = (int)request.getAttribute("pageCount");
				int pageBlock = (int)request.getAttribute("pageBlock");
				int startPage = (int)request.getAttribute("startPage"); //게시글이 하나도 없을때 0이다.
				int endPage = (int)request.getAttribute("endPage");
				
				for (int i = startPage; i<=endPage; i++) { %>
					<a href="petList.do?pageNum=<%=i%>"><%=i %></a>
				<% } %>
		</div>	

	
	<script>
		//하트버튼 눌렀을때
		function heartCheckFnc(petListNo,heartNum) {
			// get: url, post: 추가정보지정.
			fetch('heartCheck.do', {
				method: 'post',
				headers: {
					'Content-type': 'application/x-www-form-urlencoded'
				},  
				body: "petListNo="+petListNo
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