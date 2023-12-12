package com.hugo83.nitflex.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.hugo83.nitflex.domain.UserVO;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
@Repository
public interface UserMapper {
	ArrayList<HashMap<String, Object>> findAll();

	int insertUser(@Param("user") UserVO user);

	UserVO getUserByUser(UserVO user);

	@Select({"SELECT count(*) FROM nf_user WHERE idx=#{idx}"})
	int isUserByIdx(@Param("idx") Long idx);

	@Select({"SELECT count(*) FROM nf_user WHERE email=#{email}"})
	int isUserByEmail(@Param("email") String email);

	@Select({"SELECT count(*) FROM nf_user WHERE email=#{user.email} AND password=#{user.password}"})
	int getUser(@Param("user") UserVO user);

	@Select({ "SELECT email, name, reg_date, mod_date FROM nf_user WHERE idx=#{idx}" })
	UserVO getUserByIdx(Long idx);
}