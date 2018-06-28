package com.credit.mapper.person;

import java.util.List;

import com.credit.model.person.Career;

public interface CareerMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Career record);

    int insertSelective(Career record);

    Career selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Career record);

    int updateByPrimaryKey(Career record);

	List<Career> selectByPerID(String perid);

	int countCareer(String perid);
}