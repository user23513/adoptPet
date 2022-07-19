<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js" /></script>
</head>
<body>
<div align="center">
	<div>
		<form id="frm">
		<select id="val" name="val">
			  <option value="봉사">전체</option>
			  <option value="봉사참여승인대기">봉사참여승인대기</option>
			  <option value="봉사참여승인완료">봉사참여승인완료</option>
			  <option value="봉사참여승인불가">봉사참여승인불가</option>
		</select>&nbsp;
			<input type="hidden" id="key" name="key" value="volunteer_subscription_ok">
			<input type="button" value="검색" onclick="volunteerStateSerarch()">
		</form>	
	</div>
<table border="1">
		<thead>
			<tr>
				<th>회원아이디</th>
				<th>봉사신청번호</th>
				<th>상태</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty volunteerList }">
					<c:forEach var="list" items="${volunteerList }">
						<tr>
							<td>${list.memberId }</td>
							<td>${list.calendarNo }</td>
							<td>${list.volunteerSubscriptionOk }</td>
							<td id=""><input type="button" value="수정" id="modify"></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4" align="center">봉사신청내역이 없습니다.</td>
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
	<a href="volunteerList.do?pageNum=<%=i%>"><%=i%></a>
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
					$("<td />").text(item.calendarNo),
					$("<td />").text(item.volunteerSubscriptionOk),
					$("<td />").append($("<button onclick=updateVolunteerState(this) /> ").text("수정"))
				);
		tbody.append(row);
	});
	$('table').append(tbody);
}

function volunteerStateSerarch(){
	const ajax = new XMLHttpRequest();
	let key = document.getElementById('key').value;
	let val = document.getElementById('val').value;
	const url = "volunteerStateSearch.do";
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