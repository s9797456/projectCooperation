package com.credit.service.addition.impl;


import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.addition.Model;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.addition.ModelService;


@Service
@Transactional
public class ModelServiceBean extends DaoSupport<Model> implements ModelService {
	private static final Logger logger = Logger.getLogger(ModelServiceBean.class);
	
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Model> getAllRootModel() {
		logger.info("ModelService_getAllRootModel;");
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("version", "asc");
		return this.getScrollData(-1, -1, " o.parent is null ",
				new Object[] {}, orderby).getResultlist();
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public boolean exist(String field, String value) {
		logger.info("ModelService_exist;field:" + field + ";value:" + value);
		long count = (Long) entityManager
				.createQuery(
						"select count(o) from com.credit.bean.addition.Model o where o."
						+ field + "=?1").setParameter(1, value).getSingleResult();
		return count > 0;
	}
	
	public String selectByModelName(String name) {
		Query query = entityManager.createQuery("select o.uuid from Model o where o.name=?1").setParameter(1, name);
		return query.getResultList().size()==0?null:(String)query.getResultList().get(0);
	}
}
