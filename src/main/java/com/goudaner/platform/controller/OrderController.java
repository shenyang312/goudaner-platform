package com.goudaner.platform.controller;

import com.goudaner.platform.dto.GdOrderDto;
import com.goudaner.platform.entity.GdAccount;
import com.goudaner.platform.service.GdOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private GdOrderService gdOrderService;
    @RequestMapping("/orderEvent")
    public String orderEvent(@RequestBody GdOrderDto gdOrderDto){
        try {
            return gdOrderService.orderEvent(gdOrderDto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("程序异常，等会你tm再试");
            return "程序异常，等会你tm再试";
        }
    }

    @RequestMapping("/createOrder")
    public String createOrder(@RequestBody GdOrderDto gdOrderDto){
        try {
            return gdOrderService.createOrder(gdOrderDto);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("程序异常，等会你tm再试");
            return "程序异常，等会你tm再试";
        }
    }
}
