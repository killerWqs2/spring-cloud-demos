package org.killer.springcloudoauth2resourceserver.modules.user.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author killer
 * @date 2020/08/02 - 14:26
 */
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("info")
    public Map<String, Object> getUserInfo() {
        // 问题是怎么获取用户信息呢？
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", "killerWqs");
        result.put("authorities", new String[]{"all"});
        // 获取client_id
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        return result;
    }

}
