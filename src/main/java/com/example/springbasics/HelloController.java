package com.example.springbasics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/polygons")
    public String get() {
        return "Hello World";
    }


}
