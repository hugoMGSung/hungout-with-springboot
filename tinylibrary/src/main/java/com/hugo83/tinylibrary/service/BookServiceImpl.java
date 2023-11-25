package com.hugo83.tinylibrary.service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hugo83.tinylibrary.dto.BookDTO;
import com.hugo83.tinylibrary.dto.PageRequestDTO;
import com.hugo83.tinylibrary.dto.PageResponseDTO;
import com.hugo83.tinylibrary.entity.Book;
import com.hugo83.tinylibrary.repository.BooksRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

	private final ModelMapper modelMapper;

	private final BooksRepository booksRepository;

	@Override
	public Long register(BookDTO bookDTO) {
		Book book = modelMapper.map(bookDTO, Book.class);
		Long bookId = booksRepository.save(book).getBookId();

		log.info("BookService register ::: " + bookId);
		return bookId;
	}

	@Override
	public BookDTO readOne(Long bookId) {
		Optional<Book> result = booksRepository.findById(bookId);
		Book book = result.orElseThrow();
		BookDTO bookDTO = modelMapper.map(book, BookDTO.class);

		log.info("BookService readOne ::: " + bookDTO.getTitle());
		return bookDTO;
	}

	@Override
	public Long modify(BookDTO bookDTO) {
		Optional<Book> result = booksRepository.findById(bookDTO.getBookId());
		Book book = result.orElseThrow();
		book.change(bookDTO.getTitle(), bookDTO.getWriter(), bookDTO.getReleaseDate(), bookDTO.getPrice());
		Long bookId = booksRepository.save(book).getBookId();

		log.info("BookService modify ::: " + bookId);
		return bookId;
	}

	@Override
	public Long remove(Long bookId) {
		booksRepository.deleteById(bookId);

		log.info("BookService delete ::: " + bookId);
		return bookId;
	}

	@Override
	public PageResponseDTO<BookDTO> lists(PageRequestDTO pageRequestDTO) {
		// 전부 확인요
		// String[] types = pageRequestDTO.getTypes();
		// String keyword = pageRequestDTO.getKeyword();
		Pageable pageable = pageRequestDTO.getPageable("bookId");

		Page<Book> result = booksRepository.findAll(pageable); // .searchAll(types, keyword, pageable);

		List<BookDTO> dtoList = result.getContent().stream()
				.map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());

		return PageResponseDTO.<BookDTO>withAll()
				.pageRequestDTO(pageRequestDTO)
				.dtoList(dtoList)
				.total((int) result.getTotalElements())
				.build();
	}
}
