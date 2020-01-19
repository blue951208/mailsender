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
	<h1>Update</h1>
	<form action="/updateBoard" method="post" enctype="multipart/form-data" >
			<div> <!-- 파일 -->
				<label for="boardFile">boadFile:</label>
				<input name="boardFile" type="file">
			</div>
		No:<div><input readonly type="text" name="boardNo" value="${board.boardNo}"></div>
		Title:<div><input type="text" name="boardTitle" value="${board.boardTitle}"></div>
		Content:<div><input type="text" name="boardContent" value="${board.boardContent}"></div>
		User:<div><input type="text" name="boardUser" value="${board.boardUser}"></div>
		<button type="submit">수정</button>
	</form>

</body>
</html>