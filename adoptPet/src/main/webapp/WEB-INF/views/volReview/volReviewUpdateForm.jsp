<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>봉사활동 후기 수정</title>
</head>
<body>
	<div align="center">
		<div>
			<h1>게시글 수정 등록</h1>
		</div>
	<div>
		<form action="volReviewUpdate.do" method="post">
			<div>
				<table border="1">
					<tbody>
						<c:choose>
							<c:when test="${not empty BoardVO }">
								<input type="hidden" id="boardNo" name="boardNo" value="${BoardVO.boardNo}">
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
							<th>내용</th>
							<td colspan="3"><input type="text" id="boardContent" name="boardContent" value="${boardVO.boardContent}"></textarea>></td>
						</tr>
								</c:when>
										<c:otherwise>
												<tr>
													<td colspan="5" align="center">게시글이 존재하지 않습니다</td>
												</tr>
										</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
				<br> 
				<input type="submit" value="수정완료">&nbsp;&nbsp;&nbsp;
				<input type="reset" value="취소">&nbsp;&nbsp;&nbsp;
			</form>
		</div>
	</div>
</body>
</html>