package com.practice.bookApi.users.sign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.bookApi.users.sign.dto.UserDto;
import com.practice.bookApi.users.sign.mapper.UserMapper;

@Service
public class MyPageService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	public UserDto getUserById(String userId) {
		return userMapper.userCheck(userId);
	}
	
	public boolean updatePassword(String userId, String newPw) {
		String encodedPw = pwEncoder.encode(newPw);
		return userMapper.updatePassword(userId, encodedPw) > 0;
	}
	
	public UserDto getUserByEmail(String userMail) {
		return userMapper.findIdByEmail(userMail);
	}
	
	public boolean findForUpdatePassword(String userId, String userMail, String newPw) {
		System.out.println("Updating password for userId: " + userId + " and email: " + userMail);
	    String encodedPw = pwEncoder.encode(newPw);
	    return userMapper.updateFindPassword(userId, userMail, encodedPw) > 0;
	}
	
	public UserDto getUserByIdAndEmail(String userId, String userMail) {
		return userMapper.findByIdAndEmail(userId, userMail);
	}
	
}
