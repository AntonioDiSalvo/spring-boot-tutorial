package com.example.springboottutorial.controller;

import com.example.springboottutorial.Greeting;
import com.example.springboottutorial.model.GreetingModel;
import com.example.springboottutorial.repository.GreetingRepository;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Log4j2
@RestController
public class MyController {
    // GetMapping("greeting")
    @Autowired
    GreetingRepository gr;

    @RequestMapping(method = GET, path = "greeting")
    public Greeting getGreeting() {
        return new Greeting(1, "hello");
    }

    @RequestMapping(method = GET, path = "greetings")
    public List<GreetingModel> getGreetings() {
        List<GreetingModel> allGreetings = gr.findAll();
        return allGreetings;
    }

    @RequestMapping(method = POST, path = "greeting", consumes = "application/json")
    public Greeting postGreeting (@RequestBody Greeting greeting) {
        GreetingModel gm = new GreetingModel();
        gm.setId(greeting.id());
        gm.setGreeting(greeting.content());
        gr.insert(gm);
        return greeting;
    }
    @RequestMapping(method = POST, path = "greeting", consumes = "application/json", params = "language")
    public Greeting postGreetingWithParams (@RequestBody Greeting greeting, @RequestParam(name = "language") String language) {
        log.info("language: " + language);
        return greeting;
    }
}
