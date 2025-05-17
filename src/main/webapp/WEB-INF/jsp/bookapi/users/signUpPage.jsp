<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/users/css/signUp.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<title>회원가입</title>
</head>
<body>
	<script src="/users/js/sign.js"></script>
 	<div id="container">
 		<h2>Sign Up</h2>
		<div id=signUp>
			ID: <input type="text" name="userId" id="userId" maxlength="12">
			<button id="idck">중복확인</button><br>
			PW: <input type="password" name="userPw" id="userPw" maxlength="30"><br>
			REPW: <input type="password" name="rePw" id="rePw" maxlength="30"><br>
			Name: <input type="text" name="userName" id="userName"><br>
			Email: <input type="text" name="userMail" id="userMail" size="10">
			@<input type="text" name="mail" id="mail" value="naver.com" size="10" readonly>
			<select name="selectMail" id="selectMail">
				<option value="naver.com" selected>네이버</option>
				<option value="daum.net">다음</option>
				<option value="gmail.com">구글</option>
				<option value="userEmailInput">직접입력</option>
			</select>
			<button id="emailcheck">인증번호 전송</button>
			<br>
			인증번호: <input type="text" name="emailCode" id="emailCode" size="5" disabled>
			<button id="codeCk" style="display: none;">인증확인</button>
			<br>
			
			Gender: M<input type="radio" name="gender" id="gender" value="M" checked>
			F<input type="radio" name="gender" value="F">
			OTHER<input type="radio" name="gender" value="OTHER"><br>
			
			Birth: <input type="date" name="birthday" id="birthday"><br>
			PostCode: <input type="text" name="postCode" id="postCode" size="5" readonly><br>
			Address: <input type="text" name="userAddr1" id="userAddr1" readonly><br>
			JibunAddress: <input type="text" name="userJibunAddress" id="userJibunAddress" readonly><br>
			Other Address: <input type="text" name="userAddr2" id="userAddr2" placeholder="예시) 101동 204호..">
			<button type="button" id="openPostcode">우편번호 검색</button><br>
			<button id="signUpSubmit">회원가입</button>
		</div>
	</div>
	
</body>
</html>