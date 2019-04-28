package com.goudaner.platform.service;

import com.goudaner.platform.base.SyMapperUtil;
import com.goudaner.platform.entity.GdAccount;

import com.goudaner.platform.mapper.GdAccountMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GdAccountService {
	@Resource private GdAccountMapper mapper;

	public Integer addGdAccount(GdAccount gdAccount) {
		return mapper.insertSelective(gdAccount);
	}

	public Integer modifyGdAccount(GdAccount gdAccount, String... fieldStrs) {
		Example example = SyMapperUtil.generateExample(gdAccount, fieldStrs);
		return mapper.updateByExampleSelective(gdAccount, example);
	}

	public GdAccount getGdAccount(GdAccount gdAccount) {
		return mapper.selectOne(gdAccount);
	}

	public List<GdAccount> getGdAccounts(GdAccount gdAccount) {
		return mapper.select(gdAccount);
	}

}

