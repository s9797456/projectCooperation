package com.credit.service.privilege.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.privilege.C_SystemPrivilegeMapper;
import com.credit.model.privilege.C_SystemPrivilege;
import com.credit.model.privilege.C_SystemPrivilegeKey;
import com.credit.service.privilege.C_SystemPrivilegeService;

@Service
@Transactional
public class C_SystemPrivilegeServiceBean<T extends C_SystemPrivilege> implements C_SystemPrivilegeService<T> {
	
	@Resource
	private C_SystemPrivilegeMapper mapper;
	
	private static final Logger logger = Logger.getLogger(C_SystemPrivilegeServiceBean.class);

	@Override
	public int deleteByPrimaryKey(C_SystemPrivilegeKey key) {
		logger.info("C_SystemPrivilegeService _ deleteByPrimaryKey;key:" + key);
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insertSelective(C_SystemPrivilege record) {
		logger.info("C_SystemPrivilegeService _ insertSelective;record:" + record);
		return mapper.insertSelective(record);
	}

	@Override
	public C_SystemPrivilege selectByPrimaryKey(C_SystemPrivilegeKey key) {
		logger.info("C_SystemPrivilegeService _ selectByPrimaryKey;key:" + key);
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKey(C_SystemPrivilege record) {
		logger.info("C_SystemPrivilegeService _ updateByPrimaryKey;record:" + record);
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int getCount() {
		logger.info("C_SystemPrivilegeService _ getCount();");
		return mapper.getCount();
	}

	@Override
	public boolean batchInsert(List<C_SystemPrivilege> systemPrivileges) {
		logger.info("C_SystemPrivilegeService _ batchInsert;systemPrivileges:" + systemPrivileges);
		boolean flag=true;
		for(C_SystemPrivilege cs : systemPrivileges){
			int value=mapper.insertSelective(cs);
			if(value!=1){
				flag=false;
			}
		}
		return flag;
	}

}
