package com.credit.service.member.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.member.CustomerMapper;
import com.credit.model.member.Customer;
import com.credit.modelvo.Industry;
import com.credit.service.member.CustomerService;
import com.credit.util.DateTime;

@Service
@Transactional
public class CustomerServiceBean<T extends Customer> implements CustomerService<T> {

	private static final Logger logger = Logger.getLogger(CustomerServiceBean.class);
	@Resource
	private CustomerMapper mapper;

	@Override
	public int deleteByPrimaryKey(String username) {
		logger.info("CustomerService_deleteByPrimaryKey;username:" + username);
		return mapper.deleteByPrimaryKey(username);
	}

	@Override
	public int insertSelective(Customer record) {
		logger.info("CustomerService_insertSelective;record:" + record);
		record.setUpdatetime(DateTime.getCurrentTimeStamp());
		return mapper.insertSelective(record);
	}

	@Override
	public Customer selectByPrimaryKey(String username) {
		logger.info("CustomerService_selectByPrimaryKey;username:" + username);
		return mapper.selectByPrimaryKey(username);
	}

	@Override
	public int updateByPrimaryKey(Customer record) {
		logger.info("CustomerService_updateByPrimaryKey;record:" + record);
		record.setUpdatetime(DateTime.getCurrentTimeStamp());
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Customer selectByParam(Map<String, Object> params) {
		logger.info("CustomerService_selectByParam;params:" + params);
		return mapper.selectByParam(params);
	}

	@Override
	public Customer selectByEntID(String entID) {
		logger.info("CustomerService_selectByEntID;entID:" + entID);
		return mapper.selectByEntID(entID);
	}
	
	@Override
	public List<Industry> findAllIndustry() {
		logger.info("CustomerService_findAllIndustry;");
		return mapper.findAllIndustry();
	}

	@Override
	public List<Customer> selectAll() {
		// TODO Auto-generated method stub
		return mapper.selectAll();
	}

}
