<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"/></script>
<link href="css/adoptpet.css" rel="stylesheet" /> 
</head>
<body>
<div class="myDiv">
    <div class="row gx-4 gx-lg-5 justify-content-center">
        <div class="col-lg-8 col-xl-6 text-center">
            <h2 class="mt-0">MEMBER INFO</h2>
            <hr class="divider" />
        </div>
    </div>
	<table>
		<tr>
			<th>아이디</th>
			<td>${member.memberId}</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>${member.memberPassword}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${member.memberName}</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${member.memberTel}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${member.memberEmail}</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>${member.memberJob}</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${member.memberGender}</td>
		</tr>
		<tr>
			<th>권한</th>
			<td>${member.memberAuthor}</td>
		</tr>
	</table>
	<br>
	<div id="btnDiv">
		<button class="btn btn-primary btn-xl" onclick="location.href='adoptList.do'" style="margin-left: 50px">뒤로가기</button>
	</div>
</div>	
</body>
</html>