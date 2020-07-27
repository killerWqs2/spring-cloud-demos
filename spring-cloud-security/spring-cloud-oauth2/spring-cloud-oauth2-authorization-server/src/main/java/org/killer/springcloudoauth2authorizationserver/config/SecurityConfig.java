package org.killer.springcloudoauth2authorizationserver.config;

import org.killer.springcloudoauth2authorizationserver.config.oauth2.authentication.Oauth2ClientCredentialsAuthenticationProvider;
import org.killer.springcloudoauth2authorizationserver.config.properties.ProjectProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.ClientDetailsService;

/**
 * @author killer
 * @date 2020/07/08 - 22:53
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private ProjectProperties projectProperties;

    private ClientDetailsService clientDetailsService;

    public SecurityConfig(ProjectProperties projectProperties, ClientDetailsService clientDetailsService) {
        this.projectProperties = projectProperties;
        this.clientDetailsService = clientDetailsService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);
        // http.authorizeRequests().anyRequest().permitAll();

        // 配置登录页面, 有时候就是什么也不想干，就是一点意义没有，，看不到希望，，看不到希望
        // 可以配置静态资源的吗？？路径还是通过配置中心获取比较好
        http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginProcessingUrl("/user/login").permitAll()
                .loginPage(projectProperties.getLoginPath()).permitAll()
                .and().csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // super.configure(auth);
        // 这里必须要手动添加 authenticationProvider 或者设置父authenticationManager 才能够设置本地local
        // auth.parentAuthenticationManager()
        // auth.authenticationProvider(oauth2ClientCredentialsAuthenticationProvider());
        // 这个所谓的LocalConfigureAuthentication 指的是当前securityfilterchain里面的authenticationmanager
        auth.inMemoryAuthentication().withUser("killerWqs").password("{noop}wqsqzj").roles("admin");
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
