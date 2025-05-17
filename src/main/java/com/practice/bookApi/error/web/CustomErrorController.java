package com.practice.bookApi.error.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {
	
	public String handleError(HttpServletRequest request,
								HttpServletResponse response,
								Model m) {
		int statusCode = response.getStatus();
		m.addAttribute("status", statusCode);
		
		switch (statusCode) {
			case 404:
				m.addAttribute("message", "요청하신 페이지를 찾을 수 없습니다.");
				return "error/404";
			case 500:
				m.addAttribute("message", "서버 내부 오류가 발생했습니다.");
				return "error/500";
			default:
				m.addAttribute("message", "알 수 없는 오류가 발생했습니다.");
				return "error/default";
		}
	}
}
