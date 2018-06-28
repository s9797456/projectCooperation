/**
 * 
 */
package com.credit.controller.member;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import com.credit.bean.member.User;
import com.credit.bean.privilege.Department;
import com.credit.bean.privilege.Menu;
import com.credit.bean.privilege.PrivilegeGroup;
import com.credit.bean.privilege.SystemPrivilege;
import com.credit.bean.vo.html2pdf.Report;
import com.credit.service.enterprise.EntBaseInfoService;
import com.credit.service.enterprise.EntResultService;
import com.credit.service.member.UserService;
import com.credit.service.privilege.MenuService;
import com.credit.service.privilege.DepartmentService;
import com.credit.util.DateTime;
import com.credit.util.MD5Code;
import com.credit.util.ZXingUtil;
import com.credit.util.geetest.GeetestLib;
import com.credit.util.geetest.GeetestValid;
import com.credit.util.properties.BusinessUtil;
import com.credit.util.properties.GlobalUtil;

@Controller
@RequestMapping("/user")
public class UserRegistAndLoginAction {
	private static final Logger logger = Logger.getLogger(UserRegistAndLoginAction.class);

	private StringBuffer message = new StringBuffer("");
	private Boolean flag = true;
	@Resource
	private DepartmentService organizationService;
	@Resource
	private UserService userService;
	@Resource
	private MenuService menuService;
	@Resource
	private EntBaseInfoService entbaseinfoService;
	@Resource
	private EntResultService entResultService;


	@RequestMapping("/registUI")
	public String registUI(HttpServletRequest request) {
		String twoDomainNames = request.getRequestURL().toString();
		//organization = organizationService.findByTwoDomainNames(twoDomainNames);
		return "Control/regist";
	}
	@RequestMapping("/logonUI")
	public String logonUI(HttpServletRequest request) {
		request.getSession().setAttribute("corp_name", GlobalUtil.getMsg("corp_name"));
		request.getSession().setAttribute("corp_name2", GlobalUtil.getMsg("corp_name2"));
		request.getSession().setAttribute("corp_url", GlobalUtil.getMsg("corp_url"));
		request.getSession().setAttribute("corp_weibo", GlobalUtil.getMsg("corp_weibo"));
		request.getSession().setAttribute("corp_weixin", GlobalUtil.getMsg("corp_weixin"));
		request.getSession().setAttribute("obj_name", GlobalUtil.getMsg("obj_name"));
		request.getSession().setAttribute("obj_name2", GlobalUtil.getMsg("obj_name2"));
		request.getSession().setAttribute("obj_version", GlobalUtil.getMsg("obj_version"));
		request.getSession().setAttribute("obj_updatetime", GlobalUtil.getMsg("obj_updatetime"));
		request.getSession().setAttribute("obj_record", GlobalUtil.getMsg("obj_record"));
		String twoDomainNames = request.getRequestURL().toString();
		Department department = organizationService.findByTwoDomainNames(twoDomainNames);
		if (department != null) {
			String[] strs = department.getLogoImageUrl().split("/>");
			if (strs.length > 1) {
				String loginImage = strs[0].substring(strs[0].indexOf("src=") + 5,
						strs[0].indexOf("alt") - 2);
				String logoImage = strs[1].substring(strs[1].indexOf("src=") + 5,
						strs[1].indexOf("alt") - 2);
			}
		}
		request.setAttribute("organization", department);
		return "Control/login";
	}
	@RequestMapping("/logon")
	@ResponseBody
	public Map<String, Object> start(HttpServletRequest request) throws Exception {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		message.setLength(0);
		flag=true;
		MD5Code md5 = new MD5Code();
		String userName=request.getParameter("userName").toLowerCase().trim();
		String password=request.getParameter("password");
		User entity = userService.find(userName.toLowerCase().trim());
		GeetestLib gtSdk = new GeetestLib(GlobalUtil.getMsg("geetest_id"), GlobalUtil.getMsg("geetest_key"),true);
		boolean isValid=GeetestValid.isValid(request,gtSdk);
		//boolean isValid=true;
		if (entity == null) {
			message.append( "用户名错误" );
			flag = false;
		} else {
			if (entity.getVisible() != 1) {
				message.append("账户被锁定");
				flag = false;
			} else {
				if (!userService.validate(userName.trim(),
						md5.getMD5ofStr(password.trim()))) {
					message.append("用户名或密码输入错误");
					flag = false;
				} else {
					if (!isValid) {
						message.append("验证码输入错误");
						flag = false;
					}
				}
			}
		}

		if (flag) {
			entity.setLoginTimes(entity.getLoginTimes() + 1);
			entity.setLastLoginIP(request.getRemoteAddr());
			entity.setLastLoginTime(DateTime.getCurrentTimeStamp());
			userService.update(entity);
			request.getSession().setAttribute("user", entity);
			request.getSession().setAttribute("nowLoginTimes", DateTime.getCurrentTimeStamp());
			request.getSession().setAttribute("nowLoginIP",request.getRemoteAddr());
			request.getSession().setAttribute("userName", userName.trim());

			// 我的用户权限
			Set<SystemPrivilege> mySps = userService.getUserSPsOfUserName(userName);
			// 我的角色权限
			Set<PrivilegeGroup> pgs = userService
					.getUserRolesOfUserName(userName);
			Set<SystemPrivilege> roleSPs = userService.getRoleSPs(pgs);// 我的角色权限
			// 我的权限并集
			Set<SystemPrivilege> allMySPs = userService.getAllSPs(mySps,
					roleSPs);
			request.getSession().setAttribute("mySps", allMySPs);
			// 系统内所有菜单
			List<Menu> allMenus = menuService.getAllMenus();
			// 系统内所有无子节点菜单
			List<Menu> allChildMenus = menuService.getAllChildMenus(allMenus);
			// 系统内所有顶端节点菜单
			List<Menu> allRootMenus = menuService.getAllRootMenus(allMenus);
			// 我的所有菜单
			Set<Menu> myMenus = menuService.getMyMenus(allMySPs, allChildMenus);
			// 我的顶端菜单之下所有菜单输出样式
			ArrayList<Menu> myOutMenus = menuService.getMyOutMenus(
					allRootMenus, myMenus);
			// 我的顶端菜单
			ArrayList<Menu> myOutRootMenus = menuService.getMyOutRootMenus(
					allRootMenus, myOutMenus);
			request.getSession().setAttribute("myOutMenus", myOutMenus);
			request.getSession().setAttribute("myOutRootMenus", myOutRootMenus);

			message.append("用户登录成功");
			logger.info(userName.trim() + message.toString());
			logger.info("用户：" + userName + "登录成功");
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "用户登录成功");
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", message.toString());
			request.setAttribute("message", message.toString());
			logger.error(userName.trim() + "用户登录失败;原因:" + message.toString());
		}
		return msgMap;
	}
	@RequestMapping("/quit")
	public String quit(HttpServletRequest request) throws Exception {
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("nowLoginTimes");
		request.getSession().removeAttribute("nowLoginIP");

		String twoDomainNames = request.getRequestURL().toString();
		Department department = organizationService.findByTwoDomainNames(twoDomainNames);
		if (department != null) {
			String[] strs = department.getLogoImageUrl().split("/>");
			if (strs.length > 1) {
				String loginImage = strs[0].substring(strs[0].indexOf("src=") + 5,
						strs[0].indexOf("alt") - 2);
				String logoImage = strs[1].substring(strs[1].indexOf("src=") + 5,
						strs[1].indexOf("alt") - 2);
			}
		}
		return "Control/login";
	}
	
	
	@RequestMapping("/logon_check")
	public String check(HttpServletRequest request,String uscc,String id) throws Exception {
		String uuid = null;
		EntBaseInfo ent = new EntBaseInfo();
		if(uscc == null){
			message.append("参数错误");
			flag = false;
		} else{
			uscc = uscc.trim();
			ent = entbaseinfoService.selectByUSCC(uscc);
			if(ent == null){
				message.append("参数错误");
				flag = false;
			}
		}
		if(id == null){
			flag = false;
		}
		if(flag){
			uuid = ent.getUuid();
			MD5Code md5 = new MD5Code();
			String uuidBusiness = md5.getMD5ofStr(uuid+ent.getUSCC());
			EntResult entResult = new EntResult();
			if(uuid != null){
				entResult = entResultService.getEntResultByEntId(uuid);
				if(entResult!=null){
					Report report=new Report();
					String dir=request.getSession().getServletContext().getRealPath("");
					String adr=BusinessUtil.getMsg("adr");
					String root=BusinessUtil.getMsg("root");
					dir=dir+root;
					adr=adr+root;
					String encoding = entResult.getEncoding();
					request.setAttribute("encoding",encoding);
					if(id.equals(uuidBusiness)){
						String path = request.getContextPath();
						String basePath = request.getScheme() + "://"
								+ request.getServerName() + ":"
								+ request.getServerPort() + path;
						String s1 = basePath
								+ "/user/logon_check.do?uscc="
								+ ent.getUSCC()
								+ "&id="
								+ uuidBusiness;
						String s2 = dir+BusinessUtil.getMsg("templateUrl")+ "/Twocode.png";
						if (ZXingUtil.encodeQRCodeImage(s1, null, s2 , 200 , 200, null)) {
							logger.info("二维码生成成功!");
						}
						String finalLevel = entResult.getFinalLevel();
						if(finalLevel!=null){
							request.setAttribute("finallevel",finalLevel);
						}
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
						SimpleDateFormat en = new SimpleDateFormat("MMMM dd,yyyy",Locale.ENGLISH);
						Date reportDate = entResult.getGradeTime();
						if(reportDate != null){
							Calendar cal = Calendar.getInstance();
							cal.setTime(reportDate);
							cal.add(Calendar.YEAR, 1);
							String reportTime = sdf.format(reportDate);
							String usefulTime = sdf.format(cal.getTime());
							String reportTimeEn = en.format(reportDate);
							String usefulTimeEn = en.format(cal.getTime());
							request.setAttribute("reportTime",reportTime);
							request.setAttribute("reportTimeEn",reportTimeEn);
							request.setAttribute("usefulTime",usefulTime);
							request.setAttribute("usefulTimeEn",usefulTimeEn);
							request.setAttribute("message",message);
							request.setAttribute("entName",ent.getName());
							message.append("正版");
							return "Control/checksuccess";
						}else{
							message.append("盗版");
							request.setAttribute("message", message);
							return "Control/checkerror";
						}
					}else {
						message.append("盗版");
						request.setAttribute("message", message);
						return "Control/checkerror";
					}
				}
		  }
		}else{
			request.setAttribute("message",message);
			return "Control/checkerror";
		}
		return "Control/checkerror";
	}
}
