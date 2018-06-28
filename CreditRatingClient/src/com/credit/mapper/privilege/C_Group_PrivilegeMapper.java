package com.credit.mapper.privilege;

import java.util.Set;

import com.credit.model.privilege.C_Group_PrivilegeKey;
import com.credit.model.privilege.C_SystemPrivilegeKey;

public interface C_Group_PrivilegeMapper {
    int deleteByPrimaryKey(C_Group_PrivilegeKey key);

    int insert(C_Group_PrivilegeKey record);

    int insertSelective(C_Group_PrivilegeKey record);

	Set<C_SystemPrivilegeKey> selectByCustomer(String username);
}