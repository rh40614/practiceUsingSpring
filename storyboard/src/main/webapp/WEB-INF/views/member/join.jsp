<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<head>
	<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
	<link href="${pageContext.request.contextPath}/resources/css/join.css" rel="stylesheet" type="text/css">
</head>
<title>회원가입 페이지</title>
<script>

$(function(){
	
	//아이디 중복확인
	$("#idCheck").click(function(){
		
		//중복확인 버튼을 누르지 않은경우 기본값	//해쉬맵으로 처리하기
		var idck = 0;	
		//아이디 값 담기 
		var memberId = $("#memberId").val();
		
		//alert(memberId);
		
		if(memberId = ""){
			alert("아이디를 입력해주십시오");
			return;
		}else{
			
			$.ajax({
				url:"idCheck.do",
				type: "POST",		/* 넘길때 json으로 넘기면 안됨 ㅠ  */
				data: "memberId="+ memberId,
				success:function(data){
					
						console.log(data);
						
					if (data > 0) {
	                    alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
	                    $("#memberId").css("color", "red")
	                    $("#memberId").focus();
	                    
	                } else {
	                    alert("사용가능한 아이디입니다.");
	                    $("#memberPassword").focus();
	                    //아이디가 중복되지 않으면  idck = 1 
	                    idck = 1;
	                }
	           },
	           error: function(){
	        	   alert("error")
	           }
			})
		}
	});
	
	
	function join(){
		
		if(idck == 1){
			$("#frm").attr("method","POST");
			$("#frm").attr("action","join.do").submit();
			//아이디 변경 후 중복확인 확인 다시 함수 다시실행?
					
			alert("회원가입이 완료되었습니다. 로그인 후 이용바랍니다.");
		}else{
			alert("아이디 중복확인을 해주시기 바랍니다.");
	
		}
	}
	
})
</script>

</head>
<body>
<main>
	<h3>회원가입 후 로그인하여 이용하세요</h3>
	<hr>
		<form  id="frm" name="frm">
		<label><span>아이디: </span>
		<input type="text" name="memberId" id="memberId" size="13"><button type="button" id="idCheck">아이디 중복확인</button></label>
		<label><span>비밀번호: </span>
		<input type="password" name="memberPassword" id="memberPassword" size="30"></label>
		<label><span>이름: </span>
		<input type="text" name="memberName" size="30"></label>
		<label><span>이메일: </span>
		<input type="text" name="memberEmail" size="30"></label>
		<label><span>연락처: </span>
		<input type="text" name="memberPhone" size="30"></label>
		<label><span>주소: </span>
		<input type="text" name="memberAdr" size="30"></label>
		<input type="hidden" name ="adminYN">
		<input type="hidden" name ="delYN">
		<input type="button" value="회원가입"  id ="btn" onclick="join()"> 
		</form>

</main>
</body>
</html>