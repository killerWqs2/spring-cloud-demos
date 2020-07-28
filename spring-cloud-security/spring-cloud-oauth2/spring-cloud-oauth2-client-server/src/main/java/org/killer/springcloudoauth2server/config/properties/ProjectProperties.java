package org.killer.springcloudoauth2server.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author killer
 * @date 2020/07/26 - 17:39
 */
@ConfigurationProperties(prefix = "project")
@Configuration
public class ProjectProperties {

    private String loginPath;

    public String getLoginPath() {
        return loginPath;
    }

    public void setLoginPath(String loginPath) {
        this.loginPath = loginPath;
    }
}
