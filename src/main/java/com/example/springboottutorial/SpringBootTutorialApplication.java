package com.example.springboottutorial;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Log4j2
@SpringBootApplication
public class SpringBootTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTutorialApplication.class, args);
	}

	// CommandLineRunner

	// ApplicationRunner
	@Bean
	ApplicationRunner applicationRunner() {
		return args -> {
			log.info("log di esempio");
		};
	}
}
