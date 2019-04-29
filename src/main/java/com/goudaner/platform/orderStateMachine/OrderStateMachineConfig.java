package com.goudaner.platform.orderStateMachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

public class OrderStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStates, OrderEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static final String orderStateMachineId = "oneStateMachineConfig";

    @Override
    public void configure(StateMachineStateConfigurer<OrderStates, OrderEvent> states)
            throws Exception {
        states
                .withStates()
                .initial(OrderStates.UNPAID)
                .choice(OrderStates.CHOICE)
                .states(EnumSet.allOf(OrderStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStates, OrderEvent> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(OrderStates.UNPAID).target(OrderStates.WAITING_DELIVERY)
                .event(OrderEvent.PAY);
    }
}
