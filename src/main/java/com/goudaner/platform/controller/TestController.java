package com.goudaner.platform.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private String from = "1111";

    @RequestMapping("/fromss")
    public String from(){
        return this.from;
    }
}
