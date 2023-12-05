package com.hugo83.tinylibrary.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.BatchSize;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "imageSet")
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

	@OneToMany(mappedBy = "book", cascade = {
			CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true)
	@Builder.Default
	@BatchSize(size = 20)
	private Set<BookImage> imageSet = new HashSet<>();

	public void addImage(String uuid, String fileName) {
		BookImage bookImage = BookImage.builder().uuid(uuid).fileName(fileName).book(this).ord(imageSet.size()).build();
		imageSet.add(bookImage);
	}

	public void clearImages() {
		imageSet.forEach(bookImage -> bookImage.changeBook(null));
		this.imageSet.clear();
	}

	@ManyToOne
	@JoinColumn(name = "email", referencedColumnName = "email")
	private Member member;

	public void setMember(Member member) {
		this.member = member;
	}
}
