package com.hugo83.nitflex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hugo83.nitflex.mapper.UserMapper;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;

	public ArrayList<HashMap<String, Object>> findAll() {
		return userMapper.findAll();
	}
}