package com.qingteng.demo.respository;

import com.qingteng.demo.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByDevcMac(String devcMac);

}