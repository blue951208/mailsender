<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
</head>
<body>
<h1> 회원 탈퇴</h1>

	<form action="outMember" method="post">
		ID:<input type="text" name="memberId">
		PW:<input type="text" name="memberPw">
		<button type="submit">탈퇴 </button>
	</form>
</body>
</html>