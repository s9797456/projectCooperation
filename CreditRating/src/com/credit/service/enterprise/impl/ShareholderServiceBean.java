package com.credit.service.enterprise.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.enterprise.Shareholder;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.enterprise.ShareholderService;


@Service
@Transactional
public class ShareholderServiceBean extends DaoSupport<Shareholder> implements ShareholderService {

	@SuppressWarnings("unchecked")
	public List<Shareholder> findAllByEntID(String entid) {
		List<Shareholder> shareholders = new ArrayList<Shareholder>();
		Query query = entityManager
				.createQuery(
						"select o from Shareholder o where o.EntID = ?1 ")
				.setParameter(1, entid);
		shareholders = query.getResultList();
		return shareholders;
	}
	
	public int exsit(String entId) {
		Query query = entityManager.createQuery("select o from Shareholder o where o.EntID = ?1").setParameter(1, entId);
		return query.getResultList().size()>0?1:0;
	}
}
