<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<script src="jquery/jquery.js"></script>
<script type="text/javascript" src='js/bootstrap.min.js'></script>
<!-- 부트스트랩 css 사용 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/minty/bootstrap.min.css" integrity="sha384-H4X+4tKc7b8s4GoMrylmy2ssQYpDHoqzPa9aKXbDwPoPUA3Ra8PA5dGzijN+ePnH" crossorigin="anonymous">
<style>

</style>
</head>
<body>
	<header>
		<h2>
			<a href="main.do">PICKME</a>
		</h2>
	</header>

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<div class="container-fluid">


			<div class="collapse navbar-collapse" id="navbarColor01">
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link active" href="main.do"">Home
							<!-- <span class="visually-hidden">(current)</span> -->
					</a></li>

					<li class="nav-item dropdown">
					 <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
						href="#" role="button" aria-haspopup="true" aria-expanded="false">MyPage</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="memberMyPage.do">내정보보기</a> 
							<a class="dropdown-item" href="#">좋아요한 동물</a>
							<a class="dropdown-item" href="#">나의 입양신청 현황</a>
							<a class="dropdown-item" href="#">나의 봉사참여 신청 현황</a>
						</div></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
						href="#" role="button" aria-haspopup="true" aria-expanded="false">AdopPage</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="#">입양동물소개</a> 
							<a class="dropdown-item" href="#">입양동물 후기</a>
								
						</div></li>
						
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
						href="#" role="button" aria-haspopup="true" aria-expanded="false">Volunteer</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="#">봉사활동 일정</a> 
							<a class="dropdown-item" href="#">봉사활동 후기</a>
						</div></li>
						
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
						href="#" role="button" aria-haspopup="true" aria-expanded="false">Community</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="#">공지게시판</a> 
							<a class="dropdown-item" href="#">문의게시판</a>
						</div></li>
						
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
						href="#" role="button" aria-haspopup="true" aria-expanded="false">관리자페이지</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="#">회원리스트</a> 
							<a class="dropdown-item" href="#">입양신청내역</a>
							<a class="dropdown-item" href="#">봉사참여 신청내역</a>
							<a class="dropdown-item" href="#">전체후원내역</a>
							<a class="dropdown-item" href="#">입양동물등록</a>
						</div></li>
				</ul>

			</div>
		</div>
	</nav>


</body>
</html>