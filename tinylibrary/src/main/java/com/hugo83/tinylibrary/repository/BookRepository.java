package com.hugo83.tinylibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugo83.tinylibrary.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
