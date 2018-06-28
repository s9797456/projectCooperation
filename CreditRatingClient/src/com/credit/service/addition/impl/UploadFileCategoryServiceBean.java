package com.credit.service.addition.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.addition.UploadFileCategoryMapper;
import com.credit.model.addition.UploadFileCategory;
import com.credit.service.addition.UploadFileCategoryService;


@Service
@Transactional
public class UploadFileCategoryServiceBean<T extends UploadFileCategory> implements UploadFileCategoryService<T> {

	@Resource
	private UploadFileCategoryMapper mapper;
	
	private static final Logger logger = Logger.getLogger(UploadFileCategoryServiceBean.class);

	@Override
	public List<UploadFileCategory> selectEntRelevant() {
		logger.info("UploadFileCategoryService_selectEntRelevant;");
		return mapper.selectEntRelevant();
	}

	@Override
	public List<UploadFileCategory> selectPersonalRelevant() {
		logger.info("UploadFileCategoryService_selectPersonalRelevant;");
		return mapper.selectPersonalRelevant();
	}
	
	@Override
	public UploadFileCategory selectByPrimaryKey(String uuid) {
		logger.info("UploadFileCategoryService_selectByPrimaryKey;uuid:"+uuid);
		return mapper.selectByPrimaryKey(uuid);
	}
}
