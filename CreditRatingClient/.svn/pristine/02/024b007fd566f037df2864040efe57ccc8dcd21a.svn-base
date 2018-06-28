package com.credit.service.enterprise.impl;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.enterprise.EntBaseInfoMapper;
import com.credit.model.enterprise.EntBaseInfo;
import com.credit.service.enterprise.EntBaseInfoService;
@Service
@Transactional
public class EntBaseInfoServiceBean implements EntBaseInfoService {

	private static final Logger logger = Logger.getLogger(EntBaseInfoServiceBean.class);
	@Resource
	private EntBaseInfoMapper mapper;

	@Override
	public EntBaseInfo selectByPrimaryKey(String uuid) {
		logger.info("EntBaseInfoService_selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

	@Override
	public int updateByPrimaryKeySelective(EntBaseInfo record) {
		logger.info("EntBaseInfoService_updateByPrimaryKeySelective;record:"+ record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertSelective(EntBaseInfo record) {
		logger.info("EntBaseInfoService_insertSelective;record:" + record);
		return mapper.insertSelective(record);
	}

	@Override
	public EntBaseInfo selectByName(String name) {
		return mapper.selectByName(name);
	}

	@Override
	public EntBaseInfo selectByUscc(String uscc) {
		return mapper.selectByUscc(uscc);
	}
}
