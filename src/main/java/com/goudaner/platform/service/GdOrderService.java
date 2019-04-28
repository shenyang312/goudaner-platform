package com.goudaner.platform.service;

import com.goudaner.platform.base.SyMapperUtil;
import com.goudaner.platform.entity.GdOrder;

import com.goudaner.platform.mapper.GdOrderMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GdOrderService {
	@Resource private GdOrderMapper mapper;

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

}

