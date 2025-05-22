package com.practice.bookApi.aladin.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.practice.bookApi.aladin.dto.AladinDto;
import com.practice.bookApi.aladin.service.AladinService;

@RestController
@RequestMapping("/aladin")
public class AladinApiController {
	
	@Autowired
	AladinService aladinService;
	
	 @RequestMapping(value="/itemListPage", method= {RequestMethod.GET})
	 public ModelAndView itemList (HttpSession session,
			 						@RequestParam("SearchTarget") String searchTarget) throws Exception {
	 System.out.println("선택된 카테고리: " + searchTarget);
	 
	 List<AladinDto> itemList = aladinService.getItemList(searchTarget);
	 ModelAndView mav = new ModelAndView("/aladin/itemListPage");
	 mav.addObject("itemList", itemList);
	 mav.addObject("searchTarget", searchTarget);
	 return mav;
	 }
	 
	 @RequestMapping(value="/itemListAjax", method= {RequestMethod.GET})
	 public Map<String, String> itemListAjax(@RequestParam("searchTarget") String searchTarget,
			 								@RequestParam("page") int page) throws Exception {
		 int pageSize = 20;
		 int start = (page - 1) * pageSize + 1;
		 
		 List<AladinDto> allItems = aladinService.getItemList(searchTarget);
		 int total = allItems.size();
		 
		 List<AladinDto> pagedItems = allItems.stream()
				 .skip((long) (page - 1) * pageSize)
				 .limit(pageSize)
				 .collect(Collectors.toList());
		 
		 String html = generateItemHtml(pagedItems);
		 String pagination = generatePaginationHtml(page, total, pageSize);
		 
		 Map<String, String> result = new HashMap<>();
		 result.put("html",html);
		 result.put("pagination", pagination);
		 return result;
	 }
	 
	 private String generatePaginationHtml(int currentPage, int total, int pageSize) {
		    int totalPages = (int) Math.ceil((double) total / pageSize);
		    StringBuilder sb = new StringBuilder();
		    for (int i = 1; i <= totalPages; i++) {
		        sb.append("<button onclick='loadItems(").append(i).append(")'>")
		          .append(i).append("</button>");
		    }
		    return sb.toString();
		}

	 
	 private String generateItemHtml(List<AladinDto> items) {
		    StringBuilder sb = new StringBuilder();
		    sb.append("<ul class='item-list'>");
		    for (AladinDto item : items) {
		        sb.append("<li class='item'>");

		        // 책 상세페이지로 이동하는 링크 추가 (ISBN 사용)
		        String detailUrl = "/aladin/itemDetailPage?isbn13=" + item.getIsbn13();
		        AladinDto.AladinItem aladinItem = item.getItem().get(0);
		        
		        sb.append("<a href='").append(detailUrl).append("'>");
		        sb.append("<img src='").append(item.getImageUrl()).append("' alt='표지 이미지' width='100'/>");
		        sb.append("</a>");
		        sb.append("<div class='info'>");
		        sb.append("<strong>").append(item.getTitle()).append("</strong><br>");
		        sb.append("<span>").append(item.getAuthor()).append("</span><br>");
		        sb.append("<span>").append("판매가: " + aladinItem.getPriceSales() + "원").append("</span><br>");
		        sb.append("<span>").append("정가: <del>" + aladinItem.getPriceStandard() + "</del>원").append("</span>");
		        sb.append("</div>");

		        sb.append("</li>");
		    }
		    sb.append("</ul>");
		    return sb.toString();
		}
	 
	 @RequestMapping(value="/itemDetailPage", method= {RequestMethod.GET})
	 public ModelAndView itemDetail (HttpSession session,
			 						@RequestParam("isbn13") String isbn13) throws Exception {
	 AladinDto itemDetail = aladinService.getItemDetailByIsbn(isbn13);
	 
	 ModelAndView mav = new ModelAndView("/aladin/itemDetailPage");
	 mav.addObject("itemDetail", itemDetail);
	 
	 return mav;
	 }
}
