package com.qingteng.msg.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Service(interfaceClass = IHelloService.class)
@Component
public class HelloServiceImpl implements IHelloService {
    //...
    @Override
    public String hello(String param) {
        return "provider get param success,param is " + param;
    }
}