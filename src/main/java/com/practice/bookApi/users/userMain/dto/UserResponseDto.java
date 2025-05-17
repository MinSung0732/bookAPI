package com.practice.bookApi.users.userMain.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserResponseDto {
	
	// 전체 응답구조
	@Data
	public static class Item {
		private String title;			// 책 제목
		private String link;			// 네이버 도서 정보 URL
		private String image;			// 섬네일 이미지의 URL
		private String author;			// 저자 이름
		private String discount;		// 판매가격
		private String publisher;		// 출판사
		private String isbn;			// isbn
		private String description;		// 네이버 도서의 책 소개
		private String pubdate;			// 출간일
	}
	
	// 개별 검색 결과 항목
	@Data
	public static class UserSearchResponse {
		private String lastBuildDate;					// 검색 결과를 생성한 시간
		private int total;								// 총 검색 결과 개수
		private int start;								// 검색 시작 위치
		private int display;							// 한 번에 표시할 검색 결과 개수
		private List<Item> items;						// 개별 검색 결과
	}

}
