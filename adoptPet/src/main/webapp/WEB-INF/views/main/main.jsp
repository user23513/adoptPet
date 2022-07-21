<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

</script>
</head>
<body>
 <!-- Masthead-->
<header class="masthead">
    <div class="container px-4 px-lg-5 h-100">
        <div class="row gx-4 gx-lg-5 h-100 align-items-center justify-content-center text-center">
            <div class="col-lg-8 align-self-end">
                <h1 class="text-white font-weight-bold">사지말고 입양하세요.</h1>
                <hr class="divider" />
            </div>
            <div class="col-lg-8 align-self-baseline">
                <p class="text-white-75 mb-5">Help us give a happy home to thousands of stray and abandoned dogs. Don't buy a pet, adopt one!</p>
                <a class="btn btn-primary btn-xl" href="#about">Find Out More</a>
            </div>
        </div>
    </div>
</header>




 <!-- About-->
	<section class="page-section bg-primary" id="about">
		<div class="container px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-lg-8 text-center">
					<h2 class="text-white mt-0">센터위치</h2>
					<hr class="divider divider-light" />
					<div id="googleMap" style="width: 600px; height: 400px; margin:0 auto;"></div>
					<script>
						function myMap() {
							var mapOptions = {
								center : new google.maps.LatLng(
										35.86911582324232, 128.59325935232815),
								level : 3,
								zoom : 19
							};

							var map = new google.maps.Map(document.getElementById("googleMap"), mapOptions);
							
						    var subMarkerPoint = { lat: 35.86911582324232, lng: 128.59325935232815};
							var subMarker = new google.maps.Marker({
							      position: subMarkerPoint,
							      map: map,
							      label:"데려가줘",
							      icon: {
							        url: "http://maps.google.com/mapfiles/ms/icons/red-dot.png",
							        labelOrigin: new google.maps.Point(15, -10),
							        anchor: new google.maps.Point(-10,40)
							      }
							    });
							}
						
					</script>
					<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAD2hrjGpgsEXIDgmQ2lk2h7-eEBH-XiRc&callback=myMap"></script>
 					<br>
 					
					<p class="text-white-75 mb-4">오시는길</p>
 					<p class="text-white-75">주     소 : 대구광역시 중구 중앙대로 403 (남일동 135-1, 5층) 태왕 아너스 타워</p>
					<p class="text-white-75">버  스(도보 1~2분)</p>
					<p class="text-white-75">- 약령시앞 : 204, 304, 349, 401, 410-1, 503, 518, 650, 706, 730, 909, 급행2, 북구2</p>
					<p class="text-white-75">- 약령시건너(동성로입구) : 204, 304, 349, 401, 410, 503, 518, 650, 706, 730, 급행2</p>
					<p class="text-white-75">전화번호 : 053-421-2460</p>
					<p class="text-white-75">F  A  X : 053-356-3939</p>
 					<br> <a class="btn btn-light btn-xl" target="_blank"
						href="https://www.google.co.kr/maps/place/%EC%98%88%EB%8B%B4%EC%A7%81%EC%97%85%EC%A0%84%EB%AC%B8%ED%95%99%EA%B5%90/data=!3m1!4b1!4m5!3m4!1s0x3565e3c264a0f67b:0x6f6af951f8677f92!8m2!3d35.8690802!4d128.5932219?hl=ko">찾아가기</a>
				</div>
			</div>
		</div>
	</section>
	<!-- Services-->
<section class="page-section" id="services">
    <div class="container px-4 px-lg-5">
        <h2 class="text-center mt-0">At Your Service</h2>
        <hr class="divider" />
        <div class="row gx-4 gx-lg-5">
        
        
            <% if(session.getAttribute("author").equals("ADMIN")){%>
             <div class="col-lg-3 col-md-6 text-center">
                <div class="mt-5">
                    <div class="mb-2"><i class="bi-heart fs-1 text-primary"></i></div>
                    <h3 class="h4 mb-2">ADMIN PAGE</h3>
                    <a class="text-muted mb-0" href="memberList.do">회원 리스트</a><br>
                    <a class="text-muted mb-0" href="#">입양신청 내역</a>
                </div>
            </div>
            <%} else{ %>
        
            <div class="col-lg-3 col-md-6 text-center">
                <div class="mt-5">
                    <div class="mb-2"><i class="bi-gem fs-1 text-primary"></i></div>
                    <h3 class="h4 mb-2">MY PAGE</h3>
                    <a class="text-muted mb-0" href="memberMyPage.do">내 정보 보기</a><br>
                    <a class="text-muted mb-0" href="memberAdopt.do">입양 신청 현황</a><br>
                    <a class="text-muted mb-0" href="#">나의 봉사참여 신청현황</a>
                </div>
            </div>
            <%} %>
            <div class="col-lg-3 col-md-6 text-center">
                <div class="mt-5">
                    <div class="mb-2"><i class="bi-laptop fs-1 text-primary"></i></div>
                    <h3 class="h4 mb-2">ADOPT PAGE</h3>
                    <a class="text-muted mb-0" href="#">동물 보러 가기</a><br>
                    <a class="text-muted mb-0" href="#">입양 동물 후기</a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 text-center">
                <div class="mt-5">
                    <div class="mb-2"><i class="bi-globe fs-1 text-primary"></i></div>
                    <h3 class="h4 mb-2">VOLUNTEER</h3>
                    <a class="text-muted mb-0" href="#">봉사 일정</a><br>
                    <a class="text-muted mb-0" href="#">봉사 후기</a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 text-center">
                <div class="mt-5">
                    <div class="mb-2"><i class="bi-heart fs-1 text-primary"></i></div>
                    <h3 class="h4 mb-2">COMMUNITY</h3>
                    <a class="text-muted mb-0" href="#">공지사항</a><br>
                    <a class="text-muted mb-0" href="qnaBoardList.do">Q&A</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Contact-->
        <section class="page-section" id="contact">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6 text-center">
                        <h2 class="mt-0">Let's Get In Touch!</h2>
                        <hr class="divider" />
                        <p class="text-muted mb-5">Ready to start your next project with us? Send us a messages and we will get back to you as soon as possible!</p>
                    </div>
                </div>
                <div class="row gx-4 gx-lg-5 justify-content-center mb-5">
                    <div class="col-lg-6">
                        <!-- * * * * * * * * * * * * * * *-->
                <!-- * * SB Forms Contact Form * *-->
                <!-- * * * * * * * * * * * * * * *-->
                <!-- This form is pre-integrated with SB Forms.-->
                <!-- To make this form functional, sign up at-->
                <!-- https://startbootstrap.com/solution/contact-forms-->
                <!-- to get an API token!-->
                <form id="contactForm" data-sb-form-api-token="API_TOKEN">
                    <!-- Name input-->
                    <div class="form-floating mb-3">
                        <input class="form-control" id="name" type="text" placeholder="Enter your name..." data-sb-validations="required" />
                        <label for="name">Full name</label>
                        <div class="invalid-feedback" data-sb-feedback="name:required">A name is required.</div>
                    </div>
                    <!-- Email address input-->
                    <div class="form-floating mb-3">
                        <input class="form-control" id="email" type="email" placeholder="name@example.com" data-sb-validations="required,email" />
                        <label for="email">Email address</label>
                        <div class="invalid-feedback" data-sb-feedback="email:required">An email is required.</div>
                        <div class="invalid-feedback" data-sb-feedback="email:email">Email is not valid.</div>
                    </div>
                    <!-- Phone number input-->
                    <div class="form-floating mb-3">
                        <input class="form-control" id="phone" type="tel" placeholder="(123) 456-7890" data-sb-validations="required" />
                        <label for="phone">Phone number</label>
                        <div class="invalid-feedback" data-sb-feedback="phone:required">A phone number is required.</div>
                    </div>
                    <!-- Message input-->
                    <div class="form-floating mb-3">
                        <textarea class="form-control" id="message" type="text" placeholder="Enter your message here..." style="height: 10rem" data-sb-validations="required"></textarea>
                        <label for="message">Message</label>
                        <div class="invalid-feedback" data-sb-feedback="message:required">A message is required.</div>
                    </div>
                    <!-- Submit success message-->
                    <!---->
                    <!-- This is what your users will see when the form-->
                    <!-- has successfully submitted-->
                    <div class="d-none" id="submitSuccessMessage">
                        <div class="text-center mb-3">
                            <div class="fw-bolder">Form submission successful!</div>
                            To activate this form, sign up at
                            <br />
                            <a href="https://startbootstrap.com/solution/contact-forms">https://startbootstrap.com/solution/contact-forms</a>
                        </div>
                    </div>
                    <!-- Submit error message-->
                    <!---->
                    <!-- This is what your users will see when there is-->
                    <!-- an error submitting the form-->
                    <div class="d-none" id="submitErrorMessage"><div class="text-center text-danger mb-3">Error sending message!</div></div>
                    <!-- Submit Button-->
                            <div class="d-grid"><button class="btn btn-primary btn-xl disabled" id="submitButton" type="submit">Submit</button></div>
                        </form>
                    </div>
                </div>
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-4 text-center mb-5 mb-lg-0">
                        <i class="bi-phone fs-2 mb-3 text-muted"></i>
                        <div>+82 (053) 421-2460</div>
                    </div>
                </div>
            </div>
        </section>
        
     </body>



</html>