package com.credit.mapper.enterprise;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.credit.model.enterprise.EntResult;

public interface EntResultMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(EntResult record);

    int insertSelective(EntResult record);

    EntResult selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(EntResult record);

    int updateByPrimaryKey(EntResult record);
    
    
    EntResult selectByEntId(String entId);
    
    EntResult selectByCustomerID(String customerid);

	List<EntResult> selectAll();

	EntResult selectByEntAndModel(@Param("entid")String entID, @Param("modelid")String modelID);
}