<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/users/css/signUp.css" rel="stylesheet" type="text/css">
<link rel="icon" href="images/favicon.ico">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>책:고 - 로그인</title>
</head>
<body>
	<script src="/users/js/signIn.js"></script>
	<jsp:include page="../util/header.jsp" />
	<div id="container">
		<div id=signIn class="signUp">
			<img src="images/sublogo.png" class="sublogo">
			<h2 class="menuTitle">로그인</h2>
			<h4>아이디</h4>
			<input type="text" name="userId" id="userId" required="">
			<h4>비밀번호</h4>
			<input type="password" name="userPw" id="userPw" required=""><br>
			<div class="signInTexts">
				<input type="checkbox" name="autoLogin" class="check-box">
				<h4 class="signInCheck">로그인 유지</h4>
			</div>
			<div class="signInTexts">
				<h4 class="signInText"><a href="/users/findId">아이디 찾기</a></h4>
				<h4><a href="/users/findPw">비밀번호 찾기</a></h4>
			</div>
			<a href="/" class="block signScope signScopeTop">
				<button id="signInSubmit" class="btnStylePrimary">로그인</button>
			</a>
			<a href="/users/signUpPage" class="block signScopeTop">
				<button class="btnStylePrimary btnStyleSolid">회원가입</button>
			</a>
		</div>
	</div>
	<jsp:include page="../util/footer.jsp" />

</body>
</html>