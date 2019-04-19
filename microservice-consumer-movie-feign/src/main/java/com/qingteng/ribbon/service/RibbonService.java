package com.qingteng.ribbon.service;

import com.qingteng.ribbon.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {
  @Autowired
  private RestTemplate restTemplate;

  public User findById(Long id) {
    // http://服务提供者的serviceId/url
    return this.restTemplate.getForObject("http://microservice-provider-user/users" + id, User.class);
  }
}