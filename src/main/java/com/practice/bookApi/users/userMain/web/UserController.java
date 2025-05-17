package com.practice.bookApi.users.userMain.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practice.bookApi.users.sign.dto.UserDto;
import com.practice.bookApi.users.sign.service.MyPageService;
import com.practice.bookApi.users.userMain.dto.UserRequestDto;
import com.practice.bookApi.users.userMain.dto.UserResponseDto;
import com.practice.bookApi.users.userMain.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	MyPageService mypageService;
	
	@RequestMapping(value = "/userMain", method= {RequestMethod.GET})
	public ModelAndView userMain(HttpSession session,
								 RedirectAttributes redirectAttributes) throws Exception {
		System.out.println("UserController 진입 ==> userMain 사용");
		
		String loginUser = (String) session.getAttribute("Login");
		UserDto dto = mypageService.getUserById(loginUser);
		ModelAndView mav = new ModelAndView("/users/userMain");
		
		if (loginUser == null) {
			redirectAttributes.addFlashAttribute("message", "로그인이 필요합니다.");
			mav = new ModelAndView("/users/signInPage");
			return mav;
		}
		
		mav.addObject("userDto", dto);
		
		return mav;
	}
	
	@RequestMapping(value = "/userSearch", method= {RequestMethod.GET})
	@ResponseBody
	public UserResponseDto.UserSearchResponse userSearch(@ModelAttribute UserRequestDto requestVo, HttpSession session) throws Exception {
		System.out.println("UserController 진입 ==> userSearch 사용");
		
		UserResponseDto.UserSearchResponse response = userService.getUserSearchResults(requestVo);
		
		session.setAttribute("searchResponse", response);
		return response;
	}
	
}
