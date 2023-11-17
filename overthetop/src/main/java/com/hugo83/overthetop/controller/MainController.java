package com.hugo83.overthetop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainController {

	// 화면을 원하는 메인위치 URL로 옮겨주는 작업 밖에 없음
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main() {
		log.info("OverTheTop STARTS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return "redirect:/gate/home";
	}
}
