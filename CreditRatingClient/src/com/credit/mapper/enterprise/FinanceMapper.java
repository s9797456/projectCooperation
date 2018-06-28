package com.credit.mapper.enterprise;

import com.credit.model.enterprise.Finance;

public interface FinanceMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Finance record);

    int insertSelective(Finance record);

    Finance selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Finance record);

    int updateByPrimaryKey(Finance record);
    
	Finance selectByEntID(String entBaseInfoUuid);
}