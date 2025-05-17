package com.practice.bookApi.users.sign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.bookApi.users.sign.dto.UserDto;
import com.practice.bookApi.users.sign.mapper.UserMapper;

@Service
public class SignUpService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	public int idcheck(String userId) {
		return userMapper.idcheck(userId);
	}
	
	public int mailcheck(String userMail) {
		return userMapper.mailcheck(userMail);
	}
	
	public int registerUser(UserDto userDto) {
		
		System.out.println("SignUpService>>> userDto: " + userDto);
		if (pwEncoder == null) {
	        throw new IllegalStateException("PasswordEncoder is null! 의존성 주입 확인하세요.");
	    }
		
		int mailCount = mailcheck(userDto.getUserMail());
		if (mailCount > 0) {
			return -1;
		}
		
		String securePwd = pwEncoder.encode(userDto.getUserPw());
		userDto.setUserPw(securePwd);
		
		int result = 0;
		String msg = "";
		try {
			result = userMapper.insertUser(userDto);
			msg = "회원가입 완료";
		} catch (Exception e) {
			msg = "회원가입 실패";
			e.printStackTrace();
		}
		
		return result;
	}
	
}
