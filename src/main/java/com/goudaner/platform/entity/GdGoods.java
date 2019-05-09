package com.goudaner.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goudaner.platform.base.SyObject;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(schema = "`goudaner`", name = "gd_goods")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GdGoods extends SyObject {
	@Id
	@Column(name = "id")
	private Integer id;//主键id

	@Column(name = "gds_id")
	private String gdsId;//商品id

	@Column(name = "gds_name")
	private String gdsName;//商品名称

	@Column(name = "gds_price")
	private BigDecimal gdsPrice;//商品价格

	@Column(name = "gds_type")
	private String gdsType;//商品类型

	@Column(name = "gds_stock")
	private Integer gdsStock;//商品库存

	@Column(name = "gds_live_stock")
	private String gdsLiveStock;//商品活跃库存

	@Column(name = "gds_total_stock")
	private Integer gdsTotalStock;//商品总库存

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "gmt_modify")
	private Date gmtModify;//更新时间

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "gmt_create")
	private Date gmtCreate;//创建时间

}

