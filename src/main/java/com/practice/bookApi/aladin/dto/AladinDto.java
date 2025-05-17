package com.practice.bookApi.aladin.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class AladinDto {
	private String title;
	private String author;
	private String imageUrl;
	
	@Data
	public class AladinItemSearchResponseDto {
		int version;						// API Version
		String title;						// API 결과의 제목
		String link;						// API 결과와 관련된 알라딘 페이지 URL 주소
		String pubDate;						// API 출력일
		int totalResults;					// API의 총 결과수
		int startIndex;						// Page수
		int itemsPerPage;					// 한 페이지에 출려될 상품 수
		String query;						// API로 조회한 쿼리
		int searchCategoryId;				// 분야로 조회한 경우 해당 분야의 ID
		String searchCategoryName;			// 분야로 조회한 경우 해당 분야의 분야명
		List<aladinItem> item;				// 상품정보
	}
	
	@Data
	public class aladinItem {
		String title;						// 상품명
		String link;						// 상품 링크 URL
		String author;						// 저자/아티스트
		Date pubdate;						// 출간일(출시일)
		String description;					// 상품설명 (요약)
		String isbn;						// 10자리 isbn
		String isbn13;						// 13자리 isnb
		int pricesales;						//	판매가
		int pricestandard;					//	정가
		String mallType;					//	상품의 타입
	}
}
