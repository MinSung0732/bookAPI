<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="/users/css/itemList.css" rel="stylesheet" type="text/css">
<title>${itemDetail.title} 상세보기</title>
</head>
<body>
	<div id="container">
		<jsp:include page="../util/header.jsp" />
		<div id="item-detail">
	        <h2>${itemDetail.title}</h2>
	        <img src="${itemDetail.imageUrl}" alt="책 표지" width="200"/>
	        <p><strong>저자:</strong> ${itemDetail.author}</p>
	        <strong>판매가: </strong> ${itemDetail.item[0].priceSales}원<br>
	        <strong>정가: </strong> <del>${itemDetail.item[0].priceStandard}원</del>
	        <button type="button" id="buyBook" data-link="${itemDetail.item[0].link}">구매하기 - 알라딘2</button>
	        
	        <!-- 추가적인 책 상세 정보를 여기서 출력 -->
	    </div>
	    <div id="bookInfo">
	    	<div id="basicInfo">
	    		<h3>기본 정보</h3>
	    		<strong>상품 쪽수:</strong> ${itemDetail.item[0].subInfo.itemPage} <br>
	    	</div>
	    	<div id="descInfo">
	    		<br>
	    		<br>
	    		<strong>내용 요약:</strong> ${itemDetail.description}<br>
	    	</div>
	    	<div id="reviewInfo">
	    		<br>
	    		<strong>리뷰 펑점:</strong> ${itemDetail.item[0].customerReviewRank}
	    	</div>
	    </div>
		<jsp:include page="../util/footer.jsp" />
	</div>
	<script src="/users/js/itemDetail.js"></script>
</body>
</html>