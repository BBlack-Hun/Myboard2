<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 자바 프로그램을 태그로 사용하도록 정의 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--숫자나 통화, 날짜 같은 형태 맞춰주기 위하여 정의-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.dataRow:hover{
	background: #eee;
	cursor: pointer;
}
</style>
<title>게시판 목록</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(function() {
	// 데이터의 한줄 클릭 이벤트 처리 -> 글보기로 이동
	$(".dataRow").click(function() {
		var no = $(this).find(".no").text();
		location="readView?no=" + no + "&inc=1";		
	});
});
</script>
</head>
<body>
<div class="container">
	<h1>게시판 리스트</h1>
	<hr>
		<button value="write" onclick="location.href='/board/writeView'">글쓰기</button>
		<button value="home" onclick="location.href='/'">처음으로</button>
	<hr>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
		
			<!--데이터가 있는 만큼 <tr>이 반복됨 -->
			<c:forEach items="${list}" var="dto">
				<tr class="dataRow">
					<td class="no">${dto.no}</td>
					<td>${dto.title}</td>
					<td>${dto.writer}</td>
					<td><fmt:formatDate value="${dto.writeDate}" pattern="yyyy-MM-dd-hh:mm"/></td>
					<td>${dto.hit}</td>
				</tr>
			</c:forEach>
			<!-- 전체 데이터의 갯수가 한페이지를 넘지 않으면 만들지 않는다. -->
			<c:if test="${pageObject.totalRow > pageObject.perPageNum }">
			<tr>
				<td colspan="5">
					<pageNav:pageNav endPage="${pageObject.endPage }" 
					totalPage="${pageObject.totalPage }"
					startPage="${pageObject.startPage }"
					page="${pageObject.page }"/>
				</td>
			</tr>
			</c:if>
		</tbody>
	</table>
</div>
</body>
</html>