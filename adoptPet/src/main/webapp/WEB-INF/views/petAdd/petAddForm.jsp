<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입양동물 등록 폼</title>
</head>
<body>
	<form action="petAddList.do" method="post">
		동물이름<input type="text" id="petAddName" name="petAddName" value=""><br>
		동물나이<input type="text" id="petAddAge" name="petAddAge" value=""><br>
		동물성별<input type="radio" id="petAddGender" name="petAddGender" value="남자">남
			  <input type="radio" id="petAddGender" name="petAddGender" value="여자">여<br>
		동물체중<input type="text" id="petAddWeight" name="petAddWeight" value=""><br>
		동물건강상태<input type="text" id="petAddHealth" name="petAddHealth" value=""><br>
		동물나이<input type="text" id="petAddAdoptState" name="petAddAdoptState" value="입양대기" readonly><br>
		동물유형<select id="petAddType" name="petAddType">
				<option value="강아지">강아지</option>
				<option value="고양이">고양이</option>	
		      </select><br>
		<input type="submit" value="등록">
		<input type="reset" value="리셋">
	</form>
</body>
</html>