package com.hugo83.tinylibrary.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookListAllDTO {
	private Long bookId;
	private String title;
	private String writer;
	private LocalDate releaseDate;
	private List<BookImageDTO> bookImages;
}
