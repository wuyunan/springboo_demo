package com.qingteng.demo.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.qingteng.demo.entity.Author;
import com.qingteng.demo.entity.Book;
import com.qingteng.demo.entity.Greeting;
import com.qingteng.demo.entity.HelloMessage;
import com.qingteng.demo.error.BookNotFoundException;
import com.qingteng.demo.respository.AuthorRepository;
import com.qingteng.demo.respository.BookRepository;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

import com.qingteng.msg.service.IHelloService;

@Api(value = "书籍", description = "书籍")
@RestController
@Validated
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Reference
    IHelloService helloService;

    // Find
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/books")
    List<Book> findAll(Authentication authentication) {

//     authentication.getPrincipal();
        logger.info(authentication.getPrincipal().toString());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        logger.info(principal.toString());


        return repository.findAll();
    }

    // Save
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    Book newBook(@Valid @RequestBody Book newBook) {

        logger.info(newBook.toString());

        List<Author> authors = newBook.getAuthors();
        List<Author> authorList = new ArrayList<>();

        for (Author author : authors) {
            Author result = authorRepository.findByName(author.getName());
            if (result != null) {
                authorList.add(result);
            } else {
                authorList.add(author);
            }
        }
        newBook.setAuthors(authorList);
        return repository.save(newBook);
    }

    // Find
    @GetMapping("/books/{id}")
    Book findOne(@PathVariable @Min(1) Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }


    // Find
    @GetMapping("/bookshello")
    String hello() {
        return helloService.hello("ssssww");
    }

    // Save or update
    @PutMapping("/books/{id}")
    Book saveOrUpdate(@RequestBody Book newBook, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setName(newBook.getName());
                    x.setAuthors(newBook.getAuthors());
                    x.setPrice(newBook.getPrice());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return repository.save(newBook);
                });
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
//
//    // update author only
//    @PatchMapping("/books/{id}")
//    Book patch(@RequestBody Map<String, String> update, @PathVariable Long id) {
//
//        return repository.findById(id)
//                .map(x -> {
//
//                    String author = update.get("author");
//
//                    if (!StringUtils.isEmpty(author)) {
//                        x.setAuthors(author);
//
//                        // better create a custom method to update a value = :newValue where id = :id
//                        return repository.save(x);
//                    } else {
//                        throw new BookUnSupportedFieldPatchException(update.keySet());
//                    }
//
//                })
//                .orElseGet(() -> {
//                    throw new BookNotFoundException(id);
//                });
//
//    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
    }

}