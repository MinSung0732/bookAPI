package com.practice.bookApi.users.sign.web;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practice.bookApi.users.sign.service.SignInService;

@RestController
@RequestMapping("/users")
public class SignInController {
	
	@Autowired
	SignInService signInService;
	
	@RequestMapping(value = "/signInPage", method = {RequestMethod.GET})
	public ModelAndView signInPage() throws Exception {
		
		System.out.println("signUpController 진입 ==> signInPage 사용");
		
		ModelAndView mav = new ModelAndView("/users/signInPage");
		return mav;
	}
	
	@RequestMapping(value = "/signIn", method = {RequestMethod.POST})
	public ResponseEntity<?> signIn(@RequestParam String userId,
									@RequestParam String userPw,
									@RequestParam(required = false) String autoLogin,
									RedirectAttributes rs) throws Exception {
		
		System.out.println("signInController 진입 ==> signIn 사용");
		System.out.println("받아온 ID값: " + userId);
		System.out.println("받아온 PW값: " + userPw);
		
		int result = signInService.userCheck(userId, userPw);
		System.out.println("signin result값: " + result);
		
		if(result == 0) {
			rs.addAttribute("userId", userId);
			rs.addAttribute("autoLogin", autoLogin);
			return ResponseEntity.ok().body(Map.of("result", 0, "message", "로그인 성공"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("result", 1, "message", "로그인 실패"));
		}
	}
	
	@RequestMapping(value = "/successCheckLogin", method = {RequestMethod.GET})
	public ResponseEntity<?> successCheckLogin(@RequestParam String userId,
										  HttpSession session,
										  @RequestParam(required = false) String autoLogin,
										  HttpServletResponse response) throws Exception {
		
		System.out.println("signInController 진입 ==> successCheckLogin 사용");
		
		String userName = signInService.getUserName(userId);
		
		session.setAttribute("Login", userId);
		session.setAttribute("UserName", userName);
		
		if(autoLogin != null) {
			int limitTime = 60*60*24*90;
			Cookie loginCookie = new Cookie("loginCookie", session.getId());
			loginCookie.setPath("/");
			loginCookie.setMaxAge(limitTime);
			response.addCookie(loginCookie);
			
			Calendar calD = Calendar.getInstance();
			calD.setTime(new Date());
			calD.add(Calendar.MONTH, 3);
			java.sql.Date limitDate = new java.sql.Date(calD.getTimeInMillis());
			signInService.keepLogin(session.getId(), limitDate, userId);
		}
		return ResponseEntity.ok().body(Map.of("result", 0, "message", "성공"));
	}
	
	@RequestMapping(value = "/logout", method = {RequestMethod.GET})
	public ModelAndView logout(HttpSession session,
								HttpServletResponse response,
								@CookieValue(value="loginCookie", required=false) Cookie loginCookie) throws Exception {
		
		System.out.println("signInController 진입 ==> logout 사용");
		
		if (session.getAttribute("Login") != null) {
			if (loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				signInService.keepLogin("nan", new java.sql.Date(System.currentTimeMillis()),
						(String)session.getAttribute("Login"));
			}
		}
		session.invalidate();
		ModelAndView mav = new ModelAndView("/index");
		return mav;
	}
}
