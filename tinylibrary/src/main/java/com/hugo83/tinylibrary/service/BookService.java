package com.hugo83.tinylibrary.service;

import com.hugo83.tinylibrary.dto.BookDTO;
import com.hugo83.tinylibrary.dto.PageRequestDTO;
import com.hugo83.tinylibrary.dto.PageResponseDTO;

public interface BookService {
	Long register(BookDTO bookDTO);

	BookDTO readOne(Long bookId);

	Long modify(BookDTO bookDTO);

	Long remove(Long bookId);

	PageResponseDTO<BookDTO> lists(PageRequestDTO pageRequestDTO);
}
