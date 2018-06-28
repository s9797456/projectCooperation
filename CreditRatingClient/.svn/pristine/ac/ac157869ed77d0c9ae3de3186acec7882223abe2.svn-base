package com.credit.mapper.enterprise;


import org.apache.ibatis.annotations.Param;

import com.credit.model.enterprise.Opinion;

public interface OpinionMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Opinion record);

    int insertSelective(Opinion record);

    Opinion selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Opinion record);

    int updateByPrimaryKey(Opinion record);
    
    Opinion selectByEntBaseInfoKey(@Param("entid")String entid,@Param("isadmin")Integer isAdmin);
}