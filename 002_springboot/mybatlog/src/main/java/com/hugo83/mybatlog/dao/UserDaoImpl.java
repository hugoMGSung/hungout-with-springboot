package com.hugo83.mybatlog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hugo83.mybatlog.dto.UserDto;

@Repository
public class UserDaoImpl implements UserDao {
    
    @Autowired  // 자동 주입.
    private SqlSession session;
    private static String namespace = "com.hugo83.mybatlog.mapper.UserMapper.";
    
    @Override
    public int insertUser(UserDto user_info) throws Exception {
        return session.insert(namespace+"insertUser", user_info);
    }

}
