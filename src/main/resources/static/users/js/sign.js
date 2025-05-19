$(function() {

	let idck = 0;
	let emailVerified = false;

	function sendEmailCode() {
		const userMail = $('#userMail').val() + '@' + $('#mail').val();

		$.ajax({
			type: 'POST',
			url: '/mail/sendCode',
			data: {
				userMail: userMail
			},
			success: function(response) {
				alert(response.message);
				$("#emailcheck").attr("disabled", true);
			},
			error: function(xhr) {
				alert("이메일 전송 실패: " + xhr.responseText);
			}
		});
	}

	function verifyEmailCode() {
		const code = $('#emailCode').val();

		$.ajax({
			type: 'POST',
			url: '/mail/verifyCode',
			data: {
				inputCode: code
			},
			success: function(response) {
				alert(response.message);
				$('#codeCk').hide();
				$('#emailCode').prop('disabled', true);
				emailVerified = true;
			},
			error: function(xhr) {
				alert("인증 실패: " + xhr.responseText);
			}
		});

	}

	function openPostcode() {
		new daum.Postcode({
			oncomplete: function(data) {
				const postcode = data.zonecode;
				const address = data.address;
				const jibunAddress = data.jibunAddress;

				$('#userAddr1').val(address);
				$('#postCode').val(postcode);
				$('#userJibunAddress').val(jibunAddress);
			}
		}).open();
	}

	function signup() {
		const userId = $('#userId').val();
		const userPw = $('#userPw').val();
		const userRePw = $('#rePw').val();
		const userName = $('#userName').val();
		const userMail = $('#userMail').val()
		const userCom = $('#mail').val();
		const userEmail = userMail + "@" + userCom;
		const gender = $(":input:radio[name=gender]:checked").val();
		const birth = $('#birthday').val();
		const addr1 = $('#userAddr1').val();
		const addr2 = $('#userAddr2').val();

		if (userId == '') {
			alert("아이디를 입력해주세요.");
			return;
		} else if (!/^[A-Za-z0-9]{4,12}$/.test(userId)) {
			alert("아이디는 영어와 숫자 조합으로 4~12 글자로 가능합니다.");
			return;
		} else if (!/^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*(),.?":{}|<>])[A-Za-z\d!@#$%^&*(),.?":{}|<>]{8,20}$/.test(userPw)) {
			alert("비밀번호는 영문자, 숫자, 특수문자를 포함해 8~20 글자로 가능합니다.");
			return;
		} else if (userPw == '') {
			alert("비밀번호를 입력해주세요.");
			return;
		} else if (userRePw == '') {
			alert("비밀번호 확인을 입력해주세요.");
			return;
		} else if (userPw != userRePw) {
			alert("비밀번호와 비밀번호 확인이 다릅니다.");
			return;
		} else if (userName == '') {
			alert("이름을 입력해주세요.");
			return;
		} else if (userMail == '' || userCom == '') {
			alert("이메일을 입력해주세요.");
			return;
		} else if (!emailVerified) {
			alert("이메일 인증을 완료해주세요.");
			return;
		} else if (birth == '') {
			alert("생년월일을 입력해주세요.");
			return;
		} else if (addr1 == '') {
			alert("주소지를 입력해주세요.");
			return;
		} else if (idck == 0) {
			alert("아이디 중복체크를 해주세요.");
			return;
		} else {
			$.ajax({
				type: 'POST',
				url: "/users/signUp",
				contentType: "application/json",
				dataType: "JSON",
				data: JSON.stringify({
					userId: userId,
					userPw: userPw,
					userName: userName,
					userMail: userEmail,
					gender: gender,
					birthday: birth,
					userAddr1: addr1,
					userAddr2: addr2,
					deleteYn: 0,
					adminCk: 0
				}),
				success: function(response) {
					if (response.result === 1) {
						alert(response.message);
						window.location.href = "/";
					} else if (response.result === -1) {
						alert("회원가입 실패: " + response.message);
					} else {
						alert("회원가입 실패: " + response.message);
					}
				},
				error: function(xhr) {
					alert("에러 발생: " + xhr.responseText);
				}
			});
		}
	}



	$('#signUpSubmit').click(function() {
		signup()
	});

	$('#idck').click(function() {

		const userId = $("#userId").val();

		if (!/^[A-Za-z0-9]{4,12}$/.test(userId)) {
			alert("아이디는 영어와 숫자 조합으로 4글자이상 12글자 이하로 가능합니다.");
		} else {
			$.ajax({
				async: true,
				type: 'POST',
				data: userId,
				url: "/users/idcheck",
				dataType: "json",
				contentType: "application/json; charset=UTF-8",
				success: function(data) {
					if (data.cnt > 0) {
						alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
					} else {
						alert("사용가능한 아이디입니다.");
						idck = 1;
						$("#idck").attr("disabled", true);
					}
				},
				error: function(error) {
					alert("error : " + error);
				}
			})
		}

	});

	$('#emailcheck').click(function() {
		sendEmailCode()
		$("#emailCode").removeAttr("disabled");
		$("#codeCk").show();
		$('.certified').addClass('show').removeClass('hide');
	});

	$('#codeCk').click(function() {
		verifyEmailCode();
	})

	$('#openPostcode').click(function() {
		openPostcode();
	})

	$('#userId').change(function() {
		idck = 0;
		$("#idck").removeAttr("disabled");
	});

	$('#selectMail').on('change', function() {
		const mail = $('input[name=mail]');
		const val = $(this).val();

		if (val === "userEmailInput") {
			mail.removeAttr('readonly').val('');
		} else {
			mail.attr('readonly', true).val(val);
		}

	});
});

$(function() {
    $('#selectMail').change(function() {
        const mailVal = $(this).val();

        if (mailVal === 'userEmailInput') {
            $('#mail').removeClass('hide').addClass('show');
        } else {
            $('#mail').removeClass('show').addClass('hide');
        }
    });
});
