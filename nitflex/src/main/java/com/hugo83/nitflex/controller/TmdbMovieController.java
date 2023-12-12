package com.hugo83.nitflex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hugo83.nitflex.service.TmdbMovieService;

@RestController
public class TmdbMovieController {
		
	@Autowired
	TmdbMovieService tmdbMovieService;

	//장르별 영화 리스트를 불러오는 메서드 
	@GetMapping("/api/movie/genre")
	public String getMovieListByGenre(
			@RequestParam(value = "page", defaultValue = "1") String page,	//페이지번호 기본값 1페이지
			@RequestParam(value = "genre") String genre){
		System.out.println(genre);
		String result = tmdbMovieService.getMovieListByGenre(genre, page);
			return result;
		
	}
}
