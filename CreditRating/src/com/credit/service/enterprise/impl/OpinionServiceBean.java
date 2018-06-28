package com.credit.service.enterprise.impl;


import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.enterprise.Opinion;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.enterprise.OpinionService;


@Service
@Transactional
public class OpinionServiceBean extends DaoSupport<Opinion> implements OpinionService {

	public void UpdateOrAdd(Opinion opinion, String dealStatus, String entID,
			Map<String, String> searchParams) {
		if ("0".equals(dealStatus)) {
			StringBuffer sb = new StringBuffer(
					"update ProcessState o set o.applyReportState=0 ");
			for (Map.Entry<String, String> entry : searchParams.entrySet()) {
				sb.append(", o." + entry.getKey() + "=" + entry.getValue() + "");
			}
			sb.append(" where o.entBaseInfo.uuid =?1");
			entityManager.createQuery(sb.toString())
					.setParameter(1, entID).executeUpdate();
		} else {
			entityManager
					.createQuery(
							"update ProcessState o set o.scoreState=1 where o.entBaseInfo.uuid =?1")
					.setParameter(1, entID).executeUpdate();
		}
		entityManager
		.createQuery(
				"delete from Opinion o where o.entBaseInfo.uuid =?1 and o.isAdmin=1")
		.setParameter(1, entID).executeUpdate();
		this.save(opinion);
	}

}
