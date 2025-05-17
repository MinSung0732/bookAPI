$(function() {

    function signin() {
        let userId = $('#userId').val();
        let userPw = $('#userPw').val();
        let autoLoginChecked = $('.check-box').is(':checked');

        if (userId == '') {
            alert("아이디를 입력해주세요.");
            return;
        } else if (userPw == '') {
            alert("비밀번호를 입력해주세요.");
            return;
        } else {
            $.ajax({
                type: 'POST',
                url: "/users/signIn",
                data: {
                    userId: userId,
                    userPw: userPw
                },
                success: function (response) {
                    if (response.result === 0) {
                        $.ajax ({
                            type: 'GET',
                            url: "/users/successCheckLogin",
                            data: {
                                userId: userId,
                                autoLogin: (autoLoginChecked ? "on" : "")
                            },
                            success: function(response) {
                                if (response.result === 0) {
                                    window.location.href = "/";
                                }
                            },
                            error: function(xhr) {
                                alert("로그인 처리 중 오류가 발생했습니다.");
                                console.error(xhr.responseText);
                            }
                        });
                    } else {
                        alert("로그인 실패: " + response.message);
                    }
                },
                error: function (xhr) {
                    alert("에러 발생: " + xhr.responseText);
                }
                
            });
        }
    } 

    $('#signInSubmit').click(function() {
       signin()
    });

    $('#userPw').on("keypress", function(e) {
        if(e.which === 13) {
            signin()
        }
    });

    $('#userId').on("keypress", function(e) {
        if(e.which === 13) {
            signin()
        }
    });

});