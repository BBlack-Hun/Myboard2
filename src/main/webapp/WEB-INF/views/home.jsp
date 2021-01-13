<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<title>홈</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<script language="JavaScript">
	function printTime() {
		var clock = document.getElementById("clock");
		var now = new Date();
		
		clock.innerHTML = now.getFullYear() + "년 " +
		(now.getMonth()+1) + "월 " +
		now.getDate() + "일 " +
		now.getHours() + "시 " +
		now.getMinutes() + "분 " +
		now.getSeconds() + "초";
		
		setTimeout("printTime()", 1000);
		}
		
		window.onload = function() {
		printTime();
	};
</script>
<body>

<sec:authorize access="isAnonymous()">
	<h1>입구컷..</h1>
	<span id="clock"></span>
    <h5><a href='<c:url value="/manage/login"/>'>LOGIN</a> 로그인 해주세요.</h5>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<h1>어서오세요 ㅎㅎ</h1>
	<span id="clock"></span>
	<p><a href="#" onclick="document.getElementById('logout-form').submit();">Sign out</a></p>
	<form id="logout-form" action='<c:url value='/logout'/>' method="POST">
		<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
	</form>

   <p><a href="/board/index">게시판으로 넘어갑니다.</a></p>
</sec:authorize>

</body>
</html>
