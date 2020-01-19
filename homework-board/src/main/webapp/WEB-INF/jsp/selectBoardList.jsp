<%@ page language=	"java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/logout">로그아웃</a>
		<h1> Board List </h1><!-- 추가하기 -->
	<h2>추가</h2>
	
	<form id="addForm" action="${pageContext.request.contextPath}/insertBoard"
		method="post"
		enctype="multipart/form-data">
		
		<div>
			<label for="boardFile">boadFile:</label>
			<input name="boardFile" id="boardFile" type="file">
		</div>
		<div>boardPw:     <input type="text" name="boardPw"></div>
		<div>boardTitle:  <input type="text" name="boardTitle"></div>
		<div>boardContent:<input type="text" name="boardContent"></div>
		<div>boardUser:	  <input type="text" name="boardUser" value="${member.memberName}"></div>
		<button type="submit"> 확인 </button>
	</form>
	
	<h2>전체 행:${map.totalRow}</h2>
	
	<form action="${pageContext.request.contextPath}/selectBoardList" method="get">
		<div>검색<input type="text" name="searchWord"><button type="submit">확인</button></div>
	</form>
	
		<table border="1">
			<thead>
				<tr>
					<th>Board No</th>
					<th>Board Title</th>
					<th>Board User</th>
					<th>Board Date</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${map.list}">
					<tr>
						<td>${board.boardNo}</td>
						<td>
							<a href="/selectBoardOne?boardNo=${board.boardNo}">
								${board.boardTitle}
							</a>
						</td>
						<td>${board.boardUser}</td>
						<td>${board.boardDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	<c:if test="${map.currentPage>1}">
		<button type=button>
			<a href="/selectBoardList?currentPage=${map.currentPage-1}&searchWord=${searchWord}">이전</a>
		</button>
	</c:if>
	
	<c:if test="${map.currentPage<map.lastPage}">
		<button type=button>
			<a href="/selectBoardList?currentPage=${map.currentPage+1}&searchWord=${searchWord}">다음</a>
		</button>
	</c:if> 
	
</body>
</html>