package com.example.sosikcommunityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SosikCommunityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SosikCommunityServiceApplication.class, args);
	}

}
