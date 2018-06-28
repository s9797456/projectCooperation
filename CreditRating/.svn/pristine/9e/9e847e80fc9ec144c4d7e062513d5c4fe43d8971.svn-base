package com.credit.controller.enterprise;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.enterprise.EntBaseInfo;
import com.credit.bean.enterprise.EntResult;
import com.credit.bean.enterprise.Executives;
import com.credit.bean.enterprise.Finance;
import com.credit.bean.enterprise.HistoricalData;
import com.credit.bean.enterprise.Opinion;
import com.credit.bean.enterprise.ProcessState;
import com.credit.bean.enterprise.Shareholder;
import com.credit.bean.enterprise.UploadFile;
import com.credit.bean.member.Customer;
import com.credit.bean.pagelist.PageView;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.vo.enterprise.EntScoreVO;
import com.credit.bean.vo.enterprise.UploadFileVO;
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.addition.ModelService;
import com.credit.service.enterprise.EntBaseInfoService;
import com.credit.service.enterprise.EntResultService;
import com.credit.service.enterprise.ExecutivesService;
import com.credit.service.enterprise.FinanceService;
import com.credit.service.enterprise.HistoricalDataService;
import com.credit.service.enterprise.OpinionService;
import com.credit.service.enterprise.ProcessStateService;
import com.credit.service.enterprise.ShareholderService;
import com.credit.service.enterprise.UploadFileService;
import com.credit.service.member.CustomerService;
import com.credit.util.DateUtil;
import com.credit.util.FileUtil;
import com.credit.util.HistoricalDataUtil;
import com.credit.util.SaveFilePathUtil;
import com.credit.util.WebUtil;
import com.credit.util.model.Index;
import com.credit.util.model.ModelUtil;
import com.credit.util.model.Option;
import com.credit.util.properties.BusinessUtil;


/**
 * @title 评分流程
 * @author 孙尚飞   2017-7-31
 * @desc
 */
@Controller
@RequestMapping("/control/resetEntScore")
public class ResetEntScoreAction {
	private static final Logger logger = Logger.getLogger(ResetEntScoreAction.class);
	private String moduleName = "管理员操作情况";
	private StringBuffer message = new StringBuffer("");
	private Boolean flag = true;
	Map<String, Object> msgMap = new HashMap<String, Object>();

	@Resource(name = "entBaseInfoServiceBean")
	private EntBaseInfoService entBaseInfoService;
	
	@Resource(name = "entResultServiceBean")
	private EntResultService entResultService;
	
	@Resource(name = "processStateServiceBean")
	private ProcessStateService processStateService;
	
	@Resource(name = "shareholderServiceBean")
	private ShareholderService shareholderService;
	
	@Resource(name = "executivesServiceBean")
	private ExecutivesService executivesService;
	
	@Resource(name = "opinionServiceBean")
	private OpinionService opinionService;
	
	@Resource(name = "historicalDataServiceBean")
	private HistoricalDataService historicalDataService;
	
	@Resource(name = "customerServiceBean")
	private CustomerService customerService;
	
	@Resource(name = "financeServiceBean")
	private FinanceService financeService;
	
	@Resource(name = "uploadFileServiceBean")
	private UploadFileService uploadFileService;

	
	/**
	 * @title 企业重新评分
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "entScore", privilegeValue = "process")
	@RequestMapping("/resetScoring")
	@ResponseBody
	public Map<String, Object> resetScoring(String resultID,int state) {
		EntResult result=entResultService.find(resultID);
		Customer customer=new Customer();
		ProcessState process=new ProcessState();
		EntBaseInfo entBaseinfo=new EntBaseInfo();
		Set<Opinion> opinions=new HashSet<Opinion>();
		if(result==null){
			flag=false;
		}else{
			entBaseinfo=result.getEntBaseInfo();
			customer=result.getCustomer();
			if(entBaseinfo==null){
				flag=false;
			}else{
				process=result.getEntBaseInfo().getProcess();
				opinions=result.getEntBaseInfo().getOpinion();
				if(process==null){
					flag=false;
				}
			}
			if(customer==null){
				flag=false;
			}
		}
		if(flag){
			EntResult entResult=new EntResult(resultID);
			entResult.setCustomer(result.getCustomer());
			entResult.setEntBaseInfo(result.getEntBaseInfo());
			entResult.setModel(result.getModel());
			if(opinions.size()>0){
				for(Opinion opinion : opinions){
					opinionService.delete(opinion.getUuid());
				}
			}
			if(state==0){//从客户录入信息开始
				process.setApplyReportState(0);
				process.setScoreState(0);
				process.setBaseInfoState(0);
				process.setExecutivesState(0);
				process.setFinanceState(0);
				process.setPushModelState(0);
				process.setShareholderState(0);
				process.setUploadFileState(0);
			}else if(state==1){//管理员确认信息开始
				process.setApplyReportState(1);
				process.setScoreState(0);
				entResult.setInputXMLTime(result.getInputXMLTime());
				entResult.setInputXMLUrl(result.getInputXMLUrl());
			}
			entResultService.update(entResult);
			msgMap.put("success", true);
			msgMap.put("msg", "评分重置成功");
		}else{
			msgMap.put("success", true);
			msgMap.put("msg", "评分重置失败");
		}
		return msgMap;
	}
	
	/**
	 * @title 企业第二次评分
	 * @author  孙尚飞  2017-7-31
	 * @throws Exception 
	 * @desc
	 */
	@Permission(model = "resetEntScore", privilegeValue = "history")
	@RequestMapping("/secondScoring")
	@ResponseBody
	public Map<String, Object> secondScoring(String resultID,HttpServletRequest request) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		message.setLength(0);
		EntResult result=entResultService.find(resultID);
		EntBaseInfo entBaseinfo=new EntBaseInfo();
		Customer customer=new Customer();
		ProcessState process=new ProcessState();
		Set<Opinion> opinions=new HashSet<Opinion>();
		Set<HistoricalData> historicalDatas=new HashSet<HistoricalData>();
		if(result==null){
			flag=false;
		}else{
			entBaseinfo=result.getEntBaseInfo();
			customer=result.getCustomer();
			if(entBaseinfo==null){
				flag=false;
			}else{
				process=result.getEntBaseInfo().getProcess();
				opinions=result.getEntBaseInfo().getOpinion();
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
			String root=BusinessUtil.getMsg("historyXMLUrl")+entBaseinfo.getUuid()+File.separator+sdf.format(new Date())+File.separator;
			/*保存历史数据XML开始*/
			String pathname=root+historyID+"history.xml";
			String path=BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root")+pathname;
			File file = new File(BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root")+root);
			if (!file.exists()) {file.mkdirs();}
			List<Executives> executives = executivesService.findAllByEntID(entBaseinfo.getUuid());
			List<Shareholder> shareholders = shareholderService.findAllByEntID(entBaseinfo.getUuid());
			/*保存评分XML、财务数据、附件信息开始*/
			Map<String, String> map=saveOtherFiles(root,result);
			/*保存评分XML、财务数据、附件信息结束*/
			//生成企业历史数据XML
			HistoricalDataUtil.saveHistoricalDataXML(entBaseinfo, result, shareholders, executives, path,map);
			/*保存历史数据XML结束*/
			HistoricalData data=new HistoricalData();
			data.setEntBaseInfo(entBaseinfo);
			data.setEntName(entBaseinfo.getName());
			data.setHistoricalXMLUrl(pathname);
			//快照保存
			String  snapshot=root+historyID+"history.pdf";
			String  snapshotpath=BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root")+snapshot;
			if(HistoricalDataUtil.saveHistoricalSnapshot(entBaseinfo, result, snapshotpath,map.get("score"))){
				data.setSnapshotUrl(snapshot);
			}
			data.setUpdateTime(new Date());
			data.setUuid(historyID);
			historicalDataService.save(data);
			//跨服务器传递文件
			if(BusinessUtil.getMsg("CrossUpload").equals("1")){
				if(result.getCustomer()!=null){
					if(!("".equals(result.getCustomer().getDomainName())||result.getCustomer().getDomainName()==null)){
						String url=request.getScheme()+"://"+result.getCustomer().getDomainName();
						FileUtil.readfile(BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root")+root,url);
					}
				}
			}
			
			historicalDatas.add(data);
			entBaseinfo.setHistoricalData(historicalDatas);
			entBaseinfo.setUpdateTime(new Date());
			entBaseInfoService.update(entBaseinfo);
			customer.setVisible(0);
			customer.setUpdateTime(new Date());
			customerService.update(customer);
			if(opinions.size()>0){
				for(Opinion opinion : opinions){
					opinionService.delete(opinion.getUuid());
				}
			}
			logger.info("开始更新");
			//更新状态表
			ProcessState processState = new ProcessState();
			processState.setUuid(process.getUuid());
			processState.setEntBaseInfo(process.getEntBaseInfo());
			processStateService.update(processState);
			//更新 评分表
			EntResult entResult = new EntResult();
			entResult.setUuid(result.getUuid());
			entResult.setCustomer(result.getCustomer());
			entResult.setEntBaseInfo(result.getEntBaseInfo());
			entResultService.update(entResult);
			
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

	private Map<String, String> saveOtherFiles(String path, EntResult result) {
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
		logger.info("财务数据保存开始");
		List<Finance> finances = financeService.findAllByEntID(result.getEntBaseInfo().getUuid());
		String financepath=path+"finance"+File.separator;
		File financefile=new File(adr+financepath);
		if (!financefile.exists()) {financefile.mkdirs();}
		for(Finance finance : finances){
			String oldfinance=adr+finance.getFileurl();
			String newfinance=adr+financepath+finance.getUuid()+".xlsx";
			if(FileUtil.copyFile(oldfinance, newfinance))
				logger.info("财务数据EXECL保存成功");
		}
		map.put("finance", financepath);
		logger.info("财务数据保存结束");
		logger.info("附件数据保存开始");
		List<UploadFile> files=uploadFileService.findAllByEntID(result.getEntBaseInfo().getUuid());
		String uploadFilepath=path+"uploadfile"+File.separator;
		File uploadfile=new File(adr+uploadFilepath);
		if (!uploadfile.exists()) {uploadfile.mkdirs();}
		for(UploadFile upload : files){
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

