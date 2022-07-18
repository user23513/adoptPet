<%@page import="co.yedam.puppy.vo.FilesVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입양소개게시판 수정</title>
</head>
<body>
	<div>
		<form action="petListUpdate.do" method="post" enctype="multipart/form-data" >
			<input type="hidden" name="petListNo" value="${vo.petListNo}">
			동물유형<select name="petListType"> 
				<c:choose>
					<c:when test="${vo.petListType eq '강아지' }">
						<option value="강아지" selected>강아지</option>
						<option value="고양이">고양이</option>
					</c:when>
					<c:otherwise>
						<option value="강아지">강아지</option>
						<option value="고양이" selected>고양이</option>
					</c:otherwise>
				</c:choose>
				  </select>
			말머리<select name="petListState">
					<c:choose>
						<c:when test="${vo.petListState eq '입양대기' }">
							<option value="입양대기" selected>입양대기</option>
							<option value="입양완료">입양완료</option>	
						</c:when>
						<c:otherwise>
							<option value="입양대기" >입양대기</option>
							<option value="입양완료" selected>입양완료</option>	
						</c:otherwise>
					</c:choose>		
				</select><br>
			<input type="hidden" name="boardId" value="50">
			작성자<input type="text" name="petListWriter" value="관리자" readonly>
			제목<input type="text" name="petListTitle" value="${vo.petListTitle}" placeholder="유기동물 이름을 입력하세요" required="required"><br>
			내용<br><textarea rows="6" cols="60" name="petListContent">${vo.petListContent}</textarea><br>
			첨부파일
			<input type="file" name="file1"><br>
			<input type="file" name="file2"><br>
			<input type="file" name="file3"><br>
			<input type="submit" value="수정">
			<input type="reset" value="리셋">	
		</form>
	</div>
	<script>
		if(${r}>0 ) {
			alert("등록이 완료되었습니다!");
			location.href = "petList.do";
		}
	</script>
</body>
</html>