package com.hugo83.tinylibrary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = "book")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Builder
public class BookImage {
	@Id
	private String uuid;

	private String fileName;

	private int ord;

	@ManyToOne
	private Book book;

	public int compareTo(BookImage bookImage) {
		return bookImage.ord - ord;
	}

	public void changeBook(Book book) {
		this.book = book;
	}
}
