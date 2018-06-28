package com.credit.service.enterprise.impl;


import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.enterprise.FinanceMapper;
import com.credit.model.enterprise.Finance;
import com.credit.service.enterprise.FinanceService;
@Service
@Transactional
public class FinanceServiceBean implements FinanceService {

	private static final Logger logger = Logger.getLogger(FinanceServiceBean.class);
	
	@Resource
	private FinanceMapper mapper;
	@Override
	public int deleteByPrimaryKey(String uuid) {
		logger.info("FinanceService_deleteByPrimaryKey;uuid:" + uuid);
		return mapper.deleteByPrimaryKey(uuid);
	}
	@Override
	public int insert(Finance record) {
		logger.info("FinanceService_insert;Finance:" + record);
		return mapper.insertSelective(record);
	}
	@Override
	public Finance selectByPrimaryKey(String uuid) {
		logger.info("FinanceService_selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}
	@Override
	public int updateByPrimaryKey(Finance record) {
		logger.info("FinanceService_updateByPrimaryKey;Finance:" + record);
		return mapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public Finance selectByEntID(String entBaseInfoUuid) {
		logger.info("FinanceService_selectByEntID;entBaseInfoUuid:" + entBaseInfoUuid);
		return mapper.selectByEntID(entBaseInfoUuid);
	}

}
