package com.credit.service.enterprise.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.enterprise.Executives;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.enterprise.ExecutivesService;


@Service
@Transactional
public class ExecutivesServiceBean extends DaoSupport<Executives> implements ExecutivesService {

	public List<Executives> findAllByEntID(String entid) {
		List<Executives> executives = new ArrayList<Executives>();
		Query query = entityManager
				.createQuery(
						"select o from Executives o where o.EntID = ?1 ")
				.setParameter(1, entid);
		executives = query.getResultList();
		return executives;
	}

	public int exsit(String entId) {
		Query query = entityManager.createQuery("select o from Executives o where o.EntID = ?1 ").setParameter(1, entId);
		return query.getResultList().size()>0?1:0;
	}

}
