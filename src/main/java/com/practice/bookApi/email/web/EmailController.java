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
import com.practice.bookApi.users.sign.dto.UserDto;
import com.practice.bookApi.users.sign.service.MyPageService;

@RestController
@RequestMapping("/mail")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private MyPageService mypageService;
	
	@RequestMapping(value ="/sendCode", method = RequestMethod.POST)
	public ResponseEntity<?> sendCode(@RequestParam String userMail,
										HttpSession session) {
		System.out.println("EmailController 진입 ==> sendCode 사용");
		
		String code = emailService.createCode();
		
		boolean success = emailService.sendVerificationCode (userMail, code);
		if (success) {
			session.setAttribute("authCode", code);
			return ResponseEntity.ok(Map.of("result", 1, "message", "인증코드가 이메일로 전송되었습니다."));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("result", 0, "message", "인증코드 전송 실패"));
		}
	}
	
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
	
	@RequestMapping(value = "/pw/sendCode", method = RequestMethod.POST)
	public ResponseEntity<?> sendPwCode(HttpSession session) {
		String userId = (String) session.getAttribute("Login");
		
		if (userId == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
		}
		
		UserDto dto = mypageService.getUserById(userId);
		String code = emailService.createCode();
		boolean success = emailService.sendVerificationCode(dto.getUserMail(), code);
		if (success) {
			session.setAttribute("pwAuthCode", code);
			return ResponseEntity.ok("인증코드를 이메일로 보냈습니다.");
		} else {
			return ResponseEntity.status(500).body("이메일 전송 실패");
		}
	}
	
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

}
