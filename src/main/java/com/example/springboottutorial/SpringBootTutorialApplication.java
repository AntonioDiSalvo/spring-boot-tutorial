package com.example.springboottutorial;

import com.example.springboottutorial.model.GreetingModel;
import com.example.springboottutorial.model.UserModel;
import com.example.springboottutorial.repository.GreetingRepository;
import com.example.springboottutorial.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@Log4j2
@SpringBootApplication
public class SpringBootTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTutorialApplication.class, args);
	}

	// CommandLineRunner

	// ApplicationRunner
	@Bean
	ApplicationRunner applicationRunner(GreetingRepository greetingRepository) {
		return args -> {
//			UserModel user = new UserModel();
//			user.setId(10L);
//			user.setName("Mario");
//			user.setSurname("Rossi");
////		user.setBirthDate(new Date());
//
//			userRepository.insert(user);

			//List<UserModel> allUsers = userRepository.findAllByName("Mario");
			List<GreetingModel> allGreetings = greetingRepository.findAll();

			log.info("log di esempio: " + allGreetings.toString());

			GreetingModel g = new GreetingModel();
			g.setName("myName");
			g.setGreeting("myGreeting");

			UserModel u = new UserModel();
			BeanUtils.copyProperties(g, u, new String[] {"greeting"});

			log.info("property copiate : " + u.getName());
			log.info("property non copiate : " + u.getGreeting());
		};
	}
}
