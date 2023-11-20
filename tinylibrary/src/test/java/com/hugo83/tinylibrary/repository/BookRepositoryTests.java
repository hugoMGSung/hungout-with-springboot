package com.hugo83.tinylibrary.repository;

import java.time.LocalDate;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hugo83.tinylibrary.entity.Book;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BookRepositoryTests {
	@Autowired
	private BookRepository bookRepository;

	@Test
	public void testInsert() {
		LongStream.rangeClosed(1, 10).forEach(i -> {
			Book book = Book.builder()
					.title("책제목 " + i + " ....")
					.releaseDate(LocalDate.of(2023, 1, (int) (1 + i)))
					.price(10000)
					.build();

			Book result = bookRepository.save(book);
			log.info("bookId: " + result.getBookId());
		});
	}
}
