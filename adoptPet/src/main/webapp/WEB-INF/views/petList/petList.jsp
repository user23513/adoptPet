<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입양동물 소개 게시판</title>
<script src="js/jquery-3.6.0.min.js"></script> <!-- 제이쿼리 라이브러리 쓰겠다. -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/adoptpet.css" rel="stylesheet" />   
<style>

/* #cardList { */

/*   position: absolute;  */
/*   top: 0;  */
/*   right: 0;  */
/*   padding: 20px;  */
/* } */


	.card {
	  float:left;
	  margin:15px;
	  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
	  transition: 0.3s;
	  width: 370px;
	  height: 350px;
	}
	
	.card:hover {
	  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
	}
	
	.container {
	  padding: 2px 16px;
	}
	

/* ===== */
	#search {
		display: inline-block;
		float: right;
	}
	
	#heartBtn {
		padding: 0;
		border: none;
		background: none;
	}
	
	
</style>

</head>
<body>


<!-- =========== -->
	
    	
    	
		
		
		<div id="list">
		<section class="notice">
  		<div class="page-title">
        	<div class="container">
            	<h3>입양동물 소개</h3>
            	<c:if test="${author eq 'ADMIN' }">
            	<div id="search" align="right">
			<form id="frm" action="petListSearch.do" method="post">
				<select id="key" name="key" >
					<option value="pet_list_title">제목</option>
					<option value="pet_list_content">내용</option>
				</select>&nbsp;
				<input type="text" id="val" name="val" placeholder="검색어를 입력해주세요.">&nbsp;&nbsp;
				<input type="submit" value="검색"  >
			</form>
		</div>
			<button type="button" class="btn btn-primary btn-xl"  style="clear:both; margin-bottom: 20px;" onclick="location.href='petAddList.do'">입양등록</button>
		</c:if>
        	</div>
    	</div>

<div id="cardList">
<c:forEach var="list" items="${petList}">
		<div class="card">
			<c:if test="${empty list.filesPath1}">
  			<a href="petListView.do?petListNo=${list.petListNo}&petAddNo=${list.petAddNo}">
  			<img src="fileup/noImage.png" class="avatar" alt="Avatar" style="width:100%"></a>
  			</c:if>
  			<c:if test="${not empty list.filesPath1}">
  			<a href="petListView.do?petListNo=${list.petListNo}&petAddNo=${list.petAddNo}">
  			<img src="fileup/${list.filesPath1}" class="avatar" alt="Avatar" style="width:100%"></a>
  			</c:if>
  			 <div class="container">
   			 	<h5>[${list.petListState}]&nbsp;<b><a href="petListView.do?petListNo=${list.petListNo}&petAddNo=${list.petAddNo}">${list.petListTitle}</a></b></h5> 
   			 	<p>
   			 		<div>
						<button id="heartBtn" type="button" class="btn btn-primary btn-xl" onclick="heartCheckFnc(${list.petListNo},${list.heartNum })">
							<c:choose>
								<c:when test="${list.heartCheck == 1}">
									<img id="img${list.petListNo}" width="20" height="20" src="images/redHeart.png">
								</c:when>
								<c:otherwise>
									<img id="img${list.petListNo}" width="20" height="20" src="images/whiteHeart.png">
								</c:otherwise>
							</c:choose>
						</button>
						<span id="heartNum${list.petListNo}">${list.heartNum }</span> 
					</div>
   			 	</p> 
 			 </div>
		</div>
</c:forEach>
</div>
		
		
	</div>
	<div id="paging" style="clear:both; text-align: center;">
	<% 	int pageCount = (int)request.getAttribute("pageCount");
		int pageBlock = (int)request.getAttribute("pageBlock");
		int startPage = (int)request.getAttribute("startPage"); //게시글이 하나도 없을때 0이다.
		int endPage = (int)request.getAttribute("endPage");
	%>
				
				<%for (int i = startPage; i<=endPage; i++) { %>
					<a href="petList.do?pageNum=<%=i%>"><%=i %></a>
				<% } %>
		</div>	

	
	<script>
		//하트버튼 눌렀을때
		function heartCheckFnc(petListNo,heartNum) {
			// get: url, post: 추가정보지정.
			fetch('heartCheck.do', {
				method: 'post',
				headers: {
					'Content-type': 'application/x-www-form-urlencoded'
				},  
				body: "petListNo="+petListNo
			})
				.then(function(result){
					return result.json();
				})
				.then(function(result){
					if(result.heartColor == 'Red'){
						document.getElementById('heartNum'+result.petListNo).innerHTML=result.heartCount;
						document.getElementById('img'+result.petListNo).src = 'images/redHeart.png';
					} else {
						document.getElementById('heartNum'+result.petListNo).innerHTML=result.heartCount;
						document.getElementById('img'+result.petListNo).src = 'images/whiteHeart.png';
					}
				})
				.catch(function(err){
					console.error(err);
				})
				}
		
	</script>

</body>
</html>