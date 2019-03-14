package com.qingteng.demo.respository;

import com.qingteng.demo.entity.CreditCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "creditcard", path = "creditcards")
public interface CreditCardRepository extends MongoRepository<CreditCard, String> {

	List<CreditCard> findByCardNumber(@Param("cardNumber") String cardNumber);

}