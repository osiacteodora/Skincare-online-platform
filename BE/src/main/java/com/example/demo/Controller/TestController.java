package com.example.demo.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/test")
public class TestController {
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

}
