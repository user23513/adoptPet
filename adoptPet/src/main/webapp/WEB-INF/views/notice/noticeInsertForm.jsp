<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 적는 폼</title>
</head>
<body>
<div align="center">
	<div><h1>게시글등록</h1></div>
	<div>
		<form id="frm" action="noticeInsert.do" method="post" enctype="multipart/form-data">
			<div>
			
			<div class="form-group">
      <label for="exampleInputEmail1" class="form-label mt-4">제목</label>
      <input type="text" class="form-control" id="boardTitle" aria-describedby="titleHelp" placeholder="제목을 작성하세">
				<table border="1">
					<tr>
						<%-- <th width="150">작성자</th>
						<td width="200">${board.boardWriter}</td> --%>
							
					<tr>
						<th>제목</th>
						<td colspan="3">
							<input type="text" id="boardTitle" name="boardTitle" size="73">
						</td>
					</tr>
					<tr>
						<th width="150">작성일자</th>
						<td width="200">
							<input type="date" id="boardDate" name="boardDate"> 
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							<textarea rows="6" cols="75" id="boardContent" name="boardContect"></textarea>
						</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td colspan="3">
							<input type="file" id="filesName" name="filesName">
						</td>
					</tr>
				</table>
			</div><br>
			<div>
				<input type="submit" value="저장">&nbsp;&nbsp;&nbsp; 
				<input type="reset" value="취소">
			</div>
		</form>
	</div>
</div>
</body>
</html>