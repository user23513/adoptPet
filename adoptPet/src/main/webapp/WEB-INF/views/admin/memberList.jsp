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
회원리스트
<table border="1">
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>email</th>
			<th>성별</th>
			<th>직업</th>
			<th>전화번호</th>
			<th>권한</th>
			<th>수정</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty memberList }">
				<c:forEach var="list" items="${memberList }">
					<tr>
						<td>${list.memberId }</td>
						<td>${list.memberName }</td>
						<td>${list.memberEmail }</td>
						<td>${list.memberGender }</td>
						<td>${list.memberJob }</td>
						<td>${list.memberTel }</td>
						<td>${list.memberAuthor }</td>
						<td id="${list.memberId }">
						<input type="button" value="수정" id="modify" onclick="modifyFnc('${list.memberId }')" name="${list.memberId }">
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="8" align="center">회원이 없습니다.</td>
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
	<a href="memberList.do?pageNum=<%=i%>"><%=i%></a>
	<%
	}
	%>
</div>
</div>
</body>
<script type="text/javascript">
let id = document.getElementsByTagName('td')[0].innerText;
let nm = document.getElementsByTagName('td')[1].innerText;
let em = document.getElementsByTagName('td')[2].innerText;
let gd = document.getElementsByTagName('td')[3].innerText;
let job = document.getElementsByTagName('td')[4].innerText;
let tel = document.getElementsByTagName('td')[5].innerText;
let athr = document.getElementsByTagName('td')[6].innerText;


function modifyFnc(memId){
	console.log(memId);
	let modifyTd = document.getElementById(memId);
	console.log(modifyTd.previousElementSibling.innerText)
	console.log(modifyTd.firstElementChild )
	modifyTd.previousElementSibling.innerText="";
	
	let td = modifyTd.previousElementSibling
	let select =  document.createElement('select');
	select.setAttribute('id','sel');

	let author = ['선택하기','ADMIN','USER'];

	
	let option = "";
	author.forEach(ath => {
		select.innerHTML += '<option value='+ath+'>'+ ath +'</option>';
	})
	select.append(option)
	td.append(select)
	
	let btn = modifyTd.firstElementChild  
	btn.value='수정완료';
	
	let opVal = "";
	$("#sel").change(function(){
		opVal = $(this).val();
		console.log($(this).val())
	})
	
	
	btn.addEventListener('click',function(e){
		e.preventDefault();
		fetch('updateMemberList.do',{
			method:'post',
			headers: {
				'Content-type': 'application/x-www-form-urlencoded'
			},
			body:"memberId="+memId+"&memberAuthor="+opVal
		})
		.then(function(result) {
			return result.json();
		})
		.then(function(result) {
			console.log(result);
			alert('수정완료')
			location.href="memberList.do"
		})
		.catch(function(err) {
			console.error(err);
			alert('수정완료')
			location.href="memberList.do"
		})
	})
	

	
}

</script>
</html>