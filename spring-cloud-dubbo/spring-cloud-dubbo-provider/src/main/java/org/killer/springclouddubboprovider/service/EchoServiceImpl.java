package org.killer.springclouddubboprovider.service;

import org.apache.dubbo.config.annotation.Service;
import org.killer.dubbosdk.service.demo.EchoService;
import org.springframework.stereotype.Component;

/**
 * @author wqs
 * @date 2020/7/10-13:51
 * @description
 */
@Service
@Component
public class EchoServiceImpl implements EchoService {

    @Override
    public void echo(String message) {
        System.out.println("invoke echo print:" + message);
    }

}
