package com.hugo83.tinylibrary.repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hugo83.tinylibrary.entity.Book;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BooksRepositoryTests {
	/* CRUD 기본 테스트 진행 */
	@Autowired
	private BooksRepository booksRepository;

	@Test
	public void testInsert() {
		IntStream.rangeClosed(1, 20).forEach(i -> {
			Book book = Book.builder().title("책 제목 ---> " + i).writer("홍길동")
					.releaseDate(LocalDate.of(2023, 1, (1 + i)))
					.price(20000).build();

			Book result = booksRepository.save(book);
			log.info("BookID : " + result.getBookId());
		});
	}

	@Test
	public void testSelect() {
		Long bookId = 19L;
		Optional<Book> result = booksRepository.findById(bookId);

		Book book = result.orElseThrow();
		log.info("Book >> " + book.getTitle());
	}

	@Test
	public void testUpdate() {
		Long bookId = 19L;
		Optional<Book> result = booksRepository.findById(bookId);
		Book book = result.orElseThrow();

		book.change("책 제목변경 19...", "홍길순", LocalDate.of(2022, 12, 20), 340000);
		booksRepository.save(book);
		log.info("19번 책 수정 완료!!");
	}

	@Test
	public void testDelete() {
		Long bookId = 20L;
		booksRepository.deleteById(bookId);
		log.info("20번 책 삭제 완료!!");
	}
}
