/**
 * 
 */
package com.credit.service.person.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.person.PerBaseInfoMapper;
import com.credit.model.person.PerBaseInfo;
import com.credit.service.person.PerBaseInfoService;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class PerBaseInfoServiceBean implements PerBaseInfoService {
	private static final Logger logger = Logger.getLogger(PerBaseInfoServiceBean.class);
	@Resource
	private PerBaseInfoMapper mapper;
	/* (non-Javadoc)
	 * @see com.credit.service.person.PerBaseInfoService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public int deleteByPrimaryKey(String uuid) {
		logger.info("PerBaseInfoService _ deleteByPrimaryKey;uuid:" + uuid);
		return mapper.deleteByPrimaryKey(uuid);
	}

	/* (non-Javadoc)
	 * @see com.credit.service.person.PerBaseInfoService#insert(com.credit.model.person.PerBaseInfo)
	 */
	@Override
	public int insert(PerBaseInfo record) {
		logger.info("PerBaseInfoService _ insert;record:" + record);
		return mapper.insert(record);
	}

	/* (non-Javadoc)
	 * @see com.credit.service.person.PerBaseInfoService#insertSelective(com.credit.model.person.PerBaseInfo)
	 */
	@Override
	public int insertSelective(PerBaseInfo record) {
		logger.info("PerBaseInfoService _ insertSelective;uuid:" + record);
		return mapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.credit.service.person.PerBaseInfoService#selectByPrimaryKey(java.lang.String)
	 */
	@Override
	public PerBaseInfo selectByPrimaryKey(String uuid) {
		logger.info("PerBaseInfoService _ selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

	/* (non-Javadoc)
	 * @see com.credit.service.person.PerBaseInfoService#updateByPrimaryKeySelective(com.credit.model.person.PerBaseInfo)
	 */
	@Override
	public int updateByPrimaryKeySelective(PerBaseInfo record) {
		logger.info("PerBaseInfoService _ updateByPrimaryKeySelective;record:" + record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.credit.service.person.PerBaseInfoService#updateByPrimaryKey(com.credit.model.person.PerBaseInfo)
	 */
	@Override
	public int updateByPrimaryKey(PerBaseInfo record) {
		logger.info("PerBaseInfoService _ updateByPrimaryKey;record:" + record);
		return mapper.updateByPrimaryKey(record);
	}

}
