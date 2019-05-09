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
public class GdOrderMerchDto extends SyObject {

	private String gdsName;//

	private BigDecimal gdsPrice;//

	private Integer gdsState;//订单状态：0:待支付，1:待收货，2:结束

}

