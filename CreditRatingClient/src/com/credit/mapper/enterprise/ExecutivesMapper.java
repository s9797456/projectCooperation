package com.credit.mapper.enterprise;

import java.util.List;

import com.credit.model.enterprise.Executives;

public interface ExecutivesMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Executives record);

    int insertSelective(Executives record);

    Executives selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Executives record);

    int updateByPrimaryKey(Executives record);
    
    public List<Executives> selectByEntBaseInfo(String entBaseInfouuid);

    public int countExecutives(String entBaseInfouuid);
}