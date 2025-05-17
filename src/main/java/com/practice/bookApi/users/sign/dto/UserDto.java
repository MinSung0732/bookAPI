package com.practice.bookApi.users.sign.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserDto {
	
	public enum Gender {
		M, F, OTHER
	}
	
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private String userMail;
	private Gender gender;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private String userAddr1;
	private String userAddr2;
	private String deleteYn;
	private String createdDate;
	private String modifiedDate;
	private String sessionId;
	private Date limitTime;
	private int adminCk;
	private int money;

}
