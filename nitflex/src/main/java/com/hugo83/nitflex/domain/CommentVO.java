package com.hugo83.nitflex.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
	private Long idx;
	private Long user_idx;
	private Long movie_idx;
	private String contents;
	private LocalDateTime reg_date;
	private LocalDateTime mod_date;
}
