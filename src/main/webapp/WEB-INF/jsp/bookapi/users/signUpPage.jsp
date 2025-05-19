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
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<title>회원가입</title>
</head>
<body>
	<script src="/users/js/sign.js"></script>
	<jsp:include page="../util/header.jsp" />
	<div id="container">
		<img class="catbook" src="images/catbook.svg">
		<div class="signUp">
			<h2 class="menuTitle">회원가입</h2>
			<h4>아이디</h4>
			<input type="text" name="userId" id="userId" maxlength="12">
			<button id="idck" class="btnStylePrimary">중복확인</button>
			<br>
			<h4>비밀번호</h4>
			<input type="password" name="userPw" id="userPw" maxlength="30">
			<h4>비밀번호확인</h4>
			<input type="password" name="rePw" id="rePw" maxlength="30">
			<h4>성명입력</h4>
			<input type="text" name="userName" id="userName">
			<h4>Email</h4>
			
			<input type="text" name="userMail" id="userMail" size="30"
				class="eMailForward"> @
			<input type="text" name="mail" id="mail" value="naver.com" class="eMailForward hide" readonly>
			<div class="selectMailWrap">
				<select name="selectMail" id="selectMail">
					<option value="naver.com" selected>naver.com</option>
					<option value="daum.net">daum.net</option>
					<option value="gmail.com">gmail.com</option>
					<option value="userEmailInput">직접입력</option>
				</select>
				 <span class="icoArrow"><img src="images/arrow.svg" alt=""></span>
			</div>
			<button id="emailcheck" class="btnStylePrimary">인증번호 전송</button>
			<div class="hide certified">
			<h4>인증번호</h4>
			<input type="text" name="emailCode" id="emailCode" size="5" disabled>
			</div>
			<button id="codeCk" style="display: none;" class="btnStylePrimary">인증확인</button>
			<h4 class="gender">성별체크</h4>
			<p class="genderSelect">M</p>
			<input type="radio" name="gender" id="gender" value="M" checked>
			<p class="genderSelect">F</p>
			<input type="radio" name="gender" value="F"> 
			<p class="genderSelect">OTHER</p>
			<input type="radio" name="gender" value="OTHER">
			<h4>생년월일</h4>
			<input type="date" name="birthday" id="birthday">
			<h4>우편번호</h4>
			<input type="text" name="postCode" id="postCode" size="5" readonly>
			<h4>주소입력</h4>
			<input type="text" name="userAddr1" id="userAddr1" readonly>
			<h4>지번주소</h4>
			<input type="text" name="userJibunAddress" id="userJibunAddress"
				readonly>
			<h4>상세주소</h4>
			<input type="text" name="userAddr2" id="userAddr2"
				placeholder="예시) 101동 204호..">
			<button type="button" id="openPostcode" class="btnStylePrimary">우편번호
				검색</button>
			<br>
			<button id="signUpSubmit" class="btnStylePrimary btnStyleBottom">회원가입</button>
			<button id="signUp"
				class="btnStylePrimary btnStyleBottom btnStyleSolid">가입취소</button>
		</div>
	</div>
	<jsp:include page="../util/footer.jsp" />

</body>
</html>