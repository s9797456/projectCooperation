package com.credit.controller.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.enterprise.EntBaseInfo;
import com.credit.bean.enterprise.EntResult;
import com.credit.bean.member.Customer;
import com.credit.bean.member.TempRegistration;
import com.credit.bean.privilege.Organization;
import com.credit.bean.vo.member.TempRegistrationVO;
import com.credit.bean.vo.privilege.Permission;
import com.credit.bean.vo.privilege.TreeNodeVO;
import com.credit.service.enterprise.EntBaseInfoService;
import com.credit.service.member.CustomerService;
import com.credit.service.privilege.OrganizationService;
import com.credit.util.DateTime;
import com.credit.util.MD5Code;
/**
 * @title user 的CRUD操作
 * @author 孙尚飞   2017-7-31
 * @desc
 */
@Controller
@RequestMapping("/control/customer/manage")
public class CustomerManageAction {
	private static final Logger logger = Logger.getLogger(CustomerManageAction.class);
	private String moduleName = "管理员操作情况";
	private StringBuffer message = new StringBuffer("");
	private Boolean flag = true;
	Map<String, Object> msgMap = new HashMap<String, Object>();

	
	@Resource(name = "customerServiceBean")
	private CustomerService customerService;
	
	@Resource(name = "entBaseInfoServiceBean")
	private EntBaseInfoService entBaseInfoService;

	@Resource(name = "organizationServiceBean")
	private OrganizationService organizationService;
	
	/**
	 * @title 激活账户
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "customer", privilegeValue = "update")
	@RequestMapping("/updateVisible")
	@ResponseBody
	public Map<String, Object> updateVisible(String userName,int visible) throws Exception {
		Customer customer = customerService.find(userName.trim());
		message.setLength(0);
		flag=true;
		if (customer == null) {
			message.append("没有此用户");
			flag = false;
		}else{
			EntResult result=customer.getEntResult();
			if(result!=null){
				if(visible == 1&&result.getModel()==null){
					message.append("该用户没有分配行业模型");
					flag = false;
				}
			}else{
				message.append("该用户没有分配行业模型");
				flag = false;
			}
		}
		if (flag) {
			boolean judgeProccess = false; 
			EntBaseInfo ent=customer.getEntBaseInfo();
			if (visible == 1&& ent != null && ent.getProcess() == null) { 
				judgeProccess = true; 
			}
			customerService.updateVisible(judgeProccess, userName, visible, ent.getUuid());
			if (visible == 1) {
				
				/*String accesspath = SaveFilePathUtil.saveFilePathDesc("ObjectURL");
				String orgUrl = organizationService.findByName(user
						.getBelongorg());
				if (orgUrl == null) {
					accesspath = "http://www.hytcredit.cn/";
				} else {
					accesspath = orgUrl;
				}
				String[] str = { member.getEmail() };
				EmailSender.send(str, "新用户注册成功", "恭喜！您的已通过审核<br>点击链接登录<br>"
						+ accesspath, null, "text/html");*/
				message.append("用户已激活");
			} else {
				message.append("用户已锁定");
			}
			logger.info(moduleName + "[管理员状态设置:" + message + "]");
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", message.toString());

		} else {
			logger.error(moduleName + "[管理员状态设置:" + message + "]");
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "用户状态更改失败");
			msgMap.put("result", message.toString());
		}
		return msgMap;
	}

	/**
	 * @title 添加客户
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "customer", privilegeValue = "insert")
	@RequestMapping("/addCustomer")
	@ResponseBody
	public Map<String, Object> addCustomer(HttpServletRequest request,Customer customer) throws Exception {
		message.setLength(0);
		flag=true;
		if (customer.getUserName().trim().equals(customer.getPassword().trim())) {
			message.append("密码不能与账号相同");
			flag = false;
		}
		if (customerService.exist("userName", customer.getUserName().trim())) {
			message.append("用户已存在");
			flag = false;
		}
		if (!(customer.getEmail().trim().equals(""))) {
			if (this.customerService.exist("email", customer.getEmail().trim())) {
				message.append("Email已存在");
				flag = false;
			}
		}
		if (!(customer.getCellphone().trim().equals(""))) {
			if (this.customerService.exist("cellphone", customer.getCellphone().trim())) {
				message.append("联系电话已存在");
				flag = false;
			}
		}
		if (flag) {
			message.append("<p>操作成功</p>");
			MD5Code md5 = new MD5Code();
			customer.setPassword(md5.getMD5ofStr(customer.getPassword().trim()));
			customer.setLastLoginIP("192.168.1.1");
			customer.setLastLoginTime(DateTime.getCurrentTimeStamp());
			customer.setLoginTimes(0);
			customer.setRegIP(request.getRemoteAddr());
			customer.setRegTime(DateTime.getCurrentTimeStamp());
			customer.setUpdateTime(DateTime.getCurrentTimeStamp());
			customer.setVisible(0);
			customer.setType(0);

			customerService.save(customer);
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "添加客户成功");
			logger.info(moduleName + "[管理员新增:" + message + "]");
			logger.info("添加成功");

		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "添加客户失败");
			logger.error(moduleName + "[管理员新增:" + message + "]");
		}
		return msgMap;

	}

	
	/**
	 * @title 删除客户
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "customer", privilegeValue = "delete")
	@RequestMapping("/deleteCustomer")
	@ResponseBody
	public Map<String, Object> deleteCustomer(String userName) throws Exception {
		Customer customer=customerService.find(userName.trim());
		message.setLength(0);
		flag=true;
		if(customer==null){
			flag=false;
			message.append("该客户不存在，请刷新重试");
		}else{
			if(customer.getEntResult()!=null){
				flag=false;
				message.append("无法删除,请先解除关联关系");
			}
		}
		if (flag) {
			message.append("操作成功");
			customerService.delete(userName);
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "删除用户成功");
			logger.info("删除用户成功");
			logger.info(moduleName + "[管理员删除:" + message + "]");
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "删除用户失败");
			msgMap.put("result", message.toString());
			logger.error(moduleName + "[管理员删除:" + message + "]");
		}
		return msgMap;
	}
	
	
	/**
	 * @title 编辑客户界面
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "customer", privilegeValue = "update")
	@RequestMapping("/editCustomerUI")
	@ResponseBody
	public Map<String, Object> editCustomerUI(String userName) throws Exception {
		Customer customer = customerService.find(userName.trim());
		if (customer != null) {
			msgMap.put("status", true);
		} else {
			msgMap.put("status", false);
			msgMap.put("msg", "没有此用户");
		}
		return msgMap;

	}
	/**
	 * @title 编辑客户
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "customer", privilegeValue = "update")
	@RequestMapping("/editCustomer")
	@ResponseBody
	public Map<String, Object> editCustomer(Customer customer) throws Exception {
		message.setLength(0);
		flag=true;
		Customer c = customerService.find(customer.getUserName());
		if(c != null){
			if (customer.getUserName() == null || customer.getUserName().equals("")) {
				message.append("<p>用户名不能为空</p>");
				flag = false;
			}
			if (!(customer.getEmail() == null || customer.getEmail() .equals(""))) {
				if (this.customerService.exist("email", customer.getEmail() , customer.getUserName())) {
					message.append("<p>Email已存在</p>");
					flag = false;
				}
			}
			if (!(customer.getCellphone() == null || customer.getCellphone() .equals(""))) {
				if (this.customerService.exist("cellphone", customer.getCellphone(),customer.getUserName())) {
					message.append("<p>电话已存在</p>");
					flag = false;
				}
			}
		}else{
			message.append("<p>暂无该用户信息</p>");
			flag = false;
		}

		if (flag) {
			message.append("<p>操作成功</p>");
			c.setUpdateTime(DateTime.getCurrentTimeStamp());
			c.setCellphone(customer.getCellphone());
			c.setEmail(customer.getEmail());
			c.setRealName(customer.getRealName());
			c.setUserName(customer.getUserName());
			customerService.update(c);

			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "修改用户成功");
			logger.info(moduleName + "[管理员编辑:" + message + "]");
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", message.toString());
			logger.info(moduleName + "[管理员编辑:" + message + "]");
		}

		return msgMap;
	}
	/**
	 * @title 初始化密码
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "customer", privilegeValue = "initPassword")
	@RequestMapping("/initPWD")
	@ResponseBody
	public Map<String, Object> initPWD(String userName) throws Exception {
		message.setLength(0);
		flag=true;
		Customer customer = customerService.find(userName.trim());
		if (customer == null) {
			message.append("<p>没有此用户</p>");
			flag = false;
		} 
		if (flag) {
			MD5Code md5 = new MD5Code();
			customer.setPassword(md5.getMD5ofStr("123456"));
			customerService.update(customer);
			message.append("<p>用户的初始化成功，密码初始化为123456</p>");
		}
		msgMap.put("success", true);
		msgMap.put("msg",message.toString());
		return msgMap;
	}
	
	/**
	 * @title 分配机构
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "customer", privilegeValue = "appointOrganization")
	@RequestMapping("/appointOrganizationUI")
	@ResponseBody
	public Map<String, Object> appointOrganizationUI(String userName) throws Exception {
		message.setLength(0);
		flag=true;
		Customer customer = customerService.find(userName.trim());
		if (customer == null) {
			message.append("<p>没有此用户</p>");
			flag = false;
		}
		if (flag) {
			Customer cus= customerService.find(userName.trim());
			Organization organization = cus.getOrganization();
			if( organization == null){
				msgMap.put("organizationName", "");
			}else{
				msgMap.put("organizationName", organization.getName());
			}
			List<Organization> organizations = organizationService.getScrollData().getResultlist();
			List<TreeNodeVO> treeNodeVOs = new ArrayList<TreeNodeVO>();
			if(organizations != null && organizations.size()>0){
				for (Organization organization2 : organizations) {
					TreeNodeVO tnv = new TreeNodeVO();
					tnv.setId(organization2.getUuid());
					tnv.setText(organization2.getName());
					tnv.setLeaf(true);
					treeNodeVOs.add(tnv);
				}
			}
			msgMap.put("organizations", treeNodeVOs);
		}
		msgMap.put("success", true);
		msgMap.put("msg",message.toString());
		return msgMap;
	}
	
	@Permission(model = "customer", privilegeValue = "appointOrganization")
	@RequestMapping("/appointOrganization")
	@ResponseBody
	public Map<String, Object> appointOrganization(TreeNodeVO tnv) throws Exception {
		message.setLength(0);
		flag=true;
		if (tnv == null) {
			message.append("<p>参数错误</p>");
			flag = false;
		}
		if (flag) {
			Customer cus= customerService.find(tnv.getpId());
			Organization  organization = organizationService.find(tnv.getId());
			if(organization.getName().equals(tnv.getText())){
				cus.setOrganization(organization);
				customerService.update(cus);
				Set<Customer> customerSet = organization.getCustomers();
				customerSet.add(cus);
				organization.setCustomers(customerSet);
				organizationService.update(organization);
				msgMap.put("success", true);
				msgMap.put("status", true);
				msgMap.put("msg", "添加成功");
			}else{
				msgMap.put("success", true);
				msgMap.put("status", false);
				msgMap.put("msg", "添加失败");
			}
		}else{
			msgMap.put("success", false);
			msgMap.put("status", false);
			msgMap.put("msg",message.toString());
		}
		return msgMap;
	}
}
