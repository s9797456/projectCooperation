package com.credit.service.security.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.mapper.security.IPLockMapper;
import com.credit.model.security.IPLock;
import com.credit.service.security.IPLockService;





@Service
@Transactional
public class IPLockServiceBean<T extends IPLock> implements IPLockService<T> {
	
	@Resource
	private IPLockMapper mapper;

	@Override
	public List<IPLock> selectIPList(Integer statue) {
		// TODO Auto-generated method stub
		return mapper.selectIPList(statue);
	}

	@Override
	public int insert(IPLock record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public int update(IPLock record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(String uuid) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(uuid);
	}

	@Override
	public IPLock selectByPrimaryKey(String uuid) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(uuid);
	}

	@Override
	public List<IPLock> selectByIP(String iP) {
		// TODO Auto-generated method stub
		return mapper.selectByIP(iP);
	}
	

}
