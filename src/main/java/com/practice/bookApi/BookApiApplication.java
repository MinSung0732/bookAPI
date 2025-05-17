package com.practice.bookApi;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.practice.bookApi.aladin.service.AladinService;

@Controller
@SpringBootApplication
@MapperScan("com.practice.bookApi.users.sign.mapper")
public class BookApiApplication {
	
	private final AladinService aladinService;
	
	public BookApiApplication(AladinService aladinService) {
		this.aladinService = aladinService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookApiApplication.class, args);
	}
	
	@RequestMapping(value = "/", method= {RequestMethod.GET, RequestMethod.POST})
	public String home(HttpSession session,
						Model model) throws Exception {
		System.out.println("BookApiApplication RestController 진입");
		
		String loginUser = (String) session.getAttribute("Login");
		
		if (loginUser != null) {
			model.addAttribute("loginUser", loginUser);
		}
		System.out.println("현재 로그인된 세션 ID값: " + loginUser);
		
		model.addAttribute("bestSeller", aladinService.getWeeklyBestSellers());
		
		return "/index";
	}

}
