package com.credit.service.privilege;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.credit.bean.pagelist.PageView;
import com.credit.bean.privilege.Department;
import com.credit.bean.privilege.PrivilegeGroup;
import com.credit.bean.member.User;
import com.credit.dao.DAO;
import com.credit.util.tree.TreeNode;
import com.credit.util.tree.SimpleTreeNode;

public interface DepartmentService extends DAO<Department> {
	public void updateVisible(String uuid, Integer visible);

	public boolean exist(String field, String value);

	public boolean exist(String field, String value, String value2);

	public PageView<Department> getDepTree();

	public List<User> getDepOfUser(Serializable... entityids);

	public void cleanUsers();

	public void addDep(Department dep, String parentID);

	@SuppressWarnings("rawtypes")
	public List executeHql(String hql);

	public void deleteAll();

	public ArrayList<TreeNode> getAllDepTree();

	/**
	 * 将所有的部门对象转换成TreeNode对象，并返回根节点的ArrayList<TreeNode> 根据这些根节点可遍历出整棵树
	 * 
	 * @return 所有根节点
	 */
	public ArrayList<TreeNode> convertAllDepsToTreeNodesPublic();

	/**
	 * 获取拥有某个部门的所有角色
	 * 
	 * @param dep
	 *            角色拥有的部门
	 * @return 所有拥有该部门的角色
	 */
	public Set<PrivilegeGroup> allRolesOwnSomeDepartment(Department dep);

	/**
	 * 初始化页面zTree控件的simpleData类型的数据， 展现系统中所有的部门
	 * 
	 * @return 初始化树形控件的数据
	 */
	public String getAllDepZTreeJson();

	/**
	 * 通过传入的sn，查找相应的部门
	 * 
	 * @param sn
	 *            sn字符串
	 * @return 拥有相同sn值的部门
	 */
	public Department searchBySn(String sn);

	/**
	 * 根据用户所在的部门查询用户所有可见的部门， 并把这些Department对象都转换成TreeNode对象放在List集合中。
	 * 
	 * @param depSet
	 *            有权限的部门的dep的Set
	 * @return 所有可见的部门
	 */
	public ArrayList<TreeNode> getAllSeeDepsTnList(Department userDep);

	@SuppressWarnings("rawtypes")
	public Set<SimpleTreeNode> allChildrenTreeNode(Department userDep);

	/**
	 * 
	 * @param twoDomainNames
	 * @return
	 * @author hzhu
	 * @date 2015-7-27 上午9:36:59
	 * @comment 根据两级域名查找记录
	 */
	public Department findByTwoDomainNames(String twoDomainNames);

	/**
	 * 
	 * @param name
	 * @return
	 * @author hzhu
	 * @date 2015-10-29 下午12:44:49
	 * @comment
	 */
	public String findByName(String name);

	/**
	 * 
	 * @param name
	 * @return
	 * @author hzhu
	 * @date 2015-11-30 下午4:20:25
	 * @comment
	 */
	public Department findEntityByName(String name);
}
