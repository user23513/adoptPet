<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>데려가줘</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Bootstrap Icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Google fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic"
	rel="stylesheet" type="text/css" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/SimpleLightbox/2.1.0/simpleLightbox.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/styles.css" rel="stylesheet" />
</head>
<body id="page-top">
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="main.do">데려가줘 PICK ME</a>

			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

	     

			<div class="collapse navbar-collapse" id="navbarResponsive">

				<% if(session.getAttribute("id") == null){ // 로그인 안 한 상태 = 비회원 %>

				<ul class="navbar-nav ms-auto my-2 my-lg-0">
				    <li class="nav-item"><a class="nav-link" href="#services">MY PAGE</a></li>
	                <li class="nav-item"><a class="nav-link" href="#services">ADOPT PAGE</a></li>
	                <li class="nav-item"><a class="nav-link" href="#services">VOLUNTEER</a></li>
	                <li class="nav-item"><a class="nav-link" href="#services">COMMUNITY</a></li>
	                <li class="nav-item"><a class="nav-link" href="memberLoginForm.do">Login</a></li>
					<li class="nav-item"><a class="nav-link" href="memberJoinForm.do">회원가입</a></li>
				</ul>
			
				<%} else { // 로그인 한 상태 
		String author = (String) session.getAttribute("author");
		if(author.equals("ADMIN")) { // 로그인 함-> 권한:admin %>
					<ul class="navbar-nav ms-auto my-2 my-lg-0">
					  <li class="nav-item"><a class="nav-link" href="#services">MY PAGE</a></li>
	                <li class="nav-item"><a class="nav-link" href="#services">ADOPT PAGE</a></li>
	                <li class="nav-item"><a class="nav-link" href="#services">VOLUNTEER</a></li>
	                <li class="nav-item"><a class="nav-link" href="#services">COMMUNITY</a></li>
	                <li class="nav-item"><a class="nav-link" href="memberLogout.do">LogOut</a></li>
	                <li class="nav-item"><a class="nav-link" href="#services">관리자페이지</a></li>
				</ul>
				<%} else { // 로그인 함-> 권한:USER %>
				<ul class="navbar-nav ms-auto my-2 my-lg-0">
					<li class="nav-item"><a class="nav-link" href="#services">MY PAGE</a></li>
	                <li class="nav-item"><a class="nav-link" href="#services">ADOPT PAGE</a></li>
	                <li class="nav-item"><a class="nav-link" href="#services">VOLUNTEER</a></li>
	                <li class="nav-item"><a class="nav-link" href="#services">COMMUNITY</a></li>
	                <li class="nav-item"><a class="nav-link" href="memberLogout.do">LogOut</a></li>
				</ul>

				<%}%>
				<%}%>


				
			</div>
		</div>

	</nav>
</body>
</html>