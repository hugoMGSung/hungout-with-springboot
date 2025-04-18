package com.hugo83.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration    // 이 클래스는 Spring의 설정 클래스임을 나타냄 (Bean 정의 등을 포함)
public class WebClientConfig {

	@Bean			// WebClient 인스턴스를 Spring Bean으로 등록
	WebClient webClient() {
		return WebClient.builder()
				// 응답 본문을 메모리에 얼마나 저장할 수 있는지를 설정하는 부분
				.exchangeStrategies(ExchangeStrategies.builder()
						.codecs(configurer -> configurer.defaultCodecs()
								// maxInMemorySize를 -1로 설정하면 메모리 제한 없이 데이터를 읽을 수 있음
                                // 주의: 응답이 너무 크면 OOM(Out of Memory) 위험이 있으므로 신중하게 사용해야 함
								//.maxInMemorySize(-1))
								.maxInMemorySize(10 * 1024 * 1024))
						.build())
				// 기본 요청 주소 설정 (예: WebClient로 보낼 기본 API 서버 주소)
				.baseUrl("http://localhost:8000")
				// 설정 완료 후 WebClient 인스턴스 생성
				.build();
	}
}
