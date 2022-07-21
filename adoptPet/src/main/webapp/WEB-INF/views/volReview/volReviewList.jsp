<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>봉사활동 후기 리스트</title>
<script src="js/jquery-3.6.0.min.js"></script> <!-- 제이쿼리 라이브러리 쓰겠다. -->
</head>
<body>
	<div id="list" align="center">
	
		<div>봉사활동후기 목록</div>
		<div>
			<form id="vFrm">
				<select id="key" name="key">
					<option value="board_title">제목</option>
					<option value="board_subject">내용</option>
					<option value="board_writer">작성자</option>
				</select>&nbsp; 
				<input type="text" id="val" name="val">&nbsp;&nbsp; 
				<input type="button" value="검색" onclick=volReviewSearchList()>
			</form>
		</div>
		<div>
			<table border="1">
				<thead>
					<tr>
						<th width="50">No</th>
						<th width="50">게시판순서</th>
						<th width="100">작성자</th>
						<th width="150">제목</th>
						<th width="150">작성일자</th>
						<th width="50">조회수</th>
					</tr>
				</thead>
				<tbody id="tb">
				<c:choose>
					<c:when test="${not empty volReviewList }">
						<c:forEach var="b" items="${volReviewList }" varStatus="i" >
							<tr id="${b.boardNo }">
								<td>${b.boardNo }</td>
								<td>${cnt-(pageNum-1)*pageSize - i.index }</td>
								<%-- <td><a href="volReviewUpdate.do?boardNo=${b.boardNo }"></a></td> --%>
								<td>${b.boardWriter }</td>
								<td><a href="volReviewSelectOne.do?boardNo=${b.boardNo}"> ${b.boardTitle }</a></td>
								<td>${b.boardDate }</td>
								<td>${b.boardHit }</td>
								<!-- <td><button type="button" class="btn btn-primary btn-xl" id="writeBtn" onclick="location.href='volReviewUpdateForm.do?'">수정</button> </td> -->
								<td><button type="button" class="btn btn-primary btn-xl" onclick="volReviewDeleteFnc(${b.boardNo })">삭제</button></td>

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
	<%-- <c:if test="${author != 'ADMIN' }"> --%><!-- 접근권한  -->
	<button type="button" onclick="location.href='volReviewForm.do'">후기글등록</button>
	<%-- </c:if> --%>
</div>
</div>
	<div>
		<% 	int pageCount = (int)request.getAttribute("pageCount");
			int pageBlock = (int)request.getAttribute("pageBlock");
			int startPage = (int)request.getAttribute("startPage"); //게시글이 하나도 없을때 0이다.
			int endPage = (int)request.getAttribute("endPage");
			
			for (int i = startPage; i<=endPage; i++) { %>
				<a href="volReviewList.do?pageNum=<%=i%>"><%=i %></a>
			<% } %>
	</div>	
	
<script type="text/javascript">
		function volReviewSearchList(){
			let key = $("#key").val();
			let val = $("#val").val();
			$.ajax({
				url : "volReviewSearchList.do",
				type : "post",
				data : {key : key, val : val},
				dataType : "Json",
				success : function(result){
					console.log(result);
					if(result.length > 0){
						jsonHtmlConvert(result);
					} else {
						alert("검색한 결과가 없습니다.");
					}console.log(result);
			},
			error:function(){	
			}
			})
		}	
			
		function jsonHtmlConvert(data) {
			$("#tb").remove();
			var tbody = $("<tbody id />");
			$.each(data, function (index, item){
				var row = $("<tr />").append(
						   $("<td />").text(item.boardNo),
						   $("<td />").text(item.boardNo),
						   $("<td />").text(item.boardWriter),	
						   $("<td />").text(item.boardTitle),	
						   $("<td />").text(item.boardDate),	
						   $("<td />").text(item.boardHit),
						);
				tbody.appen(row);
			});
			$('table').append(tbody);
		}

		function volReviewDeleteFnc(boardNo) {
			fetch('volReviewDelete.do',{
				method: 'post',
				headers: {
					'Content-type': 'application/x-www-form-urlencoded'
				},  
				body:"boardNo="+boardNo
			})
			.then(function(result){
						return result.json();
						console.log(result);
					})
			.then(function(result){
				console.log(result)
				if(result==1) {
					alert("삭제가 완료되었습니다!");
					location.href = "volReviewList.do";
				}else{
					alert("삭제가 되지않았습니다!");
					location.href = "volReviewList.do";
				}
			})
		
		}
	</script>	
		
</body>
</html>