package com.goudaner.platform.stateMachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.OnTransitionEnd;
import org.springframework.statemachine.annotation.OnTransitionStart;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine
public class EventConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @OnTransition(target = "UNPAID")
    public void create() {
        logger.info("订单创建，待支付,跟想象中的一样，111111");
    }

    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void pay() {
        logger.info("用户完成支付，待收货,跟想象中的一样，2222222");
    }

    @OnTransitionStart(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void payStart() {
        logger.info("用户完成支付，待收货: start,跟想象中的一样，22222");
    }

    @OnTransitionEnd(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void payEnd() {
        logger.info("用户完成支付，待收货: end,跟想象中的一样，222222");
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receive() {
        logger.info("用户已收货，订单完成,跟想象中的一样，3333");
    }

}
