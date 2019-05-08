package com.goudaner.platform;

import com.goudaner.platform.dto.GdAccountDto;
import com.goudaner.platform.entity.GdAccount;
import com.goudaner.platform.mapper.GdAccountMapper;
import com.goudaner.platform.orderStateMachine.FSMBuilder;
import com.goudaner.platform.orderStateMachine.OrderEvent;
import com.goudaner.platform.orderStateMachine.OrderStates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlatformApplicationTests {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private FSMBuilder sSMBuilder;
    @Test
    public void contextLoads() {
        StateMachine<OrderStates, OrderEvent> machine = null;
//        try {
//            machine = sSMBuilder.initMachine(beanFactory);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        machine.sendEvent(OrderEvent.PAY);
//        System.out.println("过了一会");
//        machine.sendEvent(OrderEvent.DELIVERY);
        //given
//        GdAccount account = GdAccount.builder().systemNo("1111").accountNo("22222").accountName("33333").gmtModify(new Date()).build();
//
//        //when
//        GdAccount accountDto = GdAccountMapper.INSTANCE.carToCarDto( account );
//        System.out.println(accountDto);
        //then
    }

}
