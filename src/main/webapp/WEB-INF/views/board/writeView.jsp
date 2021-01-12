<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<script type="text/javascript">
		$(document).ready(function(){
			var formObj = $("form[name='writeForm']");
			$(".write_btn").on("click", function(){
				if(fn_valiChk()){
					return false;
				}
				formObj.attr("action", "/board/write");
				formObj.attr("method", "post");
				formObj.submit();
			});
		})
</script>
<body>
	<form name="writeForm" action="/board/write" method="post">
		<label for="title">제목</label>
		<input type="text"  name="title"/>
		<label for="content">내용</label>
		<input type="text" name="content"/>
		<label for="writer">글쓴이</label>
		<input type="text" name="writer"/>
		
		<button type="summit" class="write_btn" name="write">작성</button>
		<button type="button" name="cancel" onclick="location.href='/board/index'">취소</button>
	</form>
</body>
</html>