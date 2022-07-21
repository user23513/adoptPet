 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입양동물 등록 리스트</title>
<script src="js/jquery-3.6.0.min.js"></script> <!-- 제이쿼리 라이브러리 쓰겠다. -->
<link href="css/adoptpet.css" rel="stylesheet" />   

<style>
	#insertBtn {
		float: right;
		margin-top: 15px;
		margin-right: 20px
	}
	
	#ListBtn {
		float: left;
		margin-top: 15px;
	}
	
	#paging {
		margin-top: 35px;
	}
</style>
 
</head>
<body>
	<section class="notice">
  		<div class="page-title">
        	<div class="container">
            	<h3>입양 동물 등록</h3>
        	</div>
    	</div>
	<div>
		<table border="1">
			<thead>
				<tr>
					<th>등록번호</th>
					<th>이름</th>
					<th>나이</th>
					<th>성별</th>
					<th>체중</th>
					<th>건강상태</th>
					<th>입양여부</th>
					<th>동물유형</th>
					<th>글쓰기</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="vo" items="${petAddList}">
					<input id="petAddCheck" name="petAddCheck" type="hidden" value="${vo.petAddCheck}">
					<tr id="${vo.petAddNo }">
						<td>${vo.petAddNo }</td>
						<td><a href="petAddUpdate.do?petAddNo=${vo.petAddNo }">${vo.petAddName }</a></td>
						<td>${vo.petAddAge }</td>
						<td>${vo.petAddGender }</td>
						<td>${vo.petAddWeight }</td>
						<td>${vo.petAddHealth }</td>
						<td>${vo.petAddAdoptState }</td>
						<td>${vo.petAddType }</td>
						<td><button type="button" class="btn btn-primary btn-xl" id="writeBtn" onclick="location.href='petListForm.do?petAddNo=${vo.petAddNo }'">글쓰기</button> </td>
						<td><button type="button" class="btn btn-primary btn-xl" onclick="petAddDeleteFnc(${vo.petAddNo })">삭제</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<button id="insertBtn" type="button" class="btn btn-primary btn-xl" onclick="location.href='petAddForm.do'">동물 등록</button>
			<button id="ListBtn" type="button" class="btn btn-primary btn-xl" onclick="location.href='petList.do'">소개글 목록</button>
		</div>
		<div id="paging" align="center">
			<% 	int pageCount = (int)request.getAttribute("pageCount");
			int pageBlock = (int)request.getAttribute("pageBlock");
			int startPage = (int)request.getAttribute("startPage"); //게시글이 하나도 없을때 0이다.
			int endPage = (int)request.getAttribute("endPage");
			
			for (int i = startPage; i<=endPage; i++) { %>
				<a href="petAddList.do?pageNum=<%=i%>"><%=i %></a>
			<% } %>
		</div>
	</div>	

	<script>
		let writeBtn = document.querySelectorAll('#writeBtn');
		let check = document.querySelectorAll('#petAddCheck') //check가 true면 보이고 false면 안보이게
		writeBtn.forEach((element,idx) => {
			if(check[idx].value == 'false') {
				element.setAttribute('disabled','disabled');
			}	
		});

		function petAddDeleteFnc(petAddNo) {
			fetch('petAddDelete.do',{
				method: 'post',
				headers: {
					'Content-type': 'application/x-www-form-urlencoded'
				},  
				body:"petAddNo="+petAddNo
			})
			.then(function(result){
						return result.json();
					})
			.then(function(result){
				console.log(result)
				if(result==1) {
					alert("삭제가 완료되었습니다!");
					location.href = "petAddList.do";
				}else{
					alert("삭제가 되지않았습니다!");
					location.href = "petAddList.do";
				}
			})
			.catch(function(err){
						console.error(err);
				})
		}
	</script>
</body>
</html>