package com.credit.controller.privilege;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.pagelist.PageView;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.privilege.Menu;
import com.credit.bean.vo.privilege.MenuVO;
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.privilege.MenuService;
import com.credit.util.tree.SimpleTreeNode;


@Controller
@RequestMapping("/control/menu")
public class MenuAction {

	@Resource
	private MenuService menuService;
	private String moduleName = "管理员操作情况";
	private static final Logger logger = Logger.getLogger(MenuAction.class);


	/**
	 * 
	 * @title 菜单列表
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "menu", privilegeValue = "list")
	@RequestMapping("/listUI")
	@ResponseBody
	public Map<String, Object> listUI(HttpServletRequest request,int page,int limit,String parentid) throws Exception {
		logger.info(moduleName + "[查看菜单列表]");
		PageView<Menu> pageView = new PageView<Menu>(limit,page);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if (parentid != null && !"".equals(parentid.trim())) {
			if (params.size() > 0)
				jpql.append(" and ");
			jpql.append(" o.parent.uuid=?" + (params.size() + 1));
			params.add(parentid);
			Menu menu = menuService.find(parentid);
			Menu parentMenu = menu.getParent();
			List<Menu> menus = new ArrayList<Menu>();
			menus.add(menu);
			while (parentMenu != null) {
				menus.add(parentMenu);
				parentMenu = parentMenu.getParent();
			}
			msgMap.put("menus", menus);
		} else {
			if (params.size() > 0)
				jpql.append(" and ");
			jpql.append("o.parent is null");
		}

		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("orderNO", "asc");
		QueryResult<Menu> qr = menuService.getScrollData(
				pageView.getFirstResult(), pageView.getMaxresult(),
				jpql.toString(), params.toArray(), orderby);
		pageView.setQueryResult(qr);
		List<Menu> menus = qr.getResultlist();
		List<MenuVO> mvs = new ArrayList<MenuVO>();
		for (Menu m : menus) {
			MenuVO mv = new MenuVO();
			mv.setUuid(m.getUuid());
			mv.setTarget(m.getTarget());
			mv.setRel(m.getRel());
			if (m.getChilds().size() > 0) {
				mv.setName(m.getName() + " （有" + m.getChilds().size() + "子菜单）");
				mv.setDistributionOfAddress("");
				mv.setTarget_rel("");
			} else {
				if(m.getTarget()!=null&&m.getRel()!=null){
					mv.setName(m.getName()+" ");
					mv.setDistributionOfAddress("分配地址" + " | " + "分配权限");
					mv.setTarget_rel(m.getTarget() + " | " + m.getRel());
				}else{
					mv.setName(m.getName());
					mv.setDistributionOfAddress("分配地址" + " | " + "分配权限");
					mv.setTarget_rel(m.getTarget() + " | " + m.getRel());
				}
			}
			mv.setSn(m.getSn());
			if (m.getParent() == null) {
				mv.setParentName("");
				mv.setParentID("");
			} else {
				Menu mp=menuService.find(m.getParent().getUuid());
				if(mp!=null){
					mv.setParentName(mp.getName());
				}else{
					mv.setParentName("");
				}
				mv.setParentID(m.getParent().getUuid());
			}
			mv.setOrderNO(m.getOrderNO());
			mv.setUrl(m.getUrl());
			if (m.getVisible() == 1) {
				mv.setStatus("正常" + " | " + "锁定");
			} else {
				mv.setStatus("关闭" + " | " + "激活");
			}
			mvs.add(mv);
		}
		msgMap.put("menus", mvs);
		msgMap.put("total", qr.getTotalrecord());
		return msgMap;
	}
	/**
	 * 
	 * @title 菜单预览
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Permission(model = "menu", privilegeValue = "view")
	@RequestMapping("/menuPrivilegeTreeUI")
	@ResponseBody
	public Map<String, Object> menuPrivilegeTreeUI() throws Exception {
		logger.info("菜单预览");
		PageView<Menu> pageView = new PageView<Menu>(-1, -1);
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		Map<String, Object> msgMap = new HashMap<String, Object>();
		List<SimpleTreeNode> MenuTree = new ArrayList<SimpleTreeNode>();
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
		for (Menu menu : pageView.getRecords()) {
			SimpleTreeNode simpleTreeNode = new SimpleTreeNode();
			if (menu.getParent() == null) {
				simpleTreeNode.setId(menu.getUuid());
				simpleTreeNode.setText(menu.getName());
				simpleTreeNode.setExpanded(true);
				if (menu.getChilds().isEmpty()) {
					simpleTreeNode.setLeaf(true);
				} else {
					simpleTreeNode.setLeaf(false);
					simpleTreeNode.setChildren(menuService.allChildrenTreeNode(menu));
				}
				MenuTree.add(simpleTreeNode);
				msgMap.put("tree", MenuTree);
			}
		}
		return msgMap;
	}
	
	
}
