package com.credit.service.person;

import java.util.List;

import com.credit.model.person.PerResult;
import com.credit.util.model.IndexRateVo;

public interface PerResultService {
	int deleteByPrimaryKey(String uuid);

    int insert(PerResult record);

    int insertSelective(PerResult record);

    PerResult selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(PerResult record);

    int updateByPrimaryKey(PerResult record);

	PerResult selectByPerID(String perid);

	/**
	 * @title 根据xml 获取 一级指标得分
	 * @param string
	 * @return
	 */
	List<IndexRateVo> getIndexRate(String string);
}
