package com.goudaner.platform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private String from = "1111";

    @RequestMapping("/fromss")
    public String from(){
        logger.info("12313");
        return this.from;
    }
}
