package com.qingteng.demo.respository;

import com.qingteng.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Customer findByDevcMac(String devcMac);

}