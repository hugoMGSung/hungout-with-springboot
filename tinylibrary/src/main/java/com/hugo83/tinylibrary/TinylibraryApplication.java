package com.hugo83.tinylibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TinylibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinylibraryApplication.class, args);
	}

}
