package com.goudaner.platform.orderStateMachine;

import com.goudaner.platform.dto.GdOrderDto;
import com.goudaner.platform.entity.GdGoods;
import com.goudaner.platform.entity.GdOrder;
import com.goudaner.platform.entity.GdOrderMerch;
import com.goudaner.platform.service.GdGoodsService;
import com.goudaner.platform.service.GdOrderMerchService;
import com.goudaner.platform.service.GdOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.EventHeaders;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@WithStateMachine(name = "orderStateMachine")
@Transactional
public class OrderEventsConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private GdOrderMerchService gdOrderMerchService;

    @Resource
    private GdOrderService gdOrderService;



    @OnTransition(target = "UNPAID")
    public void init() {
        System.out.println("创建订单");
    }

    @OnTransition(source = "UNPAID", target = "WAITING_DELIVERY")
    public void tansFrom1To2(@EventHeaders Map<String, Object> headers,
                             ExtendedState extendedState,
                             StateMachine<String, String> stateMachine,
                             Message<String> message,
                             Exception e) {
        try {
            logger.info("支付成功，待发货，主订单");
            if (message != null && message.getHeaders().containsKey("gdOrderDto")) {
                GdOrderDto group = message.getHeaders().get("gdOrderDto", GdOrderDto.class);
//            throw new Exception("自定义异常");
                gdOrderService.modifyGdOrder(GdOrder.builder().orderId(group.getOrderId()).orderState(OrderStates.WAITING_DELIVERY.getCode()).build(),"orderId");
            }
        }catch (Exception exception){
            stateMachine.setStateMachineError(exception);
            exception.printStackTrace();
        }

    }

    @OnTransition(source = "UNPAID", target = "WAITING_DELIVERY")
    public void tansFrom1To4(@EventHeaders Map<String, Object> headers,
                             ExtendedState extendedState,
                             StateMachine<String, String> stateMachine,
                             Message<String> message,
                             Exception e) {
        logger.info("支付成功，待发货，订单详情");
        try {
            if (message != null && message.getHeaders().containsKey("gdOrderDto")) {
                GdOrderDto group = message.getHeaders().get("gdOrderDto", GdOrderDto.class);
                gdOrderMerchService.modifyGdOrderMerch(GdOrderMerch.builder().orderId(group.getOrderId()).gdsState(OrderStates.WAITING_DELIVERY.getCode()).build(),"orderId");
            }
        }catch (Exception exception){
            stateMachine.setStateMachineError(exception);
            exception.printStackTrace();
        }
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
