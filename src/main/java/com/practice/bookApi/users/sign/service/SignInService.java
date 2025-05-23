package com.practice.bookApi.users.sign.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.bookApi.users.sign.dto.UserDto;
import com.practice.bookApi.users.sign.mapper.UserMapper;

@Service
public class SignInService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	public int userCheck(String userId, String userPw) {
		UserDto userDto = userMapper.userCheck(userId);
		if (userDto != null) {
			if (pwEncoder.matches(userPw, userDto.getUserPw())) {
				return 0;
			}
		}
		return 1;
	}
	
	public UserDto getUserSessionId(String sessionId) {
		return userMapper.getUserSessionId(sessionId);
	}
	
	public String getUserName(String userId) {
		return userMapper.selectUserNameById(userId);
	}
	
	public Long getUserNo(String userId) {
	    return userMapper.findUserNoByUserId(userId); // MyBatis로 구현
	}
	
	public void keepLogin(String sessionId, Date limitDate, String userId) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("sessionId", sessionId);
		map.put("limitDate", limitDate);
		map.put("userId", userId);
		userMapper.keepLogin(map);
	}
}
