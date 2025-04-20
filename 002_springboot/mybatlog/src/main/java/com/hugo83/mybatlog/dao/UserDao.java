package com.hugo83.mybatlog.dao;

import com.hugo83.mybatlog.dto.UserDto;

public interface UserDao {
    int insertUser(UserDto user_info) throws Exception;
}
