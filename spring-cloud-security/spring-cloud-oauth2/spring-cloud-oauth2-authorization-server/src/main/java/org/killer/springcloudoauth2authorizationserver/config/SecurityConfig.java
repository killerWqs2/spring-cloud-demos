package org.killer.springcloudoauth2authorizationserver.config;

import org.killer.springcloudoauth2authorizationserver.config.oauth2.authentication.Oauth2ClientCredentialsAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;

/**
 * @author killer
 * @date 2020/07/08 - 22:53
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private ClientDetailsService clientDetailsService;

    public SecurityConfig(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);
        http.authorizeRequests().anyRequest().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // super.configure(auth);
        // 这里必须要手动添加 authenticationProvider 或者设置父authenticationManager 才能够设置本地local
        // auth.parentAuthenticationManager()
        // auth.authenticationProvider(oauth2ClientCredentialsAuthenticationProvider());
        // 这个所谓的LocalConfigureAuthentication 指的是当前securityfilterchain里面的authenticationmanager
    }

    @Configuration
    public class AuthenticationManagerConfig extends GlobalAuthenticationConfigurerAdapter {

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(oauth2ClientCredentialsAuthenticationProvider());
        }

    }

    @Bean
    public Oauth2ClientCredentialsAuthenticationProvider oauth2ClientCredentialsAuthenticationProvider() {
        return new Oauth2ClientCredentialsAuthenticationProvider(clientDetailsService);
    }

}
