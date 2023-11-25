package com.hugo83.tinylibrary.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hugo83.tinylibrary.dto.BookDTO;
import com.hugo83.tinylibrary.dto.PageRequestDTO;
import com.hugo83.tinylibrary.dto.PageResponseDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BookServiceTests {
	@Autowired
	private BookService bookService;

	@Test
	public void testRegister() {
		log.info(bookService.getClass().getName());

		BookDTO bookDTO = BookDTO.builder()
				.title("서비스 등록 책!").writer("홍길길")
				.releaseDate(LocalDate.of(2023, 11, 23))
				.price(50000).build();

		Long bookId = bookService.register(bookDTO);
		log.info("Service BookID :::: " + bookId);
	}

	@Test
	public void testReadOne() {
		Long bookId = 21L;

		BookDTO bookDTO = bookService.readOne(bookId);
		log.info("Service Read Book :::: " + bookDTO.getClass());
		log.info("Title ::: " + bookDTO.getTitle());
		log.info("Writer ::: " + bookDTO.getWriter());
		log.info("ReleaseDate ::: " + bookDTO.getReleaseDate());
		log.info("Price ::: " + bookDTO.getPrice());
	}

	@Test
	public void testModify() {
		// 변경에 필요한 데이터만
		BookDTO bookDTO = bookService.readOne(22L);
		bookDTO.setTitle("테스트에서 변경제목");
		bookDTO.setWriter("홍식이");

		bookService.modify(bookDTO);
		log.info("Service modified BookID :::: " + bookDTO.getBookId());
	}

	@Test
	public void testRemove() {
		Long bookId = 25L;
		bookService.remove(bookId);
		log.info("Service removed BookID :::: " + bookId);
	}

	@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
				.page(1)
				.size(10)
				.build();

		PageResponseDTO<BookDTO> responseDTO = bookService.lists(pageRequestDTO);

		log.info(responseDTO);
	}
}
