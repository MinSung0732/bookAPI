<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${userDto.userName} 님의 마이페이지</title>
<link href="/users/css/signUp.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<script src="/users/js/myPage.js"></script>
	<div id="container">
		<div id="userInfo">
			<h2>${userDto.userName}님 반갑습니다!</h2>
			회원 ID: <input type="text" id="userId" name="userId" value="${userDto.userId}" readonly><br>
			회원 PW: <button id="changePwBtn">변경하기</button><br>
			<div id="emailblock">
			
			</div>
			회원 이름: <strong>${userDto.userName}</strong><br>
			회원 이메일: <input type="text" id="userMail" name="userMail" value="${userDto.userMail}" readonly><br>
			회원 성별: <c:choose>
				<c:when test="${userDto.gender == 'M'}">
					남성
				</c:when>
				<c:when test="${userDto.gender == 'F'}">
					여성
				</c:when>
				<c:otherwise>
					비공개
				</c:otherwise>
			</c:choose><br>
			회원 생년월일: ${birthdayStr}<br>
			주소지: ${userDto.userAddr1}<br>
			추가 주소지: ${userDto.userAddr2}
		</div>
	</div>
</body>
</html>