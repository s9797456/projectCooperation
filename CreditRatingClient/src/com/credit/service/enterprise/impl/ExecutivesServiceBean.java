/**
 * 
 */
package com.credit.service.enterprise.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.enterprise.ExecutivesMapper;
import com.credit.model.enterprise.Executives;
import com.credit.service.enterprise.ExecutivesService;

/**
 * @Title: ExecutivesServiceBean.java
 * @author Administrator @date 2017-8-28 上午10:22:05
 * @Description: TODO
 */
@Service
@Transactional
public class ExecutivesServiceBean implements ExecutivesService {

	private static final Logger logger = Logger.getLogger(ExecutivesServiceBean.class);
	@Resource
	ExecutivesMapper mapper;
	/* 
	 * @author Administrator @date 2017-8-28 上午10:22:05
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ExecutivesService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public int deleteByPrimaryKey(String uuid) {
		logger.info("ExecutivesService_deleteByPrimaryKey;uuid:" + uuid);
		return mapper.deleteByPrimaryKey(uuid);
	}

	/* 
	 * @author Administrator @date 2017-8-28 上午10:22:05
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ExecutivesService#insert(com.credit.model.enterprise.Executives)
	 */
	@Override
	public int insert(Executives record) {
		logger.info("ExecutivesService_insert;record:" + record);
		return mapper.insert(record);
	}

	/* 
	 * @author Administrator @date 2017-8-28 上午10:22:05
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ExecutivesService#insertSelective(com.credit.model.enterprise.Executives)
	 */
	@Override
	public int insertSelective(Executives record) {
		logger.info("ExecutivesService_insertSelective;record:" + record);
		return mapper.insertSelective(record);
	}

	/* 
	 * @author Administrator @date 2017-8-28 上午10:22:05
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ExecutivesService#selectByPrimaryKey(java.lang.String)
	 */
	@Override
	public Executives selectByPrimaryKey(String uuid) {
		logger.info("ExecutivesService_selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

	/* 
	 * @author Administrator @date 2017-8-28 上午10:22:05
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ExecutivesService#updateByPrimaryKeySelective(com.credit.model.enterprise.Executives)
	 */
	@Override
	public int updateByPrimaryKeySelective(Executives record) {
		logger.info("ExecutivesService_updateByPrimaryKeySelective;record:" + record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	/* 
	 * @author Administrator @date 2017-8-28 上午10:22:05
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ExecutivesService#updateByPrimaryKey(com.credit.model.enterprise.Executives)
	 */
	@Override
	public int updateByPrimaryKey(Executives record) {
		logger.info("ExecutivesService_updateByPrimaryKey;record:" + record);
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Executives> selectByEntBaseInfo(String entBaseInfouuid) {
		// TODO Auto-generated method stub
		return mapper.selectByEntBaseInfo(entBaseInfouuid);
	}

	@Override
	public int countExecutives(String entBaseInfouuid) {
		// TODO Auto-generated method stub
		return mapper.countExecutives(entBaseInfouuid);
	}

}
