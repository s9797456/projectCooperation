package com.credit.service.security.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.security.LoginLimitMapper;
import com.credit.model.security.LoginLimit;
import com.credit.service.security.LoginLimitService;
import com.credit.util.security.DateUtil;
import com.credit.util.security.SecurityUtil;



@Service
@Transactional
public class LoginLimitServiceBean<T extends LoginLimit> implements LoginLimitService<T> {

	@Resource
	private LoginLimitMapper mapper;
	private static final Logger logger = Logger.getLogger(LoginLimitServiceBean.class);
	@Override
	public int delete(String uuid) {
		logger.info( "LoginLimitService_delete;uuid:"+uuid);
		return mapper.deleteByPrimaryKey(uuid);
	}
	@Override
	public int insert(LoginLimit record) {
		logger.info( "LoginLimitService_insert;LoginLimit:"+record);
		return mapper.insertSelective(record);
	}
	@Override
	public int update(LoginLimit record) {
		logger.info( "LoginLimitService_update;LoginLimit:"+record);
		return mapper.updateByPrimaryKeySelective(record);
	}
	@Override
	public LoginLimit selectByPrimaryKey(String uuid) {
		logger.info( "LoginLimitService_selectByPrimaryKey;uuid:"+uuid);
		return mapper.selectByPrimaryKey(uuid);
	}
	@Override
	public boolean Interval(String ip,Date date) {
		logger.info( "LoginLimitService_Interval;ip:"+ip);
		String LoginIntervalTime=SecurityUtil.getMsg("LoginIntervalTime");
		boolean flag=true;
		if(!LoginIntervalTime.equals("0")){
			List<LoginLimit> lls=mapper.selectByIP(ip);
			if(!lls.isEmpty()){
				long interval=DateUtil.getIntervalTime(lls.get(0).getLogintime(), date);
				if(interval<Long.parseLong(LoginIntervalTime)){
					flag=false;
				}
			}
			
		}
		return flag;
	}
	@Override
	public boolean Verification(String ip, String loginname) {
		logger.info( "LoginLimitService_Verification;ip:"+ip+";loginname:"+loginname);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		if(SecurityUtil.getMsg("LoginErrLimitTimes").equals("0")){
			return true;
		}else{
			long num=Long.parseLong(SecurityUtil.getMsg("LoginErrLimitTimes"));
			return mapper.countByLimit(ip,loginname,DateUtil.getTenMinuteTime(),sdf.format(new Date()))<num;
		}
	}

	
	
}
