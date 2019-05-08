package com.goudaner.platform.orderStateMachine;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine(name = "orderStateMachine")
public class OrderEventsConfig {
    @OnTransition(target = "UNPAID")
    public void init() {
        System.out.println("创建订单");
    }

    @OnTransition(source = "UNPAID", target = "WAITING_DELIVERY")
    public void tansFrom1To2() {
        System.out.println("支付成功，待发货");
    }

    @OnTransition(source = "WAITING_DELIVERY", target = "WAITING_FOR_RECEIVE")
    public void tansFrom2To3() {
        System.out.println("发货成功待签收");
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "RECEIVE")
    public void tansFrom2To5() {
        System.out.println("签收成功");
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "NOT_RECEIVE")
    public void tansFrom2To4() {
        System.out.println("拒收");
    }
}
