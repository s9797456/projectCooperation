package com.credit.service.person.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.person.CareerMapper;
import com.credit.model.person.Career;
import com.credit.service.person.PerCareerService;
@Service
@Transactional
public class PerCareerServiceBean implements PerCareerService {

	private static final Logger logger = Logger.getLogger(PerCareerServiceBean.class);
	@Resource
	private CareerMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(String uuid) {
		logger.info("PerCareerService _ deleteByPrimaryKey;uuid:" + uuid);
		return mapper.deleteByPrimaryKey(uuid);
	}

	@Override
	public int insert(Career record) {
		logger.info("PerCareerService _ insert;record:" + record);
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Career record) {
		logger.info("PerCareerService _ insertSelective;record:" + record);
		return mapper.insertSelective(record);
	}

	@Override
	public Career selectByPrimaryKey(String uuid) {
		logger.info("PerCareerService _ selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

	@Override
	public int updateByPrimaryKeySelective(Career record) {
		logger.info("PerCareerService _ updateByPrimaryKeySelective;record:" + record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Career record) {
		logger.info("PerCareerService _ updateByPrimaryKey;record:" + record);
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Career> selectByPerID(String perid) {
		logger.info("PerCareerService _ selectByPerID;perid:" + perid);
		return mapper.selectByPerID(perid);
	}

	@Override
	public int countCareer(String perid) {
		logger.info("PerCareerService _ countCareer;uuid:" +perid);
		return mapper.countCareer(perid);
	}

}
