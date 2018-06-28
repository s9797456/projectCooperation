package com.credit.service.privilege.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.privilege.Organization;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.privilege.OrganizationService;


@Service
@Transactional
public class OrganizationServiceBean<T> extends DaoSupport<Organization> implements OrganizationService {

	private static final Logger logger = Logger.getLogger(OrganizationServiceBean.class);
	
	/*
	 * @author Administrator @date 2017-10-25 上午9:50:20
	 * (non-Javadoc)
	 * @see com.credit.service.privilege.OrganizationService#exist(java.lang.String, java.lang.String)
	 */
	public boolean exist(String string, String trim) {
		logger.info("OrganizationService_exist;field:" + string + ";value:"+ trim);
		long count = (Long) entityManager.createQuery("select count(o) from Organization o where o." + string+ "=?1")
				.setParameter(1, trim).getSingleResult();
		return count > 0;
	}
	
	/*
	 * @author Administrator @date 2017-10-25 上午9:50:13
	 * (non-Javadoc)
	 * @see com.credit.service.privilege.OrganizationService#exist(java.lang.String, java.lang.String, java.lang.String)
	 */
	public boolean exist(String field, String value, String value2) {
		logger.info("OrganizationService_exist;field:" + field + ";value:"+ value + ";value2:" + value2);
		long count = (Long) entityManager
				.createQuery("select count(o) from Organization o where o." + field+ "=?1 and o.uuid!=?2").setParameter(1, value)
				.setParameter(2, value2).getSingleResult();
		return count > 0;
	}
	

	public void updateVisible(String uuid, int visible) {
		// TODO Auto-generated method stub
		logger.info("OrganizationService_updateVisible;uuid:" + uuid + ";visible:" + visible);
		Organization organization = entityManager.find(Organization.class, uuid);
		organization.setVisible(visible);
		this.update(organization);
	}


}
