<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책읽는 고양이: 책고</title>
</head>
<body>
	<div id="container">
		<jsp:include page="util/header.jsp" />
		index<br>
		<a href="/users/userMain">도서검색하기</a><br>
		<c:choose>
	        <c:when test="${not empty sessionScope.Login}">
	            <p>Welcome, ${sessionScope.UserName}님!</p>
	            <a href="/users/logout">로그아웃</a>
				<a href="/users/myPage">마이페이지</a>
	        </c:when>
	        <c:otherwise>
	            <a href="/users/signInPage">로그인</a>
				<a href="/users/signUpPage">회원가입</a>
	        </c:otherwise>
    	</c:choose>
    	
    	<ul class="tabs">
    		<li><a href="/aladin/itemSearchPage?SearchTarget=Book">국내도서</a></li>
    		<li><a href="/aladin/itemSearchPage?SearchTarget=eBook">전자책</a></li>
    		<li><a href="/aladin/itemSearchPage?SearchTarget=Foreign">외국도서</a></li>
    		<li><a href="/aladin/itemSearchPage?SearchTarget=Music">음반</a></li>
    		<li><a href="/aladin/itemSearchPage?SearchTarget=DVD">DVD</a></li>
    	</ul>
    	
    	<div>
	    	<c:if test="${not empty bestSeller}">
	    		<c:forEach var="book" items="${bestSeller}">
	    			<div style="margin-bottom: 20px;">
	    				<img src="${book.imageUrl}" width="100" height="150"/>
	    				<div><strong>${book.title}</strong></div>
	    				<div>${book.author}</div>
	    			</div>
	    		</c:forEach>
	    	</c:if>
    	</div>
    	<jsp:include page="util/footer.jsp" />
	</div>
</body>
</html>