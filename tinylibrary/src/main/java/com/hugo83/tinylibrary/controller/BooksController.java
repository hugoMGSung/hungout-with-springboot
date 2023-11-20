package com.hugo83.tinylibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;

import com.hugo83.tinylibrary.dto.BookDTO;
import com.hugo83.tinylibrary.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping(value = "/books")
public class BooksController {

	private final BookService bookService;

	@GetMapping(value = "/list")
	public String list() {
		return "/books/list"; // 굳이 필요는 없지만...
	}

	@GetMapping(value = "/create")
	public String getCreate() {
		return "/books/create";
	}

	@PostMapping(value = "/create")
	public String postCreate(BookDTO bookDTO) {
		Long bookId = this.bookService.register(bookDTO);

		return String.format("redirect:/books/read/%s", bookId);
	}
}
