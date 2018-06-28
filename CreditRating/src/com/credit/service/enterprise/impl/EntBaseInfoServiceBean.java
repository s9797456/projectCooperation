package com.credit.service.enterprise.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.enterprise.EntBaseInfo;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.enterprise.EntBaseInfoService;


@Service
@Transactional
public class EntBaseInfoServiceBean extends DaoSupport<EntBaseInfo> implements EntBaseInfoService {

	public String selectByEntName(String name) {
		Query query = entityManager.createQuery("select o.uuid from EntBaseInfo o where o.name=?1");
		query.setParameter(1, name);
		return query.getResultList().size()==0?null:(String) query.getResultList().get(0);
	}


	public List<EntBaseInfo> selectByName(String name) {
		Query query=entityManager.createQuery("SELECT o FROM EntBaseInfo o where o.name=?1");
		query.setParameter(1, name);
		return query.getResultList();
	}


	public List<String> getRepetitiveName() {
		Query query=entityManager.createQuery("SELECT o.name FROM EntBaseInfo o group by name having count(*)>1");
		return query.getResultList();	
	}


	public EntBaseInfo selectById(String uuid) {
		Query query=entityManager.createQuery("SELECT o FROM EntBaseInfo o where o.uuid=?1");
		query.setParameter(1, uuid);
		return (EntBaseInfo) query.getResultList().get(0);
	}


	public List<String> getAllEntId() {
		Query query=entityManager.createQuery("SELECT o.uuid FROM EntBaseInfo o");
		return query.getResultList();
	}


	public EntBaseInfo selectByUSCC(String uscc) {
		Query query = entityManager.createQuery("SELECT o FROM EntBaseInfo o where o.USCC=?1");
		query.setParameter(1, uscc);
		return query.getResultList().size()==1?(EntBaseInfo) query.getResultList().get(0):null;
	}

}
