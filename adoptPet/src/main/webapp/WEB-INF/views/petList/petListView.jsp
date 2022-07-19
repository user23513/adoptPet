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
	<div align="center">
		<table border="1">
				<tr>
					<th width="500">동물유형</th>
					<td width="500">${vo.petListType}</td>
					<th width="500">입양여부</th>
					<td width="500">${vo.petListState}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${vo.petListWriter}</td>
					<th>제목</th>
					<td>${vo.petListTitle}</td>
				</tr>
				<tr>
					<td colspan="4">
						<div style="text-align : center;">
							<c:forEach var="filePath" items="${filePathList}">
								<c:if test="${not empty filePath}">
									<img align="middle" width="700" height="500" src="${filePath}"><br>
								</c:if> 
							</c:forEach>
						</div>
						<span>${vo.petListContent}</span>
					</td>
				</tr>
		</table>
		<div>
			<button type="button" style="float: left;" onclick="location.href = 'petList.do'">목록</button>
<%-- 			<input type="hidden" value="${동물번호 }"> --%>
			<!-- 세션 아이디값이 어드민일때 설정 -->
			<button type="button" style="float: right;;" onclick="adoptSubFnc()">입양신청</button>
			<button type="button" style="float: right;" onclick="petListDeleteFnc(${vo.petListNo})">삭제</button>
			<button type="button" style="float: right;" onclick="location.href = 'petListUpdateForm.do?petListNo=${vo.petListNo}'">수정</button>
		</div>
	</div>
	
	<script>
	function petListDeleteFnc(petListNo) {
		fetch('petListDelete.do',{
			method: 'post',
			headers: {
				'Content-type': 'application/x-www-form-urlencoded'
			},  
			body:"petListNo="+petListNo
		})
		.then(function(result){
					return result.json();
				})
		.then(function(result){
			if(result==1) {
				alert("삭제가 완료되었습니다!");
				location.href = "petList.do";
			}else{
				alert("삭제가 되지않았습니다!");
				location.href = "petList.do";
			}
		})
		.catch(function(err){
					console.error(err);
			})
		
		
	}
	
	
	function adoptSubFnc() {
		window.open("<%= request.getContextPath()%>/petListForm.do","petListForm","width=640", "height=400");
	}
	
	</script>
</body>
</html>