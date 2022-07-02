<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h2>회원가입 화면</h2>
	
	<!-- 지금 내가 있는 화면은 user/join.do 를 이용해서 들어온 경로이므로  -->
	<form action="join.do" method="POST">		
		id: <input type="text" name="id"><br>
		password: <input type="password" name="password"><br>
		name: <input type="text" name="userName"><br>
		<input type="submit" value="회원가입">
	</form>
</body>
</html>