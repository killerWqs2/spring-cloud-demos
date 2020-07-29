package org.killer.springcloudoauth2authorizationserver.modules.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wqs
 * @date 2020/7/29-9:10
 * @description
 */
@RestController
public class TestController {

    @GetMapping("test")
    public String test() {
        return "hello world!";
    }

}
