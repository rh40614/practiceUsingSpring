<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	<!-- 태그립이 있어야 jstl 사용가능 "core를 쓸거고 별칭은 c라고 하겠다." -->
<%@ page session="true" %>	<!-- True여야 EL이용해서 session접근가능 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>게시판 목록</h2>
	
	<form method="GET" action="list.do">
		<select name="searchType">
			<option value="title" <c:if test="${!empty searchVO.searchType and searchVO.searchType eq 'title'}">selected</c:if>>제목</option>
			<option value="contentWriter" <c:if test="${!empty searchVO.searchType and searchVO.searchType eq 'contentWriter'}">selected</c:if>>작성자</option>
		</select>
		<input type="text" name="searchValue" <c:if test="${!empty searchVO.searchValue}">value="${searchVO.searchValue}"</c:if>>
		<!-- 컨트롤러에서 searchVO에 담아서 넘긴 값들을  -->
		<input type="submit" value="검색">
		
	</form>

	
	
	<table border="1"> 
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${list.size() == 0}">	<!-- list가 비워져있는지 찾는것 -->
				<tr>
					<td colspan="4">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${list.size() > 0}">	<!-- list가 비워져있는지 찾는것 -->
				<%-- <c:forEach begin="0" end="10" step="1"> 반복문 --%>
				<c:forEach var="vo" items="${list}">	<!--list라는 배열을 반복문돌려서 한줄씩 꺼내고 var에 설정한곳에 하나씩 담아서 꺼낸다.-->
					<tr>
						<td>${vo.bidx}</td>
						<td><a href="view.do?bidx=${vo.bidx}">${vo.title}</a></td>
						<td>${vo.userName}</td>
						<td>${vo.wdate}</td>
						
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	
	<c:if test="${!empty login}">
	<button onclick="goWrite()">등록</button>
	</c:if>
	
	<script>
		function goWrite(){
			/* el은 그 값을 가지고와서 출력해라 라는 뜻이기때문에 문자열로 el을 감싸주어야한다. 
			그리고 값이 없으면 null이 아니라 빈 문자열을 출력하기 때문에 디버깅할때는 빈문자열인지로 확인을 해야한다. */
			var login ="${login}";	
			console.log(login);
			
			if(login !=""){
				location.href ="write.do";	/*/없으니까 상대경로. 링크이동은 get방식 */
			}else{
				alert("로그인 이후 이용하실 수 있습니다.");
			}
			
			
		}
	
	</script>
	
	
	
	
	
</body>
</html>