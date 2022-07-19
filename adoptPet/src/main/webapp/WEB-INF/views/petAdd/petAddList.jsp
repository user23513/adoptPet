<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입양동물 등록 리스트</title>
<script src="js/jquery-3.6.0.min.js"></script> <!-- 제이쿼리 라이브러리 쓰겠다. -->
</head>
<body>
<%-- 	${message} 입양등록처리 되었습니다 메세지 --%>

	<div align="center">
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
					<tr id="${vo.petAddNo }">
						<td>${vo.petAddNo }</td>
						<td><a href="petAddUpdate.do?petAddNo=${vo.petAddNo }">${vo.petAddName }</a></td>
						<td>${vo.petAddAge }</td>
						<td>${vo.petAddGender }</td>
						<td>${vo.petAddWeight }</td>
						<td>${vo.petAddHealth }</td>
						<td>${vo.petAddAdoptState }</td>
						<td>${vo.petAddType }</td>
						<c:if test=""></c:if>
						<td><button type="button" onclick="location.href='petListForm.do?petAddNo=${vo.petAddNo }&petAddName=${vo.petAddName }'">글쓰기</button> </td>
						<td><button type="button" onclick="petAddDeleteFnc(${vo.petAddNo })">삭제</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<button type="button" onclick="location.href='petAddForm.do'">등록</button>
		</div>
		<div>
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