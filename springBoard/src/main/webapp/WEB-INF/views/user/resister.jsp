<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl 사용하려면 있어야함 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>	<!-- scope 접근 true 여야 가능 -->


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="resister.do" method="POST">
	이름: <input type="text" name="userName"><br>
	나이: <input type="number" name="userAge"><br>
	주소: <input type="text" name="userAdr"><br>
	연락처: <input type="text" name="userPhone"><br>
	<input type="submit" value ="전송">
</form>


</body>
</html>