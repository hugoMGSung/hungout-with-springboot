package com.hugo83.nitflex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hugo83.nitflex.domain.FavoriteMovieVO;
import com.hugo83.nitflex.service.FavoriteMovieService;

import lombok.extern.log4j.Log4j2;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/mylist")
@Log4j2
public class FavoriteMovieController {
	@Autowired
	FavoriteMovieService favoriteMovieService;

	@GetMapping("/{user_idx}")
	public List<FavoriteMovieVO> fetchMovie(@PathVariable Long user_idx) {
		log.info(user_idx);
		return favoriteMovieService.fetchMovie(user_idx);
	}
	
	@PostMapping
	public String addMovie(@RequestBody FavoriteMovieVO item) {
		log.info(item.getUser_idx());
		log.info(item.getMovie_id());
		int cnt = favoriteMovieService.isMovie(item.getMovie_id(), item.getUser_idx());
		if (cnt == 0) {
			favoriteMovieService.addMovie(item);
			System.out.println("즐겨찾기 저장");
			return "SUCCEED";
		} else {
			return "FAILED";
		}
	}
	
	@DeleteMapping("/{movie_id}")
	public String removeMovieByMovie(@PathVariable Long movie_id) {
		log.info("삭제 즐겨찾기 아이디2 => " + movie_id);
		int cnt = favoriteMovieService.isMovie(movie_id, 0L);
		if (cnt == 1) {
			favoriteMovieService.removeMovie(0L, movie_id);
			log.info("즐겨찾기 삭제2!");
			return "SUCCEED";
		} else {
			return "FAILED";
		}
	}
}
