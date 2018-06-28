package com.credit.service.addition.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.addition.UploadFileCategory;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.addition.UploadFileCategoryService;


@Service
@Transactional
public class UploadFileCategoryServiceBean extends DaoSupport<UploadFileCategory> implements UploadFileCategoryService {

	private static final Logger logger = Logger.getLogger(UploadFileCategoryServiceBean.class);
	
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public boolean exist(String field, String value) {
		logger.info("UploadFileService_exist;field:" + field + ";value:" + value);
		long count = (Long) entityManager
				.createQuery(
						"select count(o) from com.credit.bean.addition.UploadFileCategory o where o."
						+ field + "=?1").setParameter(1, value).getSingleResult();
		return count > 0;
	}
}
