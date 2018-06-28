/**
 * 
 */
package com.credit.service.enterprise.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.enterprise.OpinionMapper;
import com.credit.model.enterprise.Opinion;
import com.credit.service.enterprise.OpinionService;

/**
 * @Title: OpinionServiceBean.java
 * @author Administrator @date 2017-8-2 
 * @Description: TODO
 */
@Service
@Transactional
public class OpinionServiceBean implements OpinionService {

	
	private static final Logger logger = Logger
			.getLogger(OpinionServiceBean.class);
	@Resource
	private OpinionMapper mapper;
	/* 
	 * @author Administrator @date 2017-8-2 下午4:31:12
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.OpinionService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public int deleteByPrimaryKey(String uuid) {
		logger.info("OpinionService_deleteByPrimaryKey;uuid:" + uuid);
		return mapper.deleteByPrimaryKey(uuid);
	}

	/* 
	 * @author Administrator @date 2017-8-2 下午4:31:12
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.OpinionService#insert(com.credit.model.enterprise.Opinion)
	 */
	@Override
	public int insert(Opinion record) {
		logger.info("OpinionService_insert;record:" + record);
		return mapper.insert(record);
	}

	/* 
	 * @author Administrator @date 2017-8-2 下午4:31:12
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.OpinionService#insertSelective(com.credit.model.enterprise.Opinion)
	 */
	@Override
	public int insertSelective(Opinion record) {
		logger.info("OpinionService_insertSelective;record:" + record);
		return mapper.insertSelective(record);
	}

	/* 
	 * @author Administrator @date 2017-8-2 下午4:31:12
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.OpinionService#selectByPrimaryKey(java.lang.String)
	 */
	@Override
	public Opinion selectByPrimaryKey(String uuid) {
		logger.info("OpinionService_selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

	/* 
	 * @author Administrator @date 2017-8-2 下午4:31:12
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.OpinionService#updateByPrimaryKeySelective(com.credit.model.enterprise.Opinion)
	 */
	@Override
	public int updateByPrimaryKeySelective(Opinion record) {
		logger.info("OpinionService_updateByPrimaryKeySelective;record:" + record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	/* 
	 * @author Administrator @date 2017-8-2 下午4:31:12
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.OpinionService#updateByPrimaryKey(com.credit.model.enterprise.Opinion)
	 */
	@Override
	public int updateByPrimaryKey(Opinion record) {
		logger.info("OpinionService_updateByPrimaryKey;record:" + record);
		return mapper.updateByPrimaryKey(record);
	}

	/* 
	 * @author Administrator @date 2017-8-2 下午4:31:12
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.OpinionService#selectByEntBaseInfoKey(java.lang.String)
	 */
	@Override
	public Opinion selectByEntBaseInfoKey(String entBaseInfoUuid,Integer isAdmin) {
		logger.info("OpinionService_selectByEntBaseInfoKey;entBaseInfoUuid:" + entBaseInfoUuid+";isAdmin:"+isAdmin);
		return mapper.selectByEntBaseInfoKey(entBaseInfoUuid,isAdmin);
	}

}
