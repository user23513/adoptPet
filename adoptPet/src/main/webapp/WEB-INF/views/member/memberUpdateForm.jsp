<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/adoptpet.css" rel="stylesheet" /> 
<link href="css/styles.css" rel="stylesheet" />  
</head>
<body>

    <div class="row gx-4 gx-lg-5 justify-content-center">
        <div class="col-lg-8 col-xl-6 text-center">
            <h2 class="mt-0">MYPAGE</h2>
            <hr class="divider" />
        </div>
    </div>


<div id="memUpdateDiv">





	<input type="text" value="${member.memberId}" id="memberId" name="memberId" readonly="readonly" class="form-control"><br>
	<input type="password" value="${member.memberPassword}" readonly="readonly" class="form-control"><br>
	<input type="text" value="${member.memberName}" id="memberName" name="memberName" class="form-control"><br>
	<input type="text" value="${member.memberTel}" id="memberTel" name="memberTel" class="form-control"><br>
	<input type="text" value="${member.memberEmail}" id="memberEmail" name="memberEmail" class="form-control"><br>
	<input type="text" value="${member.memberJob}" id="memberJob" name="memberJob" class="form-control"><br>
	<input type="text" value="${member.memberAuthor}" id="memberAuthor" name="memberAuthor" readonly="readonly" class="form-control"><br>
	<div>
	<button type="button" id="modify" class="btn btn-primary btn-xl">수정</button>
	<button onclick="location.href='main.do'" class="btn btn-primary btn-xl">취소</button>
</div>

</div>

<script type="text/javascript">

let btn = document.getElementById('modify');
//onclick="updateMember()" 
//function updateMember(){
	btn.addEventListener('click',function(e){
		e.preventDefault();
		
		let id = document.getElementById('memberId').value;
		let job = document.getElementById('memberJob').value;
		let tel = document.getElementById('memberTel').value;
		let email = document.getElementById('memberEmail').value;

		fetch('memberUpdate.do',{
			method:'post',
			headers: {
				'Content-type': 'application/x-www-form-urlencoded'
			},
			body:"memberId="+id+"&memberJob="+job+"&memberTel="+tel+"&memberEmail="+email
		}).then(function(result){
			return result.json();
		}).then(function(result) {
			console.log(result);
			if(result == "1"){
				alert('수정완료')
				location.href="memberMyPage.do"
			}
			
		})
		.catch(function(err) {
			console.error(err);
			alert('수정실패')
//			location.href="memberMyPage.do"
		})
	})
//}
</script>	
</body>	
</html>