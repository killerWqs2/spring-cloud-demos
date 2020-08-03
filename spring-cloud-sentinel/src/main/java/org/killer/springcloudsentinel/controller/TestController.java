package org.killer.springcloudsentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wqs
 * @date 2020/7/22-15:12
 * @description
 */
@RestController
public class TestController {

    @SentinelResource(value = "hello", blockHandler = "block", fallback = "fallback")
    @GetMapping("testFlow")
    public String test() {
        return "Hello body";
    }

    /** 限流处理 */
    public String block() {
        return "this api is blocked!";
    }

    /** 降级处理 */
    public String fallback() {
        return "this api is fallback!";
    }

}
