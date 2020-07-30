package org.killer.springcloudservicea.modules.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wqs
 * @date 2020/7/30-13:02
 * @description
 */
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("/{uid}")
    public Integer user(@PathVariable Integer uid) {
        return uid;
    }

}
