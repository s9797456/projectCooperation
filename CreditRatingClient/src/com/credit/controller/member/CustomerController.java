package com.credit.controller.member;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.model.enterprise.EntBaseInfo;
import com.credit.model.enterprise.EntResult;
import com.credit.model.enterprise.Executives;
import com.credit.model.enterprise.Historical;
import com.credit.model.enterprise.Opinion;
import com.credit.model.enterprise.ProcessState;
import com.credit.model.enterprise.Shareholder;
import com.credit.model.member.Customer;
import com.credit.model.member.Organization;
import com.credit.model.person.PerBaseInfo;
import com.credit.model.person.PerOpinion;
import com.credit.model.privilege.C_Customer_RoleKey;
import com.credit.model.privilege.C_SystemPrivilegeKey;
import com.credit.model.security.IPLock;
import com.credit.model.security.LoginLimit;
import com.credit.model.security.RegistLimit;
import com.credit.modelvo.Permission;
import com.credit.modelvo.SessionName;
import com.credit.service.enterprise.EntBaseInfoService;
import com.credit.service.enterprise.EntResultService;
import com.credit.service.enterprise.ExecutivesService;
import com.credit.service.enterprise.HistoricalService;
import com.credit.service.enterprise.OpinionService;
import com.credit.service.enterprise.ProcessStateService;
import com.credit.service.enterprise.ShareholderService;
import com.credit.service.member.CustomerService;
import com.credit.service.member.OrganizationService;
import com.credit.service.person.PerBaseInfoService;
import com.credit.service.person.PerHistoryService;
import com.credit.service.person.PerOpinionService;
import com.credit.service.person.PerProcessService;
import com.credit.service.person.PerResultService;
import com.credit.service.privilege.C_PrivilegeGroupService;
import com.credit.service.security.IPLockService;
import com.credit.service.security.LoginLimitService;
import com.credit.service.security.RegistLimitService;
import com.credit.util.DateTime;
import com.credit.util.MD5Code;
import com.credit.util.RandomUtil;
import com.credit.util.WebUtil;
import com.credit.util.geetest.GeetestLib;
import com.credit.util.geetest.GeetestValid;
import com.credit.util.properties.AliyunUtil;
import com.credit.util.properties.GlobalUtil;
import com.credit.util.security.DateUtil;
import com.credit.util.security.IPUtil;
import com.credit.util.security.SecurityUtil;
import com.nbchina.ws.api.entinfo.baseinfo.BaseInterface;
import com.nbchina.ws.api.entinfo.baseinfo.Enterprise;
import com.nbchina.ws.api.entinfo.baseinfo.Partner;
import com.nbchina.ws.api.entinfo.baseinfo.QueryResult;
import com.nbchina.ws.api.entinfo.baseinfo.VipMember;
/**
 * @title 客户控制层
 * @author ssf  2017-7-25
 * @desc
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {


	private boolean flag = true;

	private StringBuffer message = new StringBuffer("");

	private static final Logger logger = Logger
			.getLogger(CustomerController.class);

	private String moduleName = "用户操作情况";
	@Resource
	private CustomerService<?> customerService;
	@Resource
	private EntBaseInfoService entBaseInfoService;
	@Resource
	private HistoricalService historicalService;
	@Resource
	private ProcessStateService processStateService;
	@Resource
	private EntResultService entResultService;
	@Resource
	private OrganizationService organizationService;
	@Resource
	private OpinionService opinionService;
	@Resource
	private ShareholderService shareholderService;
	@Resource
	private ExecutivesService executivesService;
	@Resource
	private C_PrivilegeGroupService<?> privilegeGroupService;
	@Resource
	private IPLockService<?> ipLockService;
	@Resource
	private RegistLimitService<?> registLimitService;
	@Resource
	private LoginLimitService<?> loginLimitService;
	@Resource
	private PerBaseInfoService perBaseInfoService;
	@Resource
	private PerProcessService perProcessService; //个人信用状态
	@Resource
	private PerOpinionService perOpinionService;//驳回信息
	@Resource
	private PerResultService perResultService;//评分信息
	@Resource
	private PerHistoryService perHistoryService;//历史信息
	//接口 企业
	@Resource
	private BaseInterface baseInterface;
	/**
	 * @title 进入登录页面
	 * @author  孙尚飞  2017-7-26
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @desc
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/logonUI")
	public String logonUI(HttpServletRequest request, Model model) throws ClientProtocolException, IOException {
		logger.info(moduleName + "[进入登录页面]");
		//获取 application 得到存在二级域名的map
		ServletContext application = request.getSession().getServletContext();
		// 获取连接的地址requestURL   sub->去除  http://（7位）
		String requestURL = request.getRequestURL().toString().substring(7);
		System.out.println("RequestURL : "+ requestURL);
		//从 application 获取  机构 map 集合
		Map<String, Organization> organizationMap = (Map<String, Organization>) application.getAttribute("organizations");
		System.out.println("地址："+requestURL.split("/")[0].trim());
		for (Map.Entry<String, Organization> entry : organizationMap.entrySet()) {  
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
		}
		if(organizationMap != null){
			Organization organization = organizationMap.get(requestURL.split("/")[0].trim());
			if (organization != null) {
				request.getSession().setAttribute(SessionName.ORGANIZATION, organization);
				logger.info(moduleName + "[机构用户进入相应登录的界面]");
				if(SecurityUtil.getMsg("EnableLoginCheckCode").equals("1")){
					request.setAttribute("CheckCode", true);
				}
				return "login";
			}
		}
		request.getSession().setAttribute("corp_name", GlobalUtil.getMsg("corp_name"));
		request.getSession().setAttribute("corp_email", GlobalUtil.getMsg("corp_email"));
		request.getSession().setAttribute("corp_phone", GlobalUtil.getMsg("corp_phone"));
		request.getSession().setAttribute("corp_address", GlobalUtil.getMsg("corp_address"));
		request.getSession().setAttribute("corp_name2", GlobalUtil.getMsg("corp_name2"));
		request.getSession().setAttribute("corp_url", GlobalUtil.getMsg("corp_url"));
		request.getSession().setAttribute("corp_weibo", GlobalUtil.getMsg("corp_weibo"));
		request.getSession().setAttribute("corp_weixin", GlobalUtil.getMsg("corp_weixin"));

		request.getSession().setAttribute("obj_name", GlobalUtil.getMsg("obj_name"));
		request.getSession().setAttribute("obj_name2", GlobalUtil.getMsg("obj_name2"));
		request.getSession().setAttribute("obj_version", GlobalUtil.getMsg("obj_version"));
		request.getSession().setAttribute("obj_updatetime", GlobalUtil.getMsg("obj_updatetime"));
		request.getSession().setAttribute("obj_record", GlobalUtil.getMsg("obj_record"));

		
		if(SecurityUtil.getMsg("EnableLoginCheckCode").equals("1")){
			request.setAttribute("CheckCode", true);
		}
		return "login";
	}
	/**
	 * @title 客户登录
	 * @author  孙尚飞  2017-7-26
	 * @desc
	 */
	@RequestMapping("/logon")
	@ResponseBody
	public Map<String, Object> logon(String cellphone, String password, HttpServletRequest request) throws Exception {
		logger.info(moduleName + "[登录验证用户账号]");
		boolean flag = true;
		boolean iplock=true;
		boolean isclose=false;
		boolean isValid=true;
		LoginLimit login=new LoginLimit();
		String errormsg=new String();
		String ip=WebUtil.getIpAddress(request);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if(SecurityUtil.getMsg("EnableLoginCheckCode").equals("1")){
			GeetestLib gtSdk = new GeetestLib(GlobalUtil.getMsg("geetest_id"), GlobalUtil.getMsg("geetest_key"),true);
			isValid=GeetestValid.isValid(request,gtSdk);
		}
		if(SecurityUtil.getMsg("IPLock").equals("1")){
			//判断库内IP封锁
			if(!ipValidate(request)){
				iplock = false;
			}
		}
		if(SecurityUtil.getMsg("EnableLoginOpenTime").equals("1")){
			if(DateUtil.isClose(SecurityUtil.getMsg("LoginOpenTime"))){
				isclose=true;
			}
		}
		MD5Code md5 = new MD5Code();
		request.getSession().removeAttribute(SessionName.PRIVILEGE);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("password", md5.getMD5ofStr(password.trim()));
		params.put("cellphone", cellphone.trim().toLowerCase());

		Customer customer=customerService.selectByParam(params);
		if(isclose){
			errormsg="系统未开放，关闭时段为"+DateUtil.getCloseTime(SecurityUtil.getMsg("LoginOpenTime"));
			msgMap.put("status", "-100");
			msgMap.put("msg", errormsg);
			logger.info(moduleName + "["+errormsg+"]");
			flag = false;
		}else if(!iplock){
			errormsg="您的IP已被封锁";
			msgMap.put("status", "-100");
			msgMap.put("msg",errormsg );
			logger.info(moduleName + "["+errormsg+"]");
			flag = false;
		}else if(!loginLimitService.Interval(ip, new Date())){
			errormsg="登录间隔时间为"+SecurityUtil.getMsg("LoginIntervalTime")+"秒，请等待...";
			msgMap.put("status", "-100");
			msgMap.put("msg",errormsg );
			logger.info(moduleName + "["+errormsg+"]");
			flag = false;
		}else if(!loginLimitService.Verification(ip, cellphone.trim().toLowerCase())){
			errormsg="允许登录失败次数为"+SecurityUtil.getMsg("LoginErrLimitTimes")+"次，请于10分钟后再登录";
			msgMap.put("status", "-100");
			msgMap.put("msg",errormsg );
			logger.info(moduleName + "["+errormsg+"]");
			flag = false;
		}else if (customer == null) {
			errormsg="登录用户密码和手机号错误";
			msgMap.put("status", "1");
			msgMap.put("msg",errormsg );
			logger.info(moduleName + "["+errormsg+"]");
			flag = false;
		} else if (!isValid) {
			errormsg="登录用户验证码错误";
			msgMap.put("status", "2");
			msgMap.put("msg",errormsg );
			logger.info(moduleName + "["+errormsg+"]");
			flag = false;
		} else if (customer.getVisible().toString().equals("0")) {
			errormsg="登录用户没有被激活";
			msgMap.put("status", "3");
			msgMap.put("msg",errormsg );
			logger.info(moduleName + "["+errormsg+"]");
			flag = false;
		} else if (customer.getVisible().toString().equals("3")) {
			errormsg="用户已经被注销";
			msgMap.put("status", "4");
			msgMap.put("msg",errormsg );
			logger.info(moduleName + "["+errormsg+"]");
			flag = false;
		}
		login.setUuid(UUID.randomUUID().toString().replace("-", ""));
		login.setIp(ip);
		login.setLoginname(cellphone.trim().toLowerCase());
		login.setLogintime(new Date());
		if (flag) {
			System.out.println(customer.getUsername());
			Set<C_SystemPrivilegeKey> cspks = privilegeGroupService.selectByCustomer(customer.getUsername());
			request.getSession().setAttribute(SessionName.PRIVILEGE, cspks);
			String entID = customer.getEntid();
			if (entID != null) {
				EntBaseInfo ent= entBaseInfoService.selectByPrimaryKey(entID);
				request.getSession().setAttribute(SessionName.ENTNAME, ent.getName());
			}
			String perID = customer.getPerid();
			if (perID != null) {
				PerBaseInfo perBaseInfo = perBaseInfoService.selectByPrimaryKey(perID);
				request.getSession().setAttribute(SessionName.PERNAME, perBaseInfo.getName());
			}
			if(customer.getType() == 1){
				msgMap.put("type", "/customer/jumpToOrgMain.do");
				logger.info(moduleName + "[登录的是机构账号]");
			}else if(customer.getType() == 0){
				msgMap.put("type", "/customer/jumpToMain.do");
				logger.info(moduleName + "[登录的是客户账号]");
			}else if(customer.getType() == 2){
				msgMap.put("type", "/customer/jumpToGovMain.do");
				logger.info(moduleName + "[登录的是政府账号]");
			}else if(customer.getType() == 3){
				msgMap.put("type", "/customer/jumpToPerMain.do");
				logger.info(moduleName + "[登录的是个人账号,转至个人信用评价平台]");
			}
			msgMap.put("status", "0");
			request.getSession().setAttribute(SessionName.CUSTOMER, customer);
			login.setIssuccess(1);
			login.setCustomerid(customer.getUsername());
			logger.info(moduleName + "[登录验证成功]");
		} else {
			login.setIssuccess(0);
			login.setFailresult(errormsg);
			logger.error(moduleName + "[登录验证失败]");
		}
		loginLimitService.insert(login);
		return msgMap;
	}
	/**
	 * @title 进入平台首页
	 * @author  孙尚飞  2017-7-27
	 * @desc 增加 显示状态功能（ProcessState）  驳回显示功能（Opinion） spf 2017-8-3
	 */
	@Permission(model = "enterprise", privilegeValue = "main")
	@RequestMapping("/jumpToMain")
	public String jumpToMain(HttpServletRequest request, Model model) {
		logger.info(moduleName + "[进入平台main.jsp首页]");
		ServletContext application = request.getSession().getServletContext();
		String orgTwoDomainNames = request.getRequestURL().toString();
		String twoDomainNames = orgTwoDomainNames.substring(7,orgTwoDomainNames.length());
	/*	Map<String, Organization> organizationMap = (Map<String, Organization>) application.getAttribute("organizations");
		Organization organization = organizationMap.get(twoDomainNames.substring(0, twoDomainNames.indexOf("/")).trim());
		model.addAttribute("organization", organization);*/
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer == null){
			return "redirect:/customer/logonUI.do";
		}
		String ip = WebUtil.getIpAddress(request);
		customer.setLastloginip(ip);
		customer.setLogintimes(customer.getLogintimes()+ 1);
		String entBaseInfoUuid = customer.getEntid();
		//根据entBaseInfoUuid 查找 processState 对象
		ProcessState processState =  processStateService.selectByEntBaseInfoKey(entBaseInfoUuid);
		
		//驳回项
		//根据entBaseInfoUuid 查找 Opinion 对象
				Opinion  opinion = opinionService.selectByEntBaseInfoKey(entBaseInfoUuid ,1);
				if(opinion!=null){
					if(opinion.getIsconfirm() == 1){
						request.getSession().setAttribute("reject", 1);
					}else{
						request.getSession().setAttribute("reject", 0);
					}
				}else{
					request.getSession().setAttribute("reject", 1);
				}
		//获取历史
		List<Historical> historical = historicalService.selectByEntId(customer.getEntid());
		if(historical != null && historical.size()>0){
			request.getSession().setAttribute("historical","1");
		}else{
			request.getSession().setAttribute("historical","0");
		}
		//根据entBaseInfoUuid 查找 EntResult 对象获取评分情况
		EntResult entResult = entResultService.selectByEntId(entBaseInfoUuid);
		EntBaseInfo ent= entBaseInfoService.selectByPrimaryKey(entBaseInfoUuid);
		customerService.updateByPrimaryKey(customer);
		model.addAttribute("processState", processState);
		model.addAttribute("entBaseInfo", ent);
		model.addAttribute("entResult", entResult);
		if(ent != null){
			request.getSession().setAttribute(SessionName.ENTNAME, ent.getName());
			return "enterprise/main";
		}else{
			return "redirect:/customer/logonUI.do";
		}
		
	}
	/**
	 * @title 进入机构首页
	 * @author  孙尚飞  2017-7-27
	 * @desc
	 */
	@Permission(model = "organization", privilegeValue = "main")
	@RequestMapping("/jumpToOrgMain")
	public String jumpToOrgMain(HttpServletRequest request, Model model) {
		logger.info(moduleName + "[进入平台mainOrg.jsp首页]");
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer == null){
			return "redirect:/customer/logonUI.do";
		}
		String ip = WebUtil.getIpAddress(request);
		customer.setLastloginip(ip);
		customer.setLogintimes(customer.getLogintimes()+ 1);
		customerService.updateByPrimaryKey(customer);
		Organization organization = organizationService.selectByPrimaryKey(customer.getOrgid());
		if(organization != null){
			request.getSession().setAttribute(SessionName.ENTNAME, organization.getName());
			return "organization/mainOrg";
		}else{
			return "redirect:/customer/logonUI.do";
		}
	}
	/**
	 * @title 进入政府首页
	 * @author  孙尚飞  2017-7-27
	 * @desc
	 */
	@Permission(model = "government", privilegeValue = "main")
	@RequestMapping("/jumpToGovMain")
	public String jumpToGovMain(HttpServletRequest request, Model model) {
		logger.info(moduleName + "[进入平台mainGov.jsp首页]");
		
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer == null){
			return "redirect:/customer/logonUI.do";
		}
		String ip = WebUtil.getIpAddress(request);
		customer.setLastloginip(ip);
		customer.setLogintimes(customer.getLogintimes()+ 1);
		customerService.updateByPrimaryKey(customer);
		Organization organization = organizationService.selectByPrimaryKey(customer.getOrgid());
		if(organization != null){
			request.getSession().setAttribute(SessionName.ENTNAME, organization.getName());
			return "government/mainGov";
		}else{
			return "redirect:/customer/logonUI.do";
		}
	}
	/**
	 * @title 进入个人首页
	 * @author  孙尚飞  2017-7-27
	 * @desc
	 */
	@Permission(model = "personal", privilegeValue = "main")
	@RequestMapping("/jumpToPerMain")
	public String jumpToPerMain(HttpServletRequest request, Model model) {
		logger.info(moduleName + "[进入平台mainGov.jsp首页]");
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer == null){
			return "redirect:/customer/logonUI.do";
		}
		String perid = customer.getPerid();
		String ip = WebUtil.getIpAddress(request);
		customer.setLastloginip(ip);
		customer.setLogintimes(customer.getLogintimes()+ 1);
		customerService.updateByPrimaryKey(customer);
		
		//驳回项
		PerOpinion  perOpinion = perOpinionService.selectByPerIDReject(perid);
		if(perOpinion!=null){
			if(perOpinion.getIsconfirm() == 1){
				request.getSession().setAttribute("reject", 1);
			}else{
				request.getSession().setAttribute("reject", 0);
			}
		}else{
			request.getSession().setAttribute("reject", 1);
		}
		//获取历史
		if(perHistoryService.exists(perid)){
			request.getSession().setAttribute("historical","1");
		}else{
			request.getSession().setAttribute("historical","0");
		}
		//根据entBaseInfoUuid 查找 EntResult 对象获取评分情况
		model.addAttribute("processState", perProcessService.selectByPerID(perid));
		model.addAttribute("entResult", perResultService.selectByPerID(perid));
		
		
		return "personal/mainPer";
	}

	/**
	 * @title 退出平台
	 * @author  孙尚飞  2017-7-27
	 * @desc
	 */
	@RequestMapping("/quit")
	public String quit(HttpServletRequest request, Model model)
			throws Exception {
		request.getSession().removeAttribute(SessionName.CUSTOMER);
		request.getSession().removeAttribute(SessionName.ENTNAME);
		logger.info(moduleName + "[退出登录]");
		return "redirect:/customer/logonUI.do";
	}
	
	/**
	 * @title 跳转进入注册页面
	 * @author  孙尚飞  2017-7-27
	 * @desc
	 */
	@RequestMapping("/jumpToRegist")
	public String jumpToRegist(HttpServletRequest request, Model model) {
		System.out.println("进入注册页面");
		logger.info(moduleName + "[进入注册页面]");
		ServletContext application = request.getSession().getServletContext();
		String orgTwoDomainNames = request.getRequestURL().toString();
		String twoDomainNames = orgTwoDomainNames.substring(7,orgTwoDomainNames.length());
		/*@SuppressWarnings("unchecked")
		Map<String, Organization> organizationMap = (Map<String, Organization>) application.getAttribute("organizations");
		Organization organization = organizationMap.get(twoDomainNames.substring(0, twoDomainNames.indexOf("/")).trim());
		model.addAttribute("organization", organization);*/
		return "regist";
	}
	/**
	 * @title 企业客户注册
	 * @author  孙尚飞  2017-7-27
	 * @desc  从dataservice 数据库获取   如果存在相关数据  直接录入
	 */
	@RequestMapping("/registEnt")
	@ResponseBody
	public Map<String, Object> registEnt(Customer customer,EntBaseInfo entBaseInfo,
			HttpServletRequest request) throws Exception {
		logger.info(moduleName + "[注册验证]");
		message.setLength(0);
		flag = true;
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		MD5Code md5 = new MD5Code();
		String codeNum=request.getParameter("codeNum");
		String rluuid=(String) request.getSession().getAttribute("RLUUID");
		request.getSession().removeAttribute("RLUUID");
		boolean isregclose=false;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date nowdate=new Date();
		String date=format.format(nowdate);
		String startDate=date+" 00:00:00";
		String endDate=date+" 23:59:59";
		if(SecurityUtil.getMsg("EnableRegOpenTime").equals("1")){
			if(DateUtil.isClose(SecurityUtil.getMsg("RegOpenTime"))){
				isregclose=true;
			}
		}
		if(SecurityUtil.getMsg("EnableReg").equals("0")){
			message.append("该网站已禁止注册");
			flag = false;
		}else{
			if(isregclose){
				message.append("系统未开放注册功能，关闭时段为"+DateUtil.getCloseTime(SecurityUtil.getMsg("RegOpenTime")));
				flag = false;
			}else{
				if(!registLimitService.NumLimit(startDate, endDate)){
					message.append("今日注册已超过注册人数上限");
					flag = false;
				}
			}
		}
		
		if (!(customer.getCellphone().trim().equals(""))) {
			params.clear();
			params.put("cellphone", customer.getCellphone().trim().toLowerCase());
			if (customerService.selectByParam(params) != null) {
				message.append("联系电话已存在");
				flag = false;
			}else{
				String SmsCode=(String) request.getSession().getAttribute(SessionName.SMSCODE);
				if(SmsCode==null){
					message.append("您尚未获取短信验证码，无法注册");
					flag = false;
				}else{
					if(!SmsCode.equals(codeNum)){
						message.append("短信验证码输入错误");
						flag = false;
					}
				}
			}
		}
		String ip = WebUtil.getIpAddress(request);
		EntBaseInfo ent = new EntBaseInfo();
		Customer customer1 = new Customer();
		String username="hyt"+RandomUtil.getRandomNumber(8);
		if(entBaseInfo.getName() != null){
			ent =entBaseInfoService.selectByUscc(entBaseInfo.getUscc());
			if(ent!=null){
				message.append("该企业已注册，请勿重复注册！</p>");
				flag = false;
			}
		}
		if (flag) {
			message.append("操作成功");
			System.out.println(customer.getRealname());

			customer1.setPassword(md5.getMD5ofStr(customer.getPassword().trim()));
			if (!(customer.getRealname().equals(""))){
				customer1.setRealname(customer.getRealname().trim());
			}
			if (!(customer.getCellphone().trim().equals(""))) {
				customer1.setCellphone(customer.getCellphone().toLowerCase().trim());
			}
			customer1.setUsername(username.toLowerCase());
			customer1.setLastlogintime(DateTime.getCurrentTimeStamp());
			customer1.setLogintimes(0);
			String entID="";
			if(ent!=null){
				entID=ent.getUuid();
				ent.setName(entBaseInfo.getName());
				entBaseInfoService.updateByPrimaryKeySelective(ent);
			}else{
				entID=UUID.randomUUID().toString().replace("-", "");
				EntBaseInfo entbaseinfo = new EntBaseInfo();
				entbaseinfo.setUuid(entID);
				entbaseinfo.setName(entBaseInfo.getName());
				entbaseinfo.setUscc(entBaseInfo.getUscc());
				entbaseinfo.setCreatetime(DateTime.getCurrentTimeStamp());
				entbaseinfo.setUpdatetime(DateTime.getCurrentTimeStamp());
				//从dataservice 数据库 获取数据
				QueryResult  partnerList = null;
				QueryResult  vipMemberList = null;
				try{
					QueryResult queryResult = baseInterface.accurateSelect(1, 10, entBaseInfo.getName(), null, entBaseInfo.getUscc());
					if(queryResult != null){
						List<Object>  queryResults = queryResult.getResultlist();
						if(queryResults != null && queryResults.size()>0){
							Enterprise enterprise = (Enterprise) queryResults.get(0);
							entbaseinfo.setAddress(enterprise.getAddress());//地址
							entbaseinfo.setAreacode(enterprise.getAreacode());//地区编码
							entbaseinfo.setBrief(enterprise.getBrief());//简介
							String date1 = enterprise.getBusinessperiod();//营业时间 开始-结束
							String [] array = date1.split("-");
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							if(array.length == 6){
								String data1 = array[0].trim()+"-"+array[1].trim()+"-"+array[2].trim();
								String data2 = array[3].trim()+"-"+array[4].trim()+"-"+array[5].trim();
								entbaseinfo.setStartdate(dateFormat.parse(data1));
								entbaseinfo.setEnddate(dateFormat.parse(data2));
							}else if(array.length == 3){
								Date data1 = dateFormat.parse(array[0].trim()+"-"+array[1].trim()+"-"+array[2].trim());
								Date ndate  = new Date();
								if(ndate.getTime() > data1.getTime()){
									entbaseinfo.setStartdate(data1);
								}else{
									entbaseinfo.setEnddate(data1);
								}
							}
							XMLGregorianCalendar xmlGregorianCalendar = enterprise.getIssuedate();
							if(xmlGregorianCalendar!= null && !"".equals(xmlGregorianCalendar)){
								Date issuedate = xmlGregorianCalendar.toGregorianCalendar().getTime();
								entbaseinfo.setIssuedate(issuedate);//发照日期
							}
							entbaseinfo.setBusinessscope(enterprise.getBusinessscope());//经营范围
							entbaseinfo.setCurrencytype(enterprise.getCurrencytype());//币种
							entbaseinfo.setEmail(enterprise.getEmail());//电子邮箱
							entbaseinfo.setEname(enterprise.getEnglishname());//英文名称
							entbaseinfo.setEnttype(enterprise.getEnttype());//企业类型
							entbaseinfo.setFax(enterprise.getFax());//传真
							entbaseinfo.setIndustry(enterprise.getIndustry());//行业类型
							entbaseinfo.setIndustrycode(enterprise.getIndustrycode());//行业类型
							entbaseinfo.setLegalperson(enterprise.getLegalperson());//法人
							String regiscapital = enterprise.getRegiscapital();
							if(regiscapital != null && ! "".equals(regiscapital.trim())){
								String[] regiscapital1 = regiscapital.split("万");
								entbaseinfo.setRegisorg(regiscapital1[0]+"万");//注册资本
							}
							entbaseinfo.setRegisorg(enterprise.getRegisorg());//注册  单位（工商管理局）
							entbaseinfo.setScale(enterprise.getScale());//规模
							entbaseinfo.setTel(enterprise.getTel());//电话
							entbaseinfo.setWebsite(enterprise.getWebsite());//官网
							entbaseinfo.setZipcode(enterprise.getZipcode());//邮编
							//获取股东信息  和  高管信息
							try{
							partnerList = baseInterface.selectPartnerList(1, 10, enterprise.getUuid());
							vipMemberList = baseInterface.selectVIPMemberList(1, 10, enterprise.getUuid());
							}catch (Exception e) {
								logger.error(moduleName + " Error：获取DataService股东信息、高管信息接口失败 ");
								System.out.println(moduleName + " Error：获取DataService股东信息、高管信息接口失败");
								System.out.println(moduleName +" "+ e.getMessage());
							}
						}
					}
				}catch (Exception e) {
					logger.error(moduleName + " Error： DataService interface data waiting time is too long  ");
					System.out.println(moduleName + " Error：DataService interface data waiting time is too long  ");
					System.out.println(moduleName +" "+ e.getMessage());
				}
				entBaseInfoService.insertSelective(entbaseinfo);
				//股东
				if(partnerList != null && partnerList.getResultlist().size() > 0){
					for (Object object : partnerList.getResultlist()) {
						Partner partner = (Partner) object;
						Shareholder shareholder = new Shareholder();
						shareholder.setName(partner.getPartnername());
						String realcapi =partner.getRealcapi();
						if(realcapi != null && ! "".equals(realcapi.trim())){
							String []str = realcapi.split("万");
							int lenght = str[0].indexOf(".");
							if(lenght <= 0){
								lenght = str[0].length();
							}else{
								lenght = str[0].indexOf(".")+3;
							}
							String s = str[0].substring(0,lenght);
							shareholder.setRealcapi(s+"万");
						}
						String shouldcapi = partner.getShouldcapi();
						if(shouldcapi != null && ! "".equals(shouldcapi.trim())){
							String []str = shouldcapi.split("万");
							int lenght = str[0].indexOf(".");
							if(lenght <= 0){
								lenght = str[0].length();
							}else{
								lenght = str[0].indexOf(".")+3;
							}
							String s = str[0].substring(0, lenght);
							shareholder.setShouldcapi(s+"万");
						}
						shareholder.setStockpercent(partner.getStockpercent());
						shareholder.setEntid(entID);
						shareholder.setUuid(UUID.randomUUID().toString().replace("-", ""));
						shareholderService.insertSelective(shareholder);
					}
				}
				//高管
				if(vipMemberList != null && vipMemberList.getResultlist().size() > 0){
					for (Object object : vipMemberList.getResultlist()) {
						VipMember vipMember = (VipMember) object;
						Executives executives = new Executives();
						executives.setJob(vipMember.getJob());
						executives.setName(vipMember.getName());
						executives.setEntid(entID);
						executives.setUuid(UUID.randomUUID().toString().replace("-", ""));
						executivesService.insertSelective(executives);
					}
				}
			}
			customer1.setEntid(entID);
			customer1.setRegip(ip);
			customer1.setLastloginip(ip);
			customer1.setRegtime(DateTime.getCurrentTimeStamp());
			customer1.setUpdatetime(DateTime.getCurrentTimeStamp());
			customer1.setVisible(0);
			customer1.setDomainname(request.getServerName());
			if(SecurityUtil.getMsg("EnableRegAdminConfirm").equals("0")){
				customer1.setVisible(1);
			}else{
				customer1.setVisible(0);
			}
			customerService.insertSelective(customer1);
			C_Customer_RoleKey key=new C_Customer_RoleKey();
			key.setGroupid(privilegeGroupService.selectByRoleName("企业"));
			key.setUsername(customer1.getUsername());
			privilegeGroupService.insertCustomerRole(key);
			RegistLimit rl= registLimitService.selectByPrimaryKey(rluuid);
			if(rl!=null){
				rl.setIssuccess(1);
				registLimitService.updateByPrimaryKey(rl);
			}
			msgMap.put("status", true);
			msgMap.put("msg", "恭喜您，注册成功，我们会在两个工作日之内激活。");
			logger.info(moduleName + "[注册成功]");
		} else {
			msgMap.put("status", false);
			msgMap.put("msg", message.toString());
			logger.error(moduleName + "[注册失败]");
		}
		return msgMap;
	}
	/**
	 * @title 个人客户注册
	 * @param customer
	 * @param entBaseInfo
	 * @param request
	 * @return Map<String, Object>
	 * @throws Exception
	 * @desc
	 */
	@RequestMapping("/registPersonal")
	@ResponseBody
	public Map<String, Object> registPersonal(Customer customer,String idcard,String codeNum,HttpServletRequest request) throws Exception {
		logger.info(moduleName + "[注册验证]");
		System.out.println(idcard+" : "+codeNum);
		message.setLength(0);
		flag = true;
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		MD5Code md5 = new MD5Code();
		String rluuid=(String) request.getSession().getAttribute("RLUUID");
		request.getSession().removeAttribute("RLUUID");
		boolean isregclose=false;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date nowdate=new Date();
		String date=format.format(nowdate);
		String startDate=date+" 00:00:00";
		String endDate=date+" 23:59:59";
		if(SecurityUtil.getMsg("EnableRegOpenTime").equals("1")){
			if(DateUtil.isClose(SecurityUtil.getMsg("RegOpenTime"))){
				isregclose=true;
			}
		}
		if(SecurityUtil.getMsg("EnableReg").equals("0")){
			message.append("该网站已禁止注册");
			flag = false;
		}else{
			if(isregclose){
				message.append("系统未开放注册功能，关闭时段为"+DateUtil.getCloseTime(SecurityUtil.getMsg("RegOpenTime")));
				flag = false;
			}else{
				if(!registLimitService.NumLimit(startDate, endDate)){
					message.append("今日注册已超过注册人数上限");
					flag = false;
				}
			}
		}
		
		if (!(customer.getCellphone().trim().equals(""))) {
			params.clear();
			params.put("cellphone", customer.getCellphone().trim().toLowerCase());
			if (customerService.selectByParam(params) != null) {
				message.append("联系电话已存在");
				flag = false;
			}else{
				String SmsCode=(String) request.getSession().getAttribute(SessionName.SMSCODE);
				if(SmsCode==null){
					message.append("您尚未获取短信验证码，无法注册");
					flag = false;
				}else{
					if(!SmsCode.equals(codeNum)){
						message.append("短信验证码输入错误");
						flag = false;
					}
				}
			}
		}
		String ip = WebUtil.getIpAddress(request);
		Customer customer1 = new Customer();
		String username="hyt"+RandomUtil.getRandomNumber(8);
		if (flag) {
			message.append("操作成功");
			PerBaseInfo baseInfo = new PerBaseInfo();
			baseInfo.setUuid(UUID.randomUUID().toString().replace("-", ""));
			baseInfo.setCreatetime(DateTime.getCurrentTimeStamp());
			baseInfo.setUsedname(customer1.getRealname());
			baseInfo.setIdcard(idcard);
			perBaseInfoService.insertSelective(baseInfo);
			customer1.setPassword(md5.getMD5ofStr(customer.getPassword().trim()));
			if (!(customer.getRealname().equals(""))){
				customer1.setRealname(customer.getRealname().trim());
			}
			if (!(customer.getCellphone().trim().equals(""))) {
				customer1.setCellphone(customer.getCellphone().toLowerCase().trim());
			}
			customer1.setUsername(username.toLowerCase());
			customer1.setLastlogintime(DateTime.getCurrentTimeStamp());
			customer1.setLogintimes(0);
			
			customer1.setRegip(ip);
			customer1.setLastloginip(ip);
			customer1.setRegtime(DateTime.getCurrentTimeStamp());
			customer1.setUpdatetime(DateTime.getCurrentTimeStamp());
			customer1.setVisible(0);
			customer1.setDomainname(request.getServerName());
			if(SecurityUtil.getMsg("EnableRegAdminConfirm").equals("0")){
				customer1.setVisible(1);
			}else{
				customer1.setVisible(0);
			}
			customer1.setType(3);
			customer1.setPerid(baseInfo.getUuid());
			customerService.insertSelective(customer1);
			C_Customer_RoleKey key=new C_Customer_RoleKey();
			key.setGroupid(privilegeGroupService.selectByRoleName("个人"));
			key.setUsername(customer1.getUsername());
			privilegeGroupService.insertCustomerRole(key);
			RegistLimit rl= registLimitService.selectByPrimaryKey(rluuid);
			if(rl!=null){
				rl.setIssuccess(1);
				registLimitService.updateByPrimaryKey(rl);
			}
			msgMap.put("status", true);
			msgMap.put("msg", "恭喜您，注册成功，我们会在两个工作日之内激活。");
			logger.info(moduleName + "[注册成功]");
		} else {
			msgMap.put("status", false);
			msgMap.put("msg", message.toString());
			logger.error(moduleName + "[注册失败]");
		}
		return msgMap;
	}
	/**
	 * @title 发送短信验证码
	 * @author  孙尚飞  2017-7-28
	 * @desc
	 */
	@RequestMapping("/sendSMS")
	@ResponseBody
	public Map<String, Object> sendSMS(HttpServletRequest request,HttpServletResponse response) throws Exception {
		logger.info( "发送短信验证码sendSMS;cellphone:"+request.getParameter("cellphone"));
		Map<String, Object> msgMap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		message.setLength(0);
		flag = true;
		Date nowdate=new Date();
		String date=sdf.format(nowdate);
		String startDate=date+" 00:00:00";
		String endDate=date+" 23:59:59";
		String ip=WebUtil.getIpAddress(request);
		String cellphone=request.getParameter("cellphone");
		String findpwd=request.getParameter("findpwd");
		request.getSession().removeAttribute(SessionName.SMSCODE);
		
		Map<String, Object> params=new HashMap<String, Object>();
		Map<String, String> paramMap=new HashMap<String, String>();
		params.put("cellphone", cellphone);
		if(cellphone==null&&"".equals(cellphone)){
			message.append("网络异常，请检查网络");
			flag = false;
		}
		if(findpwd==null||"".equals(findpwd)){
			params.put("cellphone", cellphone);
			if(customerService.selectByParam(params)!=null){
				message.append("电话号码被占用");
				flag = false;
			}else{
				if(!registLimitService.IPLimit(ip, startDate, endDate)){
					message.append("IP地址已被锁定，请于明日再进行注册");
					flag = false;
				}else{
					if(!registLimitService.PhoneLimit(cellphone, startDate, endDate)){
						message.append("该电话号码已被锁定，请于明日再进行注册");
						flag = false;
					}
				}
			}
		}else{
			params.put("cellphone", cellphone);
			if(customerService.selectByParam(params)==null){
				message.append("该电话号码未被注册过");
				flag = false;
			}
		}
		if(flag){
			String SMSCode=RandomUtil.getRandomNumber(6);
			request.getSession().setAttribute(SessionName.SMSCODE,SMSCode);
			paramMap.put("code", SMSCode);
			System.out.println("发送短信验证码");
			String uuid=UUID.randomUUID().toString().replace("-", "");
			request.getSession().setAttribute("RLUUID",uuid);
			RegistLimit rl=new RegistLimit();
			rl.setIp(WebUtil.getIpAddress(request));
			rl.setPhone(cellphone);
			rl.setRegisttime(new Date());
			rl.setUuid(uuid);
			rl.setIssuccess(0);
			registLimitService.insertSelective(rl);
			if(AliyunUtil.sendSMS(cellphone, paramMap,AliyunUtil.aliyunMsg("SMS_model1"))){
				System.out.println("短信验证码发送成功");
				msgMap.put("status", true);
				msgMap.put("msg", "短信验证码发送成功");
			}else{
				System.out.println("短信验证码发送失败");
				msgMap.put("status", false);
				msgMap.put("msg", "短信验证码发送失败");
			}

		}else{
			msgMap.put("status", false);
			msgMap.put("msg", message.toString());
		}
		return msgMap;
	}
	/**
	 * @title 进入找回密码首页
	 * @author  孙尚飞  2017-7-27
	 * @desc
	 */
	@RequestMapping("/findPWDByCellphoneUI")
	public String findPWDByCellphoneUI(HttpServletRequest request) throws Exception {
		logger.info( "客户找回密码findPWDByCellphoneUI");
		return "/findPWDByCellphone";
	}
	/**
	 * @title 通过手机号找回密码
	 * @author  孙尚飞  2017-10-12
	 * @desc
	 */
	@RequestMapping("/findPWDByCellPhone")
	@ResponseBody
	public Map<String, Object> findPWDByCellPhone(HttpServletRequest request) throws Exception {
		logger.info( "客户找回密码findPWDByCellPhone;cellphone:"+request.getParameter("cellphone"));
		Map<String, Object> params=new HashMap<String, Object>();
		Map<String, Object> msgMap = new HashMap<String, Object>();
		params.put("cellphone", request.getParameter("cellphone"));
		Customer member=customerService.selectByParam(params);
		if (member == null) {
			msgMap.put("code", -1);
			msgMap.put("info", "该用户不存在");
		} else {
			String SmsCode=(String) request.getSession().getAttribute(SessionName.SMSCODE);
			if(SmsCode==null){
				msgMap.put("code", -2);
				msgMap.put("info","您尚未获取短信验证码，无法进行验证" );
			}else{
				if(!SmsCode.equals(request.getParameter("codeNum").trim())){
					msgMap.put("code",-2 );
					msgMap.put("info","短信验证码输入错误" );
				}else{
					MD5Code md5 = new MD5Code();
					String validateCode=md5.getMD5ofStr(member.getUsername()+member.getPassword());
					msgMap.put("code",1);
					msgMap.put("acturl", "/customer/resetPassword.do?uuid="+member.getUsername()+"&"+"validateCode="+validateCode);
				}
			}
		}
		return msgMap;
	}
	/**
	 * @title 重置密码
	 * @author  孙尚飞  2017-10-12
	 * @desc
	 */
	@RequestMapping("/resetPassword")
	public String resetPassword(HttpServletRequest request,String uuid,String validateCode) throws Exception{
		//测试地址：http://localhost/client/member/findpwd/resetPassword.do?uuid=50341b2c881041e5bcd3c1fa5977303f&validateCode=3E760F3465C0A09DDD73EEC261AF1A55
		logger.info( "客户重置密码resetPassword;uuid:"+uuid+";validateCode:"+validateCode);
		System.out.println(uuid);
		System.out.println(validateCode);
		MD5Code md5 = new MD5Code();
		boolean flag=true;
		if(validateCode == null ||validateCode.equals("")){
			flag = false;
		}else{
				Customer member = customerService.selectByPrimaryKey(uuid);
				if (member == null) {
					System.out.println("该用户不存在!");
					flag = false;
				} else{
					if(validateCode.equals(md5.getMD5ofStr(member.getUsername()+member.getPassword()))){
						System.out.println(md5.getMD5ofStr(member.getUsername()+member.getPassword()));
					}else{
						flag = false;
					}
				}
		}
		if (flag) {
				request.setAttribute("validateCode", validateCode);	
				request.setAttribute("uuid", uuid);	
				return	"/resetPassword";
		}else{
			return "redirect:/customer/logonUI.do";
		}
	}
	/**
	 * @title 修改密码
	 * @author  孙尚飞  2017-10-12
	 * @desc
	 */
	
	@RequestMapping("/editPassword")
	@ResponseBody
	public Map<String, Object> editPassword(HttpServletRequest request,HttpServletResponse response) throws Exception{
		logger.info( "客户修改密码editPassword;uuid:"+request.getParameter("uuid"));
		Map<String, Object> msgMap = new HashMap<String, Object>();
		System.out.println("密码1："+request.getParameter("password"));
		System.out.println("密码2："+request.getParameter("password2"));
		Customer member = customerService.selectByPrimaryKey(request.getParameter("uuid"));
		if(member==null){
			msgMap.put("code", -1);
			msgMap.put("info","该用户不存在");
		}else{
			if(!request.getParameter("password").equals(request.getParameter("password2"))){
				msgMap.put("code", -2);
				msgMap.put("info","两次密码输入不一致");
			}else{
				MD5Code md5 = new MD5Code();
				System.out.println(md5.getMD5ofStr(request.getParameter("password")));
				member.setPassword(md5.getMD5ofStr(request.getParameter("password")));
				customerService.updateByPrimaryKey(member);
				msgMap.put("code", 1);
				msgMap.put("info", "修改密码成功");
				System.out.println("修改密码成功");
			}
		}	
		return msgMap;
	}	
	/**
	 * @title 注册时验证手机号
	 * @author  孙尚飞  2017-7-27
	 * @desc
	 */
	@RequestMapping("/cellphoneExist")
	@ResponseBody
	public Map<String, Object> cellphoneExist(String cellphone,
			HttpServletRequest request) {
		logger.info(moduleName + "[电话验证]");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cellphone", cellphone.trim());

		if (!Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$").matcher(cellphone.trim())
				.matches()) {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "格式错误！");
		} else {
			Customer mem = customerService.selectByParam(params);
				if (mem != null) {
					msgMap.put("success", true);
					msgMap.put("status", false);
					msgMap.put("msg", "已存在！");
					logger.error(moduleName + "[电话验证失败]");
				} else {
					msgMap.put("success", true);
					msgMap.put("status", true);
					msgMap.put("msg", "输入合法！");
					logger.info(moduleName + "[电话验证成功]");
				}
			}
	
		return msgMap;
	}
	//效验ip
		protected boolean ipValidate(HttpServletRequest request) {
			System.out.println("IP效验开始");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String IP=WebUtil.getIpAddress(request);
			System.out.println("IP："+IP);
			boolean flag=true;
			//获取库内IP黑名单
				List<IPLock> ips=ipLockService.selectIPList(1);
			if(!ips.isEmpty()){
				for(IPLock ip:ips){
					if(ip.getIp()!=null&&ip.getIsforever()!=null&&ip.getIslimit()!=null&&ip.getAddtime()!=null){
						//判断是否为IP段
						if(ip.getIslimit()==1){
							//IP段
							if(ip.getIplimit()!=null){
								String ipSection=ip.getIp()+"-"+ip.getIplimit();
								boolean isblack=IPUtil.ipExistsInRange(IP, ipSection);
								if(isblack){
									//判断是否为永久封锁
									if(ip.getIsforever()==1){
										//永久封锁
										flag=false;
									}else{
										//暂时封锁
										if(ip.getLockdate()!=null){
											boolean isindate=DateUtil.judgeInDate(new Date(), format.format(ip.getAddtime()), DateUtil.addDate(ip.getAddtime(), ip.getLockdate()));
											if(isindate){
												flag=false;
											}
										}
									}
								}
							}
						}else{
							//固定IP
							if(ip.getIp().trim().equals(IP)){
								//判断是否为永久封锁
								if(ip.getIsforever()==1){
									//永久封锁
									flag=false;
								}else{
									//暂时封锁
									if(ip.getLockdate()!=null){
										boolean isindate=DateUtil.judgeInDate(new Date(), format.format(ip.getAddtime()), DateUtil.addDate(ip.getAddtime(), ip.getLockdate()));
										if(isindate){
											flag=false;
										}
									}
								}
							}
						}
					}
				}
			}
			System.out.println("IP效验结束");
		    return flag;
		}
}