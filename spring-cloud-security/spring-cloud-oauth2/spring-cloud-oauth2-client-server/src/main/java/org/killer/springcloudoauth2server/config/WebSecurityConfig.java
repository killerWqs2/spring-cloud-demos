package org.killer.springcloudoauth2server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author killer
 * @date 2020/07/07 - 21:40
 */
// @Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http); client模式是干什么呢？？
        // http.oauth2Client().authorizationCodeGrant().

    }

}
