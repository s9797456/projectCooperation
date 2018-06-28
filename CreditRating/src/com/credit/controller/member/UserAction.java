package com.credit.controller.member;

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
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.member.UserService;

@Controller
@RequestMapping("/control/user")
public class UserAction {
	private static final Logger logger = Logger.getLogger(UserAction.class);
	private String moduleName = "管理员操作情况";

	@Resource(name = "userServiceBean")
	private UserService userService;

	@Permission(model = "user", privilegeValue = "list")
	@RequestMapping("/listUI")
	@ResponseBody
	public Map<String, Object> listUI(String userName,int page,int start,int limit) throws Exception {
		logger.info(moduleName + "[查看用户列表]");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		List<Object> responseJson = new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("regTime", "desc");
		orderby.put("userName", "asc");
		PageView<User> pageView = null;
		pageView = new PageView<User>(limit, page);
		// 根据用户名进行模糊查询
		if (userName != null && !"".equals(userName.trim())) {
			if (params.size() > 0)
				jpql.append(" and ");
			jpql.append(" o.userName like ?" + (params.size() + 1));
			params.add("%" + userName.trim() + "%");
		}
		QueryResult<User> qr = userService.getScrollData(
				pageView.getFirstResult(), pageView.getMaxresult(),
				jpql.toString(), params.toArray(), orderby);
		pageView.setQueryResult(qr);
		List<User> users = qr.getResultlist();
		for (User u : users) {
			UserVO uv=new UserVO();
			uv.setUsername(u.getUserName());
			uv.setCellphone(u.getCellphone());
			uv.setEmail(u.getEmail());
			uv.setLastloginip(u.getLastLoginIP());
			uv.setPassword(u.getPassword());
			uv.setRealname(u.getRealName());
			uv.setRegip(u.getRegIP());
			
			uv.setLogintimes(u.getLoginTimes().toString());
			if (u.getVisible().intValue() == 1) {
				uv.setVisible("正常" + " | " + "锁定");
			} else {
				uv.setVisible("关闭" + " | " + "激活");
			}
			
			if(u.getLastLoginTime()!=null&&!"".equals(u.getLastLoginTime())){
				uv.setLastlogintime(sdf.format(u.getLastLoginTime()));
			}else{
				uv.setLastlogintime("");
			}
			if(u.getRegTime()!=null&&!"".equals(u.getRegTime())){
				uv.setRegtime(sdf.format(u.getRegTime()));
			}else{
				uv.setRegtime("");
			}
			if(u.getUpdateTime()!=null&&!"".equals(u.getUpdateTime())){
				uv.setUpdatetime(sdf.format(u.getUpdateTime()));
			}else{
				uv.setUpdatetime("");
			}
			if (u.getGroups().size() == 0) {
				uv.setGroups("");
			} else {
				StringBuffer sb = new StringBuffer();
				Set<PrivilegeGroup> groups = u.getGroups();
				for (PrivilegeGroup group : groups) {
					sb.append(group.getName()).append(" ");
				}
				uv.setGroups(sb.toString());
			}
			if(u.getDepartments().size() ==0){
				uv.setDepname("");
			}else{
				StringBuffer sb = new StringBuffer("");
				Set<Department> departments = u.getDepartments();
				for(Department dep : departments){
					sb.append(dep.getName());
				}
				uv.setDepname(sb.toString());
			}
			responseJson.add(uv);
		}
		msgMap.put("users", responseJson);
		msgMap.put("total",  qr.getTotalrecord());
		return msgMap;
	}
}
