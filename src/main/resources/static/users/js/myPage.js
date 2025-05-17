$(function() {

    $('#changePwBtn').click(function() {
        $.ajax({
            type: 'POST',
            url: '/mail/pw/sendCode',
            success: function (response) {
                alert(response);
                $('#emailblock').html(`
                <input type="password" id="newPw" placeholder="새 비밀번호"><br>
                <input type="text" id="emailCode" placeholder="인증 코드"><br>
                <button id="verifyCode">인증확인</button><br>
                <button id="changePwFinal">비밀번호 변경</button>
                `);
            },
            error: function (xhr) {
                alert(xhr.responseText);
            }
        });
    });

    $('#emailblock').on('click', '#verifyCode', function() {
        const code = $('#emailCode').val();
        $.ajax({
            type: 'POST',
            url: '/mail/pw/verifyCode',
            data: {
                code: code
            },
            success: function (response) {
                alert(response);
                $('#verifyCode').attr("disabled", true);
            },
            error: function (xhr) {
                alert(xhr.responseText);
            }
        });
    });

    $('#emailblock').on('click', '#changePwFinal', function() {
        const newPw = $('#newPw').val();
        if (!/^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*(),.?":{}|<>])[A-Za-z\d!@#$%^&*(),.?":{}|<>]{8,20}$/.test(newPw)) {
            alert("비밀번호는 영문자, 숫자, 특수문자를 포함해 8~20 글자로 가능합니다.");
            return;
        } else {
            $.ajax({
                type: 'POST',
                url: '/users/pwUpdate',
                data: {
                    newPw: newPw
                },
                success: function (response) {
                    alert(response);
                    $('#emailblock').empty();
                },
                error: function (xhr) {
                    alert(xhr.responseText);
                }
            });
        }
    });
})