<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"></script> <!-- 제이쿼리 라이브러리 쓰겠다. -->
<style>
	
</style>
</head>
<body>
	<div align="center">
	<textarea rows="" cols=""></textarea>
		<div>
			<label for="memberId">회원아이디&nbsp;</label> <input type="text" id="memberId" name="memberId" value="lee" readonly>
		</div>		
		<div>
			<label for="petAddNo">입양동물번호&nbsp;</label> <input type="text" id="petAddNo" name="petAddNo" value="${petAddNo}" readonly>
		</div>
		<div>
			<label for="adoptSubscriptionOk">입양승인여부&nbsp;</label> <input type="text" id="adoptSubscriptionOk" name="adoptSubscriptionOk" value="입양승인대기" readonly>
		</div>
		<div>
			<label for="adoptSubscriptionReason">입양사유&nbsp;</label><textarea id="adoptSubscriptionReason" name="adoptSubscriptionReason" rows="6" cols="70"></textarea>
		</div>
		<div>
			<button type="button" onclick="adoptSubFnc()">신청</button>
		</div>
	</div>
	
	<script>
		function adoptSubFnc() {
			let memberId = $('#memberId').val();
			let petAddNo = $('#petAddNo').val();
			let adoptSubOk = $('#adoptSubscriptionOk').val();
			let adoptSubReason = $('#adoptSubscriptionReason').val();
			console.log(adoptSubReason)
			//ajax function Call
			$.ajax({
				url:"adoptSubScription.do",
				type:"post",
				data:{
					"memberId" : memberId,
					"petAddNo" : petAddNo,
					"adoptSubscriptionOk" : adoptSubOk,
					"adoptSubscriptionReason" : adoptSubReason
				},
				dataType:"json",
				success : function(result) {
					if(result == 1) {
						alert("신청되었습니다!");
						window.close();
					} else {
						alert("신청되지않았습니다!");
						window.close();
					}
				},
				error : function(error) {
					console.log(error);
				}
			})
		}
	</script>
	
</body>
</html>