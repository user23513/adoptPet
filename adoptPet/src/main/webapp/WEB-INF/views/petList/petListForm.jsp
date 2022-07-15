<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입양소개게시판 등록</title>
</head>
<body>
	<div>
		<form action="petListInsert.do" method="post" enctype="multipart/form-data" >
			동물유형<select name="petListType"> 
					<option value="강아지">강아지</option>
					<option value="고양이">고양이</option>
				  </select>
			말머리<select name="petListState" onChange="this.selectedIndex = this.initialSelect;">
					<option value="입양대기">입양대기</option>
					<option value="입양완료">입양완료</option>			
				</select><br>
			작성자<input type="text" name="petListWriter" value="관리자" readonly>
			제목<input type="text" required placeholder="유기동물 이름을 입력하세요"><br>
			내용<br><textarea rows="6" cols="60" name="petListContent"></textarea><br>
			첨부파일<br><input type="file" name="file"><br>
					  <input type="file" name="file"><br>
					  <input type="file" name="file"><br>
			<input type="submit" value="등록">
			<input type="reset" value="리셋">	
		</form>
	</div>
</body>
</html>