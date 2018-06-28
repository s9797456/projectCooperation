package com.credit.mapper.enterprise;

import com.credit.model.enterprise.ProcessState;

public interface ProcessStateMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ProcessState record);

    int insertSelective(ProcessState record);

    ProcessState selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ProcessState record);

    int updateByPrimaryKey(ProcessState record);
    
    ProcessState selectByEntBaseInfoKey(String entBaseInfoUuid);

}