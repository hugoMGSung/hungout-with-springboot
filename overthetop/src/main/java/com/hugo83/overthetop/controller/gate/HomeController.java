package com.hugo83.overthetop.controller.gate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping(value = "/gate")
public class HomeController {

	@GetMapping(value = "/home")
	public String home() {
		return "/gate/home";
	}
}
