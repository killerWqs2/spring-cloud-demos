package org.killer.springclouddubbo.unit;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.jupiter.api.Test;
import org.killer.springclouddubbo.dubbo.demo.api.EchoService;

/**
 * @author wqs
 * @date 2020/7/9-17:29
 * @description
 */
public class DubboSpiTest {

    @Test
    public void test() {

        // spi service provider interface 服务发现机制
        ExtensionLoader<EchoService> extensionLoader = ExtensionLoader.getExtensionLoader(EchoService.class);

        EchoService defaultExtension = extensionLoader.getExtension("echoService");

        System.out.println(defaultExtension.echo("biubiubiu"));

    }

}
