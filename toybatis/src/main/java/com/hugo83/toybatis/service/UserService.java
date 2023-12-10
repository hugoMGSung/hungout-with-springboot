package com.hugo83.toybatis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.cache.annotation.CacheEvict;
// import org.springframework.cache.annotation.CachePut;
// import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hugo83.toybatis.domain.User;
import com.hugo83.toybatis.mapper.UserMapper;

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

	// @Cacheable(value = "tb_user", key = "#idx")
	public User findByIdx(Long idx) {
		return userMapper.findByIdx(idx);
	}

	public User findByEmail(String email) {
		return userMapper.findByEmail(email);
	}

	public void saveUser(User user) {
		userMapper.saveUser(user);
	}

	public User createUser(User user) {
		userMapper.saveUser(user);

		return userMapper.findByEmail(user.getEmail());
	}

	// @CachePut(value = "tb_user", key = "#user.idx")
	public User updateUser(User user) {
		userMapper.updateUser(user);

		return user;
	}

	// @CacheEvict(value = "tb_user", key = "#idx")
	public void deleteByIdx(Long idx) {
		userMapper.deleteByIdx(idx);
	}

	public List<User> findAllWithPaging(int offset, int limit) {
		return userMapper.findAllWithPaging(offset, limit);
	}

	public List<User> findAllSortedBy(String orderBy) {
		return userMapper.findAllSortedBy(orderBy);
	}

	public List<User> search(String keyword) {
		return userMapper.searchUser(keyword);
	}
}