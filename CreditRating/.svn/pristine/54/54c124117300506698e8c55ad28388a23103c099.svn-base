package com.credit.service.privilege;

import java.util.List;

import com.credit.bean.privilege.SystemPrivilege;
import com.credit.dao.DAO;

public interface SystemPrivilegeService  extends DAO<SystemPrivilege> {
	/**
	 * 批量保存权限
	 * @param privileges
	 */
	public void batchSave(List<SystemPrivilege> privileges);
	/**
	 * 批量删除权限
	 * @param privileges
	 */
	public void batchDelete(List<SystemPrivilege> privileges);
	
	/**
	 * 获取数据库中所有的SystemPrivilege数据
	 * @return List<SystemPrivilege>
	 */
	public List<SystemPrivilege> allSPs();
	
	/**
	 * 获取所有的权限数据，按照model字段的升序排序
	 * @return 权限的List集合
	 */
	public List<SystemPrivilege> allSPsOrderByModel();
	
}
