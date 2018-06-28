package com.credit.service.person.impl;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.person.Skills;
import com.credit.bean.person.Train;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.person.TrainService;


@Service
@Transactional
public class TrainServiceBean extends DaoSupport<Train> implements TrainService {

	public List<Train> findAllByPerID(String uuid) {
		List<Train> trains = new ArrayList<Train>();
		Query query = entityManager
				.createQuery(
						"select o from Train o where o.PerID = ?1 order by o.startTime desc")
				.setParameter(1, uuid);
		trains = query.getResultList();
		return trains;
	}



}
