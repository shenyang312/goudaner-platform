package com.goudaner.platform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.goudaner.platform.base.SyObject;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(schema = "`goudaner`", name = "gd_account")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GdAccount extends SyObject {
	@Id
	@Column(name = "id")
	private Integer id;//32位自增id

	@Column(name = "account_no")
	private String accountNo;//用户id

	@Column(name = "system_no")
	private String systemNo;//系统用户id

	@Column(name = "account_name")
	private String accountName;//用户名

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "gmt_modify")
	private Date gmtModify;//更新时间

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "gmt_create")
	private Date gmtCreate;//创建时间

}

