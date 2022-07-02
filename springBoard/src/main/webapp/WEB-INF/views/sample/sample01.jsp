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
	<h2>sample01.jsp 입니다.</h2>
	<!-- 홈에서 링크 클릭시 sample1.do로 포워딩 이때 컨트롤러에서는 "안녕하세요" 라는데이터 받아와서 출력-->
	<%=data %>
	
	
</body>
</html>