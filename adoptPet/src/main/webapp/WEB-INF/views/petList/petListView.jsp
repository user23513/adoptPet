<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"></script> <!-- 제이쿼리 라이브러리 쓰겠다. -->
<link href="css/adoptpet.css" rel="stylesheet" />  
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

/* Add a background color when the inputs get focus */
input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for all buttons */
button {
  background-color: #f4623a;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100px;
  opacity: 0.9;
}

button:hover {
  opacity:1;
}

/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color: #ef9899;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn, .signupbtn {
  float: left;
  width: 50%;
}

/* Add padding to container elements */
.container {
  padding: 16px;
}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: #474e5d;
  padding-top: 50px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* Style the horizontal ruler */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}
 
/* The Close Button (x) */
.close {
  position: absolute;
  right: 35px;
  top: 15px;
  font-size: 40px;
  font-weight: bold;
  color: #f1f1f1;
}

.close:hover,
.close:focus {
  color: #f44336;
  cursor: pointer;
}

/* Clear floats */
.clearfix::after {
  content: "";
  clear: both;
  display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
  .cancelbtn, .signupbtn {
     width: 100%;
  }
}

img {
	margin-bottom: 30px;
}

tr:nth-child(odd){background-color: #f2f2f2;}
tr:nth-child(even){background-color: white;}

</style>
</head>
<body>
	<div align="center">
		<section class="notice">
  		<div class="page-title">
        	<div class="container">
            	<h3>[${vo.petListState}]&nbsp; ${vo.petListTitle}</h3>
        	</div>
    	</div>
		<table border="1">
				<tr>
					<th width="200">동물유형</th>
					<td width="200">${vo.petListType}</td>
					<th width="200">입양여부</th>
					<td width="200">${vo.petListState}</td>
					<th width="200">작성자</th>
					<td width="200">${vo.petListWriter}</td>
				</tr>
				<tr>
					<td colspan="6">
						<div style="text-align : center;">
							<c:forEach var="filePath" items="${filePathList}">
								<c:if test="${not empty filePath}">
									<img align="middle" width="650px" height="650px" src="${filePath}"><br>
								</c:if> 
							</c:forEach>
						</div>
						<div>${vo.petListContent}</div> 
					</td>
				</tr>
		</table>
		<div><input type="hidden" name="petAddNo" value="${vo.petAddNo}"> </div>
		<div>
			<button type="button" class="btn btn-primary btn-xl" style="float: left; margin-top: 20px" onclick="location.href = 'petList.do'">목록</button>
			<c:if test="${check == true}">
				<button type="button" class="btn btn-primary btn-xl" onclick="document.getElementById('id01').style.display='block'" style="width:auto; float: right; margin-top: 20px">입양신청</button>
			</c:if>

			<c:if test="${author eq 'ADMIN'}">
					<button type="button" class="btn btn-primary btn-xl" style="float: right;" onclick="petListDeleteFnc(${vo.petListNo})">삭제</button>
					<button type="button" class="btn btn-primary btn-xl" style="float: right;" onclick="location.href = 'petListUpdateForm.do?petListNo=${vo.petListNo}'">수정</button>
			</c:if>

		</div>
	</div>

<div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="modal-content" action="adoptSubScription.do">
    <div class="container">
      <h1>입양 신청</h1>
      <p>Please fill in this form to create an account.</p>
      <hr>
      <label for="memberId"><b>신청자 아이디</b></label>
      <input type="text" id="memberId" name="memberId" value="lee" required>

      <label for="petAddNo"><b>동물 번호</b></label>
      <input type="text" id="petAddNo" name="petAddNo" value="${vo.petAddNo}" required>

	  <label for="adoptSubscriptionOk"><b>입양 승인여부</b></label>
      <input type="text" id="adoptSubscriptionOk" name="adoptSubscriptionOk" value="${vo.petListState}" required>
      
      <label for="adoptSubscriptionReason"><b>입양 사유</b></label>
      <input type="text" id="adoptSubscriptionReason" name="adoptSubscriptionReason" required>
      

      <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

      <div class="clearfix">
        <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">취소</button>
        <button type="button" class="signupbtn" onclick="adoptSubFnc();">신청</button>
      </div>
    </div>
  </form>
</div>

	<script>
	     
	 function petListDeleteFnc(petListNo) {
	      fetch('petListDelete.do',{
	         method: 'post',
	         headers: {
	            'Content-type': 'application/x-www-form-urlencoded'
	         },  
	         body:"petListNo="+petListNo
	      })
	      .then(function(result){
	               return result.json();
	            })
	      .then(function(result){
	         if(result==1) {
	            alert("삭제가 완료되었습니다!");
	            location.href = "petList.do";
	         }else{
	            alert("삭제가 되지않았습니다!");
	            location.href = "petList.do";
	         }
	      })
	      .catch(function(err){
	               console.error(err);
	         })
	   }
	 
		function adoptSubFnc() {
			let memberId = $('#memberId').val();
			let petAddNo = $('#petAddNo').val();
			let adoptSubOk = $('#adoptSubscriptionOk').val();
			let adoptSubReason = $('#adoptSubscriptionReason').val();
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
						document.getElementById('id01').style.display='none';
					} else {
						alert("신청되지않았습니다!");
						document.getElementById('id01').style.display='none';
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