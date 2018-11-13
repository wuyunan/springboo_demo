package com.qingteng.demo.controller;


import com.qingteng.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/helloworld")
public class HelloController {

    private HelloWorld helloWorld;




    public HelloController(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }


    @GetMapping("/hello")
    public String anotherHello() {
        return helloWorld.anotherHello();
    }

}