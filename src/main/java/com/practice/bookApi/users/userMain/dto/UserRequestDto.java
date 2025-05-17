package com.practice.bookApi.users.userMain.dto;

import lombok.Data;

@Data
public class UserRequestDto {
	
	private String query;		// 필수 여부 : Y (검색어)
	private Integer display;	// 필수 여부 : N (한 번에 표시할 검색 결과 개수)
	private Integer start;		// 필수 여부 : N (검색 시작 위치)
	private String sort;		// 필수 여부 : N (검색 결과 정렬)

}
