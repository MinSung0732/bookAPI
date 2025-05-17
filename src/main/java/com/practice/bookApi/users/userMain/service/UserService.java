package com.practice.bookApi.users.userMain.service;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.bookApi.users.userMain.dto.UserRequestDto;
import com.practice.bookApi.users.userMain.dto.UserResponseDto;

@Service
public class UserService {
	
	@Value("${naver.client.id}")
	private String clientId;
	@Value("${naver.client.secret}")
	private String clientSecret;

	public UserResponseDto.UserSearchResponse getUserSearchResults(UserRequestDto requestVo) {
		
		System.out.println("UserService Client Id => " + clientId);
		System.out.println("UserService Client Secret => " + clientSecret);
		
		String apiURL = "https://openapi.naver.com/v1/search/book?query="
						+ requestVo.getQuery() 
						+ "&display=" + requestVo.getDisplay() 
						+ "&start=" + requestVo.getStart() 
						+ "&sort=" + requestVo.getSort();
		
		System.out.println("UserService ApiURL => " + apiURL);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-Naver-Client-Id", clientId);
		headers.set("X-Naver-Client-Secret", clientSecret);
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate.exchange(
				apiURL,
				HttpMethod.GET,
				entity,
				String.class
		);
		
		
		String responseBody = response.getBody();
		System.out.println("네이버 API 응답: " + responseBody);
		
		UserResponseDto.UserSearchResponse searchResponse = parseResponse(responseBody);
		
		return searchResponse;
	}
	
	private UserResponseDto.UserSearchResponse parseResponse(String responseBody) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(responseBody, UserResponseDto.UserSearchResponse.class);
		} catch (IOException e) {
			System.out.println("파싱 오류 상세 정보: " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("응답 파싱 실패", e);
		}
	}
	
}
