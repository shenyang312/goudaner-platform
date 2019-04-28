package com.goudaner.platform.service;

import com.goudaner.platform.base.SyMapperUtil;
import com.goudaner.platform.entity.GdWallet;

import com.goudaner.platform.mapper.GdWalletMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GdWalletService {
	@Resource private GdWalletMapper mapper;

	public Integer addGdWallet(GdWallet gdWallet) {
		return mapper.insertSelective(gdWallet);
	}

	public Integer modifyGdWallet(GdWallet gdWallet, String... fieldStrs) {
		Example example = SyMapperUtil.generateExample(gdWallet, fieldStrs);
		return mapper.updateByExampleSelective(gdWallet, example);
	}

	public GdWallet getGdWallet(GdWallet gdWallet) {
		return mapper.selectOne(gdWallet);
	}

	public List<GdWallet> getGdWallets(GdWallet gdWallet) {
		return mapper.select(gdWallet);
	}

}

