package org.killer.springcloudoauth2server.config;

import org.killer.springcloudoauth2server.config.properties.ProjectProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;

/**
 * @author killer
 * @date 2020/07/26 - 22:32
 */
@Configuration
@EnableOAuth2Sso // 这个需要配合oauth2Client使用
public class OAuth2ClientConfig extends WebSecurityConfigurerAdapter {

    private final ProjectProperties projectProperties;

    /** 注入的应该是内存存储的 */
    private final ClientRegistrationRepository clientRegistrationRepository;

    public OAuth2ClientConfig(ProjectProperties projectProperties, ClientRegistrationRepository clientRegistrationRepository) {
        this.projectProperties = projectProperties;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        DefaultOAuth2AuthorizationRequestResolver defaultOAuth2AuthorizationRequestResolver = new DefaultOAuth2AuthorizationRequestResolver(clientRegistrationRepository, projectProperties.getLoginPath());

        // 开启oauth2 授权码模式, 这个是没有办法跟Sso公用的，这里他持久化了开始的请求
        http.oauth2Client().authorizationCodeGrant().authorizationRequestResolver(defaultOAuth2AuthorizationRequestResolver)
                .and()
                .and().authorizeRequests().anyRequest().authenticated();
        // 现在的问题是 我获取到token之后，如何登录呢？？

        // enableoauth2sso 用来校验oauth2 token的，，听有趣的，，
        // 问题是权限呢，，有了token可以向权限服务器请求数据，， 这种架构是真的有问题，，

    }

}
