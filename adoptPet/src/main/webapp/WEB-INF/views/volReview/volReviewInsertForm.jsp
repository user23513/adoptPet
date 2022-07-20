<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>봉사활동후기등록폼</title>
<link href="css/adoptpet.css" rel="stylesheet" /> 
</head>
<body>
	<div align="center">
		<div><h1>봉사후기등록</h1></div>
		<div>
			<form id="vFrm" action="volReviewInsert.do" method="post" >
				<div>
					<table border="1">
					<tr>
						<th width="150">작성자</th>
						<td><input type="text" id="boardWriter" name="boardWriter" value ="${id }" required ></td>
					</tr>
						<tr>
							<th>제목</th>
							<td colspan="3"><input type="text" id="boardTitle" name="boardTitle" size="95" required></td>
						</tr>
						<tr>
							<th>내용</th>
							<td colspan="3"><textarea rows="6" cols="100" id="boardContent" name="boardContect" required></textarea></td>
						</tr>

					</table>


				</div>
				<br>
				<div>
					<input type="submit" value="저장">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="전체삭제">&nbsp;&nbsp;&nbsp;
					<button type="button" onclick="location.href='volReviewList.do'">등록취소</button>
				</div>
			</form>
		</div>
</div>		
</body>
</html>