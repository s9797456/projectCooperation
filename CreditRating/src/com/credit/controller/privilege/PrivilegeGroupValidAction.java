package com.credit.controller.privilege;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.vo.privilege.Permission;
import com.credit.service.privilege.PrivilegeGroupService;



@Controller
@RequestMapping("/control/privilegegroup/valid")
public class PrivilegeGroupValidAction {
	
	private static final Logger logger = Logger.getLogger(PrivilegeGroupValidAction.class);
	private Map<String, Object> msgMap = new HashMap<String, Object>();
	
	@Resource
	private PrivilegeGroupService groupService;
	/**
	 * @Title 添加角色 时 验证角色名是否重复
	 * @author  Administrator  @date 2017-7-25 
	 * @Description 
	 *
	 */
	@Permission(model = "privilegeGroup", privilegeValue = "valid")
	@RequestMapping("/nameExist")
	@ResponseBody
	public Map<String, Object> nameExist(String name) throws Exception {
		logger.info( "角色名称验证orgNameExist;String:"+name);
		if(name != null && !"".equals(name.trim())){
			if (groupService.exist("name",name.trim())){
				msgMap.put("success", true);
				msgMap.put("status",false);
			}else{
				msgMap.put("success", true);
				msgMap.put("status",true);
			}
		}else{
			msgMap.put("success", true);
			msgMap.put("status",false);
		}
		return msgMap;
	}
}
