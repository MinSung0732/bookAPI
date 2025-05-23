$(function () {
    $('#buyBook').on('click', function () {
        const link = $(this).data('link');
        window.open(link, '_blank');
    });
});