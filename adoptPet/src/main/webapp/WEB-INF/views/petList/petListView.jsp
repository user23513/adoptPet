<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
  background-color: #04AA6D;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

button:hover {
  opacity:1;
}

/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color: #f44336;
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
</style>
</head>
<body>
	<div align="center">
		<table border="1">
				<tr>
					<th width="500">ëë¬¼ì í</th>
					<td width="500">${vo.petListType}</td>
					<th width="500">ììì¬ë¶</th>
					<td width="500">${vo.petListState}</td>
				</tr>
				<tr>
					<th>ìì±ì</th>
					<td>${vo.petListWriter}</td>
					<th>ì ëª©</th>
					<td>${vo.petListTitle}</td>
				</tr>
				<tr>
					<td colspan="4">
						<div style="text-align : center;">
							<c:forEach var="filePath" items="${filePathList}">
								<c:if test="${not empty filePath}">
									<img align="middle" width="700" height="500" src="${filePath}"><br>
								</c:if> 
							</c:forEach>
						</div>
						<div>${vo.petListContent}</div> 
					</td>
				</tr>
		</table>
		<div><input type="hidden" id="petAddNo" name="petAddNo" value="${vo.petAddNo}"> </div>
		<div>
			<button type="button" style="float: left;" onclick="location.href = 'petList.do'">ëª©ë¡</button>
<%-- 			<input type="hidden" value="${ëë¬¼ë²í¸ }"> --%>
			<!-- ì¸ì ìì´ëê°ì´ ì´ëë¯¼ì¼ë ì¤ì  -->
			<!-- ììì ì²­ë²í¼ì´ ììì ì²­í ì ì ì´ ìì¼ë©´ ë³´ì´ì§ ìê² -->
			<c:if test="${check == true}">
				<button onclick="document.getElementById('id01').style.display='block'" style="width:auto;">ììì ì²­</button>
			</c:if>
			<button type="button" style="float: right;" onclick="petListDeleteFnc(${vo.petListNo})">ì­ì </button>
			<button type="button" style="float: right;" onclick="location.href = 'petListUpdateForm.do?petListNo=${vo.petListNo}'">ìì </button>
		</div>
	</div>
	
	<!-- -============================================== -->

<div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="modal-content" action="adoptSubScription.do">
    <div class="container">
      <h1>ììì ì²­</h1>
      <p>Please fill in this form to create an account.</p>
      <hr>
      <label for="memberId"><b>íììì´ë</b></label>
      <input type="text" name="memberId" value="lee" required>

      <label for="petAddNo"><b>ëë¬¼ë²í¸</b></label>
      <input type="text" name="petAddNo" value="${vo.petAddNo}" required>

      <label for="adoptSubscriptionOk"><b>ììì¹ì¸ì¬ë¶</b></label>
      <input type="text" name="adoptSubscriptionOk" value="${vo.petListState}" required>
      
      <label for="adoptSubscriptionReason"><b>ììì¬ì </b></label>
      <input type="text" name="adoptSubscriptionReason" required>
      

      <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

      <div class="clearfix">
        <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">ì·¨ì</button>
        <button type="button" class="signupbtn" onclick="adoptSubFnc();">ì ì²­</button>
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

	
	
	</script>
</body>
</html>