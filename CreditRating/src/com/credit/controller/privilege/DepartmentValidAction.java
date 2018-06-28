package com.credit.controller.privilege;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.vo.privilege.Permission;
import com.credit.service.privilege.DepartmentService;


@Controller
@RequestMapping("/control/department/valid")
public class DepartmentValidAction {

	private static final Logger logger = Logger
			.getLogger(DepartmentValidAction.class);

	@Resource(name = "departmentServiceBean")
	private DepartmentService departmentService;
	/**
	 * 
	 * @title 添加部门时，部门名称验证
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "department", privilegeValue = "valid")
	@RequestMapping("/depNameExist")
	@ResponseBody
	public Map<String, Object> depNameExist(String name) throws Exception {
		logger.info( "部门名称验证depNameExist;String:"+name);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (departmentService.exist("name", name.trim())) {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "已存在！");
		} else {
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "输入合法！");
		}
		return msgMap;
	}
	/**
	 * 
	 * @title 编辑部门时，部门名验证
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "department", privilegeValue = "valid")
	@RequestMapping("/editDepNameExist")
	@ResponseBody
	public Map<String, Object> editDepNameExist(String name,String uuid) throws Exception {
		logger.info( "部门名称验证editNameExist;String:"+name);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (departmentService.exist("name", name.trim(), uuid)) {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "已存在！");
		} else {
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "输入合法！");
		}
		
		return msgMap;
	}
	/**
	 * 
	 * @title 添加部门时，邮箱验证
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "department", privilegeValue = "valid")
	@RequestMapping("/depEmailExist")
	@ResponseBody
	public Map<String, Object> depEmailExist(String email) throws Exception {
		logger.info( "部门邮箱验证depEmailExist;String:"+email);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (!Pattern.compile("^([a-z0-9A-Z]+[-|\\._]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").matcher(email).matches()) {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "格式错误！");
		} else {
			if (departmentService.exist("email", email.trim())) {
				msgMap.put("success", true);
				msgMap.put("status", false);
				msgMap.put("msg", "已存在！");
			} else {
				msgMap.put("success", true);
				msgMap.put("status", true);
				msgMap.put("msg", "输入合法！");
			}
		}
		return msgMap;
	}
	/**
	 * 
	 * @title 编辑部门时，邮箱验证
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "department", privilegeValue = "valid")
	@RequestMapping("/editDepEmailExist")
	@ResponseBody
	public Map<String, Object> editDepEmailExist(String email,String uuid) throws Exception {
		logger.info( "部门邮箱验证editDepEmailExist;String:"+email);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (!Pattern.compile("^([a-z0-9A-Z]+[-|\\._]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").matcher(email).matches()) {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "格式错误！");
		} else {
			if (departmentService.exist("email", email.trim(), uuid)) {
				msgMap.put("success", true);
				msgMap.put("status", false);
				msgMap.put("msg", "已存在！");
			} else {
				msgMap.put("success", true);
				msgMap.put("status", true);
				msgMap.put("msg", "输入合法！");
			}
		}
		return msgMap;
	}
	/**
	 * 
	 * @title 添加部门时，电话验证
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "department", privilegeValue = "valid")
	@RequestMapping("/depPhoneExist")
	@ResponseBody
	public Map<String, Object> depPhoneExist(String phone) throws Exception {
		logger.info( "部门电话验证depPhoneExist;String:"+phone);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (!Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$").matcher(phone).matches()) {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "格式错误！");
		} else {
			if (departmentService.exist("phone", phone.trim())) {
				msgMap.put("success", true);
				msgMap.put("status", false);
				msgMap.put("msg", "已存在！");
			} else {
				msgMap.put("success", true);
				msgMap.put("status", true);
				msgMap.put("msg", "输入合法！");
			}
		}
		return msgMap;
	}
	/**
	 * 
	 * @title 编辑部门时，电话验证
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "department", privilegeValue = "valid")
	@RequestMapping("/editDepPhoneExist")
	@ResponseBody
	public Map<String, Object> editDepPhoneExist(String phone,String uuid) throws Exception {
		logger.info( "部门电话验证editDepPhoneExist;String:"+phone);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (!Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$").matcher(phone).matches()) {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "格式错误！");
		} else {
			if (departmentService.exist("phone", phone.trim(), uuid)) {
				msgMap.put("success", true);
				msgMap.put("status", false);
				msgMap.put("msg", "已存在！");
			} else {
				msgMap.put("success", true);
				msgMap.put("status", true);
				msgMap.put("msg", "输入合法！");
			}
		}
		return msgMap;
	}
}
