package com.credit.controller.privilege;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.vo.privilege.Permission;
import com.credit.service.privilege.MenuService;



@Controller
@RequestMapping("/control/menu/valid")
public class MenuValidAction  {
	
	@Resource
	private MenuService menuService;
	private StringBuffer message = new StringBuffer("");
	private Boolean flag = true;

	private static final Logger logger = Logger.getLogger(MenuValidAction.class);

	/**
	 * 
	 * @title 添加菜单时，对菜单名进行验证
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "menu", privilegeValue = "valid")
	@RequestMapping("/targetExist")
	@ResponseBody
	public Map<String, Object> targetExist(String target,String uuid) throws Exception {
		logger.info( "标题验证targetExist;String:"+target);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("target", target);
		params.put("uuid", uuid);
		if (menuService.targetAndRelExist(params)) {
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
	 * @title 添加菜单时，对路径验证
	 * @author  孙尚飞  2017-7-25
	 * @desc
	 */
	@Permission(model = "menu", privilegeValue = "valid")
	@RequestMapping("/relExist")
	@ResponseBody
	public Map<String, Object> relExist(String rel,String uuid) throws Exception {
		logger.info( "路径验证relExist;String:"+rel);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("rel", rel);
		params.put("uuid", uuid);
		if (menuService.targetAndRelExist(params)) {
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
	
}
