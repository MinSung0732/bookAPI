<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>책:고 - PW 찾기</title>
</head>
<body>
	<script src="/users/js/findPw.js"></script>
	
	<div id="container">
		<jsp:include page="../util/header.jsp" />
		
		<div class="findMyPw">
			<h2>이메일로 Pw 찾기</h2>
			이메일 입력: 
			<input type="text" id="userMail" name="userMail">
			<button type="button" id="mailCkBtn" name="mailCkBtn">확인</button>
			<br>
			<div id="ckCodeSection" style="display: none;">
				<label id="ckCodeText" name="ckCodeText">
				인증번호 입력: 
				</label>
				<input type="text" id="ckCode" name="ckCode">
				<button type="button" id="ckCodeBtn" name="ckCodeBtn">확인</button>
				<label id="timeForCheck"></label>
			</div>
			<div id="idCheckSection" style="display: none; margin-top: 15px;">
				아이디 입력:
				<input type="text" id="userId" name="userId" placeholder="아이디를 입력해주세요">
			</div>
			<div id="pwResetSection" style="display: none; margin-top: 20px;">
				<h3>새 비밀번호 설정</h3>
				<input type="password" id="newPw" placeholder="새 비밀번호">
				<br>
				<input type="password" id="newPwConfirm" placeholder="비밀번호 확인">
				<br>
				<button type="button" id="resetPwBtn">비밀번호 변경</button>
			</div>
		</div>
		
		<jsp:include page="../util/footer.jsp" />
	</div>

</body>
</html>