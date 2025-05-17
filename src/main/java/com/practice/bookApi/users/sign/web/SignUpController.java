package com.practice.bookApi.users.sign.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.practice.bookApi.users.sign.dto.UserDto;
import com.practice.bookApi.users.sign.service.SignUpService;

@RestController
@RequestMapping("/users")
public class SignUpController {
	
	@Autowired
	SignUpService signUpService;
	
	@RequestMapping(value = "/signUpPage", method = {RequestMethod.GET})
	public ModelAndView signUpPage() throws Exception {
		System.out.println("signUpController 진입 ==> signUpPage 사용");
		ModelAndView mav = new ModelAndView("/users/signUpPage");
		return mav;
	}
	
	@RequestMapping(value = "/signUp", method = {RequestMethod.POST})
	public ResponseEntity<?> signUp(@RequestBody UserDto dto) throws Exception {
		System.out.println("signUpController 진입 ==> signUp 사용");
		System.out.println("UserDto: " + dto);
		
		int result = signUpService.registerUser(dto);
		
		if(result==1) {
			return ResponseEntity.ok().body(Map.of("result", 1, "message", "회원가입 성공"));
		} else if(result == -1) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("result", -1, "message", "중복된 이메일입니다."));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("result", 0, "message", "회원가입 실패"));
		}

	}
	
	@RequestMapping(value = "/idcheck", method = {RequestMethod.POST})
	public Map<Object, Object> idcheck(@RequestBody String userId) {
		int count = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		count = signUpService.idcheck(userId);
		map.put("cnt", count);
		
		return map;
	}
}
