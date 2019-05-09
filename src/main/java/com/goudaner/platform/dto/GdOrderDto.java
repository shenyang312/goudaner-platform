package com.goudaner.platform.dto;

import com.goudaner.platform.base.SyObject;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GdOrderDto extends SyObject {

	private String orderId;//订单id

	private String systemNo;//用户id

	private String orderName;//订单名称

	private BigDecimal orderAmt;//订单总金额

	private Integer orderState;//订单状态：0:待支付，1:待收货，2:结束

	private List<GdOrderMerchDto> gdOrderMerchList;//商品集合

	private Integer eventCode;//订单操作事件：0：创建订单 ，1：支付，2：发货，3：收货，4：拒收货


}

