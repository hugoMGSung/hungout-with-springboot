package com.hugo83.mybatlog.mapper;

import org.apache.ibatis.annotations.*;

import com.hugo83.mybatlog.dto.UserDto;

@Mapper
public interface UserMapper {
    int insertUser(UserDto user_info);
}
