package org.killer.springcloudoauth2authorizationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
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
                .secret("{noop}$2a$10$J9fGzwblYSR0TxeBNjtJC.wCyhN4cSnEHaEqYFQsdKVR7K5fWk0tu")
                .redirectUris("")
                .authorizedGrantTypes("client_credentials")
                .scopes("all");
    }

    // 感觉还是很复杂的，，


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        endpoints.tokenStore(new RedisTokenStore(lettuceConnectionFactory()));

        endpoints.authorizationCodeServices(jdbcAuthorizationCodeServices());
    }

    @Bean
    public JdbcAuthorizationCodeServices jdbcAuthorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
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
