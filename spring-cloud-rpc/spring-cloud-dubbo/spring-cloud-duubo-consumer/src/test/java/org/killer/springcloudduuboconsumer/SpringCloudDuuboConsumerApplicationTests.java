package org.killer.springcloudduuboconsumer;

import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.killer.dubbosdk.service.demo.EchoService;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringCloudDuuboConsumerApplicationTests {

    @Reference
    private EchoService echoService;

    @Test
    void contextLoads() {
        echoService.echo("hello");
    }

}
