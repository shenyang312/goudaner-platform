package com.goudaner.platform.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goudaner.platform.base.SyObject;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

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

	private String gdsId;//商品id

	private Integer eventCode;//订单操作事件：0：创建订单 ，1：支付，2：发货，3：收货，4：拒收货


}

