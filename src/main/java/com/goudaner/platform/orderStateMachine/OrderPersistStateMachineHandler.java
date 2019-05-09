package com.goudaner.platform.orderStateMachine;

import com.goudaner.platform.orderStateMachine.OrderEvent;
import com.goudaner.platform.orderStateMachine.OrderStates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.access.StateMachineAccess;
import org.springframework.statemachine.access.StateMachineFunction;
import org.springframework.statemachine.event.OnStateMachineError;
import org.springframework.statemachine.listener.AbstractCompositeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.LifecycleObjectSupport;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
@Component
public class OrderPersistStateMachineHandler extends LifecycleObjectSupport {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final OrderPersistingStateChangeInterceptor interceptor = new OrderPersistingStateChangeInterceptor();
    private final OrderCompositePersistStateChangeListener listeners = new OrderCompositePersistStateChangeListener();
    @Autowired
    private FSMBuilder fSMBuilder;
    @Autowired
    private BeanFactory beanFactory;
    private StateMachine<OrderStates, OrderEvent> stateMachine;


    @Override
    protected void onInit() throws Exception {
        System.out.println("在启动的时候就会走这个快，第一次初始化stateMachine");
        stateMachine = fSMBuilder.initMachine(beanFactory);
        stateMachine.getStateMachineAccessor().doWithAllRegions(function -> function.addStateMachineInterceptor(interceptor));

        //to add PersistStateChangeListener
        Map<String, OrderPersistStateChangeListener> matchingBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors((ListableBeanFactory) this.getBeanFactory(), OrderPersistStateChangeListener.class, true, false);
        if (!matchingBeans.isEmpty()) {
            listeners.setListeners(new ArrayList(matchingBeans.values()));
        }
    }

    /**
     * Handle event with entity.
     *
     * @param event the event
     * @param state the state
     * @return true if event was accepted
     */
    public boolean handleEventWithState(Message<OrderEvent> event, OrderStates state,String stateMachineId) throws Exception {
        //even 存在几个参数
        //当前 state 状态，触发event 事件，选择的状态值：true or false
        stateMachine.stop();
        List<StateMachineAccess<OrderStates, OrderEvent>> withAllRegions = stateMachine.getStateMachineAccessor().withAllRegions();
        for (StateMachineAccess<OrderStates, OrderEvent> a : withAllRegions) {
            a.resetStateMachine(new DefaultStateMachineContext<>(state, null, null, null, null, stateMachineId));
        }
        stateMachine.start();

        try {
            stateMachine.sendEvent(event);
            System.out.println("过了一会");
            Boolean errorFlag = stateMachine.hasStateMachineError();
            //判断状态机内部是否发生异常，如果发生为true
            if(errorFlag){
                stateMachine.setStateMachineError(null);
                //充值当前beanFactory上下文中的orderStateMachine 的StateMachine
//                this.stateMachine = fSMBuilder.initMachine(beanFactory);
            }
            return errorFlag;
        }catch (Exception e){
            e.printStackTrace();
            this.stateMachine = fSMBuilder.initMachine(beanFactory);
            return true;
        }
    }

    /**
     * Adds the persist state change listener.
     *
     * @param listener the listener
     */
    public void addPersistStateChangeListener(OrderPersistStateChangeListener listener) {
        listeners.register(listener);
    }

    /**
     * The listener interface for receiving persistStateChange events.
     * The class that is interested in processing a persistStateChange
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addPersistStateChangeListener</code> method. When
     * the persistStateChange event occurs, that object's appropriate
     * method is invoked.
     */
    public interface OrderPersistStateChangeListener {
        /**
         * Called when state needs to be persisted.
         *
         * @param state        the state
         * @param message      the message
         * @param transition   the transition
         * @param stateMachine the state machine
         */
        void onPersist(State<OrderStates, OrderEvent> state, Message<OrderEvent> message, Transition<OrderStates, OrderEvent> transition,
                       StateMachine<OrderStates, OrderEvent> stateMachine) throws Exception;
    }

    private class OrderPersistingStateChangeInterceptor extends StateMachineInterceptorAdapter<OrderStates, OrderEvent> {
        @Override
        public void preStateChange(State<OrderStates, OrderEvent> state, Message<OrderEvent> message, Transition<OrderStates, OrderEvent> transition, StateMachine<OrderStates, OrderEvent> stateMachine) {
            listeners.onPersist(state,message,transition,stateMachine);
        }
    }

    private class OrderCompositePersistStateChangeListener extends AbstractCompositeListener<OrderPersistStateChangeListener> implements
            OrderPersistStateChangeListener {
        public void onPersist(State<OrderStates, OrderEvent> state, Message<OrderEvent> message,
                              Transition<OrderStates, OrderEvent> transition, StateMachine<OrderStates, OrderEvent> stateMachine){
            for (Iterator<OrderPersistStateChangeListener> iterator = getListeners().reverse(); iterator.hasNext(); ) {
                OrderPersistStateChangeListener listener = iterator.next();
                try {
                    listener.onPersist(state, message, transition, stateMachine);
                } catch (Exception e) {
                    e.printStackTrace();
                    stateMachine.setStateMachineError(e);
                }
            }
        }

    }
}