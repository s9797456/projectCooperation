package com.credit.controller.privilege;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.pagelist.PageView;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.privilege.Menu;
import com.credit.bean.privilege.PrivilegeGroup;
import com.credit.bean.privilege.SystemPrivilege;
import com.credit.bean.privilege.SystemPrivilegePK;
import com.credit.bean.vo.privilege.Permission;
import com.credit.bean.vo.privilege.PrivilegeGroupVO;
import com.credit.service.privilege.MenuService;
import com.credit.service.privilege.PrivilegeGroupService;
import com.credit.util.tree.SimpleTreeNode;


@Controller
@RequestMapping("/control/privilegegroup/manage/")
public class PrivilegeGroupManageAction {
	private static final Logger logger = Logger.getLogger(PrivilegeGroupManageAction.class);
	private Map<String, Object> msgMap = new HashMap<String, Object>();
	private Boolean flag = true;
	private String moduleName = "管理员操作情况";
	private StringBuffer message = new StringBuffer("");
	
	@Resource
	private PrivilegeGroupService groupService;
	@Resource
	MenuService menuService;
	
	/**
	 * @Title 保存新增的角色
	 * @author  Administrator  @date 2017-7-25 
	 * @Description 
	 *
	 */
	@Permission(model = "privilegeGroup", privilegeValue = "insert")
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(String name) throws Exception {
		if(name == null || "".equals(name.trim())){
			flag = false;
			msgMap.put("success", true);
			msgMap.put("status",false);
			msgMap.put("msg", "<p>角色名为空</p>");
		}
		
		
		if (groupService.exist("name", name.trim())) {
			flag = false;
			msgMap.put("success", true);
			msgMap.put("status",false);
			msgMap.put("msg", "<p>已有相同的角色名存在</p>");
		}

		if (flag) {
			message.append("<p>操作成功</p>");

			PrivilegeGroup pg = new PrivilegeGroup();
			pg.setUuid(UUID.randomUUID().toString().replace("-", ""));
			pg.setName(name.trim());

			groupService.save(pg);
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "角色新增成功");
			logger.info(moduleName + "[角色新增:" + message + "]");
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", message.toString());
			logger.info(moduleName + "[角色新增:" + message + "]");
		}
		return msgMap;
	}
	
	/**
	 * @Title 删除角色信息
	 * @author  Administrator  @date 2017-7-25 
	 * @Description 
	 *
	 */
	@Permission(model = "privilegeGroup", privilegeValue = "delete")
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(String uuid) throws Exception {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (uuid == null) {
			message.append("<p>参数错误</p>");
			flag = false;
		} else {
			if (groupService.find(uuid) == null) {
				message.append("<p>参数错误</p>");
				flag = false;
			}
		}
		if (flag) {
			groupService.delete((Serializable) uuid.trim());
			message.append("<p>操作成功</p>");
			logger.info(moduleName + "[角色删除:" + message + "]");
			msgMap.put("success", true);
		} else {
			logger.info(moduleName + "[角色删除:" + message + "]");
			msgMap.put("success", false);
		}
		return msgMap;
	}
	
	/**
	 * @Title 更新角色信息
	 * @author  Administrator  @date 2017-7-25 
	 * @Description 
	 *
	 */
	@Permission(model = "privilegeGroup", privilegeValue = "update")
	@RequestMapping("/edit")
	@ResponseBody
	public Map<String, Object> edit(PrivilegeGroupVO privilegeGroupVO) throws Exception {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (privilegeGroupVO.getUuid() == null) {
			message.append("<p>参数错误</p>");
			flag = false;
		} else {
			if (groupService.find(privilegeGroupVO.getUuid()) == null) {
				message.append("<p>参数错误</p>");
				flag = false;
			}
		}
		if (groupService.exist("name", privilegeGroupVO.getName())) {
			flag = false;
			message.append("<p>已有相同的角色名存在</p>");
		}

		if (flag) {
			message.append("<p>操作成功</p>");
			PrivilegeGroup pg = groupService.find(privilegeGroupVO.getUuid().trim());
			pg.setName(privilegeGroupVO.getName().trim());
			pg.getSystemPrivileges().clear();
			groupService.update(pg);
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "角色编辑成功");
			logger.info(moduleName + "[角色编辑:" + message + "]");
		} else {

			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", message.toString());
			logger.info(moduleName + "[角色编辑:" + message + "]");
		}
		return msgMap;

	}
	
	@SuppressWarnings("rawtypes")
	@Permission(model = "privilegeGroup", privilegeValue = "setPrivilege")
	@RequestMapping("/menuPrivilegeEditUI")
	@ResponseBody
	public List<SimpleTreeNode> menuPrivilegeEditUI(HttpServletRequest request, String uuid) throws Exception {
		List<SimpleTreeNode> jsonMenuTree = new ArrayList<SimpleTreeNode>();
		PageView<Menu> pageView = new PageView<Menu>(-1, -1);
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();

		if (params.size() > 0)
			jpql.append(" and ");
		jpql.append(" o.visible=?" + (params.size() + 1));
		params.add(1);

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("uuid", "desc");

		QueryResult<Menu> qr = menuService.getScrollData(
				pageView.getFirstResult(), pageView.getMaxresult(),
				jpql.toString(), params.toArray(), orderby);

		pageView.setQueryResult(qr);

		PrivilegeGroup privilegeGroup = groupService.find(uuid);

		request.setAttribute("treeNodes",menuService.getTreeNodes(pageView));
		request.setAttribute("childNodes",menuService.getChildNodes(pageView));
		request.setAttribute("entity", privilegeGroup);
		request.setAttribute("selectSystemPrivileges",privilegeGroup.getSystemPrivileges());

		for (Menu menu : pageView.getRecords()) {
			SimpleTreeNode simpleTreeNode = new SimpleTreeNode();
			if (menu.getParent() == null) {// 过滤出根节点
				simpleTreeNode.setId(menu.getUuid());
				simpleTreeNode.setText(menu.getName());
				if (menu.getChilds().size() == 0) {
					// 判断根节点是否有子节点
					simpleTreeNode.setLeaf(true);
				} else {
					simpleTreeNode.setLeaf(false);
					simpleTreeNode.setExpanded(true);
					simpleTreeNode.setChildren(menuService.allChildrenTreeNode(menu, privilegeGroup));
				}
				jsonMenuTree.add(simpleTreeNode);
			}
		}
		return jsonMenuTree;
	}
	
	@Permission(model = "privilegeGroup", privilegeValue = "setPrivilege")
	@RequestMapping("/menuPrivilegeEdit")
	@ResponseBody
	public String menuPrivilegeEdit(String uuid,String[] privilegesStr) throws Exception {

		PrivilegeGroup privilegeGroup = groupService.find(uuid);
		privilegeGroup.getSystemPrivileges().clear();
		if (privilegesStr != null) {
			for (int i = 0; i < privilegesStr.length; i++) {
				privilegeGroup.addPrivilege(new SystemPrivilege(
						new SystemPrivilegePK(privilegesStr[i].split(",")[0], privilegesStr[i]
								.split(",")[1])));
			}
		}
		groupService.update(privilegeGroup);
		message.append("<p>操作成功</p>");
		logger.info(moduleName + "[角色菜单权限设置:" + message + "]");
		return null;
	}

}
