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
			동물유형<select name="petListType"> 
					<option value="강아지">강아지</option>
					<option value="고양이">고양이</option>
				  </select>
			말머리<select name="petListState">
					<option value="입양대기">입양대기</option>
					<option value="입양완료">입양완료</option>			
				</select><br>
			<input type="hidden" name="boardId" value="50">
			작성자<input type="text" name="petListWriter" value="관리자" readonly>
			제목<input type="text" name="petListTitle" placeholder="유기동물 이름을 입력하세요" required="required"><br>
			내용<br><textarea rows="6" cols="60" name="petListContent"></textarea><br>
			첨부파일<br><input type="file" name="file1"><br>
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