package com.credit.service.member.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.member.UserMapper;
import com.credit.model.member.User;
import com.credit.service.member.UserService;
import com.credit.util.DateTime;

@Service
@Transactional
public class UserServiceBean<T extends User> implements UserService<T> {

	@Resource
	private UserMapper mapper;
	
	private static final Logger logger = Logger.getLogger(UserServiceBean.class);

	@Override
	public int deleteByPrimaryKey(String username) {
		logger.info("UserService_deleteByPrimaryKey;username:" + username);
		return mapper.deleteByPrimaryKey(username);
	}

	@Override
	public int insertSelective(User record) {
		logger.info("UserService_insertSelective;record:" + record);
		record.setUpdatetime(DateTime.getCurrentTimeStamp());
		return mapper.insertSelective(record);
	}

	@Override
	public User selectByPrimaryKey(String username) {
		logger.info("UserService_selectByPrimaryKey;username:" + username);
		return mapper.selectByPrimaryKey(username);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		logger.info("UserService_updateByPrimaryKey;record:" + record);
		record.setUpdatetime(DateTime.getCurrentTimeStamp());
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public User selectByParam(Map<String, Object> params) {
		logger.info("UserService_selectByParam;params:" + params);
		return mapper.selectByParam(params);
	}


}
