package com.goudaner.platform.service;

import com.goudaner.platform.base.SyMapperUtil;
import com.goudaner.platform.entity.GdGoods;

import com.goudaner.platform.mapper.GdGoodsMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GdGoodsService {
	@Resource private GdGoodsMapper mapper;

	public Integer addGdGoods(GdGoods gdGoods) {
		return mapper.insertSelective(gdGoods);
	}

	public Integer modifyGdGoods(GdGoods gdGoods, String... fieldStrs) {
		Example example = SyMapperUtil.generateExample(gdGoods, fieldStrs);
		return mapper.updateByExampleSelective(gdGoods, example);
	}

	public GdGoods getGdGoods(GdGoods gdGoods) {
		return mapper.selectOne(gdGoods);
	}

	public List<GdGoods> getGdGoodss(GdGoods gdGoods) {
		return mapper.select(gdGoods);
	}

}

