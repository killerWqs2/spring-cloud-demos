package org.killer.springcloudconfigclient.modules.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 没有动态刷新，烦的一批
 *
 * @author wqs
 * @date 2020/7/15-15:05
 * @description
 */
@RefreshScope
@RestController
public class TestController {

    @Value("${serverport}")
    private String serverPort;

    @GetMapping("serverPort")
    public String getServerPort() {
        return serverPort;
    }

}
