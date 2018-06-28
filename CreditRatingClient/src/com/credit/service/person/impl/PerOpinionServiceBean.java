package com.credit.service.person.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.person.PerOpinionMapper;
import com.credit.model.person.PerOpinion;
import com.credit.service.person.PerOpinionService;

@Service
@Transactional
public class PerOpinionServiceBean implements PerOpinionService {

	private static final Logger logger = Logger.getLogger(PerOpinionServiceBean.class);
	@Resource
	private PerOpinionMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(String uuid) {
		logger.info("PerOpinionService _ deleteByPrimaryKey;uuid:" + uuid);
		return mapper.deleteByPrimaryKey(uuid);
	}

	@Override
	public int insert(PerOpinion record) {
		logger.info("PerOpinionService _ insert;record:" + record);
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(PerOpinion record) {
		logger.info("PerOpinionService _ insertSelective;record:" + record);
		return mapper.insertSelective(record);
	}

	@Override
	public PerOpinion selectByPrimaryKey(String uuid) {
		logger.info("PerOpinionService _ selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

	@Override
	public int updateByPrimaryKeySelective(PerOpinion record) {
		logger.info("PerOpinionService _ updateByPrimaryKeySelective;record:" + record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PerOpinion record) {
		logger.info("PerOpinionService _ updateByPrimaryKey;record:" + record);
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public PerOpinion selectByPerIDReject(String perid) {
		logger.info("PerOpinionService _ selectByPerIDReject;perid:" + perid);
		return mapper.selectByPerIDReject(perid);
	}

	@Override
	public PerOpinion selectByPerIDAndScore(String perid) {
		logger.info("PerOpinionService _ selectByPerIDAndScore;perid:" + perid);
		return mapper.selectByPerIDAndScore(perid);
	}

}
