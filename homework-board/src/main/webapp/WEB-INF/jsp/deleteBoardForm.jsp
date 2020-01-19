<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/logout">로그아웃</a>
	<h1>Delete</h1>
	<form action="deleteBoard" method="post">
		No:<input type="text" name="boardNo" value="${boardNo}">
		PW:<input type="password" name="boardPw">
		<button type="submit">삭제 </button>
	</form>
	
</body>
</html>