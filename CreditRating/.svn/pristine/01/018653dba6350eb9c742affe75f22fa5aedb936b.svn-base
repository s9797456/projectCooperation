package com.credit.service.privilege.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.credit.bean.pagelist.PageView;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.privilege.Menu;
import com.credit.bean.privilege.PrivilegeGroup;
import com.credit.bean.privilege.SystemPrivilege;
import com.credit.bean.privilege.SystemPrivilegePK;
import com.credit.bean.vo.privilege.TreeNode;
import com.credit.bean.member.User;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.privilege.MenuService;
import com.credit.service.privilege.SystemPrivilegeService;
import com.credit.service.member.UserService;
import com.credit.util.tree.StandardTreeNode;
import com.credit.util.tree.SimpleTreeNode;
import com.credit.util.tree.StandardTreeNodeVo;
import com.google.gson.Gson;

@Service
@Transactional
public class MenuServiceBean<T> extends DaoSupport<Menu> implements MenuService {
	/**
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(MenuServiceBean.class);
	@Resource
	UserService userService;
	@Resource
	SystemPrivilegeService systemPrivilegeService;
	@Override
	public void save(Menu entity) {
		logger.info("MenuService_save;entity:" + entity);
		((Menu) entity).setUuid(UUID.randomUUID().toString());
		super.save(entity);
	}

	public void deleteAll() {
		logger.info("MenuService_deleteAll;");
		PageView<Menu> pageView = new PageView<Menu>(-1, -1);
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if (params.size() > 0)
			jpql.append(" and ");
		jpql.append("o.parent is null");

		QueryResult<Menu> qr = this.getScrollData(pageView.getFirstResult(),
				pageView.getMaxresult(), jpql.toString(), params.toArray());

		for (Menu m : qr.getResultlist()) {
			this.delete(m.getUuid());
		}
	}

	@Override
	public void delete(Serializable... entityids) {
		logger.info("MenuService_delete;entityids:" + entityids);
		for (Serializable id : entityids) {
			Menu menu = entityManager.find(Menu.class, id);
			/*
			 * if(menu.getChilds().size()>=1){ for(Iterator<Menu> it =
			 * menu.getChilds().iterator();it.hasNext();){ Menu m = it.next();
			 * this.delete(m.getUuid()); } em.remove(menu); }
			 */
			entityManager.remove(menu);

		}
	}

	public void addMenu(Menu menu, String parentID) {
		logger.info("MenuService_addMenu;menu:" + menu + ";parentID:"
				+ parentID);
		if (parentID != null) {
			menu.setParent(entityManager.find(Menu.class, parentID));
		}
		this.save(menu);

		// this.update(menu);
	}

	/*
	 * @Override public void delete(Serializable... entityids) {
	 * for(Serializable id : entityids){ Menu menu = em.find(Menu.class, id);
	 * 
	 * this.delete(id); //解除与人员的关系 if(menu!=null){
	 * 
	 * }
	 * 
	 * } }
	 */

	public void updateVisible(String uuid, Integer visible) {
		// em.createQuery("update Organization o set o.visible=?1 where o.uuid=?2").setParameter(1,
		// visible).setParameter(2, uuid).executeUpdate();
		logger.info("MenuService_updateVisible;uuid:" + uuid + ";visible:"
				+ visible);
		Menu menu = entityManager.find(Menu.class, uuid);
		if (menu.getChilds().size() >= 1) {
			for (Iterator<Menu> ite = menu.getChilds().iterator(); ite
					.hasNext();) {
				Menu menu1 = ite.next();
				this.updateVisible(menu1.getUuid(), visible);
			}
			menu.setVisible(visible);
		} else {
			menu.setVisible(visible);
		}

	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public PageView<Menu> getMenuTree() {
		logger.info("MenuService_getMenuTree;");
		PageView<Menu> pageView = new PageView<Menu>(-1, -1);
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();

		if (params.size() > 0)
			jpql.append(" and ");
		jpql.append(" o.visible=?" + (params.size() + 1));
		params.add(true);

		if (params.size() > 0)
			jpql.append(" and ");
		jpql.append("o.parent is null");

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("uuid", "desc");

		QueryResult<Menu> qr = this.getScrollData(pageView.getFirstResult(),
				pageView.getMaxresult(), jpql.toString(), params.toArray(),
				orderby);

		pageView.setQueryResult(qr);

		return pageView;

	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public String test() {
		logger.info("MenuService_test;");
		PageView<Menu> pageView = new PageView<Menu>(-1, -1);
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();

		if (params.size() > 0)
			jpql.append(" and ");
		jpql.append(" o.visible=?" + (params.size() + 1));
		params.add(true);

		// if(params.size()>0) jpql.append(" and ");
		// jpql.append("o.parent is null");

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("uuid", "desc");

		QueryResult<Menu> qr = this.getScrollData(pageView.getFirstResult(),
				pageView.getMaxresult(), jpql.toString(), params.toArray(),
				orderby);

		pageView.setQueryResult(qr);

		Gson gson = new Gson();

		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		List<Menu> menus = new ArrayList<Menu>();

		for (Menu menu : pageView.getRecords()) {
			if (menu.getChilds().isEmpty()) {
				menus.add(menu);
			}
			if (menu.getParent() == null) {
				treeNodes
						.add(new TreeNode(menu.getUuid(), "0", menu.getName()));
			} else {
				treeNodes.add(new TreeNode(menu.getUuid(), menu.getParent()
						.getUuid(), menu.getName()));
			}
		}

		/*
		 * treeNodes.add(new
		 * TreeNode("c80c39bf-8718-4b7c-a6ef-6b124a5551a8","0","父节点1 - 展1开"));
		 * treeNodes.add(new TreeNode("496d733d-33cc-42a8-bf41-397658348c6e",
		 * "c80c39bf-8718-4b7c-a6ef-6b124a5551a8","父节点11 - 折1叠"));
		 * treeNodes.add(new TreeNode("5b333d7e-1ffa-438e-a11a-f3606c28ae1c",
		 * "496d733d-33cc-42a8-bf41-397658348c6e","叶子节点111"));
		 */

		String str = gson.toJson(treeNodes);


		/*
		 * StringBuffer message= new StringBuffer(""); message.append("[");
		 * message
		 * .append("{ id:1, pId:0, name:'父节点1 - 展开', open:true}").append(",");
		 * message.append("{ id:11, pId:1, name:'父节点11 - 折叠'}").append(",");
		 * message.append("{ id:111, pId:11, name:'叶子节点111'}");
		 * message.append("]");
		 * 
		 */
		return str;
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public String getTreeNodes(PageView<Menu> pageView) {
		logger.info("MenuService_getTreeNodes;pageView:" + pageView);
		Gson gson = new Gson();
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();

		for (Menu menu : pageView.getRecords()) {

			if (menu.getParent() == null) {
				treeNodes
						.add(new TreeNode(menu.getUuid(), "0", menu.getName()));
			} else {
				treeNodes.add(new TreeNode(menu.getUuid(), menu.getParent()
						.getUuid(), menu.getName()));
			}
		}
		String str = gson.toJson(treeNodes);
		return str;
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Menu> getChildNodes(PageView<Menu> pageView) {
		logger.info("MenuService_getChildNodes;pageView:" + pageView);
		List<Menu> menus = new ArrayList<Menu>();

		for (Menu menu : pageView.getRecords()) {
			if (menu.getChilds().isEmpty()) {
				menus.add(menu);
			}
		}
		return menus;
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Menu> getAllMenus() {// 系统内所有菜单
		logger.info("MenuService_getAllMenus;");
		PageView<Menu> pageView = new PageView<Menu>(-1, -1);
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(1);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("orderNO", "asc");
		QueryResult<Menu> qr = this.getScrollData(pageView.getFirstResult(),
				pageView.getMaxresult(), jpql.toString(), params.toArray(),
				orderby);

		return qr.getResultlist();
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Menu> getAllChildMenus(List<Menu> allMenus) {
		logger.info("MenuService_getAllChildMenus;allMenus:" + allMenus);
		List<Menu> allChildMenus = new ArrayList<Menu>();
		for (Menu m : allMenus) {
			if (m.getChilds().size() == 0) {
				allChildMenus.add(m);
			}
		}
		return allChildMenus;
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Menu> getAllRootMenus(List<Menu> allMenus) {
		logger.info("MenuService_getAllRootMenus;allMenus:" + allMenus);
		List<Menu> allRootMenus = new ArrayList<Menu>();
		for (Menu m : allMenus) {
			if (m.getParent() == null) {
				allRootMenus.add(m);
			}
		}
		return allRootMenus;
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Set<Menu> getMyMenus(Set<SystemPrivilege> allMySPs,
			List<Menu> allChildMenus) {
		logger.info("MenuService_getMyMenus;allMySPs:" + allMySPs
				+ ";allChildMenus:" + allChildMenus);
		Set<Menu> menus = new HashSet<Menu>();

		for (SystemPrivilege sp : allMySPs) {
			for (Menu m : allChildMenus) {
				if (m.getSystemPrivileges().contains(sp)) {// //我的用户权限
					menus.add(m);
				}
			}
		}
		return menus;
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public ArrayList<Menu> getMyOutMenus(List<Menu> allRootMenus,
			Set<Menu> myMenus) {
		logger.info("MenuService_getMyOutMenus;allRootMenus:" + allRootMenus
				+ ";myMenus:" + myMenus);
		ArrayList<Menu> myOutMenus = new ArrayList<Menu>();
		for (Menu menu : allRootMenus) {
			ifShow(menu, myMenus, myOutMenus);
		}
		Collections.sort(myOutMenus, new Comparator<Menu>() {
			public int compare(Menu o1, Menu o2) {
				// 按照升序排列
				if (o1.getOrderNO() > o2.getOrderNO()) {
					return 1;
				}
				if (o1.getOrderNO() == o2.getOrderNO()) {
					return 0;
				}
				return -1;
			}
		});
		return myOutMenus;
	}

	public boolean ifShow(Menu menu, Set<Menu> myMenus,
			ArrayList<Menu> myOutMenus) {// menu 顶端节点菜单 myMenus 我的菜单 myOutMenus
											// 样式
		logger.info("MenuService_ifShow;menu:" + menu + ";myMenus:" + myMenus
				+ ";myOutMenus:" + myOutMenus);
		boolean flag = false;
		if (menu.getChilds().size() > 0) {
			for (Menu child : menu.getChilds()) {
				flag = ifShow(child, myMenus, myOutMenus) || flag;
			}
		} else {
			if (myMenus.contains(menu)) {
				flag = true;
			}
		}
		if (flag) {
			myOutMenus.add(menu);
		}
		return flag;
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public ArrayList<Menu> getMyOutRootMenus(List<Menu> allRootMenus,
			ArrayList<Menu> myOutMenus) {
		logger.info("MenuService_getMyOutRootMenus;allRootMenus:"
				+ allRootMenus + ";myOutMenus:" + myOutMenus);
		ArrayList<Menu> myOutRootMenus = new ArrayList<Menu>();
		for (Menu m : allRootMenus) {
			if (myOutMenus.contains(m)) {
				myOutRootMenus.add(m);
			}
		}
		return myOutRootMenus;
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public String getMyOutMenuStyle(ArrayList<Menu> myOutRootMenus,
			ArrayList<Menu> myOutMenus) {
		logger.info("MenuService_getMyOutMenuStyle;myOutRootMenus:"
				+ myOutRootMenus + ";myOutMenus:" + myOutMenus);
		StringBuffer myOutMenuStyle = new StringBuffer("");

		for (Menu m : myOutRootMenus) {
			myOutMenuStyle.append("<div class='accordionHeader'>");
			myOutMenuStyle.append("<h2><span>Folder</span>");
			myOutMenuStyle.append(m.getName());
			myOutMenuStyle.append("</h2>");
			myOutMenuStyle.append("</div>");
			myOutMenuStyle.append("<div class='accordionContent'>");
			myOutMenuStyle.append("<ul class='tree treeFolder'>");
			myOutMenuStyle.append(menuTree(m, myOutMenus));
			// myOutMenuStyle.append("<ul class='tree treeFolder collapse'>");
			// myOutMenuStyle.append(menuTree(m, myOutMenus, rcSet));
			myOutMenuStyle.append("</ul>");
			myOutMenuStyle.append("</div>");
		}

		return myOutMenuStyle.toString();
	}

	private String menuTree(Menu root, ArrayList<Menu> myOutMenus) {
		logger.info("MenuService_menuTree;root:" + root + ";myOutMenus:"
				+ myOutMenus);
		String treeStr = "";

		for (Menu menu : myOutMenus) {
			if (menu.getParent() != null && menu.getParent().equals(root)) {
				treeStr += readTree(menu, myOutMenus);
			}
		}

		return treeStr;
	}

	private String readTree(Menu parent, ArrayList<Menu> myOutMenus) {
		StringBuilder treeStr = new StringBuilder();
		logger.info("MenuService_readTree;parent:" + parent + ";myOutMenus:"
				+ myOutMenus);
		/*
		 * if(parent.getName().endsWith("@InsertToMenu") &&
		 * "reportCategoryTree".equals(parent.getUrl())) {
		 * //若是报表查看菜单，则插入报表分类树（报表查看菜单的标志
		 * ：菜单名以“@InsertToMenu”结尾，且url中的值为“reportCategoryTree”）
		 * treeStr.append("<li><a>"+ parent.getName().substring(0,
		 * parent.getName().length()-13) +"</a>\n<ul>\n");
		 * //treeStr.append(reportCategoryService
		 * .dwzTreeMenuInitialData(rcSet)); treeStr.append("</ul>\n</li>");
		 * //读取完报表树后直接返回 return treeStr.toString(); }
		 */
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		if (parent.getChilds().size() <= 0) {
			treeStr.append("<li><a href='");
			treeStr.append(request.getContextPath()+ parent.getUrl());
			treeStr.append("' target='");
			treeStr.append(parent.getTarget());
			treeStr.append("' rel='");
			treeStr.append(parent.getRel());
			treeStr.append("' title='");
			treeStr.append(parent.getName());
			treeStr.append("'>" + parent.getName() + "</a></li>\n");
		} else {
			treeStr.append("<li><a>" + parent.getName() + "</a>\n<ul>\n");
			for (Menu menu : myOutMenus) {
				if (menu.getParent() != null && menu.getParent().equals(parent)) {
					treeStr.append(readTree(menu, myOutMenus));
				}
			}
			treeStr.append("</ul>\n</li>");
		}
		return treeStr.toString();
	}

	/*
	 * @Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	 * public String getMyOutMenuStyle(User entity){
	 * 
	 * //我的用户权限 Set<SystemPrivilege> mySps = entity.getSystemPrivileges();
	 * //我的角色权限 Set<SystemPrivilege> roleSPs =
	 * userService.getRoleSPs(entity.getGroups()); //我的权限并集 Set<SystemPrivilege>
	 * allMySPs = userService.getAllSPs(mySps,roleSPs); //系统内所有菜单 List<Menu>
	 * allMenus = this.getAllMenus(); //系统内所有无子节点菜单 List<Menu> allChildMenus =
	 * this.getAllChildMenus(allMenus); //系统内所有顶端节点菜单 List<Menu> allRootMenus =
	 * this.getAllRootMenus(allMenus); //我的所有菜单 Set<Menu> myMenus =
	 * this.getMyMenus(allMySPs, allChildMenus); //我的顶端菜单之下所有菜单输出样式
	 * ArrayList<Menu> myOutMenus = this.getMyOutMenus(allRootMenus, myMenus);
	 * //我的顶端菜单 ArrayList<Menu> myOutRootMenus =
	 * this.getMyOutRootMenus(allRootMenus, myOutMenus);
	 * Collections.sort(myOutMenus, new Comparator<Menu>(){
	 * 
	 * 
	 * int compare(Menu o1, Menu o2) 返回一个基本类型的整型， 返回负数表示：o1 小于o2， 返回0
	 * 表示：o1和o2相等， 返回正数表示：o1大于o2。
	 * 
	 * public int compare(Menu o1, Menu o2) {
	 * 
	 * //按照升序排列 if(o1.getOrderNO() > o2.getOrderNO()){ return 1; }
	 * if(o1.getOrderNO() == o2.getOrderNO()){ return 0; } return -1; } });
	 * //按照样式输出我的菜单 String myOutMenuStyle =
	 * this.getMyOutMenuStyle(myOutRootMenus, myOutMenus); return
	 * myOutMenuStyle; }
	 */
	/*
	 * //我的用户权限 Set<SystemPrivilege> mySps = entity.getSystemPrivileges();
	 * //我的角色权限 Set<SystemPrivilege> roleSPs =
	 * userService.getRoleSPs(entity.getGroups()); //我的权限并集 Set<SystemPrivilege>
	 * allMySPs = userService.getAllSPs(mySps,roleSPs); //系统内所有菜单 List<Menu>
	 * allMenus = menuService.getAllMenus(); //系统内所有无子节点菜单 List<Menu>
	 * allChildMenus = menuService.getAllChildMenus(allMenus); //系统内所有顶端节点菜单
	 * List<Menu> allRootMenus = menuService.getAllRootMenus(allMenus); //我的所有菜单
	 * Set<Menu> myMenus = menuService.getMyMenus(allMySPs, allChildMenus);
	 * //我的顶端菜单之下所有菜单输出样式 ArrayList<Menu> myOutMenus =
	 * menuService.getMyOutMenus(allRootMenus, myMenus); //我的顶端菜单
	 * ArrayList<Menu> myOutRootMenus =
	 * menuService.getMyOutRootMenus(allRootMenus, myOutMenus); //按照样式输出我的菜单
	 * String myOutMenuStyle = menuService.getMyOutMenuStyle(myOutRootMenus,
	 * myOutMenus);
	 */

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public String getMyOutMenuStyle(User entity) {
		logger.info("MenuService_getMyOutMenuStyle;entity:" + entity);
		// 我的用户权限
		Set<SystemPrivilege> mySps = entity.getSystemPrivileges();
		// 我的角色权限
		Set<SystemPrivilege> roleSPs = userService.getRoleSPs(entity
				.getGroups());// 我的角色权限
		// 我的权限并集
		Set<SystemPrivilege> allMySPs = userService.getAllSPs(mySps, roleSPs);
		// 系统内所有菜单
		List<Menu> allMenus = this.getAllMenus();
		// 系统内所有无子节点菜单
		List<Menu> allChildMenus = this.getAllChildMenus(allMenus);
		// 系统内所有顶端节点菜单
		List<Menu> allRootMenus = this.getAllRootMenus(allMenus);
		// 我的所有菜单
		Set<Menu> myMenus = this.getMyMenus(allMySPs, allChildMenus);
		// 我的顶端菜单之下所有菜单输出样式
		ArrayList<Menu> myOutMenus = this.getMyOutMenus(allRootMenus, myMenus);
		// 我的顶端菜单
		ArrayList<Menu> myOutRootMenus = this.getMyOutRootMenus(allRootMenus,
				myOutMenus);

		Collections.sort(myOutMenus, new Comparator<Menu>() {

			/*
			 * int compare(Menu o1, Menu o2) 返回一个基本类型的整型， 返回负数表示：o1 小于o2， 返回0
			 * 表示：o1和o2相等， 返回正数表示：o1大于o2。
			 */
			public int compare(Menu o1, Menu o2) {

				// 按照升序排列
				if (o1.getOrderNO() > o2.getOrderNO()) {
					return 1;
				}
				if (o1.getOrderNO() == o2.getOrderNO()) {
					return 0;
				}
				return -1;
			}
		});
		// 按照样式输出我的菜单
		String myOutMenuStyle = this.getMyOutMenuStyle(myOutRootMenus,
				myOutMenus);
		return myOutMenuStyle;
	}

	@SuppressWarnings("rawtypes")
	public List executeHql(String hql) {
		logger.info("MenuService_executeHql;hql:" + hql);
		List list = entityManager.createQuery(hql).getResultList();
		return list;
	}

	public Set<SystemPrivilege> getAllOwnSPs(Menu menu) {
		logger.info("MenuService_getAllOwnSPs;menu:" + menu);
		String jpql = " o.uuid = ?1 ";
		Object[] params = { menu.getUuid() };
		List<Menu> menuList = this.getScrollData(-1, -1, jpql, params)
				.getResultlist();
		if (menuList.size() > 0) {
			Set<SystemPrivilege> sps = menuList.get(0).getSystemPrivileges();
			return sps;
		}
		return null;
	}

	public Set<Menu> getAllOwnChildMenus(Menu menu) {
		logger.info("MenuService_getAllOwnChildMenus;menu:" + menu);
		String jpql = " o.uuid = ?1 ";
		Object[] params = { menu.getUuid() };
		List<Menu> menuList = this.getScrollData(-1, -1, jpql, params)
				.getResultlist();
		if (menuList.size() > 0) {
			Set<Menu> children = menuList.get(0).getChilds();
			return children;
		}
		return null;
	}

	public ArrayList<StandardTreeNode> allMenusStnRootNodesList() {
		logger.info("MenuService_allMenusStnRootNodesList;");
		ArrayList<StandardTreeNode> rootList = new ArrayList<StandardTreeNode>();

		List<Menu> rootMenus = allChildMenusByOrderBy(null);
		for (Menu m : rootMenus) {
			rootList.add(menuRecursive(m, null));
		}

		return rootList;
	}

	public Set<SystemPrivilege> allSystemPrivilegesOfSomeMenu(Menu menu) {
		logger.info("MenuService_allSystemPrivilegesOfSomeMenu;menu:" + menu);
		Menu entity = find(menu.getUuid());
		Set<SystemPrivilege> spSet = entity.getSystemPrivileges();
		for (SystemPrivilege sp : spSet) {
			// 取一下属性，否则在懒加载的情况下默认不会将数据加载进来
			sp.getId().getModel();
			sp.getId().getPrivilegeValue();
			sp.getName();
		}
		return spSet;
	}

	/**
	 * 中序递归遍历所有菜单，并将菜单对象转换成StandardTreeNode对象。
	 * 其中Menu的uuid、name属性对应StandardTreeNode的id、name属性。
	 * Menu的orderNO、target、rel、url属性对应StandardTreeNode的datas， 为null的值设为长度为0的字符串
	 * 的下标为0、1、2、3的值 parent和children属性分别对应相应对象的引用
	 * 
	 * @param m
	 *            菜单
	 * @param parent
	 *            StandardTreeNode父节点
	 * @return 根据传入的菜单生成的对应的StandardTreeNode对象
	 */
	private StandardTreeNode menuRecursive(Menu m, StandardTreeNode parent) {
		logger.info("MenuService_menuRecursive;m:" + m + ";parent:" + parent);
		StandardTreeNode tn = new StandardTreeNode();
		tn.setId(m.getUuid());
		tn.setName(m.getName() == null ? "" : m.getName());
		tn.setParent(parent);
		ArrayList<Object> attrList = new ArrayList<Object>();
		attrList.add(m.getOrderNO());
		attrList.add(m.getTarget() == null ? "" : m.getTarget());
		attrList.add(m.getRel() == null ? "" : m.getRel());
		attrList.add(m.getUrl() == null ? "" : m.getUrl());
		tn.setDatas(attrList);
		for (Menu child : allChildMenusByOrderBy(m)) {
			tn.getChildren().add(menuRecursive(child, tn));
		}
		return tn;
	}

	/**
	 * 根据传入的父菜单，找到父菜单下所有的子菜单，找出的结果按Menu的orderNO属性升序排序 parent为null则会找所有的菜单根节点
	 * 
	 * @param parent
	 *            父菜单
	 * @return 菜单List
	 */
	private List<Menu> allChildMenusByOrderBy(Menu parent) {
		logger.info("MenuService_allChildMenusByOrderBy;parent:" + parent);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("orderNO", "asc");

		if (parent == null) {
			return getScrollData(-1, -1, " o.parent is null", new Object[] {},
					orderby).getResultlist();
		} else {
			return getScrollData(-1, -1, " o.parent=?1",
					new Object[] { parent }, orderby).getResultlist();
		}

	}

	public List<Menu> allChildMenus(Menu parent) {
		logger.info("MenuService_allChildMenus;parent:" + parent);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("orderNO", "asc");

		if (parent == null) {
			return getScrollData(-1, -1, " o.parent is null", new Object[] {},
					orderby).getResultlist();
		} else {
			return getScrollData(-1, -1, " o.parent=?1",
					new Object[] { parent }, orderby).getResultlist();
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Set<SystemPrivilege> allSystemPrivilegesDistributeToMenus() {
		logger.info("MenuService_allSystemPrivilegesDistributeToMenus;");
		Set<SystemPrivilege> menuSPList = new HashSet<SystemPrivilege>();

		List<Menu> allMenus = getScrollData().getResultlist();
		for (Menu m : allMenus) {
			menuSPList.addAll(m.getSystemPrivileges());
		}

		return menuSPList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Set<SimpleTreeNode> allChildrenTreeNode(Menu menu) {
		logger.info("MenuService_allChildrenTreeNode;menu:" + menu);
		Set<SimpleTreeNode> children = new HashSet<SimpleTreeNode>();
		for (Menu childrenMenu : menu.getChilds()) {
			SimpleTreeNode childrenTreeNode = new SimpleTreeNode();
			childrenTreeNode.setId(childrenMenu.getUuid());
			childrenTreeNode.setText(childrenMenu.getName());
			childrenTreeNode.setExpanded(true);
			childrenTreeNode.setLeaf(false);
			if (childrenMenu.getChilds().size() == 0) {
				childrenTreeNode.setChildren(this
						.allSystemPrivilegesTreeNodeOfSomeMenu(childrenMenu));
			} else {

				childrenTreeNode.setChildren(this
						.allChildrenTreeNode(childrenMenu));
			}
			children.add(childrenTreeNode);
		}
		return children;
	}

	public Set<StandardTreeNodeVo> allChildrenTreeNode(Menu menu, User user) {
		logger.info("MenuService_allChildrenTreeNode;menu:" + menu + ";user:"
				+ user);
		Set<StandardTreeNodeVo> children = new HashSet<StandardTreeNodeVo>();
		for (Menu childrenMenu : menu.getChilds()) {
			StandardTreeNodeVo childrenTreeNode = new StandardTreeNodeVo();
			childrenTreeNode.setId(childrenMenu.getUuid());
			childrenTreeNode.setText(childrenMenu.getName());
			childrenTreeNode.setExpanded(true);
			childrenTreeNode.setLeaf(false);
			if (childrenMenu.getChilds().size() == 0) {
				childrenTreeNode.setChildren(this
						.allSystemPrivilegesTreeNodeOfSomeMenu(childrenMenu,
								user));
			} else {
				childrenTreeNode.setChildren(this.allChildrenTreeNode(
						childrenMenu, user));
			}
			children.add(childrenTreeNode);
		}
		return children;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Set<SimpleTreeNode> allChildrenTreeNode(Menu menu,
			PrivilegeGroup privilegeGroup) {
		logger.info("MenuService_allChildrenTreeNode;menu:" + menu
				+ ";privilegeGroup:" + privilegeGroup);
		Set<SimpleTreeNode> children = new HashSet<SimpleTreeNode>();
		for (Menu childrenMenu : menu.getChilds()) {
			SimpleTreeNode childrenTreeNode = new SimpleTreeNode();
			childrenTreeNode.setId(childrenMenu.getUuid());
			childrenTreeNode.setText(childrenMenu.getName());
			childrenTreeNode.setExpanded(true);
			childrenTreeNode.setLeaf(false);
			if (childrenMenu.getChilds().size() == 0) {
				childrenTreeNode.setChildren(this
						.allSystemPrivilegesTreeNodeOfSomeMenu(childrenMenu,
								privilegeGroup));
			} else {
				childrenTreeNode.setChildren(this.allChildrenTreeNode(
						childrenMenu, privilegeGroup));
			}
			children.add(childrenTreeNode);
		}
		return children;
	}

	// 获得所有的菜单权限
	public Set<StandardTreeNodeVo> allSystemPrivilegesTreeNodeOfSomeMenu(
			Menu childrenMenu, User user) {
		logger.info("MenuService_allSystemPrivilegesTreeNodeOfSomeMenu;childrenMenu:"
				+ childrenMenu + ";user:" + user);
		if (childrenMenu.getChilds().isEmpty()) {
			Set<StandardTreeNodeVo> childrenSystemPrivileges = new HashSet<StandardTreeNodeVo>();
			for (SystemPrivilege sps : this
					.allSystemPrivilegesOfSomeMenu(childrenMenu)) {
				StandardTreeNodeVo systemPrivilegesTreeNode = new StandardTreeNodeVo();
				systemPrivilegesTreeNode.setId(sps.getId().getModel() + ","
						+ sps.getId().getPrivilegeValue());
				systemPrivilegesTreeNode.setText(sps.getName());
				systemPrivilegesTreeNode.setExpanded(true);
				systemPrivilegesTreeNode.setLeaf(true);
				for (SystemPrivilege usp : user.getSystemPrivileges()) {
					if (sps.getId().equals(usp.getId())) {
						systemPrivilegesTreeNode.setChecked(true);
					}
				}
				childrenSystemPrivileges.add(systemPrivilegesTreeNode);
			}
			return childrenSystemPrivileges;
		}
		return null;
	}

	// 获得所有的菜单权限
	public Set<StandardTreeNodeVo> allSystemPrivilegesTreeNodeOfSomeMenu(
			Menu childrenMenu) {
		logger.info("MenuService_allSystemPrivilegesTreeNodeOfSomeMenu;childrenMenu:"
				+ childrenMenu);
		if (childrenMenu.getChilds().isEmpty()) {
			Set<StandardTreeNodeVo> childrenSystemPrivileges = new HashSet<StandardTreeNodeVo>();
			for (SystemPrivilege sps : this
					.allSystemPrivilegesOfSomeMenu(childrenMenu)) {
				StandardTreeNodeVo systemPrivilegesTreeNode = new StandardTreeNodeVo();
				systemPrivilegesTreeNode.setId(sps.getId().getModel() + ","
						+ sps.getId().getPrivilegeValue());
				systemPrivilegesTreeNode.setText(sps.getName());
				systemPrivilegesTreeNode.setExpanded(true);
				systemPrivilegesTreeNode.setLeaf(true);
				systemPrivilegesTreeNode.setChecked(false);
				childrenSystemPrivileges.add(systemPrivilegesTreeNode);
			}
			return childrenSystemPrivileges;
		}
		return null;
	}

	// 获得所有的菜单权限
	public Set<StandardTreeNodeVo> allSystemPrivilegesTreeNodeOfSomeMenu(
			Menu childrenMenu, PrivilegeGroup privilegeGroup) {
		logger.info("MenuService_allSystemPrivilegesTreeNodeOfSomeMenu;childrenMenu:"
				+ childrenMenu + ";privilegeGroup:" + privilegeGroup);
		if (childrenMenu.getChilds().isEmpty()) {
			Set<StandardTreeNodeVo> childrenSystemPrivileges = new HashSet<StandardTreeNodeVo>();
			for (SystemPrivilege sps : this
					.allSystemPrivilegesOfSomeMenu(childrenMenu)) {
				StandardTreeNodeVo systemPrivilegesTreeNode = new StandardTreeNodeVo();
				systemPrivilegesTreeNode.setId(sps.getId().getModel() + ","
						+ sps.getId().getPrivilegeValue());
				systemPrivilegesTreeNode.setText(sps.getName());
				systemPrivilegesTreeNode.setExpanded(true);
				systemPrivilegesTreeNode.setLeaf(true);
				for (SystemPrivilege usp : privilegeGroup.getSystemPrivileges()) {
					if (sps.getId().equals(usp.getId())) {
						systemPrivilegesTreeNode.setChecked(true);
					}
				}
				childrenSystemPrivileges.add(systemPrivilegesTreeNode);
			}
			return childrenSystemPrivileges;
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public Set<SimpleTreeNode> allMenuTreeNode(User entity) {
		logger.info("MenuService_allMenuTreeNode;entity:" + entity);
		// 我的用户权限
		Set<SystemPrivilege> mySps = entity.getSystemPrivileges();
		// 我的角色权限
		Set<SystemPrivilege> roleSPs = userService.getRoleSPs(entity
				.getGroups());
		// 我的权限并集
		Set<SystemPrivilege> allMySPs = userService.getAllSPs(mySps, roleSPs);
		// 系统内所有菜单
		List<Menu> allMenus = this.getAllMenus();
		// 系统内所有无子节点菜单
		List<Menu> allChildMenus = this.getAllChildMenus(allMenus);
		// 系统内所有顶端节点菜单
		List<Menu> allRootMenus = this.getAllRootMenus(allMenus);
		// 我的所有菜单
		Set<Menu> myMenus = this.getMyMenus(allMySPs, allChildMenus);
		// 我的顶端菜单之下所有菜单输出样式
		ArrayList<Menu> myOutMenus = this.getMyOutMenus(allRootMenus, myMenus);
		// 我的顶端菜单
		ArrayList<Menu> myOutRootMenus = this.getMyOutRootMenus(allRootMenus,
				myOutMenus);
		Set<SimpleTreeNode> treeNodes = new HashSet<SimpleTreeNode>();
		for (Menu menu : myMenus) {
			SimpleTreeNode treeNode = new SimpleTreeNode();
			if (menu.getParent() == null) {
				treeNode.setId(menu.getUuid());
				treeNode.setText(menu.getName());
				if (menu.getChilds() != null) {
					treeNode.setLeaf(false);
					treeNode.setExpanded(true);
					treeNode.setChildren(this.allMenuChildrenTreeNode(menu,
							myOutMenus));
				} else {
					treeNode.setLeaf(true);
					treeNode.setExpanded(false);
				}
				treeNodes.add(treeNode);
			}
		}
		return treeNodes;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Set<SimpleTreeNode> allMenuChildrenTreeNode(Menu menu,
			ArrayList<Menu> myOutMenus) {
		logger.info("MenuService_allMenuChildrenTreeNode;menu:" + menu
				+ ";myOutMenus:" + myOutMenus);
		Set<SimpleTreeNode> children = new HashSet<SimpleTreeNode>();
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		String path = request.getContextPath();
		for (Menu childrenMenu : menu.getChilds()) {
			if (myOutMenus.contains(childrenMenu)) {
				SimpleTreeNode childrenTreeNode = new SimpleTreeNode();
				childrenTreeNode.setId(childrenMenu.getUuid());
				childrenTreeNode.setText(childrenMenu.getName());
				if (childrenMenu.getImgUrl() != null) {
					childrenTreeNode.setIcon(path + childrenMenu.getImgUrl());
				}
				if (childrenMenu.getChilds().size() == 0) {
					childrenTreeNode.setExpanded(false);
					childrenTreeNode.setLeaf(true);
				} else {
					childrenTreeNode.setExpanded(false);
					childrenTreeNode.setLeaf(false);
					childrenTreeNode.setChildren(this.allMenuChildrenTreeNode(
							childrenMenu, myOutMenus));
				}
				children.add(childrenTreeNode);
			}
		}
		return children;
	}

	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Menu getMenuOfUuid(String uuid) {
		logger.info("MenuService_getMenuOfUuid;uuid:" + uuid);
		Menu menu = new Menu();
		Query query = entityManager.createQuery(
				"select o from Menu o where o.uuid=?1").setParameter(1, uuid);
		List lists = query.getResultList();
		if (lists != null && lists.size() > 0) {
			menu = (Menu) lists.get(0);
		}
		return menu;
	}

	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Menu> getAllChildMenus() {
		logger.info("MenuService_getAllChildMenus;");
		List<Menu> menuLists = new ArrayList<Menu>();
		/*
		 * SELECT * FROM menu t WHERE t.uuid NOT IN (SELECT parentID FROM menu
		 * WHERE parentID IS NOT NULL)
		 */
		Query query = entityManager
				.createNativeQuery("select uuid,name,sn,orderNO,url,target,rel,imgUrl from tp_Menu o where o.visible=1 and o.uuid not in(select parentID from tp_Menu where parentID is not null)");
		List lists = query.getResultList();
		if (lists != null && lists.size() > 0) {
			for (int i = 0; i < lists.size(); i++) {
				Object[] cells = (Object[]) lists.get(i);
				Menu menu = new Menu();
				menu.setUuid(cells[0].toString());
				menu.setName(cells[1].toString());
				if (cells[2] != null)
					menu.setSn(cells[2].toString());
				if (cells[3] != null)
					menu.setOrderNO(Integer.parseInt(cells[3].toString()));
				if (cells[4] != null)
					menu.setUrl(cells[4].toString());
				if (cells[5] != null)
					menu.setTarget(cells[5].toString());
				if (cells[6] != null)
					menu.setRel(cells[6].toString());
				if (cells[7] != null)
					menu.setImgUrl(cells[7].toString());
				menuLists.add(menu);
			}
		}
		return menuLists;
	}

	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Set<SystemPrivilege> getMenuSps(String menuID) {
		logger.info("MenuService_getMenuSps;menuID:" + menuID);
		Set<SystemPrivilege> sps = new HashSet<SystemPrivilege>();
		Query query = entityManager
				.createNativeQuery(
						"select menuID,model,privilegeValue from  TP_MENU_PRIVILEGE o where o.menuID=?1")
				.setParameter(1, menuID);
		List lists = query.getResultList();
		if (lists != null && lists.size() > 0) {
			for (int i = 0; i < lists.size(); i++) {
				Object[] cells = (Object[]) lists.get(i);
				SystemPrivilege sp = systemPrivilegeService
						.find(new SystemPrivilegePK(cells[1].toString(),
								cells[2].toString()));
				sps.add(sp);
			}
		}
		return sps;
	}

	public boolean targetAndRelExist(Map<String, Object> params) {
		String uuid=(String) params.get("uuid");
		String target=(String) params.get("target");
		String rel=(String) params.get("rel");
		long count =0;
		if(target!=null){
			 count = (Long) entityManager.createQuery(
					"select count(o) from Menu o where o.target =?1 and o.uuid!=?2")
										.setParameter(1, target)
										.setParameter(2, uuid)
						.getSingleResult();
		}
		if(rel!=null){
			 count = (Long) entityManager.createQuery(
					"select count(o) from Menu o where o.rel =?1 and o.uuid!=?2")
										.setParameter(1, rel)
										.setParameter(2, uuid)
						.getSingleResult();
		}
		return count > 0;
	}

}
