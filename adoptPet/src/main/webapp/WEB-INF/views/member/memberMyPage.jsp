<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"/></script>
<link href="css/adoptpet.css" rel="stylesheet" /> 
</head>
<body>
<div id="myPageDiv">
	<p>${member.memberId}</p>
	<p>${member.memberPassword}</p>
	<p>${member.memberName }</p>
	<p>${member.memberTel}</p>
	<p>${member.memberEmail }</p>
	<p>${member.memberJob}</p>
	<p>${member.memberGender }</p>
	<p>${member.memberAuthor }</p>
	<br>
	<button class="btn btn-primary btn-xl" onclick="location.href='memberUpdateForm.do'">내정보수정하기</button>
	<form id="frm" action="memberDelete.do" method="post">
		<input type="hidden" id="memberId" name="memberId" value="${member.memberId}">
		<button class="btn btn-primary btn-xl" type="button" onclick="memberDeleteCall()">탈퇴하기</button>
	</form>
</div>	
<script type="text/javascript">

function memberDeleteCall(){
	 if(confirm("정말 탈퇴 하시겠습니까?\n(※ 작성한 글과 댓글 모두 삭제 됩니다.)") == true){		

		//frm.submit();
		this.addEventListener('click',function(e){
			e.preventDefault();
			fetch('memberDelete.do',{
				method:'post',
				headers: {
					'Content-type': 'application/x-www-form-urlencoded'
				},
				body:"memberId="+memId
			}).then(function(result) {
				return result.json();
			})
			.then(function(result) {
				console.log(result);
				alert('탈퇴완료')
				location.href="main.do"
			})
			.catch(function(err) {
				console.error(err);
				alert('탈퇴완료')
				location.href="main.do"
			})
		})

	 }else{
		alert("탈퇴를 취소 했습니다.");
	 }
	}
</script>	
</body>
</html>