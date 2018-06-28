package com.credit.service.addition.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.addition.ModelMapper;
import com.credit.model.addition.Model;
import com.credit.service.addition.ModelService;


@Service
@Transactional
public class ModelServiceBean<T extends Model> implements ModelService<T> {

	@Resource
	private ModelMapper mapper;
	
	private static final Logger logger = Logger.getLogger(ModelServiceBean.class);

	@Override
	public Model selectByPrimaryKey(String uuid) {
		logger.info("ModelService_selectByPrimaryKey;uuid:" + uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

}
