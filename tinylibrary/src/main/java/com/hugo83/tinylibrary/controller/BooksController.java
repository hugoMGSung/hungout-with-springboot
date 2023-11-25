package com.hugo83.tinylibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hugo83.tinylibrary.dto.BookDTO;
import com.hugo83.tinylibrary.dto.PageRequestDTO;
import com.hugo83.tinylibrary.dto.PageResponseDTO;
import com.hugo83.tinylibrary.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/books")
@Log4j2
@RequiredArgsConstructor
public class BooksController {
	private final BookService bookService;

	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		PageResponseDTO<BookDTO> responseDTO = bookService.lists(pageRequestDTO);

		log.info("/books/list COUNT ::::: " + responseDTO.getSize());
		model.addAttribute("responseDTO", responseDTO);
	}

	@GetMapping(value = "/register")
	public void getRegister() {
	}

	@PostMapping(value = "/register")
	public String postRegister(@Valid BookDTO bookDTO, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		log.info("Books POST register");

		if (bindingResult.hasErrors()) {
			log.error("Books POST error!");
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/books/register";
		}

		log.info("bookDTO :::::: " + bookDTO);
		Long bookId = bookService.register(bookDTO);
		redirectAttributes.addFlashAttribute("result", bookId);
		return "redirect:/books/list";
	}

	@GetMapping(value = { "/read", "/modify" })
	public void getRead(Long bookId, PageRequestDTO pageRequestDTO, Model model) {
		BookDTO bookDTO = bookService.readOne(bookId);
		log.info("BookDTO ::::: " + bookDTO.getTitle());
		model.addAttribute("dto", bookDTO);
	}

	@PostMapping("/modify")
	public String modify(PageRequestDTO pageRequestDTO,
			@Valid BookDTO bookDTO,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		log.info("BookDTO modify post......." + bookDTO);
		if (bindingResult.hasErrors()) {
			log.info("has errors.......");
			String link = pageRequestDTO.getLink();
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			redirectAttributes.addAttribute("bookId", bookDTO.getBookId());
			return "redirect:/books/modify?" + link;
		}

		bookService.modify(bookDTO);
		redirectAttributes.addFlashAttribute("result", "modified");
		redirectAttributes.addAttribute("bookId", bookDTO.getBookId());

		return "redirect:/books/read";
	}

	@PostMapping(value = "/remove")
	public String remove(Long bookId, RedirectAttributes redirectAttributes) {
		log.info("REMOVE POST ::::: " + bookId);

		bookService.remove(bookId);
		redirectAttributes.addFlashAttribute("result", "removed");
		return "redirect:/books/list";
	}
}
