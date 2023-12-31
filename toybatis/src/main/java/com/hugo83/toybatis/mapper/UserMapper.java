package com.hugo83.toybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.hugo83.toybatis.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {
	ArrayList<HashMap<String, Object>> findAll();

	List<User> findAllNew();

	User findByIdx(Long idx);

	User findByEmail(String email);

	void saveUser(User userDto);

	void updateUser(User user);

	void deleteByIdx(Long idx);

	List<User> findAllWithPaging(int offset, int limit);

	List<User> findAllSortedBy(String orderBy);

	List<User> searchUser(String keyword);
}