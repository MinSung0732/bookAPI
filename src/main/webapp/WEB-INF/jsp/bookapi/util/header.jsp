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
		<div>
			<img src="${pageContext.request.contextPath}/users/images/hamburger.svg">
		</div>
		<div>
			<a href="/"><img src="${pageContext.request.contextPath}/users/images/mainlogo1.svg"></a>
		</div>
		<ul>
			<li>ACCOUNT</li>
			<li>BOOK</li>
			<li>LOGIN</li>
		</ul>
	</header>
</body>
</html>