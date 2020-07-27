package org.killer.springcloudoauth2server.modules.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author killer
 * @date 2020/07/27 - 22:34
 */
@RestController
public class TestController {

    @GetMapping("test")
    public String test() {
        return "access successfully!";
    }


}
