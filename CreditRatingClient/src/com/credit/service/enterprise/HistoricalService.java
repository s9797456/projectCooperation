package com.credit.service.enterprise;

import java.util.List;

import com.credit.model.enterprise.Historical;

public interface HistoricalService {

	int deleteByPrimaryKey(String uuid);

    int insert(Historical record);

    int insertSelective(Historical record);

    Historical selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Historical record);

    int updateByPrimaryKey(Historical record);
    
    List<Historical> selectByEntId (String entId);
}
