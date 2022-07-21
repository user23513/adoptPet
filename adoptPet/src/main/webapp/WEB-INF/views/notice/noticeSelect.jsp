<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 상세페이지</title>
<link href="css/adoptpet.css" rel="stylesheet" />   
<style>
	table{
		border: 1px solid #e7e7e7;
		border-radius: 10px;
	}
	
	button {
		float: right;
}
	
</style>
</head>
<body>
<section class="notice">
  		<div class="page-title">
        	 <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6 text-center">
                        <h2 class="mt-0">NOTICE</h2>
                        <hr class="divider" />
                    </div>
                </div>
    	</div>
 <div>
	<table>
 		<tr>
 			<th width="100px" style="border: 1px solid #e7e7e7">글번호</th>
 			<td width="400px">${board.boardNo}</td>
			<th style="border: 1px solid #e7e7e7">작성일자</th>
			<td>${board.boardDate}</td>
		</tr>
		<tr>
			<th style="border: 1px solid #e7e7e7">제목</th>
			<td colspan="4">${board.boardTitle}</td>
		</tr>
		<tr>
			<th style="border: 1px solid #e7e7e7">내용</th>
			<td colspan="6" style="padding: 20px">${board.boardContent}</td>
		</tr>
	</table>
	<button type="submit" style="margin: 10px" class="btn btn-primary btn-xl">수정</button>
	<button type="submit" style="margin: 10px" class="btn btn-primary btn-xl">삭제</button>
 </div>
 
</body>
</html>