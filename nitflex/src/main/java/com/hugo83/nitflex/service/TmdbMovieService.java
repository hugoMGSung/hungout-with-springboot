package com.hugo83.nitflex.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class TmdbMovieService {

	@Value("${tmdb-key}")
	private String apiKey;

	private String getGenre(String genre) {
		String result = "";
		switch (genre) {
			case "Adventure":
				result = "12";
				break;
			case "Comedy":
				result = "35";
				break;
			case "Crime":
				result = "80";
				break;
			case "Documentary":
				result = "99";
				break;
			case "Drama":
				result = "18";
				break;
			case "Family":
				result = "10751";
				break;
			case "Fantasy":
				result = "14";
				break;
			case "History":
				result = "36";
				break;
			case "Horror":
				result = "27";
				break;
			case "Music":
				result = "10402";
				break;
			case "Mystery":
				result = "9648";
				break;
			case "Science_Fiction":
				result = "878";
				break;
			case "TV_Movie":
				result = "10770";
				break;
			case "Thriller":
				result = "53";
				break;
			case "War":
				result = "10752";
				break;
			case "Western":
				genre = "37";
				break;
			default:
				// 기본값 액션영화
				result = "28";
				break;
		}

		return result;
	}

	// http://api.themoviedb.org/3/discover/movie?with_genres=27&api_key=2daa7f8ee3c810361492a3382e06545d&language=en-US&page=1
	public String getMovieListByGenre(String genre, String page) {
		genre = getGenre(genre); // 장르 코드로 변경하기

		String url = "http://api.themoviedb.org/3/discover/movie";
		String api_key = apiKey;
		String language = "ko-KR";
		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("page", page)
				.queryParam("with_genres", genre)
				.queryParam("language", language)
				.queryParam("api_key", api_key)
				.build(false); // 자동으로 encode해주는 것을 막기 위해

		String uri = builder.toUriString();
		log.info(uri);
		ResponseEntity<String> response = rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class);

		return String.valueOf(response.getBody());
	}
	
	public String popularMovies(String page) {
		String language = "ko-KR";
		String url = "https://api.themoviedb.org/3/movie/popular";
		String api_key = apiKey;
		//투표점수기준 정렬시킴
		String sort_by = "vote_count.desc";
		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("page", page)
				.queryParam("language", language)
				.queryParam("sort_by", sort_by)
				.queryParam("api_key", api_key)
				.build(false); // 자동으로 encode해주는 것을 막기 위해

		String uri = builder.toUriString();
		log.info(uri);
		ResponseEntity<String> response = rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class);
		return String.valueOf(response.getBody());
	}

	//		https://api.themoviedb.org/3/discover/movie?api_key=2daa7f8ee3c810361492a3382e06545d&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1
	public String getSortByMovies(String page, String sort) {
		String url = "https://api.themoviedb.org/3/discover/movie";
		String language = "ko-KR";
		String api_key = apiKey;
		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("page", page)
				.queryParam("language", language)
				.queryParam("sort_by", sort)
				.queryParam("api_key", api_key)
				.build(false); // 자동으로 encode해주는 것을 막기 위해
		String uri = builder.toUriString();
		ResponseEntity<String> response = rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class);
		return String.valueOf(response.getBody());
	}


	// https://api.themoviedb.org/3/search/movie?api_key=<<api_key>>&language=en-US&page=1&include_adult=false
	public String searchMoviesByQueryString(String page, String query) {
		String url = "https://api.themoviedb.org/3/search/movie";
		String language = "ko-KR";
		String api_key = apiKey;
		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("page", page)
				.queryParam("query", query)
				.queryParam("language", language)
				.queryParam("api_key", api_key)
				.build(false); // 자동으로 encode해주는 것을 막기 위해
		String uri = builder.toUriString();
		ResponseEntity<String> response = rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class);
		return String.valueOf(response.getBody());
	}
	
	public Object getMovieImage(String id) throws JsonMappingException, JsonProcessingException {
		String url = "https://api.themoviedb.org/3/movie/" + id + "/images";
		String api_key = apiKey;
		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("api_key", api_key)
				.build(false);    //자동으로 encode해주는 것을 막기 위해 false
		String uri = builder.toUriString();
		ResponseEntity<String> response =  rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class
		);
		Map<String, Object> map = new HashMap<>();
		Object temp = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		log.info(String.valueOf(response.getBody()));
		map = objectMapper.readValue(String.valueOf( response.getBody()), new TypeReference<Map<String,Object>>() {});
		log.info(map);
		temp =map.get("backdrops");
		
		return temp;
	}

	public String getRealMovieImage(String image) {
		String url = "http://image.tmdb.org/t/p/w500" + image;
		URI uri = null;
		try {
			uri = new URI(url);
			log.info(uri);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return String.valueOf(uri);
	}

	//	https://api.themoviedb.org/3/movie/{movie_id}/videos?api_key=<<api_key>>&language=en-US
	// 오류 잡아야 함
	public String getMovieVideo(String id) {
		String url = "https://api.themoviedb.org/3/movie/" + id + "/videos";
		String api_key = apiKey;
		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("api_key", api_key)
				.build(false); //자동으로 encode해주는 것을 막기 위해 false
		String uri = builder.toUriString();
		
		ResponseEntity<String> response = rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class);
		//log.info(response.getBody().toString());
		log.info(String.valueOf(response.getBody()));

		return String.valueOf(response.getBody());
	}
	
	// https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US
	public String getMovieDetails(String id) {
		String url = "https://api.themoviedb.org/3/movie/"+id;
		String language = "ko-KR";
		String api_key = apiKey;
		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("api_key", api_key)
				.queryParam("language", language)
				.build(false);    //자동으로 encode해주는 것을 막기 위해 false
		String uri = builder.toUriString();
		ResponseEntity<String> response =  rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class
		);
		log.info(String.valueOf(response.getBody()));
		
		return String.valueOf(response.getBody());
	}

	//해당 영화 크레딧 정보 불러오는 메서드
	// https://api.themoviedb.org/3/movie/{movie_id}/credits?api_key=<<api_key>>&language=en-US
	public String getMovieCredits(String id) {
		String url = "https://api.themoviedb.org/3/movie/"+id+"/credits";
		String language = "en-KO";
		String api_key = apiKey;
		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("api_key", api_key)
				.queryParam("language", language)
				.build(false);    //자동으로 encode해주는 것을 막기 위해 false
		String uri = builder.toUriString();
		ResponseEntity<String> response =  rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class
		);
		log.info(String.valueOf(response.getBody()));
		
		return String.valueOf(response.getBody());
	}

	//유사한 영화정보 받기
	// https://api.themoviedb.org/3/movie/590706/similar?api_key=2daa7f8ee3c810361492a3382e06545d&page=1
	public String searchSimilarMoviesByid(String page, String id) {
		String api_key = apiKey;
		String language = "ko-KR";
		String url = "https://api.themoviedb.org/3/movie/" + id + "/similar";

		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("page", page)
				.queryParam("language", language)
				.queryParam("api_key", api_key)
				.build(false); //자동으로 encode해주는 것을 막기 위해 false
		String uri = builder.toUriString();
		ResponseEntity<String> response = rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class);
		return String.valueOf(response.getBody());
	}
		
	//영화정보 받기 페이지값 받으면 해당페이지(20개씩)
	// https://api.themoviedb.org/3/discover/movie?api_key=<<api_key>>&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1
	public String getAllMovies(String page) {
		String api_key = apiKey;
		String language = "ko-KR";
		String url = "https://api.themoviedb.org/3/discover/movie";

		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("page", page)
				.queryParam("language", language)
				.queryParam("api_key", api_key)
				.build(false); //자동으로 encode해주는 것을 막기 위해 false
		String uri = builder.toUriString();
		log.info(uri);
		ResponseEntity<String> response = rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class);
		return String.valueOf(response.getBody());
	}
	
	//평점으로 검색하기
	// https://api.themoviedb.org/3/movie/top_rated?api_key=<<api_key>>&language=en-US&page=1
	public String getTopratedMovies(String page) {
		String api_key = apiKey;
		String language = "ko-KR";
		String url = "https://api.themoviedb.org/3/movie/top_rated";

		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("page", page)
				.queryParam("language", language)
				.queryParam("api_key", api_key)
				.build(false); //자동으로 encode해주는 것을 막기 위해 false
		String uri = builder.toUriString();
		log.info(uri);
		ResponseEntity<String> response = rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class);
		return String.valueOf(response.getBody());
	}
}
