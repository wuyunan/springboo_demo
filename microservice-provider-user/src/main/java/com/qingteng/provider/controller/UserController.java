package com.qingteng.provider.controller;

import com.qingteng.common.error.NotFoundException;
import com.qingteng.provider.bean.User;
import com.qingteng.provider.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

    @Value("${spring.application.name}")
   private String serviceId;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserRepository repository;

    /**
     * 注：@GetMapping("/{id}")是spring 4.3的新注解等价于：
     * @RequestMapping(value = "/id", method = RequestMethod.GET)
     * 类似的注解还有@PostMapping等等
     * @param id
     * @return user信息
     */
    @GetMapping("/users/{id}")
    public User findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    /**
     * 本地服务实例的信息
     * @return
     */
    @GetMapping("/instance-info")
    public List<ServiceInstance> showInfo() {
        List<ServiceInstance> localServiceInstance = this.discoveryClient.getInstances(this.serviceId);
        return localServiceInstance;
    }
}
