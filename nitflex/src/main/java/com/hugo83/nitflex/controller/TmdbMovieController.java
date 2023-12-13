package com.hugo83.nitflex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.hugo83.nitflex.service.TmdbMovieService;

@RestController
public class TmdbMovieController {
		
	@Autowired
	TmdbMovieService tmdbMovieService;

	//장르별 영화 리스트를 불러오는 메서드 
	@GetMapping("/api/movie/genre")
	public String getMovieListByGenre(
			@RequestParam(value = "page", defaultValue = "1") String page,	//페이지번호 기본값 1페이지
			@RequestParam(value = "genre", defaultValue = "Science_Fiction") String genre) {
		System.out.println(genre);
		String result = tmdbMovieService.getMovieListByGenre(genre, page);
		return result;
	}
	
	// 특정 단어로 검색해서 데이터 호출
	@GetMapping(value = "/api/movie/search")
	public String searchTvShowsByQueryString(@RequestParam(value = "pageNo", defaultValue = "1") String page,
			@RequestParam(value = "query", required = true) String query) {
		String result = tmdbMovieService.searchMoviesByQueryString(page, query);

		return result;
	}

	// 영화 1개 크레딧정보 호출 메서드
	@GetMapping(value = "/api/movie/credits")
	public String getMovieCredits(
			@RequestParam(value = "id", required = true) String id) {
		String result = tmdbMovieService.getMovieCredits(id);

		return result;
	}

	// 영화 1개 디테일정보 호출 메서드
	@GetMapping(value = "/api/movie/detail")
	public String getMovieDetails(
			@RequestParam(value = "id", required = true) String id) {
		String result = tmdbMovieService.getMovieDetails(id);

		return result;
	}

	//popular 영화 리스트를 불러오는 메서드 
	@GetMapping("/api/movie/popular")
	public String popularMovies(@RequestParam(value = "page", defaultValue = "1") String page) {
		String result = tmdbMovieService.popularMovies(page);
		return result;
	}
	
	//popularity.desc로 정렬해서 해당 리스트를 가져옴 sort_by=popularity.desc
	@GetMapping("/api/movie/sortBy")
	public String getSortByMovies(@RequestParam(value = "page",defaultValue = "1") String page,
			@RequestParam(value = "sort", defaultValue = "popularity.desc") String sort) {
		String result = tmdbMovieService.getSortByMovies(page, sort);
		return result;
	}
		
	// query 값으로 전달받은 id(movie)가 가지고있는 file_path(이미지들)의 정보를 배열로 전달함
	@GetMapping("/api/movie/image")
	public Object getMovieImage(@RequestParam(value = "id") String id)
			throws JsonMappingException, JsonProcessingException {
		Object result = tmdbMovieService.getMovieImage(id);
		return result;
	}
			
	//  http://image.tmdb.org/t/p/w500/이미지.png
	//해당 이미지 정말 불러주기 -> string 값으로 전달 
	@GetMapping("/api/movie/realimage")
	public String getRealMovieImage(@RequestParam(value = "image") String image) throws JsonMappingException, JsonProcessingException {
		String result = tmdbMovieService.getRealMovieImage(image);
		return result;
	}
	
	// 해당 영화 id를 인자로 받아서 해당 영화의 video정보를 돌려줌 youtube에서 예고편 재생가능
	// 오류 잡아야...
	@GetMapping("/api/movie/video")
	public String getMovieVideo(@RequestParam(value = "id") String id)
			throws JsonMappingException, JsonProcessingException {
		String result = tmdbMovieService.getMovieVideo(id);
		return result;
	}
	
	//tmdb api 영화의 id값 전달해주면 해당값과 비슷한 영화 프로그램을 찾아서 전달해줌
	// 오류 잡아야...
	@GetMapping(value ="/api/movie/similar")
	public String searchSimilarTvShowsByid(@RequestParam(value = "page",defaultValue = "1")String page,
			@RequestParam(value = "id", required = true) String id) {
		String result = tmdbMovieService.searchSimilarMoviesByid(page, id);
		return result;
	}
	
	//그냥 영화데이터 전부 받아옴 20개씩 받기때문에 다음걸 받기위해선 페이지 필수
	@GetMapping(value ="/api/movie/discover")
	public String getAllMovies(@RequestParam(value = "page", defaultValue = "1") String page) {
		System.out.println();
		String result = tmdbMovieService.getAllMovies(page);

		return result;
	}
	
	//그냥 영화데이터 전부 받아옴 20개씩 받기때문에 다음걸 받기위해선 페이지 필수
	@GetMapping(value ="/api/movie/toprated")
	public String getTopratedMovies(@RequestParam(value = "page", defaultValue = "1") String page) {
		System.out.println();
		String result = tmdbMovieService.getTopratedMovies(page);

		return result;
	}
}
