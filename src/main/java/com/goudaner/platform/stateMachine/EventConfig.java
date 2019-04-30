package com.goudaner.platform.stateMachine;

import com.goudaner.platform.entity.GdGoods;
import com.goudaner.platform.service.GdGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.*;

import java.util.Map;

@WithStateMachine(name = "StateMachineCCC")
public class EventConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private GdGoodsService gdGoodsService;

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
    public void payEnd(@EventHeaders Map<String, Object> headers,
                       ExtendedState extendedState,
                       StateMachine<String, String> stateMachine,
                       Message<String> message,
                       Exception e) {

        logger.info("用户完成支付，待收货: end,跟想象中的一样，33333");
        try {
//            throw new Exception("跟想象中的一样");
            gdGoodsService.addGdGoods(GdGoods.builder().gdsLiveStock("11111").gdsId("123123123").gdsName("123123123").build());
        } catch (Exception ex) {
            stateMachine.setStateMachineError(new Exception("已经牛逼"));
            ex.printStackTrace();
        }
    }
    @OnTransition(source = "PARTIALLY_APPROVED", target = "REJECTED")
    public void pay() throws Exception {

        logger.info("用户完成支付，待收货,跟想象中的一样，11111");
        throw new Exception("跟想象中的一样");
    }
    @OnTransitionStart(source = "PARTIALLY_APPROVED", target = "PENGDING_DOCUMENT_CHECK")
    public void payStart() throws Exception {
        logger.info("用户完成支付，待收货: start,跟想象中的一样，22222");
//        throw new Exception("跟想象中的一样");
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
