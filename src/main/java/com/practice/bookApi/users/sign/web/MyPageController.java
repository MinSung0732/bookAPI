package com.practice.bookApi.users.sign.web;

import java.text.SimpleDateFormat;

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
	
	@RequestMapping(value = "/myPage", method = {RequestMethod.GET})
	public ModelAndView mypage(HttpSession session, Model model) {
		
		System.out.println("MyPageController 진입 ==> myPage 사용");
		System.out.println(session.getAttribute("Login"));

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
	
	@RequestMapping(value = "/pwUpdate", method = RequestMethod.POST)
	public ResponseEntity<?> pwUpdate(@RequestParam String newPw,
										HttpSession session) {
		
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

}
