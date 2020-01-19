<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login </h1>
			<a href="/joinMember">회원가입</a>
	<form action="/login" method="post">
		ID:	<input type ="text" name="memberId">
		PW:	<input type ="text" name="memberPw">
		<button type="submit">로그인!</button>		
	</form>
</body>
</html>