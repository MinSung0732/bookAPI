function getSearchTagetFromURL() {
    const params = new URLSearchParams(window.location.search);
    return params.get("SearchTarget") || "Book";
}

function loadItems(page) {
    const searchTarget = getSearchTagetFromURL();

    $.ajax({
        url: "/aladin/itemListAjax",
        method: "GET",
        data: {
            searchTarget: searchTarget,
            page: page
        },
        success: function(data) {
            $("#item-container").html(data.html);
            $("#pagination-block").html(data.pagination);
        },
        error: function(xhr, status, error) {
            console.error("AJAX error:", error);
        }
    });
}

$(function () {
    // 버튼에 동적으로 이벤트를 연결
    $(document).on('click', '#pagination-block button', function () {
        const page = $(this).text();
        loadItems(page);
    });

    loadItems(1);
});
