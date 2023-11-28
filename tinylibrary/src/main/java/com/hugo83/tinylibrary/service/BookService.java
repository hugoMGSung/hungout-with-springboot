package com.hugo83.tinylibrary.service;

import java.util.ArrayList;
import java.util.List;
// import java.util.stream.Collectors;

import com.hugo83.tinylibrary.dto.BookDTO;
import com.hugo83.tinylibrary.dto.BookListAllDTO;
import com.hugo83.tinylibrary.dto.PageRequestDTO;
import com.hugo83.tinylibrary.dto.PageResponseDTO;
import com.hugo83.tinylibrary.entity.Book;
import com.hugo83.tinylibrary.entity.BookImage;

public interface BookService {
	Long register(BookDTO bookDTO);

	BookDTO readOne(Long bookId);

	Long modify(BookDTO bookDTO);

	Long remove(Long bookId);

	PageResponseDTO<BookDTO> lists(PageRequestDTO pageRequestDTO);

	// 게시글의 이미지
	PageResponseDTO<BookListAllDTO> listWithAll(PageRequestDTO pageRequestDTO);

	default Book dtoToEntity(BookDTO bookDTO) {
		Book book = Book.builder()
				.bookId(bookDTO.getBookId())
				.title(bookDTO.getTitle())
				.writer(bookDTO.getWriter())
				.releaseDate(bookDTO.getReleaseDate())
				.price(bookDTO.getPrice())
				.build();

		if (bookDTO.getFileNames() != null) {
			bookDTO.getFileNames().forEach(fileName -> {
				String[] arr = fileName.split("_", 2); // 여기 limit가 없으면 문제가 발생. 파일명에 _ 있으면 다 잘려버린다.
				book.addImage(arr[0], arr[1]);
			});
		}

		return book;
	}

	default BookDTO entityToDTO(Book book) {
		BookDTO bookDTO = BookDTO.builder()
				.bookId(book.getBookId())
				.title(book.getTitle())
				.writer(book.getWriter())
				.releaseDate(book.getReleaseDate())
				.price(book.getPrice())
				.regDate(book.getRegDate())
				.modDate(book.getModDate())
				.build();

		List<String> fileNames = new ArrayList<>();

		for (BookImage bookImage : book.getImageSet()) {
			var fileName = bookImage.getUuid() + "_" + bookImage.getFileName();
			fileNames.add(fileName);
		}
		bookDTO.setFileNames(fileNames);
		return bookDTO;
	}
}
