/**
 * 
 */
package com.credit.service.enterprise.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.enterprise.HistoricalMapper;
import com.credit.model.enterprise.Historical;
import com.credit.service.enterprise.HistoricalService;

/**
 * @Title: HistoricalServiceBean.java
 * @author Administrator @date 2017-9-25 上午11:15:04
 * @Description: TODO
 */
@Service
@Transactional
public class HistoricalServiceBean implements HistoricalService {

	@Resource
	HistoricalMapper mapper;
	
	private static final Logger logger = Logger.getLogger(HistoricalServiceBean.class);
	
	
	/* 
	 * @author Administrator @date 2017-9-25 上午11:15:04
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.HistoricalService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public int deleteByPrimaryKey(String uuid) {
		logger.info("HistoricalService_deleteByPrimaryKey;uuid:" + uuid);
		return mapper.deleteByPrimaryKey(uuid);
	}

	/* 
	 * @author Administrator @date 2017-9-25 上午11:15:04
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.HistoricalService#insert(com.credit.model.enterprise.Historical)
	 */
	@Override
	public int insert(Historical record) {
		logger.info("HistoricalService_insert;record:" + record);
		return mapper.insert(record);
	}

	/* 
	 * @author Administrator @date 2017-9-25 上午11:15:04
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.HistoricalService#insertSelective(com.credit.model.enterprise.Historical)
	 */
	@Override
	public int insertSelective(Historical record) {
		logger.info("HistoricalService_insertSelective;record:" + record);
		return mapper.insertSelective(record);
	}

	/* 
	 * @author Administrator @date 2017-9-25 上午11:15:04
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.HistoricalService#selectByPrimaryKey(java.lang.String)
	 */
	@Override
	public Historical selectByPrimaryKey(String uuid) {
		logger.info("HistoricalService_selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

	/* 
	 * @author Administrator @date 2017-9-25 上午11:15:04
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.HistoricalService#updateByPrimaryKeySelective(com.credit.model.enterprise.Historical)
	 */
	@Override
	public int updateByPrimaryKeySelective(Historical record) {
		logger.info("HistoricalService_updateByPrimaryKeySelective;record:" + record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	/* 
	 * @author Administrator @date 2017-9-25 上午11:15:04
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.HistoricalService#updateByPrimaryKey(com.credit.model.enterprise.Historical)
	 */
	@Override
	public int updateByPrimaryKey(Historical record) {
		logger.info("HistoricalService_updateByPrimaryKey;record:" + record);
		return mapper.updateByPrimaryKey(record);
	}

	/* 
	 * @author Administrator @date 2017-9-25 上午11:15:04
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.HistoricalService#selectByEntId(java.lang.String)
	 */
	@Override
	public List<Historical> selectByEntId(String entId) {
		logger.info("HistoricalService_selectByEntId;entId:" + entId);
		return mapper.selectByEntId(entId);
	}

}
