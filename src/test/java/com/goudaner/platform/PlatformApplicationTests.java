package com.goudaner.platform;

import com.goudaner.platform.dto.GdAccountDto;
import com.goudaner.platform.entity.GdAccount;
import com.goudaner.platform.mapper.GdAccountMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlatformApplicationTests {

    @Test
    public void contextLoads() {
        //given
//        GdAccount account = GdAccount.builder().systemNo("1111").accountNo("22222").accountName("33333").gmtModify(new Date()).build();
//
//        //when
//        GdAccount accountDto = GdAccountMapper.INSTANCE.carToCarDto( account );
//        System.out.println(accountDto);
        //then
    }

}
