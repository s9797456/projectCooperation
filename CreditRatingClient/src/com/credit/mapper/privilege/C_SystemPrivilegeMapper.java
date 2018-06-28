package com.credit.mapper.privilege;

import com.credit.model.privilege.C_SystemPrivilege;
import com.credit.model.privilege.C_SystemPrivilegeKey;

public interface C_SystemPrivilegeMapper {
    int deleteByPrimaryKey(C_SystemPrivilegeKey key);

    int insert(C_SystemPrivilege record);

    int insertSelective(C_SystemPrivilege record);

    C_SystemPrivilege selectByPrimaryKey(C_SystemPrivilegeKey key);

    int updateByPrimaryKeySelective(C_SystemPrivilege record);

    int updateByPrimaryKey(C_SystemPrivilege record);

	int getCount();
}