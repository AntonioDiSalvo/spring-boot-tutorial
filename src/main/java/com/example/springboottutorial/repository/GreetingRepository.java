package com.example.springboottutorial.repository;

import com.example.springboottutorial.model.GreetingModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface GreetingRepository  extends MongoRepository<GreetingModel, Long> {

    GreetingModel findFirstByGreeting(String greeting);

    List<GreetingModel> findAllByGreeting(String greeting);

    GreetingModel insert(GreetingModel greetingModel);
}
