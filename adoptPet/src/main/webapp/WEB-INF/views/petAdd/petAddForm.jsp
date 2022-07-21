<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입양동물 등록 폼</title>
</head>
<body>
	<section class="page-section" id="contact">
		<div class="container px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-lg-8 col-xl-6 text-center">
					<h2 class="mt-0">동물 등록</h2>
					<hr class="divider" />
				</div>
			</div>
			<div class="row gx-4 gx-lg-5 justify-content-center mb-5">
				<div class="col-lg-6">



					<form id="contactForm" action="petAddInsert.do" method="post">
						<!-- Name input-->
						<div class="form-floating mb-3">
							<input class="form-control" type="text" id="petAddName"
								name="petAddName" value="" /> <label for="petAddName">동물이름</label>

						</div>

						<div class="form-floating mb-3">
							<input class="form-control" type="text" id="petAddAge"
								name="petAddAge" value="" /> <label for="petAddAge">동물나이(대략)</label>

						</div>

						<div class="form-floating mb-3">
							<select class="form-control" id="petAddGender" name="petAddGender" >
								<option value="남자">--선택--</option>
								<option value="남자">남자</option>
								<option value="여자">여자</option>
							</select> <label for="petAddGender">동물성별</label>
						</div>
						
						<div class="form-floating mb-3">
							<input class="form-control" type="text" id="petAddWeight"
								name="petAddWeight" value="" /> <label for="name">동물체중(대략)</label>

						</div>
						<div class="form-floating mb-3">
							<input class="form-control" type="text" id="petAddHealth"
								name="petAddHealth" value="" /> <label for="name">동물건강상태</label>

						</div>
						<div class="form-floating mb-3">
							<input class="form-control" type="text" id="petAddAdoptState"
								name="petAddAdoptState" value="입양대기" readonly /> <label
								for="name">입양여부</label>

						</div>
						<div class="form-floating mb-3">
							<select class="form-control" id="petAddType" name="petAddType" >
								<option value="강아지">--선택--</option>
								<option value="강아지">강아지</option>
								<option value="고양이">고양이</option>
							</select> <label for="name">동물유형</label>
<!-- 						</div> -->
<!-- 						<div class="form-floating mb-3"> -->
<!--                         <input class="form-control" id="files" type="file" /> -->
<!--                    		</div> -->

						
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

</body>
</html>