package com.practice.bookApi.users.sign.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BookLikeDto {
	private long likeId;
	private long userNo;
	private String isbn13;
	private String title;
	private String image;
	private String author;
	private String pubDate;
	private Timestamp createAt;
}
