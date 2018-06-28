package com.credit.controller.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.member.User;
import com.credit.bean.pagelist.PageView;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.privilege.Menu;
import com.credit.bean.privilege.Department;
import com.credit.bean.privilege.PrivilegeGroup;
import com.credit.bean.privilege.SystemPrivilege;
import com.credit.bean.privilege.SystemPrivilegePK;
import com.credit.bean.vo.member.UserPassword;
import com.credit.bean.vo.member.UserVO;
import com.credit.bean.vo.privilege.Permission;
import com.credit.bean.vo.privilege.PrivilegeGroupVO;
import com.credit.bean.vo.privilege.TreeNodeVO;
import com.credit.service.privilege.MenuService;
import com.credit.service.privilege.DepartmentService;
import com.credit.service.privilege.PrivilegeGroupService;
import com.credit.service.member.UserService;
import com.credit.util.DateTime;
import com.credit.util.EmailSender;
import com.credit.util.MD5Code;
import com.credit.util.properties.GlobalUtil;
import com.credit.util.tree.TreeNode;
import com.credit.util.WebUtil;
import com.credit.util.tree.StandardTreeNodeVo;
/**
 * @Title: UserManageAction.java
 * @author Administrator @date 2017-7-21 上午10:43:47
 * @Description: user 的CRUD操作
 */
@Controller
@RequestMapping("/control/user/manage")
public class UserManageAction {
	
	private static final Logger logger = Logger.getLogger(UserManageAction.class);
	private String moduleName = "管理员操作情况";
	private Map<String, Object> msgMap = new HashMap<String, Object>();
	private StringBuffer message = new StringBuffer("");
	private Boolean flag = true;


	//部门 的业务层
	@Resource
	private DepartmentService departmentService;
	//用户的业务层
	@Resource
	private UserService userService;
	//权限组(角色) 的业务层
	@Resource
	private PrivilegeGroupService groupService;
	//菜单  的业务层
	@Resource
	private MenuService menuService;

	/**
	 * @Title 添加用户操作
	 * @author  Administrator  @date 2017-7-21 
	 * @Description 
	 *
	 */
	@Permission(model = "user", privilegeValue = "insert")
	@RequestMapping("/addUser")
	@ResponseBody
	public Map<String, Object> addUser(HttpServletRequest request,User user) throws Exception {
		logger.info( "添加用户addUser;User:"+user);
		message.setLength(0);
		flag=true;
		if (user.getUserName().trim().equals(user.getPassword().trim())) {
			message.append("密码不能与账号相同");
			flag = false;
		}
		if (userService.exist("userName", user.getUserName().trim())) {
			message.append("用户已存在");
			flag = false;
		}
		if (!(user.getEmail().trim().equals(""))) {
			if (this.userService.exist("email", user.getEmail().trim())) {
				message.append("Email已存在");
				flag = false;
			}
		}
		if (!(user.getCellphone().trim().equals(""))) {
			if (this.userService.exist("cellphone", user.getCellphone().trim())) {
				message.append("联系电话已存在");
				flag = false;
			}
		}
		if (flag) {
			message.append("操作成功");
			MD5Code md5 = new MD5Code();
			user.setPassword(md5.getMD5ofStr(user.getPassword().trim()));
			user.setLastLoginIP("192.168.1.1");
			user.setLastLoginTime(DateTime.getCurrentTimeStamp());
			user.setLoginTimes(0);
			user.setRegIP(request.getRemoteAddr());
			user.setRegTime(DateTime.getCurrentTimeStamp());
			user.setVisible(0);

			userService.save(user);
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "添加用户成功");
			logger.info(moduleName + "[管理员新增:" + message + "]");
			logger.info("添加成功");

		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "添加用户失败");
			logger.error(moduleName + "[管理员新增:" + message + "]");
		}
		return msgMap;

	}
	
	/**
	 * 
	 * @Title 删除用户操作
	 * @author  Administrator @date 2017-7-21 
	 * @Description 
	 *
	 */
	@Permission(model = "user", privilegeValue = "delete")
	@RequestMapping("/deleteUser")
	@ResponseBody
	public Map<String, Object> deleteUser(String userNames) throws Exception {
		message.setLength(0);
		flag=true;
		if(userNames == null || "".equals(userNames.trim())){
			flag = false;
		}
		if (flag) {
			message.append("操作成功");
			String[] userName = userNames.split(",");
			for (String str : userName) {
				userService.delete(str.trim());
			}
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "删除用户成功");
			logger.info("删除用户成功");
			logger.info(moduleName + "[管理员删除:" + message + "]");

		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "删除用户失败");
			logger.error(moduleName + "[管理员删除:" + message + "]");
		}
		return msgMap;
	}

	/**
	 * @Title 进入 编辑页面 根据传入的username 来进行查找user对象
	 * @author  Administrator  @date 2017-7-21 
	 * @Description 
	 *
	 */
	@Permission(model = "user", privilegeValue = "update")
	@RequestMapping("/editUI")
	@ResponseBody
	public Map<String, Object> editUI(HttpServletRequest request, String userName) throws Exception {
		User user = userService.find(userName.trim());
		if (user != null) {
			msgMap.put("status", true);
		} else {
			msgMap.put("status", false);
			msgMap.put("msg", "没有此用户");
		}
		String imgurl = GlobalUtil.getMsg("defaultAvatar");
		if(imgurl == null || "".equals(imgurl.trim())){
			imgurl = "/Images/replace/null-headPortrait.jpg";
		}
		msgMap.put("defaultAvatar",imgurl);
		return msgMap;

	}

	/**
	 * @Title 更新用户操作
	 * @author  Administrator  @date 2017-7-21 
	 * @Description 
	 *
	 */
	@Permission(model = "user", privilegeValue = "update")
	@RequestMapping("/editUser")
	@ResponseBody
	public Map<String, Object> editUser(UserVO uservo) throws Exception {
		message.setLength(0);
		flag=true;
		if(uservo != null){
			String userName = uservo.getUsername();
			String email = uservo.getEmail();
			String cellphone = uservo.getCellphone();
			if (userName.trim() == null || userName.trim().equals("")) {
				message.append("<p>用户名不能为空</p>");
				flag = false;
			}
			if (!(email.trim() == null || email.trim().equals(""))) {
				if (this.userService.exist("email", email.trim(), userName.trim())) {
					message.append("<p>Email已存在</p>");
					flag = false;
				}
			}
			if (!(cellphone.trim() == null || cellphone.trim().equals(""))) {
				if (this.userService.exist("cellphone", cellphone.trim(),
						userName.trim())) {
					message.append("<p>电话已存在</p>");
					flag = false;
				}
			}
	
			if (flag) {
				User user = userService.find(userName);
				user.setEmail(email);
				user.setCellphone(cellphone);
				user.setRealName(uservo.getRealname());
				user.setUpdateTime(DateTime.getCurrentTimeStamp());
				userService.update(user);
				message.append("<p>操作成功</p>");
				msgMap.put("success", true);
				msgMap.put("status", true);
				msgMap.put("msg", "修改用户成功");
				logger.info("修改用户成功");
				logger.info(moduleName + "[管理员编辑:" + message + "]");
			} else {
				msgMap.put("success", true);
				msgMap.put("status", false);
				msgMap.put("msg", message.toString());
			}
		}else{
			msgMap.put("success", false);
			msgMap.put("status", false);
			msgMap.put("msg", "更新用户信息失败");
		}
		return msgMap;
	}

	/**
	 * @Title 操作用户密码初始化
	 * @author  Administrator  @date 2017-7-21
	 * @Description 
	 *
	 */
	@Permission(model = "user", privilegeValue = "initPassword")
	@RequestMapping("/initPWD")
	@ResponseBody
	public Map<String, Object> initPWD(String userName) throws Exception {
		logger.info("开始密码初始化");
		message.setLength(0);
		flag=true;
		User user = userService.find(userName.trim());
		if (user == null) {
			message.append("<p>没有此用户</p>");
			flag = false;
		}
		if (flag) {
			MD5Code md5 = new MD5Code();
			user.setPassword(md5.getMD5ofStr("123456"));
			user.setUpdateTime(DateTime.getCurrentTimeStamp());
			userService.update(user);
			message.append("<p>操作成功，密码初始化为123456</p>");

			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", userName + "用户的初始化成功");
			logger.info(userName + "用户的初始化成功");
			logger.info(moduleName + "[管理员密码初始化:" + message + "]");

		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", userName + "用户的初始化失败");
			logger.error(moduleName + "[管理员密码初始化:" + message + "]");
		}
		return msgMap;
	}

	/**
	 * @Title 获取角色信息 如果该用户已存在该角色信息 则checked该角色
	 * @author  Administrator  @date 2017-7-21 
	 * @Description 
	 *
	 */
	@Permission(model = "user", privilegeValue = "setRole")
	@RequestMapping("/getRoles")
	@ResponseBody
	public List<PrivilegeGroupVO> getRoles(HttpServletRequest request,String userName) throws Exception {
		logger.info("开始获取角色！！。。");
		List<PrivilegeGroupVO> responseJsonList = new ArrayList<PrivilegeGroupVO>();
		List<PrivilegeGroup> pgs = groupService.getScrollData().getResultlist();
		request.setAttribute("groups", pgs);
		User user = userService.find(userName.trim());
		Set<PrivilegeGroup> userGroups = user.getGroups();
		request.setAttribute("userGroups", userGroups);
		if (userGroups.size() == 0) {
			for (PrivilegeGroup pg : pgs) {
				PrivilegeGroupVO pgvo = new PrivilegeGroupVO();
				pgvo.setUuid(pg.getUuid());
				pgvo.setName(pg.getName());
				pgvo.setChecked(false);
				responseJsonList.add(pgvo);
			}
		} else {
			for (PrivilegeGroup pg : pgs) {
				boolean flag = false;
				String groupID = pg.getUuid();
				for (PrivilegeGroup ug : userGroups) {
					String userGroupID = ug.getUuid();
					if (userGroupID.equals(groupID)) {
						flag = true;
					}
				}
				PrivilegeGroupVO pgvo = new PrivilegeGroupVO();
				pgvo.setUuid(pg.getUuid());
				pgvo.setName(pg.getName());
				if (flag) {
					pgvo.setChecked(true);
				} else {
					pgvo.setChecked(false);
				}
				responseJsonList.add(pgvo);
			}
		}
		return responseJsonList;
	}

	/**
	 * @Title 把角色信息重新赋给用户   更新用户角色信息
	 * @author  Administrator  @date 2017-7-21 
	 * @Description 
	 *
	 */
	@Permission(model = "user", privilegeValue = "setRole")
	@RequestMapping("/privilegeGroupEdit")
	@ResponseBody
	public Map<String, Object> privilegeGroupEdit(String userName,String[] groupIds) throws Exception {
		User user = userService.find(userName.trim());
		message.setLength(0);
		flag=true;
		if (user == null) {
			message.append("<p>没有此用户</p>");
			flag = false;
		}
		if (flag) {
			user.getGroups().clear();
			if (groupIds != null) {
				for (String ID : groupIds) {
					if (ID != null && !ID.equals("")) {
						user.addPrivilegeGroup(new PrivilegeGroup(ID));
					}
				}
			}
			user.setUpdateTime(DateTime.getCurrentTimeStamp());
			userService.update(user);
			message.append("<p>操作成功</p>");
			logger.info(moduleName + "[管理员角色编辑:" + message + "]");
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "给用户授权成功");
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "给用户授权失败");
			logger.error(moduleName + "[管理员角色编辑:" + message + "]");
		}
		return msgMap;
	}
	
	/**
	 * @Title 向用户分配部门  获取所有部门
	 * @author  Administrator  @date 2017-7-21 
	 * @Description 
	 *
	 */
	@Permission(model = "user", privilegeValue = "setDep")
	@RequestMapping("/editDepUI")
	@ResponseBody
	public Map<String, Object> editDepUI(HttpServletRequest request,String userName,String node) {
		List<TreeNodeVO> responseJsonTree = new ArrayList<TreeNodeVO>();
		// 向页面传递用户
		ArrayList<TreeNode> orgTree = null;
		Set<Department> orgs = null;
		if(userName != null){
			try {
				// 获取用户当前的部门
				orgs = userService.getUserDeps(new User(userName));
				// 获取所有部门
				// orgTree = departmentService.getAllDepZTreeJson();
				orgTree = departmentService.getAllDepTree();
				if ("root".equals(node)) {
					// 表示加载父节点
					for (TreeNode tn : orgTree) {
						if (tn.getParent() == null) {
							TreeNodeVO tnv = new TreeNodeVO();
							tnv.setText(tn.getName());
							tnv.setId(tn.getId());
							if (tn.getChilds().size() == 0) {
								tnv.setLeaf(true);
							} else {
								tnv.setLeaf(false);
							}
							responseJsonTree.add(tnv);
						}
					}
				} else {
					// 加载子节点
					for (TreeNode tn : orgTree) {
						if (node.equals(tn.getId())) {
							
							for (TreeNode childNode : tn.getChilds()) {
								logger.info("子节点 ： "+childNode.getName());
								TreeNodeVO tnv = new TreeNodeVO();
								tnv.setText(childNode.getName());
								tnv.setId(childNode.getId());
								if (childNode.getChilds().size() == 0) {
									tnv.setLeaf(true);
								} else {
									tnv.setLeaf(false);
								}
								responseJsonTree.add(tnv);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				request.setAttribute("errorFlag", "true");
				request.setAttribute("errorMsg", "查询数据库出错");
			}
			// 向页面传递用户拥有的部门
			if (orgs.size() == 0) {
				logger.info("当前用户 没有 部门   ");
				request.setAttribute("org", new Department());
			} else if (orgs.size() > 1) {
				request.setAttribute("errorFlag", "true");
				request.setAttribute("errorMsg", "该用户拥有多个部门，请在数据库中删除");
			} else {
				logger.info("当前用户 部门   "+ orgs.iterator().next().getName());
				msgMap.put("userDep", orgs.iterator().next().getName());
			}
		}else{
			request.setAttribute("errorFlag", "true");
			request.setAttribute("errorMsg", "请选择用户后，进行操作");
		}
		msgMap.put("responseJsonTree", responseJsonTree);
		return msgMap;
	}

	/**
	 * @Title 更新用户部门信息，
	 * @author  Administrator  @date 2017-7-21 
	 * @Description 
	 *
	 */
	@Permission(model = "user", privilegeValue = "setDep")
	@RequestMapping("/editDep")
	@ResponseBody
	public Map<String, Object> editDep(String userName,String departmentId) throws Exception {
		User user = null;
		message.setLength(0);
		flag=true;
		try {
			user = userService.find(userName.trim());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			message.append("查询数据库出错");
			flag = false;
		}
		if (flag && (user == null)) {
			message.append("不存在此用户");
			flag = false;
		}

		if (flag) {
			user.getDepartments().clear();
			if (departmentId != null && !"".equals(departmentId.trim())) {
				user.addDepartments(new Department(departmentId));
			}
			user.setUpdateTime(DateTime.getCurrentTimeStamp());
			try {
				userService.update(user);
				message.append("<p>操作成功</p>");
				msgMap.put("msg", message.toString());
				msgMap.put("success", true);
				msgMap.put("status", true);
			} catch (Exception e) {
				e.printStackTrace();
				message.append("数据库操作失败");
				msgMap.put("msg", message.toString());
				msgMap.put("success", true);
				msgMap.put("status", false);
			}
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
		}
		return msgMap;
	}

	/**
	 * 
	 * @Title 向选中的用户分配菜单权限
	 * @author  Administrator  @date 2017-7-21 
	 * @Description 
	 *
	 */
	@Permission(model = "user", privilegeValue = "setPrivilege")
	@RequestMapping("/menuPrivilegeEditUI")
	@ResponseBody
	public List<StandardTreeNodeVo> menuPrivilegeEditUI(String userName) throws Exception {
		List<StandardTreeNodeVo> jsonMenuTree = new ArrayList<StandardTreeNodeVo>();
		if(userName != null && ! "".equals(userName.trim())){
			PageView<Menu> pageView = new PageView<Menu>(-1, -1);
			StringBuffer jpql = new StringBuffer("");
			List<Object> params = new ArrayList<Object>();
	
			if (params.size() > 0){
				jpql.append(" and ");
			}
			jpql.append(" o.visible=?" + (params.size() + 1));
			params.add(1);
			LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
			orderby.put("uuid", "desc");
			QueryResult<Menu> qr = menuService.getScrollData(
					pageView.getFirstResult(), pageView.getMaxresult(),
					jpql.toString(), params.toArray(), orderby);
			pageView.setQueryResult(qr);
			User user = userService.find(userName);
			for (Menu menu : pageView.getRecords()) {
				StandardTreeNodeVo simpleTreeNode = new StandardTreeNodeVo();
				if (menu.getParent() == null) {// 过滤出根节点
					simpleTreeNode.setId(menu.getUuid());
					simpleTreeNode.setText(menu.getName());
					if (menu.getChilds().size() == 0) {
						// 判断根节点是否有子节点
						simpleTreeNode.setLeaf(true);
					} else {
						simpleTreeNode.setLeaf(false);
						simpleTreeNode.setExpanded(true);
						simpleTreeNode.setChildren(menuService.allChildrenTreeNode(menu, user));
					}
					jsonMenuTree.add(simpleTreeNode);
				}
			}
		}
		return jsonMenuTree;
	}

	/**
	 * @Title 跟新对用户菜单权限的操作
	 * @author  Administrator  @date 2017-7-25 
	 * @Description 
	 *
	 */
	@Permission(model = "user", privilegeValue = "setPrivilege")
	@RequestMapping("/menuPrivilegeEdit")
	@ResponseBody
	public Map<String, Object> menuPrivilegeEdit(String userName,String[] privilegesStr) throws Exception {
		message.setLength(0);
		if (userName != null) {
			if(privilegesStr != null && privilegesStr.length>0){
				User user = userService.find(userName);
				user.getSystemPrivileges().clear();
				if (privilegesStr != null) {
					for (int i = 0; i < privilegesStr.length; i++) {
						user.addPrivilege(new SystemPrivilege(new SystemPrivilegePK(
								privilegesStr[i].split(",")[0], privilegesStr[i].split(",")[1])));
					}
				}
				user.setUpdateTime(DateTime.getCurrentTimeStamp());
				userService.update(user);
				message.append("<p>操作成功</p>");
				msgMap.put("msg", message.toString());
				msgMap.put("success", true);
				msgMap.put("status", true);
				logger.info(moduleName + "[管理员菜单权限:" + message + "]");
			}else{
				msgMap.put("msg", "您尚未选择！");
				msgMap.put("success", true);
				msgMap.put("status", false);
			}
		} else {
			msgMap.put("msg", "获取失败，请重试！");
			msgMap.put("success", true);
			msgMap.put("status", false);
		}

		return msgMap;
	}

	/**
	 * @Title 切换 用户锁定状态
	 * @author  Administrator  @date 2017-7-21 
	 * @Description 
	 *
	 */
	@Permission(model = "user", privilegeValue = "update")
	@RequestMapping("/updateVisible")
	@ResponseBody
	public Map<String, Object> updateVisible(String userName,int visible ) throws Exception {
		User user = userService.find(userName.trim());
		message.setLength(0);
		flag=true;
		if (user == null) {
			message.append("<p>没有此用户</p>");
			flag = false;
		}
		if (flag) {
			user.setUpdateTime(DateTime.getCurrentTimeStamp());
			user.setVisible(visible);
			userService.update(user);
			if (visible == 1) {
				String accesspath = "";
				String depUrl = departmentService.findByName(user
						.getBelongdep());
				if (depUrl == null) {
					accesspath = "http://www.hytcredit.cn/";
				} else {
					accesspath = depUrl;
				}
				String[] str = { user.getEmail() };
				EmailSender.send(str, "新用户注册成功", "恭喜！您的已通过审核<br>点击链接登录<br>"
						+ accesspath, null, "text/html");
				message.append("<p>激活操作成功</p>");
			} else {
				message.append("<p>锁定操作成功</p>");
			}
			logger.info(moduleName + "[管理员状态设置:" + message + "]");
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "用户状态更改成功");
			logger.info("更改成功！！。");

		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "用户状态更改失败");
			logger.error(moduleName + "[管理员状态设置:" + message + "]");
		}
		return msgMap;
	}

	
	@Permission(model = "user", privilegeValue = "updatePassword")
	@RequestMapping("/editPWDUI")
	@ResponseBody
	public Map<String, Object> editPWDUI(HttpServletRequest request) throws Exception {
		User user = WebUtil.getUser(request);
		if (user != null) {
			String userName = user.getUserName();
			msgMap.put("userName", userName);
		}
		return msgMap;
	}
	

	/**
	 * 
	 * @ClassName UserManageAction.java
	 * @author  Administrator  @date 2017-7-21 下午1:57:49
	 * @Description  修改密码
	 * @param request
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	@Permission(model = "user", privilegeValue = "updatePassword")
	@RequestMapping("/editPWD")
	@ResponseBody
	public Map<String, Object> editPWD(HttpServletRequest request,UserPassword pass) throws Exception {
		MD5Code md5 = new MD5Code();
		message.setLength(0);
		flag=true;
		User user = WebUtil.getUser(request);
		//判断用户是否一致
		if (pass.getUserName().trim().equals(user.getUserName())) {
			//判断原密码和输入的原密码 是否一致
			if (!user.getPassword().equals(md5.getMD5ofStr(pass.getOldpassword()))) {
				message.append("<p>原始密码错误</p>");
				flag = false;
			}
			//判断新密码和原密码是否一致
			if (pass.getNewpassword().trim().equals(user.getPassword())) {
				message.append("<p>新密码与原密码重复</p>");
				flag = false;
			}
			//判断输入的新密码和重复输入的新密码是否一致
			if (!pass.getNewpassword().trim().equals(pass.getRepeatpassword().trim())) {
				message.append("<p>请保持新密码与确认密码一致</p>");
				flag = false;
			}
		} else {
			message.append("<p>您的操作违法！</p>");
			flag = false;
		}
		
		if (flag) {
			user.setPassword(md5.getMD5ofStr(pass.getNewpassword().trim()));
			userService.update(user);
			request.getSession().removeAttribute("user");
			request.getSession().removeAttribute("nowLoginTimes");
			request.getSession().removeAttribute("nowLoginIP");
			request.getSession().removeAttribute("dwrTest");
			request.getSession().removeAttribute("userName");
			request.getSession().removeAttribute("myOutMenus");
			request.getSession().removeAttribute("myOutRootMenus");
			message.append("<p>操作成功!</p>");
			message.append("<p>请重新登录!</p>");
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", message.toString());
			msgMap.put("flag", flag);
			logger.info(moduleName + "[管理员密码修改:" + message + "]");
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", message.toString());
			msgMap.put("flag", flag);
			logger.error(moduleName + "[管理员密码修改:" + message + "]");
		}
		return msgMap;
	}


	/**
	 * 
	 * @ClassName UserManageAction.java
	 * @author  Administrator  @date 2017-7-21 下午4:39:35
	 * @Description 展示用户信息
	 * @param request
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	// 初始化数据
	@Permission(model = "user", privilegeValue = "list")
	@RequestMapping("/showUI")
	@ResponseBody
	public Map<String, Object> showUI(HttpServletRequest request) throws Exception {
		User userSession = WebUtil.getUser(request);
		User user = userService.find(userSession.getUserName());
		User userT = new User();
		if (user != null) {
			msgMap.put("cellphone", user.getCellphone());
			userT.setCellphone(user.getCellphone());
			msgMap.put("email", user.getEmail());
			userT.setEmail(user.getEmail());
			msgMap.put("gender", user.getGender());
			userT.setGender(user.getGender());
			msgMap.put("realName", user.getRealName());
			userT.setRealName(user.getRealName());
			msgMap.put("userName", user.getUserName());
			userT.setUserName(user.getUserName());
			if (user.getVisible() == 1) {
				msgMap.put("status", "正常");
			} else {
				msgMap.put("status", "关闭");
			}
			msgMap.put("success", true);
		} else {
			msgMap.put("success", false);
		}
		msgMap.put("user", userT);
		return msgMap;
	}

}
