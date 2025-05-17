$(function() {
    let currentPage = 1;
    let currentQuery = "";
    let sortType = "sim";
    const itemsPerPage = 10;
    const totalTitems = 0;
    
    $(document).on("click", ".read-more", function() {
        let index = $(this).data("index");
        let descElement = $("#desc-" + index);
        
        if (descElement.hasClass("expanded")) {
            descElement.removeClass("expanded");
            $(this).text("더보기");
        } else {
            descElement.addClass("expanded");
            $(this).text("접기");
        }
    });

    $("#sortType").on("change", function() {
        sortType = $(this).val();
    });

    function highlightTxt(text, query) {
        if (!query) return text;
        let escapedQuery = query.replace(/[.*+?^${}()|[\]\\]/g, '\\$&'); // 정규식 특수문자 이스케이프
        let regex = new RegExp("(" + escapedQuery + ")", "gi");
        return text.replace(regex, "<span class='highlight'>$1</span>");
    }
    
    $("#submit").on("click", function() {
        currentQuery = $("#searchBar").val();
        currentPage = 1;
        searchBooks(currentQuery, currentPage);
    });
    
    $("#searchBar").on("keypress", function(e) {
        if (e.which === 13) {
            currentQuery = $("#searchBar").val();
            currentPage = 1;
            searchBooks(currentQuery, currentPage);
        }
    });
    
    function searchBooks(query, page) {
        if (!query) {
            alert("검색어를 입력해주세요.");	
        }
        
        let start = (page - 1) * itemsPerPage + 1;
        
        $.ajax({
            type: "GET",
            url: "/users/userSearch",
            data: {
                query: query,
                display: itemsPerPage,
                start: start,
                sort: sortType
            },
            success: function(response) {
                console.log("검색 결과:", response);
                displayResults(response);
                
                totalItems = response.total;
                
                updatePagination(page);
            },
            error: function(xhr, status, error) {
                console.error("API 호출 오류:", error);
                $("#searchDiv").html("<p>검색 중 오류가 발생했습니다. 다시 시도해주세요.</p>");
            }
        });
    }
    
    function displayResults(data) {
        let resultsHTML = "";
        
        if (data.total === 0) {
            resultsHTML = "<p>검색 결과가 없습니다.</p>";
        } else {
            resultsHTML = "<h3>검색 결과: 총 " + data.total + "건</h3>";
            
            $.each(data.items, function(index, book) {
                let title = highlightTxt(book.title.replace(/<\/?b>/g, ""), currentQuery);
                let author = highlightTxt(book.author.replace(/<\/?b>/g, ""), currentQuery);
                let description = book.description ? highlightTxt(book.description.replace(/<\/?b>/g, ""), currentQuery) : "설명 없음";
                /* const description = book.description ? book.description.replace(/<\/?b>/g, "") : "설명 없음"; */
                
                resultsHTML += '<div class="book-item">' +
                    (book.image ? '<div class="book-image"><img src="' + book.image + '" alt="' + title + ' 표지" width="250px" height="350px"></div>' : '') +
                    '<div class="book-title">' + title + '</div>' +
                    '<div class="book-info">' +
                    '<span class="book-author">저자: ' + author + '</span> | ' +
                    '<span class="book-publisher">출판사: ' + book.publisher + '</span> | ' +
                    '<span class="book-pubdate">출판일: ' + formatDate(book.pubdate) + '</span>' +
                    '</div>' +
                    '<div class="book-price">가격: ' + book.discount + '원</div>' +
                    '<div class="book-description" id="desc-' + index + '">' + description + '</div>' +
                    '<span class="read-more" id="more-' + index + '" data-index="' + index + '">더보기</span>' +
                    '<div class="book-buy"><a href="' + book.link + '">구매하기</a></div>' +
                    '</div>';
            });
        }
        $("#searchDiv").html(resultsHTML);
        console.log("검색 결과 표시 완료 - 더보기 버튼 수: " + $(".read-more").length);
    }
    
    function updatePagination(currentPage) {
        let totalPages = Math.ceil(totalItems / itemsPerPage);
        let paginationHTML = "";
        
        // 최대 표시할 페이지 버튼 수
        const maxPageButtons = 10;
        
        // 시작 페이지 계산 - 현재 페이지를 기준으로 10개 단위로 그룹핑
        let startPage = Math.floor((currentPage - 1) / maxPageButtons) * maxPageButtons + 1;
        let endPage = Math.min(totalPages, startPage + maxPageButtons - 1);
        
        // "처음" 버튼
        paginationHTML += '<a onclick="goToPage(1)" style="cursor:pointer;">처음</a> ';
        
        // "이전" 버튼 (<)
        if (currentPage > 1) {
            paginationHTML += '<a onclick="goToPage(' + (currentPage - 1) + ')" style="cursor:pointer;"> &lt; </a> ';
        } else {
            paginationHTML += '<a class="disabled"> &lt; </a> ';
        }
        
        // 페이지 번호 표시
        for (let i = startPage; i <= endPage; i++) {
            if (i === currentPage) {
                paginationHTML += '<a class="active" style="font-weight:bold;">' + i + '</a> ';
            } else {
                paginationHTML += '<a onclick="goToPage(' + i + ')" style="cursor:pointer;">' + i + '</a> ';
            }
        }
        
        // "다음" 버튼 (>)
        if (currentPage < totalPages) {
            paginationHTML += '<a onclick="goToPage(' + (currentPage + 1) + ')" style="cursor:pointer;"> &gt; </a> ';
        } else {
            paginationHTML += '<a class="disabled"> &gt; </a> ';
        }
        
        // "마지막" 버튼
        paginationHTML += '<a onclick="goToPage(' + totalPages + ')" style="cursor:pointer;">마지막</a>';
        
        $("#paginationDiv").html(paginationHTML);
    }
    
    function formatDate(dateStr) {
        if (!dateStr || dateStr.length !== 8) return dateStr;
        return dateStr.substring(0, 4) + '-' + dateStr.substring(4, 6) + '-' + dateStr.substring(6, 8);
    }
    
    window.goToPage = function(page) {
        currentPage = page;
        searchBooks(currentQuery, page);
    };
});