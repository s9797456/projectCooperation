package com.credit.controller.member;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.vo.privilege.Permission;
import com.credit.service.member.UserService;


/**
 * @Title: 验证 用户添加界面 
 * @author Administrator @date 2017-7-24 下午2:32:07
 */
@Controller
@RequestMapping("/control/user/valid")
public class UserValidAction {
	
	@Resource
	UserService userService;
	
	Map<String, Object> msgMap = new HashMap<String, Object>();
	
	/**
	 * @Title 用户添加界面对 用户名 进行 重复验证   并把 英文大写字符统一转成小写字符
	 * @author  Administrator  @date 2017-7-24 
	 * @Description 
	 *
	 */
	@Permission(model = "user", privilegeValue = "valid")
	@RequestMapping("/usernameExist")
	@ResponseBody
	public Map<String, Object> userNameExist(@RequestParam("username")String userName) throws Exception {
		// Thread.sleep(5000);
		if(userName != null && !"".equals(userName.trim())){
			//对用户名 进行 重复验证   如存在大写字符 转成小写字符
			if (userService.exist("userName", userName.toLowerCase().trim())) {
				msgMap.put("success", true);
				msgMap.put("status", false);
				msgMap.put("msg", "已存在！");
			} else {
				msgMap.put("success", true);
				msgMap.put("status", true);
				msgMap.put("msg", "输入合法！");
			}
		}else{
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "输入不合法！");
		}
		return msgMap;
	}
	
	/**
	 * @Title 添加用户界面  对输入的电话号进行 正则验证
	 * @author  Administrator  @date 2017-7-24 
	 * @Description 
	 *
	 */
	@Permission(model = "user", privilegeValue = "valid")
	@RequestMapping("/cellphoneExist")
	@ResponseBody
	public Map<String, Object> cellphoneExist(String cellphone) throws Exception {
		if(cellphone != null && !"".equals(cellphone.trim())){
			if (!Pattern.compile("^1[358]\\d{9}$").matcher(cellphone.trim()).matches()) {
				msgMap.put("success", true);
				msgMap.put("status", false);
				msgMap.put("msg", "格式错误！");
			} else {
				if (userService.exist("cellphone", cellphone.trim())) {
					msgMap.put("success", true);
					msgMap.put("status", false);
					msgMap.put("msg", "已存在！");
				} else {
					msgMap.put("success", true);
					msgMap.put("status", true);
					msgMap.put("msg", "输入合法！");
				}
			}
		}else{
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "输入不合法！");
		}
		return msgMap;
	}
	
	/**
	 * @Title 对添加用户界面 的 邮箱进行 正则验证
	 * @author  Administrator  @date 2017-7-24 
	 * @Description 
	 *
	 */
	@Permission(model = "user", privilegeValue = "valid")
	@RequestMapping("/emailExist")
	@ResponseBody
	public Map<String, Object> emailExist(String email) throws Exception {
		if (!Pattern.compile("^([a-z0-9A-Z]+[-|\\._]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").matcher(email.trim()).matches()) {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "格式错误！");
		} else {
			if (!userService.exist("email", email.trim())) {
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
}
