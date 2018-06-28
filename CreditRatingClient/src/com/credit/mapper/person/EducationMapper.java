package com.credit.mapper.person;

import java.util.List;

import com.credit.model.person.Education;

public interface EducationMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Education record);

    int insertSelective(Education record);

    Education selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Education record);

    int updateByPrimaryKey(Education record);

	List<Education> selectByPerID(String perid);

	int countEducation(String perid);
}