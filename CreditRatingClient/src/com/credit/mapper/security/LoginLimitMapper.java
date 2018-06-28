package com.credit.mapper.security;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.credit.model.security.LoginLimit;

public interface LoginLimitMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(LoginLimit record);

    int insertSelective(LoginLimit record);

    LoginLimit selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(LoginLimit record);

    int updateByPrimaryKey(LoginLimit record);
    
    List<LoginLimit> selectByIP(String ip);

	long countByLimit(@Param("ip")String ip, @Param("loginname")String loginname, @Param("startDate")String startDate,@Param("endDate")String endDate);
}