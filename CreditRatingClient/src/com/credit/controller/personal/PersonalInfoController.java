/**
 * 
 */
package com.credit.controller.personal;

import java.io.File;
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
import com.credit.model.member.Customer;
import com.credit.model.person.Career;
import com.credit.model.person.Education;
import com.credit.model.person.PerBaseInfo;
import com.credit.model.person.PerProcess;
import com.credit.model.person.PerResult;
import com.credit.model.person.PerUploadFile;
import com.credit.model.person.Skills;
import com.credit.model.person.Train;
import com.credit.modelvo.Permission;
import com.credit.modelvo.SessionName;
import com.credit.modelvo.UploadFileVO;
import com.credit.service.addition.ModelService;
import com.credit.service.addition.UploadFileCategoryService;
import com.credit.service.person.PerCareerService;
import com.credit.service.person.PerEducationService;
import com.credit.service.person.PerBaseInfoService;
import com.credit.service.person.PerProcessService;
import com.credit.service.person.PerResultService;
import com.credit.service.person.PerSkillsService;
import com.credit.service.person.PerTrainService;
import com.credit.service.person.PerUploadFileService;
import com.credit.util.DateTime;
import com.credit.util.RandomUtil;
import com.credit.util.SaveFile;
import com.credit.util.model.Index;
import com.credit.util.model.ModelUtil;
import com.credit.util.model.Option;
import com.credit.util.properties.BusinessUtil;
import com.credit.util.security.SecurityUtil;

/**
 * 个人信息录入信息
 * @author Administrator
 */
@Controller
@RequestMapping("/personalInfo")
public class PersonalInfoController {
	
	private static final Logger logger = Logger.getLogger(PersonalInfoController.class);

	private String moduleName = "用户操作情况";
	
	@Resource
	private PerProcessService perProcessService;//个人评价状态信息
	
	@Resource
	private PerBaseInfoService perBaseInfoService;//个人基础信息
	
	@Resource
	private PerEducationService educationService;//教育信息
	
	@Resource
	private PerCareerService careerService;//工作经验
	
	@Resource
	private PerTrainService trainService;//培训

	@Resource
	private PerSkillsService skillsService;//培训
	
	@Resource
	private PerResultService perResultService;//个人评分结果
	
	@Resource
	private ModelService<?> modelService;//模型
	
	@Resource
	private UploadFileCategoryService<?> uploadFileCategoryService ;//附件分类
	
	@Resource
	private PerUploadFileService  perUploadFileService;//附件列表
	
	
	/**
	 * @Title spring mvc在绑定表单之前，会先进入该方法（从jsp -> java）本方法是str转换为date类型
	 * 		      或者使用   实体类 属性上注入方式（    @DateTimeFormat(pattern = "yyyy-MM-dd")）
	 * @author  Administrator  @date 2017-8-23 
	 * @Description 
	 */
	@InitBinder  
    public void initBinder(WebDataBinder binder) {  
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
    	dateFormat.setLenient(false);  
    	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); //"true":允许输入空值，"false":不能为空值 
    }
	
	/**
	 * @Title 进入录入信息界面
	 * @author  Administrator  @date 2017-9-15 
	 * @Description 
	 */
	@Permission(model = "personal", privilegeValue = "inputInfo")
	@RequestMapping("/inputPerInfo")
	public String inputPerInfo(HttpServletRequest request){
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer != null){
			//日志记录
			logger.info(moduleName + "[个人信息数据录入信息界面]");
			return "personal/mainProcess";
		}else{
			request.setAttribute("error", "获取用户信息超时,请重新登录");
			//页面 获取session失败   ，则 跳转到登录界面
			return "redirect:/customer/logonUI.do";
		}
	}
	
	/**
	 * @Title 获取状态信息
	 * @author  Administrator  @date 2017-9-15 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "perProcess")
	@RequestMapping("/listPerProcess")
	@ResponseBody
	public Map<String, Object> listPerProcess(HttpServletRequest request){
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String perid = customer.getPerid();
		PerProcess perProcess =  perProcessService.selectByPerID(perid);
		if(perProcess == null){
			perProcess = new PerProcess();
			perProcess.setUuid(UUID.randomUUID().toString().replace("-", ""));
			perProcess.setPerid(perid);
			perProcessService.insertSelective(perProcess);
		}else{
		}
		//日志记录
		logger.info(moduleName + "[查询 个人数据录入状态信息]");
		msgMap.put("perProcess", perProcess);
		return msgMap;
	}
	
	/**
	 * @Title 点击提交按钮  更新 状态信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "perProcess")
	@RequestMapping("/updatePerProcess")
	@ResponseBody
	public Map<String, Object> updatePerProcess(HttpServletRequest request) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String perid = customer.getPerid();
		PerProcess perProcess =  perProcessService.selectByPerID(perid);
		if(perProcess == null){
			msgMap.put("msg", "申请 提交失败，请重新尝试");
		}else{
			if(perProcess.getBaseinfostate() == 1  && perProcess.getPushmodelstate() == 1 && perProcess.getEducationstate() == 1 &&  perProcess.getCareerstate() == 1 && perProcess.getUploadfilestate() == 1 ){
				perProcess.setApplyreportstate(1);
				perProcessService.updateByPrimaryKeySelective(perProcess);
				
				msgMap.put("msg", "申请 提交成功");
			}else{
				msgMap.put("msg", "申请 提交失败，必填项不完整");
			}
		}
		//日志记录
		logger.info(moduleName + "[更新 个人数据录入状态信息为 已提交]");
		msgMap.put("perProcess", perProcess);
		return msgMap;
	}
	/**
	 * @Title 异步加载 个人基础信息
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "perBaseInfo")
	@RequestMapping("/listPerBaseInfo")
	@ResponseBody
	public Map<String, Object> listPerBaseInfo(HttpServletRequest request) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer != null){
			//日志记录
			logger.info(moduleName + "[查询个人基础信息]");
			String perid = customer.getPerid();
			PerBaseInfo perBaseInfo = perBaseInfoService.selectByPrimaryKey(perid);
			if(perBaseInfo == null){
				perBaseInfo = new PerBaseInfo();
				perBaseInfo.setCreatetime(DateTime.getCurrentTimeStamp());
				perBaseInfo.setUuid(perid);
				perBaseInfo.setName(customer.getRealname());
				perBaseInfoService.insertSelective(perBaseInfo);
			}
			msgMap.put("perBaseInfo",perBaseInfo);
			msgMap.put("status", true);
		}else{
			msgMap.put("msg", "登陆超时,请重新登录");
			msgMap.put("status", false);
		}
		return msgMap;
	}
	
	/**
	 * @Title 更新 个人基础信息表
	 * @author  Administrator  @date 2017-8-20 
	 * @throws ParseException 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "perBaseInfo")
	@RequestMapping("/updatePerBaseInfo")
	@ResponseBody
	public Map<String, Object> updatePerBaseInfo(HttpServletRequest request, PerBaseInfo perBaseInfo) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if (perBaseInfo != null) {
			//更新企业信息表
			perBaseInfo.setUpdatetime(DateTime.getCurrentTimeStamp());
			perBaseInfoService.updateByPrimaryKeySelective(perBaseInfo);
			//向前台 发送数据 ajax
			msgMap.put("perBaseInfo", perBaseInfoService.selectByPrimaryKey(perBaseInfo.getUuid()));
			msgMap.put("success", "true");
			msgMap.put("status", "true");
			//获取 信息完整度表单
			Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
			if(customer != null){
				String perid = customer.getPerid();
				if(perid != null && ! "".equals(perid.trim())){
					PerProcess perProcess =  perProcessService.selectByPerID(perid);
					if(perProcess == null){
						perProcess = new PerProcess();
						perProcess.setUuid(UUID.randomUUID().toString().replace("-", ""));
						perProcess.setPerid(perid);
						perProcessService.insertSelective(perProcess);
					}
					perProcess.setBaseinfostate(1);
					perProcessService.updateByPrimaryKeySelective(perProcess);
				}
			}
			//日志记录
			logger.info(moduleName + "[更新企业基础信息]");
		} else {
			msgMap.put("msg", "参数错误");
			msgMap.put("success", "true");
			msgMap.put("status", "false");
		}
		return msgMap;
	}
	
	/**
	 * @Title 异步加载教育信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "education")
	@RequestMapping("/listEducation")
	@ResponseBody
	public Map<String, Object> listEducation(HttpServletRequest request,String educationId) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer != null){
			if(educationId != null && !"".equals(educationId)){
				logger.info(moduleName + "[查询教育信息;uuid:"+educationId+"]");
				Education education = educationService.selectByPrimaryKey(educationId);
				if(education != null){
					msgMap.put("education",education);
					msgMap.put("edit", true);
					msgMap.put("status", true);
				}else{
					msgMap.put("status", false);
					msgMap.put("msg", "未查询到 该条记录");
				}
			}else{
				//日志记录
				logger.info(moduleName + "[查询 并获取教育信息列表]");
				String perID = customer.getPerid();
				if(perID != null && ! "".equals(perID.trim())){
					msgMap.put("educationList",educationService.selectByPerID(perID));
					msgMap.put("edit", false);
					msgMap.put("status", true);
				}else{
					msgMap.put("status", false);
					msgMap.put("msg", "未查询到 教育信息");
				}
			}
		}else{
			msgMap.put("msg", "登陆超时,请重新登录");
			msgMap.put("status", -1);
		}
		return msgMap;
	}
	/**
	 * @Title 异步更新教育信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "education")
	@RequestMapping("/updateEducation")
	@ResponseBody
	public Map<String, Object> updateEducation(HttpServletRequest request,Education edu) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer != null){
			if(edu != null){
				Education education =educationService.selectByPrimaryKey(edu.getUuid());
				if(education !=null){
					logger.info(moduleName + "[更新教育信息;uuid:"+edu.getUuid()+"]");
					educationService.updateByPrimaryKeySelective(edu);
				}else{
					logger.info(moduleName + "[插入教育信息;edu:"+edu+"]");
					edu.setUuid(UUID.randomUUID().toString().replace("-", ""));
					edu.setPerid(customer.getPerid());
					educationService.insertSelective(edu);
				}
				//获取个人信息状态  更改教育的状态 
				String perid = customer.getPerid();
				if(perid != null && ! "".equals(perid.trim())){
					PerProcess perProcess =  perProcessService.selectByPerID(perid);
					if(perProcess == null){
						perProcess = new PerProcess();
						perProcess.setUuid(UUID.randomUUID().toString().replace("-", ""));
						perProcess.setPerid(perid);
						perProcessService.insertSelective(perProcess);
					}
					if(educationService.countEducation(perid) == 0){
						//设置教育的属性
						perProcess.setEducationstate(0);
					}else{
						perProcess.setEducationstate(1);
					}
					perProcessService.updateByPrimaryKeySelective(perProcess);
				}
				msgMap.put("status", true);
			}else{
				msgMap.put("msg", "参数错误,请刷新后重新尝试");
				msgMap.put("status", false);
			}
		}else{
			msgMap.put("msg", "参数错误,请刷新后重新尝试");
			msgMap.put("status", -1);
		}
		return msgMap;
	}
	/**
	 * @Title 异步删除教育信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "education")
	@RequestMapping("/deleteEducation")
	@ResponseBody
	public Map<String, Object> deleteEducation(HttpServletRequest request,String educationId) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer != null){
			String perid = customer.getEntid();
			if(educationId != null && ! "".equals(educationId.trim())){
				educationService.deleteByPrimaryKey(educationId);
				//删除后更改 个人状态信息表 中数据状态
				if(perid != null && ! "".equals(perid.trim())){
					PerProcess perProcess =  perProcessService.selectByPerID(perid);
					if(perProcess == null){
						perProcess = new PerProcess();
						perProcess.setUuid(UUID.randomUUID().toString().replace("-", ""));
						perProcess.setPerid(perid);
						perProcessService.insertSelective(perProcess);
					}
					if(educationService.countEducation(perid) == 0){
						//设置教育的属性
						perProcess.setEducationstate(0);
					}else{
						perProcess.setEducationstate(1);
					}
					perProcessService.updateByPrimaryKeySelective(perProcess);
				}
				msgMap.put("status", true);
				//日志记录
				logger.info(moduleName + "[删除个人教育信息]");
			}else{
				msgMap.put("status", false);
				msgMap.put("msg", "未查询到相关数据");
			}
		}else{
			msgMap.put("status",-1);
			msgMap.put("msg", "登陆超时,请重新登录后再进行删除操作");
		}
		return msgMap;
	}
	/**
	 * @Title 异步加载工作经验信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "career")
	@RequestMapping("/listCareer")
	@ResponseBody
	public Map<String, Object> listCareer(HttpServletRequest request,String careerId) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer != null){
			if(careerId != null && !"".equals(careerId)){
				logger.info(moduleName + "[查询工作经验信息;uuid:"+careerId+"]");
				Career career = careerService.selectByPrimaryKey(careerId);
				if(career != null){
					msgMap.put("career",career);
					msgMap.put("edit", true);
					msgMap.put("status", true);
				}else{
					msgMap.put("status", false);
					msgMap.put("msg", "未查询到 该条记录");
				}
			}else{
				//日志记录
				logger.info(moduleName + "[查询 并获取工作经验信息列表]");
				String perID = customer.getPerid();
				if(perID != null && ! "".equals(perID.trim())){
					msgMap.put("careerList",careerService.selectByPerID(perID));
					msgMap.put("edit", false);
					msgMap.put("status", true);
				}else{
					msgMap.put("status", false);
					msgMap.put("msg", "未查询到工作经验信息");
				}
			}
		}else{
			msgMap.put("msg", "登录失败,请重新登录后再进行相关操作");
			msgMap.put("status", -1);
		}
		return msgMap;
	}
	/**
	 * @Title 异步更新工作经验信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "career")
	@RequestMapping("/updateCareer")
	@ResponseBody
	public Map<String, Object> updateCareer(HttpServletRequest request,Career career) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer != null){
			if(career != null){
				Career car =careerService.selectByPrimaryKey(career.getUuid());
				if(car !=null){
					logger.info(moduleName + "[更新教育信息;career:"+career.getUuid()+"]");
					careerService.updateByPrimaryKeySelective(career);
				}else{
					logger.info(moduleName + "[插入教育信息;career:"+career+"]");
					career.setUuid(UUID.randomUUID().toString().replace("-", ""));
					career.setPerid(customer.getPerid());
					careerService.insertSelective(career);
				}
				//获取个人信息状态  更改教育的状态 
				String perid = customer.getPerid();
				if(perid != null && ! "".equals(perid.trim())){
					PerProcess perProcess =  perProcessService.selectByPerID(perid);
					if(perProcess == null){
						perProcess = new PerProcess();
						perProcess.setUuid(UUID.randomUUID().toString().replace("-", ""));
						perProcess.setPerid(perid);
						perProcessService.insertSelective(perProcess);
					}
					if(careerService.countCareer(perid) == 0){
						//设置教育的属性
						perProcess.setCareerstate(0);
					}else{
						perProcess.setCareerstate(1);
					}
					perProcessService.updateByPrimaryKeySelective(perProcess);
				}
				msgMap.put("status", true);
			}else{
				msgMap.put("msg", "参数错误,请刷新后重新尝试");
				msgMap.put("status", false);
			}
		}else{
			msgMap.put("msg", "登录失败,请重新登录后再进行相关操作");
			msgMap.put("status", -1);
		}
		return msgMap;
	}
	/**
	 * @Title 异步删除工作经验信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "career")
	@RequestMapping("/deleteCareer")
	@ResponseBody
	public Map<String, Object> deleteCareer(HttpServletRequest request,String careerId) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer != null){
			String perid = customer.getEntid();
			if(careerId != null && ! "".equals(careerId.trim())){
				careerService.deleteByPrimaryKey(careerId);
				//删除后更改 个人状态信息表 中数据状态
				if(perid != null && ! "".equals(perid.trim())){
					PerProcess perProcess =  perProcessService.selectByPerID(perid);
					if(perProcess == null){
						perProcess = new PerProcess();
						perProcess.setUuid(UUID.randomUUID().toString().replace("-", ""));
						perProcess.setPerid(perid);
						perProcessService.insertSelective(perProcess);
					}
					if(careerService.countCareer(perid) == 0){
						//设置工作经验的属性
						perProcess.setCareerstate(0);
					}else{
						perProcess.setCareerstate(1);
					}
					perProcessService.updateByPrimaryKeySelective(perProcess);
				}
				msgMap.put("status", true);
				//日志记录
				logger.info(moduleName + "[删除个人教育信息]");
			}else{
				msgMap.put("status", false);
				msgMap.put("msg", "未查询到相关数据");
			}
		}else{
			msgMap.put("status",-1);
			msgMap.put("msg", "登陆超时,请重新登录后再进行删除操作");
		}
		return msgMap;
	}
	/**
	 * @Title 异步加载培训信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "train")
	@RequestMapping("/listTrain")
	@ResponseBody
	public Map<String, Object> listTrain(HttpServletRequest request,String trainId) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer != null){
			if(trainId != null && !"".equals(trainId)){
				logger.info(moduleName + "[查询培训信息;uuid:"+trainId+"]");
				Train train = trainService.selectByPrimaryKey(trainId);
				if(train != null){
					msgMap.put("train",train);
					msgMap.put("edit", true);
					msgMap.put("status", true);
				}else{
					msgMap.put("status", false);
					msgMap.put("msg", "未查询到 该条记录");
				}
			}else{
				//日志记录
				logger.info(moduleName + "[查询 并获取培训信息列表]");
				String perID = customer.getPerid();
				if(perID != null && ! "".equals(perID.trim())){
					msgMap.put("trainList",trainService.selectByPerID(perID));
					msgMap.put("edit", false);
					msgMap.put("status", true);
				}else{
					msgMap.put("status", false);
					msgMap.put("msg", "未查询到工作经验信息");
				}
			}
		}else{
			msgMap.put("msg", "登录失败,请重新登录后再进行相关操作");
			msgMap.put("status", -1);
		}
		return msgMap;
	}
	/**
	 * @Title 异步更新培训信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "train")
	@RequestMapping("/updateTrain")
	@ResponseBody
	public Map<String, Object> updateTrain(HttpServletRequest request,Train train) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer != null){
			if(train != null){
				Train tra =trainService.selectByPrimaryKey(train.getUuid());
				if(tra !=null){
					logger.info(moduleName + "[更新培训信息;train:"+train.getUuid()+"]");
					trainService.updateByPrimaryKeySelective(train);
				}else{
					logger.info(moduleName + "[插入培训信息;train:"+train+"]");
					train.setUuid(UUID.randomUUID().toString().replace("-", ""));
					train.setPerid(customer.getPerid());
					trainService.insertSelective(train);
				}
				//获取个人信息状态  更改教育的状态 
				String perid = customer.getPerid();
				if(perid != null && ! "".equals(perid.trim())){
					PerProcess perProcess =  perProcessService.selectByPerID(perid);
					if(perProcess == null){
						perProcess = new PerProcess();
						perProcess.setUuid(UUID.randomUUID().toString().replace("-", ""));
						perProcess.setPerid(perid);
						perProcessService.insertSelective(perProcess);
					}
					if(trainService.countTrain(perid) == 0){
						//设置教育的属性
						perProcess.setTrainstate(0);
					}else{
						perProcess.setTrainstate(1);
					}
					perProcessService.updateByPrimaryKeySelective(perProcess);
				}
				msgMap.put("status", true);
			}else{
				msgMap.put("msg", "参数错误,请刷新后重新尝试");
				msgMap.put("status", false);
			}
		}else{
			msgMap.put("msg", "登录失败,请重新登录后再进行相关操作");
			msgMap.put("status", -1);
		}
		return msgMap;
	}
	/**
	 * @Title 异步删除培训信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "train")
	@RequestMapping("/deleteTrain")
	@ResponseBody
	public Map<String, Object> deleteTrain(HttpServletRequest request,String trainId) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer != null){
			String perid = customer.getEntid();
			if(trainId != null && ! "".equals(trainId.trim())){
				trainService.deleteByPrimaryKey(trainId);
				//删除后更改 个人状态信息表 中数据状态
				if(perid != null && ! "".equals(perid.trim())){
					PerProcess perProcess =  perProcessService.selectByPerID(perid);
					if(perProcess == null){
						perProcess = new PerProcess();
						perProcess.setUuid(UUID.randomUUID().toString().replace("-", ""));
						perProcess.setPerid(perid);
						perProcessService.insertSelective(perProcess);
					}
					if(trainService.countTrain(perid) == 0){
						//设置工作经验的属性
						perProcess.setTrainstate(0);
					}else{
						perProcess.setTrainstate(1);
					}
					perProcessService.updateByPrimaryKeySelective(perProcess);
				}
				msgMap.put("status", true);
				//日志记录
				logger.info(moduleName + "[删除个人培训信息]");
			}else{
				msgMap.put("status", false);
				msgMap.put("msg", "未查询到相关数据");
			}
		}else{
			msgMap.put("status",-1);
			msgMap.put("msg", "登陆超时,请重新登录后再进行删除操作");
		}
		return msgMap;
	}
	/**
	 * @Title 异步加载技能信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "skills")
	@RequestMapping("/listSkills")
	@ResponseBody
	public Map<String, Object> listSkills(HttpServletRequest request,String skillsId) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer != null){
			if(skillsId != null && !"".equals(skillsId)){
				logger.info(moduleName + "[查询技能信息;uuid:"+skillsId+"]");
				Skills skills = skillsService.selectByPrimaryKey(skillsId);
				if(skills != null){
					msgMap.put("skills",skills);
					msgMap.put("edit", true);
					msgMap.put("status", true);
				}else{
					msgMap.put("status", false);
					msgMap.put("msg", "未查询到 该条记录");
				}
			}else{
				//日志记录
				logger.info(moduleName + "[查询 并获取技能信息列表]");
				String perID = customer.getPerid();
				if(perID != null && ! "".equals(perID.trim())){
					msgMap.put("skillsList",skillsService.selectByPerID(perID));
					msgMap.put("edit", false);
					msgMap.put("status", true);
				}else{
					msgMap.put("status", false);
					msgMap.put("msg", "未查询到职业技能信息");
				}
			}
		}else{
			msgMap.put("msg", "登录失败,请重新登录后再进行相关操作");
			msgMap.put("status", -1);
		}
		return msgMap;
	}
	/**
	 * @Title 异步更新技能信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "skills")
	@RequestMapping("/updateSkills")
	@ResponseBody
	public Map<String, Object> updateSkills(HttpServletRequest request,Skills skills) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer != null){
			if(skills != null){
				Skills ski =skillsService.selectByPrimaryKey(skills.getUuid());
				if(ski !=null){
					logger.info(moduleName + "[更新技能信息;train:"+skills.getUuid()+"]");
					skillsService.updateByPrimaryKeySelective(skills);
				}else{
					logger.info(moduleName + "[插入技能信息;train:"+skills+"]");
					skills.setUuid(UUID.randomUUID().toString().replace("-", ""));
					skills.setPerid(customer.getPerid());
					skillsService.insertSelective(skills);
				}
				//获取个人信息状态  更改教育的状态 
				String perid = customer.getPerid();
				if(perid != null && ! "".equals(perid.trim())){
					PerProcess perProcess =  perProcessService.selectByPerID(perid);
					if(perProcess == null){
						perProcess = new PerProcess();
						perProcess.setUuid(UUID.randomUUID().toString().replace("-", ""));
						perProcess.setPerid(perid);
						perProcessService.insertSelective(perProcess);
					}
					if(skillsService.countSkills(perid) == 0){
						//设置教育的属性
						perProcess.setSkillstate(0);
					}else{
						perProcess.setSkillstate(1);
					}
					perProcessService.updateByPrimaryKeySelective(perProcess);
				}
				msgMap.put("status", true);
			}else{
				msgMap.put("msg", "参数错误,请刷新后重新尝试");
				msgMap.put("status", false);
			}
		}else{
			msgMap.put("msg", "登录失败,请重新登录后再进行相关操作");
			msgMap.put("status", -1);
		}
		return msgMap;
	}
	/**
	 * @Title 异步删除技能信息表
	 * @author  Administrator  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "skills")
	@RequestMapping("/deleteSkills")
	@ResponseBody
	public Map<String, Object> deleteSkills(HttpServletRequest request,String skillsId) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer != null){
			String perid = customer.getEntid();
			if(skillsId != null && ! "".equals(skillsId.trim())){
				skillsService.deleteByPrimaryKey(skillsId);
				//删除后更改 个人状态信息表 中数据状态
				if(perid != null && ! "".equals(perid.trim())){
					PerProcess perProcess =  perProcessService.selectByPerID(perid);
					if(perProcess == null){
						perProcess = new PerProcess();
						perProcess.setUuid(UUID.randomUUID().toString().replace("-", ""));
						perProcess.setPerid(perid);
						perProcessService.insertSelective(perProcess);
					}
					if(skillsService.countSkills(perid) == 0){
						//设置工作经验的属性
						perProcess.setSkillstate(0);
					}else{
						perProcess.setSkillstate(1);
					}
					perProcessService.updateByPrimaryKeySelective(perProcess);
				}
				msgMap.put("status", true);
				//日志记录
				logger.info(moduleName + "[删除个人技能信息]");
			}else{
				msgMap.put("status", false);
				msgMap.put("msg", "未查询到相关数据");
			}
		}else{
			msgMap.put("status",-1);
			msgMap.put("msg", "登陆超时,请重新登录后再进行删除操作");
		}
		return msgMap;
	}
	
	/**
	 * @title 异步加载 信用指标
	 * @author  孙尚飞  2017-8-30
	 * @desc
	 */
	@Permission(model = "personal", privilegeValue = "creditIndex")
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
		PerResult result= perResultService.selectByPerID(customer.getPerid());
		String path=new String();
		if(result != null){
			if(result.getInputxmlurl() != null){
				path=adr+result.getInputxmlurl();
				File file=new File(path);
				if(!file.exists()){
					flag=false;
					message.append("XML文件不存在");
				}
			}else{
				if(result.getModelid() != null && !"".equals(result.getModelid().trim())){
					Model model=modelService.selectByPrimaryKey(result.getModelid()); 
					if(model != null){
						if(model.getXmlurl() != null && !"".equals(model.getXmlurl())){
							path=adr+model.getXmlurl();
							File file=new File(path);
							if(!file.exists()){
								flag=false;
								message.append("XML文件不存在");
							}
						}else{
							flag=false;
							message.append("该企业未分配模型");
						}
					}else{
						flag=false;
						message.append("该企业未分配模型");
					}
				}else{
					flag=false;
					message.append("该企业未分配模型");
				}
			}
		}else{
			flag=false;
			message.append("该企业未进入评分流程");
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
			msgMap.put("status", true);
		}else{
			msgMap.put("status", false);
			msgMap.put("msg", message.toString());
		}
		return msgMap;
	}
	/**
	 * @title 异步更新信用指标
	 * @author  孙尚飞  2017-8-30
	 * @desc
	 */
	@Permission(model = "personal", privilegeValue = "creditIndex")
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
		PerResult result= perResultService.selectByPerID(customer.getPerid());
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
					perResultService.updateByPrimaryKey(result);
					PerProcess perProcess = perProcessService.selectByPerID(result.getPerid());
					if(perProcess != null){
						perProcess.setPushmodelstate(1);
						perProcessService.updateByPrimaryKeySelective(perProcess);
					}
					logger.info(moduleName + "[本地文件保存成功]");
					msgMap.put("status", true);
				}else{
					logger.info(moduleName + "[本地文件保存失败]");
					msgMap.put("status", false);
					msgMap.put("msg", "XML文件保存失败");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			msgMap.put("status", false);
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
	@Permission(model = "personal", privilegeValue = "uploadFile")
	@RequestMapping("/listUploadFile")
	@ResponseBody
	public Map<String, Object> listUploadFile(HttpServletRequest request) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		List<UploadFileCategory> categorys = uploadFileCategoryService.selectPersonalRelevant();
		if(!categorys.isEmpty()){
			msgMap.put("categorys",categorys);
			msgMap.put("status", true);
		}else{
			msgMap.put("status", false);
			msgMap.put("msg", "未查询到附件列表信息");
		}
		return msgMap;
	}
	
	/**
	 * @Title 上传附件
	 * @author  孙尚飞  @date 2017-8-22 
	 * @throws Exception 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "uploadFile")
	@RequestMapping("/uploadFile")
	@ResponseBody
	public void uploadFile(HttpServletRequest request) throws Exception {
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String perid = customer.getPerid();
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		String dir = request.getSession().getServletContext().getRealPath("");
		String realUrl=BusinessUtil.getMsg("uploadFileUrl")+perid+File.separator;
		String fileID=request.getParameter("uid");
		String fileSize=request.getParameter("size");
		String fileName=SaveFile.uploadFile(request,adr+realUrl,null);
		String fileUrl=realUrl+fileName;
		SaveFile.uploadFile(request,dir+realUrl,fileName);
		if(perid != null && ! "".equals(perid.trim())){
			UploadFileCategory filetype = uploadFileCategoryService.selectByPrimaryKey(fileID);
			if(filetype!=null&&filetype.getType()==0){
				List<PerUploadFile> files = perUploadFileService.selectByPerIDAndFileID(perid, fileID);
				if(!files.isEmpty()){
					for(PerUploadFile file : files){
						SaveFile.deleteFile(adr+file.getFileurl());
						SaveFile.deleteFile(dir+file.getFileurl());
						perUploadFileService.deleteByPrimaryKey(file.getUuid());
					}
				}
			}
			PerUploadFile file=new PerUploadFile();
			file.setAdddate(new Date());
			file.setPerid(perid);
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
			perUploadFileService.insert(file);
			System.out.println("附件保存成功");
		}
	}

	/**
	 * @Title 获取当前个人指定类型的附件信息
	 * @author  孙尚飞  @date 2017-8-22 
	 * @Description 
	 */
	@Permission(model = "personal", privilegeValue = "uploadFile")
	@RequestMapping("/getUploadFile")
	@ResponseBody
	public Map<String, Object> getUploadFile(HttpServletRequest request) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		String perid = customer.getPerid();
		String fileID=request.getParameter("fileID");
		String dir = request.getSession().getServletContext().getRealPath("");
		String realUrl=BusinessUtil.getMsg("uploadFileUrl");
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		if(perid != null && ! "".equals(perid.trim())){
			List<PerUploadFile> files = perUploadFileService.selectByPerIDAndFileID(perid,fileID);
			if(files.isEmpty()||files.size()==0){
				msgMap.put("msg", "无上传文件");
				msgMap.put("status", false);
			}else{
				List<UploadFileVO> ufvos=new ArrayList<UploadFileVO>();
				for(PerUploadFile file : files){
					UploadFileVO ufvo=new UploadFileVO();
					ufvo.setUuid(file.getUuid());
					ufvo.setEntitID(file.getPerid());
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
				msgMap.put("status", true);
			}
		}else{
			msgMap.put("msg", "参数错误");
			msgMap.put("status", false);
		}
		return msgMap;
	}
	
	/**
	 * @Title 删除附件
	 * @author  孙尚飞  @date 2017-8-22 
	 * @Description 
	 *
	 */
	@Permission(model = "personal", privilegeValue = "uploadFile")
	@RequestMapping("/deleteUploadFile")
	@ResponseBody
	public Map<String, Object> deleteUploadFile(HttpServletRequest request,String uuid) throws ParseException {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		String dir = request.getSession().getServletContext().getRealPath("");
		PerUploadFile perUploadFile = perUploadFileService.selectByPrimaryKey(uuid);
		if(perUploadFile!=null){
			String result1=SaveFile.deleteFile(adr+perUploadFile.getFileurl());
			String result2=SaveFile.deleteFile(dir+perUploadFile.getFileurl());
			if(result1.equals("fail")||result2.equals("fail")){
				msgMap.put("status", false);
				msgMap.put("msg", "图片删除失败");
			}else{
				perUploadFileService.deleteByPrimaryKey(uuid);
				msgMap.put("fileID", perUploadFile.getUploadfilecategoryid());
				msgMap.put("name", uploadFileCategoryService.selectByPrimaryKey(perUploadFile.getUploadfilecategoryid()).getName());
				msgMap.put("status", true);
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
	@Permission(model = "personal", privilegeValue = "uploadFile")
	@RequestMapping("/judgeMustFile")
	@ResponseBody
	public Map<String, Object> judgeMustFile(HttpServletRequest request) {
		logger.info(moduleName + "[判断必须上传的扫描件是否全部上传了]");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Customer customer = (Customer) request.getSession().getAttribute(SessionName.CUSTOMER);
		if(customer!=null){
			String perid = customer.getPerid();
			List<String> strs = perUploadFileService.judgeFileAllUpload(perid,"true");
			String str = "";
			for (int i = 0; i < strs.size(); i++) {
				if (strs.get(i).split("#").length == 1) {
					str += strs.get(i).split("#")[0].replace(
							"<font color='red'>＊</font>", "") + "/n";
				}
			}
			if (str != null && ! "".equals(str.trim())) {
				str += "请检查这些文件是否正确上传了！！";
				msgMap.put("msg", str);
				msgMap.put("status", false);
			} else {
				PerProcess perProcess =  perProcessService.selectByPerID(perid);
				if(perProcess != null){
					perProcess.setUploadfilestate(1);
					perProcessService.updateByPrimaryKeySelective(perProcess);
				}
				msgMap.put("status", true);
				msgMap.put("msg", "相关文件已经全部上传了，数据保存成功");
			}
		}
		return msgMap;
	}
}
