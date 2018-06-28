package com.credit.service.person.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.person.SkillsMapper;
import com.credit.model.person.Skills;
import com.credit.service.person.PerSkillsService;
@Service
@Transactional
public class PerSkillsServiceBean implements PerSkillsService {

	private static final Logger logger = Logger.getLogger(PerSkillsServiceBean.class);
	@Resource
	private SkillsMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(String uuid) {
		logger.info("PerSkillsService _ deleteByPrimaryKey;uuid:" + uuid);
		return mapper.deleteByPrimaryKey(uuid);
	}

	@Override
	public int insert(Skills record) {
		logger.info("PerSkillsService _ insert;record:" + record);
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Skills record) {
		logger.info("PerSkillsService _ insertSelective;record:" + record);
		return mapper.insertSelective(record);
	}

	@Override
	public Skills selectByPrimaryKey(String uuid) {
		logger.info("PerSkillsService _ selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

	@Override
	public int updateByPrimaryKeySelective(Skills record) {
		logger.info("PerSkillsService _ updateByPrimaryKeySelective;record:" + record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Skills record) {
		logger.info("PerSkillsService _ updateByPrimaryKey;record:" + record);
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Skills> selectByPerID(String perid) {
		logger.info("PerSkillsService _ selectByPerID;perid:" + perid);
		return mapper.selectByPerID(perid);
	}

	@Override
	public int countSkills(String perid) {
		logger.info("PerSkillsService _ countSkills;perid:" + perid);
		return mapper.countSkills(perid);
	}

}
