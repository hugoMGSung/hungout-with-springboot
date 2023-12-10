package com.hugo83.toybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hugo83.toybatis.domain.User;
import com.hugo83.toybatis.dto.ResponseDTO;
import com.hugo83.toybatis.service.UserService;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

	@PostMapping("/saveUser")
	public void postSaveUser(String email, String password) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		userService.saveUser(user);
	}	
}