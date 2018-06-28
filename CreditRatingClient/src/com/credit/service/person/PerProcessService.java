package com.credit.service.person;

import com.credit.model.person.PerProcess;

public interface PerProcessService {
	
	int deleteByPrimaryKey(String uuid);

    int insert(PerProcess record);

    int insertSelective(PerProcess record);

    PerProcess selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(PerProcess record);

    int updateByPrimaryKey(PerProcess record);
    
    /**
     * @Title 根据personalID 返回 ProcessState 对象
     * @author  Administrator  @date 2017-8-2 
     * @param personalID
     * @return ProcessState
     * @Description
     */
    PerProcess selectByPerID(String perid);
}