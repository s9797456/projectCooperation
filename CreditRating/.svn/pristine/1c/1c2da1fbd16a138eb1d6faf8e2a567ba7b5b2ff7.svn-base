package com.credit.controller.privilege;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.member.User;
import com.credit.bean.pagelist.PageView;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.privilege.Department;
import com.credit.bean.privilege.PrivilegeGroup;
import com.credit.bean.vo.member.UserVO;
import com.credit.bean.vo.privilege.DepartmentVO;
import com.credit.bean.vo.privilege.Permission;
import com.credit.bean.vo.privilege.PrivilegeGroupVO;
import com.credit.service.member.UserService;
import com.credit.service.privilege.PrivilegeGroupService;
import com.credit.util.tree.SimpleTreeNode;

/**
 * 
 * @Title: PrivilegeGroupAction.java
 * @author Administrator @date 2017-7-24 下午4:50:54
 * @Description: TODO
 */

@Controller
@RequestMapping("/control/privilegegroup/")
public class PrivilegeGroupAction {
	
	
	private static final Logger logger = Logger.getLogger(PrivilegeGroupAction.class);
	
	private Map<String, Object> msgMap = new HashMap<String, Object>();
	private String moduleName = "管理员操作情况";
	
	
	@Resource(name = "privilegeGroupServiceBean")
	private PrivilegeGroupService privilegeGroupService;

	/**
	 * @Title 获取角色列表
	 * @author  Administrator  @date 2017-7-26 
	 * @Description 
	 *
	 */
	@Permission(model = "privilegeGroup", privilegeValue = "list")
	@RequestMapping("/listUI")
	@ResponseBody
	public Map<String, Object> listUI(String userName,int page,int start,int limit) throws Exception {
		logger.info(moduleName + "[查看角色列表]");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		PageView<PrivilegeGroup> pageView = null;
		pageView = new PageView<PrivilegeGroup>(limit, page);
		// 根据用户名进行模糊查询
		if (userName != null && !"".equals(userName.trim())) {
			if (params.size() > 0)
				jpql.append(" and ");
			jpql.append(" o.name like ?" + (params.size() + 1));
			params.add("%" + userName.trim() + "%");
		}
		QueryResult<PrivilegeGroup> qr = privilegeGroupService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(),jpql.toString(), params.toArray());
		pageView.setQueryResult(qr);
		
		List<PrivilegeGroupVO> list = new ArrayList<PrivilegeGroupVO>();
		for (PrivilegeGroup privilegeGroup : qr.getResultlist()) {
			PrivilegeGroupVO pri = new PrivilegeGroupVO();
			pri.setUuid(privilegeGroup.getUuid());
			pri.setName(privilegeGroup.getName());
			list.add(pri);
		}
		msgMap.put("privilegeGroups", list);
		msgMap.put("total",  qr.getTotalrecord());
		return msgMap;
	}
}
