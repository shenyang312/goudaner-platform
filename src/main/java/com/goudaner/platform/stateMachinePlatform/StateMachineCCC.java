package com.goudaner.platform.stateMachinePlatform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.support.DefaultStateContext;

import java.util.EnumSet;

@Configuration
@Scope("prototype")
@EnableStateMachineFactory(name="recruitStateMachineFactory")
@EnableStateMachine(name="StateMachineCCC")
public class StateMachineCCC extends StateMachineConfigurerAdapter<Status,ActionType> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

//    @Override
//    public void configure(
//            StateMachineConfigurationConfigurer
//                    <Status,ActionType> config) throws Exception {
//
//        config.withConfiguration()
//                .listener(listener())
//                .autoStartup(true);
//    }

    @Override
    public void configure(StateMachineStateConfigurer<Status,ActionType> states) throws Exception {
        states.withStates()
                .initial(Status.PENDING_APPROVAL)
                .choice(Status.CHOICE)
                .states(EnumSet.allOf(Status.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<Status,ActionType> transitions) throws Exception {
        transitions
                .withExternal()
                .source(Status.PENDING_APPROVAL).target(Status.PARTIALLY_APPROVED).event(ActionType.APPROVE)
                .action(errorCallingAction(action(), errorAction()))
                .and()
                .withExternal()
                .source(Status.PARTIALLY_APPROVED).target(Status.CHOICE).event(ActionType.APPROVE)
                .and()
                .withChoice()//根据查询到的值，进行判断，如果为true，更改为 PENGDING_DOCUMENT_CHECK，否在 走正常的 CHOICE
                .source(Status.CHOICE)
                .first(Status.PENGDING_DOCUMENT_CHECK,(context)->{
                    Group group = context.getMessage().getHeaders().get("group",Group.class);
                    return group.isAdvance();
                })
                .last(Status.APPROVED)
                .and()
                .withExternal()
                .source(Status.PENGDING_DOCUMENT_CHECK).target(Status.PENDING_APPROVAL_CONFIRMATION).event(ActionType.APPROVE)
                .and()
                .withExternal()
                .source(Status.PENDING_APPROVAL_CONFIRMATION).target(Status.APPROVED).event(ActionType.APPROVE)
                .and()
                .withExternal()
                .source(Status.PENDING_APPROVAL_CONFIRMATION).target(Status.PENGDING_DOCUMENT_CHECK).event(ActionType.REJECT)
                .and()
                .withExternal()
                .source(Status.PENGDING_DOCUMENT_CHECK).target(Status.PENDING_REJECT_CONFIRMATION).event(ActionType.REJECT)
                .and()
                .withExternal()
                .source(Status.PENDING_REJECT_CONFIRMATION).target(Status.PENGDING_DOCUMENT_CHECK).event(ActionType.REJECT)
                .and()
                .withExternal()
                .source(Status.PENDING_REJECT_CONFIRMATION).target(Status.REJECTED).event(ActionType.APPROVE)
                .and()
                .withExternal()
                .source(Status.PENDING_APPROVAL).target(Status.REJECTED).event(ActionType.REJECT)
                .and()
                .withExternal()
                .source(Status.PARTIALLY_APPROVED).target(Status.REJECTED).event(ActionType.REJECT);
    }

    @Bean
    public Action<Status, ActionType> action() {
        return new Action<Status, ActionType>() {

            @Override
            public void execute(StateContext<Status, ActionType> context) {
//                throw new RuntimeException("MyError");
            }
        };
    }

    @Bean
    public Action<Status, ActionType> errorAction() {
        return new Action<Status, ActionType>() {

            @Override
            public void execute(StateContext<Status, ActionType> context) {
                // RuntimeException("MyError") added to context
                logger.info("官方 demo就是牛逼");
//                context.getVariables().put("hasError", true);
//                context.getVariables().put("error", ex);
                Exception exception = context.getException();
                context.getExtendedState().getVariables().put("hasError", true);
                context.getExtendedState().getVariables().put("error", exception);;
                exception.getMessage();
            }
        };
    }

    @Bean
    public StateMachineListener<Status, ActionType> listener() {
        return new StateMachineListenerAdapter<Status, ActionType>(){

            @Override
            public void eventNotAccepted(Message<ActionType> event) {
                logger.error("event not accepted: {}", event);
                logger.error("就别解释了。。。已经牛逼了 ", event);
            }
        };
    }

    public static <S,E>Action<S,E> errorCallingAction(final Action<S,E> action,final Action<S,E> errorAction){
        return new Action<S, E>() {

            @Override
            public void execute(StateContext<S, E> context) {
                try {
                    action.execute(context);
                }catch (Exception exception){
                    try {

                        errorAction.execute(new DefaultStateContext<>(context.getStage(),context.getMessage(),context.getMessageHeaders(),context.getExtendedState(),
                                context.getTransition(),context.getStateMachine(),context.getSource(),context.getTarget(),exception));
                    }catch (Exception e){
                    }
                    throw exception;
                }
            }

        };
    }

}
