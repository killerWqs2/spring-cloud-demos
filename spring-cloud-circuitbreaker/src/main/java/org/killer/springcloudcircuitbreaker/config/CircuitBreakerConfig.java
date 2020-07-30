package org.killer.springcloudcircuitbreaker.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wqs
 * @date 2020/7/29-17:45
 * @description
 */
@Configuration
public class CircuitBreakerConfig {

    @Bean
    public Customizer<HystrixCircuitBreakerFactory> defaultConfig() {
        // 这是什么语法，，好吧这就是一个lambda语法
        return factory -> factory.configureDefault(id -> HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(id))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(4000)));
    }

}
