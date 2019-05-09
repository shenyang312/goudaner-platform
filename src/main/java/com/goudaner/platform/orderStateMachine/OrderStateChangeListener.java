package com.goudaner.platform.orderStateMachine;

import com.goudaner.platform.dto.GdOrderDto;
import com.goudaner.platform.service.GdGoodsService;
import com.goudaner.platform.stateMachinePlatform.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

@Component
public class OrderStateChangeListener implements OrderPersistStateMachineHandler.OrderPersistStateChangeListener {


        private final Logger logger = LoggerFactory.getLogger(getClass());

        public void onPersist(State<OrderStates, OrderEvent> state, Message<OrderEvent> message, Transition<OrderStates, OrderEvent> transition, StateMachine<OrderStates, OrderEvent> stateMachine) throws Exception {
            if (message != null && message.getHeaders().containsKey("gdOrderDto")) {
                GdOrderDto group = message.getHeaders().get("gdOrderDto", GdOrderDto.class);
                System.out.println("end-----orderId"+group.getOrderId());
                logger.info("之后做一个mq发送给消息模块");
//            throw new Exception("自定义异常");
            }
        }
}
