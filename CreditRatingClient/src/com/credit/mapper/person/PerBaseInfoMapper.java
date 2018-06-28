package com.credit.mapper.person;

import com.credit.model.person.PerBaseInfo;

public interface PerBaseInfoMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(PerBaseInfo record);

    int insertSelective(PerBaseInfo record);

    PerBaseInfo selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(PerBaseInfo record);

    int updateByPrimaryKey(PerBaseInfo record);
}