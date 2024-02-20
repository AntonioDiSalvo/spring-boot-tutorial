package com.example.springboottutorial.controller;

import com.example.springboottutorial.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MyController {

    // GetMapping("greeting")
    @RequestMapping(method = GET, path = "greeting")
    public Greeting getGreeting() {
        return new Greeting(1, "hello");
    }
}
