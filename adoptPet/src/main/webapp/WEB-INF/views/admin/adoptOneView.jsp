<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
입양신청 상세보기
<table border="1">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>직업</th>
			<th>성별</th>
			<th>전화번호</th>
		</tr>
		<tr>
			<td>${ vo.memberId }</td>
			<td>${mvo.memberName }</td>
			<td>${mvo.memberJob }</td>
			<td>${mvo.memberGender }</td>
			<td>${mvo.memberTel }</td>
		</tr>
		<tr>
			<th>동물번호</th>
			<th>동물이름</th>
			<th>동물건강상태</th>
			<th>동물성별</th>
			<th>동물몸무게</th>
		</tr>
		<tr>
			<td>${vo.petAddNo }</td>
			<td>${pvo.petAddName }</td>
			<td>${pvo.petAddHealth }</td>
			<td>${pvo.petAddGender }</td>
			<td>${pvo.petAddWeight }</td>
		</tr>
		<tr>
			<th colspan="5">이유</th>
		</tr>
		<tr>
			<td colspan="5">${vo.adoptSubscriptionReason }</td>
		</tr>
		<tr>
			<th colspan="5">입양상태</th>
		</tr>
		<tr>
			<td colspan="5">${vo.adoptSubscriptionOk }</td>
		</tr>
</table>
<button onclick="location.href='adoptList.do'">뒤로가기</button>
</div>
</body>
</html>