package com.credit.mapper.person;

import java.util.List;

import com.credit.model.person.PerHistory;

public interface PerHistoryMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(PerHistory record);

    int insertSelective(PerHistory record);

    PerHistory selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(PerHistory record);

    int updateByPrimaryKey(PerHistory record);

	List<PerHistory> selectByPerID(String perid);

	int exists(String perid);
}