package com.goudaner.platform.stateMachinePlatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;

@org.springframework.stereotype.Service
public class GroupService {

    @Autowired
    private PersistStateMachineHandler handler;
    @Autowired
    private GroupRepository repository;

    public boolean handleAction(int groupId, String event) {
        Group group = repository.findGroupById(groupId);
        return handler.handleEventWithState(MessageBuilder.withPayload(ActionType.valueOf(event))
                .setHeader("group", group).build(), Status.valueOf(group.getState()),"stateMachineSS");
    }

    public void create(Group group) {
        repository.create(group);
    }
    public List listAll() {
        return repository.listAll();
    }
}
