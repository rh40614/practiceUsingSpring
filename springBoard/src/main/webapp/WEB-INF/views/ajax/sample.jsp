<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script><!-- 자원에 관한 가상경로는 servlet-context에 지정  -->
</head>
<script>

	function userInfo(){
		var pjtPath="<%=request.getContextPath()%>";	
		$.ajax({
			url:pjtPath+'/ajax/userInfo.do',	/* ajax컨트롤러에서 가상경로가 들어오면 처리한다. *//*화면에서 (데이터를?) 찾을때는 프로젝트 패스를 붙혀줘야한다.  */
			type: "get",
			success: function(data){
				/* $("#result").html(data); */
				
				console.log(data);
				$("#result").html(data.id+","+data.userName+","+data.password+","+data.midx)
			}
		});
	}

	
	function goText(){
		$.ajax({
			url: 'goText.do',
			type:"get",
			data: "text="+$("#t1").val(),
			success: function(data){
				console.log(data);
				$("#result2").html(data);
			},
			error:function(){
				alert(1);
			}
		});
	
	}
	
	
	
	function callBoard(){
		$.ajax({
			url: "boardList.do",
			type: "get",
			success:function(data){
				var html="";
				html +="<table>";
				html +="<thead>";
				html +="<tr>";
				html +="<th>글번호</th>";
				html +="<th>제목</th>";
				html +="<th>작성자</th>";
				html +="</tr>";
				html +="<tbody>";
				for(var i=0; i<data.length; i++){
					html+="<tr>";
					html+="<td>"+data[i].bidx+"</td>";
					html+="<td><a href='javascript:viewBoard("+data[i].bidx+");'>"+data[i].title+"</td>";	
					/* ajax통신안에서 자바스크립트 메서드 사용해서 이동. 링크 이동 바로 이동못함 */
					html+="<td>"+data[i].userName+"</td>";
					html+="</tr>";
				}
				html +="</tbody>";
				html +="</table>";
				
				$("#boardList").html(html);
			}
		})
	}
	
	
	/* jackson 라이브러리 꼭 있어야 함 */
	function viewBoard(bidx){
		$.ajax({
			url: "boardView.do",
			type: "get",
			data: "bidx="+bidx,
			success: function(data){
				var html ="";
				
				html+="<table>"
				html+="<tr>";	
				html+="<td>글번호</td>";
				html+="<td>"+data.bidx+"</td>";
				html+="</tr>";
				html+="<tr>";
				html+="<td>작성자</td>";	
				html+="<td>"+data.userName+"</td>";
				html+="</tr>";
				html+="<tr>";
				html+="<td>제목</td>";	
				html+="<td>"+data.title+"</td>";	
				html+="<tr>";
				html+="<td>내용</td>";	
				html+="<td>"+data.content+"</td>";	
				html+="</tr>";
				html+="</tr>";	
				html+="</table>";	
				
				$("#boardView").html(html);
		
			}
					
		});
	}
	
	
	
	
	function searchGo(){
		$.ajax({
			url: "boardList.do",	/* 위의 callboard()와 같은 가상주소를 사용 */
			type:"GET",
			data: $("#searchForm").serialize(), //serialize: 셀렉터 form 안에 있는 name을 가지고 있는 모든 입력양식 데이터를 파라미터 문자열로 반환
			success:function(data){
				var html="";
				html +="<table>";
				html +="<thead>";
				html +="<tr>";
				html +="<th>글번호</th>";
				html +="<th>제목</th>";
				html +="<th>작성자</th>";
				html +="</tr>";
				html +="<tbody>";
				for(var i=0; i<data.length; i++){
					html+="<tr>";
					html+="<td>"+data[i].bidx+"</td>";
					html+="<td><a href='javascript:viewBoard("+data[i].bidx+");'>"+data[i].title+"</td>";	
					/* ajax통신안에서 자바스크립트 메서드 사용해서 이동. 링크 이동 바로 이동못함 */
					html+="<td>"+data[i].userName+"</td>";
					html+="</tr>";
				}
				html +="</tbody>";
				html +="</table>";
				
				$("#boardList").html(html);
			},
			error:function(){
				alert(1);
			}
		});
	}
	
</script>
<body>
	<h2>ajax 예제 페이지 </h2>
	<button onclick="userInfo()"> 현재 로그인 유저 확인</button>
	<div id="result"></div>
	<hr><hr>
	<input type="text" id="t1">
	<button onclick="goText()">클릭!</button> 
	<div id="result2"></div>
	<hr><hr>
	<button onclick="callBoard()">게시글 불러오기</button>
	<br>
	
	<!-- ajax를 이용하여 검색 기능을 만들면 검색 화면을 이동하지 않기 때문에 jstl로 화면을 그릴 필요없다. -->
	<!-- action 태그도 필요하지 않다. -->
	<form id="searchForm">
		<select name="searchType">
			<option value="title" >제목</option>
			<option value="contentWriter" >작성자,내용</option>
		</select>
		<input type="text" name="searchValue" >
		<!-- 컨트롤러에서 searchVO에 담아서 넘긴 값들을  -->
		<input type="button" value="검색" onclick="searchGo()">
		
	</form>
	
	
	<div id="boardList"></div>

	<div id="boardView"></div>
	
</body>
</html>