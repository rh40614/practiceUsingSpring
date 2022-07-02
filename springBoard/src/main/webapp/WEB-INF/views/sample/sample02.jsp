<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String data = (String)request.getAttribute("key"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>sample02.jsp 입니다.</h2>
	<%=data %>
	
	<form action="sample2.do" method="POST">
	data: <input type="text" name="testData"><br>
	<input type="submit">
	</form>
	
	
	
</body>
</html>