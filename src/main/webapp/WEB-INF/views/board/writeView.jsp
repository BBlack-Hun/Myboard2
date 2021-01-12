<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
	<form action="/board/write" method="post">
		<label>제목</label>
		<input type="text" />
		<label>내용</label>
		<input type="text" />
		<label>글쓴이</label>
		<input type="text" />
		
		<input type="button" name="write" value="작성">
		<input type="button" name="cancel" value="취소">
	</form>
</body>
</html>