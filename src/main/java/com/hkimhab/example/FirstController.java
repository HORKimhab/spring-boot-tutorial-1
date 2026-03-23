package com.hkimhab.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FirstController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Spring Boot!";
    }

    @PostMapping("/message")
    public String postMessage(@RequestBody String message) {
        // return "Your message: " + message;
        return String.format("Your message: %s", message);
    }
}
