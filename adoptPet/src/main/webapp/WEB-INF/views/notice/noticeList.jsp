<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<script type="js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div align="center">
<div>게시판 목록</div>
	<div>
		<form id="frm">
		<select id="key" name="cars">
			  <!-- <option value="1">전체</option> -->
			  <option value="board_title">제목</option>
			  <option value="board_subject">내용</option>
			  <option value="board_writer">작성자</option>
		</select>&nbsp;
			<input type="text" id="val" name="val">&nbsp;&nbsp;
			<input type="button" value="검색" onclick="noticeSerarch()">
		</form>	
	</div>
<div>
	<table border="1">
		<thead>
			<tr>
				<th width="50">순번</th>
				<th width="50">유형</th>
				<th width="100">작성자</th>
				<th width="250">제목</th>
				<th width="70">작성일자</th>
			<!-- 	<th width="180">첨부파일</th> -->
				<th width="50">조회수</th>
			</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${not empty list }">
				<c:forEach items="${list }" var="b">
					<tr>
						<td>${b.boardNo }</td>
						<td>${b.boardId }</td>
						<td>${b.boardWriter }</td>
						<td><a href="noticeSelect.do">${b.boardTitle }</td>
						<td>${b.boardDate }</td>
					<%-- 	<td>${b.boardAttech }</td> --%>
						<td>${b.boardHit }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6" align="center">게시글이 존재하지 않습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>
</div>
<br>
<div>
	<c:if test="${author == 'ADMIN' }"><!-- 접근권한  -->
	<button type="button" onclick="location.href='noticeForm.do'">글등록</button>
	</c:if>
</div>
</div>
<!-- <script type="text/javascript">
	function noticeSerarch() {
		let key = $("#key").val();//$("#key").val();
;		let val = $("#val").val();//$("#val").val();
		//ajax function Call
		$.ajax({
			url : "ajaxBoardSearch.do",
			type : "post",
			data : {key:key, val:val},
			dataType : "json",
			
			success : function(result){
				if(result.length > 0){
					jsonHtmlConvert(result);
				}else{
					alert("검색한 결과가 없습니다.")
				}
		},
			error : function(){

	}
});
	}
	function jsonHtmlConvert(data){
		$('tbody').remove();
		var tbody = $("<tbody />");
		$.each(data, function(index,item){
			var row = $("<tr />").append($("<td />").text(item.boardId),
					  $("<td />").text(item.boardWriter),
					  $("<td />").text(item.boardTitle),
					  $("<td />").text(item.boardDate),
				//	  $("<td />").text(item.boardAttech),
					  $("<td />").text(item.boardHit)
					  );
			tbody.append(row);
		});
		
		$("table").append(tbody);
	}
</script> -->
</body>
</html>