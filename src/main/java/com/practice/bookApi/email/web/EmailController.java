package com.practice.bookApi.email.web;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.bookApi.email.serivce.EmailService;
import com.practice.bookApi.email.serivce.EmailService.EmailSendResult;
import com.practice.bookApi.users.sign.dto.UserDto;
import com.practice.bookApi.users.sign.service.MyPageService;

@RestController
@RequestMapping("/mail")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private MyPageService mypageService;
	
	
	// 회원가입 이메일 인증코드 발송 경로
	@RequestMapping(value ="/sendCode", method = RequestMethod.POST)
	public ResponseEntity<?> sendCode(@RequestParam String userMail,
										HttpSession session) {
		System.out.println("EmailController 진입 ==> sendCode 사용");
		System.out.println("받아온 유저의 메일값: " + userMail);
		
		if (!emailService.isValidEmail(userMail)) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		        .body(Map.of("result", 0, "message", "이메일 형식이 유효하지 않습니다."));
		}
		
		String code = emailService.createCode();
		
		EmailSendResult result = emailService.sendVerificationCode(userMail, code);
		if (result.isSuccess()) {
			session.setAttribute("authCode", code);
			return ResponseEntity.ok(Map.of("result", 1, "message", "인증코드가 이메일로 전송되었습니다."));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("result", 0, "message", result.getErrorMessage()));
		}
	}
	
	// 회원가입 이메일 인증코드 확인 경로
	@RequestMapping(value = "/verifyCode", method = RequestMethod.POST)
	public ResponseEntity<?> verifyCode(@RequestParam String inputCode, HttpSession session) {
		
		System.out.println("EmailController 진입 ==> verfyCode 사용");
		
		String sessionCode = (String) session.getAttribute("authCode");
		if (sessionCode != null && sessionCode.equals(inputCode)) {
			return ResponseEntity.ok(Map.of("result", 1, "message", "인증 성공"));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("result", 0, "message", "인증 실패"));
		}
	}
	
	
	// 마이페이지 비밀번호 변경 인증코드 발송 경로
	@RequestMapping(value = "/pw/sendCode", method = RequestMethod.POST)
	public ResponseEntity<?> sendPwCode(HttpSession session) {
		String userId = (String) session.getAttribute("Login");
		
		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
		}
		
		UserDto dto = mypageService.getUserById(userId);
		String code = emailService.createCode();
		EmailSendResult result = emailService.sendVerificationCode(dto.getUserMail(), code);
		if (result.isSuccess()) {
			session.setAttribute("pwAuthCode", code);
			return ResponseEntity.ok("인증코드를 이메일로 보냈습니다.");
		} else {
			return ResponseEntity.status(500).body(result.getErrorMessage());
		}
	}
	
	
	// 마이페이지 비밀번호 변경 인증코드 확인 경로
	@RequestMapping(value = "/pw/verifyCode", method = RequestMethod.POST)
	public ResponseEntity<?> verifyPwCode(@RequestParam String code,
											HttpSession session) {
		
		String sessionCode = (String) session.getAttribute("pwAuthCode");
		
		if (code.equals(sessionCode)) {
			session.setAttribute("pwVerified", true);
			return ResponseEntity.ok("인증 성공");
		} else {
			return ResponseEntity.status(400).body("인증 실패");
		}
	}
	
	
	// 로그인 회원 아이디 찾기 인증코드 발송 경로
	@RequestMapping(value = "/findId/sendCode", method = RequestMethod.POST)
	public ResponseEntity<?> sendIdCode (@RequestParam String userMail,
											HttpSession session) throws Exception {
		
		String code = emailService.createCode();
		EmailSendResult result = emailService.sendVerificationCode(userMail, code);
		
		if (result.isSuccess()) {
			session.setAttribute("findIdAuthCode", code);
			session.setAttribute("findIdEmail", userMail);
			return ResponseEntity.ok(Map.of("result", 1, "message", "인증코드를 이메일로 보냈습니다."));
		} else {
			return ResponseEntity.status(500).body(Map.of("result", 0, "message", "이메일 전송 실패"));
		}
		
	}
	
	
	// 로그인 회원 아이디 찾기 인증코드 확인 경로
	@RequestMapping(value = "/findId/verifyCode", method = RequestMethod.POST)
	public ResponseEntity<?> verifyIdCode (@RequestParam String inputCode,
											HttpSession session) throws Exception {
		
		String sessionCode = (String) session.getAttribute("findIdAuthCode");
		
		if(inputCode.equals(sessionCode)) {
			session.setAttribute("findIdVerified", true);
			return ResponseEntity.ok(Map.of("result", 1, "message", "인증 성공"));
		} else {
			return ResponseEntity.status(400).body(Map.of("result", 0, "message", "인증 실패"));
		}
		
	}
	
	
	// 로그인 회원 비밀번호 찾기 인증코드 발송 경로
	@RequestMapping(value = "/findPw/sendCode", method = RequestMethod.POST)
	public ResponseEntity<?> sendPwCode(@RequestParam String userMail, HttpSession session) {
	    String code = emailService.createCode();
	    EmailSendResult result = emailService.sendVerificationCode(userMail, code);

	    if (result.isSuccess()) {
	        session.setAttribute("findPwAuthCode", code);
	        session.setAttribute("findPwEmail", userMail);
	        return ResponseEntity.ok(Map.of("result", 1, "message", "인증코드를 이메일로 보냈습니다."));
	    } else {
	        return ResponseEntity.status(500).body(Map.of("result", 0, "message", "이메일 전송 실패"));
	    }
	}
	
	// 로그인 회원 비밀번호 찾기 인증코드 확인 경로
	@RequestMapping(value = "/findPw/verifyCode", method = RequestMethod.POST)
	public ResponseEntity<?> verifyPassCode(@RequestParam String inputCode, HttpSession session) {
	    String sessionCode = (String) session.getAttribute("findPwAuthCode");

	    if (inputCode.equals(sessionCode)) {
	        session.setAttribute("findPwVerified", true);
	        return ResponseEntity.ok(Map.of("result", 1, "message", "인증 성공"));
	    } else {
	        return ResponseEntity.status(400).body(Map.of("result", 0, "message", "인증 실패"));
	    }
	}

}
