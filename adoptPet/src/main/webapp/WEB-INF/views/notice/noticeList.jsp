<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<script type="js/jquery-3.6.0.min.js"></script>
<link href="css/adoptpet.css" rel="stylesheet" />   
<style>
	option{
		border: 1px solid black;
		border-radius: 10px;
	}
	
	section.notice {
  		padding: 80px 20px 20px 20px;
	}
	
	button {
  		float: right;
	}
</style>
</head>
<body>
<div align="center">
<section class="notice">
<div class="page-title">
        	<div class="container">
            	<h3>게시판 목록</h3>
        	</div>
    	</div>

	<div style="float: right; margin: 10px">
		<form id="frm" >
		<select id="key" name="key">
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
				<th width="50">No</th>
				<!-- <th width="50">유형</th> -->
				<th width="100">작성자</th>
				<th width="250">제목</th>
				<th width="70">작성일자</th>
				<th width="50">조회수</th>
			</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${not empty list }">
				<c:forEach items="${list }" var="b">
					<tr>
						<td>${b.boardNo }</td>
						<td>${b.boardWriter }</td>
						<td><a href="noticeSelect.do?no=${b.boardNo }">${b.boardTitle }</a></td>
						<td>${b.boardDate }</td>
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
	</div>
	<div id="paging" style="clear:both; text-align: center;">
	<% 	int pageCount = (int)request.getAttribute("pageCount");
		int pageBlock = (int)request.getAttribute("pageBlock");
		int startPage = (int)request.getAttribute("startPage"); //게시글이 하나도 없을때 0이다.
		int endPage = (int)request.getAttribute("endPage");
	%>
				
				<%for (int i = startPage; i<=endPage; i++) { %>
					<a href="noticeList.do?pageNum=<%=i%>"><%=i %></a>
				<% } %>
		</div>	




<div>
	<c:if test="${author == 'ADMIN' }"><!-- 접근권한  -->
	<button class="btn btn-primary btn-l" type="button" onclick="location.href='noticeForm.do'">글등록</button>
	</c:if>
</div>


<script type="text/javascript">
 	function noticeSerarch() {
		let key = $("#key").val();
;		let val = $("#val").val();
		//ajax function Call
		$.ajax({
			url : "noticeSearch.do",
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
			var row = $("<tr />").append(
					  $("<td />").text(item.boardNo),
					  $("<td />").text(item.boardId),
					  $("<td />").text(item.boardWriter),
					  $("<td />").text(item.boardTitle),
					  $("<td />").text(item.boardDate),
					  $("<td />").text(item.boardHit)
					  );
			tbody.append(row);
		});
		
		$("table").append(tbody);
	}
	
/* 	function noticeSerarch(){
		const ajax = new XMLHttpRequest();
		let key = document.getElementById('key').value;
		let val = document.getElementById('val').value;
		const url = "noticeSearch.do";
		const data = {"key" : key,"val" : val};
		ajax.onload = function(){
			if(ajax.status >= 200 && ajax.status < 300){
				jsonHtmlConvert(ajax.response);
			}else {
				errorCallback(new Error(ajax.stautsText));
			}
		};
		
		ajax.onerror = errorCallback;
		ajax.open('POST',url,true);
		ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		ajax.responseType='json';
		ajax.send(Object.keys(data).map(key => key+"="+data[key]).join('&')); //   
	} */
	
	function errorCallback(err){
		console.log('error : '+err.message);
	}
	
	function delNotice(obj){	
		let row = $(obj).parent().parent().get(0);
		let td = row.cells[0];
		let id = $(td).html();		
		
			const xhr = new XMLHttpRequest();
		const url = "NoticeDelete.do?id="+id;
		xhr.onload = function(){
			if(xhr.status >= 200 && xhr.status < 300){
				if(xhr.response == 1) {
					alert("데이터가 삭제되었습니다.");
					$(row).remove();
				}else {
					alert("삭제 할 수 없습니다.");
				};
			}else {
				errorCallback(new Error(xhr.stautsText));
			}
		};
	
		xhr.open('GET',url);
		xhr.send(); 
	}
	
	function noticeSelect(id) {  //get방식 안전하지 않음
		location.href='noticeSelect.do?id='+id;			
	}
</script> 
</body>
</html>