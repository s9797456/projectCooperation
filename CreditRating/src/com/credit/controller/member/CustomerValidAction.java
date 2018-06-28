package com.credit.controller.member;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.vo.privilege.Permission;
import com.credit.service.member.CustomerService;

@Controller
@RequestMapping("/control/customer/valid")
public class CustomerValidAction{

	@Resource(name = "customerServiceBean")
	private CustomerService customerService;

	Map<String, Object> msgMap = new HashMap<String, Object>();
	/**
	 * @title 用户添加界面对 用户名 进行 重复验证   并把 英文大写字符统一转成小写字符
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "customer", privilegeValue = "valid")
	@RequestMapping("/userNameExist")
	@ResponseBody
	public Map<String, Object> userNameExist(String userName) throws Exception {
		// Thread.sleep(5000);
		if (customerService.exist("userName", userName.trim())) {
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
	 * @title 对添加用户界面 的 邮箱进行 正则验证
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "customer", privilegeValue = "valid")
	@RequestMapping("/emailExist")
	@ResponseBody
	public Map<String, Object> emailExist(String email) throws Exception {
		if (!Pattern.compile("^([a-z0-9A-Z]+[-|\\._]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").matcher(email.trim()).matches()) {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "格式错误！");
		} else {
			if (customerService.exist("email", email.trim())) {
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
	 * @title 添加用户界面  对输入的电话号进行 正则验证
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "customer", privilegeValue = "valid")
	@RequestMapping("/cellphoneExist")
	@ResponseBody
	public Map<String, Object> cellphoneExist(String cellphone) throws Exception {
		if (!Pattern.compile("^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$").matcher(cellphone.trim()).matches()) {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "格式错误！");
		} else {
			if (customerService.exist("cellphone", cellphone.trim())) {
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
	 * @title 编辑用户界面  对输入的电话号进行 正则验证
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "customer", privilegeValue = "valid")
	@RequestMapping("/editCellphoneExist")
	@ResponseBody
	public Map<String, Object> editCellphoneExist(String cellphone,String userName) throws Exception {
		if (!Pattern.compile("^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$").matcher(cellphone.trim())
				.matches()) {
				msgMap.put("success", true);
				msgMap.put("status", false);
				msgMap.put("msg", "格式错误！");
		} else {
			if (!customerService.exist("cellphone", cellphone.trim(),userName.trim())) {
				msgMap.put("success", true);
				msgMap.put("status", true);
				msgMap.put("msg", "输入合法！");
			} else {
				msgMap.put("success", true);
				msgMap.put("status", false);
				msgMap.put("msg", "已经存在！");
			}
		}
		return msgMap;
	}
	/**
	 * @title 编辑用户界面  对输入的邮箱进行 正则验证
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "customer", privilegeValue = "valid")
	@RequestMapping("/editEmailExist")
	@ResponseBody
	public Map<String, Object> editEmailExist(String email,String userName) throws Exception {
		if (!Pattern.compile("^([a-z0-9A-Z]+[-|\\._]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").matcher(email.trim()).matches()) {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "格式错误！");
		} else {
			if (!customerService.exist("email", email.trim(), userName.trim())) {
				msgMap.put("success", true);
				msgMap.put("status", true);
				msgMap.put("msg", "名称合法！");
			} else {
				msgMap.put("success", true);
				msgMap.put("status", false);
				msgMap.put("msg", "已存在！");
			}
		}
		return msgMap;
	}
	
/*	//不要任何权限
	public String findAllModels(){
		Map<String, Object> msgMap = new HashMap<String, Object>();
		msgMap.put("mapModel", modelService.findAllModels());
		this.setResponseJson(msgMap);
		return "jsonResult";
	}
	
	@Resource(name="industryServiceBean")
	private IndustryService industryService;	
	
	public String findRootName(){
		Map<String, Object> msgMap = new HashMap<String, Object>();
		msgMap.put("list", industryService.findAll());
		this.setResponseJson(msgMap);
		return "jsonResult";
	}*/

}
