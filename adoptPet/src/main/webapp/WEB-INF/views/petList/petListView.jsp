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
					<th width="100">동물유형</th>
					<td width="100">${vo.petListType}</td>
					<th width="100">입양여부</th>
					<td width="100">${vo.petListState}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${vo.petListWriter}</td>
					<th>제목</th>
					<td>${vo.petListTitle}</td>
				</tr>
				<tr>
					<td colspan="4">
						<div>
							<c:forEach var="filePath" items="${filePathList}">
								<c:if test="${not empty filePath}">
									<img align="middle" width="500" height="500" src="${filePath}"><br>
								</c:if> 
							</c:forEach>
						</div>
						<span>${vo.petListContent}</span>
					</td>
				</tr>
		</table>
		<div>
			<c:if test="${id}">
			
			
				<button type="button">수정</button>
			</c:if>
		</div>
	</div>
</body>
</html>