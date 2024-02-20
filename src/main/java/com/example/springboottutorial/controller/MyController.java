package com.example.springboottutorial.controller;

import com.example.springboottutorial.Greeting;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MyController {
    // GetMapping("greeting")
    @RequestMapping(method = GET, path = "greeting")
    public Greeting getGreeting() {
        return new Greeting(1, "hello");
    }

    @RequestMapping(method = POST, path = "greeting", consumes = "application/json")
    public Greeting postGreeting (@RequestBody Greeting greeting) {
        return greeting;
    }
}
