package com.credit.controller.privilege;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.member.Customer;
import com.credit.bean.privilege.Organization;
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.member.CustomerService;
import com.credit.service.privilege.OrganizationService;
import com.credit.util.DateTime;
import com.credit.util.MD5Code;
import com.credit.util.WebUtil;

@Controller
@RequestMapping("/control/organization/manage")
public class OrganizationManageAction {

	private static final Logger logger = Logger.getLogger(OrganizationManageAction.class);
	
	private String moduleName = "管理员操作情况";
	
	private StringBuffer message = new StringBuffer("");
	
	@Resource(name = "organizationServiceBean")
	private OrganizationService organizationService;
	
	@Resource(name = "customerServiceBean")
	private CustomerService customerService;

	
	/**
	 * @Title  添加机构
	 * @author  Administrator  @date 2017-10-24 
	 * @Description 
	 *
	 */
	@Permission(model = "organization", privilegeValue = "insert")
	@RequestMapping("/addOrganization")
	@ResponseBody
	public Map<String, Object> addOrganization(Organization o,Customer c,HttpServletRequest request) throws Exception {
		logger.info( "添加机构addOrganization;Organization:"+o);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		boolean flag = true;
		message.setLength(0);
		if (o.getName().trim().toLowerCase().equals("")) {
			message.append("请输入机构名");
			flag = false;
		}
		if (o.getTwoDomainNames().trim().equals("")) {
			message.append("请输入二级域名");
			flag = false;
		}
		if (o.getPhone().trim().equals("")) {
			message.append("请输入联系方式");
			flag = false;
		}
		if (c.getUserName().trim().toLowerCase().equals("")) {
			message.append("请输入登陆账号");
			flag = false;
		}
		if (c.getPassword().trim().equals("")) {
			message.append("请输入登陆密码");
			flag = false;
		}
		if (organizationService.exist("name", o.getName().trim().toLowerCase())) {
			message.append("该机构名已存在");
			flag = false;
		}
		if (organizationService.exist("twoDomainNames", o.getTwoDomainNames().trim())) {
			message.append("二级域名已存在");
			flag = false;
		}
		if (organizationService.exist("phone", o.getPhone().trim())) {
			message.append("联系方式已存在");
			flag = false;
		}
		if (customerService.exist("cellphone", o.getPhone().trim())) {
			message.append("联系方式已存在");
			flag = false;
		}
		if (customerService.exist("userName", c.getUserName().trim().toLowerCase())) {
			message.append("登陆账号已存在");
			flag = false;
		}
		if (flag) {
			message.append("操作成功");
			o.setUuid(UUID.randomUUID().toString().replace("-", ""));
			String oid = o.getUuid();
			o.setVisible(1);
			organizationService.save(o);
			
			Customer customer = new Customer();
			MD5Code md5 = new MD5Code();
			customer.setUserName(c.getUserName().trim().toLowerCase());
			customer.setPassword(md5.getMD5ofStr(c.getPassword().trim()));
			customer.setCellphone(o.getPhone().trim().toLowerCase());
			customer.setEmail(c.getEmail());
			customer.setOrganization(o);
			customer.setType(c.getType());
			customer.setVisible(1);
			customer.setRegIP( WebUtil.getIpAddress(request));
			customer.setRegTime(DateTime.getCurrentTimeStamp());
			customerService.save(customer);
			
			Set<Customer> cusSet = new HashSet<Customer>();
			cusSet.add(customer);
			Organization organization = organizationService.find(oid);
			organization.setCustomers(cusSet);
			organizationService.update(organization);
			
			logger.info(moduleName + "[机构新增:" + message + "]");
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", message.toString());
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", message.toString());
			logger.info(moduleName + "[机构新增:" + message + "]");
		}

		return msgMap;
	}

	/**
	 * @Title 编辑更新机构
	 * @author  Administrator  @date 2017-10-25 
	 * @Description 
	 *
	 */
	@Permission(model = "organization", privilegeValue = "update")
	@RequestMapping("/editOrganization")
	@ResponseBody
	public Map<String, Object> editOrganization(Organization o,Customer c) throws Exception {
		logger.info( "编辑机构deleteOrganization;Organization:"+o);
		boolean flag = true;
		Map<String, Object> msgMap = new HashMap<String, Object>();
		message.setLength(0);
		Organization organization = organizationService.find(o.getUuid());
		if (organization == null) {
			message.append("没有查到实体");
			flag = false;
		}
		if (o.getTwoDomainNames().trim().equals("")) {
			message.append("请输入机构域名");
			flag = false;
		}
		if (o.getPhone().trim().equals("")) {
			message.append("请输入联系电话");
			flag = false;
		}

		if (flag) {
			message.append("操作成功");
			Organization organization2 = organizationService.find(organization.getUuid());
			o.setCustomers(organization2.getCustomers());
			Customer cus = customerService.find(c.getUserName());
			cus.setCellphone(o.getPhone());
			cus.setEmail(o.getEmail());
			customerService.update(cus);
			organizationService.update(o);
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", message.toString());

			logger.info(moduleName + "[机构编辑:" + message + "]");
		} else {

			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", message.toString());
			logger.info(moduleName + "[机构编辑:" + message + "]");
		}

		return msgMap;
	}
	/**
	 * 
	 * @title 删除机构
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "organization", privilegeValue = "delete")
	@RequestMapping("/deleteOrganization")
	@ResponseBody
	public Map<String, Object> deleteOrganization(String uuid,int type) throws Exception {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		message.setLength(0);
		boolean flag = true;
		Organization organization = organizationService.find(uuid);
		if (organization == null) {
			message.append("参数错误");
			flag = false;
		}
		if (type == 0) {
			message.append("未查询到该机构相关登录信息");
			flag = false;
		}

		if (flag) {
			List<Customer> customers = customerService.selectByTypeAndOrg(uuid, null);
			for (Customer customer2 : customers) {
				customer2.setOrganization(null);
				if(customer2.getType() != 0){
					customerService.delete(customer2.getUserName());
				}else{
					customerService.update(customer2);
				}
			}
			organizationService.delete((Serializable) uuid.trim());
			message.append("操作成功");
			logger.info(moduleName + "[机构删除:" + message + "]");
			msgMap.put("success", true);
			msgMap.put("msg", message.toString());
		} else {
			logger.info(moduleName + "[机构删除:" + message + "]");
			msgMap.put("success", false);
			msgMap.put("msg", message.toString());
		}
		return msgMap;
	}

	/**
	 * @Title 机构状态更改
	 * @author  Administrator  @date 2017-10-25 
	 * @Description 
	 *
	 */
	@Permission(model = "organization", privilegeValue = "update")
	@RequestMapping("/updateVisible")
	@ResponseBody
	public Map<String, Object> updateVisible(String uuid,int visible,int type) throws Exception {
		logger.info( "机构状态更改 updateVisible;String:"+uuid+"visible:"+visible);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		message.setLength(0);
		boolean flag = true;
		Organization organization = organizationService.find(uuid);
		if (organization == null) {
			message.append("参数错误");
			flag = false;
		}
		if (type == 0) {
			message.append("未查询到该机构相关登录信息");
			flag = false;
		}
		List<Customer> customers = customerService.selectByTypeAndOrg(uuid, type);
		if (customers == null) {
			message.append("未查询到该机构相关登录信息");
			flag = false;
		}

		if (flag) {
			organizationService.updateVisible(uuid, visible);
			if(customers != null  && customers.size()>0 && customers.get(0).getType() != 0){
				Customer customer = customers.get(0);
				customer.setVisible(visible);
				customerService.update(customer);
			}
			if (visible== 1) {
				message.append("激活操作成功");
			} else {
				message.append("锁定操作成功");
			}
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "机构状态更改成功，"+message);
			logger.info(moduleName + "[机构状态设置:" + message + "]");
			return msgMap;
		} else {
			logger.info(moduleName + "[机构状态更改:" + message + "]");
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", message.toString());
			return msgMap;
		}
	}
	
}
