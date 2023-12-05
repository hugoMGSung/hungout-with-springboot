package com.hugo83.nitflex.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HomeController {
	@GetMapping(value = "/home")
	public List<String> getHome() {
		return Arrays.asList("Hello", "Spring RestAPI");
	}

	@GetMapping(value = "/test")
	public String getTest() {
		return "React Test!!";
	}
}
