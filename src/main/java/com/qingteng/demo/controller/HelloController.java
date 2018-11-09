package com.qingteng.demo.controller;


import com.qingteng.demo.model.Person;
import com.qingteng.demo.model.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/helloworld")
public class HelloController {

    private HelloWorld helloWorld;


    @Autowired
    private PersonRepository repository;

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
        return repository.findByLastName("Smith").toString();
    }

    @PostMapping("/persion")
    public String addPersion(@RequestParam String firstName, @RequestParam String lastName) {
         repository.save(new Person(firstName,lastName));
         return "ok";
    }
}