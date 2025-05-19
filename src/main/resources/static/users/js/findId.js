$(function() {
   
    let timer;
    let time = 300;

    function startTimer() {
        timer = setInterval(function () {
            if(time <= 0) {
                clearInterval(timer);
                alert("인증 시간이 초과되었습니다.");
                $("#ckCode, #ckCodeBtn, #ckCodeText, #timeForCheck").hide();
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
            url: "/mail/findId/sendCode",
            type: "POST",
            data: {
                userMail
            },
            success: function (res) {
                if (res.result === 1) {
                    alert("인증코드가 전송되었습니다.");
                    $("#ckCode, #ckCodeBtn, #ckCodeText, #timeForCheck").show();
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
            url: "/mail/findId/verifyCode",
            type: "POST",
            data: {
                inputCode
            },
            success: function (res) {
                if (res.result === 1) {
                    clearInterval(timer);
                    alert("이메일 인증에 성공하셨습니다.");

                    $.ajax({
                        url: "/users/findId/getId",
                        type: "POST",
                        success: function (res) {
                            if (res.result === 1) {
                                alert("해당 이메일로 가입한 아이디: " + res.userId);
                                window.location.href = "/"
                            } else {
                                alert(res.message || "아이디를 찾을 수 없습니다.");
                            }
                        },
                        error: function () {
                            alert("서버와의 연결에 실패했습니다.");
                        }
                    });
                } else {
                    alert(res.message || "인증에 실패했습니다.");
                }
            },
            error: function () {
                alert("서버와의 연결에 실패했습니다.");
            }
        });
    });
});