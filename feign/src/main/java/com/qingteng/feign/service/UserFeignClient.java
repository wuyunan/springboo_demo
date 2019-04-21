package com.qingteng.feign.service;

import com.qingteng.feign.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {

    @RequestMapping(value = "users/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
}
