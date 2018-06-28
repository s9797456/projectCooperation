package com.credit.controller.privilege;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.privilege.Department;
import com.credit.bean.vo.privilege.DepartmentVO;
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.privilege.MenuService;
import com.credit.service.privilege.DepartmentService;
import com.credit.service.privilege.PrivilegeGroupService;

@Controller
@RequestMapping("/control/department/manage")
public class DepartmentManageAction {

	private static final Logger logger = Logger
			.getLogger(DepartmentManageAction.class);
	private String moduleName = "管理员操作情况";
	private StringBuffer message = new StringBuffer("");

	@Resource(name = "menuServiceBean")
	private MenuService menuService;

	@Resource(name = "departmentServiceBean")
	private DepartmentService departmentService;

	@Resource(name = "privilegeGroupServiceBean")
	private PrivilegeGroupService privilegeGroupService;
	
	
	/**
	 * 
	 * @title 添加部门界面
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "department", privilegeValue = "insert")
	@RequestMapping("/addDepartmentUI")
	@ResponseBody
	public Map<String, Object> addDepartmentUI(String parentID) throws Exception {
		logger.info( "添加部门界面addDepartmentUI;parentID:"+parentID);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		msgMap.put("parentID", parentID);
		msgMap.put("status", true);
		return msgMap;
	}
	 /**
	  * 
	  * @title 添加部门
	  * @author  孙尚飞  2017-7-25
	  * @desc
	  */
	@Permission(model = "department", privilegeValue = "insert")
	@RequestMapping("/addDepartment")
	@ResponseBody
	public Map<String, Object> addDepartment(DepartmentVO o) throws Exception {
		logger.info( "添加部门addDepartment;DepartmentVO:"+o);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		message.setLength(0);
		Boolean flag = true;
		if (o.getName().trim().equals("")) {
			message.append("请输入部门名");
			flag = false;
		}
		if (o.getSn().trim().equals("")) {
			message.append("请输入部门编码");
			flag = false;
		}
		if (departmentService.exist("sn", o.getSn().trim())) {
			message.append("部门编码已存在");
			flag = false;
		}
		String wherejpql = "o.name=?1 and o.parent ";
		Object[] queryParams;
		if (o.getParentID()==null) {
			wherejpql += "is null";
			queryParams = new Object[] { o.getName().trim() };
		} else {
			wherejpql += "=?2";
			queryParams = new Object[] { o.getName().trim(), new Department(o.getParentID()) };
		}
		long length = departmentService.getScrollData(-1, -1, wherejpql,queryParams).getTotalrecord();
		if (length > 0) {
			message.append("该目录下已有同名的部门");
			flag = false;
		}

		if (flag) {
			logger.info("操作成功");
			message.append("操作成功");
			Department department = new Department();
			if (o.getParentID()!=null){
				Department dep =  departmentService.find(o.getParentID());
				department.setParent(dep);
			}
			department.setName(o.getName().trim());
			if (o.getDescription() != null)
				department.setDescription(o.getDescription().trim());
			if (o.getPhone() != null)
				department.setPhone(o.getPhone().trim());
			if (o.getFax() != null)
				department.setFax(o.getFax().trim());
			if (o.getEmail() != null)
				department.setEmail(o.getEmail().trim());
			if (o.getSn() != null)
				department.setSn(o.getSn().trim());
			if (o.getTwoDomainNames() != null)
				department.setTwoDomainNames(o.getTwoDomainNames().trim());
			if (o.getDepUrl() != null)
				department.setDepUrl(o.getDepUrl().trim());
			if (o.getLogoImageUrl() != null)
				department.setLogoImageUrl(o.getLogoImageUrl().trim());
			if (o.getProccessImageUrl() != null)
				department.setProccessImageUrl(o.getProccessImageUrl().trim());
			departmentService.save(department);

			logger.info(moduleName + "[部门新增:" + message + "]");
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", message.toString());
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", message.toString());
			logger.info(moduleName + "[部门新增:" + message + "]");
		}

		return msgMap;
	}
	/**
	 * 
	 * @title 编辑部门
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "department", privilegeValue = "update")
	@RequestMapping("/editDepartment")
	@ResponseBody
	public Map<String, Object> editDepartment(Department o) throws Exception {
		logger.info( "编辑部门deleteDepartment;Department:"+o);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		message.setLength(0);
		Boolean flag = true;
		Department department = departmentService.find(o.getUuid());
		if (department == null) {
			message.append("没有查到实体");
			flag = false;
		}
		if (o.getName().trim().equals("")) {
			message.append("请输入部门名");
			flag = false;
		}
		if (o.getSn().trim().equals("")) {
			message.append("请输入部门编码");
			flag = false;
		}
		for (Department org : departmentService.getScrollData(-1, -1,
				"o.sn=?1", new Object[] { o.getSn().trim() }).getResultlist()) {
			if (!org.getUuid().equals(o.getUuid())) {
				message.append("部门编码已存在");
				flag = false;
				break;
			}
		}

		String wherejpql = "o.name=?1 and o.uuid<>?2 and o.parent ";
		Object[] queryParams;
		if (o.getParent()==null) {
			wherejpql += "is null";
			queryParams = new Object[] { o.getName().trim(), o.getUuid() };
		} else {
			wherejpql += "=?3";
			queryParams = new Object[] { o.getName().trim(), o.getUuid(), new Department(o.getParent().getUuid()) };
		}
		long length = departmentService.getScrollData(-1, -1, wherejpql,
				queryParams).getTotalrecord();
		if (length > 0) {
			message.append("该目录下已有同名的部门");
			flag = false;
		}

		if (flag) {
			message.append("操作成功");
			if (o.getParent()!=null)
				department.setParent(new Department(o.getParent().getUuid()));
			department.setName(o.getName().trim());
			if (o.getDescription() != null)
				department.setDescription(o.getDescription().trim());
			if (o.getPhone() != null)
				department.setPhone(o.getPhone().trim());
			if (o.getFax() != null)
				department.setFax(o.getFax().trim());
			if (o.getEmail() != null)
				department.setEmail(o.getEmail().trim());
			if (o.getSn() != null)
				department.setSn(o.getSn().trim());
			if (o.getTwoDomainNames() != null)
				department.setTwoDomainNames(o.getTwoDomainNames().trim());
			if (o.getDepUrl() != null)
				department.setDepUrl(o.getDepUrl().trim());
			if (o.getLogoImageUrl() != null)
				department.setLogoImageUrl(o.getLogoImageUrl().trim());
			if (o.getProccessImageUrl() != null)
				department.setProccessImageUrl(o.getProccessImageUrl().trim());
			departmentService.update(department);

			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", message.toString());

			logger.info(moduleName + "[部门编辑:" + message + "]");
		} else {

			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", message.toString());
			logger.info(moduleName + "[部门编辑:" + message + "]");
		}

		return msgMap;
	}
	/**
	 * 
	 * @title 删除部门
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "department", privilegeValue = "delete")
	@RequestMapping("/deleteDepartment")
	@ResponseBody
	public Map<String, Object> delete(String uuid) throws Exception {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		message.setLength(0);
		Boolean flag = true;
		Department department = departmentService.find(uuid);
		if (department == null) {
			message.append("参数错误");
			flag = false;
		}
		if (department.getChilds().size() >= 1) {
			message.append("请删除子部门");
			flag = false;
		}

		if (flag) {
			departmentService.delete((Serializable) uuid.trim());
			message.append("操作成功");
			logger.info(moduleName + "[部门删除:" + message + "]");
			msgMap.put("success", true);
			msgMap.put("msg", message.toString());
		} else {
			logger.info(moduleName + "[部门删除:" + message + "]");
			msgMap.put("success", false);
			msgMap.put("msg", message.toString());
		}
		return msgMap;
	}
	/**
	 * 
	 * @title 激活部门
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "department", privilegeValue = "update")
	@RequestMapping("/updateVisible")
	@ResponseBody
	public String updateVisible(String uuid,int visible) throws Exception {
		logger.info( "激活部门updateVisible;String:"+uuid);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		message.setLength(0);
		departmentService.updateVisible(uuid, visible);
		if (visible== 1) {
			message.append("激活操作成功");
		} else {
			message.append("锁定操作成功");
		}
		msgMap.put("success", true);
		msgMap.put("status", true);
		msgMap.put("msg", "部门状态更改成功，"+message);
		logger.info(moduleName + "[部门状态设置:" + message + "]");
		return "controlResult";
	}
   /**
    * 
    * @title 获得父部门名称
    * @author  孙尚飞  2017-7-25
    * @desc
    */
	@Permission(model = "department", privilegeValue = "list")
	@RequestMapping("/getParentDepartmentName")
	@ResponseBody
	public Map<String, Object> getParentDepartmentName(String uuid) throws Exception {
		logger.info( "得到父部门名称getParentDepartmentName;String:"+uuid);
		logger.info("===========开始获取===========");
    	Map<String, Object> msgMap = new HashMap<String, Object>();    	
		Department org=departmentService.find(uuid);
		if (org != null) {			
			String names="";
			String ownName="------"+"<a href=javascript:backParent("+"'"+org.getUuid()+"'"+") style='color:#FFF'>"+org.getName()+"</a>";
			while(org.getParent()!=null){			
				Department parentDep=departmentService.find(org.getParent().getUuid());		
				names="------"+"<a href=javascript:backParent("+"'"+parentDep.getUuid()+"'"+") style='color:#FFF'>"+parentDep.getName()+"</a>"+"------"+names;
				org=parentDep;
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
	
}
