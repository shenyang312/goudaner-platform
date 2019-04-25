package com.goudaner.platform.controller;

import com.goudaner.platform.stateMachine.Events;
import com.goudaner.platform.stateMachine.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private String from = "1111";

    @Autowired
    private StateMachine<States, Events> stateMachine;
    @RequestMapping("/start")
    public String start(){
        logger.info("1111");
        stateMachine.start();
        return "1";
    }

    @RequestMapping("/evensPay")
    public String eventPay(){
        logger.info("22222");
        stateMachine.sendEvent(Events.PAY);
        return "2";
    }

    @RequestMapping("/evensRECEIVE")
    public String evensRECEIVE(){
        logger.info("333333");
        stateMachine.sendEvent(Events.RECEIVE);
        return "3";
    }
}
