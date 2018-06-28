package com.credit.mapper.enterprise;

import java.util.List;

import com.credit.model.enterprise.Shareholder;

public interface ShareholderMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Shareholder record);

    int insertSelective(Shareholder record);

    Shareholder selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Shareholder record);

    int updateByPrimaryKey(Shareholder record);
    
    public List<Shareholder> selectByEntBaseInfo(String entBaseInfouuid);

    public int countShareholder(String entBaseInfouuid);
}