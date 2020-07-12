package org.killer.springcloudzookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudZookeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudZookeeperApplication.class, args);
    }

}
