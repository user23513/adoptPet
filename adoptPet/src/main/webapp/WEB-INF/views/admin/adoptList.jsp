<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js" /></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
<link rel="stylesheet" href="/css/bootstrap.css">
</head>
<body>
<div align="center">
	<div>
		<form id="frm">
		<select id="val" name="val">
			  <option value="입양">전체</option>
			  <option value="입양승인대기">입양승인대기</option>
			  <option value="입양승인완료">입양승인완료</option>
			  <option value="입양승인불가">입양승인불가</option>
		</select>&nbsp;
			<input type="hidden" id="key" name="key" value="adopt_subscription_ok">
			<input type="button" value="검색" onclick="adoptStateSerarch()">
		</form>	
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>회원아이디</th>  
				<th>동물아이디</th>
				<th>상태</th>
				<th>이유</th>
				<th>승인여부변경</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty adoptList }">
					<c:forEach var="list" items="${adoptList }">
						<tr>
							<td>${list.memberId }</td>
							<td>${list.petListNo }</td>
							<td>${list.adoptSubscriptionOk }</td>
							<td>${list.adoptSubscriptionReason }</td>
							<td id=""><input type="button" value="수정" id="modify"></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5" align="center">입양신청내역이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
<div>
	<%
	int pageCount = (int) request.getAttribute("pageCount");
	int pageBlock = (int) request.getAttribute("pageBlock");
	int startPage = (int) request.getAttribute("startPage");
	int endPage = (int) request.getAttribute("endPage");

	for (int i = startPage; i <= endPage; i++) {
	%>
	<a href="adoptList.do?pageNum=<%=i%>"><%=i%></a>
	<%
	}
	%>
</div>
</div>
<script type="text/javascript">
function jsonHtmlConvert(data) {
	$('tbody').remove();
	var tbody = $("<tbody />");
	$.each(data, function(index, item){
		var row = $("<tr />").append(
					$("<td />").text(item.memberId),
					$("<td />").text(item.petListNo),
					$("<td />").text(item.adoptSubscriptionOk),
					$("<td />").text(item.adoptSubscriptionReason),
					$("<td />").append($("<button onclick=updateAdoptState(this) /> ").text("수정"))
				);
		tbody.append(row);
	});
	$('table').append(tbody);
}

function adoptStateSerarch(){
	const ajax = new XMLHttpRequest();
	let key = document.getElementById('key').value;
	let val = document.getElementById('val').value;
	const url = "adoptStateSearch.do";
	const data = {"key" : key,"val" : val};
	ajax.onload = function(){
		if(ajax.status >= 200 && ajax.status < 300){
			jsonHtmlConvert(ajax.response);
		}else {
			errorCallback(new Error(ajax.stautsText));
			console.log('error : '+ajax.stautsText.message);
		}
	};
	
	ajax.onerror = errorCallback;
	ajax.open('POST',url,true);
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	ajax.responseType='json';
	ajax.send(Object.keys(data).map(key => key+"="+data[key]).join('&')); //  

}


function errorCallback(err){
	console.log('error : '+err.message);
}

</script>
</body>
</html>