package com.goudaner.platform.stateMachinePlatform;

import com.goudaner.platform.orderStateMachine.OrderEvent;
import com.goudaner.platform.orderStateMachine.OrderPersistStateMachineHandler;
import com.goudaner.platform.orderStateMachine.OrderStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;

@org.springframework.stereotype.Service
public class GroupService {
//
    @Autowired
    private OrderPersistStateMachineHandler handler;
    @Autowired
    private GroupRepository repository;
//
    public boolean handleAction(int groupId, String event) {
        Group group = repository.findGroupById(groupId);
        try {
            return handler.handleEventWithState(MessageBuilder.withPayload(OrderEvent.valueOf("PAY"))
                    .setHeader("group", group).build(), OrderStates.UNPAID,"orderStateMachine");
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
//
//    public void create(Group group) {
//        repository.create(group);
//    }
//    public List listAll() {
//        return repository.listAll();
//    }
}
