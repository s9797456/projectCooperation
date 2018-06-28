package com.credit.controller.privilege;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.privilege.Menu;
import com.credit.bean.privilege.SystemPrivilege;
import com.credit.bean.privilege.SystemPrivilegePK;
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.privilege.MenuService;
import com.credit.service.privilege.SystemPrivilegeService;
import com.credit.util.checkbox.CheckBox;
import com.credit.util.checkbox.CheckBoxGroup;




@Controller
@RequestMapping("/control/menu/manage")
public class MenuManageAction  {

	

	@Resource
	private MenuService menuService;
	@Resource
	private SystemPrivilegeService systemPrivilegeService;
	
	private StringBuffer message = new StringBuffer("");
	private String moduleName = "管理员操作情况";
	
	private static final Logger logger = Logger.getLogger(MenuManageAction.class);
	
	private boolean flag=true;
	 /**
	  * 
	  * @title 添加菜单界面
	  * @author  孙尚飞  2017-7-25
	  * @desc
	  */
	@Permission(model = "menu", privilegeValue = "insert")
	@RequestMapping("/addUI")
	@ResponseBody
	public Map<String, Object> addUI(String parentID) throws Exception {
		logger.info( "添加菜单界面addUI;String:"+parentID);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		msgMap.put("parentID", parentID);
		msgMap.put("status", true);
		return msgMap;
	}
	 /**
	  * 
	  * @title 添加菜单
	  * @author  孙尚飞  2017-7-25
	  * @desc
	  */
	@Permission(model = "menu", privilegeValue = "insert")
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(Menu m,String parentid) throws Exception {
		logger.info( "添加菜单add;Menu:"+m);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		message.setLength(0);
		if (m.getName().trim().equals("")) {
			message.append("请输入菜单名");
			flag = false;
		}
		if ((Integer)m.getOrderNO()==null) {
			message.append("请输入排序号");
			flag = false;
		}
		String wherejpql = "o.name=?1 and o.parent ";
		Object[] queryParams;
		if (parentid==null||parentid.equals("")) {
			wherejpql += "is null";
			queryParams = new Object[] { m.getName().trim() };
		} else {
			wherejpql += "=?2";
			queryParams = new Object[] { m.getName().trim(), new Menu(parentid) };
		}
		long length = menuService.getScrollData(-1, -1, wherejpql, queryParams)
				.getTotalrecord();
		if (length > 0) {
			message.append("该目录下已有同名的菜单");
			flag = false;
		}
		if (flag) {
			message.append("操作成功");
			Menu menu = new Menu();
			if (parentid!=null&&!parentid.equals(""))
				menu.setParent(new Menu(parentid));
			menu.setName(m.getName().trim());
			if (m.getSn() != null)
				menu.setSn(m.getSn().trim());
			if ((Integer)m.getOrderNO()!=null)
				menu.setOrderNO((Integer)m.getOrderNO());
			if (m.getUrl() != null)
				menu.setUrl(m.getUrl().trim());
         /*if (m.uploadFile != null) {
				if (uploadFile.getImgUrl().length() > 0) {
					if (!ValidateFile.imageType(uploadFile.getImgUrlFileName(),
							uploadFile.getImgUrlContentType())) {
						logger.info("图片格式错误.试图上传的文件名是:"
								+ uploadFile.getImgUrlFileName() + "文件类型是:"
								+ uploadFile.getImgUrlContentType());
					} else {
						String pathdir = "/UploadFiles/Images/"
								+ DateTime.getDirectory();// 构建图片保存的目录
						String realpathdir = ServletActionContext
								.getServletContext().getRealPath(pathdir);
						String ext = uploadFile
								.getImgUrlFileName()
								.trim()
								.substring(
										uploadFile.getImgUrlFileName().trim()
												.lastIndexOf('.') + 1)
								.toLowerCase();
						String saveimagename = UUID.randomUUID().toString()
								+ "." + ext;// 构建文件名称
						SaveFile.image(uploadFile, realpathdir, saveimagename);
						menu.setImgUrl(pathdir + "/" + saveimagename);
					}
				}
			}*/
			menuService.save(menu);
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "添加菜单成功");
			logger.info(moduleName + "[菜单新增:" + message + "]");
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "添加菜单失败,"+message);
			logger.info(moduleName + "[菜单新增:" + message + "]");
		}
		return msgMap;

	}
	 /**
	  * 
	  * @title 编辑菜单
	  * @author  孙尚飞  2017-7-25
	  * @desc
	  */
	@Permission(model = "menu", privilegeValue = "update")
	@RequestMapping("/edit")
	@ResponseBody
	public Map<String, Object> edit(Menu m) throws Exception {
		logger.info( "编辑菜单edit;Menu:"+m);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		message.setLength(0);
		Menu menu = menuService.find(m.getUuid());
		if (menu == null) {
			message.append("没有查到实体");
			flag = false;
		}
		if (m.getName().trim().equals("")) {
			message.append("请输入菜单名");
			flag = false;
		}
		if ((Integer)m.getOrderNO()==null) {
			message.append("请输入排序号");
			flag = false;
		}
		String wherejpql = "o.name=?1 and o.uuid<>?2 and o.parent ";
		Object[] queryParams;
		if (m.getParent()==null) {
			wherejpql += "is null";
			queryParams = new Object[] { m.getName(), m.getUuid() };
		} else {
			wherejpql += "=?3";
			queryParams = new Object[] { m.getName(), m.getUuid(), new Menu(m.getParent().getUuid()) };
		}
		long length = menuService.getScrollData(-1, -1, wherejpql, queryParams)
				.getTotalrecord();
		if (length > 0) {
			message.append("该目录下已有同名的菜单");
			flag = false;
		}
		if (flag) {
			message.append("操作成功");
			if (m.getParent()!=null)
				menu.setParent(new Menu(m.getParent().getUuid()));
			menu.setName(m.getName().trim());
			if (m.getSn() != null)
				menu.setSn(m.getSn().trim());
			if ((Integer)m.getOrderNO()!=null)
				menu.setOrderNO((Integer)m.getOrderNO());
			if (m.getUrl() != null)
				menu.setUrl(m.getUrl().trim());

			/*if (uploadFile != null) {
				if (uploadFile.getImgUrl().length() > 0) {
					if (!ValidateFile.imageType(uploadFile.getImgUrlFileName(),
							uploadFile.getImgUrlContentType())) {
						logger.info("图片格式错误.试图上传的文件名是:"
								+ uploadFile.getImgUrlFileName() + "文件类型是:"
								+ uploadFile.getImgUrlContentType());
					} else {
						String pathdir = "/UploadFiles/Images/"
								+ DateTime.getDirectory();// 构建图片保存的目录
						String realpathdir = ServletActionContext
								.getServletContext().getRealPath(pathdir);
						String ext = uploadFile
								.getImgUrlFileName()
								.trim()
								.substring(
										uploadFile.getImgUrlFileName().trim()
												.lastIndexOf('.') + 1)
								.toLowerCase();
						String saveimagename = UUID.randomUUID().toString()
								+ "." + ext;// 构建文件名称
						SaveFile.image(uploadFile, realpathdir, saveimagename);
						menu.setImgUrl(pathdir + "/" + saveimagename);
					}
				}
			}*/

			menuService.update(menu);
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "修改菜单成功");
			logger.info(moduleName + "[菜单编辑:" + message + "]");
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "修改菜单失败,"+message);
			logger.info(moduleName + "[菜单编辑:" + message + "]");
		}
		return msgMap;
	}

	 /**
	  * 
	  * @title 删除菜单
	  * @author  孙尚飞  2017-7-25
	  * @desc
	  */
	@Permission(model = "menu", privilegeValue = "delete")
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(String uuid) throws Exception {
		logger.info( "删除菜单delete;String:"+uuid);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		message.setLength(0);
		Menu menu = menuService.find(uuid);
		if (menu == null) {
			message.append("参数错误");
			flag = false;
		}
		if (menu.getChilds().size() >= 1) {
			message.append("请删除子菜单");
			flag = false;
		}
		if (flag) {
			menuService.delete((Serializable) uuid.trim());
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "删除菜单成功");
			message.append("操作成功");
			logger.info(moduleName + "[菜单删除:" + message + "]");
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "删除菜单失败,"+message);
			logger.info(moduleName + "[菜单删除:" + message + "]");
		}
		return msgMap;
	}
	/**
	 * 
	 * @title 更改菜单状态
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "menu", privilegeValue = "update")
	@RequestMapping("/updateVisible")
	@ResponseBody
	public Map<String, Object> updateVisible(String uuid,int visible) throws Exception {
		logger.info( "更改菜单状态updateVisible;String:"+uuid);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		message.setLength(0);
		menuService.updateVisible(uuid, visible);
		if (visible == 1) {
			message.append("菜单激活成功");
		} else {
			message.append("菜单锁定成功");
		}

		msgMap.put("success", true);
		msgMap.put("status", true);
		msgMap.put("msg", message);
	
		logger.info(moduleName + "[菜单状态设置:" + message + "]");
		return msgMap;
	}
	
    /**
     * 
     * @title 获取父菜单名称
     * @author  孙尚飞  2017-7-25
     * @desc
     */
	@Permission(model = "menu", privilegeValue = "list")
	@RequestMapping("/getParentMenuName")
	@ResponseBody
	public Map<String, Object> getParentMenuName(String uuid) throws Exception {
		logger.info( "得到父菜单名称getParentMenuName;String:"+uuid);
		logger.info("===========开始获取===========");
    	Map<String, Object> msgMap = new HashMap<String, Object>();    	
		Menu menu=menuService.find(uuid);
		if (menu != null) {
			String names="";
			String ownName="------"+"<a href=javascript:backParent("+"'"+menu.getUuid()+"'"+") style='color:#FFF'>"+menu.getName()+"</a>";
			while(menu.getParent()!=null){			
				Menu parentMenu=menuService.find(menu.getParent().getUuid());		
				names="------"+"<a href=javascript:backParent("+"'"+parentMenu.getUuid()+"'"+") style='color:#FFF'>"+parentMenu.getName()+"</a>"+"------"+names;
				menu=parentMenu;
			}
			if(!names.equals("")){	
				names=names.substring(0, names.length()-6);
			}
			msgMap.put("names", names);
			msgMap.put("ownName", ownName);
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "获取成功");
		}else{			
			msgMap.put("success", false);
			msgMap.put("status", false);
			msgMap.put("msg", "获取失败");
		} 	
		return msgMap;
	}
	/**
	 * 
	 * @title 给菜单分配地址
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "menu", privilegeValue = "setUrl")
	@RequestMapping("/urlEdit")
	@ResponseBody
	public Map<String, Object> urlEdit(Menu m) throws Exception {
		logger.info( "分配地址urlEdit;Menu:"+m);
		Menu menu = menuService.find(m.getUuid());
		Map<String, Object> msgMap = new HashMap<String, Object>();
		message.setLength(0);
		if (menu == null) {
			message.append("没有查到实体");
			flag = false;
		}
		if (!menu.getChilds().isEmpty()) {
			message.append("只能给子节点赋权");
			flag = false;
		}
		if (flag) {
			message.append("操作成功");

			menu.setUrl(m.getUrl());
			menu.setTarget(m.getTarget());
			menu.setRel(m.getRel());

			menuService.update(menu);
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", message.toString());
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", message.toString());
		}
		return msgMap;
	}
	/**
	 * 
	 * @title 获取所有的系统权限
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "menu", privilegeValue = "setPrivilege")
	@RequestMapping("/privilegeEditUI")
	@ResponseBody
	public Map<String, Object> privilegeEditUI(HttpServletResponse response,String uuid) throws Exception {
		logger.info( "获取权限privilegeEditUI;String:"+uuid);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		message.setLength(0);
		// 返回经过分类的所有权限的数据
		ArrayList<ArrayList<SystemPrivilege>[]> returnSPList = new ArrayList<ArrayList<SystemPrivilege>[]>();
		Menu menu = menuService.find(uuid);
		if (menu == null) {
			message.append("<p>没有查到实体</p>");
			flag = false;
		}
		if (!menu.getChilds().isEmpty()) {
			message.append("<p>只能给子节点赋权</p>");
			flag = false;
		}
		if (flag) {
			// 获取所有已被分配给菜单的权限
			Set<SystemPrivilege> menuSPList = menuService.allSystemPrivilegesDistributeToMenus();
			// 获取根据model字段排序的所有的权限
			List<SystemPrivilege> allSPList = systemPrivilegeService.allSPsOrderByModel();
			// 拥有相同model的SystemPrivilege的List集合
			ArrayList<SystemPrivilege> sameModelSPList = new ArrayList<SystemPrivilege>();
			int allSPListSize = allSPList.size();
			List<CheckBox> checkBoxs = new ArrayList<CheckBox>();
			// List<CheckBoxGroup> checkBoxGroups =new
			// ArrayList<CheckBoxGroup>();
			// 遍历所有的权限
			for (int i = 0; i < allSPListSize; i++) {
				if (i < allSPListSize - 1) {
					sameModelSPList.add(allSPList.get(i));
					if (!allSPList.get(i).getId().getModel()
							.equals(allSPList.get(i + 1).getId().getModel())) {
						// 若下一个权限的model属性与当前权限的model属性不相同，则说明所有当前model的权限已读完
						// 将权限分成已使用和未被使用的
						returnSPList.add(processSameModelSP(sameModelSPList,
								menuSPList));
						// 清空sameModelSPList，以存储下一种model的权限
						sameModelSPList = new ArrayList<SystemPrivilege>();
					}
				} else {
					sameModelSPList.add(allSPList.get(i));
					returnSPList.add(processSameModelSP(sameModelSPList,
							menuSPList));
				}
			}
			for (ArrayList<SystemPrivilege>[] systemPrivilegeArr : returnSPList) {
				for (SystemPrivilege usedSP : systemPrivilegeArr[1]) {
					CheckBox checkBox = new CheckBox();
					checkBox.setChecked(true);
					checkBox.setBoxLabel(usedSP.getName());
					checkBox.setDisabled(true);
					checkBox.setInputValue(usedSP.getId().getModel() + "#"
							+ usedSP.getId().getPrivilegeValue());
					checkBoxs.add(checkBox);
				}
				for (SystemPrivilege unUsedSP : systemPrivilegeArr[0]) {
					CheckBox checkBox = new CheckBox();
					checkBox.setChecked(false);
					checkBox.setDisabled(false);
					checkBox.setBoxLabel(unUsedSP.getName());
					checkBox.setInputValue(unUsedSP.getId().getModel() + "#"
							+ unUsedSP.getId().getPrivilegeValue());
					checkBoxs.add(checkBox);
				}
			}
			int checkBoxSize = checkBoxs.size();
			List<CheckBox> items = new ArrayList<CheckBox>();
			JSONArray array = new JSONArray();
			for (int i = 0; i < checkBoxSize; i++) {
				items.add(checkBoxs.get(i));
				if (i < checkBoxSize - 1) {
					CheckBox checkbox1 = checkBoxs.get(i);
					CheckBox checkbox2 = checkBoxs.get(i + 1);
					String inputValue1 = checkbox1.getInputValue();
					String inputValue2 = checkbox2.getInputValue();
					String[] intputArr1 = inputValue1.split("#");
					String[] intputArr2 = inputValue2.split("#");
					if (intputArr2.length > 0) {
						if (!intputArr1[0].equals(intputArr2[0])) {
							JSONObject json = new JSONObject();
							json.element("items", items);
							array.add(json);
							items = new ArrayList<CheckBox>();
						}
					}
				} else {
					JSONObject json = new JSONObject();
					json.element("items", items);
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
		msgMap.put("entity", menu);
		// 当前菜单包含的权限
		msgMap.put("selectSPs",menu.getSystemPrivileges());
		msgMap.put("spList", returnSPList);
		return msgMap;
	}
	/**
	 * 
	 * @title 给菜单分配权限
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "menu", privilegeValue = "setPrivilege")
	@RequestMapping("/privilegeEdit")
	@ResponseBody
	public Map<String, Object> privilegeEdit(String uuid,String SP) throws Exception {
		logger.info( "分配权限privilegeEdit;String:"+uuid);
		Map<String, Object> menuSPEdition = new HashMap<String, Object>();
		message.setLength(0);
		Menu menu = menuService.find(uuid);
		if (menu == null) {
			message.append("没有查到实体");
			flag = false;
		}
		if (!menu.getChilds().isEmpty()) {
			message.append("只能给子节点赋权");
			flag = false;
		}
		if (flag) {
			message.append("操作成功");
			if (menu.getChilds().isEmpty()) {
				//menu.getSystemPrivileges().clear();
				if (SP != null) {
					String[] spsArr = SP.split(",");
					for (int i = 0; i < spsArr.length; i++) {
						String[] spsPKArr = spsArr[i].split("#");
						SystemPrivilegePK id = new SystemPrivilegePK(spsPKArr[0], spsPKArr[1]);
						menu.addPrivilege(new SystemPrivilege(id));
					}
				}
				menuService.update(menu);

				menuSPEdition.put("success", true);
				menuSPEdition.put("status", true);
				menuSPEdition.put("msg", message.toString());
			}
			logger.info(moduleName + "[菜单权限编辑:" + message + "]");
		} else {
			menuSPEdition.put("success", true);
			menuSPEdition.put("status", false);
			menuSPEdition.put("msg", message.toString());
			logger.info(moduleName + "[菜单权限编辑:" + message + "]");
		}
		return menuSPEdition;
	}
	
	/**
	 * @title 辅助类
	 * @author  孙尚飞  2017-7-25
	 * @desc 将model名相同的权限进行分类，并存在一个长度为2的ArrayList<SystemPrivilege>[]中。
	 *            下标0存放已被使用的权限的List集合，下标1存放未被使用的权限的List集合
	 * @param sameModelSPList
	 *            相同的model属性的权限的List集合
	 * @param menuSPList
	 *            已被菜单使用的权限的Set集合
	 * @return 经过分类的权限
	 */
	private ArrayList<SystemPrivilege>[] processSameModelSP(
			ArrayList<SystemPrivilege> sameModelSPList,
			Set<SystemPrivilege> menuSPList) {
		// 已被菜单使用的权限的List集合
		ArrayList<SystemPrivilege> unusedSPList = new ArrayList<SystemPrivilege>();
		// 未被菜单使用的权限的List集合
		ArrayList<SystemPrivilege> usedSPList = new ArrayList<SystemPrivilege>();

		for (SystemPrivilege sp : sameModelSPList) {
			if (menuSPList.contains(sp)) {
				usedSPList.add(sp);
			} else {
				unusedSPList.add(sp);
			}
		}

		@SuppressWarnings("unchecked")
		ArrayList<SystemPrivilege>[] sameModelSPs = new ArrayList[] {
				unusedSPList, usedSPList };
		return sameModelSPs;
	}
}
