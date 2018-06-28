package com.credit.service.person.impl;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.person.Career;
import com.credit.bean.person.Education;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.person.EducationService;


@Service
@Transactional
public class EducationServiceBean extends DaoSupport<Education> implements EducationService {

	public List<Education> findAllByPerID(String uuid) {
		List<Education> educations = new ArrayList<Education>();
		Query query = entityManager
				.createQuery(
						"select o from Education o where o.PerID = ?1 order by o.startTime desc")
				.setParameter(1, uuid);
		educations = query.getResultList();
		return educations;
	}



}
