package com.credit.controller.enterprise;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
import com.credit.bean.enterprise.HistoricalData;
import com.credit.bean.enterprise.Shareholder;
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
import com.credit.service.enterprise.ProcessStateService;
import com.credit.service.enterprise.ShareholderService;
import com.credit.service.enterprise.UploadFileService;
import com.credit.service.member.CustomerService;
import com.credit.util.FileUtil;
import com.credit.util.HistoricalDataUtil;
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
@RequestMapping("/control/entScore")
public class EntScoreAction {
	private static final Logger logger = Logger.getLogger(EntScoreAction.class);
	private String moduleName = "管理员操作情况";
	private StringBuffer message = new StringBuffer("");
	private Boolean flag = true;
	Map<String, Object> msgMap = new HashMap<String, Object>();

	
	@Resource(name = "customerServiceBean")
	private CustomerService customerService;
	
	@Resource(name = "entBaseInfoServiceBean")
	private EntBaseInfoService entBaseInfoService;
	
	@Resource(name = "entResultServiceBean")
	private EntResultService entResultService;
	
	@Resource(name = "modelServiceBean")
	private ModelService modelService;
	
	@Resource(name = "processStateServiceBean")
	private ProcessStateService processStateService;
	
	@Resource(name = "executivesServiceBean")
	private ExecutivesService executivesService;
	
	@Resource(name = "shareholderServiceBean")
	private ShareholderService shareholderService;
	
	@Resource(name = "uploadFileServiceBean")
	private UploadFileService uploadFileService;
	

	
	/**
	 * @title 企业评分列表
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "entScore", privilegeValue = "list")
	@RequestMapping("/entScoreLists")
	@ResponseBody
	public Map<String, Object> entScoreLists(HttpServletRequest request,int page,int limit) {
		logger.info(moduleName + "[查看评分列表]");
		Map<String,String> params = new HashMap<String,String>();
		List<EntScoreVO> esvs=new ArrayList<EntScoreVO>();
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		String name=request.getParameter("name");
		String value=request.getParameter("value");
		QueryResult<EntScoreVO> queryResult = new QueryResult<EntScoreVO>();
		if(name!=null&&value!=null&&!"".equals(value.trim())){
			params.put(name, "'%" + value.trim() + "%'");
		}
		queryResult = entResultService.findScoreEnterperise(page, limit,params);
		esvs=queryResult.getResultlist();
		if(esvs!=null){
			List<EntScoreVO> lists=new ArrayList<EntScoreVO>();
			for(EntScoreVO esv : esvs){
				if(esv.getApplyReportState()==0&&esv.getScoreState()==0){
					esv.setState("[1]客户录入信息");
				}else if(esv.getApplyReportState()==1&&esv.getScoreState()==0){
					esv.setState("[2]管理员确认基本信息");
				}else if(esv.getApplyReportState()==1&&esv.getScoreState()==1){
					esv.setState("[3]管理员调查指标信息");
				}else if(esv.getApplyReportState()==1&&esv.getScoreState()==2){
					esv.setState("[4]管理员编辑或结束调查");
				}else if(esv.getApplyReportState()==1&&esv.getScoreState()==3){
					esv.setState("[5]客户初评");
				}else if(esv.getApplyReportState()==1&&esv.getScoreState()==4){
					esv.setState("[6]管理员终评");
				}else if(esv.getApplyReportState()==1&&esv.getScoreState()==5){
					esv.setState("[7]管理员管理评分报告");
				}else{
					esv.setState("该状态不存在");
				}
				//设置帐号状态
				Customer customer = customerService.selectByEntID(esv.getEntID());
				if(customer != null){
					int visible = customer.getVisible();
					esv.setVisible(visible);
					if(esv.getModelID() == null && visible == 0){
						esv.setState("<font style = 'color:#ef5959'>帐号未激活或模型未分配</font>");
					}else if(esv.getModelID() != null && visible == 0){
						esv.setState("<font style = 'color:rgb(245, 117, 9)'>帐号未激活</font>");
					}
				}
				//历史数据
				EntBaseInfo entBaseInfo=entBaseInfoService.find(esv.getEntID());
				Set<HistoricalData> histories=new HashSet<HistoricalData>();
				histories=entBaseInfo.getHistoricalData();
				if(histories.size()!=0||!histories.isEmpty()){
					for(HistoricalData history : histories){
						if(history.getHistoricalXMLUrl()!=null&&FileUtil.isExist(adr+root+history.getHistoricalXMLUrl())){
							EntScoreVO entscore=HistoricalDataUtil.getEntScore(adr+root+history.getHistoricalXMLUrl(), esv,history);
							lists.add(entscore);
						}
					}
				}
			}
			esvs.addAll(lists);
		}
		msgMap.put("total", queryResult.getTotalrecord());
		msgMap.put("list", esvs);
		return msgMap;
	}
	
	/**
	 * @title 企业评分时，显示企业基础信息
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "entScore", privilegeValue = "list")
	@RequestMapping("/entBaseInfoUI")
	@ResponseBody
	public Map<String, Object> entBaseInfoUI(String entID) {
		EntBaseInfo entBaseInfo = new EntBaseInfo();
		EntBaseInfo ent =entBaseInfoService.find(entID);
		if (ent != null) {
			msgMap.put("uuid",ent.getUuid());
			entBaseInfo.setUuid(ent.getUuid());
			msgMap.put("createTime",ent.getCreateTime());
			entBaseInfo.setCreateTime(ent.getCreateTime());
			msgMap.put("updateTime",ent.getUpdateTime());
			entBaseInfo.setUpdateTime(ent.getUpdateTime());
			msgMap.put("name",ent.getName());
			entBaseInfo.setName(ent.getName());
			msgMap.put("ename",ent.getEname());
			entBaseInfo.setEname(ent.getEname());
			msgMap.put("setupDate",ent.getSetupDate());
			entBaseInfo.setSetupDate(ent.getSetupDate());
			msgMap.put("issueDate",ent.getIssueDate());
			entBaseInfo.setIssueDate(ent.getIssueDate());
			msgMap.put("startDate",ent.getStartDate());
			entBaseInfo.setStartDate(ent.getStartDate());
			msgMap.put("endDate",ent.getEndDate());
			entBaseInfo.setEndDate(ent.getEndDate());
			msgMap.put("address",ent.getAddress());
			entBaseInfo.setAddress(ent.getAddress());
			msgMap.put("regiCapital",ent.getRegiCapital());
			entBaseInfo.setRegiCapital(ent.getRegiCapital());
			msgMap.put("currencyType",ent.getCurrencyType());
			entBaseInfo.setCurrencyType(ent.getCurrencyType());
			msgMap.put("legalPerson",ent.getLegalPerson());
			entBaseInfo.setLegalPerson(ent.getLegalPerson());
			msgMap.put("tel",ent.getTel());
			entBaseInfo.setTel(ent.getTel());
			msgMap.put("fax",ent.getFax());
			entBaseInfo.setFax(ent.getFax());
			msgMap.put("zipCode",ent.getZipCode());
			entBaseInfo.setZipCode(ent.getZipCode());
			msgMap.put("email",ent.getEmail());
			entBaseInfo.setEmail(ent.getEmail());
			msgMap.put("website",ent.getWebsite());
			entBaseInfo.setWebsite(ent.getWebsite());
			msgMap.put("scale",ent.getScale());
			entBaseInfo.setScale(ent.getScale());
			msgMap.put("entType",ent.getEntType());
			entBaseInfo.setEntType(ent.getEntType());
			msgMap.put("regisOrg",ent.getRegisOrg());
			entBaseInfo.setRegisOrg(ent.getRegisOrg());
			msgMap.put("industry",ent.getIndustry());
			entBaseInfo.setIndustry(ent.getIndustry());
			msgMap.put("businessScope",ent.getBusinessScope());
			entBaseInfo.setBusinessScope(ent.getBusinessScope());
			msgMap.put("brief",ent.getBrief());
			entBaseInfo.setBrief(ent.getBrief());
			msgMap.put("uscc",ent.getUSCC());
			entBaseInfo.setUSCC(ent.getUSCC());
			
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("entBaseInfo", entBaseInfo);
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
		}
		return msgMap;
	}
	/**
	 * @title 企业评分时显示高层信息
	 * @author  孙尚飞  2017-8-11
	 * @desc
	 */
	@Permission(model = "entScore", privilegeValue = "list")
	@RequestMapping("/entExecutivesList")
	@ResponseBody
	public Map<String, Object> entExecutivesList(String entID,String uuid,int limit,int page){
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if(entID!=null &&! "".equals(entID.trim())){
			if(params.size()>0){
				jpql.append(" and ");
			} 
			jpql.append(" o.EntID = ?"+ (params.size()+1));
			params.add(entID.trim());
		}
		if(uuid!=null &&! "".equals(uuid.trim())){
			if(params.size()>0){
				jpql.append(" and ");
			} 
			jpql.append(" o.uuid = ?"+ (params.size()+1));
			params.add(uuid.trim());
		}
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("name", "desc");
		PageView<Executives> pageView= new PageView<Executives>(limit,page);
		QueryResult<Executives> qr = executivesService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), jpql.toString(), params.toArray(), orderby);
		pageView.setQueryResult(qr);
		msgMap.put("executives", qr.getResultlist());
		msgMap.put("total", qr.getTotalrecord());
		msgMap.put("success", true);
		msgMap.put("status", true);	
		return msgMap;
	}
	/**
	 * @title 企业评分时显示股东信息
	 * @author  孙尚飞  2017-8-11
	 * @desc
	 */
	@Permission(model = "entScore", privilegeValue = "list")
	@RequestMapping("/entShareholderList")
	@ResponseBody
	public Map<String, Object> entShareholderList(String entID,String judge,String uuid,int limit,int page){
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if(entID!=null &&! "".equals(entID.trim())){
			if(params.size()>0){
				jpql.append(" and ");
			} 
			jpql.append(" o.EntID = ?"+ (params.size()+1));
			params.add(entID.trim());
		}
		if(uuid!=null &&! "".equals(uuid.trim())){
			if(params.size()>0){
				jpql.append(" and ");
			} 
			jpql.append(" o.uuid = ?"+ (params.size()+1));
			params.add("%"+ uuid.trim() + "%");
		}
	
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("stockpercent", "desc");
		PageView<Shareholder> pageView= new PageView<Shareholder>(limit,page);
		QueryResult<Shareholder> qr = shareholderService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), jpql.toString(), params.toArray(), orderby);
		pageView.setQueryResult(qr);
		msgMap.put("shareholders", qr.getResultlist());
		msgMap.put("total", qr.getTotalrecord());
		msgMap.put("success", true);
		msgMap.put("status", true);	
		return msgMap;
	}
	/**
	 * @title 企业评分时显示上传附件
	 * @author  孙尚飞  2017-8-11
	 * @desc
	 */
	@Permission(model = "entScore", privilegeValue = "list")
	@RequestMapping("/uploadFiles")
	@ResponseBody
	public Map<String, Object> uploadFiles(String name,String entID,int limit,int page) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Map<String, String> searchpParams = new HashMap<String, String>();
		if (name != null && !"".equals(name.trim())) {
			searchpParams.put("name", name);
		}
		QueryResult<UploadFileVO> queryResult = new QueryResult<UploadFileVO>();
		queryResult = uploadFileService.findEntUploadFiles(searchpParams,entID, page, limit);
		msgMap.put("total", queryResult.getTotalrecord());
		msgMap.put("uploadFiles", queryResult.getResultlist());
		msgMap.put("success", true);
		return msgMap;
	}
	/**
	 * @title 用户录入信息填写的指标信息
	 * @author  孙尚飞  2017-8-16
	 * @desc
	 */
	@Permission(model = "entScore", privilegeValue = "list")
	@RequestMapping("/extraIndexList")
	@ResponseBody
	public Map<String, Object> extraIndexList(String resultID,HttpServletRequest request){
		Map<String, Object> msgMap = new HashMap<String, Object>();
		List<Index> indexs=new ArrayList<Index>();
		//String dir=request.getSession().getServletContext().getRealPath("");
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		flag=true;
		message.setLength(0);
		EntResult result=entResultService.find(resultID);
		String path=new String();
		if(result==null){
			flag=false;
			message.append("该企业不存在");
		}else{
			if(result.getInputXMLUrl()==null){
				flag=false;
				message.append("该企业填写的信用信息为空");
			}else{
				path=adr+root+result.getInputXMLUrl();
				File file=new File(path);
				if(!file.exists()){
					flag=false;
					message.append("该企业填写的信用信息XML文件丢失");
				}
			}
		}
		if(flag){
			try {
				File file=new File(path);
				Document document = new SAXReader().read(file);
				indexs=ModelUtil.getIndex(document);
			} catch (DocumentException e) {
				e.printStackTrace();
			}	
			Map<String, List<Index>> map = new HashMap<String, List<Index>>();
			String firstname=new String();
			for(Index index1 : indexs){
				firstname=index1.getName();
				List<Index> index=new ArrayList<Index>();
				for(Index index2 : index1.getChirds()){
					for(Index index3 : index2.getChirds()){
						if(index3.getPushed()!=null&&index3.getPushed().equals("true")){
							for(Option option : index3.getOptions()){
								if(option.getSelected()!=null&&option.getSelected().equals("true")){
									index3.setInsert(option.getName());
								}
							}
							index.add(index3);
						}
					}
				}
				map.put(firstname, index);
			}
			msgMap.put("indexs", map);
			msgMap.put("statue", true);
		}else{
			msgMap.put("statue", false);
			msgMap.put("msg", message.toString());
		}
		return msgMap;
	}
	/**
	 * @title 报告模板列表
	 * @author  孙尚飞  2017-8-16
	 * @desc
	 */
	@Permission(model = "entScore", privilegeValue = "list")
	@RequestMapping("/reportTemplateList")
	@ResponseBody
	public Map<String, Object> reportTemplateList(){
		Map<String, Object> msgMap = new HashMap<String, Object>();
		List<Option> options=new ArrayList<Option>();
		String ReportTemplate=BusinessUtil.getMsg("reportTemplate");
		String[] report=null;
		if(ReportTemplate!=null&&!"".equals(ReportTemplate)){
			if(ReportTemplate.indexOf("#")!=-1){
				report=ReportTemplate.split("#");
				for(String str : report){
					Option option=new Option();
					option.setName(str.split("=")[0]);
					option.setValue(str.split("=")[1]);
					options.add(option);
				}
			}else{
				Option option=new Option();
				option.setName(ReportTemplate.split("=")[0]);
				option.setValue(ReportTemplate.split("=")[1]);
				options.add(option);
			}
		}
		
		/*List<ReportTemplateCategory> reportTemplateCategorys = new ArrayList<ReportTemplateCategory>();
		List<ReportTemplate> reportTemplates = new ArrayList<ReportTemplate>();
		List<ReportTemplateCategoryVO> reportTemplateCategoryVOs = new ArrayList<ReportTemplateCategoryVO>();
		StringBuffer jpql = new StringBuffer("");
		if(uuid != null && !"".equals(uuid.trim())){
			for (ReportTemplate rt : reportTemplateCategoryService.find(uuid).getReportTemplates()) {
				ReportTemplate reportTemplateT = new ReportTemplate();
				reportTemplateT.setUploadFiles(rt.getUploadFiles());
				reportTemplateT.setName(rt.getName());
				reportTemplates.add(reportTemplateT);
			}
			msgMap.put("reportTemplates", reportTemplates);
		}else{
			jpql.append(" o.visible = 1");
			reportTemplateCategorys = reportTemplateCategoryService.getQueryData(jpql.toString(), null, null);
			if(reportTemplateCategorys != null && reportTemplateCategorys.size()>0){
				for (int i = 0; i < reportTemplateCategorys.size(); i++) {
					ReportTemplateCategory s = reportTemplateCategorys.get(i);
					ReportTemplateCategoryVO v= new ReportTemplateCategoryVO();
					v.setUuid(s.getUuid());
					v.setName(s.getName());
					reportTemplateCategoryVOs.add(v);
				}
			}
			msgMap.put("reportTemplateCategoryVOs", reportTemplateCategoryVOs);
		}*/
		msgMap.put("options", options);
		msgMap.put("success",true);
		msgMap.put("status",true);
		return msgMap;
	}
}

