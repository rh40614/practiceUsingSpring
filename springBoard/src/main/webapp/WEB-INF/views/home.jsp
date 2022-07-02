<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>	<!-- True여야 EL이용가능  -->
<%String data =(String)request.getAttribute("serverTime"); %>

<!-- HoemController에서 model.addAttribute("serverTime", formattedDate ); 로 코드를 작성하여 
"serverTime"이라는 이름으로 저장을 했기때문에 스트립트릿에서 이름값으로 data를 가지고 올수있다.   -->
<html>
<head>
	<title>Home</title>
	<meta charset="UTF-8">
	</head>
<body>


<h1>
	Hello world! 
	<c:if  test="${login != null}" >
 
	</c:if>
</h1>
<P>  The time on the server is ${serverTime}. </P>	
<%=data %>


<a href="sample1.do">sample1.do</a><br>
<a href="sample2.do">sample2.do</a><br>
<a href="user/resister.do">resister.do</a><br>
<br>
<!-- core 에 있는것 사용 JSTL -->
<!--login은 session -->
<c:if  test="${login == null}" >
<a href="user/join.do">회원가입하기</a>
<a href="user/login.do">로그인</a>
</c:if>

<c:if test="${login != null }">
<a href="user/mypage.do">마이페이지 </a>
<a href="user/logout.do">로그아웃 </a>
</c:if>

<br>
<a href="board/list.do">게시판 이동하기</a>
<a href="board/write.do">게시글 작성</a>
<br>
<a href="ajax/sample.do">ajax로 게시판 이동하기11  </a>
<a href="file/sample.do">file upload 예제로 이동하기</a>
</body>
</html>
