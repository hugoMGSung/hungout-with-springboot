package com.hugo83.nitflex.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

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

@Service
@Log4j2
public class TmdbTvService {
	@Value("${tmdb-key}")
	private String apiKey;

	// https://api.themoviedb.org/3/tv/popular?api_key=<<api_key>>&language=en-US&page=1
	public String getPopularTvs(String page) {
		String api_key = apiKey;
		String language = "ko-KR";
		String url = "https://api.themoviedb.org/3/tv/popular";
		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("page", page)
				.queryParam("language", language)
				.queryParam("api_key", api_key)
				.build(false); // 자동으로 encode해주는 것을 막기 위해 false
		String uri = builder.toUriString();
		ResponseEntity<String> response = rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class);
		return String.valueOf(response.getBody());
	}

	// https://api.themoviedb.org/3/search/tv?api_key=<<api_key>>&language=en-US&page=1&include_adult=false
	public String searchTvShowsByQueryString(String page, String query) {
		String api_key = apiKey;
		String language = "ko-KR";
		String url = "https://api.themoviedb.org/3/search/tv";

		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("page", page)
				.queryParam("language", language)
				.queryParam("query", query)
				.queryParam("api_key", api_key)
				.build(false); // 자동으로 encode해주는 것을 막기 위해 false
		String uri = builder.toUriString();
		ResponseEntity<String> response = rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class);
		return String.valueOf(response.getBody());
	}

	// https://api.themoviedb.org/3/tv/{tv_id}/similar?api_key=<<api_key>>&language=en-US&page=1
	public String searchSimilarTvShowsByid(String page, String id) {
		String api_key = apiKey;
		String language = "ko-KR";
		String url = "https://api.themoviedb.org/3/tv/" + id + "/similar";

		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("page", page)
				.queryParam("language", language)
				.queryParam("api_key", api_key)
				.build(false); // 자동으로 encode해주는 것을 막기 위해 false
		String uri = builder.toUriString();
		ResponseEntity<String> response = rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class);
		return String.valueOf(response.getBody());
	}

	// https://api.themoviedb.org/3/tv/{tv_id}/images?api_key=<<api_key>>&language=en-US
	public Object getTvImage(String id) throws JsonMappingException, JsonProcessingException {
		String api_key = apiKey;
		String url = "https://api.themoviedb.org/3/tv/" + id + "/images";
		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("api_key", api_key)
				.build(false); // 자동으로 encode해주는 것을 막기 위해 false
		String uri = builder.toUriString();
		ResponseEntity<String> response = rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class);
		Map<String, Object> map = new HashMap<>();
		Object temp = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		log.info(String.valueOf(response.getBody()));
		map = objectMapper.readValue(String.valueOf(response.getBody()), new TypeReference<Map<String, Object>>() {
		});
		log.info(map);
		temp = map.get("backdrops");

		return temp;
	}

	public String getRealTvImage(String image) {
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

	// https://api.themoviedb.org/3/movie/{movie_id}/videos?api_key=<<api_key>>&language=en-US
	public String getTvVideo(String id) {
		String url = "https://api.themoviedb.org/3/tv/" + id + "/videos";
		String api_key = apiKey;
		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("api_key", api_key)
				.build(false); // 자동으로 encode해주는 것을 막기 위해 false
		String uri = builder.toUriString();
		ResponseEntity<String> response = rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class);
		log.info(String.valueOf(response.getBody()));

		return String.valueOf(response.getBody());
	}

	// tv 디테일 정보 불러오기
	// https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US
	public String getTvDetails(String id) {
		String url = "https://api.themoviedb.org/3/tv/" + id;
		String language = "ko-KR";
		String api_key = apiKey;
		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("api_key", api_key)
				.queryParam("language", language)
				.build(false); // 자동으로 encode해주는 것을 막기 위해 false
		String uri = builder.toUriString();
		ResponseEntity<String> response = rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class);
		log.info(String.valueOf(response.getBody()));

		return String.valueOf(response.getBody());
	}

	// 해당 TV 크레딧 정보 불러오는 메서드
	// https://api.themoviedb.org/3/movie/{movie_id}/credits?api_key=<<api_key>>&language=en-US
	public String getTvCredits(String id) {
		String url = "https://api.themoviedb.org/3/tv/" + id + "/credits";
		String language = "ko-KR";
		String api_key = apiKey;
		// RestTemplate 생성
		RestTemplate rt = new RestTemplate();
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("api_key", api_key)
				.queryParam("language", language)
				.build(false); // 자동으로 encode해주는 것을 막기 위해 false
		String uri = builder.toUriString();
		ResponseEntity<String> response = rt.exchange(
				uri,
				HttpMethod.GET,
				null,
				String.class);
		log.info(String.valueOf(response.getBody()));

		return String.valueOf(response.getBody());
	}
}
