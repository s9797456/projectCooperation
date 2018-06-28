package com.credit.mapper.person;

import com.credit.model.person.PerProcess;

public interface PerProcessMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(PerProcess record);

    int insertSelective(PerProcess record);

    PerProcess selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(PerProcess record);

    int updateByPrimaryKey(PerProcess record);
    
    PerProcess selectByPerID(String perid);
}