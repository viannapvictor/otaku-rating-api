package com.otaku.rating.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.otaku.rating")
public class OtakuRatingApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(OtakuRatingApiApplication.class, args);
	}
}
