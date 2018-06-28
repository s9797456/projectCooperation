package com.credit.service.member;

import java.util.List;
import java.util.Set;

import com.credit.bean.member.User;
import com.credit.bean.privilege.Department;
import com.credit.bean.privilege.PrivilegeGroup;
import com.credit.bean.privilege.SystemPrivilege;
import com.credit.dao.DAO;

public interface UserService extends DAO<User> {
	
	/**
	 * 更新密码
	 * 
	 * @param userName
	 *            用户名
	 * @param newpassword
	 *            新密码
	 */
	public void updatePassword(String userName, String newPassword);

	/**
	 * 判断用户名是否存在
	 * 
	 * @param userName
	 *            用户名
	 * @return
	 */
	public boolean exist(String field, String value);

	/**
	 * 判断用户名是否存在
	 * 
	 * @param userName
	 *            用户名
	 * @return
	 */
	public boolean exist(String field, String value, String value2);

	/**
	 * 判断用户名及密码是否正确
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	public boolean validate(String userName, String password);

	/**
	 * 找回密码判断用户名及邮箱是否正确
	 * 
	 * @param userName
	 *            用户名
	 * @param email
	 *            邮箱
	 * @return
	 */
	public boolean validateFindpwd(String userName, String email);

	public Set<Department> getUserDeps(User user);

	public void batchDelete(List<User> users);

	/**
	 * 获取用户所拥有的所有的角色
	 * 
	 * @param user
	 *            用户
	 * @return 角色的Set集合
	 */
	public Set<PrivilegeGroup> getUserPrivilegeGroups(User user);

	/**
	 * 获取只属于用户名下的所有权限
	 * 
	 * @param user
	 *            用户
	 * @return 权限的Set集合
	 */
	public Set<SystemPrivilege> getSystemPrivilegesOnlyUnderUser(User user);

	/**
	 * 获取用户所拥有的所有权限，包括通过角色获得的权限和角色下所拥有的权限 取所有权限的合集
	 * 
	 * @param user
	 *            用户
	 * @return 权限的Set集合
	 */
	public Set<SystemPrivilege> getUserSystemPrivileges(User user);

	public Set<SystemPrivilege> getRoleSPs(Set<PrivilegeGroup> pgs);

	public Set<SystemPrivilege> getAllSPs(Set<SystemPrivilege> sps1,
			Set<SystemPrivilege> sps2);

	/**
	 * 获取用户所拥有的所有的角色
	 * 
	 * @param userName
	 *            关键字用户名
	 * @return 用户实体
	 */
	public User getUserOfUserName(String userName);

	/**
	 * 获取用户所拥有的所有的权限
	 * 
	 * @param userName
	 *            关键字用户名
	 * @return 用户权限的Set集合
	 */
	public Set<SystemPrivilege> getUserSPsOfUserName(String userName);

	public Set<PrivilegeGroup> getUserRolesOfUserName(String userName);

	public void deleteByUserNameGetRole(String userName);

}
