package com.credit.service.enterprise.impl;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.enterprise.HistoricalData;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.enterprise.HistoricalDataService;


@Service
@Transactional
public class HistoricalDataServiceBean extends DaoSupport<HistoricalData> implements HistoricalDataService {

	public List<HistoricalData> selectByEntId(String entid) {
		Query query = entityManager
				.createQuery(
						"select o from HistoricalData o where o.entBaseInfo.uuid = ?1 ")
				.setParameter(1, entid);
		return query.getResultList();
	}

}
