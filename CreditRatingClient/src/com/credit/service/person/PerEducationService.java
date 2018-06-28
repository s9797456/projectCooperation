/**
 * 
 */
package com.credit.service.person;

import java.util.List;

import com.credit.model.person.Education;

/**
 * @author Administrator
 *
 */
public interface PerEducationService {

	int deleteByPrimaryKey(String uuid);

    int insert(Education record);

    int insertSelective(Education record);

    Education selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Education record);

    int updateByPrimaryKey(Education record);

    List<Education> selectByPerID(String perid);

	int countEducation(String perid);
}
