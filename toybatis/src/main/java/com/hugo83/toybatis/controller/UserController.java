package com.hugo83.toybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hugo83.toybatis.domain.User;
import com.hugo83.toybatis.dto.ResponseDTO;
import com.hugo83.toybatis.service.UserService;

import jakarta.validation.Valid;

import java.net.URLDecoder;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(value = "/findAll")
	public ResponseEntity<?> findAll() {
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setResultCode("S0001"); // 성공 실패에 따라 나오도록 해주면 됨
		responseDTO.setRes(userService.findAll());
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/findAllNew")
	public List<User> findAllNew() {
		return userService.findAllNew();
	}

	@GetMapping("/findByIdx/{idx}")
	public User getUserByIdx(@PathVariable Long idx) {
		return userService.findByIdx(idx);
	}

	@GetMapping("/findByEmail/{email}")
	public User getUserByEmail(@PathVariable String email) {
		String decodeEmail = "";
		try {
			decodeEmail = URLDecoder.decode(email, "UTF-8"); // 굳이 필요없음!
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userService.findByEmail(decodeEmail);
	}

	@PostMapping("/saveUser")
	public void postSaveUser(String email, String password) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		userService.saveUser(user);
	}

	@PostMapping("/createUser")
	public ResponseEntity<User> postCreateUser(@Valid @RequestBody User user) {
		User newUser = userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}

	@PutMapping("/editUser/{idx}")
	public User putEditUser(@PathVariable Long idx, @RequestBody User user) {
		user.setIdx(idx);
		return userService.updateUser(user);
	}

	@DeleteMapping("/{idx}")
	public void deleteUser(@PathVariable Long idx) {
		userService.deleteByIdx(idx);
	}

	@GetMapping("/paged")
    public List<User> getAllUsersWithPaging(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
			@RequestParam(value = "limit", defaultValue = "10") int limit) {
		return userService.findAllWithPaging(offset, limit);
	}
	
	@GetMapping("/sorted")
	public List<User> getAllUsersSortedBy(
			@RequestParam(value = "orderBy", defaultValue = "idx") String orderBy) {
		return userService.findAllSortedBy(orderBy);
	}

	@GetMapping("/search")
	public List<User> searchUsers(
			@RequestParam(value = "keyword", required = false) String keyword) {
		if (keyword == null || keyword.isEmpty()) {
			return userService.findAllNew();
		}
		return userService.search("%" + keyword + "%");
	}
}