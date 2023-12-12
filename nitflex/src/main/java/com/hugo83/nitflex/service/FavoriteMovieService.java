package com.hugo83.nitflex.service;

import com.hugo83.nitflex.domain.FavoriteMovieVO;
import com.hugo83.nitflex.mapper.FavoriteMovieMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteMovieService {

	@Autowired
	FavoriteMovieMapper favoriteMovieMapper;

	public List<FavoriteMovieVO> fetchMovie(Long user_idx) {
		return favoriteMovieMapper.fetchMovie(user_idx);
	}

	public void addMovie(FavoriteMovieVO nitflex) {
		favoriteMovieMapper.addMovie(nitflex);
	}

	public void removeMovie(Long idx, Long movie_id) {
		if (idx > 0) {
			favoriteMovieMapper.removeMovieByIdx(idx);
		} else if (movie_id > 0) {
			favoriteMovieMapper.removeMovieByMovieId(movie_id);
		}
	}

	public int isMovie(Long movie_id, Long user_idx) {
		if (movie_id > 0 && user_idx > 0) {
			return favoriteMovieMapper.isMovieByMovieAndUser(movie_id, user_idx);
		} else {
			return favoriteMovieMapper.isMovieByMovie(movie_id);
		}
	}
}
