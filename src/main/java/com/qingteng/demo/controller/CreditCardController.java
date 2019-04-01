package com.qingteng.demo.controller;


import com.qingteng.demo.entity.Book;
import com.qingteng.demo.entity.CreditCard;
import com.qingteng.demo.error.BookNotFoundException;
import com.qingteng.demo.respository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController("/creditcards")
public class CreditCardController {


    @Autowired
    private CreditCardRepository repository;



    public CreditCardController() {
    }

    /**
     *
     * @return
     */
    @GetMapping("/creditcards")
    List<CreditCard> findAll() {
        return repository.findAll();
    }


    // Save
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/creditcards")
    CreditCard newBook(@Valid @RequestBody CreditCard creditCard) {
        return repository.save(creditCard);
    }

    // Find
    @GetMapping("/creditcards/{id}")
    CreditCard findOne( @Valid @PathVariable @Min(1)  Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }


}