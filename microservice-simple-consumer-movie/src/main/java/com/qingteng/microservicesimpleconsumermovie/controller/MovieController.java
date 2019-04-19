package com.qingteng.microservicesimpleconsumermovie.controller;

import com.qingteng.microservicesimpleconsumermovie.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    RestTemplate restTemplate;

    @Value("${user.userServiceUrl}")
    private String userServiceUrl;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        LOGGER.info(this.userServiceUrl);
        return this.restTemplate.getForObject(this.userServiceUrl + id, User.class);
    }


}