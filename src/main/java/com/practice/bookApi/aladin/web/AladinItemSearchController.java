package com.practice.bookApi.aladin.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.practice.bookApi.aladin.service.AladinItemSearchService;

@RestController
@RequestMapping("/aladin")
public class AladinItemSearchController {
	
	AladinItemSearchService aladinService;
	
	/*
	 * @RequestMapping(value="/itemSearchPage", method= {RequestMethod.GET}) public
	 * ModelAndView itemSearch (HttpSession session,
	 * 
	 * @RequestParam("SearchTarget") String searchTarget) throws Exception {
	 * System.out.println("선택된 카테고리: " + searchTarget);
	 * 
	 * 
	 * 
	 * }
	 */
}
