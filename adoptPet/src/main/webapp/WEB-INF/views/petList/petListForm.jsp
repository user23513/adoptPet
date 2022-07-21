<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입양소개게시판 등록</title>
</head>
<body>
	
	<section class="page-section" id="contact">
		<div class="container px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-lg-8 col-xl-6 text-center">
					<h2 class="mt-0">동물 소개글 등록</h2>
					<hr class="divider" />
				</div>
			</div>
			<div class="row gx-4 gx-lg-5 justify-content-center mb-5">
				<div class="col-lg-6">



					<form id="contactForm" action="petListInsert.do" method="post" enctype="multipart/form-data">
						<!-- Name input-->
						<div class="form-floating mb-3">
							<input class="form-control" type="text" id="petListType"
								name="petListType" value="${petAddVo.petAddType}" readonly/> <label for="petListType">동물유형</label>

						</div>

						<div class="form-floating mb-3">
							<input class="form-control" type="text" id="petListState"
								name="petListState" value="입양대기" readonly/> <label for="petListState">입양여부</label>

						</div>

						<input type="hidden" name="boardId" value="50">
						
						
						<div class="form-floating mb-3">
							<input class="form-control" type="text" id="petListWriter"
								name="petListWriter" value="관리자" readonly /> <label for="petListWriter">작성자</label>

						</div>
						
						<input type="hidden" name="petAddNo" value="${petAddVo.petAddNo }">
						
						<div class="form-floating mb-3">
							<input class="form-control" type="text" id="petListTitle"
								name="petListTitle" value="${petAddVo.petAddName}" readonly /> <label
								for="petListTitle">제목(동물이름)</label>
						</div>
						
						 <div class="form-floating mb-3">
	                        <textarea class="form-control" id="petListContent" name="petListContent" type="text" placeholder="Enter your content here..." style="height: 10rem" data-sb-validations="required"></textarea>
	                        <label for="message">내용</label>
	                        <div class="invalid-feedback" data-sb-feedback="message:required">Content is required.</div>
                   		 </div>
						
						
						<div class="form-floating mb-3">
                       		 <input class="form-control" id="files" name="file1" type="file" />
                       		 <input class="form-control" id="files" name="file2" type="file" />
                       		 <input class="form-control" id="files" name="file3" type="file" />
                   		</div>

						
						<!-- Submit success message-->
						<!---->
						<!-- This is what your users will see when the form-->
						<!-- has successfully submitted-->
						<div class="d-none" id="submitSuccessMessage">
							<div class="text-center mb-3">
								<div class="fw-bolder">공지 올리기 성공!</div>
								<br /> <a
									href="https://startbootstrap.com/solution/contact-forms">공지페이지로
									가기</a>
							</div>
						</div>
						<!-- Submit error message-->
						<!---->
						<!-- This is what your users will see when there is-->
						<!-- an error submitting the form-->
						<div class="d-none" id="submitErrorMessage">
							<div class="text-center text-danger mb-3">Error sending
								message!</div>
						</div>


						<!-- Submit Button-->
						<div class="d-grid">
							<button style="margin-top: 15px" class="btn btn-primary btn-xl" id="submitButton"
								type="submit">등록</button>&nbsp;&nbsp;
							<button class="btn btn-primary btn-xl" id="resetButton"
								type="reset">리셋</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	
	<script>
		if(${r} != null) {
 		if(${r}>0 ) {
 				alert("등록이 완료되었습니다!");
 				location href ="petList.do";
 			} else {
 				alert("등록되지 않았습니다! 다시 시도해주세요!");
 				location href ="petList.do";
 			}
 		}
	</script>
</body>
</html>