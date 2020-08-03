package org.killer.springcloudoauth2authorizationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * @author killer
 * @date 2020/07/07 - 23:47
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private DataSource dataSource;

    public AuthorizationServerConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);

        // security.authenticationEntryPoint() 用来exception 跳转
        security.allowFormAuthenticationForClients() // 这一步允许从requestParams中获取client-id, client-secret，设置校验client的功能
                .tokenKeyAccess("permitAll()");

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // 内置一个 client， 应用吧, oauth2 rfc文档就将这个称之为client，另外就是 authorizatin-server, resource-server
        clients.inMemory().withClient("test")
                // 客户端模式
                .secret("{noop}$2a$10$J9fGzwblYSR0TxeBNjtJC.wCyhN4cSnEHaEqYFQsdKVR7K5fWk0tu")
                .authorizedGrantTypes("client_credentials")
                .scopes("all")
                // 自动授权，也可以用户手动同意，，
                .autoApprove(true)
                .and()
                // 授权码模式
                .withClient("t0")
                .secret("{noop}wqsqzj")
                // 用于认证完之后跳转回自己系统的连接
                .redirectUris("http://client-server:8084/login")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .scopes("all")
                .autoApprove(true);

        // oauth2要实现sso就是需要将第三方登录默认为自己的登录方式，，

    }
    // 感觉还是很复杂的，,

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);

        // token 存在redis里面
        endpoints.tokenStore(new RedisTokenStore(lettuceConnectionFactory()));

        // authorization code 存在内存里面， 这里存内存里面吧，公司电脑没有mysql
        endpoints.authorizationCodeServices(inMemoryAuthorizationCodeServices());

    }

    @Bean
    public JdbcAuthorizationCodeServices jdbcAuthorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Bean
    public InMemoryAuthorizationCodeServices inMemoryAuthorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        RedisStandaloneConfiguration configuration = lettuceConnectionFactory.getStandaloneConfiguration();
        configuration.setHostName("47.107.106.214");
        configuration.setDatabase(3);
        configuration.setPassword("killerWqs");
        return lettuceConnectionFactory;
    }

}
