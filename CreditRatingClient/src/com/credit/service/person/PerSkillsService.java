package com.credit.service.person;

import java.util.List;

import com.credit.model.person.Skills;

public interface PerSkillsService {
	 int deleteByPrimaryKey(String uuid);

	    int insert(Skills record);

	    int insertSelective(Skills record);

	    Skills selectByPrimaryKey(String uuid);

	    int updateByPrimaryKeySelective(Skills record);

	    int updateByPrimaryKey(Skills record);
	    
	    List<Skills> selectByPerID(String perid);

		int countSkills(String perid);
}