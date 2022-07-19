<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

/* Style the header */
header {
  background-color: #666;
  padding: 30px;
  text-align: center;
  font-size: 35px;
  color: white;
}

/* Create two columns/boxes that floats next to each other */
nav {
  float: left;
  width: 30%;
  height: 300px; /* only for demonstration, should be removed */
  background: #ccc;
  padding: 20px;
}

/* Style the list inside the menu */
nav ul {
  list-style-type: none;
  padding: 0;
}

article {
  float: left;
  padding: 20px;
  width: 70%;
  background-color: #f1f1f1;
  height: 300px; /* only for demonstration, should be removed */
}

/* Clear floats after the columns */
section::after {
  content: "";
  display: table;
  clear: both;
}

/* Style the footer */
footer {
  background-color: #777;
  padding: 10px;
  text-align: center;
  color: white;
}

/* Responsive layout - makes the two columns/boxes stack on top of each other instead of next to each other, on small screens */
@media (max-width: 600px) {
  nav, article {
    width: 100%;
    height: auto;
  }
}
</style>
</head>

<body>
<% if(session.getAttribute("id") == null){ // 로그인 안 한 상태 %> 
	<ul>
	  <li><a href="memberJoinForm.do">회원가입</a></li>
      <li><a href="memberLoginForm.do">로그인</a></li>
      <li><a href="noticeList.do">공지리스트</a></li>

     </ul>
<%} else { // 로그인 한 상태 %>
		<%if(session.getAttribute("author") == "ADMIN") { // 로그인 함-> 권한이 admin인 사람 %>
		 <ul>
			<li><a href="memberLogoutForm.do">로그아웃</a></li>
			<li><a href="noticeList.do">공지리스트</a></li>
		 </ul>
		<%} else { // 로그인 함-> 권한이 admin이 아닌 사람=회원 %>
		 <ul>
			<li><a href="memberLogoutForm.do">로그아웃</a></li>
			<li><a href="memberMyPage.do">마이페이지</a></li>
			<li><a href="noticeList.do">공지리스트</a></li>
		 </ul>
		<%}%>
<%}%>





</body>

</html>