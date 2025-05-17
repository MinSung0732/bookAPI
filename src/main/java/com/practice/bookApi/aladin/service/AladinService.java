package com.practice.bookApi.aladin.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.bookApi.aladin.dto.AladinDto;

@Service
public class AladinService {

	@Value("${aladin.TTBKey}")
	private String ttbKey;
	private final RestTemplate restTemplate = new RestTemplate();
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	public List<AladinDto> getWeeklyBestSellers() {
		String url = "http://www.aladin.co.kr/ttb/api/ItemList.aspx"
				+ "?ttbkey=" + ttbKey
				+ "&QueryType=Bestseller"
				+ "&MaxResults=10"
				+ "&SearchTarget=Book"
				+ "&OutPut=JS"
				+ "&Version=20131101";
		
		try {
			String json = restTemplate.getForObject(url, String.class);
			JsonNode root = objectMapper.readTree(json);
			JsonNode items = root.get("item");
			
			List<AladinDto> books = new ArrayList<>();
			for (JsonNode item : items) {
				AladinDto book = new AladinDto();
				book.setTitle(item.get("title").asText());
				book.setAuthor(item.get("author").asText());
				book.setImageUrl(item.get("cover").asText());
				books.add(book);
			}
			return books;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
		
	}
}
