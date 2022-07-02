<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>파일 업로드 예제</h2>
	
	<form action="upload.do" method="POST" enctype="multipart/form-data"> <!-- get방식은 문자열로 데이터를 넘기기때문에 사용불가 -->
	파일: <input type="file" name="file"> 
	
	<br>
	<input type="submit" value="업로드">
	
	</form>
</body>
</html>