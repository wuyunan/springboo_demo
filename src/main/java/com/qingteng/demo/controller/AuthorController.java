package com.qingteng.demo.controller;


import com.qingteng.demo.entity.Author;
import com.qingteng.demo.respository.AuthorRepository;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "书籍", description = "书籍")
@RestController
@Validated
public class AuthorController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private AuthorRepository repository;

    // Find
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/authors")
    List<Author> findAll(Authentication authentication) {

//     authentication.getPrincipal();
        logger.info(authentication.getPrincipal().toString());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        logger.info(principal.toString());


        return repository.findAll();
    }


}