package com.hugo83.nitflex.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiKey {
	@Value("${tmdb-key}")
	private String apiKey;

	public String getApiKey() {
		return apiKey;
	}

	@Override
	public String toString() {
		return "ApiKey [apiKey=" + apiKey + "]";
	}
	
}