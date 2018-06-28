package com.credit.controller.enterprise;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.model.addition.Model;
import com.credit.model.addition.UploadFileCategory;
import com.credit.model.enterprise.EntBaseInfo;
import com.credit.model.enterprise.EntResult;
import com.credit.model.enterprise.Executives;
import com.credit.model.enterprise.Finance;
import com.credit.model.enterprise.ProcessState;
import com.credit.model.enterprise.Shareholder;
import com.credit.model.enterprise.UploadFile;
import com.credit.model.member.Customer;
import com.credit.modelvo.Permission;
import com.credit.modelvo.SessionName;
import com.credit.modelvo.UploadFileVO;
import com.credit.service.addition.IndustryService;
import com.credit.service.addition.ModelService;
import com.credit.service.addition.UploadFileCategoryService;
import com.credit.service.enterprise.EntBaseInfoService;
import com.credit.service.enterprise.EntResultService;
import com.credit.service.enterprise.ExecutivesService;
import com.credit.service.enterprise.FinanceService;
import com.credit.service.enterprise.ProcessStateService;
import com.credit.service.enterprise.ShareholderService;
import com.credit.service.enterprise.UploadFileService;
import com.credit.util.DateTime;
import com.credit.util.RandomUtil;
import com.credit.util.SaveFile;
import com.credit.util.financialexecl.ExcelShower;
import com.credit.util.model.Index;
import com.credit.util.model.ModelUtil;
import com.credit.util.model.Option;
import com.credit.util.properties.BusinessUtil;
import com.credit.util.security.SecurityUtil;
/**
 * @title 企业录入信息 控制层
 * @author ssf   2017-7-25
 * @desc
 */
@Controller
@RequestMapping("/initEnterprise")
public class EnterpriseController {



	private static final Logger logger = Logger.getLogger(EnterpriseController.class);

	private String moduleName = "用户操作情况";
	
	/*信息更新状态*/
	@Resource
	private ProcessStateService processStateService;
	/*企业基础信息*/
	@Resource
	private EntBaseInfoService entBaseInfoService;
	/*股东信息*/
	@Resource
	private ShareholderService shareholderService;
	/*高管信息*/
	@Resource
	private ExecutivesService executivesService ;
	/*评分结果*/
	@Resource
	private EntResultService entResultService ;
	/*模型*/
	@Resource
	private ModelService<?> modelService ;
	/*附件分类*/
	@Resource
	private UploadFileCategoryService<?> uploadFileCategoryService ;
	/*附件*/
	@Resource
	private UploadFileService uploadFileService ;
	/*财务数据*/
	@Resource
	private FinanceService financeService;
	/*行业信息*/
	@Resource
	private IndustryService industryService;
	
	/**
	 * @Title spring mvc在绑定表单之前，会先进入该方法（从jsp -> java）本方法是str转换为date类型
	 * 	或者使用   实体类 属性上注入方式（    @DateTimeFormat(pattern = "yyyy-MM-dd")  ）
	 * @author  Administrator  @date 2017-8-23 
	 * @Description 
	 *
	 */
	@InitBinder  
    public void initBinder(WebDataBinder binder) {  
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
    	dateFormat.setLenient(false);  
    	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); //"true":允许输入空值，"false":不能为空值 
    }
	
	
	/**
	 * @Title 加载录入信息界面 
	 * @author  Administrator  @date 2017-8-20 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "inputInfo")
	@RequestMapping("/jumpIntoEnterprise")
	public String jumpIntoEnterprise(HttpServletRequest request) {
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer != null){
			//日志记录
			logger.info(moduleName + "[企业数据录入信息界面]");
			return "enterprise/mainProcess";
		}else{
			//页面 获取session失败   ，则 跳转到登录界面
			return "redirect:/customer/logonUI.do";
		}
	}
	/**
	 * @Title 异步加载 状态信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "processState")
	@RequestMapping("/listProcessState")
	@ResponseBody
	public Map<String, Object> listProcessState(HttpServletRequest request) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String entBaseInfoUuid = customer.getEntid();
		ProcessState processState =  processStateService.selectByEntBaseInfoKey(entBaseInfoUuid);
		if(processState == null){
			processState = new ProcessState();
			processState.setUuid(UUID.randomUUID().toString().replace("-", ""));
			processState.setEntid(entBaseInfoUuid);
			processStateService.insertSelective(processState);
		}else{
			if(processState.getApplyreportstate() == 1){
				List<String> strs = uploadFileService.judgeFileAllUpload(entBaseInfoUuid,null);
				List<String> fileAllUploadName = new ArrayList<String>();
				for (String string : strs) {
					String[] s = string.split("#");
					if(s.length==2){
						fileAllUploadName.add(s[0]);
					}
				}
				System.out.println(fileAllUploadName);
				msgMap.put("fileAllUploadName", fileAllUploadName);
			}
		}
		
		//日志记录
		logger.info(moduleName + "[查询 企业数据录入状态信息]");
		msgMap.put("processState", processState);
		return msgMap;
	}
	
	/**
	 * @Title 异步加载 状态信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "processState")
	@RequestMapping("/updateProcessState")
	@ResponseBody
	public Map<String, Object> updateProcessState(HttpServletRequest request) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String entBaseInfoUuid = customer.getEntid();
		ProcessState processState =  processStateService.selectByEntBaseInfoKey(entBaseInfoUuid);
		if(processState == null){
			msgMap.put("msg", "申请 提交失败，请重新尝试");
		}else{
			if(processState.getExecutivesstate() == 1 && processState.getShareholderstate() == 1 
					&& processState.getBaseinfostate() == 1&&processState.getPushmodelstate()==1
					&& processState.getFinancestate() == 1&&processState.getUploadfilestate()==1){
				processState.setApplyreportstate(1);
				processStateService.updateByPrimaryKeySelective(processState);
				msgMap.put("msg", "申请 提交成功");
			}else{
				msgMap.put("msg", "申请 提交失败，必填项不完整");
			}
		}
		//日志记录
		logger.info(moduleName + "[更新 企业数据录入状态信息为 已提交]");
		msgMap.put("processState", processState);
		return msgMap;
	}
	/**
	 * @Title 异步加载 企业信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "entBaseInfo")
	@RequestMapping("/listEntBaseInfo")
	@ResponseBody
	public Map<String, Object> listEntBaseInfo(HttpServletRequest request) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String entBaseInfoUuid = customer.getEntid();
		if(entBaseInfoUuid != null){
			//日志记录
			logger.info(moduleName + "[查询企业基础信息]");
			msgMap.put("entBaseInfo", entBaseInfoService.selectByPrimaryKey(entBaseInfoUuid));
			msgMap.put("success", "true");
			msgMap.put("sturts", "true");
		}else{
			msgMap.put("success", "true");
			msgMap.put("sturts", "false");
		}
		return msgMap;
	}
	/**
	 * @Title 获取所有行业信息
	 * @author  Administrator  @date 2017-9-20 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "entBaseInfo")
	@RequestMapping("/findRootName")
	@ResponseBody
	public Map<String, Object> findRootName() {
		logger.info(moduleName + "[所有行业类型]");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		msgMap.put("list", industryService.findAll());
		return msgMap;
	}
	
	/**
	 * @Title 更新 企业基础信息表
	 * @author  Administrator  @date 2017-8-20 
	 * @throws ParseException 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "entBaseInfo")
	@RequestMapping("/updateEntBaseInfo")
	@ResponseBody
	public Map<String, Object> updateEntBaseInfo(HttpServletRequest request, EntBaseInfo baseInfo) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (baseInfo != null) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			//更新 企业信息表的更新时间
			baseInfo.setUpdatetime(DateTime.getCurrentTimeStamp());
			//更新企业信息表
			entBaseInfoService.updateByPrimaryKeySelective(baseInfo);
			//向前台 发送数据 ajax
			msgMap.put("entBaseInfo", entBaseInfoService.selectByPrimaryKey(baseInfo.getUuid()));
			msgMap.put("success", "true");
			msgMap.put("sturts", "true");
			//获取 信息完整度表单
			ProcessState processState =  processStateService.selectByEntBaseInfoKey(baseInfo.getUuid());
			if(processState != null){
				//设置完成表单项，更改状态
				processState.setBaseinfostate(1);
				processStateService.updateByPrimaryKeySelective(processState);
			}
			//日志记录
			logger.info(moduleName + "[更新企业基础信息]");
		} else {
			msgMap.put("msg", "参数错误");
			msgMap.put("success", "true");
			msgMap.put("sturts", "false");
		}
		return msgMap;
	}

	/**
	 * @Title 异步加载股东信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "shareholder")
	@RequestMapping("/listShareholder")
	@ResponseBody
	public Map<String, Object> listShareholder(HttpServletRequest request,String shareholderUuid) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String entBaseInfoUuid = customer.getEntid();
		if(shareholderUuid != null && ! "".equals(shareholderUuid.trim())){
			//日志记录
			logger.info(moduleName + "[编辑 股东信息]");
			Shareholder shareholder = shareholderService.selectByPrimaryKey(shareholderUuid);
			if(shareholder != null){
				msgMap.put("shareholder",shareholder);
				msgMap.put("edit", "true");
				msgMap.put("sturts", "true");
			}else{
				msgMap.put("sturts", "false");
				msgMap.put("msg", "未查询到 该条记录");
			}
		
		}else{
			//日志记录
			logger.info(moduleName + "[查询 股东信息]");
			if(entBaseInfoUuid != null && ! "".equals(entBaseInfoUuid.trim())){
				msgMap.put("shareholderList",shareholderService.selectByEntBaseInfo(entBaseInfoUuid));
				msgMap.put("edit", "false");
				msgMap.put("sturts", "true");
			}else{
				msgMap.put("sturts", "false");
				msgMap.put("msg", "未查询到 股东信息");
			}
		}
		msgMap.put("success", "true");
		
		return msgMap;
	}
	/**
	 * @Title 异步更新股东信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "shareholder")
	@RequestMapping("/updateShareholder")
	@ResponseBody
	public Map<String, Object> updateShareholder(HttpServletRequest request,Shareholder shareholder) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String entBaseInfoUuid = customer.getEntid();
		if(entBaseInfoUuid != null &&! "".equals(entBaseInfoUuid.trim())){
			List<Shareholder> shareholds=shareholderService.selectByEntBaseInfo(entBaseInfoUuid);
			double newstock=Double.parseDouble(shareholder.getStockpercent());
			double stock=0;
			for(Shareholder holder : shareholds){
				if(holder.getStockpercent()!=null||!"".equals(holder.getStockpercent())){
					if(shareholder.getUuid()!=null){
						if(!shareholder.getUuid().equals(holder.getUuid())){
							stock+=Double.parseDouble(holder.getStockpercent());
						}
					}else{
						stock+=Double.parseDouble(holder.getStockpercent());
					}
				}
			}
			System.out.println("stock："+stock);
			System.out.println("newstock："+newstock);
			if(stock+newstock<=100){
				if(shareholder.getUuid()!= null){
					shareholderService.updateByPrimaryKeySelective(shareholder);
					//日志记录
					logger.info(moduleName + "[更新企业股东信息]");
				}else{
					shareholder.setUuid(UUID.randomUUID().toString().replace("-",""));
					shareholder.setEntid(entBaseInfoUuid);
					shareholderService.insertSelective(shareholder);
					//日志记录
					logger.info(moduleName + "[创建企业股东信息]");
				}
				ProcessState processState =  processStateService.selectByEntBaseInfoKey(entBaseInfoUuid);
				if(processState != null){
					//设置股东的属性
					processState.setShareholderstate(1);
					processStateService.updateByPrimaryKeySelective(processState);
				}
				msgMap.put("success", "true");
				msgMap.put("sturts", "true");
			}else{
				msgMap.put("msg", "股比不能超过100%，请检查");
				msgMap.put("success", "true");
				msgMap.put("sturts", "false");
			}
		}else{
			msgMap.put("msg", "参数错误");
			msgMap.put("success", "true");
			msgMap.put("sturts", "false");
		}
		return msgMap;
	}
	/**
	 * @Title 异步删除股东信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "shareholder")
	@RequestMapping("/deleteShareholder")
	@ResponseBody
	public Map<String, Object> deleteShareholder(HttpServletRequest request,String shareholderUuid) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String entBaseInfoUuid = customer.getEntid();
			if(shareholderUuid != null && ! "".equals(shareholderUuid.trim())){
				shareholderService.deleteByPrimaryKey(shareholderUuid);
				
				if(entBaseInfoUuid != null && ! "".equals(entBaseInfoUuid.trim())){
					if(shareholderService.countShareholder(entBaseInfoUuid) == 0){
						ProcessState processState =  processStateService.selectByEntBaseInfoKey(entBaseInfoUuid);
						//设置股东的属性
						processState.setShareholderstate(0);
						processStateService.updateByPrimaryKeySelective(processState);
					}
				}
				
				msgMap.put("success", "true");
				msgMap.put("sturts", "true");
				//日志记录
				logger.info(moduleName + "[删除企业股东信息]");
			}else{
				msgMap.put("success", "true");
				msgMap.put("sturts", "false");
				msgMap.put("msg", "未查询到该对象");
			}
		return msgMap;
	}
	
	/**
	 * @Title 异步加载高管信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "executives")
	@RequestMapping("/listExecutives")
	@ResponseBody
	public Map<String, Object> listExecutives(HttpServletRequest request,String executivesUuid) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String entBaseInfoUuid = customer.getEntid();
		if(executivesUuid != null && ! "".equals(executivesUuid.trim())){
			//日志记录
			logger.info(moduleName + "[编辑高管信息]");
			Executives executives = executivesService.selectByPrimaryKey(executivesUuid);
			if(executives != null){
				msgMap.put("executives",executives);
				msgMap.put("edit", "true");
				msgMap.put("sturts", "true");
			}else{
				msgMap.put("sturts", "false");
				msgMap.put("msg", "未查询到 该条记录");
			}
		
		}else{
			//日志记录
			logger.info(moduleName + "[查询 高管信息]");
			if(entBaseInfoUuid != null && ! "".equals(entBaseInfoUuid.trim())){
				msgMap.put("executivesList",executivesService.selectByEntBaseInfo(entBaseInfoUuid));
				msgMap.put("edit", "false");
				msgMap.put("sturts", "true");
			}else{
				msgMap.put("sturts", "false");
				msgMap.put("msg", "未查询到 股东信息");
			}
		}
		msgMap.put("success", "true");
		
		return msgMap;
	}
	/**
	 * @Title 异步更新高管信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "executives")
	@RequestMapping("/updateExecutives")
	@ResponseBody
	public Map<String, Object> updateExecutives(HttpServletRequest request,Executives executives) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String entBaseInfoUuid = customer.getEntid();
		if(entBaseInfoUuid != null &&! "".equals(entBaseInfoUuid.trim())){
			if(executives.getUuid()!= null){
				executivesService.updateByPrimaryKeySelective(executives);
				//日志记录
				logger.info(moduleName + "[更新企业高管信息]");
			}else{
				executives.setUuid(UUID.randomUUID().toString().replace("-",""));
				executives.setEntid(entBaseInfoUuid);
				executivesService.insertSelective(executives);
				//日志记录
				logger.info(moduleName + "[创建企业高管信息]");
			}
			ProcessState processState =  processStateService.selectByEntBaseInfoKey(entBaseInfoUuid);
			if(processState != null){
				//设置高管的属性
				processState.setExecutivesstate(1);
				processStateService.updateByPrimaryKeySelective(processState);
			}
			
			msgMap.put("success", "true");
			msgMap.put("sturts", "true");
		}else{
			msgMap.put("msg", "参数错误");
			msgMap.put("success", "true");
			msgMap.put("sturts", "false");
		}
		return msgMap;
	}
	/**
	 * @Title 异步删除高管信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "executives")
	@RequestMapping("/deleteExecutives")
	@ResponseBody
	public Map<String, Object> deleteExecutives(HttpServletRequest request,String executivesUuid) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String entBaseInfoUuid = customer.getEntid();
			if(executivesUuid != null && ! "".equals(executivesUuid.trim())){
				executivesService.deleteByPrimaryKey(executivesUuid);
				
				if(entBaseInfoUuid != null && ! "".equals(entBaseInfoUuid.trim())){
					if(executivesService.countExecutives(entBaseInfoUuid) == 0){
						ProcessState processState =  processStateService.selectByEntBaseInfoKey(entBaseInfoUuid);
						//设置高管的属性
						processState.setExecutivesstate(0);
						processStateService.updateByPrimaryKeySelective(processState);
					}
				}
				
				msgMap.put("success", "true");
				msgMap.put("sturts", "true");
				//日志记录
				logger.info(moduleName + "[删除企业高管信息]");
			}else{
				msgMap.put("success", "true");
				msgMap.put("sturts", "false");
				msgMap.put("msg", "未查询到该对象");
			}
		return msgMap;
	}
	
	/**
	 * @title 异步加载 信用指标
	 * @author  孙尚飞  2017-8-30
	 * @desc
	 */
	@Permission(model = "enterprise", privilegeValue = "index")
	@RequestMapping("/listCreditIndex")
	@ResponseBody
	public Map<String, Object> listCreditIndex(HttpServletRequest request) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		StringBuffer message=new StringBuffer();
		boolean flag=true;
		List<Index> indexs=new ArrayList<Index>();
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		EntResult result= entResultService.selectByCustomerID(customer.getUsername());
		String path=new String();
		if(result==null){
			flag=false;
			message.append("该企业未进入评分流程");
		}else{
			if(result.getInputxmlurl()!=null){
				path=adr+result.getInputxmlurl();
				File file=new File(path);
				if(!file.exists()){
					flag=false;
					message.append("XML文件不存在");
				}
			}else{
				if(result.getModelid()==null){
					flag=false;
					message.append("该企业未分配模型");
				}else{
					Model model=modelService.selectByPrimaryKey(result.getModelid()); 
					if(model==null){
						flag=false;
						message.append("该企业未分配模型");
					}else{
						if(model.getXmlurl()==null||"".equals(model.getXmlurl())){
							flag=false;
							message.append("该企业未分配模型");
						}else{
							path=adr+model.getXmlurl();
							File file=new File(path);
							if(!file.exists()){
								flag=false;
								message.append("XML文件不存在");
							}
						}
					}
				}
			}
		}
		System.out.println("读取路径："+path);
		//path="D:\\model.xml";
		if(flag){
			try {
				File file=new File(path);
				Document document = new SAXReader().read(file);
				indexs=ModelUtil.getPushedIndex(document);
			} catch (DocumentException e) {
				e.printStackTrace();
			}	
			msgMap.put("indexs", indexs);
			msgMap.put("sturts", "true");
		}else{
			msgMap.put("sturts", "false");
			msgMap.put("msg", message.toString());
		}
		msgMap.put("success", "true");
		return msgMap;
	}
	/**
	 * @title 异步更新信用指标
	 * @author  孙尚飞  2017-8-30
	 * @desc
	 */
	@Permission(model = "enterprise", privilegeValue = "index")
	@RequestMapping("/updateCreditIndex")
	@ResponseBody
	public Map<String, Object> updateCreditIndex(HttpServletRequest request,@RequestParam Map<String, String> params) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		StringBuffer message=new StringBuffer();
		boolean flag=true;
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		EntResult result= entResultService.selectByCustomerID(customer.getUsername());
		String xmlpath="";
		if(result==null){
			flag=false;
			message.append("该企业不存在");
		}else{
			if(result.getInputxmlurl()!=null){
				File file=new File(adr+result.getInputxmlurl());
				if(file.exists()){
					file.delete();
				}
			}
			if(result.getModelid()==null){
				flag=false;
				message.append("该企业未分配模型");
			}else{
				Model model=modelService.selectByPrimaryKey(result.getModelid()); 
				if(model==null){
					flag=false;
					message.append("该企业未分配模型");
				}else{
					if(model.getXmlurl()==null||"".equals(model.getXmlurl())){
						flag=false;
						message.append("该企业未分配模型");
					}else{
						xmlpath=adr+model.getXmlurl();
						File file=new File(xmlpath);
						if(!file.exists()){
							flag=false;
							message.append("XML文件不存在");
						}
					}
				}
			}
		}
		if(flag){
			try {
				File file=new File(xmlpath);
				Document document = new SAXReader().read(file);
				List<Index> indexs=ModelUtil.getIndex(document);
				for (Map.Entry<String, String> entry : params.entrySet()) {  
				    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
				    for(Index firstindex : indexs){
						for(Index secondindex : firstindex.getChirds()){
							for(Index thirdindex : secondindex.getChirds()){
								if(thirdindex.getUuid().equals(entry.getKey())){
									if(thirdindex.getWrite()!=null&&thirdindex.getWrite().equals("true")){
										System.out.println(thirdindex.getName());
										thirdindex.setInsert(entry.getValue());
									}else{
										for(Option option : thirdindex.getOptions()){
											if(option.getValue().equals(entry.getValue())){
												option.setSelected("true");
											}
										}
									}
									
								}
							}
						}	
					}
				} 
				String inputXmlName=result.getUuid()+".xml";
				String XMLURL=BusinessUtil.getMsg("inputXMLUrl");
				String inputXMLUrl=adr+XMLURL;
				File saveFile=new File(inputXMLUrl);
				//如果文件夹不存在则创建    
				if (!saveFile.exists()&&!saveFile.isDirectory()){
					saveFile.mkdirs();
				}   
				if(ModelUtil.saveXML(indexs, inputXMLUrl+inputXmlName)){
					result.setInputxmlurl(XMLURL+inputXmlName);
					result.setInputxmltime(new Date());
					result.setInputxmlstate(0);
					if(SecurityUtil.getMsg("CrossUpload").equals("1")){
						if(SaveFile.crossFile(XMLURL+inputXmlName,  inputXMLUrl+inputXmlName)){
							result.setInputxmlstate(1);
							logger.info(moduleName + "[服务器文件保存成功]");
						}else{
							logger.info(moduleName + "[服务器文件保存失败]");
						}
					}
					entResultService.updateByPrimaryKey(result);
					ProcessState processState = processStateService.selectByEntBaseInfoKey(result.getEntid());
					if(processState != null){
						processState.setPushmodelstate(1);
						processStateService.updateByPrimaryKeySelective(processState);
					}
					logger.info(moduleName + "[本地文件保存成功]");
					msgMap.put("success", "true");
					msgMap.put("sturts", "true");
				}else{
					logger.info(moduleName + "[本地文件保存失败]");
					msgMap.put("success", "true");
					msgMap.put("sturts", "false");
					msgMap.put("msg", "XML文件保存失败");
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			msgMap.put("success", "true");
			msgMap.put("sturts", "false");
			msgMap.put("msg", message.toString());
		}
		return msgMap;
	}
	
	/**
	 * @Title 异步加载附件列表信息
	 * @author  孙尚飞  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "uploadFile")
	@RequestMapping("/listUploadFile")
	@ResponseBody
	public Map<String, Object> listUploadFile(HttpServletRequest request) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		List<UploadFileCategory> categorys=uploadFileCategoryService.selectEntRelevant();
		if(!categorys.isEmpty()){
			msgMap.put("categorys",categorys);
			msgMap.put("sturts", "true");
		}else{
			msgMap.put("sturts", "false");
			msgMap.put("msg", "未查询到附件列表信息");
		}
		msgMap.put("success", "true");
		return msgMap;
	}
	
	/**
	 * @Title 上传附件
	 * @author  孙尚飞  @date 2017-8-22 
	 * @throws Exception 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "uploadFile")
	@RequestMapping("/uploadFile")
	@ResponseBody
	public void uploadFile(HttpServletRequest request) throws Exception {
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String entBaseInfoUuid = customer.getEntid();
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		String dir = request.getSession().getServletContext().getRealPath("");
		String realUrl=BusinessUtil.getMsg("uploadFileUrl")+entBaseInfoUuid+File.separator;
		String fileID=request.getParameter("uid");
		String fileSize=request.getParameter("size");
		String fileName=SaveFile.uploadFile(request,adr+realUrl,null);
		String fileUrl=realUrl+fileName;
		SaveFile.uploadFile(request,dir+realUrl,fileName);
		if(entBaseInfoUuid != null && ! "".equals(entBaseInfoUuid.trim())){
			UploadFileCategory filetype = uploadFileCategoryService.selectByPrimaryKey(fileID);
			if(filetype!=null&&filetype.getType()==0){
				List<UploadFile> files = uploadFileService.selectByEntIDAndFileID(entBaseInfoUuid, fileID);
				if(!files.isEmpty()){
					for(UploadFile file : files){
						SaveFile.deleteFile(adr+file.getFileurl());
						SaveFile.deleteFile(dir+file.getFileurl());
						uploadFileService.deleteByPrimaryKey(file.getUuid());
					}
				}
			}
			UploadFile file=new UploadFile();
			file.setAdddate(new Date());
			file.setEntid(entBaseInfoUuid);
			file.setUploadfilecategoryid(fileID);
			file.setFilesize(fileSize);
			file.setFileurl(fileUrl);
			file.setUuid(UUID.randomUUID().toString().replace("-", ""));
			file.setCrossstate(0);
			if(SecurityUtil.getMsg("CrossUpload").equals("1")){
				if(SaveFile.crossFile(fileUrl,  adr+fileUrl)){
					file.setCrossstate(1);
					logger.info(moduleName + "[服务器文件保存成功]");
				}else{
					logger.info(moduleName + "[服务器文件保存失败]");
				}
			}
			uploadFileService.insert(file);
			System.out.println("附件保存成功");
		}
	}

	/**
	 * @Title 获取当前企业指定类型的附件信息
	 * @author  孙尚飞  @date 2017-8-22 
	 * @Description 
	 */
	@Permission(model = "enterprise", privilegeValue = "uploadFile")
	@RequestMapping("/getUploadFile")
	@ResponseBody
	public Map<String, Object> getUploadFile(HttpServletRequest request) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String entBaseInfoUuid = customer.getEntid();
		String fileID=request.getParameter("fileID");
		String dir = request.getSession().getServletContext().getRealPath("");
		String realUrl=BusinessUtil.getMsg("uploadFileUrl");
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		if(entBaseInfoUuid != null && ! "".equals(entBaseInfoUuid.trim())){
			List<UploadFile> files = uploadFileService.selectByEntIDAndFileID(entBaseInfoUuid,fileID);
			if(files.isEmpty()||files.size()==0){
				msgMap.put("msg", "无上传文件");
				msgMap.put("sturts", "false");
			}else{
				List<UploadFileVO> ufvos=new ArrayList<UploadFileVO>();
				for(UploadFile file : files){
					UploadFileVO ufvo=new UploadFileVO();
					ufvo.setUuid(file.getUuid());
					ufvo.setEntitID(file.getEntid());
					ufvo.setUploadfilecategoryid(file.getUploadfilecategoryid());
					ufvo.setFilesize(RandomUtil.getPrintSize(Long.parseLong(file.getFilesize())));
					ufvo.setFileurl(file.getFileurl());
					ufvo.setAdddate(sdf.format(file.getAdddate()));
					ufvos.add(ufvo);
					String path1= adr+file.getFileurl();
					String path2= dir+file.getFileurl();
					File file1=new File(path1);
					File file2=new File(path2);
					File file3=new File(dir+realUrl);
					if(file1.exists()){
						if(!file3.exists()){
							file3.mkdirs();
							SaveFile.copyFile(path1, path2);
						}else{
							if(!file2.exists()){
								SaveFile.copyFile(path1, path2);
							}
						}
					}
					
				}
				msgMap.put("files", ufvos);
				msgMap.put("sturts", "true");
			}
		}else{
			msgMap.put("msg", "参数错误");
			msgMap.put("sturts", "false");
		}
		msgMap.put("success", "true");
		return msgMap;
	}
	
	/**
	 * @Title 删除附件
	 * @author  孙尚飞  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "uploadFile")
	@RequestMapping("/deleteUploadFile")
	@ResponseBody
	public Map<String, Object> deleteUploadFile(HttpServletRequest request,String uuid) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		String dir = request.getSession().getServletContext().getRealPath("");
		UploadFile uploadfile=uploadFileService.selectByPrimaryKey(uuid);
		if(uploadfile!=null){
			String result1=SaveFile.deleteFile(adr+uploadfile.getFileurl());
			String result2=SaveFile.deleteFile(dir+uploadfile.getFileurl());
			if(result1.equals("fail")||result2.equals("fail")){
				msgMap.put("sturts", "false");
				msgMap.put("msg", "图片删除失败");
			}else{
				uploadFileService.deleteByPrimaryKey(uuid);
				msgMap.put("fileID", uploadfile.getUploadfilecategoryid());
				msgMap.put("name", uploadFileCategoryService.selectByPrimaryKey(uploadfile.getUploadfilecategoryid()).getName());
				msgMap.put("sturts", "true");
				msgMap.put("msg", "图片删除成功");
			}
		}
		msgMap.put("success", "true");
		return msgMap;
	}
	/**
	 * @title 判断必须上传的扫描件是否全部上传了
	 * @author  孙尚飞  2017-9-15
	 * @desc
	 */
	@Permission(model = "enterprise", privilegeValue = "uploadFile")
	@RequestMapping("/judgeMustFile")
	@ResponseBody
	public Map<String, Object> judgeMustFile(HttpServletRequest request) {
		logger.info(moduleName + "[判断必须上传的扫描件是否全部上传了]");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer!=null){
			String entBaseInfoUuid = customer.getEntid();
			List<String> strs = uploadFileService.judgeFileAllUpload(entBaseInfoUuid,"true");
			String str = "";
			for (int i = 0; i < strs.size(); i++) {
				if (strs.get(i).split("#").length == 1) {
					str += strs.get(i).split("#")[0].replace(
							"<font color='red'>＊</font>", "") + "/n";
				}
			}
			ProcessState processState =  processStateService.selectByEntBaseInfoKey(entBaseInfoUuid);
			if (str != null && !"".equals(str.trim())) {
				str += "请检查这些文件是否正确上传了！！";
				if(processState != null){
					processState.setUploadfilestate(0);
				}
				msgMap.put("msg", str);
				msgMap.put("sturts", "false");
			} else {
				if(processState != null){
					processState.setUploadfilestate(1);
				}
				msgMap.put("sturts", "true");
				msgMap.put("msg", "相关文件已经全部上传了，数据保存成功");
			}
			processStateService.updateByPrimaryKeySelective(processState);
		}
		msgMap.put("success", "true");
		return msgMap;
	}
	/**
	 * @Title 判断财务报表模板是否存在
	 * @author  孙尚飞  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "finance")
	@RequestMapping("/judgeExecl")
	@ResponseBody
	public Map<String, Object> judgeExecl(HttpServletRequest request,String uuid) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		String path=BusinessUtil.getMsg("financialTemplate");
		String name=BusinessUtil.getMsg("financialName");
		File file=new File(adr+path+name);
		if(file.exists()){
			msgMap.put("sturts", "true");
		}else{
			File file1=new File(adr+path);
			if(!file1.exists()){
				file1.mkdirs();
			}
			msgMap.put("sturts", "false");
			msgMap.put("msg", "财务模板不存在，请联系管理员");
		}
		msgMap.put("success", "true");
		return msgMap;
	}
	/**
	 * @title 财务模板下载
	 * @author  孙尚飞  2017-9-14
	 * @desc
	 */
	@Permission(model = "enterprise", privilegeValue = "finance")
	@RequestMapping("/downloadFinance")
	public String downloadFinance(HttpServletResponse response) {
		logger.info(moduleName + "[财务数据模板下载]");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		String path=BusinessUtil.getMsg("financialTemplate");
		String templename=BusinessUtil.getMsg("financialName");
		String name="FINANCETEMPLATE"+sdf.format(new Date())+".xlsx";
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream fos = null;
		InputStream fis = null;
		// 进行下载
		try {
			fis = new FileInputStream(adr+path+templename);
			bis = new BufferedInputStream(fis);
			fos = response.getOutputStream();
			bos = new BufferedOutputStream(fos);
			// 这个就就是弹出下载对话框的关键代码
			/*
			 * System.out.println(name);
			 * System.out.println(URLEncoder.encode(name,"utf-8"));
			 */
			name = URLEncoder.encode(name, "utf-8");
			name = URLEncoder.encode(name, "utf-8");
			name = URLDecoder.decode(name, "utf-8");
			name = URLDecoder.decode(name, "utf-8");
			/* name = new String(name.getBytes("ISO8859-1"), "utf-8"); */
			System.out.println(name);
			name = new String(name.getBytes("gbk"), "ISO8859-1");
			System.out.println(name);
			response.setHeader("cache-control", "public");
			response.setHeader("Pragma", "public");
			response.setHeader("Content-disposition", "attachment;filename="
					+ name);
			int bytesRead = 0;
			// 都是用输入流进行先读，然后用输出流去写，唯一不同的是我用的是缓冲输入输出流
			byte[] buffer = new byte[8192];
			while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			bos.flush();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	/**
	 * @Title 上传财务报表
	 * @author  孙尚飞  @date 2017-8-22 
	 * @throws Exception 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "finance")
	@RequestMapping("/uploadExecl")
	@ResponseBody
	public void uploadExecl(HttpServletRequest request) throws Exception {
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String entBaseInfoUuid = customer.getEntid();
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		String realUrl=BusinessUtil.getMsg("financeExeclUrl");
		String fileSize=request.getParameter("size");
		String fileName=SaveFile.uploadFile(request,adr+realUrl,null);
		String fileUrl=realUrl+fileName;
		if(entBaseInfoUuid != null && ! "".equals(entBaseInfoUuid.trim())){
			Finance oldfinance = financeService.selectByEntID(entBaseInfoUuid);
			if(oldfinance!=null){
				financeService.deleteByPrimaryKey(oldfinance.getUuid());
				SaveFile.deleteFile(adr+oldfinance.getFileurl());
			}
			Finance finance=new Finance();
			finance.setUuid(UUID.randomUUID().toString().replace("-", ""));
			finance.setEntid(entBaseInfoUuid);
			finance.setFilesize(fileSize);
			finance.setFileurl(fileUrl);
			finance.setUpdatetime(new Date());
			finance.setCrossstate(0);
			if(SecurityUtil.getMsg("CrossUpload").equals("1")){
				if(SaveFile.crossFile(fileUrl,  adr+fileUrl)){
					finance.setCrossstate(1);
					logger.info(moduleName + "[服务器文件保存成功]");
				}else{
					logger.info(moduleName + "[服务器文件保存失败]");
				}
			}
			financeService.insert(finance);
			System.out.println("财务数据保存成功");
		}
	}
	
	/**
	 * @Title 判断财务数据是否存在
	 * @author  孙尚飞  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "finance")
	@RequestMapping("/judgeFinance")
	@ResponseBody
	public Map<String, Object> judgeFinance(HttpServletRequest request) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String entBaseInfoUuid = customer.getEntid();
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		if(entBaseInfoUuid != null && ! "".equals(entBaseInfoUuid.trim())){
			Finance finance = financeService.selectByEntID(entBaseInfoUuid);
			if(finance!=null){
				File file=new File(adr+finance.getFileurl());
				if(file.exists()){
					msgMap.put("sturts", "true");
				}
			}
		}
		msgMap.put("success", "true");
		return msgMap;
	}
	
	/**
	 * @Title 校检财务数据
	 * @author  孙尚飞  @date 2017-8-22 
	 * @throws Exception 
	 * @Description 
	 *
	 */
	@Permission(model = "enterprise", privilegeValue = "finance")
	@RequestMapping("/checkExecl")
	@ResponseBody
	public Map<String, Object> checkExecl(HttpServletRequest request) throws Exception {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		boolean flag=true;
		StringBuffer message=new StringBuffer();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String entBaseInfoUuid = customer.getEntid();
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		String path=new String();
		if(entBaseInfoUuid == null || "".equals(entBaseInfoUuid.trim())){
			flag=false;
			message.append("参数错误");
		}else{
			Finance finance = financeService.selectByEntID(entBaseInfoUuid);
			if(finance==null){
				flag=false;
				message.append("未上传财务数据，请上传");
			}else{
				path=adr+finance.getFileurl();
				File file=new File(path);
				if(!file.exists()){
					flag=false;
					message.append("财务数据不存在,请重新上传");
				}
			}
		}
		if(flag){
			ProcessState processState =  processStateService.selectByEntBaseInfoKey(entBaseInfoUuid);
			if(processState != null){
				processState.setFinancestate(1);
				processStateService.updateByPrimaryKeySelective(processState);
			}
			msgMap.put("sturts", "true");
			msgMap.put("excelhtml", ExcelShower.getExcelInfo(path));
		}else{
			msgMap.put("sturts", "false");
			msgMap.put("msg",message.toString());
		}
		msgMap.put("success", "true");
		return msgMap;
	}
}
