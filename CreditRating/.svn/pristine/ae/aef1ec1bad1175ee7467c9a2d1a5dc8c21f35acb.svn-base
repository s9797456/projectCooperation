package com.credit.service.privilege.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.bean.pagelist.PageView;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.privilege.Department;
import com.credit.bean.privilege.PrivilegeGroup;
import com.credit.bean.member.User;
import com.credit.dao.impl.DaoSupport;
import com.credit.service.privilege.DepartmentService;
import com.credit.util.tree.OrgTreeHelper;
import com.credit.util.tree.TreeNode;
import com.credit.util.tree.SimpleTreeNode;

@Service
@Transactional
public class DepartmentServiceBean<T> extends DaoSupport<Department>
		implements DepartmentService {
	/**
	 * 
	 */
	private static final Logger logger = Logger
			.getLogger(DepartmentServiceBean.class);

	@Override
	public void save(Department entity) {
		logger.info("DepartmentService_save;entity:" + entity);
		((Department) entity).setUuid(UUID.randomUUID().toString());
		super.save(entity);
	}

	/*
	 * @Override public void delete(Serializable... entityids) {
	 * for(Serializable id : entityids){ Department department =
	 * em.find(Department.class, id);
	 * if(department!=null){ for(User user : department.getUsers()){
	 * user.getDepartments().remove(department); } //解除与文档类型的关系
	 * em.remove(department); }
	 * 
	 * } }
	 */
	public void addDep(Department dep, String parentID) {
		logger.info("DepartmentService_addDep;dep:" + dep + ";parentID:"
				+ parentID);
		if (parentID != null) {
			dep.setParent(entityManager.find(Department.class, parentID));
		}
		this.save(dep);

		// this.update(menu);
	}

	// 删除机构以及其下所有子机构
	@Override
	public void delete(Serializable... entityids) {
		logger.info("DepartmentService_delete;entityids:" + entityids);
		for (Serializable id : entityids) {
			Department department = entityManager.find(Department.class,
					id);
			if (department.getChilds().size() >= 1) {
				for (@SuppressWarnings("rawtypes")
				Iterator ite = department.getChilds().iterator(); ite
						.hasNext();) {
					Department dep = (Department) ite.next();
					this.delete(dep.getUuid());

				}
				for (PrivilegeGroup pg : department.getPrivilegeGroups()) {
					pg.getDepartments().remove(department);
				}
				for (User user : department.getUsers()) {
					// user.setDepartment(null);
					user.getDepartments().remove(department);
				}
				entityManager.remove(department);
			} else {
				for (PrivilegeGroup pg : department.getPrivilegeGroups()) {
					pg.getDepartments().remove(department);
				}
				for (User user : department.getUsers()) {
					user.getDepartments().remove(department);
				}
				entityManager.remove(department);

			}
		}
	}

	public void deleteAll() {
		logger.info("DepartmentService_deleteAll;");
		PageView<Department> pageView = new PageView<Department>(-1, -1);
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if (params.size() > 0)
			jpql.append(" and ");
		jpql.append("o.parent is null");

		QueryResult<Department> qr = this.getScrollData(
				pageView.getFirstResult(), pageView.getMaxresult(),
				jpql.toString(), params.toArray());

		for (Department o : qr.getResultlist()) {
			this.delete(o.getUuid());
		}
	}

	public List<User> users = new ArrayList<User>();

	public void cleanUsers() {
		this.users.clear();
	}

	public List<User> getDepOfUser(Serializable... entityids) {
		logger.info("DepartmentService_getDepOfUser;entityids:" + entityids);
		for (Serializable id : entityids) {
			Department department = entityManager.find(Department.class,
					id);

			/*
			 * 只找出本机构所拥有的用户，不再递归找出其子机构拥有的用户
			 * if(!department.getChilds().isEmpty()){
			 * for(Iterator<Department> ite =
			 * department.getChilds().iterator();ite.hasNext();){ Department
			 * dep = ite.next(); this.getDepOfUser(dep.getUuid()); }
			 * 
			 * if(!department.getUsers().isEmpty()){ for(User user:
			 * department.getUsers()){ users.add(user); } } }else{
			 * if(!department.getUsers().isEmpty()){ for(User user:
			 * department.getUsers()){ users.add(user); } } }
			 */

			if (!department.getUsers().isEmpty()) {
				for (User user : department.getUsers()) {
					users.add(user);
				}
			}

		}
		return users;
	}

	public PageView<Department> getDepTree() {
		logger.info("DepartmentService_getDepTree;");
		PageView<Department> pageView = new PageView<Department>(-1, -1);
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

		QueryResult<Department> qr = this.getScrollData(
				pageView.getFirstResult(), pageView.getMaxresult(),
				jpql.toString(), params.toArray(), orderby);

		pageView.setQueryResult(qr);

		return pageView;

	}

	public void updateVisible(String uuid, Integer visible) {
		// entityManager.createQuery("update Department o set o.visible=?1 where o.uuid=?2").setParameter(1,
		// visible).setParameter(2, uuid).executeUpdate();
		logger.info("DepartmentService_updateVisible;uuid:" + uuid
				+ ";visible:" + visible);
		Department department = entityManager
				.find(Department.class, uuid);
		if (department.getChilds().size() >= 1) {
			for (Iterator<Department> ite = department.getChilds()
					.iterator(); ite.hasNext();) {
				Department dep = ite.next();
				this.updateVisible(dep.getUuid(), visible);
			}
			department.setVisible(visible);
		} else {
			department.setVisible(visible);
		}

	}

	public boolean exist(String field, String value) {
		logger.info("DepartmentService_exist;field:" + field + ";value:"
				+ value);
		long count = (Long) entityManager
				.createQuery(
						"select count(o) from Department o where o." + field
								+ "=?1").setParameter(1, value)
				.getSingleResult();
		return count > 0;
	}

	public boolean exist(String field, String value, String value2) {
		logger.info("DepartmentService_exist;field:" + field + ";value:"
				+ value + ";value2:" + value2);
		long count = (Long) entityManager
				.createQuery(
						"select count(o) from Department o where o." + field
								+ "=?1 and o.uuid!=?2").setParameter(1, value)
				.setParameter(2, value2).getSingleResult();
		return count > 0;
	}

	@SuppressWarnings("rawtypes")
	public List executeHql(String hql) {
		logger.info("DepartmentService_executeHql;hql:" + hql);
		List list = entityManager.createQuery(hql).getResultList();
		return list;
	}

	public Department searchBySn(String sn) {
		logger.info("DepartmentService_searchBySn;sn:" + sn);
		List<Department> depList = getScrollData(-1, -1, " o.sn=?1 ",
				new Object[] { sn }).getResultlist();
		if (depList.size() != 1)
			return null;
		return depList.get(0);
	}

	public ArrayList<TreeNode> getAllSeeDepsTnList(Department userDep) {
		logger.info("DepartmentService_getAllSeeDepsTnList;userDep:"
				+ userDep);
		ArrayList<TreeNode> depTnList = new ArrayList<TreeNode>();
		TreeNode userDepTn = departmentRecursive(userDep, depTnList);
		userDepTn.setParent(null);
		depTnList.add(userDepTn);
		return depTnList;
	}

	public String getAllDepZTreeJson() {
		logger.info("DepartmentService_getAllDepZTreeJson;");
		ArrayList<TreeNode> rootTnList = convertAllDepsToTreeNodes();
		StringBuilder sb = new StringBuilder();
		for (TreeNode root : rootTnList) {
			OrgTreeHelper.tnRecursive(root, sb);
		}
		int sbLen = sb.length();
		// 若有节点需要被显示的话则删掉最后一个“,”
		if (sbLen > 0)
			sb.deleteCharAt(sbLen - 1);
		sb.insert(0, "[");
		sb.append("]");
		return sb.toString();
	}

	public ArrayList<TreeNode> convertAllDepsToTreeNodesPublic() {
		logger.info("DepartmentService_convertAllDepsToTreeNodesPublic;");
		return this.convertAllDepsToTreeNodes();
	}

	public Set<PrivilegeGroup> allRolesOwnSomeDepartment(Department dep) {
		logger.info("DepartmentService_allRolesOwnSomeDepartment;dep:"
				+ dep);
		Department entity = find(dep.getUuid());
		Set<PrivilegeGroup> pgSet = entity.getPrivilegeGroups();
		for (PrivilegeGroup pg : pgSet) {
			// 取一下数据，否则在懒加载模式下会报异常，取不到数据
			pg.getName();
		}
		return pgSet;
	}

	/**
	 * 用递归遍历所有department，并将每个dep对象转换成TreeNode对象
	 * 
	 * @return
	 */
	private ArrayList<TreeNode> convertAllDepsToTreeNodes() {
		logger.info("DepartmentService_convertAllDepsToTreeNodes;");
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("sn", "asc");
		ArrayList<Department> rootDepList = (ArrayList<Department>) this
				.getScrollData(-1, -1, "o.parent is null", new Object[] {},
						orderby).getResultlist();
		ArrayList<TreeNode> rootTnList = new ArrayList<TreeNode>();
		for (Department dep : rootDepList) {
			rootTnList.add(departmentRecursive(dep));
		}
		return rootTnList;
	}

	private TreeNode departmentRecursive(Department dep) {
		logger.info("DepartmentService_departmentRecursive;dep:" + dep);
		if (dep == null)
			return null;

		TreeNode tn = new TreeNode();
		tn.setId(dep.getUuid());
		tn.setName(dep.getName());
		Department pDep = dep.getParent();
		tn.setParent(pDep == null ? null : pDep.getUuid());
		tn.setData(dep.getSn());

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("sn", "asc");
		ArrayList<Department> depList = (ArrayList<Department>) this
				.getScrollData(-1, -1, "o.parent=?1", new Object[] { dep },
						orderby).getResultlist();
		for (Department child : depList) {
			tn.getChilds().add(departmentRecursive(child));
		}
		return tn;
	}

	private boolean tnRecursive(TreeNode tn, ArrayList<TreeNode> pTn,
			ArrayList<String> privilegeSnList) {
		logger.info("DepartmentService_tnRecursive;tn:" + tn + ";pTn:" + pTn
				+ ";privilegeSnList:" + privilegeSnList);
		// 该节点的操作权限标志
		boolean privilegeFlag = false;
		if (privilegeSnList.contains(tn.getData())) {
			privilegeFlag = true;
		}
		if (tn.getChilds().size() > 0) {
			// 为了能进行下面for的&&操作，初始化为true
			privilegeFlag = true;
		}
		for (TreeNode n : tn.getChilds()) {
			boolean flag = tnRecursive(n, pTn, privilegeSnList);
			// 拥有所有子节点的权限的话就意味着拥有该父节点的权限，不可能出现拥有父节点权限却没有所有其下的子节点权限的情况
			privilegeFlag = privilegeFlag && flag;
		}

		if (privilegeFlag == true) {
			pTn.add(tn);
		}

		return privilegeFlag;
	}

	private TreeNode departmentRecursive(Department dep,
			ArrayList<TreeNode> depTnList) {
		logger.info("DepartmentService_departmentRecursive;dep:" + dep
				+ ";depTnList:" + depTnList);
		if (dep == null)
			return null;

		TreeNode tn = new TreeNode();
		tn.setId(dep.getUuid());
		tn.setName(dep.getName());
		Department pDep = dep.getParent();
		tn.setParent(pDep == null ? null : pDep.getUuid());
		tn.setData(dep.getSn());

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("sn", "asc");
		ArrayList<Department> depList = (ArrayList<Department>) this
				.getScrollData(-1, -1, "o.parent=?1", new Object[] { dep },
						orderby).getResultlist();
		for (Department child : depList) {
			TreeNode ctn = departmentRecursive(child);
			tn.getChilds().add(ctn);
			depTnList.add(ctn);
		}
		return tn;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Set<SimpleTreeNode> allChildrenTreeNode(Department userDep) {
		logger.info("DepartmentService_allChildrenTreeNode;userDep:"
				+ userDep);
		Set<SimpleTreeNode> children = new HashSet<SimpleTreeNode>();
		for (Department childrenDep : userDep.getChilds()) {
			SimpleTreeNode childrenTreeNode = new SimpleTreeNode();
			childrenTreeNode.setId(childrenDep.getUuid());
			childrenTreeNode.setText(childrenDep.getName());
			childrenTreeNode.setExpanded(false);
			// childrenTreeNode.setLeaf(false);
			if (childrenDep.getChilds().size() == 0) {
				childrenTreeNode.setLeaf(true);
			} else {
				childrenTreeNode.setLeaf(false);
				childrenTreeNode.setChildren(this
						.allChildrenTreeNode(childrenDep));
			}
			children.add(childrenTreeNode);
		}
		return children;
	}

	public ArrayList<TreeNode> getAllDepTree() {
		logger.info("DepartmentService_getAllDepTree;");
		ArrayList<TreeNode> rootTnList = convertAllDepsToTreeNodes();
		return rootTnList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.credit.service.privilege.DepartmentService#findByTwoDomainNames
	 * (java.lang.String)
	 */
	public Department findByTwoDomainNames(String twoDomainNames) {
		logger.info("DepartmentService_findByTwoDomainNames;twoDomainNames:"
				+ twoDomainNames);
		StringBuffer sb = new StringBuffer(
				"select e from Department e where instr(?1,e.twoDomainNames)>0 ");
		@SuppressWarnings("unchecked")
		List<Department> departments = this.entityManager.createQuery(sb.toString()).setParameter(1, twoDomainNames).getResultList();
		Department department = null;
		if (departments != null && departments.size() > 0) {
			department = departments.get(0);
		}
		return department;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.credit.service.privilege.DepartmentService#findByName(java.lang
	 * .String)
	 */
	public String findByName(String name) {
		logger.info("DepartmentService_findByName;name:" + name);
		StringBuffer sb = new StringBuffer(
				"select twoDomainNames from TP_Department e where e.name=?1 ");
		@SuppressWarnings("unchecked")
		List<String> urls = this.entityManager.createNativeQuery(sb.toString())
				.setParameter(1, name).getResultList();
		String url = null;
		if (urls != null && urls.size() > 0) {
			url = urls.get(0);
		}
		return url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.credit.service.privilege.DepartmentService#findEntityByName(java
	 * .lang.String)
	 */
	public Department findEntityByName(String name) {
		logger.info("DepartmentService_findEntityByName;name:" + name);
		StringBuffer sb = new StringBuffer(
				"select e from Department e where e.name=?1 ");
		@SuppressWarnings("unchecked")
		List<Department> departments = this.entityManager
				.createQuery(sb.toString()).setParameter(1, name)
				.getResultList();
		Department department = null;
		if (departments != null && departments.size() > 0) {
			department = departments.get(0);
		}
		return department;
	}
}
