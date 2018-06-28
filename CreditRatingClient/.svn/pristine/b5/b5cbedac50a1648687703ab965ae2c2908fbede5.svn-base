package com.credit.service.person.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.person.EducationMapper;
import com.credit.model.person.Education;
import com.credit.service.person.PerEducationService;

@Service
@Transactional
public class PerEducationServiceBean implements PerEducationService {

	private static final Logger logger = Logger.getLogger(PerEducationServiceBean.class);
	@Resource
	private EducationMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(String uuid) {
		logger.info("PerEducationService _ deleteByPrimaryKey;uuid:" + uuid);
		return mapper.deleteByPrimaryKey(uuid);
	}

	@Override
	public int insert(Education record) {
		logger.info("PerEducationService _ insert;record:" + record);
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Education record) {
		logger.info("PerEducationService _ insertSelective;record:" + record);
		return mapper.insertSelective(record);
	}

	@Override
	public Education selectByPrimaryKey(String uuid) {
		logger.info("PerEducationService _ selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

	@Override
	public int updateByPrimaryKeySelective(Education record) {
		logger.info("PerEducationService _ updateByPrimaryKeySelective;record:" + record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Education record) {
		logger.info("PerEducationService _ updateByPrimaryKey;record:" + record);
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Education> selectByPerID(String perid) {
		logger.info("PerEducationService _ selectByPrimaryKey;perid:" + perid);
		return mapper.selectByPerID(perid);
	}

	@Override
	public int countEducation(String perid) {
		logger.info("PerEducationService _ countEducation;perid:" + perid);
		return mapper.countEducation(perid);
	}

}
