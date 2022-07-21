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

button {
	float: right;
}
</style>
</head>
<body>
			<h1>봉사후기 내용</h1>
		
		
			<form name="writeFrm">
				<input type="hidden" name="boardNo" value="${BoardVO.boardNo}">
				<table>
					<tr>
						<th width="100px" style="border: 1px solid #e7e7e7">글번호</th>
						<td width="400px">${BoardVO.boardNo}</td>
						<th style="border: 1px solid #e7e7e7">작성일자</th>
						<td>${BoardVO.boardDate}</td>
					</tr>
					<tr>
						<th style="border: 1px solid #e7e7e7">제목</th>
						<td colspan="4">${BoardVO.boardTitle}</td>
					</tr>
					<tr>
						<th style="border: 1px solid #e7e7e7">내용</th>
						<td colspan="6" style="padding: 20px">${BoardVO.boardContent}</td>
					</tr>
				</table>
				<button type="button" style="margin: 10px" class="btn btn-primary btn-xl" onclick="update()">수정</button>
				<button type="button" style="margin: 10px" class="btn btn-primary btn-xl" onclick="location.href='volReviewList.do'">뒤로가기</button>
		
		</form>
	

	<script>
		function update() {
			var result = confirm("게시글을 수정하시겠습니까?");
			if (result) {
				var form = document.writeFrm;
				form.method = "post";
				form.action = "volReviewUpdate.do";
				form.submit();
			}
		}
	</script>
</body>
</html>