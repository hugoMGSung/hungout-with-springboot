package com.hugo83.tinylibrary.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	@Column(length = 200, nullable = false)
	private String title;

	@Column(length = 100, nullable = false)
	private String writer;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDate releaseDate;

	private Integer price;

	public void change(String title, String writer, LocalDate releaseDate, Integer price) {
		this.title = title;
		this.writer = writer;
		this.releaseDate = releaseDate;
		this.price = price;
	}
}
