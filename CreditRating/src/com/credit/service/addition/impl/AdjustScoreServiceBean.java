/**
 * 
 */
package com.credit.service.addition.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.addition.AdjustScores;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.addition.AdjustScoreService;

/**
 * @Title: AdjustScoreServiceBean.java
 * @author Administrator @date 2017-7-28
 * @Description: TODO
 */
@Service
@Transactional
public class AdjustScoreServiceBean extends DaoSupport<AdjustScores> implements AdjustScoreService {

private static final Logger logger = Logger.getLogger(AdjustScoreServiceBean.class);
	
/*	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public boolean exist(String field, String value) {
		logger.info("AdjustScoreService_exist;field:" + field + ";value:" + value);
		long count = (Long) entityManager.createQuery(
						"select count(o) from com.credit.bean.addition.AdjustScores o where o."
						+ field + "=?1").setParameter(1, value).getSingleResult();
		return count > 0;
	}

	@SuppressWarnings("unchecked")
	public List<AdjustScores> selectAll() {
		Query queryThird = entityManager.createQuery("select o from AdjustScores o ");
		return  queryThird.getResultList();
	}*/
}
