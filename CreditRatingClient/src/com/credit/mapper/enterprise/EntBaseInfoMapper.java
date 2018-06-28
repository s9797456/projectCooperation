package com.credit.mapper.enterprise;

import java.util.List;

import com.credit.model.enterprise.EntBaseInfo;

public interface EntBaseInfoMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(EntBaseInfo record);

    int insertSelective(EntBaseInfo record);

    EntBaseInfo selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(EntBaseInfo record);

    int updateByPrimaryKey(EntBaseInfo record);

	EntBaseInfo selectByName(String name);
	
	EntBaseInfo selectByUscc(String uscc);
	
	List<EntBaseInfo> selectAllByOrg(String orgId);
}