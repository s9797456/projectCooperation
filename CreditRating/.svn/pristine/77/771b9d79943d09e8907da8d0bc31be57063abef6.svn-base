package com.credit.service.addition.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.vo.addition.ModelIndex;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.addition.ModelIndexService;

@Service
@Transactional
public class ModelIndexServiceBean extends DaoSupport<ModelIndex> implements ModelIndexService{
	private static final Logger logger = Logger.getLogger(ModelIndexServiceBean.class);

	public List<ModelIndex> selectByName(String name,String queryName) {
		logger.info("ModelIndexService_selectByName;name:" + name);
		@SuppressWarnings("unchecked")
		List<ModelIndex> list = entityManager.createQuery(
						"select o from com.credit.bean.addition.ModelIndex o where o."
						+ queryName + "=?1").setParameter(1, name).getResultList();
		return list;
	}

	public List<String> selectByUpperName(String name,String queryName,String expectName) {
		logger.info("ModelIndexService_selectByName;name:" + name);
		@SuppressWarnings("unchecked")
		List<String> list = entityManager.createQuery(
						"select distinct o."+expectName+" from com.credit.bean.addition.ModelIndex o where o."
						+ queryName + "=?1").setParameter(1, name).getResultList();
		return list;
	}
}
