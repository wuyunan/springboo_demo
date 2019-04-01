package com.qingteng.demo.respository;

import com.qingteng.demo.entity.CreditCard;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

	List<CreditCard> findByCardNumber(@Param("cardNumber") String cardNumber);

}