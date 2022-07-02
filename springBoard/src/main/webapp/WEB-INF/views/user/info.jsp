<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- userController에서 "vo" 라는 이름으로 받아왔고 바로 필드명을 써주면된다. -->
${vo.userName}	<!-- 파라미터나 세션값을 바로 출력가능하다 -->
${vo.userAge}
${vo.userAdr}
${vo.userPhone}


</body>
</html>

<!--  EL 에서 제한을 두지않으면 모든 내장객체에 접근한다.
EL의 내장객체는 param이다. param으로 바로 값을 가지고 올 수도 있따. -->

