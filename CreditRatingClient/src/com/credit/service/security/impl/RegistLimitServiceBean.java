package com.credit.service.security.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.security.RegistLimitMapper;
import com.credit.model.security.RegistLimit;
import com.credit.service.security.RegistLimitService;
import com.credit.util.security.SecurityUtil;



@Service
@Transactional
public class RegistLimitServiceBean<T extends RegistLimit> implements RegistLimitService<T> {

	@Resource
	private RegistLimitMapper mapper;
	private static final Logger logger = Logger.getLogger(RegistLimitServiceBean.class);
	
	@Override
	public void insertSelective(RegistLimit record) {
		logger.info( "RegistLimitService_insert;record:"+record);
		mapper.insertSelective(record);
	}
	
	@Override
	public boolean IPLimit(String ip, String startDate, String endDate) {
		logger.info( "RegistLimitService_IPLimit;ip:"+ip);
		if(SecurityUtil.getMsg("RegIPNum").equals("0")){
			return true;
		}else{
			long num=Long.parseLong(SecurityUtil.getMsg("RegIPNum"));
			return mapper.countByIPLimit(ip,startDate,endDate)<num;
		}
	}
	
	@Override
	public boolean PhoneLimit(String phone, String startDate, String endDate) {
		logger.info( "RegistLimitService_PhoneLimit;phone:"+phone);
		if(SecurityUtil.getMsg("RegPhoneNum").equals("0")){
			return true;
		}else{
			long num=Long.parseLong(SecurityUtil.getMsg("RegPhoneNum"));
			return mapper.countByPhoneLimit(phone,startDate,endDate)<num;
		}
	}

	@Override
	public boolean NumLimit(String startDate, String endDate) {
		logger.info( "RegistLimitService_NumLimit");
		if(SecurityUtil.getMsg("EnableTodayReg").equals("0")){
			return true;
		}else{
			long num=Long.parseLong(SecurityUtil.getMsg("EnableTodayReg"));
			return mapper.countByNumLimit(startDate,endDate)<num;
		}
	}

	@Override
	public RegistLimit selectByPrimaryKey(String uuid) {
		logger.info( "RegistLimitService_selectByPrimaryKey;uuid:"+uuid);
		return mapper.selectByPrimaryKey(uuid);
	}

	@Override
	public int updateByPrimaryKey(RegistLimit record) {
		logger.info( "RegistLimitService_updateByPrimaryKey;RegistLimit:"+record);
		return mapper.updateByPrimaryKeySelective(record);
	}
	

}
