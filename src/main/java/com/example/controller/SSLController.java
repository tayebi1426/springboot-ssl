package com.example.controller;

import com.example.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SSLController {

    @GetMapping("/ssl")
    public String hello() {
        return "hello ssl";
    }

    @GetMapping("/person")
    @ResponseBody
    public Person getPerson() {
        return new Person(1L, "hadi", "tayebi", "123456");
    }
}
