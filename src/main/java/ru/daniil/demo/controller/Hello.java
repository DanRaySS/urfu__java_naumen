package ru.daniil.demo.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("/hello")
    public String hello() {
        return "hello!";
    }

    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable("name") String name) {
        return "hello " + name;
    }
}
