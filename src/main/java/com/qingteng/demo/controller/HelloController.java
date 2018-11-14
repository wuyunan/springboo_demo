package com.qingteng.demo.controller;


import com.qingteng.demo.entity.HelloWorld;
import com.qingteng.demo.respository.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/helloworld")
public class HelloController {

    private HelloWorld helloWorld;

    @Autowired
    private VideoService videoService;


    public HelloController(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }


    @GetMapping("/hello")
    public String anotherHello() {
        return videoService.getVideoName();
    }

}