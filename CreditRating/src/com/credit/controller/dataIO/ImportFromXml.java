package com.credit.controller.dataIO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.credit.bean.member.User;
import com.credit.bean.privilege.Menu;
import com.credit.bean.privilege.Department;
import com.credit.bean.privilege.PrivilegeGroup;
import com.credit.bean.privilege.SystemPrivilege;
import com.credit.bean.privilege.SystemPrivilegePK;
import com.credit.bean.vo.member.Gender;
import com.credit.service.member.UserService;
import com.credit.service.privilege.MenuService;
import com.credit.service.privilege.DepartmentService;
import com.credit.service.privilege.PrivilegeGroupService;
import com.credit.service.privilege.SystemPrivilegeService;


@Controller
@RequestMapping("/initial/import")
public class ImportFromXml {
	
	private static final Logger logger = Logger.getLogger(ImportFromXml.class);
	
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
		return "Initial/importUI";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/importData")
	public String importData(HttpServletRequest request) {
		
		String requestUrl = request.getContextPath();
		
		try{
			Document document = new SAXReader().read(
					Thread.currentThread().getContextClassLoader().getResourceAsStream("com/config/init_datas.xml")
			);
			logger.info("【开始导入权限模块】");
			importPermissions(document.selectNodes("//Permissions/Permission"));
			logger.info("【结束导入权限模块】");
			logger.info("【开始导入菜单模块】");
			importMenus(document.selectNodes("//Menus/Menu"), null);
			logger.info("【结束导入菜单模块】");
			/*logger.info("【开始导入角色模块】");
			importRoles(document.selectNodes("//Roles/Role"));
			logger.info("【结束导入角色模块】");
			logger.info("【开始导入部门模块】");
			importDepartments(document.selectNodes("//Departments/Dep"), null);
			logger.info("【结束导入部门模块】");

			logger.info("【开始导入用户模块】");
			//importUsers(document.selectNodes("//Users/User"));
			importUsersByRecord(document.selectNodes("//Users/User"));
			logger.info("【结束导入用户模块】");*/

			logger.info("\n初始化数据成功！\n");
			this.resultStr = "<span style=\"color:green;\">成功</span>";
			this.tips = "请<a href=\"" + requestUrl + "\">登录</a>";
		}catch(Exception e) {
			e.printStackTrace();
			this.resultStr = "<span style=\"color:red;\">失败</span>";
		}
		request.setAttribute("resultStr", resultStr);
		request.setAttribute("tips", tips);
		return "Initial/importResult";
	}
	
	protected void importPermissions(List<SystemPrivilege> lists){
		if (systemPrivilegeService.getCount() == 0) {
			List<SystemPrivilege> systemPrivileges = new ArrayList<SystemPrivilege>();
			for (Iterator<SystemPrivilege> iter = lists.iterator(); iter.hasNext();) {
				Element element = (Element) iter.next();
				SystemPrivilege systemPrivilege = new SystemPrivilege();
				systemPrivilege.setName(element.attributeValue("name"));
				systemPrivilege.setId(new SystemPrivilegePK(element.attributeValue("model"),element.attributeValue("privilegeValue")));
				systemPrivileges.add(systemPrivilege);
				logger.info("导入权限模块["+element.attributeValue("name")+"]");
			}
			systemPrivilegeService.batchSave(systemPrivileges);	
		}else{
			logger.error("---导入权限模块失败:已存在数据---");
		}
		logger.info("+++导入权限模块成功+++");
	}
	
	
	@SuppressWarnings("unchecked")
	protected void importMenus(List<Menu> lists,Menu parent){
		try{
			for (Iterator<Menu> iter = lists.iterator(); iter.hasNext();) {
				Element element = (Element) iter.next();
				Menu menu = new Menu();
				menu.setName(element.attributeValue("name"));
				menu.setUrl(element.attributeValue("url"));
				menu.setOrderNO(Integer.parseInt(element.attributeValue("orderNO")));
				menu.setTarget(element.attributeValue("target"));
				menu.setRel(element.attributeValue("rel"));
				menu.setImgUrl(element.attributeValue("imgUrl"));
				menuService.addMenu(menu,parent == null?null:parent.getUuid());
				
				List<SystemPrivilege> permissions = element.selectNodes("Permission");
				Set<SystemPrivilege> systemPrivileges = new HashSet<SystemPrivilege>();;
				for (Iterator<SystemPrivilege> iterator = permissions.iterator(); iterator.hasNext();) {
					Element pElem = (Element) iterator.next();
					SystemPrivilege sp = new SystemPrivilege();
					sp.setName(pElem.attributeValue("name"));
					sp.setId(new SystemPrivilegePK(pElem.attributeValue("model"),pElem.attributeValue("privilegeValue")));
					systemPrivileges.add(sp);
				}
				menu.setSystemPrivileges(systemPrivileges);
				menuService.update(menu);
				logger.info("导入菜单模块["+element.attributeValue("name")+"]");
				importMenus(element.selectNodes("Menu") , menu);
			}
			logger.info("+++导入菜单模块成功+++");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	protected void importRoles(List<PrivilegeGroup> lists){
		for (Iterator<PrivilegeGroup> iter = lists.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			PrivilegeGroup pg = new PrivilegeGroup();
			pg.setName(element.attributeValue("name"));
			privilegeGroupService.save(pg);
			
			List<SystemPrivilege> permissions = element.selectNodes("Permission");
			Set<SystemPrivilege> systemPrivileges = new HashSet<SystemPrivilege>();;
			for (Iterator<SystemPrivilege> iterator = permissions.iterator(); iterator.hasNext();) {
				Element spElem = (Element) iterator.next();
				SystemPrivilege sp = new SystemPrivilege();
				sp.setName(spElem.attributeValue("name"));
				sp.setId(new SystemPrivilegePK(spElem.attributeValue("model"),spElem.attributeValue("privilegeValue")));
				systemPrivileges.add(sp);
			}
			pg.setSystemPrivileges(systemPrivileges);
			privilegeGroupService.update(pg);
		}
		

	}
	
	@SuppressWarnings("unchecked")
	protected void importDepartments(List<Department> lists,Department parent){
		
		for (Iterator<Department> iter = lists.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			Department dep = new Department();
			dep.setName(element.attributeValue("name"));
			dep.setSn(element.attributeValue("id"));
			departmentService.addDep(dep,parent == null?null:parent.getUuid());
			
			//List<PrivilegeGroup> elements = element.selectNodes("Roles/Role");
			List<PrivilegeGroup> elements = element.selectNodes("Role");
			Set<PrivilegeGroup> privilegeGroups = new HashSet<PrivilegeGroup>();;
			for (Iterator<PrivilegeGroup> iterator = elements.iterator(); iterator.hasNext();) {
				Element pgElem = (Element) iterator.next();
				PrivilegeGroup pg = privilegeGroupService.searchByName(pgElem.attributeValue("name"));
				privilegeGroups.add(pg);
			}
			dep.setPrivilegeGroups(privilegeGroups);
			departmentService.update(dep);
			logger.info("导入部门模块["+element.attributeValue("name")+"]");
			importDepartments(element.selectNodes("Dep") , dep);
		}
		logger.info("+++导入部门模块成功+++");
	}
	
	/////////// 根据导出的XML文件初始化用户模块 /////////////
	@SuppressWarnings("unchecked")
	protected void importUsersByRecord(List<User> lists){
		for (Iterator<User> iter = lists.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			logger.info("导入用户：" + element.attributeValue("userName"));
			
			User user = new User();
			user.setUserName(element.attributeValue("userName"));
			user.setPassword(element.attributeValue("password"));
			user.setRealName(element.attributeValue("realName"));
			user.setRegIP(element.attributeValue("regIP"));
			//user.setCellphone(element.attributeValue("cellphone"));
			//user.setEmail(element.attributeValue("email"));
			
			user.setGender(Gender.getGender(element.attributeValue("gender")));
			//user.setImgUrl(element.attributeValue("imgUrl"));
			user.setVisible(Integer.valueOf(element.attributeValue("visible")));
			
			
			String timeStr = element.attributeValue("regTime");
			String[] timeStrSplit = timeStr.split("\\-");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.parseInt(timeStrSplit[0]), Integer.parseInt(timeStrSplit[1]) - 1, Integer.parseInt(timeStrSplit[2]));
			user.setRegTime(calendar.getTime());
			
			Set<PrivilegeGroup> groups = new HashSet<PrivilegeGroup>();
			for(Object roleNode : element.elements("Role")) {
				Element elemRole = (Element)roleNode;
				PrivilegeGroup pg = privilegeGroupService.searchByName(elemRole.attributeValue("name"));
				if(pg != null) {
					groups.add(pg);
				}
			}
			user.setGroups(groups);
			
			Set<Department> departments = new HashSet<Department>();
			List<Department> depList = departmentService.getScrollData().getResultlist();
			for(Object depNode : element.elements("Dep")) {
				Element elemDep = (Element)depNode;
				for(Department dep : depList) {
					if(dep.getSn() != null && dep.getSn().equals(elemDep.attributeValue("id"))) {
						departments.add(dep);
						break;
					}
				}
			}
			user.setDepartments(departments);
			
			Set<SystemPrivilege> systemPrivileges = new HashSet<SystemPrivilege>();
			List<SystemPrivilege> spList = systemPrivilegeService.getScrollData().getResultlist();
			for(Object spNode : element.elements("Permission")){
				Element elemSp = (Element)spNode;
				for(SystemPrivilege sp : spList){
					if(sp.getId().getModel().equals(elemSp.attributeValue("model")) 
							&& sp.getId().getPrivilegeValue().equals(elemSp.attributeValue("privilegeValue"))) {
						systemPrivileges.add(sp);
						break;
					}
				}
			}
			user.setSystemPrivileges(systemPrivileges);
			
			userService.save(user);
			
			List<PrivilegeGroup> elements = element.selectNodes("Role");
			Set<PrivilegeGroup> privilegeGroups = new HashSet<PrivilegeGroup>();;
			for (Iterator<PrivilegeGroup> iterator = elements.iterator(); iterator.hasNext();) {
				Element roleElem = (Element) iterator.next();
				PrivilegeGroup pg = privilegeGroupService.searchByName(roleElem.attributeValue("name"));
				privilegeGroups.add(pg);
			}
			user.setGroups(privilegeGroups);
			userService.update(user);
		}
	}
	
	
	
}
