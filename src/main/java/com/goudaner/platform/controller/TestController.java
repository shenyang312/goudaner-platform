package com.goudaner.platform.controller;

import com.goudaner.platform.entity.GdAccount;
import com.goudaner.platform.entity.GdOrder;
import com.goudaner.platform.mapper.GdAccountMapper;
import com.goudaner.platform.stateMachine.Events;
import com.goudaner.platform.stateMachine.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private String from = "1111";

    @Resource
    private StateMachine<States, Events> stateMachine;

    @RequestMapping("/start")
    public String start(){
        logger.info("1111");
        //given
        GdAccount account = GdAccount.builder().systemNo("1111").accountNo("22222").accountName("33333").gmtModify(new Date()).build();
        //when
        System.out.println(account);
        stateMachine.start();
        return "1";
    }

    @RequestMapping("/evensPay")
    public String eventPay(){
        logger.info("22222");
        stateMachine.sendEvent(Events.PAY);
        logger.info("状态机完毕");
        return "2";
    }

    @RequestMapping("/evensRECEIVE")
    public String evensRECEIVE(){
        logger.info("333333");
        stateMachine.sendEvent(Events.RECEIVE);
        return "3";
    }
}
