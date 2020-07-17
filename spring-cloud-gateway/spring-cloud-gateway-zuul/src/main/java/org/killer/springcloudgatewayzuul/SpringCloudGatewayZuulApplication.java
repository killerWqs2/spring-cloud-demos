package org.killer.springcloudgatewayzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Cloud Zuul 主要的功能是提供负载均衡、反向代理、权限认证、动态路由、监控、弹性、安全等的边缘服务。
 * */
@SpringBootApplication
public class SpringCloudGatewayZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayZuulApplication.class, args);
    }

}
