<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>회원가입</h1>
		<form action="/joinMemberList" method="post">
			Name:<input type="text" name="memberName"><br>
			ID:<input type="text" name="memberId"><br>
			Pw:<input type="text" name="memberPw"><br>
			<button type="submit">가입</button>
		</form>
</body>
</html>