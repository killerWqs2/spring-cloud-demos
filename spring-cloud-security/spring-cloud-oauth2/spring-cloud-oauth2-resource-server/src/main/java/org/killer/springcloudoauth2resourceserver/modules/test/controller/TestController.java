package org.killer.springcloudoauth2resourceserver.modules.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wqs
 * @date 2020/7/22-15:12
 * @description
 */
@RestController
public class TestController {

    @GetMapping("test")
    public String test() {
        return "Hello body";
    }

}
