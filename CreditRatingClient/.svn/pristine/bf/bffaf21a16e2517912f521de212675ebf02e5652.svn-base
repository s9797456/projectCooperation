package com.credit.service.privilege;

import java.util.Set;

import com.credit.model.privilege.C_Customer_RoleKey;
import com.credit.model.privilege.C_PrivilegeGroup;
import com.credit.model.privilege.C_SystemPrivilege;
import com.credit.model.privilege.C_SystemPrivilegeKey;


public interface C_PrivilegeGroupService<T> {
	
    int deleteByPrimaryKey(String uuid);

    int insertSelective(C_PrivilegeGroup record);

    C_PrivilegeGroup selectByPrimaryKey(String uuid);

    int updateByPrimaryKey(C_PrivilegeGroup record);

	boolean insertGroupPrivilege(Set<C_SystemPrivilege> systemPrivileges,String uuid);

	void insertCustomerRole(C_Customer_RoleKey ccrk);

	Set<C_SystemPrivilegeKey> selectByCustomer(String username);

	String selectByRoleName(String string);
}
