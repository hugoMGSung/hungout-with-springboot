package com.hugo83.tinylibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hugo83.tinylibrary.dto.BookDTO;
import com.hugo83.tinylibrary.entity.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Long> {
	@Query(value = "SELECT VERSION()", nativeQuery = true)
	String getVersion();

	BookDTO save(java.awt.print.Book book);
}
