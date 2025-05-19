package com.practice.bookApi.users.sign.web;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.practice.bookApi.users.sign.dto.UserDto;
import com.practice.bookApi.users.sign.service.MyPageService;

@RestController
@RequestMapping("/users")
public class MyPageController {
	
	@Autowired
	MyPageService mypageService;
	
	// 마이페이지 JSP 이동 경로
	@RequestMapping(value = "/myPage", method = {RequestMethod.GET})
	public ModelAndView mypage(HttpSession session,
								Model model) throws Exception {
		
		ModelAndView mav = new ModelAndView("/users/myPage");
		
		String userId = (String) session.getAttribute("Login");
		
		if (userId == null) {
			mav.setViewName("/users/signInPage");
			return mav;
		}
		
		UserDto dto = mypageService.getUserById(userId);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birthdayStr = sdf.format(dto.getBirthday());
		
		session.setAttribute("userDto", dto);
		model.addAttribute("userDto", dto);
		model.addAttribute("birthdayStr", birthdayStr);
		
		return mav;
	}
	
	// 비밀번호 재설정 컨트롤러
	@RequestMapping(value = "/pwUpdate", method = RequestMethod.POST)
	public ResponseEntity<?> pwUpdate(@RequestParam String newPw,
										HttpSession session) throws Exception {
		
		String userId = (String) session.getAttribute("Login");
		Boolean verified = (Boolean) session.getAttribute("pwVerified");
		
		if (userId == null || verified == null || !verified) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증을 먼저 완료해주세요.");
		}
		
		boolean success = mypageService.updatePassword(userId, newPw);
		if (success) {
			session.removeAttribute("pwVerified");
			return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("비밀번호 변경에 실패했습니다.");
		}
		
	}
	
	// ID 찾기 JSP 이동 경로
	@RequestMapping(value = "/findId", method = RequestMethod.GET)
	public ModelAndView findid(HttpSession session) throws Exception {
		
		ModelAndView mav = new ModelAndView("/users/findId");
		return mav;
	}
	
	
	// ID 찾기 ID 조회 및 반환
	@RequestMapping(value = "/findId/getId", method = RequestMethod.POST)
	public ResponseEntity<?> getUserId (HttpSession session) throws Exception {
		
		Boolean verified = (Boolean) session.getAttribute("findIdVerified");
		String email = (String) session.getAttribute("findIdEmail");
		
		if (verified == null || !verified || email == null) {
			return ResponseEntity.status(401).body(Map.of("result", 0, "message", "이메일 인증이 필요합니다."));
		}
		
		UserDto user = mypageService.getUserByEmail(email);
		if (user != null) {
			return ResponseEntity.ok(Map.of("result", 1, "userId", user.getUserId()));
		} else {
			return ResponseEntity.status(404).body(Map.of("result", 0, "message", "해당 유저를 찾을 수 없습니다."));
		}
	}
	
	// Pw 찾기 JSP 이동 경로
	@RequestMapping(value = "/findPw", method = RequestMethod.GET)
	public ModelAndView findPw(HttpSession session) throws Exception {
		
		ModelAndView mav = new ModelAndView("/users/findPw");
		return mav;
	}
	
	
	// Pw 찾기 비밀번호 재설정
	@RequestMapping(value = "/findPw/reset", method = RequestMethod.POST)
	public ResponseEntity<?> resetPassword(@RequestParam String newPw,
	                                       @RequestParam String userId,
	                                       HttpSession session) throws Exception {
	    String email = (String) session.getAttribute("findPwEmail");

	    if (email == null) {
	        return ResponseEntity.status(401).body(Map.of("result", 0, "message", "이메일 인증이 필요합니다."));
	    }

	    UserDto user = mypageService.getUserByIdAndEmail(userId, email);
	    if (user == null) {
	        return ResponseEntity.status(404).body(Map.of("result", 0, "message", "아이디와 이메일이 일치하지 않습니다."));
	    }

	    boolean updated = mypageService.findForUpdatePassword(userId, email, newPw);
	    if (updated) {
	        return ResponseEntity.ok(Map.of("result", 1));
	    } else {
	        return ResponseEntity.status(500).body(Map.of("result", 0, "message", "비밀번호 변경에 실패했습니다."));
	    }
	}

}
