package com.hugo83.tinylibrary.service;

import org.springframework.stereotype.Service;

import com.hugo83.tinylibrary.dto.BookDTO;
import com.hugo83.tinylibrary.entity.Book;
import com.hugo83.tinylibrary.repository.BookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	@Override
	public Long register(BookDTO bookDTO) {
		Book book = Book.builder()
				.title(bookDTO.getTitle())
				.writer(bookDTO.getWriter())
				.releaseDate(bookDTO.getReleaseDate())
				.price(bookDTO.getPrice())
				.build();

		bookRepository.save(book);
		return book.getBookId();
	}

}
