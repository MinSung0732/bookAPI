<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/users/css/header.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<header>
<<<<<<< HEAD:src/main/webapp/WEB-INF/jsp/bookapi/users/header.jsp
		<div class="hamburger">
			<img src="images/hamburger.svg">
=======
		<div>
			<img src="${pageContext.request.contextPath}/users/images/hamburger.svg">
>>>>>>> samusilMinsung:src/main/webapp/WEB-INF/jsp/bookapi/util/header.jsp
		</div>
		<div>
			<a href="/"><img src="${pageContext.request.contextPath}/users/images/mainlogo1.svg"></a>
		</div>
		<ul class="rightMenu">
			<li>ACCOUNT</li>
			<li>BOOK</li>
			<li>LOGIN</li>
		</ul>
	</header>
	<aside class="side-bar">
		<section class="side-bar__icon-box">
			<section class="side-bar__icon-1">
				<div></div>
				<div></div>
				<div></div>
			</section>
		</section>
		<ul class="headerMenu">
			<li><a href="#">♤ menu1</a>
				<ul>
					<li><a href="#">text1</a></li>
					<li><a href="#">text2</a></li>
					<li><a href="#">text3</a></li>
					<li><a href="#">text4</a></li>
				</ul></li>
			<li><a href="#">menu2</a>
				<ul>
					<li><a href="#">text1</a></li>
					<li><a href="#">text2</a></li>
					<li><a href="#">text3</a></li>
					<li><a href="#">text4</a></li>
				</ul></li>
			<!-- 				<li><a href="#">menu3</a>
					<ul>
						<li><a href="#">text1</a></li>
						<li><a href="#">text2</a></li>
						<li><a href="#">text3</a></li>
						<li><a href="#">text4</a></li>
					</ul></li>
				<li><a href="#">menu4</a>
					<ul>
						<li><a href="#">text1</a></li>
						<li><a href="#">text2</a></li>
						<li><a href="#">text3</a></li>
						<li><a href="#">text4</a></li>
					</ul></li> -->
		</ul>
	</aside>
</body>
<script>
$('.hamburger').click(function () {
	$('.side-bar').toggleClass('open');
});
</script>
</html>