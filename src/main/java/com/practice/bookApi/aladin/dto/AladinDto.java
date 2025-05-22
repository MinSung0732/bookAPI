package com.practice.bookApi.aladin.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class AladinDto {
	private String itemId;
	private String title;
	private String author;
	private String imageUrl;
	private String description;
	private String isbn13;
	private List<AladinItem> item;
	
	/*
	 * @Data public static class AladinItemSearchResponseDto { int version; // API
	 * Version String title; // API 결과의 제목 String link; // API 결과와 관련된 알라딘 페이지 URL
	 * 주소 String pubDate; // API 출력일 int totalResults; // API의 총 결과수 int startIndex;
	 * // Page수 int itemsPerPage; // 한 페이지에 출려될 상품 수 String query; // API로 조회한 쿼리
	 * int searchCategoryId; // 분야로 조회한 경우 해당 분야의 ID String searchCategoryName; //
	 * 분야로 조회한 경우 해당 분야의 분야명 List<AladinItem> item; // 상품정보 }
	 */
	
	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class AladinItem {
		String title;						// 상품명
		String link;						// 상품 링크 URL
		String author;						// 저자/아티스트
		Date pubDate;						// 출간일(출시일)
		String description;					// 상품설명 (요약)
		String isbn;						// 10자리 isbn
		String isbn13;						// 13자리 isnb
		String itemId;						// 책 고유 번호
		int priceSales;						//	판매가
		int priceStandard;					//	정가
		String mallType;					//	상품의 타입
		String stockstatus;					// 재고상태
		int mileage;						// 마일리지
		String cover;						// 표지
		String publisher;					// 출판사(제작사/출사사)
		int salesPoint;						// 판매지수
		boolean adult;						// 성인여부
		boolean fixedPrice;					// 정가제 여부
		int customerReviewRank;				// 회원 리뷰 평점
		String bestDuration;				// 베스트셀러 순위 관련 추가 정보
		int bestRank;						// 베스트셀러 순위 정보
		SeriesInfo seriesInfo;				// 시리즈 ID
		SubInfo subInfo;					// 부가정보
	}
	
	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class SubInfo {
		String subTitle;
		String originalTitle;
		int itemPage;
		List<EbookList> ebookList;
		List<PaperBookList> paperBookList;
		List<FileFormatList> fileFormatList;
	}
	
	@Data
	public static class SeriesInfo {
		int seriesId;
		String seriesLink;
		String seriesName;
	}
	
	@Data
	public static class EbookList {
		int itemId;
		String ISBN;
		String isbn13;
		int priceSales;
		String link;
	}
	
	@Data
	public static class PaperBookList {
		int itemId;
		String isbn;
		String isbn13;
		int priceSales;
		String link;
	}
	
	@Data
	public static class FileFormatList {
		String fileType;
		int fileSize;
	}
}
