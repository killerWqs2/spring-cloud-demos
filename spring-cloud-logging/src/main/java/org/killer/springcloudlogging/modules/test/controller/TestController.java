package org.killer.springcloudlogging.modules.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author killer
 * @date 2020/07/27 - 22:34
 */
@RestController
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("test")
    public String test() {

        logger.info(logger.getClass().getName()); // ch.qos.logback.classic.Logger

        logger.error("access successfully!");

        return "access successfully!";

    }


}
