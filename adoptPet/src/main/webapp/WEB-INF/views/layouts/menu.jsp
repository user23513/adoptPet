<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>

<body>
<% if(session.getAttribute("id") == null){ // 로그인 안 한 상태 = 비회원 %> 
	<nav>
	<ul>
	  <li><a href="memberJoinForm.do">회원가입</a></li>
      <li><a href="memberLoginForm.do">로그인</a></li>
      <li><a href="noticeList.do">공지리스트</a></li>
     </ul>

	</nav>
<%} else { // 로그인 한 상태 
		String author = (String) session.getAttribute("author");
		//System.out.println(author);
		if(author.equals("ADMIN")) { // 로그인 함-> 권한:admin %>
		<nav>


		 <ul>
			<li><a href="memberLogout.do">로그아웃</a></li>
			<li><a href="noticeList.do">공지리스트</a></li>
		 </ul>
		 </nav>
		<%} else { // 로그인 함-> 권한:USER %>
		<nav>
		 <ul>
			<li><a href="memberLogout.do">로그아웃</a></li> 
			<li><a href="memberMyPage.do">마이페이지</a></li>
			<li><a href="noticeList.do">공지리스트</a></li>
		
		 
		 </ul>
		 </nav>
		<%}%>
<%}%>


</body>

</html>