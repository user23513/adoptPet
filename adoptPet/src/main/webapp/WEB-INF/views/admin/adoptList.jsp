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
<link rel="stylesheet" href="css/adoptpet.css">
</head>
<body>

<div class="AdoptDiv">
    <div class="row gx-4 gx-lg-5 justify-content-center">
        <div class="col-lg-8 col-xl-6 text-center">
            <h2 class="mt-0">ADOPT PET LIST</h2>
            <hr class="divider" />
        </div>
    </div>

<div>                     
	<form id="frm" action="adoptStateSearch.do" method="post">
	<select id="val" name="val">
		  <option value="입양">전체</option>
		  <option value="입양승인대기">입양승인대기</option>
		  <option value="입양승인완료">입양승인완료</option>
		  <option value="입양승인불가">입양승인불가</option>
	</select>&nbsp;
		<input type="hidden" id="key" name="key" value="adopt_subscription_ok">
		<input type="submit" class="btn btn-primary btn-l" value="검색">
		 <!-- onclick="adoptStateSearch()" -->
	</form>	
</div>
<br>
<div>
<table border="1">
	<thead>
		<tr>
			<th>회원아이디</th>  
			<th>동물아이디</th>
			<th>상태</th>
			<th>이유</th>
			<th>상태변경</th>
			<th>상세보기</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty adoptList }">
				<c:forEach var="list" items="${adoptList }">
					<tr>
						<td><a href="memberOnePage.do?memberId=${list.memberId }" >${list.memberId }</a></td>
						<td><a href="petListView2.do?petAddNo=${list.petAddNo }">${list.petAddNo }</a></td>
						<td id="${list.memberId }">${list.adoptSubscriptionOk }</td>
						<td width="300px" >${list.adoptSubscriptionReason }</td>
						<td><input type="button" class="btn btn-primary btn-l" value="수정" id="modify" onclick="modifyFnc('${list.memberId }','${list.petAddNo }')"></td>
					    <td>
 						<form action="adoptOneView.do">
					    <input type="hidden" value="${list.adoptSubscriptionReason }" id="adoptSubscriptionReason" name="adoptSubscriptionReason">
					    <input type="hidden" value="${list.adoptSubscriptionOk }" id="adoptSubscriptionOk" name="adoptSubscriptionOk">
					    <input type="hidden" value="${list.memberId }" id="memberId" name="memberId">
					    <input type="hidden" value="${list.petAddNo }" id="petAddNo" name="petAddNo">
					    <input type="submit" class="btn btn-primary btn-l" value="상세보기">
					    </form> 	
				    	</td> 
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6" align="center">입양신청내역이 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>


</div>

<div style="text-align:center;">
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
function modifyFnc(memId,petNo){
	let modifyTd = document.getElementById(memId);
	modifyTd.innerText="";

	let select = document.createElement('select');
	select.setAttribute('id','sel');
	let adoptState = ['선택하기','입양승인대기','입양승인완료','입양승인불가'];
	let option = "";

	adoptState.forEach(elem => {
		select.innerHTML += '<option value='+elem+'>'+elem+'</option>'
	});

	select.append(option);
	modifyTd.append(select);
	let btn = modifyTd.nextElementSibling.nextElementSibling.firstElementChild
	btn.value="수정완료";
	
	let opVal = "";
	$("#sel").change(function(){
	opVal = $(this).val();
	})

	btn.addEventListener('click',function(e){
		e.preventDefault();
		fetch('adoptListUpdate.do',{
			method:'post',
			headers: {
				'Content-type': 'application/x-www-form-urlencoded'
			},
			body:"adoptSubscriptionOk="+opVal+"&memberId="+memId+"&petAddNo="+petNo
		})
		.then(function (result){
			return result.json();
		})
		.then(function (result){
			console.log(result);
			alert('수정완료');
			location.href="adoptList.do"
		})
		.catch(function(err){
			console.error(err);
			alert('수정완료');
			location.href="adoptList.do"
		})
	})
}
</script>
</body>
</html>