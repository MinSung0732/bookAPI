package com.practice.bookApi.users.sign.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.practice.bookApi.users.sign.dto.UserDto;

@Mapper
public interface UserMapper {
	
	int idcheck(String userId);
	int mailcheck(String userMail);
	int insertUser(UserDto userDto);
	public UserDto userCheck(String userId);
	public UserDto getUserSessionId(String sessionId);
	public void keepLogin(Map<String, Object>map);
	String selectUserNameById(String userId);
	long findUserNoByUserId(String userId);
	int updatePassword(@Param("userId") String userId, @Param("newPw") String newPw);
	public UserDto findIdByEmail(String userMail);
	public UserDto findByIdAndEmail(@Param("userId") String userId,@Param("userMail") String userMail);
	int updateFindPassword(@Param("userId") String userId, @Param("userMail") String userMail, @Param("newPw") String newPw);

}
