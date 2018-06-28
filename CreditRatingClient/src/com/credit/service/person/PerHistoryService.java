package com.credit.service.person;

import java.util.List;

import com.credit.model.enterprise.Historical;
import com.credit.model.person.PerHistory;

public interface PerHistoryService {
	 	int deleteByPrimaryKey(String uuid);

	    int insert(PerHistory record);

	    int insertSelective(PerHistory record);

	    PerHistory selectByPrimaryKey(String uuid);

	    int updateByPrimaryKeySelective(PerHistory record);

	    int updateByPrimaryKey(PerHistory record);

		List<PerHistory> selectByPerID(String perid);

		Boolean exists(String perid);
}
