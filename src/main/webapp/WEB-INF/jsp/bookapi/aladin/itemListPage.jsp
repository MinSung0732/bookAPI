<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="/users/css/itemList.css" rel="stylesheet" type="text/css">
<title>${searchTarget} 상품 페이지</title>
</head>
<body>
	<div id="container">
		<jsp:include page="../util/header.jsp" />
		<div id="item-container">
			
		</div>
		<div id="pagination-block">
		
		</div>
		<jsp:include page="../util/footer.jsp" />
	</div>
	<script src="/users/js/itemList.js"></script>
</body>
</html>