<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입양동물 소개 게시판</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery-3.6.0.min.js"></script> <!-- 제이쿼리 라이브러리 쓰겠다. -->
<style>
#list {
	 text-align : center;
}

table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 16px;
}

tbody>tr:nth-child(even) {
  background-color: #e7e7e5;
}

tbody>tr:nth-child(odd) {
  background-color: #bed4cf;
}

thead {
	background-color: #bcccc9;
}
</style>
</head>
<body>
<div>
	<% 	int pageCount = (int)request.getAttribute("pageCount");
		int pageBlock = (int)request.getAttribute("pageBlock");
		int startPage = (int)request.getAttribute("startPage"); //게시글이 하나도 없을때 0이다.
		int endPage = (int)request.getAttribute("endPage");
	%>
		
	</div>
	<div id="list">
		<h2>입양동물 소개 리스트</h2>
		<button type="button" style="float: left;" onclick="location.href='petAddList.do'">입양등록</button>
		<div align="right">
			<form id="frm">
				<select id="key" name="key" >
					<option value="pet_list_title">제목</option>
					<option value="pet_list_content">내용</option>
				</select>&nbsp;
				<input type="text" id="val" name="val">&nbsp;&nbsp;
				<input type="button" value="검색" onclick="petListSearch()">
			</form>
		</div>
		<table id="tb">
			<thead>
				  <tr>
				  	<th>No</th>
				    <th>게시물번호</th>
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
						<c:forEach var="list" items="${petList}">
							<tr>
								<td>1</td>
								<td>${list.petListNo}</td>
								<c:if test="${not empty list.filesPath1}">
									<td><img width="70" height="70" src="fileup/${list.filesPath1}"></td>
								</c:if>
								<c:if test="${empty list.filesPath1}">
									<td></td>
								</c:if>
								<td><a href="petListView.do?petListNo=${list.petListNo}&petAddNo=${list.petAddNo}"> ${list.petListTitle}</a></td>
								<td>${list.petListState}</td>
								<td>${list.petListType}</td>
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
				
				<%for (int i = startPage; i<=endPage; i++) { %>
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
		
		//검색기능
		function petListSearch() {
			let key = $('#key').val();
			let val = $('#val').val();
			//ajax function Call
			$.ajax({
				url:"petListSearch.do",
				type:"post",
				data:{
					"key" : key,
					"val" : val
				},
				dataType:"json",
				success : function(result) {
					console.log(result)
					$('tbody').remove();
					var tbody = $("<tbody />");
					
					$.each(result, function(index, item) {
						var row = $("<tr />").append(
								$("<td />").text(item.petListNo),
								$("<td />").text(item.filesPath1),
								$("<td />").text(item.petListTitle),
								$("<td />").text(item.petListState),
								$("<td />").text(item.petListType),
								$("<td />").text(item.heartNum)
								);
								tbody.append(row);
							});
					
					$('table').append(tbody);
					
				},
				error : function(error) {
					console.log(error);
				}
			})
			
		}
	</script>
</body>
</html>