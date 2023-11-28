package com.hugo83.tinylibrary.service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hugo83.tinylibrary.dto.BookDTO;
import com.hugo83.tinylibrary.dto.BookListAllDTO;
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
		// Book book = modelMapper.map(bookDTO, Book.class);
		// Long bookId = booksRepository.save(book).getBookId();
		Book book = dtoToEntity(bookDTO);
		Long bookId = booksRepository.save(book).getBookId();

		log.info("BookService register ::: " + bookId);
		return bookId;
	}

	@Override
	public BookDTO readOne(Long bookId) {
		// book_image 까지 조인처리되는 findByWithImages()로 변경
		// Optional<Book> result = booksRepository.findById(bookId);
		Optional<Book> result = booksRepository.findByIdWithImages(bookId);

		Book book = result.orElseThrow();
		// BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
		BookDTO bookDTO = entityToDTO(book);

		log.info("BookService readOne ::: " + bookDTO.getTitle());
		return bookDTO;
	}

	@Override
	public Long modify(BookDTO bookDTO) {
		Optional<Book> result = booksRepository.findById(bookDTO.getBookId());
		Book book = result.orElseThrow();
		book.change(bookDTO.getTitle(), bookDTO.getWriter(), bookDTO.getReleaseDate(), bookDTO.getPrice());

		// 첨부파일 처리 추가
		book.clearImages();

		if (bookDTO.getFileNames() != null) {
			for (String fileName : bookDTO.getFileNames()) {
				String[] arr = fileName.split("_", 2);
				book.addImage(arr[0], arr[1]);
			}
		}

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

	@Override
	public PageResponseDTO<BookListAllDTO> listWithAll(PageRequestDTO pageRequestDTO) {
		// String[] types = pageRequestDTO.getTypes();
		// String keyword = pageRequestDTO.getKeyword();
		Pageable pageable = pageRequestDTO.getPageable("bookId");

		Page<Book> result = booksRepository.findAll(pageable);
		List<BookListAllDTO> dtoList = result.getContent().stream()
				.map(book -> modelMapper.map(book, BookListAllDTO.class)).collect(Collectors.toList());

		return PageResponseDTO.<BookListAllDTO>withAll()
				.pageRequestDTO(pageRequestDTO)
				.dtoList(dtoList)
				.total((int) result.getTotalElements())
				.build();
	}
}
