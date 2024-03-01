package com.example.springboottutorial;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootTest
class SpringBootTutorialApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testPropertyIsSet() {
		assert(JwtUtil.generateToken("test-username") != null);
	}

	@Configuration
	public class MyTestConfiguration {

	}
}
