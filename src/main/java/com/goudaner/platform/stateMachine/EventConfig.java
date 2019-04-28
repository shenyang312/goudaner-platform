package com.goudaner.platform.stateMachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.OnTransitionEnd;
import org.springframework.statemachine.annotation.OnTransitionStart;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine
public class EventConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @OnTransition(target = "UNPAID")
    public void create12() {

        logger.info("订单创建，待支付,跟想象中的一样，111111");
    }
    @OnTransitionEnd(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void payEnd32() {
        logger.info("用户完成支付，待收货: end,跟想象中的一样，33333");
    }
    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void pay32() {
        logger.info("用户完成支付，待收货,跟想象中的一样，11111");
    }
    @OnTransitionStart(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void payStart123() {
        logger.info("用户完成支付，待收货: start,跟想象中的一样，22222");
    }


    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receive123() {
        logger.info("用户已收货，订单完成,跟想象中的一样，3333");
    }

    @OnTransition(target = "PENDING_APPROVAL")
    public void create() {

        logger.info("订单创建，待支付,跟想象中的一样，111111");
    }
    @OnTransitionEnd(source = "PENDING_APPROVAL", target = "PARTIALLY_APPROVED")
    public void payEnd() {
        logger.info("用户完成支付，待收货: end,跟想象中的一样，33333");
    }
    @OnTransition(source = "PARTIALLY_APPROVED", target = "REJECTED")
    public void pay() {
        logger.info("用户完成支付，待收货,跟想象中的一样，11111");
    }
    @OnTransitionStart(source = "PARTIALLY_APPROVED", target = "PENGDING_DOCUMENT_CHECK")
    public void payStart() {
        logger.info("用户完成支付，待收货: start,跟想象中的一样，22222");
    }

    @OnTransitionStart(source = "PARTIALLY_APPROVED", target = "CHOICE")
    public void payStart11() {
        logger.info("用户完成支付，待收货: start,跟想象中的一样，22222");
    }

    @OnTransition(source = "PENGDING_DOCUMENT_CHECK", target = "PENDING_APPROVAL_CONFIRMATION")
    public void receive() {
        logger.info("用户已收货，订单完成,跟想象中的一样，3333");
    }

}
