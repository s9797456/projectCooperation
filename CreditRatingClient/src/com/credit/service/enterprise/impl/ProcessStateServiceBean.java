/**
 * 
 */
package com.credit.service.enterprise.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.enterprise.ProcessStateMapper;
import com.credit.model.enterprise.ProcessState;
import com.credit.service.enterprise.ProcessStateService;

/**
 * @Title: ProcessStateServiceBean.java
 * @author Administrator @date 2017-8-2 下午4:55:21
 * @Description: TODO
 */
@Service
@Transactional
public class ProcessStateServiceBean implements ProcessStateService {

	private static final Logger logger = Logger.getLogger(ProcessStateServiceBean.class);
	@Resource
	private ProcessStateMapper mapper;
	/* 
	 * @author Administrator @date 2017-8-2 下午4:55:21
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ProcessStateService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public int deleteByPrimaryKey(String uuid) {
		logger.info("ProcessStateService_deleteByPrimaryKey;uuid:" + uuid);
		return mapper.deleteByPrimaryKey(uuid);
	}

	/* 
	 * @author Administrator @date 2017-8-2 下午4:55:21
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ProcessStateService#insert(com.credit.model.enterprise.ProcessState)
	 */
	@Override
	public int insert(ProcessState record) {
		logger.info("ProcessStateService_insert;record:" + record);
		return mapper.insert(record);
	}

	/* 
	 * @author Administrator @date 2017-8-2 下午4:55:21
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ProcessStateService#insertSelective(com.credit.model.enterprise.ProcessState)
	 */
	@Override
	public int insertSelective(ProcessState record) {
		logger.info("ProcessStateService_insertSelective;record:" + record);
		return mapper.insertSelective(record);
	}

	/* 
	 * @author Administrator @date 2017-8-2 下午4:55:21
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ProcessStateService#selectByPrimaryKey(java.lang.String)
	 */
	@Override
	public ProcessState selectByPrimaryKey(String uuid) {
		logger.info("ProcessStateService_selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

	/* 
	 * @author Administrator @date 2017-8-2 下午4:55:21
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ProcessStateService#updateByPrimaryKeySelective(com.credit.model.enterprise.ProcessState)
	 */
	@Override
	public int updateByPrimaryKeySelective(ProcessState record) {
		logger.info("ProcessStateService_updateByPrimaryKeySelective;record:" + record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	/* 
	 * @author Administrator @date 2017-8-2 下午4:55:21
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ProcessStateService#updateByPrimaryKey(com.credit.model.enterprise.ProcessState)
	 */
	@Override
	public int updateByPrimaryKey(ProcessState record) {
		logger.info("ProcessStateService_updateByPrimaryKey;record:" + record);
		return mapper.updateByPrimaryKey(record);
	}

	/* 
	 * @author Administrator @date 2017-8-2 下午4:55:21
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ProcessStateService#selectByEntBaseInfoKey(java.lang.String)
	 */
	@Override
	public ProcessState selectByEntBaseInfoKey(String entBaseInfoUuid) {
		logger.info("ProcessStateService_selectByEntBaseInfoKey;entBaseInfoUuid:" + entBaseInfoUuid);
		return mapper.selectByEntBaseInfoKey(entBaseInfoUuid);
	}

}
