<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--숫자나 통화, 날짜 같은 형태 맞춰주기 위하여 정의-->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>${dto.title}</title>
</head>
<script type="text/javascript">
		$(document).ready(function(){
			var formObj = $("form[name='readForm']");
			
			// 수정 
			$(".update_btn").on("click", function(){
				formObj.attr("action", "/board/updateView");
				formObj.attr("method", "get");
				formObj.submit();				
			})
			
			// 삭제
			$(".delete_btn").on("click", function(){
				
				var deleteYN = confirm("삭제하시겠습니가?");
				if(deleteYN == true){
					
				formObj.attr("action", "/board/delete");
				formObj.attr("method", "post");
				formObj.submit();
					
				}
			})
			
			// 목록
			$(".list_btn").on("click", function(){
				
				location.href = "/board/index?page=${scrl.page}"
								+"&perPageNum${scrl.perPageNum}"
								+"&searchType=${scrl.searchType}&keyword=${scrl.keyword}";
			})
		
		})
		
		
</script>
<body>
	<h1>게시판 글보기</h1>
	<form name="readForm" role="form" method="post">
		<input type="hidden" id="no" name="no" value="${dto.no}" />
	</form>
	<table class=table>
		<tr>
			<th>번호</th>
			<td>${dto.no }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${dto.title }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${dto.content}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dto.writer }</td>
		</tr>
		<tr>
			<th>작성일자</th>
			<td><fmt:formatDate value="${dto.writeDate}" pattern="yyyy-MM-dd-hh:mm"/></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${dto.hit }</td>
		</tr>
		<tr>
			<button type="summit" class="update_btn">수정</button>
			<button type="summit" class="delete_btn">삭제</button>
			<button type="summit" class="list_btn">목록</button>
		</tr>
	</table>
	
</body>
</html>