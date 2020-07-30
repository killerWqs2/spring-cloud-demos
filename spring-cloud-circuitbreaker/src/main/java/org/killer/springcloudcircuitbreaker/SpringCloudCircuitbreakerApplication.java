package org.killer.springcloudcircuitbreaker;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class SpringCloudCircuitbreakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudCircuitbreakerApplication.class, args);
    }

}
