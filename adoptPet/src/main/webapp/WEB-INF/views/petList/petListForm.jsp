<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입양소개게시판 등록</title>
</head>
<body>
	<div align="center">
		<form action="petListInsert.do" method="post" enctype="multipart/form-data" >
			동물유형<select id="petListType" name="petListType"> 
					<option value="강아지">강아지</option>
					<option value="고양이">고양이</option>
				  </select>
			말머리<select id="petListState" name="petListState" onChange="this.selectedIndex = this.initialSelect;">
					<option value="입양대기">입양대기</option>
					<option value="입양완료">입양완료</option>			
				</select><br>
				<input type="hidden" name="boardId" value="50">
			작성자<input type="text" id="petListWriter" name="petListWriter" value="관리자" readonly>
			   	<input type="hidden" name="petAddNo" value="${petAddVo.petAddNo }">
				제목<input type="text" id="petListTitle" name="petListTitle" value="${petAddVo.petAddName}" readonly><br>
			내용<br><textarea rows="6" cols="60" id="petListContent" name="petListContent"></textarea><br>
			첨부파일<br><input type="file" name="file1"><br>
					  <input type="file" name="file2"><br>
					  <input type="file" name="file3"><br>
			<input type="submit" value="등록">
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