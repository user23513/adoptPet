<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>봉사활동후기 단건조회</title>
<style>
table {
	border: 1px solid black;
	border-radius: 10px;
}
</style>
<h2>봉사후기글 상세페이지</h2>
</head>
<body>
	<div align="center">
		<div>
			<h1>봉사후기 등록</h1>
		</div>
		<div>
			<form action="volReviewSelectOne.do" method="post">
				<div>
				<input type="hidden" id="boardNo" name="boardNo" value="${BoardVO.boardNo}">
						
						<br>
					<table border="1">

						<tr>
							<th width="100">게시판유형</th>
							<td width="200"><input type="text" id="boardId"
								name="boardId" value="${BoardVO.boardId }">
							<td></td>
						</tr>

						<tr>
							<th>제목</th>
							<td colspan="3"><input type="text" size="73" id="boardTitle"
								name="boardTitle" value="${BoardVO.boardTitle }"><br>
							</td>
						</tr>
						<tr>
							<th width="100">작성자 <input type="text" id="boardWriter"
								name="boardWriter" value="${BoardVO.boardWriter }"><br>
						</tr>

						<tr>
							<th>내용
							<th>
							<td colspan="3"><textarea rows="6" cols="75"
									id="boardContent" name="boardContent"></textarea>></td>
						</tr>

						<tr>
							<th width="150">작성일자</th>
							<td width="200"><input type="date" id="boardDate"
								name="boardDate"></td>
						</tr>

						<tr>
							<th>조회수</th>
							<td><input type="number" id="boaardHit" name="boaardHit">
							</td>
						</tr>
					</table>
				</div>
				<br> <input type="submit" value="저장">&nbsp;&nbsp;&nbsp;
				<input type="reset" value="취소">&nbsp;&nbsp;&nbsp;
			</form>
		</div>
	</div>
</body>
</html>