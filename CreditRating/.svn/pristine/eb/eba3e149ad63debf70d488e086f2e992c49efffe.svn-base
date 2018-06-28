package com.credit.service.privilege;

import java.util.List;
import java.util.Set;

import com.credit.bean.privilege.PrivilegeGroup;
import com.credit.bean.privilege.SystemPrivilege;
import com.credit.dao.DAO;

public interface PrivilegeGroupService extends DAO<PrivilegeGroup>{
	/**
	 * 判断用户名是否存在
	 * @param userName 用户名
	 * @return
	 */
	public boolean exist(String field, String value);
	/**
	 * 判断用户名是否存在
	 * @param userName 用户名
	 * @return
	 */
	public boolean exist(String field, String value, String value2);
	public PrivilegeGroup searchByName(String name);
	public void batchDelete(List<PrivilegeGroup> privilegeGroups);
	
	/**
	 * 获取某个角色下所有拥有的所有的权限的Set集合
	 * @param pg 角色实体
	 * @return 权限的set集合
	 */
	public Set<SystemPrivilege> allSystemPrivilegeInSomePrivilegeGroup(PrivilegeGroup pg);
	
	
	public PrivilegeGroup getPrivilegeGroupOfUuid(String uuid);
}
