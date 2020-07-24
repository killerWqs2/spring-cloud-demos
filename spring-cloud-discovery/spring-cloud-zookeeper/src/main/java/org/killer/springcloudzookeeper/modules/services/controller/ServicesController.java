package org.killer.springcloudzookeeper.modules.services.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wqs
 * @date 2020/7/13-17:57
 * @description
 */
@RestController
public class ServicesController {

    @Value("${spring.application.name}")
    private String applicationName;

    private final DiscoveryClient discoveryClient;

    public ServicesController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/services")
    public void getServices() {
        List<ServiceInstance> instances = discoveryClient.getInstances(applicationName);

        instances.forEach(serviceInstance -> {
            System.out.println(serviceInstance.getHost());
        });

    }
}
