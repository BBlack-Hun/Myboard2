<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body onload="document.f.username.focus();">
    <h3>Login with Username and Password</h3>
    <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
	    <font color="red">
	        <p>Your login attempt was not successful due to <br/>
	            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
	        <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
	    </font>
	</c:if>
    <form class="px-4 py-3" name="f" action="/manage/login" method="POST">
        <table>
            <tbody>
                <tr>
                    <td>User:</td>
                    <td><input type="text" name="username" value=""></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password"></td>
                </tr>
                <tr>
                    <td colspan="2"><input name="submit" type="submit" value="Login"></td>
                    <td><button type="button" name="register" onclick="location.href='/manage/registerView'">회원가입</button> </td>
                </tr>
                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
            </tbody>
        </table>
    </form>
</body>
</html>