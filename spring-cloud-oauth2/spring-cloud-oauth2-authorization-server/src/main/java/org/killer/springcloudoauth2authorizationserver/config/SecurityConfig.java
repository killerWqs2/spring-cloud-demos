package org.killer.springcloudoauth2authorizationserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author killer
 * @date 2020/07/08 - 22:53
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);
        // http.authorizeRequests().antMatchers("/oauth/token").permitAll();
        http.authorizeRequests().anyRequest().permitAll();
    }

}
