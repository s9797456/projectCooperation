/**
 * 
 */
package com.credit.service.person.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.person.PerHistoryMapper;
import com.credit.model.person.PerHistory;
import com.credit.service.person.PerHistoryService;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class PerHistoryServiceBean implements PerHistoryService {

	private static final Logger logger = Logger.getLogger(PerHistoryServiceBean.class);
	@Resource
	private PerHistoryMapper mapper;
	
	/* (non-Javadoc)
	 * @see com.credit.service.person.PerHistoryService#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public int deleteByPrimaryKey(String uuid) {
		logger.info("PerHistoryService _ deleteByPrimaryKey;uuid:" + uuid);
		return mapper.deleteByPrimaryKey(uuid);
	}

	/* (non-Javadoc)
	 * @see com.credit.service.person.PerHistoryService#insert(com.credit.model.person.PerHistory)
	 */
	@Override
	public int insert(PerHistory record) {
		logger.info("PerHistoryService _ insert;record:" + record);
		return mapper.insert(record);
	}

	/* (non-Javadoc)
	 * @see com.credit.service.person.PerHistoryService#insertSelective(com.credit.model.person.PerHistory)
	 */
	@Override
	public int insertSelective(PerHistory record) {
		logger.info("PerHistoryService _ insertSelective;record:" + record);
		return mapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.credit.service.person.PerHistoryService#selectByPrimaryKey(java.lang.String)
	 */
	@Override
	public PerHistory selectByPrimaryKey(String uuid) {
		logger.info("PerHistoryService _ selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

	/* (non-Javadoc)
	 * @see com.credit.service.person.PerHistoryService#updateByPrimaryKeySelective(com.credit.model.person.PerHistory)
	 */
	@Override
	public int updateByPrimaryKeySelective(PerHistory record) {
		logger.info("PerHistoryService _ updateByPrimaryKeySelective;record:" + record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.credit.service.person.PerHistoryService#updateByPrimaryKey(com.credit.model.person.PerHistory)
	 */
	@Override
	public int updateByPrimaryKey(PerHistory record) {
		logger.info("PerHistoryService _ updateByPrimaryKey;record:" +record);
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<PerHistory> selectByPerID(String perid) {
		logger.info("PerHistoryService _ selectByPerID;perid:" +perid);
		return mapper.selectByPerID(perid);
	}

	@Override
	public Boolean exists(String perid) {
		logger.info("PerHistoryService _ exists;perid:" +perid);
		if(mapper.exists(perid) > 0 ){
			return true;
		}else{
			return false;
		}
	}

}
