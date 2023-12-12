package com.hugo83.nitflex.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
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
																							// false
		String uri = builder.toUriString();
		System.out.println(uri);
		ResponseEntity<String> response = rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class);
		return response.getBody().toString();
	}
}
