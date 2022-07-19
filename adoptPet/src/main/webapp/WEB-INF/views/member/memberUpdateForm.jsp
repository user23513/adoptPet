<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 
 
 <form action="memberUpdate.do" method="post">--%>
 	<input type="text" value="${member.memberId}" id="memberId" name="memberId" readonly="readonly"><br>
	<input type="password" value="${member.memberPassword}" readonly="readonly"><button>비밀번호수정</button><br>
	<input type="text" value="${member.memberName}" id="memberName" name="memberName"><br>
	<input type="text" value="${member.memberTel}" id="memberTel" name="memberTel"><br>
	<input type="text" value="${member.memberEmail}" id="memberEmail" name="memberEmail"><br>
	<input type="text" value="${member.memberJob}" id="memberJob" name="memberJob"><br>
	<input type="text" value="${member.memberAuthor}" id="memberAuthor" name="memberAuthor"><br>
	<button type="submit" id="modify" onclick="updateMember()">수정</button>
	<button onclick="location.href='main.do'">취소</button>
<%-- </form>--%>
<script type="text/javascript">

let btn = document.getElementById('modify');
let id = document.getElementById('memberId').value;
let job = document.getElementById('memberJob').value;
let tel = document.getElementById('memberTel').value;
let email = document.getElementById('memberEmail').value;
console.log(id+job+tel+email)
function updateMember(){
	btn.addEventListener('click',function(e){
		//e.preventDefault();
		fetch('memberUpdate.do',{
			method:'post',
			headers: {
				'Content-type': 'application/x-www-form-urlencoded'
			},
			body:"memberId="+id+"&memberJob="+job+"&memberTel="+tel+"&memberEmail"+email
		}).then(function(result){
			return result.json();
		}).then(function(result) {
			console.log(result);
			alert('수정완료')
			location.href="memberMyPage.do"
		})
		.catch(function(err) {
			console.error(err);
			alert('수정완료')
			location.href="memberMyPage.do"
		})
	})
}
</script>	
</body>
</html>