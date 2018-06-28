package com.credit.service.person.impl;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.person.Education;
import com.credit.bean.person.Skills;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.person.SkillsService;


@Service
@Transactional
public class SkillsServiceBean extends DaoSupport<Skills> implements SkillsService {

	public List<Skills> findAllByPerID(String uuid) {
		List<Skills> skillss = new ArrayList<Skills>();
		Query query = entityManager
				.createQuery(
						"select o from Skills o where o.PerID = ?1 ")
				.setParameter(1, uuid);
		skillss = query.getResultList();
		return skillss;
	}



}
