<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>봉사활동후기 단건조회</title>
<link href="css/adoptpet.css" rel="stylesheet" />
<style>
table {
	border: 1px solid black;
	border-radius: 10px;
}
</style>
<h2>봉사후기글 상세페이지</h2>
</head>
<body>
<section class="volunteer">
<div class = "page-title">
	<div align="center">
		<div>
			<h1>봉사후기 내용</h1>
		</div>
		<div>
			<form name="writeFrm">
	
	<table>
 		<tr>
 		<th width="100px" style="boarder:1px solid #e7e7e7"> 글번호</th>
 			<td width ="400px">${BoardVO.boardNo}</td>
 		<th>제목</th>
			<td>${BoardVO.boardTitle}</td>
		<th>작성일자</th>
			<td>${BoardVO.boardDate}</td>
		</tr>
		<tr>
		<th>내용</th>
			<td colspan="6" rowspan="10">${BoardVO.boardcontent}</td>
		</tr>
		
	</table>
			
		<div align="center">
		<button type="button" onclick="update()">수정</button>
		<button type="button" onclick="location.href='volReviewList.do'">뒤로가기</button>
		</div>
	</form>
	</div>
</div>
	<script>

	 
	 function update() {
		 	var result = confirm("게시글을 수정하시겠습니까?");
		 	if (result) {
		 		var form = document.writeFrm;
		 		form.method = "post";
		 		form.action = "volReviewUpdateForm.do";
		 		form.submit();
		 	}
		 }
	 
	
	</script>
</body>
</html>