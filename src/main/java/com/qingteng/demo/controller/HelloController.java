package com.qingteng.demo.controller;


import com.qingteng.demo.entity.Person;
import com.qingteng.demo.respository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/helloworld")
public class HelloController {

    private HelloWorld helloWorld;


    @Autowired
    private PersonRepository personRepository;

    public HelloController(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }

    @GetMapping
    public String hello() {
        return helloWorld.hello();
    }

    @GetMapping("/anotherHelloworld")
    public String anotherHello() {
        return helloWorld.anotherHello();
    }
    @GetMapping("/anotherHelloworld1")
    public String anotherHello1() {
        return personRepository.findAll().toString();
    }

    @PostMapping("/persion")
    public String addPersion(@RequestParam String firstName, @RequestParam String lastName) {
        personRepository.save(new Person(firstName,lastName));
         return "ok";
    }
}