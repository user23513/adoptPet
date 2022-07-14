<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="memberUpdate.do">
<input type="text" value="${member.memberId}" disabled="disabled"><br>
<input type="password" value="${member.memberPassword}" disabled="disabled"><button>비밀번호수정</button><br>
<input type="text" value="${member.memberName}"><br>
<input type="text" value="${member.memberTel}"><br>
<input type="text" value="${member.memberEmail}"><br>
<input type="text" value="${member.memberJob}"><br>
<input type="submit" value="수정완료">
<button onclick="location.href=''">수정취소</button>
</form>
</html>