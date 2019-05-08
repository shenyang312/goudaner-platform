//package com.goudaner.platform.stateMachinePlatform;
//
//import com.goudaner.platform.entity.GdGoods;
//import com.goudaner.platform.service.GdGoodsService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.messaging.Message;
//import org.springframework.statemachine.StateMachine;
//import org.springframework.statemachine.state.State;
//import org.springframework.statemachine.transition.Transition;
//import org.springframework.stereotype.Component;
//
//@Component
//public class GroupPersistStateChangeListener  implements PersistStateMachineHandler.PersistStateChangeListener {
//
//    private final Logger logger = LoggerFactory.getLogger(getClass());
//    @Autowired
//    private GdGoodsService gdGoodsService;
//
////    public GroupPersistStateChangeListener(JdbcTemplate jdbcTemplate) {
////        this.jdbcTemplate = jdbcTemplate;
////    }
//
//    public void onPersist(State<Status, ActionType> state, Message<ActionType> message, Transition<Status, ActionType> transition, StateMachine<Status, ActionType> stateMachine) throws Exception {
//        if (message != null && message.getHeaders().containsKey("group")) {
//            Group group = message.getHeaders().get("group", Group.class);
//            System.out.println("end");
//            logger.info("官方 demo就是牛逼,这里执行顺序在最后");
////            gdGoodsService.addGdGoods(GdGoods.builder().gdsLiveStock("11111").gdsId("123123123").gdsName("123123123").build());
////            jdbcTemplate.update("update pagroup set status = ? where groupId = ?", state.getId().getStatusCode(), group.getGroupId());
////            throw new Exception("自定义异常");
//        }
//    }
//}
