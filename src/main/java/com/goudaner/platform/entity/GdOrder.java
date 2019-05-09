package com.goudaner.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goudaner.platform.base.SyObject;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(schema = "`goudaner`", name = "gd_order")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GdOrder extends SyObject {
	@Id
	@Column(name = "id")
	private Integer id;//主键id

	@Column(name = "order_id")
	private String orderId;//订单id

	@Column(name = "system_no")
	private String systemNo;//用户id

	@Column(name = "order_name")
	private String orderName;//订单名称

	@Column(name = "order_amt")
	private BigDecimal orderAmt;//订单总金额

	@Column(name = "order_state")
	private Integer orderState;//订单状态：0:待支付，1:待收货，2:结束

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "gmt_modify")
	private Date gmtModify;//更新时间

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "gmt_create")
	private Date gmtCreate;//创建时间

}

