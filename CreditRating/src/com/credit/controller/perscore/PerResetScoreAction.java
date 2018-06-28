package com.credit.controller.perscore;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.enterprise.EntResult;
import com.credit.bean.enterprise.HistoricalData;
import com.credit.bean.enterprise.Opinion;
import com.credit.bean.enterprise.ProcessState;
import com.credit.bean.enterprise.UploadFile;
import com.credit.bean.member.Customer;
import com.credit.bean.person.Career;
import com.credit.bean.person.Education;
import com.credit.bean.person.PerBaseInfo;
import com.credit.bean.person.PerHistory;
import com.credit.bean.person.PerOpinion;
import com.credit.bean.person.PerProcess;
import com.credit.bean.person.PerResult;
import com.credit.bean.person.PerUploadFile;
import com.credit.bean.person.Skills;
import com.credit.bean.person.Train;
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.member.CustomerService;
import com.credit.service.person.CareerService;
import com.credit.service.person.EducationService;
import com.credit.service.person.PerBaseInfoService;
import com.credit.service.person.PerHistoryService;
import com.credit.service.person.PerOpinionService;
import com.credit.service.person.PerProcessService;
import com.credit.service.person.PerResultService;
import com.credit.service.person.PerUploadFileService;
import com.credit.service.person.SkillsService;
import com.credit.service.person.TrainService;
import com.credit.util.FileUtil;
import com.credit.util.HistoricalDataUtil;
import com.credit.util.properties.BusinessUtil;


/**
 * @title 评分流程
 * @author 孙尚飞   2017-7-31
 * @desc
 */
@Controller
@RequestMapping("/control/perResetScore")
public class PerResetScoreAction {
	private static final Logger logger = Logger.getLogger(PerResetScoreAction.class);
	private String moduleName = "管理员操作情况";
	private StringBuffer message = new StringBuffer("");
	private Boolean flag = true;
	Map<String, Object> msgMap = new HashMap<String, Object>();

	@Resource(name = "perBaseInfoServiceBean")
	private PerBaseInfoService perBaseInfoService;
	
	@Resource(name = "perResultServiceBean")
	private PerResultService perResultService;
	
	@Resource(name = "perProcessServiceBean")
	private PerProcessService processService;
	
	@Resource(name = "perOpinionServiceBean")
	private PerOpinionService opinionService;
	
	@Resource(name = "perHistoryServiceBean")
	private PerHistoryService perHistoryService;
	
	@Resource(name = "customerServiceBean")
	private CustomerService customerService;
	
	@Resource(name = "careerServiceBean")
	private CareerService careerService;
	
	@Resource(name = "educationServiceBean")
	private EducationService educationService;
	
	@Resource(name = "perUploadFileServiceBean")
	private PerUploadFileService perUploadFileService;
	
	@Resource(name = "skillsServiceBean")
	private SkillsService skillsService;
	
	@Resource(name = "trainServiceBean")
	private TrainService trainService;

	
	/**
	 * @title 个人重新评分
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "entScore", privilegeValue = "process")
	@RequestMapping("/resetScoring")
	@ResponseBody
	public Map<String, Object> resetScoring(String resultID,int state) {
		PerResult result=perResultService.find(resultID);
		Customer customer=new Customer();
		PerProcess process=new PerProcess();
		PerBaseInfo perBaseinfo=new PerBaseInfo();
		Set<PerOpinion> opinions=new HashSet<PerOpinion>();
		if(result==null){
			flag=false;
		}else{
			perBaseinfo=result.getPerBaseInfo();
			customer=result.getCustomer();
			if(perBaseinfo==null){
				flag=false;
			}else{
				process=result.getPerBaseInfo().getProcess();
				opinions=result.getPerBaseInfo().getOpinion();
				if(process==null){
					flag=false;
				}
			}
			if(customer==null){
				flag=false;
			}
		}
		if(flag){
			PerResult perResult=new PerResult(resultID);
			perResult.setCustomer(result.getCustomer());
			perResult.setPerBaseInfo(result.getPerBaseInfo());
			perResult.setModel(result.getModel());
			if(opinions.size()>0){
				for(PerOpinion opinion : opinions){
					opinionService.delete(opinion.getUuid());
				}
			}
			if(state==0){//从客户录入信息开始
				process.setApplyReportState(0);
				process.setScoreState(0);
				process.setReadState(1);
				process.setBaseInfoState(0);
				process.setCareerState(0);
				process.setEducationState(0);
				process.setSkillState(0);
				process.setTrainState(0);
				process.setPushModelState(0);
				process.setUploadFileState(0);
			}else if(state==1){//管理员确认信息开始
				process.setApplyReportState(1);
				process.setScoreState(0);
				perResult.setInputXMLTime(result.getInputXMLTime());
				perResult.setInputXMLUrl(result.getInputXMLUrl());
			}
			perResultService.update(perResult);
			msgMap.put("success", true);
			msgMap.put("msg", "评分重置成功");
		}else{
			msgMap.put("success", true);
			msgMap.put("msg", "评分重置失败");
		}
		return msgMap;
	}
	
	/**
	 * @title 个人第二次评分
	 * @author  孙尚飞  2017-7-31
	 * @throws Exception 
	 * @desc
	 */
	//@Permission(model = "resetEntScore", privilegeValue = "history")
	@RequestMapping("/secondScoring")
	@ResponseBody
	public Map<String, Object> secondScoring(String resultID,HttpServletRequest request) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		message.setLength(0);
		PerResult result=perResultService.find(resultID);
		PerBaseInfo perBaseinfo=new PerBaseInfo();
		Customer customer=new Customer();
		PerProcess process=new PerProcess();
		Set<PerOpinion> opinions=new HashSet<PerOpinion>();
		Set<PerHistory> perHistorys=new HashSet<PerHistory>();
		if(result==null){
			flag=false;
		}else{
			perBaseinfo=result.getPerBaseInfo();
			customer=result.getCustomer();
			if(perBaseinfo==null){
				flag=false;
			}else{
				process=result.getPerBaseInfo().getProcess();
				opinions=result.getPerBaseInfo().getOpinion();
				if(process==null){
					flag=false;
				}
			}
			if(customer==null){
				flag=false;
			}
			/*if(result.getGradeTime()!=null){
				int a=Integer.parseInt(BusinessUtil.getMsg("intervalTime"));
				int b=DateUtil.daysBetween(result.getGradeTime(), new Date());
				if(a>=b){
					flag=false;
					message.append("距离上一次评分不足"+BusinessUtil.getMsg("intervalTime")+"天，无法进行第二次评分");
				}
			}else{
				flag=false;
				message.append("当次评分没有生成报告，无法进行第二次评分");
			}*/
		}
		if(flag){
			String historyID=UUID.randomUUID().toString().replace("-", "");
			String root=BusinessUtil.getMsg("historyXMLUrl")+perBaseinfo.getUuid()+File.separator+sdf.format(new Date())+File.separator;
			/*保存历史数据XML开始*/
			String pathname=root+historyID+"history.xml";
			String path=BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root")+pathname;
			File file = new File(BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root")+root);
			if (!file.exists()) {file.mkdirs();}
			List<Career> careers=careerService.findAllByPerID(perBaseinfo.getUuid());
			List<Education> educations=educationService.findAllByPerID(perBaseinfo.getUuid());
			List<Skills> skillss=skillsService.findAllByPerID(perBaseinfo.getUuid());
			List<Train> trains=trainService.findAllByPerID(perBaseinfo.getUuid());
			/*保存评分XML、财务数据、附件信息开始*/
			Map<String, String> map=saveOtherFiles(root,result);
			/*保存评分XML、财务数据、附件信息结束*/
			//生成企业历史数据XML
			HistoricalDataUtil.savePerHistoricalDataXML(perBaseinfo, result, careers, educations,skillss,trains, path,map);
			/*保存历史数据XML结束*/
			PerHistory data=new PerHistory();
			data.setPerBaseInfo(perBaseinfo);
			data.setHistoricalXMLUrl(pathname);
			//快照保存
			String  snapshot=root+historyID+"history.pdf";
			String  snapshotpath=BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root")+snapshot;
			if(HistoricalDataUtil.savePerHistoricalSnapshot(perBaseinfo, result, snapshotpath,map.get("score"))){
				data.setSnapshotUrl(snapshot);
			}
			data.setUpdateTime(new Date());
			data.setUuid(historyID);
			perHistoryService.save(data);
			//跨服务器传递文件
			if(BusinessUtil.getMsg("CrossUpload").equals("1")){
				if(result.getCustomer()!=null){
					if(!("".equals(result.getCustomer().getDomainName())||result.getCustomer().getDomainName()==null)){
						String url=request.getScheme()+"://"+result.getCustomer().getDomainName();
						FileUtil.readfile(BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root")+root,url);
					}
				}
			}
			
			perHistorys.add(data);
			perBaseinfo.setHistory(perHistorys);
			perBaseinfo.setUpdateTime(new Date());
			perBaseInfoService.update(perBaseinfo);
			customer.setVisible(0);
			customer.setUpdateTime(new Date());
			customerService.update(customer);
			if(opinions.size()>0){
				for(PerOpinion opinion : opinions){
					opinionService.delete(opinion.getUuid());
				}
			}
			logger.info("开始更新");
			//更新状态表
			PerProcess perProcess = new PerProcess();
			perProcess.setUuid(process.getUuid());
			perProcess.setPerBaseInfo(process.getPerBaseInfo());
			processService.update(perProcess);
			//更新 评分表
			PerResult perResult = new PerResult();
			perResult.setUuid(result.getUuid());
			perResult.setCustomer(result.getCustomer());
			perResult.setPerBaseInfo(result.getPerBaseInfo());
			perResultService.update(perResult);
			
			logger.info("结束删除");
			msgMap.put("success", true);
			msgMap.put("msg", "评分重置成功");
		}else{
			msgMap.put("success", true);
			if(message.length()!=0){
				msgMap.put("msg", message.toString());
			}else{
				msgMap.put("msg", "评分重置失败");
			}
			
		}
		return msgMap;
	}

	private Map<String, String> saveOtherFiles(String path, PerResult result) {
		Map<String, String> map=new HashMap<String, String>();
		logger.info("开始保存评分XML");
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		String oldpath1=adr+result.getInputXMLUrl();
		String oldpath2=adr+result.getScoreXMLUrl();
		String newpath1=path+result.getUuid()+"input.xml";
		String newpath2=path+result.getUuid()+"score.xml";
		File file = new File(adr+path);
		if (!file.exists()) {file.mkdirs();}
		if(FileUtil.copyAndDelete(oldpath1, adr+newpath1)){
			logger.info("客户评分XML保存成功");
		    map.put("input", newpath1);
		}
		if(FileUtil.copyAndDelete(oldpath2, adr+newpath2)){
			logger.info("管理员评分XML保存成功");
		map.put("score", newpath2);
		}
		logger.info("评分XML保存结束");
		logger.info("附件数据保存开始");
		List<PerUploadFile> files=perUploadFileService.findAllByPerID(result.getPerBaseInfo().getUuid());
		String uploadFilepath=path+"uploadfile"+File.separator;
		File uploadfile=new File(adr+uploadFilepath);
		if (!uploadfile.exists()) {uploadfile.mkdirs();}
		for(PerUploadFile upload : files){
			String oldfile=adr+upload.getFileUrl();
			String str=oldfile.substring(oldfile.lastIndexOf(".") + 1);
			String newfile=adr+uploadFilepath+upload.getUuid()+"."+str;
			if(FileUtil.copyFile(oldfile, newfile))
				logger.info("附件数据保存成功");
		}
		map.put("uploadfile", uploadFilepath);
		logger.info("附件数据保存结束");
		logger.info("评分报告保存开始");
		String oldreportpath=adr+result.getReportUrl();
		String newreportpath=path+result.getUuid()+"report.pdf";
		if(FileUtil.copyAndDelete(oldreportpath, adr+newreportpath))
			logger.info("评分报告保存成功");
		map.put("report", newreportpath);
		logger.info("评分报告保存结束");
		return map;
	}
}

