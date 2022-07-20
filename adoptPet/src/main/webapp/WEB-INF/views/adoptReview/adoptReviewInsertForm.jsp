<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>입양 후기 글쓰기</title>
</head>
<body>
<form>
 	<div class="form-group">
      <label>제목</label>
      <input type="text" id="boardTitle" name="boardTitle" placeholder="Enter title">
	</div>
  	<div class="form-group">
      <label>내용</label>
      <textarea  id="boardContent" name="boardContect" rows="8"></textarea>
    </div>
	
    <div class="form-group">
      <label>첨부파일</label>
      <input type="file" id="filesName" name="filesName">
    </div>
    <div>
    <input type="submit" class="btn btn-primary" value="저장">&nbsp;&nbsp;&nbsp; 
	<input type="reset" class="btn btn-primary" value="취소">
    
    </div>
</form>
</body>
</html>