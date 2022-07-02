<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>	<!-- True여야 EL이용가능  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<table>
<%-- <c:if test="${vo =!null}"> --%>
<tr>
<td>${vo.userName}님의 정보입니다.</td>
</tr>
<tr>
<td>회원번호: </td><td>${vo.midx}</td>
</tr>
<tr>
<td>이름: </td><td>${vo.userName}</td>
</tr>
<tr>
<td>id: </td><td>${vo.id}</td>
</tr>
<%-- </c:if> --%>
</table>

<button onclick="location.href='modify.do?midx=${vo.midx}'">수정</button>
</body>
</html>