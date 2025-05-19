$(function() {
   
    let timer;
    let time = 300;

    function startTimer() {
        timer = setInterval(function () {
            if(time <= 0) {
                clearInterval(timer);
                alert("인증 시간이 초과되었습니다.");
                $("#ckCodeSection").hide();
            } else {
                time--;
                let minutes = Math.floor(time / 60);
                let seconds = time % 60;
                $("#timeForCheck").text(`${minutes}:${seconds < 10 ? '0' + seconds : seconds}`);
            }
        }, 1000);
    }

    $("#mailCkBtn").click(function () {
        
        const userMail = $("#userMail").val();

        if (!userMail) {
            alert("이메일을 입력하세요.");
            return;
        }

        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(userMail)) {
            alert("올바른 이메일 형식을 입력하세요. 예: example@example.com");
            return;
        }

        $.ajax({
            url: "/mail/findPw/sendCode",
            type: "POST",
            data: {
                userMail
            },
            success: function (res) {
                if (res.result === 1) {
                    alert("인증코드가 전송되었습니다.");
                    $("#ckCodeSection").show();
                    time = 300;
                    startTimer();
                } else {
                    alert(res.message || "이메일 전송에 실패했습니다.");
                }
            },
            error: function () {
                alert("서버와의 연결에 실패했습니다.");
            }
        });

    });

    $("#ckCodeBtn").click(function () {

        const inputCode = $("#ckCode").val();

        if (!inputCode) {
            alert("인증코드를 입력하세요.");
            return;
        }

        $.ajax({
            url: "/mail/findPw/verifyCode",
            type: "POST",
            data: {
                inputCode
            },
            success: function (res) {
                if (res.result === 1) {
                    clearInterval(timer);
                    alert("이메일 인증에 성공하셨습니다.");
                    $("#idCheckSection").show();
                    $("#pwResetSection").show();
                } else {
                    alert(res.message || "인증에 실패했습니다.");
                }
            },
            error: function () {
                alert("서버와의 연결에 실패했습니다.");
            }
        });
    });

    $("#resetPwBtn").click(function () {

        const userId = $("#userId").val().trim();
        const newPw = $("#newPw").val();
        const newPwConfirm = $("#newPwConfirm").val();

        if (!userId || !newPw || !newPwConfirm) {
            alert("모든 항목에 입력을 해주세요.");
            return ;
        }

        if (newPw !== newPwConfirm) {
            alert("비밀번호가 일치하지 않습니다.");
            return;
        }

        if (!/^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*(),.?":{}|<>])[A-Za-z\d!@#$%^&*(),.?":{}|<>]{8,20}$/.test(newPw)) {
            alert("비밀번호는 영문자, 숫자, 특수문자를 포함해 8~20 글자로 가능합니다.");
            return;
        }

        $.ajax({
            url: "/users/findPw/reset",
            type: "POST",
            data: {
                userId: userId,
                newPw: newPw
            },
            success: function (res) {
                if (res.result === 1) {
                    alert("비밀번호가 성공적으로 변경되었습니다.");
                    window.location.href = "/users/signInPage";
                } else {
                    alert(res.message || "비밀번호 변경에 실패했습니다.");
                }
            },
            error: function() {
                alert("서버와의 연결에 실패했습니다.");
            }
        });
    });
});