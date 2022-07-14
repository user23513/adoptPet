<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>${member.memberId}</p>
	<p>${member.memberPassword}</p>
	<p>${member.memberName }</p>
	<p>${member.memberTel}</p>
	<p>${member.memberEmail }</p>
	<p>${member.memberJob}</p>
	<p>${member.memberGender }</p>
	<p>${member.memberAuthor }</p>
	<button onclick="location.href='memberUpdate.do'">내정보수정하기</button>
	<button onclick="memberDelete()">내정보삭제하기</button>
	
<script type="text/javascript">

function memberDelete(){
	let inputPassword = prompt("비밀번호를 입력해주세요.");
}
</script>	
</body>
</html>