package com.qingteng.feign.service;

import com.qingteng.feign.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {
  @Autowired
  private RestTemplate restTemplate;

  public User findById(Long id) {
    // http://服务提供者的serviceId/url
    return this.restTemplate.getForObject("http://localhost:8000/users/" + id, User.class);
  }
}