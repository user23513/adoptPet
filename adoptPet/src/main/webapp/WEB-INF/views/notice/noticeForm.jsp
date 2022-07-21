<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 글쓰기 </title>
</head>
<body>
 <section class="page-section" id="contact">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6 text-center">
                        <h2 class="mt-0">공지 글쓰기!!</h2>
                        <hr class="divider" />
                    </div>
                </div>
                <div class="row gx-4 gx-lg-5 justify-content-center mb-5">
                    <div class="col-lg-6">
        		
                <form id="contactForm" action="noticeInsert.do" method="post">
                    <!-- Name input-->
                    <div class="form-floating mb-3">
                        <input class="form-control" id="boardTitle" type="text" placeholder="Title please...." data-sb-validations="required" />
                        <label for="name">Title</label>
                        <div class="invalid-feedback" data-sb-feedback="name:required">Title is required.</div>
                    </div>
                     <div class="form-floating mb-3">
                        <input class="form-control" id="name" type="date"  data-sb-validations="required" />
                        <label for="name">Date</label>
                    </div>
                    <!-- Message input-->
                    <div class="form-floating mb-3">
                        <textarea class="form-control" id="boardContent" type="text" placeholder="Enter your content here..." style="height: 10rem" data-sb-validations="required"></textarea>
                        <label for="message">Content</label>
                        <div class="invalid-feedback" data-sb-feedback="message:required">Content is required.</div>
                    </div>
                    <div class="form-floating mb-3">
                        <input class="form-control" id="files" type="file" />
                    </div>
                    
                    <div class="d-none" id="submitSuccessMessage">
                        <div class="text-center mb-3">
                            <div class="fw-bolder">공지 올리기 성공!</div>
                            <br />
                            <a href="https://startbootstrap.com/solution/contact-forms">공지페이지로 가기</a>
                        </div>
                    </div>
                    
                    <div class="d-none" id="submitErrorMessage">
                    	<div class="text-center text-danger mb-3">Error sending message!</div>
                    </div>
                    
                    
                     <div class="d-grid"><button class="btn btn-primary btn-xl" id="submitButton" type="submit">Submit</button>
                     </div>
                       
                       
                        </form>
                    </div>
                </div>
                </div>
                </section>
</body>
</html>