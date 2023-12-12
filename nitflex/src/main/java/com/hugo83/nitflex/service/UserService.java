package com.hugo83.nitflex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.hugo83.nitflex.domain.UserVO;
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

	public int insertUser(UserVO user) {
		try {
			if (userMapper.isUserByEmail(user.getEmail()) > 0)
				throw new DuplicateKeyException("같은 이메일 존재!!!!");

			userMapper.insertUser(user);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int isUserByIdx(Long idx) {
		return userMapper.isUserByIdx(idx);
	}

	public int isUserByEmail(String email) {
		return userMapper.isUserByEmail(email);
	}

	public int getUser(UserVO user) {
		return userMapper.getUser(user);
	}

	public UserVO getUserByIdx(Long idx) {
		return userMapper.getUserByIdx(idx);
	}

	public UserVO getUserByUser(UserVO user) {
		return userMapper.getUserByUser(user);
	}
}