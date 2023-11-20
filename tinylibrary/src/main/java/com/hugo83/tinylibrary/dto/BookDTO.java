package com.hugo83.tinylibrary.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
	@NonNull
	private String title;

	@NonNull
	private String writer;

	@NonNull
	private LocalDate releaseDate;

	private Integer price;
}
