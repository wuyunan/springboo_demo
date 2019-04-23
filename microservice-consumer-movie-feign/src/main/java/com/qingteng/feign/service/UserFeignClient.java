package com.qingteng.feign.service;

import com.qingteng.feign.bean.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;

@FeignClient(name = "microservice-provider-user", fallbackFactory = UserFeignClientFallbackFactory.class)
public interface UserFeignClient {

    @RequestMapping(value = "users/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
}

@Component
@Slf4j
class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
    @Override
    public UserFeignClient create(Throwable throwable) {
        return id -> {
            log.error("进入回退逻辑", throwable);
            return new User(-1L, "默认用户", "默认用户", 0, new BigDecimal(1));
        };
    }
}
