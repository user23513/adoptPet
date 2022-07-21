<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글 작성/등록</title>
</head>
<body>
<section class="page-section" id="contact">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6 text-center">
                        <h2 class="mt-0">문의글 등록!!</h2>
                        <hr class="divider" />
                    </div>
                </div>
                <div class="row gx-4 gx-lg-5 justify-content-center mb-5">
                    <div class="col-lg-6">
        		
                      
                <form id="contactForm" action=“주소적기” method="post">
                    <!-- Name input-->
                    <div class="form-floating mb-3">
                        <input class="form-control"id="boardTitle" name="boardTitle"" type="text" placeholder="Title please...." data-sb-validations="required" />
                        <label for="boardTitle">제목</label>
                        <div class="invalid-feedback" data-sb-feedback="name:required">제목을 입력하세요.</div>
                    </div>

                     <div class="form-floating mb-3">
                        <input class="form-control" id="name" type="date"  data-sb-validations="required" />
                        <label for="name">작성일자</label>
                    </div>

                    <!-- Message input-->
                    <div class="form-floating mb-3">
                        <textarea class="form-control" id="boardContent" type="text" placeholder=“내용을 입력하세요…” style="height: 10rem" data-sb-validations="required"></textarea>
                        <label for="boardContent">내용</label>
                        <div class="invalid-feedback" data-sb-feedback="message:required">내용을 입력하세요 !.</div>
                    </div>
                                      <!-- Submit success message-->
                    <!---->
                    <!-- This is what your users will see when the form-->
                    <!-- has successfully submitted-->
                    <div class="d-none" id="submitSuccessMessage">
                        <div class="text-center mb-3">
                            <div class="fw-bolder">문의글 올리기 성공!</div>
                            <br />
                            <a href=“문의게시판 주소 적기”>문의 게시판 페이지로 가기</a>
                        </div>
                    </div>
                    <!-- Submit error message-->
                    <!---->
                    <!-- This is what your users will see when there is-->
                    <!-- an error submitting the form-->
                    <div class="d-none" id="submitErrorMessage"><div class="text-center text-danger mb-3">글 올리기에 실패했습니다!</div></div>
                    
                    
                    <!-- Submit Button-->
                            <div class="d-grid">
<button class="btn btn-primary btn-xl" id="submitButton" type="submit">저장</button>&nbsp;
<button class="btn btn-primary btn-xl" id="submitButton" type=“reset”>취소</button>

</div>
                        </form>
                    </div>
                </div>
                </div>
                </section>







<div align="center">
	<div><h1>문의글 작성/등록</h1></div>
	<div>
		<form id="frm" action="qnaBoardInsert.do" method="post" enctype="form-data">
			<div>
				<table border="1">
					<tr>
						<%-- <th width="150">작성자</th>
						<td width="200">${board.boardWriter}</td> --%>
							
					<tr>
						<th>제목</th>
						<td colspan="3">
							<input type="text" id="boardTitle" name="boardTitle" size="73">
						</td>
					</tr>
					<tr>
						<th width="150">작성일자</th>
						<td width="200">
							<input type="date" id="boardDate" name="boardDate"> 
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">
							<textarea rows="6" cols="75" id="boardContent" name="boardContect"></textarea>
						</td>
					</tr>

				</table>
			</div><br>
			<div>
				<input type="submit" value="저장">&nbsp;&nbsp;&nbsp; 
				<input type="reset" value="취소">
			</div>
		</form>
	</div>
</div>
</body>
</html>