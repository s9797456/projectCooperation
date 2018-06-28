package com.credit.controller.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.privilege.Menu;
import com.credit.bean.privilege.Department;
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.privilege.MenuService;
import com.credit.service.privilege.DepartmentService;
import com.credit.util.properties.GlobalUtil;

@Controller
@RequestMapping("/control/navigation")
public class NavigationMenuAction {
	
	
	@Resource
	private MenuService menuService;
	@Resource
	private DepartmentService organizationService;
	private Department department;
	private String logoImage;
	
	@RequestMapping("/main")
	public String main(HttpServletRequest request) {
		String twoDomainNames = request.getRequestURL().toString();
		department = organizationService.findByTwoDomainNames(twoDomainNames);
		if (department != null) {
			String[] strs = department.getLogoImageUrl().split("/>");
			if (strs.length > 1) {
				logoImage = strs[1].substring(strs[1].indexOf("src=") + 5,
						strs[1].indexOf("alt") - 2);
			}
			String proccessImageUrl = department.getProccessImageUrl();
			request.setAttribute("name", department.getName());
			request.setAttribute("email", "E-mail:"+department.getEmail());
			request.setAttribute("phone", "Tel" + department.getPhone());
			request.setAttribute("orgUrl", department.getDepUrl());
			request.setAttribute(
					"logoImage",
					"padding: 45px 20px 10px 31px;background-repeat: no-repeat ; background-image: url("
							+ logoImage + ");");
			request.setAttribute(
					"proccessImageUrl",
					"background-image: url("
							+ proccessImageUrl.substring(
									proccessImageUrl.indexOf("src=") + 5,
									proccessImageUrl.indexOf("alt") - 2)
							+ ");background-repeat: no-repeat;background-position: center;background-size:100% 100%;");
		} else {
			request.setAttribute("name", GlobalUtil.getMsg("corp_name"));
			request.setAttribute("email", "E-mail:"+GlobalUtil.getMsg("corp_email"));
			request.setAttribute("phone", "Tel:"+GlobalUtil.getMsg("corp_phone"));
			request.setAttribute("orgUrl", GlobalUtil.getMsg("corp_url"));
			request.setAttribute(
					"logoImage",
					"padding: 45px 20px 10px 31px;background-repeat: no-repeat ; background-image: url("
							+ request.getContextPath()
							+  GlobalUtil.getMsg("obj_logoImage")+");");
			request.setAttribute(
					"proccessImageUrl",
					"background-image: url("
							+ request.getContextPath()
							+ "/Images/replace/right.jpg);background-repeat: no-repeat;background-position: center;background-size:100% 100%;");
		}
		return "Control/newIndex";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/getTreePanelList")
	@ResponseBody
	public String getTreePanelList(HttpServletRequest request,HttpServletResponse response ) {
		String path = request.getContextPath();// /Score
		// 顶层菜单--企业管理--信用评分--数据平台--系统管理
		ArrayList<Menu> myOutRootMenus =(ArrayList<Menu>) request.getSession().getAttribute("myOutRootMenus");
		JSONArray array = new JSONArray();
		if(!myOutRootMenus.isEmpty()){
			for (Menu menu : myOutRootMenus) {
				JSONObject json = new JSONObject();
				json.element("id", menu.getUuid());
				if (menu.getImgUrl() != null) {
					json.element("icon", path + menu.getImgUrl());
				}
				json.element("title", menu.getName());
				array.add(json);
			}
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/userMenuTree")
	@ResponseBody
	public String userMenuTree(HttpServletRequest request,HttpServletResponse response) {
		ArrayList<Menu> myOutMenus = (ArrayList<Menu>) request.getSession().getAttribute("myOutMenus");
		Menu rootMenus = menuService.find(request.getParameter("id"));
		JSONArray array = new JSONArray();
		String path = request.getContextPath();
		if (rootMenus.getChilds() != null) {
			for (Menu menu : rootMenus.getChilds()) {
				if (myOutMenus.contains(menu)) {
					JSONObject json = new JSONObject();
					json.element("id", menu.getUuid());
					json.element("text", menu.getName());
					if (menu.getImgUrl() != null) {
						json.element("icon", path + menu.getImgUrl());
					} else {
						json.element("icon", "");
					}
					if (menu.getChilds().size() == 0) {
						json.element("leaf", true);
					} else {
						json.element("leaf", false);
						json.element("expanded", true);
						json.element("children", menuService
								.allMenuChildrenTreeNode(menu, myOutMenus));
					}
					array.add(json);
				}
			}
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(array.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Permission(model = "user", privilegeValue = "list")
	@RequestMapping("getMenus")
	@ResponseBody
	public Map<String, Object> getMenus(HttpServletRequest request) {
		Menu menu = menuService.getMenuOfUuid(request.getParameter("id"));
		Map<String, Object> msgMap = new HashMap<String, Object>();
		msgMap.put("rel", menu.getRel());
		msgMap.put("target", menu.getTarget());
		return msgMap;

	}
}
