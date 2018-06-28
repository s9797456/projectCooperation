package com.credit.controller.privilege;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.service.member.CustomerService;
import com.credit.service.privilege.OrganizationService;


@Controller
@RequestMapping("/control/organization/valid")
public class OrganizationValidAction {

	private static final Logger logger = Logger
			.getLogger(OrganizationValidAction.class);

	@Resource(name = "organizationServiceBean")
	private OrganizationService organizationService;
	@Resource(name = "customerServiceBean")
	private CustomerService customerService;
	
	/**
	 * @Title 添加机构时，登录帐号验证
	 * @author  Administrator  @date 2017-10-25 
	 * @Description 
	 *
	 */
	@RequestMapping("/orgUserNameExist")
	@ResponseBody
	public Map<String, Object> orgUserNameExist(String userName) throws Exception {
		logger.info( "添加 -- 机构名称验证orgUserNameExist;String:"+userName);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (customerService.exist("userName", userName.trim().toLowerCase())) {
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
	 * @title 添加机构时，机构名称验证
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@RequestMapping("/orgNameExist")
	@ResponseBody
	public Map<String, Object> orgNameExist(String name) throws Exception {
		logger.info( "添加 -- 机构名称验证orgNameExist;String:"+name);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (organizationService.exist("name", name.trim().toLowerCase())) {
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
	 * @title 编辑机构时，机构名验证
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@RequestMapping("/editOrgNameExist")
	@ResponseBody
	public Map<String, Object> editOrgNameExist(String name,String uuid) throws Exception {
		logger.info( "编辑 -- 机构名称 验证editNameExist;String:"+name);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (organizationService.exist("name", name.trim().toLowerCase())) {
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
	 * @Title 添加机构时，二级域名验证
	 * @author  Administrator  @date 2017-10-25 
	 * @Description 
	 *
	 */
	@RequestMapping("/orgTwoDomainNamesExist")
	@ResponseBody
	public Map<String, Object> orgTwoDomainNamesExist(String twoDomainNames) throws Exception {
		logger.info( "添加 -- 机构二级域名验证orgTwoDomainNamesExist;String:"+twoDomainNames);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (organizationService.exist("twoDomainNames", twoDomainNames.trim().toLowerCase())) {
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
	 * @Title 编辑机构时，二级域名验证
	 * @author  Administrator  @date 2017-10-25 
	 * @Description 
	 *
	 */
	@RequestMapping("/editOrgTwoDomainNamesExist")
	@ResponseBody
	public Map<String, Object> editOrgTwoDomainNamesExist(String twoDomainNames,String uuid) throws Exception {
		logger.info( "编辑 -- 机构二级域名 验证orgTwoDomainNamesExist;String:"+twoDomainNames+"uuid"+uuid);
		Map<String, Object> msgMap = new HashMap<String, Object>();
			if (organizationService.exist("twoDomainNames", twoDomainNames.trim().toLowerCase(),uuid)) {
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
	 * @title 添加机构时，邮箱验证
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@RequestMapping("/orgEmailExist")
	@ResponseBody
	public Map<String, Object> orgEmailExist(String email) throws Exception {
		logger.info( "添加--机构邮箱验证orgEmailExist;String:"+email);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (!Pattern.compile("^([a-z0-9A-Z]+[-|\\._]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").matcher(email).matches()) {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "格式错误！");
		} else {
			if (organizationService.exist("email", email.trim().toLowerCase())) {
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
	 * @Title 编辑机构时，邮箱验证
	 * @author  Administrator  @date 2017-10-25 
	 * @Description 
	 *
	 */
	@RequestMapping("/editOrgEmailExist")
	@ResponseBody
	public Map<String, Object> editOrgEmailExist(String email,String uuid) throws Exception {
		logger.info( "编辑 -- 机构邮箱验证editOrgEmailExist;String:"+email);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (!Pattern.compile("^([a-z0-9A-Z]+[-|\\._]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").matcher(email).matches()) {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "格式错误！");
		} else {
			if (organizationService.exist("email", email.trim(), uuid)) {
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
	 * @title 添加机构时，电话验证
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@RequestMapping("/orgPhoneExist")
	@ResponseBody
	public Map<String, Object> orgPhoneExist(String phone) throws Exception {
		logger.info( "添加 -- 机构电话验证orgPhoneExist;String:"+phone);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (!Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$").matcher(phone).matches()) {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "格式错误！");
		} else {
			if (organizationService.exist("phone", phone.trim())) {
				msgMap.put("success", true);
				msgMap.put("status", false);
				msgMap.put("msg", "已存在！");
			} else {
				if(customerService.exist("cellphone", phone.trim())){
					msgMap.put("success", true);
					msgMap.put("status", false);
					msgMap.put("msg", "已存在！");
				}else{
					msgMap.put("success", true);
					msgMap.put("status", true);
					msgMap.put("msg", "输入合法！");
				}
			}
		}
		return msgMap;
	}
	/**
	 * 
	 * @title 编辑机构时，电话验证
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@RequestMapping("/editOrgPhoneExist")
	@ResponseBody
	public Map<String, Object> editOrgPhoneExist(String phone,String uuid,String userName) throws Exception {
		logger.info( "编辑 -- 机构电话验证editOrgPhoneExist;String:"+phone);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (!Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$").matcher(phone).matches()) {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "格式错误！");
		} else {
			if (organizationService.exist("phone", phone.trim(), uuid)) {
				msgMap.put("success", true);
				msgMap.put("status", false);
				msgMap.put("msg", "已存在！");
			} else {
				if(userName != null && !"".equals(userName.trim())){
					if(customerService.exist("cellphone", phone.trim(),userName)){
						msgMap.put("success", true);
						msgMap.put("status", false);
						msgMap.put("msg", "已存在！");
					}else{
						msgMap.put("success", true);
						msgMap.put("status", true);
						msgMap.put("msg", "输入合法！");
					}
				}else{
					msgMap.put("success", true);
					msgMap.put("status", true);
					msgMap.put("msg", "输入合法！");
				}
			}
		}
		return msgMap;
	}
}
