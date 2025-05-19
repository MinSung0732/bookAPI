<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>책:고 - ID 찾기</title>
</head>
<body>
	<script src="/users/js/findId.js"></script>
	<div id="container">
		<jsp:include page="../util/header.jsp" />

		<div class="findMyID">
			<h2>이메일로 ID 찾기</h2>
			이메일 입력: 
			<input type="text" id="userMail" name="userMail">
			<button type="button" id="mailCkBtn" name="mailCkBtn">확인</button>
			<br>
			<label id="ckCodeText" name="ckCodeText" style="display: none;">
			인증번호 입력: 
			</label>
			<input type="text" id="ckCode" name="ckCode" style="display: none;">
			<button type="button" id="ckCodeBtn" name="ckCodeBtn" style="display: none;">확인</button>
			<label id="timeForCheck" style="display: none;"></label>
		</div>
		
		<jsp:include page="../util/footer.jsp" />
	</div>

</body>
</html>