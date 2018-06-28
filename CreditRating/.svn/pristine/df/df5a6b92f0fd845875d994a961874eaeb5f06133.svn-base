package com.credit.controller.privilege;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.pagelist.PageView;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.privilege.Department;
import com.credit.bean.vo.privilege.DepartmentVO;
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.privilege.DepartmentService;


@Controller
@RequestMapping("/control/department")
public class DepartmentAction {

	private static final Logger logger = Logger.getLogger(DepartmentAction.class);

	@Resource(name = "departmentServiceBean")
	private DepartmentService departmentService;


	/**
	 * 
	 * @title 部门列表l
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "department", privilegeValue = "list")
	@RequestMapping("/listUI")
	@ResponseBody
	public Map<String, Object> listUI(Department department,int page,int limit,String parentid) throws Exception {
		logger.info( "部门列表listUI;Department:"+department);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		PageView<Department> pageView = new PageView<Department>(limit, page);
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();

		if (parentid != null && !"".equals(parentid.trim())) {
			if (params.size() > 0)
				jpql.append(" and ");
			jpql.append(" o.parent.uuid=?" + (params.size() + 1));
			params.add(parentid);
			Department menu = departmentService.find(parentid);
			Department parentDepartment = menu.getParent();
			List<Department> departments = new ArrayList<Department>();
			departments.add(menu);
			while (parentDepartment != null) {
				departments.add(parentDepartment);
				parentDepartment = parentDepartment.getParent();
			}
			msgMap.put("list", departments);
		} else {
			if (params.size() > 0)
				jpql.append(" and ");
			jpql.append("o.parent is null");
		}
		if (department.getName() != null && !"".equals(department.getName().trim())) {
			if (params.size() > 0)
				jpql.append(" and ");
			jpql.append(" o.name like ?");
			params.add("%" + department.getName().trim() + "%");
		}

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("sn", "asc");
		QueryResult<Department> qr = departmentService.getScrollData(
				pageView.getFirstResult(), pageView.getMaxresult(),
				jpql.toString(), params.toArray(), orderby);

		pageView.setQueryResult(qr);
		List<DepartmentVO> list = new ArrayList<DepartmentVO>();
		for (Department dep : qr.getResultlist()) {
			DepartmentVO depvo = new DepartmentVO();
			// ov.setParentName(or.getParent().getName());
			if (dep != null) {
				depvo.setSn(dep.getSn());
				depvo.setDescription(dep.getDescription());
				depvo.setEmail(dep.getEmail());
				depvo.setFax(dep.getFax());
				depvo.setPhone(dep.getPhone());
				depvo.setTwoDomainNames(dep.getTwoDomainNames());
				depvo.setDepUrl(dep.getDepUrl());
				depvo.setLogoImageUrl(dep.getLogoImageUrl());
				depvo.setProccessImageUrl(dep.getProccessImageUrl());
				if (dep.getChilds().size() > 0) {
					depvo.setName(dep.getName() + " (有" + dep.getChilds().size()+ "子部门）");
					depvo.setLength(dep.getChilds().size());
				} else {
					depvo.setName(dep.getName());
					depvo.setLength(0);
				}
				if (dep.getVisible().intValue() == 1) {
					depvo.setStatus("正常" + " | " + "锁定");
				} else {
					depvo.setStatus("关闭" + " | " + "激活");
				}
				if (dep.getParent() != null) {
					depvo.setParentID(dep.getParent().getUuid());
				}
				depvo.setUuid(dep.getUuid());
				if (dep.getParent() != null) {
					depvo.setParentName(dep.getParent().getName());
				} else {
					depvo.setParentName("");
				}
				list.add(depvo);

			} else {
				depvo.setParentName("");
				depvo.setName("");
				depvo.setStatus("");
				depvo.setSn("");
				depvo.setUuid("");
				depvo.setLength(0);
				list.add(depvo);
			}
		}

		msgMap.put("total", qr.getTotalrecord());
		msgMap.put("list", list);
		return msgMap;

	}


/*	@Permission(model = "department", privilegeValue = "view")
	public String treeUI() throws Exception {
		List<Department> pageView = new ArrayList<Department>();
		ActionContext.getContext().put("pageView",
				departmentService.getDepTree());
		// logger.info("部门管理访问");
		pageView = departmentService.getDepTree().getRecords();
		for (Department or : pageView) {
			SimpleTreeNode simpleTreeNode = new SimpleTreeNode();
			if (or.getParent() == null) {
				simpleTreeNode.setId(or.getUuid());
				simpleTreeNode.setText(or.getName());
				// simpleTreeNode.setChildren(or.getChilds());
				simpleTreeNode.setExpanded(false);
				if (or.getChilds().isEmpty()) {
					simpleTreeNode.setLeaf(true);
				} else {
					simpleTreeNode.setLeaf(false);
					simpleTreeNode.setChildren(departmentService
							.allChildrenTreeNode(or));
				}
				jsonDepartmentTree.add(simpleTreeNode);
			}
		}
		return "jsonDepartmentTree";
	}

	@Permission(model = "department", privilegeValue = "viewUser")
	public String userTreeElementUI() throws Exception {
		departmentService.cleanUsers();
		Map<String, Object> userMap = new HashMap<String, Object>();
		List<UserOV> lists = new ArrayList<UserOV>();
		List<User> users = departmentService
				.getDepOfUser(getDepartmentID());

		if (!users.isEmpty()) {
			List<String> userNames = new ArrayList<String>();
			for (User user : users) {
				userNames.add(user.getUserName());

			}


			PageView<User> pageView = new PageView<User>(getNumPerPage(),
					getPageNum());
			StringBuffer jpql = new StringBuffer("");
			List<Object> params = new ArrayList<Object>();

			// jpql.append(" o.userName in('admin2','admin')");

			StringBuffer str = new StringBuffer("");

			for (int i = 0; i < userNames.size(); i++) {
				str.append("'").append(userNames.get(i)).append("'")
						.append(",");
			}
			str.deleteCharAt(str.length() - 1);

			jpql.append(" o.userName in(" + str.toString() + ")");

			LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
			if (orderField != null && !"".equals(orderField)) {
				orderby.put(orderField, orderDirection);
			} else {
				orderby.put("regTime", "desc");
				orderby.put("userName", "asc");
			}

			QueryResult<User> qr = userService.getScrollData(
					pageView.getFirstResult(), pageView.getMaxresult(),
					jpql.toString(), params.toArray(), orderby);

			pageView.setQueryResult(qr);
			this.total = qr.getTotalrecord();
			this.users = qr.getResultlist();

			// if(users!=null){
			for (User user : users) {
				UserOV uv = new UserOV();

				uv.setUserName(user.getUserName());
				uv.setRealName(user.getRealName());
				if (user.getVisible() == 1) {
					uv.setStatus("正常");
				} else {
					uv.setStatus("关闭");
				}
				uv.setUpdateTime(user.getUpdateTime());
				lists.add(uv);
			}
		} else {
			UserOV uv = new UserOV();
			
			 * uv.setUserName(""); uv.setRealName(""); uv.setStatus("");
			 * uv.setUpdateTime(null);
			 
			lists.add(uv);
		}
		userMap.put("totals", total);
		userMap.put("lists", lists);
		this.setResponseJson(userMap);

		return "jsonResult";

	}

	@Permission(model = "department", privilegeValue = "viewRole")
	public String privilegeGroupTreeElementUI() throws Exception {

		Department department = departmentService.find(departmentID);

		ActionContext.getContext().put("PrivilegeGroups",
				department.getPrivilegeGroups());
		ActionContext.getContext().put("departmentID", departmentID);

		// logger.info("部门包含角色访问");
		return "privilegeGroupTreeElementList";
	}
*/
}
