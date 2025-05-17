<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="/users/css/userMain.css" rel="stylesheet" type="text/css">

<title>도서 검색하기</title>
</head>
<body>
	<div id="container">
		<h2>도서 검색</h2>
		${userDto.userName }님 어서오세요. 무엇을 도와드릴까요?<br><br>
		<input type="search" id="searchBar" placeholder="제목 내용 검색"/>
		<select id="sortType">
			<option value="sim" selected>정확순</option>
			<option value="date">날짜순</option>
		</select>
		<button id="submit">검색2</button>
		
		<div id="searchDiv">
			
		</div>
		
		<div id="paginationDiv" class="pagination">
		
		</div>
	</div>
	<script src="/users/js/userMain.js"></script>
</body>
</html>

<!-- 페이지네이션 -->
<!-- var totalPages = Math.ceil(totalItems / itemsPrePage);
				var paginationHTML = "";
				
				var maxPageButtons = 10;
				var startPage = Math.max(1, currentPage - Math.floor(maxPageButtons / 2));
				var endPage = Math.min(totalPages, startPage + maxPageButtons - 1);
				
				if (startPage > 1) {
					paginationHTML += '<a onclick="goToPage(1)">처음</a>';
					paginationHTML += '<a onclick="goToPage(' + (currentPage - 1) + ')">이전</a>';
				}
				
				for (var i = startPage; i <= endPage; i++) {
					if (i === currentPage) {
						paginationHTML += '<a class="active">' + i + '</a>';
					} else {
						paginationHTML == '<a onclick="goToPage(' + i + ')">' + i + '</a>';
					}
				}
				
				if (endPage < totalPages) {
					paginationHTML += '<a onclick="goToPage(' + (currentPage + 1) + ')">다음</a>';
					paginationHTML += '<a onclick="goToPage(' + totalPages + ')">마지막</a>';
				}
				
				$("#paginationDiv").html(paginationHTML);
 -->

<!-- 
var text = $("#searchBar").val();

$.ajax({
					type: "GET",
					url: "/users/userSearch",
					contentType: "application/json",
					data: {
						query: text,
						display: 3,
						start: 1,
						sort: "date"
					},
					success: function(response) {
						console.log(response);
	                },
					error : function(xhr, status, error) {
						console.error("API 호출 오류:", error);
					}
				}); -->