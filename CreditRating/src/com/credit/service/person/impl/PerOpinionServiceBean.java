package com.credit.service.person.impl;



import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.person.PerOpinion;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.person.PerOpinionService;


@Service
@Transactional
public class PerOpinionServiceBean extends DaoSupport<PerOpinion> implements PerOpinionService {

	public void UpdateOrAdd(PerOpinion opinion, String dealStatus,
			String perID, Map<String, String> searchParams) {
		if ("0".equals(dealStatus)) {
			StringBuffer sb = new StringBuffer(
					"update PerProcess o set o.applyReportState=0 ");
			for (Map.Entry<String, String> entry : searchParams.entrySet()) {
				sb.append(", o." + entry.getKey() + "=" + entry.getValue() + "");
			}
			sb.append(" where o.perBaseInfo.uuid =?1");
			entityManager.createQuery(sb.toString())
					.setParameter(1, perID).executeUpdate();
		} else {
			entityManager
					.createQuery(
							"update PerProcess o set o.scoreState=1 where o.perBaseInfo.uuid =?1")
					.setParameter(1, perID).executeUpdate();
		}
		entityManager
		.createQuery(
				"delete from PerOpinion o where o.perBaseInfo.uuid =?1 and o.isAdmin=1")
		.setParameter(1, perID).executeUpdate();
		this.save(opinion);
	}



}
