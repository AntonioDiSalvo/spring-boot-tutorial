package com.example.springboottutorial.repository;

import com.example.springboottutorial.model.GreetingModel;
import com.example.springboottutorial.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public  interface UserRepository  extends MongoRepository<UserModel, Long> {
    UserModel insert(UserModel user);

    @Query(value="{'name' : ?0}", fields = "{'name' : 1}")
    List<UserModel> findAllByName(String name);

    UserModel findByUsername(String username);
}
