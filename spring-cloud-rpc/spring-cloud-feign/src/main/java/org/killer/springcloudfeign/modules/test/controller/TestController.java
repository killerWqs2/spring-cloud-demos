package org.killer.springcloudfeign.modules.test.controller;

import org.killer.springcloudfeign.utils.rpc.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wqs
 * @date 2020/7/30-13:44
 * @description
 */
@RestController
public class TestController {

    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public Integer test() {
        return userService.user(1);
    }

}
