package com.hugo83.tinylibrary.dto;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
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
	private Long bookId;

	@NonNull
	@NotBlank(message = "책 제목은 필수입니다")
	private String title;

	@NonNull
	@NotBlank(message = "저자명은 필수입니다")
	private String writer;

	@NonNull
	private LocalDate releaseDate;

	private Integer price;
	private LocalDateTime regDate;
	private LocalDateTime modDate;

	// 첨부파일 리스트
	private List<String> fileNames;
}
