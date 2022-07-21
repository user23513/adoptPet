<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>봉사활동후기등록폼</title>
<link href="css/adoptpet.css" rel="stylesheet" /> 
</head>
<body>
 <section class="page-section" id="contact">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6 text-center">
                        <h2 class="mt-0">봉사 후기 등록 글쓰기!!</h2>
                        <hr class="divider" />
                    </div>
                </div>
                <div class="row gx-4 gx-lg-5 justify-content-center mb-5">
                    <div class="col-lg-6">
        	
                <form id="contactForm" action="주소적기" method="post">
                    <!-- Name input-->
                    <div class="form-floating mb-3">
                        <input class="form-control" type=“text” id="boardWriter" name="boardWriter" data-sb-validations="required" />
                        <label for="name">작성자</label>
                    </div>


 <div class="form-floating mb-3">
                        <input class="form-control" type=“text” id="boardTitle" name="boardTitle"  data-sb-validations="required" />
                        <label for="name">제목</label>
<div class="invalid-feedback" data-sb-feedback="message:required">제목을 입력하세요.</div>
                    </div>

            
                    <!-- Message input-->
                    <div class="form-floating mb-3">
                        <textarea class="form-control" id="boardContent" name="boardContect" type="text" placeholder=“여기에 내용을 입력하세요 …” style="height: 10rem" data-sb-validations="required"></textarea>
                        <label for="message">내용</label>
                        <div class="invalid-feedback" data-sb-feedback="message:required">내용을 입력하세요!</div>
                    </div>
                  
                   
                    <div class="d-none" id="submitSuccessMessage">
                        <div class="text-center mb-3">
                            <div class="fw-bolder">봉사 후기 등록 성공!</div>
                            <br />
                            <a href=“봉사후기 게시판으로 가는 주소넣기”>봉사 후기 게시판으로 가기</a>
                        </div>
                    </div>
                    <!-- Submit error message-->
                    <!---->
                    <!-- This is what your users will see when there is-->
                    <!-- an error submitting the form-->
                    <div class="d-none" id="submitErrorMessage"><div class="text-center text-danger mb-3">글 등록에 실패했습니다!</div></div>
                    
                    
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
		<div><h1>봉사후기등록</h1></div>
		<div>
			<form id="vFrm" action="volReviewInsert.do" method="post" >
				<div>
					<table border="1">
						<th width="150">작성자</th>
						<td width="200">
							<input type="text" id="boardWriter" name="boardWriter" required>
						<!-- id-javascript  name-java -->
						</td>
						</tr>
						<tr>
							<th>제목</th>
							<td colspan="3"><input type="text" id="boardTitle" name="boardTitle" size="73" required></td>
						</tr>
						<tr>
							<th>내용</th>
							<td colspan="3"><textarea rows="6" cols="75" id="boardContent" name="boardContect" required></textarea></td>
						</tr>

					</table>


				</div>
				<br>
				<div>
					<input type="submit" value="저장">&nbsp;&nbsp;&nbsp; <input type="reset" value="취소">
				</div>
			</form>
		</div>
</div>		
</body>
</html>