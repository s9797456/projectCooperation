package com.credit.mapper.security;

import org.apache.ibatis.annotations.Param;

import com.credit.model.security.RegistLimit;

public interface RegistLimitMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(RegistLimit record);

    int insertSelective(RegistLimit record);

    RegistLimit selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(RegistLimit record);

    int updateByPrimaryKey(RegistLimit record);

    public long countByIPLimit(@Param("IP")String ip, @Param("startDate")String startDate,@Param("endDate")String endDate);

   	public long countByPhoneLimit(@Param("Phone")String phone, @Param("startDate")String startDate,@Param("endDate")String endDate);
   	
	public long countByNumLimit(@Param("startDate")String startDate,@Param("endDate")String endDate);
}