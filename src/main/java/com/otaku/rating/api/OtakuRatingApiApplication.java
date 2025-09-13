package com.otaku.rating.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.otaku.rating")
@EnableJpaRepositories(basePackages = "com.otaku.rating.infra.**.repository")
@EntityScan(basePackages = "com.otaku.rating.infra.**.persistence")
public class OtakuRatingApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(OtakuRatingApiApplication.class, args);
	}
}
