package org.killer.springcloudcircuitbreaker.modules.test.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wqs
 * @date 2020/7/29-17:43
 * @description
 */
@RestController
public class TestController {

    @HystrixCommand(fallbackMethod = "testBreaker")
    @GetMapping("test")
    public String test() {
        return "hello world";
    }

    /** 模拟服务超时 */
    @HystrixCommand(fallbackMethod = "testBreaker")
    @GetMapping("testNetworkBlock")
    public String testNetworkBock() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "network block!!";
    }

    /** 模拟抛出异常 */
    @HystrixCommand(fallbackMethod = "testBreaker")
    @GetMapping("testException")
    public String testException() throws Exception {
        throw new Exception("exception");
    }

    /** 还有啥？？ */

    public String testBreaker() {
        return "sorry the service is maintaining!! ";
    }

}
