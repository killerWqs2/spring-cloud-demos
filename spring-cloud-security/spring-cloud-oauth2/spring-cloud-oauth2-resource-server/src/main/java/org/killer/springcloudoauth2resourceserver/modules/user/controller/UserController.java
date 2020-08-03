package org.killer.springcloudoauth2resourceserver.modules.user.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("info")
    public Map<String, Object> getUserInfo() {
        // 问题是怎么获取用户信息呢？
        HashMap<String, Object> result = new HashMap<>();
        result.put("user_name", SecurityContextHolder.getContext().getAuthentication().getName());
        result.put("authorities", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        // 获取client_id,, 好像没有办法获取到
        result.put("client_id", ((OAuth2Authentication)(SecurityContextHolder.getContext().getAuthentication())).getOAuth2Request().getClientId());
        result.put("active", true);
        return result;
    }

}
