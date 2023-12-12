package com.hugo83.nitflex.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteMovieVO {
	private Long idx;
	private Long user_idx;
	private Long movie_id;
	private String movie_original_title;
	private String poster_path;
	private LocalDateTime reg_date;
	private LocalDateTime mod_date;
}