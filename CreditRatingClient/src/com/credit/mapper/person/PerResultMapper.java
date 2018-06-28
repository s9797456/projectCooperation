package com.credit.mapper.person;

import com.credit.model.person.PerResult;

public interface PerResultMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(PerResult record);

    int insertSelective(PerResult record);

    PerResult selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(PerResult record);

    int updateByPrimaryKey(PerResult record);

	PerResult selectByPerID(String perid);

}