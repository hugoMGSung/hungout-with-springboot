package com.hugo83.myapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration			// 이 클래스가 Spring의 설정 클래스임을 명시
public class WebConfig implements WebMvcConfigurer {	// WebMvcConfigurer를 구현해서 MVC 설정을 커스터마이징

	// 뷰 컨트롤러를 추가하는 메서드 오버라이딩
    // 주로 단순한 경로 → 뷰 연결에 사용 (컨트롤러 메서드 없이 바로 JSP/HTML로 매핑할 때 유용)
    @Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// "/ai" URL로 들어오는 요청에 대해 "ai"라는 이름의 뷰를 반환하도록 설정
        // 예: templates/ai.html (Thymeleaf), ai.jsp 등
		registry.addViewController("/ai").setViewName("ai");
	}
}
