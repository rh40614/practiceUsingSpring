<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>


<c:if test="${login == null}">
<a href="member/join.do">회원가입</a>
<a href="member/login.do">로그인</a>
</c:if>


<c:if test="${login != null }">
<a href="member/logout.do">로그아웃</a>
</c:if>

<a href="board/notice.do">공지사항</a>


</body>
</html>
