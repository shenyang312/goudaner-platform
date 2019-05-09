package com.goudaner.platform.service;

import com.goudaner.platform.base.SyMapperUtil;
import com.goudaner.platform.common.NoUtil;
import com.goudaner.platform.dto.GdOrderDto;
import com.goudaner.platform.entity.GdOrder;

import com.goudaner.platform.entity.GdOrderMerch;
import com.goudaner.platform.mapper.GdOrderMapper;
import com.goudaner.platform.orderStateMachine.OrderEvent;
import com.goudaner.platform.orderStateMachine.OrderPersistStateMachineHandler;
import com.goudaner.platform.orderStateMachine.OrderStates;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GdOrderService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private OrderPersistStateMachineHandler handler;
	@Resource private GdOrderMapper mapper;
	@Resource private GdOrderMerchService gdOrderMerchService;

	public Integer addGdOrder(GdOrder gdOrder) {
		return mapper.insertSelective(gdOrder);
	}

	public Integer modifyGdOrder(GdOrder gdOrder, String... fieldStrs) {
		Example example = SyMapperUtil.generateExample(gdOrder, fieldStrs);
		return mapper.updateByExampleSelective(gdOrder, example);
	}

	public GdOrder getGdOrder(GdOrder gdOrder) {
		return mapper.selectOne(gdOrder);
	}

	public List<GdOrder> getGdOrders(GdOrder gdOrder) {
		return mapper.select(gdOrder);
	}

	public String orderEvent(GdOrderDto gdOrderDto) throws Exception {
		Example example = new Example(GdOrder.class);
		Example.Criteria criteria = example.createCriteria().andEqualTo("orderId", gdOrderDto.getOrderId());
		GdOrder gdOrder = mapper.selectByPrimaryKey(criteria);
		Boolean retFlag = handler.handleEventWithState(MessageBuilder.withPayload(OrderEvent.valueOf(OrderEvent.getDesc(gdOrderDto.getEventCode())))
				.setHeader("gdOrderDto", gdOrderDto).build(), OrderStates.valueOf(OrderEvent.getDesc(gdOrder.getOrderState())),"orderStateMachine");

		return retFlag?"出错了，就让你知道知道":gdOrder.getOrderId();
	}

	public String createOrder(GdOrderDto gdOrderDto)  {
		logger.info("根据商品id对缓存中库存加锁");
		logger.info("-库存");
		logger.info("释放锁");
		logger.info("生成订单");
		logger.info("如果是非抢购 需要放入orderMerch表中，记录当次订单包含那些商品");
		String orderId = NoUtil.getOrderNo();
		 mapper.insertSelective(GdOrder.builder().orderId(orderId)
				 .systemNo(gdOrderDto.getSystemNo())
				 .orderName(gdOrderDto.getOrderName())
				 .orderAmt(gdOrderDto.getOrderAmt())
				 .orderState(OrderStates.UNPAID.getCode())
				 .build());
		 List<GdOrderMerch> blist = gdOrderDto.getGdOrderMerchList().stream().map(a -> GdOrderMerch.builder().orderId(orderId).build()).collect(Collectors.toList());
		 gdOrderMerchService.addGdOrderMerchList(blist);
		return "O JB K";
	}

}

