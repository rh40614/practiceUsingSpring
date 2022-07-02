<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
</head>
<body>
<h2>회원정보 수정</h2>

<form action="modify.do" method="POST"> 
	<input type ="hidden" name ="midx" value="${vo.midx}"> 
	midx : ${vo.midx}<br>
	id: ${vo.id}<br>
	password: <input type="password" name="password" value="${vo.password}"><br>
	name: <input type="text" name="userName" value = "${vo.userName }"><br>
	
	<input type="submit" value="저장">
	
</form>
</body>
</html>