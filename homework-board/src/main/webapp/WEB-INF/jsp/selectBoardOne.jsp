<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html"; charset=UTF-8>
<title>Insert title here</title>
<!-- <script src="/jquery-ex/scripts/jquery-3.4.1.min.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<a href="/logout">로그아웃</a>
	<h1> Board One </h1>
		<table border="1">
			<thead>
				<tr>
					<th>No</th>
					<th>Title</th>
					<th>Content</th>
					<th>User</th>
					<th>Date</th>
					<th>fileName</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${board.boardNo}</td>
					<td>${board.boardTitle}</td>
					<td>${board.boardContent}</td>
					<td>${board.boardUser}</td>
					<td>${board.boardDate}</td>
					<c:if test="${board.boardFile!=null}">
						<td>
							<a href="/upload/${board.boardFile.fileName}">
								 ${board.boardFile.originName}						
							</a>
						</td>
					</c:if>
					
				</tr>
			</tbody>
		</table>
						
	<a href="/deleteBoardForm?boardNo=${board.boardNo}">삭제</a>
	<a href="/updateBoardForm?boardNo=${board.boardNo}">수정</a>
	
	<div>
	
		<!-- 댓글 -->
		<input type="hidden" id="boardNo" value="${board.boardNo}">
		
		<br>
		작성자:<input type="text" id="commentUser" value="${member.memberName}">
		비밀번호:<input type="password" id="commentPw">
		내용:<textarea id="commentContent"></textarea>
			<button type="button" id="addComment">입력</buttom>
	</div>
	<table>
		<thead>
			<tr>
				<th>No</th>
				<th>User</th>
				<th>Content</th>
			</tr>
		</thead>
		<tbody id="comment">
		</tbody>
	</table>
<script>
$(document).ready(function(){

	$.ajax({
		url:"/rest/getCommentList",
		method:"post",
		data:{
				"boardNo":$("#boardNo").val()
				},
		success:function(json){
				console.log("json");
				$.each(json,function(index,item){
						$("#comment").append(
									"<tr>"
									+"<td id='no'>"+item.commentNo+"</td>"
									+"<td id='user'>"+item.commentUser+"</td>"
									+"<td id='content'>"+item.commentContent+"</td>"
									+"<td><input type='password' id='commentPw'>"
									+"<button type='button' id='deleteComment' value='"+item.commentNo+"''>삭제</button></td>"
									+"</tr>"
								)
					})
			}
	});


	
	$(document).on("click","#deleteComment",function(){
		$.ajax({
			url:"/rest/deleteComment",
			method:"post",
			data:{
					"commentNo":$(this).val(),
					"commentPw":$(this).prev().val()
				},
			success:function(){
					location.reload();//수정바람,--댓글만 새로고침 되도록 
				}	
		})
	})

	
	$("#addComment").click(function(){
		if($("#commentContent").val()=="" || $("#commentPw").val()==""){
				alert("댓글을 입력하세요")
			}
		else{
		$.ajax({
			url:"/rest/addComment",
			method:"post",
			async: false,
			data:{
					"boardNo":$("#boardNo").val(),
					"commentPw":$("#commentPw").val(),
					"commentUser":$("#commentUser").val(),
					"commentContent":$("#commentContent").val()
				},
			success:function(json){
				console.log("success");
			}
		});
		}
	});


	
});
</script>
</body>
</html>