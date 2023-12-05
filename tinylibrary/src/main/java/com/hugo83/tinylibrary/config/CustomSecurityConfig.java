package com.hugo83.tinylibrary.config;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("<====================----- CONFIGURE          -----====================>");

		http.cors(hsc -> {
			hsc.configurationSource(corsConfigurationSource());
		});
		http.csrf(csrf -> csrf.disable()); // http.csrf() is deprecated.

		http.authorizeHttpRequests(authz -> authz
				.requestMatchers("/member/login", "/member/join").permitAll()
				.anyRequest().authenticated());

		http.formLogin(login -> login
				.loginPage("/member/login")
				.failureUrl("/member/login?error")
				.defaultSuccessUrl("/books/list", true));

		http.logout(logout -> logout.logoutSuccessUrl("/books/list"));

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		log.info("<====================----- WEB CONFIGURE      -----====================>");
		return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
				.requestMatchers("/css/**", "/img/**", "/fonts/**", "/js/**");
	}
}
