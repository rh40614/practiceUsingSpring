<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

   <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet" type="text/css">
 
<title>로그인</title>
</head>
<body>

<main>
	<h5>해당 시스템은 로그인 후 이용가능합니다.</h5>
<hr>
	<div id="loginSet">
	<form action="login.do" method="POST">
		<label>
		<span>아이디:</span>
		<input type="text" name="memberId" size="25"></label> <br>
		<label>
		<span>비밀번호:</span>
		<input type="password" name="memberPassword" size="25"></label><br>
		
		<input type="button" value="로그인" style="width: 300px; margin-top: 10px;">
	</form><br>
	<a href="join.do">[회원가입]</a> 
	</div>
</main>

</body>
</html>