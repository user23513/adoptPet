<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글 상세 보기</title>
<style>
	table{
		border: 1px solid black;
		border-radius: 10px;
	}
</style>
</head>
<body>
 <h2>문의글 상세보기</h2>
 <div>
	<table>
 		<tr>
 			<td>글번호</td>
 			<td>${board.boardNo}</td>
			<td>제목</td>
			<td>${board.boardTitle}</td>
			<td>작성일자</td>
			<td>${board.boardDate}</td>
		</tr>
		<tr>
			<td colspan="6">내용</td>
		</tr>
		<tr>
			<td colspan="6" rowspan="10">${board.boardcontent}</td>
		</tr>

	</table>
	<button type="submit">수정</button>
	<button type="submit">삭제</button>
 </div>
 
</body>
</html>