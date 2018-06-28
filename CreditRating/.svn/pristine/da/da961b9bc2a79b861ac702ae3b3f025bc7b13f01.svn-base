package com.credit.service.privilege;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.credit.bean.pagelist.PageView;
import com.credit.bean.privilege.Menu;
import com.credit.bean.privilege.PrivilegeGroup;
import com.credit.bean.privilege.SystemPrivilege;
import com.credit.bean.member.User;
import com.credit.dao.DAO;
import com.credit.util.tree.StandardTreeNode;
import com.credit.util.tree.SimpleTreeNode;
import com.credit.util.tree.StandardTreeNodeVo;
@SuppressWarnings("rawtypes")
public interface MenuService extends DAO<Menu> {
	public void updateVisible(String uuid, Integer visible);
	public PageView<Menu> getMenuTree();
	public String getTreeNodes(PageView<Menu> pageView);
	public List<Menu> getChildNodes(PageView<Menu> pageView);
	public void addMenu(Menu menu, String parentID);
	public void deleteAll();
	public String test();
	
	public List<Menu> getAllMenus();
	public List<Menu> getAllChildMenus(List<Menu> allMenus);
	public List<Menu> getAllRootMenus(List<Menu> allMenus);
	public Set<Menu> getMyMenus(Set<SystemPrivilege> allMySPs,List<Menu> allChildMenus);
	public ArrayList<Menu> getMyOutMenus(List<Menu> allRootMenus,Set<Menu> myMenus);
	public ArrayList<Menu> getMyOutRootMenus(List<Menu> allRootMenus,ArrayList<Menu> myOutMenus);
	public String getMyOutMenuStyle(ArrayList<Menu> myOutRootMenus,ArrayList<Menu> myOutMenus);
	
	public String getMyOutMenuStyle(User user);
	
	public List executeHql(String hql);
	public Set<SystemPrivilege> getAllOwnSPs(Menu menu);
	public Set<Menu> getAllOwnChildMenus(Menu menu);
	
	/**
	 * 将所有菜单对象转换成StandardTreeNode对象。
	 * 其中Menu的uuid、name属性对应StandardTreeNode的id、name属性。
	 * Menu的orderNO、target、rel、url属性对应StandardTreeNode的datas
	 * 的下标为0、1、2、3的值
	 * parent和children属性分别对应相应对象的引用
	 * 
	 * @return StandardTreeNode的所有根节点
	 */
	public ArrayList<StandardTreeNode> allMenusStnRootNodesList();
	
	/**
	 * 获取菜单所拥有的所有权限的集合
	 * @param menu 菜单
	 * @return 权限的Set集合
	 */
	public Set<SystemPrivilege> allSystemPrivilegesOfSomeMenu(Menu menu);
	
	/**
	 * 获取所有已被分配给菜单的权限
	 * @return 权限的set集合
	 */
	public Set<SystemPrivilege> allSystemPrivilegesDistributeToMenus();
	
	/**
	 * 获取父菜单下的所有子菜单树节点
	 * @param menu 父菜单
	 * @param user 当前用户
	 * @return	子菜单树节点的集合
	 */
	public Set<SimpleTreeNode> allChildrenTreeNode(Menu menu);
	public Set<StandardTreeNodeVo> allChildrenTreeNode(Menu menu,User user);
	public Set<SimpleTreeNode> allChildrenTreeNode(Menu menu,PrivilegeGroup privilegeGroup);
	
	/**
	 * 获取菜单下所有权限的树节点 并根据用户判断哪些权限节点被选中
	 * @param menu  菜单
	 * @param user 当前用户
	 * @return	当前菜单下所有权限的树节点的集合
	 */
	public Set<StandardTreeNodeVo>allSystemPrivilegesTreeNodeOfSomeMenu(Menu menu,User user);
	public Set<StandardTreeNodeVo>allSystemPrivilegesTreeNodeOfSomeMenu(Menu menu);
	
	public Set<SimpleTreeNode> allMenuTreeNode(User entity);
	
	public Set<SimpleTreeNode>allMenuChildrenTreeNode(Menu menu,ArrayList<Menu> myOutMenus);
	
	public Menu getMenuOfUuid(String uuid);
	public List<Menu> getAllChildMenus();
	public Set<SystemPrivilege> getMenuSps(String menuID);
	public boolean targetAndRelExist(Map<String, Object> params);
}

