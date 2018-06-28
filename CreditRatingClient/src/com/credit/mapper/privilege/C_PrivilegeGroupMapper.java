package com.credit.mapper.privilege;

import com.credit.model.privilege.C_Group_PrivilegeKey;
import com.credit.model.privilege.C_PrivilegeGroup;

public interface C_PrivilegeGroupMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(C_PrivilegeGroup record);

    int insertSelective(C_PrivilegeGroup record);

    C_PrivilegeGroup selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(C_PrivilegeGroup record);

    int updateByPrimaryKey(C_PrivilegeGroup record);

	int insertGroupPrivilege(C_Group_PrivilegeKey cgpk);

	String selectByRoleName(String name);
}