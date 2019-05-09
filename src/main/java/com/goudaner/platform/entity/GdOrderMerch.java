package com.goudaner.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goudaner.platform.base.SyObject;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(schema = "`goudaner`", name = "gd_order_merch")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GdOrderMerch extends SyObject {

	@Id
	@Column(name = "id")
	private Integer id;//

	@Column(name = "order_id")
	private String orderId;//订单id

	@Column(name = "gds_name")
	private String gdsName;//

	@Column(name = "gds_price")
	private BigDecimal gdsPrice;//

	@Column(name = "gds_state")
	private Integer gdsState;//订单状态：0:待支付，1:待收货，2:结束

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "gmt_create")
	private Date gmtCreate;//创建时间

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "gmt_modify")
	private Date gmtModify;//更新时间



}

