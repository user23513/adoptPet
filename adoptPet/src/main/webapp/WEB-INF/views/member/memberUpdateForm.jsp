<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 
 --%>
 <form action="memberUpdate.do" method="post">
 	<input type="text" value="${member.memberId}" id="memberId" name="memberId" readonly="readonly"><br>
	<input type="password" value="${member.memberPassword}" readonly="readonly"><button>비밀번호수정</button><br>
	<input type="text" value="${member.memberName}" id="memberName" name="memberName"><br>
	<input type="text" value="${member.memberTel}" id="memberTel" name="memberTel"><br>
	<input type="text" value="${member.memberEmail}" id="memberEmail" name="memberEmail"><br>
	<input type="text" value="${member.memberJob}" id="memberJob" name="memberJob"><br>
	<input type="text" value="${member.memberAuthor}" id="memberAuthor" name="memberAuthor"><br>
	<button type="submit">수정</button>
	<button onclick="location.href='main.do'">취소</button>
</form>	
</html>