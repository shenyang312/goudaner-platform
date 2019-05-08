package com.goudaner.platform.orderStateMachine;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.StaticListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.config.StateMachineBuilder.Builder;

import javax.annotation.Resource;
import java.util.EnumSet;

@Configuration
public class FSMBuilder {
    public StateMachine<OrderStates, OrderEvent> initMachine(BeanFactory beanFactory) throws Exception {
        Builder<OrderStates, OrderEvent> builder = StateMachineBuilder.builder();
        builder.configureConfiguration().withConfiguration().machineId("orderStateMachine").beanFactory(beanFactory);
        builder.configureStates().withStates().initial(OrderStates.UNPAID).states(EnumSet.allOf(OrderStates.class));
        builder.configureTransitions()
                .withExternal()
                .source(OrderStates.UNPAID)
                .target(OrderStates.WAITING_DELIVERY)
                .event(OrderEvent.PAY)
                .and()
                .withExternal()
                .source(OrderStates.WAITING_DELIVERY)
                .target(OrderStates.WAITING_FOR_RECEIVE)
                .event(OrderEvent.DELIVERY)
                .and()
                .withExternal()
                .source(OrderStates.WAITING_FOR_RECEIVE)
                .target(OrderStates.RECEIVE)
                .event(OrderEvent.RECEIVE)
                .and()
                .withExternal()
                .source(OrderStates.WAITING_FOR_RECEIVE)
                .target(OrderStates.NOT_RECEIVE)
                .event(OrderEvent.NOT_RECEIVE);
        return builder.build();
    }
}
