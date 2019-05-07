package com.goudaner.platform.orderStateMachine;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine(name = "orderStateMachine")
public class OrderEventsConfig {
    @OnTransition(target = "UNPAID")
    public void init() {
        System.out.println("Init to state1:");
    }

    @OnTransition(target = "UNPAID", source = "WAITING_DELIVERY")
    public void tansFrom1To2() {
        System.out.println("Transiform from 1 to 2");
    }

    @OnTransition(target = "WAITING_DELIVERY", source = "WAITING_FOR_RECEIVE")
    public void tansFrom2To3() {
        System.out.println("Transiform from 2 to 3");
    }
}
