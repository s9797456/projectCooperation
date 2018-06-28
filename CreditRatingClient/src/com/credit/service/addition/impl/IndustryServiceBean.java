/**
 * 
 */
package com.credit.service.addition.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.addition.IndustryMapper;
import com.credit.model.addition.Industry;
import com.credit.service.addition.IndustryService;

/**
 * @Title: IndustryServiceBean.java
 * @author Administrator @date 2017-9-20 下午9:33:59
 * @Description: TODO
 */
@Service
@Transactional
public class IndustryServiceBean implements IndustryService {

	@Resource
	private IndustryMapper mapper;
	
	private static final Logger logger = Logger.getLogger(IndustryServiceBean.class);
	
	@Override
	public List<Industry> findAll() {
		logger.info("IndustryService_findAll;");
		return mapper.findAll();
	}

	

}
