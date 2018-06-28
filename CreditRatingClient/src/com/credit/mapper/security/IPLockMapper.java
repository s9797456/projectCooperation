package com.credit.mapper.security;

import java.util.List;

import com.credit.model.security.IPLock;

public interface IPLockMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(IPLock record);

    int insertSelective(IPLock record);

    IPLock selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(IPLock record);

    int updateByPrimaryKey(IPLock record);

	List<IPLock> selectIPList(Integer statue);

	List<IPLock> selectByIP(String iP);
}