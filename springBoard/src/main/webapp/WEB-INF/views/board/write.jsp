<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>게시글 작성</h2>

<form action="write.do" method="POST">
<table>
	<tbody>
		<tr>
			<td> 제목: </td>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<td> 내용: </td>
			<td><textarea rows="10" cols="50" name="content"></textarea>
			</td>
		</tr>
	</tbody>
</table>
	<input type="submit" value="작성"> 
<!-- <button>작성</button> 버튼태그가 form 태그안에 있으면 기본적으로 submit이 된다. -->
</form>


</body>
</html>