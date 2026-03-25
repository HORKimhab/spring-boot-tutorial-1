package com.hkimhab.example.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hkimhab.example.model.Order;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PostMapping(value = "/post-order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> storeOrder(@RequestBody Order order) {
        return ResponseEntity.ok(order);
    }

    // @GetMapping("/hello/{user-name}")
    // public String sayHelloPathVar(
    //         @PathVariable("user-name") String userName
    // ) {
    //     return String.format("Hello %s!", userName);
    // }

    // Fix: ❌ Can't parse JSON — raw string
    @GetMapping("/hello/{user-name}")
    public ResponseEntity<Map<String, String>> sayHelloPathVar(
            @PathVariable("user-name") String userName
    ) {
        return ResponseEntity.ok(Map.of("message", String.format("Hello %s!", userName)));
    }

    @GetMapping("/hello/name")
    public ResponseEntity<Map<String, String>> sayHelloParamVar(
            @RequestParam("first-name") String firstName,
            @RequestParam("last-name") String lastName
    ) {
        return ResponseEntity.ok(Map.of("message", String.format("Hello %s %s!", firstName, lastName)));
    }

}
