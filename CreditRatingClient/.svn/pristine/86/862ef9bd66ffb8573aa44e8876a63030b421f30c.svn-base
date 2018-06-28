/**
 * 
 */
package com.credit.service.enterprise.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.enterprise.ShareholderMapper;
import com.credit.model.enterprise.Shareholder;
import com.credit.service.enterprise.ShareholderService;

/**
 * @Title: ShareholderServiceBean.java
 * @author Administrator @date 2017-8-21 上午1:05:43
 * @Description: TODO
 */
@Service
@Transactional
public class ShareholderServiceBean implements ShareholderService {

	private static final Logger logger = Logger.getLogger(ProcessStateServiceBean.class);
	@Resource
	private ShareholderMapper mapper;
	/* 
	 * @author Administrator @date 2017-8-21 上午1:05:43
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ShareholderService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public int deleteByPrimaryKey(String uuid) {
		logger.info("ShareholderService_deleteByPrimaryKey;uuid:" + uuid);
		return mapper.deleteByPrimaryKey(uuid);
	}

	/* 
	 * @author Administrator @date 2017-8-21 上午1:05:43
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ShareholderService#insert(com.credit.model.enterprise.Shareholder)
	 */
	@Override
	public int insert(Shareholder record) {
		logger.info("ShareholderService_insert;record:" + record);
		return mapper.insert(record);
	}

	/* 
	 * @author Administrator @date 2017-8-21 上午1:05:43
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ShareholderService#insertSelective(com.credit.model.enterprise.Shareholder)
	 */
	@Override
	public int insertSelective(Shareholder record) {
		logger.info("ShareholderService_insertSelective;record:" + record);
		return mapper.insertSelective(record);
	}

	/* 
	 * @author Administrator @date 2017-8-21 上午1:05:43
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ShareholderService#selectByPrimaryKey(java.lang.String)
	 */
	@Override
	public Shareholder selectByPrimaryKey(String uuid) {
		logger.info("ShareholderService_selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

	/* 
	 * @author Administrator @date 2017-8-21 上午1:05:43
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ShareholderService#selectByEntBaseInfo(java.lang.String)
	 */
	@Override
	public List<Shareholder> selectByEntBaseInfo(String entBaseInfouuid) {
		logger.info("ShareholderService_selectByEntBaseInfo;entBaseInfouuid:" + entBaseInfouuid);
		return mapper.selectByEntBaseInfo(entBaseInfouuid);
	}

	/* 
	 * @author Administrator @date 2017-8-21 上午1:05:43
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ShareholderService#updateByPrimaryKeySelective(com.credit.model.enterprise.Shareholder)
	 */
	@Override
	public int updateByPrimaryKeySelective(Shareholder record) {
		logger.info("ShareholderService_updateByPrimaryKeySelective;record:" + record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	/* 
	 * @author Administrator @date 2017-8-21 上午1:05:43
	 * (non-Javadoc)
	 * @see com.credit.service.enterprise.ShareholderService#updateByPrimaryKey(com.credit.model.enterprise.Shareholder)
	 */
	@Override
	public int updateByPrimaryKey(Shareholder record) {
		logger.info("ShareholderService_updateByPrimaryKey;record:" + record);
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int countShareholder(String entBaseInfouuid) {
		logger.info("ShareholderService_countShareholder;entBaseInfouuid:" + entBaseInfouuid);
		return mapper.countShareholder(entBaseInfouuid);
	}

}
