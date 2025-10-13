package com.otaku.rating.anime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.otaku.rating.anime")
public class AnimeServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(AnimeServiceApplication.class, args);
	}
}
