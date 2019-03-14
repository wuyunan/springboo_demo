package com.qingteng.demo.controller;


import com.qingteng.demo.entity.CreditCard;
import com.qingteng.demo.respository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/creditcard")
public class CreditCardController {


    @Autowired
    private CreditCardRepository repository;



    public CreditCardController() {
    }




}