package com.hugo83.toybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hugo83.toybatis.domain.User;
import com.hugo83.toybatis.mapper.UserMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

	public ArrayList<HashMap<String, Object>> findAll() {
		return userMapper.findAll();
	}
	
	public List<User> findAllNew() {
		return userMapper.findAllNew();
	}

	public User findByIdx(Long idx) {
		return userMapper.findByIdx(idx);
	}

	public void saveUser(User user) {
		userMapper.saveUser(user);
	}
}