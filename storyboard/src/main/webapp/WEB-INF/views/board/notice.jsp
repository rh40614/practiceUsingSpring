<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
	<link href="${pageContext.request.contextPath}/resources/css/notice.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>

<header>
<a href="notice.do">공지사항</a>
<a href="board_free.do">자유게시판</a>
<a href="board_Q&A.do">Q&A 게시판</a>

<c:if  test="${login == null}" >
<a href="<%=request.getContextPath()%>/member/login.do">로그인</a>
</c:if>

<c:if test="${login != null}">
${vo.memberName}님 환영합니다.
[ 마이페이지  | <a href="member/logout.do">로그아웃</a> ]

</c:if>
<hr>

</header>
<main>
|공지사항
<hr>

	<form method="GET" action="notice.do">
		<select name="searchType">
			<option value="title" <c:if test="${!empty searchVO.searchType and searchVO.searchType eq 'title'}">selected</c:if>>제목</option>
			<option value="contentWriter" <c:if test="${!empty searchVO.searchType and searchVO.searchType eq 'contentWriter'}">selected</c:if>>작성자</option>
		</select>
		<input type="text" name="searchValue" <c:if test="${!empty searchVO.searchValue}">value="${searchVO.searchValue}"</c:if>>
		<!-- 컨트롤러에서 searchVO에 담아서 넘긴 값들을  -->
		<input type="submit" value="검색">
		
	</form>

	<table>
		<thead>
			<tr>
			<th>No</th><th>제목</th><th>작성자</th><th>등록일</th><th>조회수</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${list.size() == 0}">	<!-- list가 비워져있는지 찾는것 -->
			<tr>
				<td colspan="5">등록된 게시물이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${list.size() > 0}">	<!-- list가 비워져있는지 찾는것 -->
				<%-- <c:forEach begin="0" end="10" step="1"> 반복문 --%>
			<c:forEach var="vo" items="${list}">	
				<tr>
					<td>${vo.bidx}</td>
					<td>${vo.title}</td>
					<td>${vo.writer}</td>
					<td>${vo.wdate}</td>
					<td>${vo.hit}</td>
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>


















</main>
</body>
</html>