package com.goudaner.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goudaner.platform.base.SyObject;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(schema = "`goudaner`", name = "dg_wallet")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GdWallet extends SyObject {
	@Id
	@Column(name = "id")
	private Integer id;//主键id

	@Column(name = "system_no")
	private String systemNo;//用户id

	@Column(name = "order_name")
	private String orderName;//订单名称

	@Column(name = "total_amt")
	private BigDecimal totalAmt;//累计总金额

	@Column(name = "surplus")
	private BigDecimal surplus;//剩余金额

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "gmt_modify")
	private Date gmtModify;//更新时间

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "gmt_create")
	private Date gmtCreate;//创建时间

}

