<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입양동물 수정 폼</title>
</head>
<body>
	<form action="petAddUpdateForm.do" method="post">
			  <input type="hidden" id="petAddNo" name="petAddNo" value="${petAddVO.petAddNo}"><br>
		동물이름<input type="text" id="petAddName" name="petAddName" value="${petAddVO.petAddName}"><br>
		동물나이<input type="text" id="petAddAge" name="petAddAge" value="${petAddVO.petAddAge}"><br>
		동물성별<input type="radio" id="petAddGender" name="petAddGender" value="남자" 
			<c:if test="${petAddVO.petAddGender eq '남자'}">checked</c:if>>남
			  <input type="radio" id="petAddGender" name="petAddGender" value="여자"
			  <c:if test="${petAddVO.petAddGender eq '여자'}">checked</c:if>>여<br>
		동물체중<input type="text" id="petAddWeight" name="petAddWeight" value="${petAddVO.petAddWeight}"><br>
		건강상태<input type="text" id="petAddHealth" name="petAddHealth" value="${petAddVO.petAddHealth}"><br>
		입양여부<input type="text" id="petAddAdoptState" name="petAddAdoptState" value="${petAddVO.petAddAdoptState}"><br>
		동물유형<select id="petAddType" name="petAddType">
				<option value="강아지" <c:if test="${petAddVO.petAddType eq '강아지'}">selected</c:if> >강아지</option>
				<option value="고양이" <c:if test="${petAddVO.petAddType eq '고양이'}">selected</c:if> >고양이</option>	
		      </select><br>
		<input type="submit" value="수정">
		<input type="reset" value="리셋">
	</form>
</body>
</html>