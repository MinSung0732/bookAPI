<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/users/css/signUp.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>로그인</title>
</head>
<body>
	<script src="/users/js/signIn.js"></script>
 	<div id="container">
 		<h2>Sign In</h2>
		<div id=signIn>
			ID: <input type="text" name="userId" id="userId" required=""><br>
			PW: <input type="password" name="userPw" id="userPw" required=""><br>
			로그인 유지 <input type="checkbox" name="autoLogin" class="check-box"><br>
			<button id="signInSubmit">로그인</button><br>
			<a href="/users/signUpPage"><button>회원가입</button></a>
		</div>
	</div>
	
</body>
</html>