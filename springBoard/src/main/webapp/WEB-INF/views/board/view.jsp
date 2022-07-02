<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>.
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>	<!-- True여야 EL이용가능  -->
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${vo.title}</title>
</head>
<body>
<h2>게시글 상세</h2>

<table>
	<tr>
		<td>글번호</td>
		<td>${vo.bidx}</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${vo.userName}</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td>${vo.wdate}</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>${vo.title}</td>
	</tr>

</table>

<!-- 작성자만 보이게-->
<c:if test="${login.midx eq vo.midx}">
<button>수정</button>
<button>삭제</button>
</c:if>
<button onclick="location.href='list.do'">목록</button>
</body>
</html>