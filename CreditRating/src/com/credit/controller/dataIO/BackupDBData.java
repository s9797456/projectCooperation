package com.credit.controller.dataIO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.credit.bean.privilege.Menu;
import com.credit.bean.privilege.Department;
import com.credit.bean.privilege.PrivilegeGroup;
import com.credit.bean.privilege.SystemPrivilege;
import com.credit.bean.member.User;
import com.credit.service.privilege.MenuService;
import com.credit.service.privilege.DepartmentService;
import com.credit.service.privilege.PrivilegeGroupService;
import com.credit.service.privilege.SystemPrivilegeService;
import com.credit.service.member.UserService;
import com.credit.util.tree.StandardTreeNode;
import com.credit.util.tree.TreeNode;

@Controller
@RequestMapping("/initial/backup")
public class BackupDBData {

	private static final Logger logger = Logger.getLogger(BackupDBData.class);

	@Resource(name="systemPrivilegeServiceBean")
	private SystemPrivilegeService systemPrivilegeService;
	
	@Resource(name="privilegeGroupServiceBean")
	private PrivilegeGroupService privilegeGroupService;
	
	@Resource(name="departmentServiceBean")
	private DepartmentService departmentService;
	
	@Resource(name="userServiceBean")
	private UserService userService;
	
	@Resource(name="menuServiceBean")
	private MenuService menuService;
	
	/**
	 * 操作结果
	 */
	public String getResultStr() { return resultStr; }
	public void setResultStr(String resultStr) { this.resultStr = resultStr; }
	private String resultStr;
	
	/**
	 * 提示
	 */
	public String getTips() { return tips; }
	public void setTips(String tips) { this.tips = tips; }
	private String tips;
	@RequestMapping("/UI")
	public String UI() {
		return "Initial/backupUI";
	}
	@RequestMapping("/backup")
	public String backup(HttpServletRequest request) {

		String requestUrl = request.getContextPath();
		
		try{
			Document allData = DocumentHelper.createDocument();
			Element root = allData.addElement("InitDatas");
			exportPermissions(root);
			exportMenu(root);
			exportRole(root);
			exportOrg(root);
			exportUsers(root);
			logger.info("导出结束！");
			XMLWriter writer = getXMLWriter("com/config/export_init_datas.xml");
			System.out.println(writeXml(writer, allData));
			this.resultStr = "<span style=\"color:green;\">成功</span>";
			this.tips = "<a href=\"" + requestUrl + "\">返回登录界面</a>";
		}catch(Exception e) {
			e.printStackTrace();
			this.resultStr = "<span style=\"color:red;\">失败</span>";
			this.tips = "请<a href=\"" + requestUrl + "/initial/backup/UI.do\">重试</a>";
		}
		request.setAttribute("resultStr", resultStr);
		request.setAttribute("tips", tips);
		return "Initial/backupResult";
	}
	
	private void exportPermissions(Element domRoot) throws Exception {
		try{
			Element permissionRoot = domRoot.addElement("Permissions");
			for(SystemPrivilege permission : systemPrivilegeService.allSPs()) {
				Element permissionNode = permissionRoot.addElement("Permission");
				System.out.println("权限名：" + permission.getName());
				permissionNode.addAttribute("name", permission.getName());
				permissionNode.addAttribute("privilegeValue", permission.getId().getPrivilegeValue());
				permissionNode.addAttribute("model", permission.getId().getModel());
			}
			logger.info("导出权限模块成功!");
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("导出权限模块失败!");
			throw new Exception("导出权限模块失败!");
		}
	}
	
	private void exportMenu(Element domRoot) throws Exception {
		try{
			Element menuRoot = domRoot.addElement("Menus");
			/*List<Menu> menuList =  menuService.getScrollData().getResultlist();
			Set<Menu> parentMenuSet = new HashSet<Menu>();
			for(Menu menu : menuList) {
				if(menu.getParent() == null) {
					parentMenuSet.add(menu);
				}
			}
			List<Menu> sortedParetMenuList = menuSortByOrderNO(parentMenuSet);
			for(int i = 0; i < sortedParetMenuList.size(); i++) {
				menuRecursion(sortedParetMenuList.get(i), menuRoot.addElement("Menu"));
			}*/
			ArrayList<StandardTreeNode> rootNode = menuService.allMenusStnRootNodesList();
			for(StandardTreeNode root : rootNode) {
				System.out.println(root.getName() + "----------------------");
				menuRecursion(root, menuRoot.addElement("Menu"));
				System.out.println("----------------------");
			}
			logger.info("导出菜单模块成功!");
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("导出菜单模块失败!");
			throw new Exception("导出菜单模块失败!");
		}
	}
	
	private void menuRecursion(StandardTreeNode stn, Element menuRoot) {
		System.out.println("菜单名:" + stn.getName());
		menuRoot.addAttribute("name", stn.getName());
		menuRoot.addAttribute("orderNO", stn.getDatas().get(0).toString());
		menuRoot.addAttribute("target", stn.getDatas().get(1).toString());
		menuRoot.addAttribute("rel", stn.getDatas().get(2).toString());
		menuRoot.addAttribute("url", stn.getDatas().get(3).toString());
		
		Set<SystemPrivilege> systemPrivilegeSet = 
				menuService.allSystemPrivilegesOfSomeMenu(new Menu(stn.getId()));
		for(SystemPrivilege sp : systemPrivilegeSet){
			Element permission  = menuRoot.addElement("Permission");
			permission.addAttribute("name", sp.getName());
			permission.addAttribute("privilegeValue", sp.getId().getPrivilegeValue());
			permission.addAttribute("model", sp.getId().getModel());
		}
		
		for(StandardTreeNode child : stn.getChildren()) {
			Element childElem = menuRoot.addElement("Menu");
			menuRecursion(child, childElem);
		}
		
	}
	
	/*private void menuRecursion(Menu parent, Element menuRoot) {
		System.out.println("menu name:" + parent.getName());
		menuRoot.addAttribute("name", parent.getName());
		menuRoot.addAttribute("orderNO", String.valueOf(parent.getOrderNO()));
		menuRoot.addAttribute("target", parent.getTarget());
		menuRoot.addAttribute("rel", parent.getRel());
		menuRoot.addAttribute("url", parent.getUrl());
		
		Set<SystemPrivilege> systemPrivilegeSet =  menuService.getAllOwnSPs(parent);
		for(SystemPrivilege sp : systemPrivilegeSet){
			Element permission  = menuRoot.addElement("Permission");
			permission.addAttribute("name", sp.getName());
			permission.addAttribute("model", sp.getId().getModel());
			permission.addAttribute("privilegeValue", sp.getId().getPrivilegeValue());
		}
		
		ArrayList<Menu> menuList = menuSortByOrderNO(menuService.getAllOwnChildMenus(parent));
		for(int i = 0; i < menuList.size(); i++) {
			Element child = menuRoot.addElement("Menu");
			menuRecursion(menuList.get(i), child);
		}
		
	}*/
	
	/*private ArrayList<Menu> menuSortByOrderNO(Set<Menu> menuSet) {
		//对此ArrayList中的menu进行排序
		ArrayList<Menu> menuList = new ArrayList<Menu>();
		menuList.addAll(menuSet);
		
		boolean flag = true;
		int end = menuList.size();
		while(flag && end > 0){
			flag = false;
			for(int i = 0; i < end - 1; i++) {
				if(menuList.get(i).getOrderNO() > menuList.get(i+1).getOrderNO()) {
					flag = true;
					Menu menu = menuList.get(i);
					menuList.set(i, menuList.get(i+1));
					menuList.set(i+1, menu);
				}
			}
			end --;
		}//冒泡排序
		return menuList;
	}*/
	
	private void exportRole(Element domRoot) throws Exception {
		try{
			Element roleRoot = domRoot.addElement("Roles");
			for(PrivilegeGroup pg : privilegeGroupService.getScrollData().getResultlist()) {
				Element roleNode = roleRoot.addElement("Role");
				System.out.println("角色名：" + pg.getName());
				roleNode.addAttribute("name", pg.getName());
				for(SystemPrivilege sp : 
							privilegeGroupService.allSystemPrivilegeInSomePrivilegeGroup(new PrivilegeGroup(pg.getUuid()))) {
					Element permissionNode = roleNode.addElement("Permission");
					permissionNode.addAttribute("name", sp.getName());
					permissionNode.addAttribute("privilegeValue", sp.getId().getPrivilegeValue());
					permissionNode.addAttribute("model", sp.getId().getModel());
				}
			}
			logger.info("导出角色模块成功！");
		}catch(Exception e){
			e.printStackTrace();
			logger.error("导出角色模块失败！");
			throw new Exception("导出角色模块失败!");
		}
	}
	
	private void exportOrg(Element domRoot) throws Exception {
		try{
			Element depRoot = domRoot.addElement("Departments");
			ArrayList<TreeNode> rootsList = 
					departmentService.convertAllDepsToTreeNodesPublic();
			for(TreeNode root : rootsList) {
				depRecursion(root, depRoot);
			}
			/*for(Department dep : departmentService.getScrollData().getResultlist()) {
				if(dep.getParent() == null) {
					depRecursion(dep, depRoot.addElement("Org"));
				}
			}*/
			logger.info("导出机构模块成功！");
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("导出机构模块失败！");
			throw new Exception("导出机构模块失败!");
		}
	}
	
	private void depRecursion(TreeNode tn, Element parentNode) {
		Element elem = parentNode.addElement("Org");
		System.out.println("机构名：" + tn.getName());
		elem.addAttribute("name", tn.getName());
		elem.addAttribute("id", tn.getData().toString());
		@SuppressWarnings("unused")
		Element roles = elem.addElement("OwnByRoles");
		/*由于一个用户只属于一个机构，只让用户有机构，角色不再关联机构（因为一个用户可以分配多个角色）
		 * Set<PrivilegeGroup> pgSet = 
				departmentService.allRolesOwnSomeDepartment(new Department(tn.getId()));
		for(PrivilegeGroup pg : pgSet) {
			Element role = roles.addElement("Role");
			System.out.println(pg.getName());
			role.addAttribute("name", pg.getName());
		}*/
		
		for(TreeNode child : tn.getChilds()) {
			depRecursion(child, elem);
		}
	}
	
	/*private void depRecursion(Department parent, Element parentNode) {
		parentNode.addAttribute("name", parent.getName());
		parentNode.addAttribute("id", parent.getSn());
		
		for(Department child : parent.getChilds()) {
			depRecursion(child, parentNode.addElement("Org"));
		}
	}*/
	
	private void exportUsers(Element domRoot) throws Exception {
		try {
			Element userRoot = domRoot.addElement("Users");
			for(User user : userService.getScrollData().getResultlist()) {
				Element userNode = userRoot.addElement("User");
				System.out.println("用户名：" + user.getUserName());
				userNode.addAttribute("realName", user.getRealName());
				userNode.addAttribute("password", user.getPassword());
				userNode.addAttribute("cellphone", user.getCellphone());
				userNode.addAttribute("email", user.getEmail());
				userNode.addAttribute("imgUrl", user.getImgUrl());
				userNode.addAttribute("gender", user.getGender().getName());
				userNode.addAttribute("regTime", user.getRegTime().toString());
				userNode.addAttribute("regIP", user.getRegIP());
				userNode.addAttribute("visible", String.valueOf(user.getVisible()));
				userNode.addAttribute("userName", user.getUserName());
				
				for(PrivilegeGroup pg : userService.getUserPrivilegeGroups(user)) {
					Element roleNode = userNode.addElement("Role");
					roleNode.addAttribute("name", pg.getName());
				}
				
				for(SystemPrivilege sp : userService.getSystemPrivilegesOnlyUnderUser(user)) {
					Element permissionNode = userNode.addElement("Permission");
					permissionNode.addAttribute("model", sp.getId().getModel());
					permissionNode.addAttribute("privilegeValue", sp.getId().getPrivilegeValue());
					permissionNode.addAttribute("name", sp.getName());
				}
				
				for(Department dep : userService.getUserDeps(user)) {
					Element depNode = userNode.addElement("Org");
					depNode.addAttribute("name", dep.getName());
					depNode.addAttribute("id", dep.getSn());
				}
			}
			logger.info("导出用户模块成功！");
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("导出用户模块失败！");
			throw new Exception("导出用户模块失败!");
		}
	}
	
	
	protected XMLWriter getXMLWriter(String fileName) {
		XMLWriter writer = null;
		
		OutputStream oStream;
		
		OutputFormat outFormat = OutputFormat.createPrettyPrint();
		outFormat.setEncoding("UTF-8");
		
		if(fileName == null || "".equals(fileName.trim())){
			System.out.println("------Error:fileName is null or \"\", XMLWriter cannot be created!");
			return null;
		}
		
		try {
			String filePath = 
					Thread.currentThread().getContextClassLoader().getResource("") + fileName;
			filePath = filePath.substring(6, filePath.length());
			System.out.println("输出到文件：" + filePath);
			oStream = new FileOutputStream( filePath );
			/*boolean throwFlag = false;
			String filePath = 
					Thread.currentThread().getContextClassLoader().getResource("") + fileName;
			filePath = filePath.substring(6, filePath.length());
			File oFile = new File(filePath);
			if(!oFile.exists()) {
				System.out.println("文件" + filePath + "不存在");
				try {
					throwFlag = !oFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					throwFlag = true;
				}
				if(throwFlag) {
					throw new Exception("创建文件" + filePath + "失败");
				}
			}
			oFile.
			oStream = new FileOutputStream(
					Thread.currentThread().getContextClassLoader().getResource("") + fileName
				);*/
			writer = new XMLWriter(oStream, outFormat);
		} catch (FileNotFoundException e) {
			System.out.println("------Error:Can not find xml file '" + fileName + "'!");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			writer = new XMLWriter();
			System.out.println("------Info:Create default XMLWriter Object without OutputFormat");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("------Info:Create output xml file failed");
		}
		
		return writer;
	}
	
	protected boolean writeXml(XMLWriter writer, Document document) {
		if(writer == null || document == null) {
			System.out.println("------Error:write xml file failed!writer or document is null");
			return false;
		}
		
		try {
			writer.write(document);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}