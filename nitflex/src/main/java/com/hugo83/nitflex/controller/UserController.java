package com.hugo83.nitflex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hugo83.nitflex.domain.ResponseVO;
import com.hugo83.nitflex.domain.UserVO;
import com.hugo83.nitflex.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/api/v1/user/")
@Log4j2
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping(value = "findAll")
	public ResponseEntity<?> findAll() {
		ResponseVO responseVO = new ResponseVO();
		responseVO.setResultCode("S0001"); // 역시 결과를 보고 만들면 됨.
		log.info("resultCode는 결과 리턴 후 나오는 값에 따라 조절할 것!");
		responseVO.setRes(userService.findAll());
		return new ResponseEntity<>(responseVO, HttpStatus.OK);
	}

	@PostMapping(value = "registerUser")
	public int postRegisterUser(@RequestBody UserVO user) {
		log.info("사용자 등록!!!!!!!");
		return userService.insertUser(user);
	}

	@GetMapping(value = "userCheck")
	int getIsUser(@RequestParam("idx") Long idx) {
		int cnt = userService.isUserByIdx(idx);
		log.info("등록 사용자 수 ::::: " + cnt);
		return cnt;
	}
	
	@PostMapping(value ="/login")
	Long postLogin(@RequestBody UserVO user, HttpSession httpSession) throws Exception {
		log.info("사용자 정보 ::::::: " + user.toString());
		int cnt = userService.getUser(user);
		UserVO myuser = null;
		if (cnt == 1) {
			myuser = userService.getUserByUser(user);
			log.info("로그인 사용자 ::::::: " + myuser.toString());
			httpSession.setAttribute("USERID_SESSION", user.getEmail());
		}

		if (myuser == null) {
			return 0L;
		}

		return myuser.getIdx(); //회원의 인덱스값(아이디값을 보내줌)
	}
	
	@GetMapping("/logout")
	String logout(HttpSession httpSession) {
		log.info("로그아웃 처리 ::::::::::: ");
		httpSession.invalidate();
		return "로그아웃";
	}
}
