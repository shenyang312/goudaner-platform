package com.goudaner.platform.service;

import com.goudaner.platform.base.SyMapperUtil;
import com.goudaner.platform.entity.GdOrderMerch;

import com.goudaner.platform.mapper.GdOrderMerchMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class GdOrderMerchService {
	@Resource private GdOrderMerchMapper mapper;

	public Integer addGdOrderMerch(GdOrderMerch gdOrderMerch) {
		return mapper.insertSelective(gdOrderMerch);
	}

	public Integer addGdOrderMerchList(List<GdOrderMerch> gdOrderMerch){
		return mapper.insertList(gdOrderMerch);
	}

	public Integer modifyGdOrderMerch(GdOrderMerch gdOrderMerch, String... fieldStrs) {
		Example example = SyMapperUtil.generateExample(gdOrderMerch, fieldStrs);
		return mapper.updateByExampleSelective(gdOrderMerch, example);
	}

	public GdOrderMerch getGdOrderMerch(GdOrderMerch gdOrderMerch) {
		return mapper.selectOne(gdOrderMerch);
	}

	public List<GdOrderMerch> getGdOrderMerchs(GdOrderMerch gdOrderMerch) {
		return mapper.select(gdOrderMerch);
	}

}

