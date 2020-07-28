package org.killer.springcloudoauth2server.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * @author killer
 * @date 2020/07/26 - 22:32
 */
@Configuration
//@EnableOAuth2Client
@EnableOAuth2Sso // 这个需要配合oauth2Client使用
public class OAuth2ClientConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启oauth2 授权码模式, 这个是没有办法跟Sso公用的，这里他持久化了开始的请求
        http.oauth2Client().and().authorizeRequests().anyRequest().authenticated();
        // 现在的问题是 我获取到token之后，如何登录呢？？

        // enableoauth2sso 用来校验oauth2 token的，，听有趣的，，
        // 问题是权限呢，，有了token可以向权限服务器请求数据，， 这种架构是真的有问题，，


    }

}
