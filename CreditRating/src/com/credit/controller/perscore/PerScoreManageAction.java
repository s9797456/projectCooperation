package com.credit.controller.perscore;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.addition.Model;
import com.credit.bean.enterprise.EntResult;
import com.credit.bean.enterprise.HistoricalData;
import com.credit.bean.member.User;
import com.credit.bean.person.PerBaseInfo;
import com.credit.bean.person.PerHistory;
import com.credit.bean.person.PerOpinion;
import com.credit.bean.person.PerResult;
import com.credit.bean.vo.addition.ModelVO;
import com.credit.bean.vo.person.PerResultVO;
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.addition.ModelService;
import com.credit.service.enterprise.EntBaseInfoService;
import com.credit.service.enterprise.EntResultService;
import com.credit.service.enterprise.HistoricalDataService;
import com.credit.service.person.PerBaseInfoService;
import com.credit.service.person.PerHistoryService;
import com.credit.service.person.PerOpinionService;
import com.credit.service.person.PerResultService;
import com.credit.util.CODEUtil;
import com.credit.util.FileUtil;
import com.credit.util.HistoricalDataUtil;
import com.credit.util.WebUtil;
import com.credit.util.checkbox.CheckBox;
import com.credit.util.model.Index;
import com.credit.util.model.IndexMap;
import com.credit.util.model.IndexVO;
import com.credit.util.model.ModelUtil;
import com.credit.util.model.Option;
import com.credit.util.properties.BusinessUtil;
/**
 * @title 评分流程
 * @author 孙尚飞   2017-7-31
 * @desc
 */
@Controller
@RequestMapping("/control/perScore/manage")
public class PerScoreManageAction {
	private static final Logger logger = Logger.getLogger(PerScoreManageAction.class);
	private String moduleName = "管理员操作情况";
	private StringBuffer message = new StringBuffer("");
	private Boolean flag = true;
	Map<String, Object> msgMap = new HashMap<String, Object>();
	
	@Resource(name = "perBaseInfoServiceBean")
	private PerBaseInfoService perBaseInfoService;
	
	@Resource(name = "perResultServiceBean")
	private PerResultService perResultService;
	
	@Resource(name = "modelServiceBean")
	private ModelService modelService;
	
	@Resource(name = "perOpinionServiceBean")
	private PerOpinionService perOpinionService;
	
	@Resource(name = "perHistoryServiceBean")
	private PerHistoryService perHistoryService;
	/**
	 * @title 客户分配模型功能，下拉显示模型
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	//@Permission(model = "entScore", privilegeValue = "process")
	@RequestMapping("/saveModelUI")
	@ResponseBody
	public Map<String, Object> saveModelUI(String modelID) throws Exception {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		List<ModelVO> list = new ArrayList<ModelVO>();
		if (modelID != null && !"".equals(modelID.trim())) {
			Model model = modelService.find(modelID);
			for (Model m : model.getChilds()) {
				ModelVO mv = new ModelVO();
				mv.setUuid(m.getUuid());
				mv.setName(m.getName()+m.getVersion());
				list.add(mv);
			}
		} else {
			List<Model> modellist = modelService.getAllRootModel();
			for (Model m : modellist) {
				ModelVO mv = new ModelVO();
				mv.setUuid(m.getUuid());
				if(m.getVersion()!=null){
					mv.setName(m.getName()+m.getVersion());
				}else{
					mv.setName(m.getName());
				}
				list.add(mv);
			}
		}
		msgMap.put("list", list);
		return msgMap;
	}
	/**
	 * @title 客户分配模型功能
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "entScore", privilegeValue = "process")
	@RequestMapping("/savePerModel")
	@ResponseBody
	public Map<String, Object> savePerModel(String uuid,String modelID,String secModelID) throws Exception {
		message.setLength(0);
		Model model = modelService.find(secModelID);
		PerBaseInfo info = perBaseInfoService.find(uuid);
		PerResult result= perResultService.selectByPerID(uuid);
		if (model == null) {
			message.append("请选择类型");
			flag = false;
		}
		if(result!=null&&result.getInputXMLUrl()!=null){
			message.append("该客户已分配过模型，勿重新分配");
			flag = false;
		}
		if (flag) {
			if(result!=null){
				result.setModel(model);
				if(info!=null){
					result.setPerBaseInfo(info);
					result.setCustomer(info.getCustomer());
				}
				perResultService.update(result);
			}else{
				PerResult perResult=new PerResult();
				perResult.setUuid(UUID.randomUUID().toString().replace("-", ""));
				perResult.setModel(model);
				perResult.setPerBaseInfo(info);
				if(info!=null){
					perResult.setCustomer(info.getCustomer());
				}
				perResultService.save(perResult);
			}
			message.append("操作成功");
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", message.toString());
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", message.toString());
		}
		return msgMap;
	}
	
	/**
	 * @title 展示调查页面和编辑页面
	 * @author  孙尚飞  2017-8-1
	 * @desc
	 */
	//@Permission(model = "entScore", privilegeValue = "process")
	@RequestMapping("/surveryScoreView")
	@ResponseBody
	public Map<String, Object> surveryScoreView(HttpServletRequest request,String resultID) {
		logger.info(moduleName + "[展示调查页面和编辑页面]");
		List<Index> indexs=new ArrayList<Index>();
		//String dir=request.getSession().getServletContext().getRealPath("");
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		flag=true;
		message.setLength(0);
		PerResult result=perResultService.find(resultID);
		String path=new String();
		if(result==null){
			flag=false;
			message.append("此人数据不存在");
		}else{
			if(result.getScoreXMLUrl()==null){
				if(result.getInputXMLUrl()==null){
					path=adr+result.getModel().getXMLurl();
				}else{
					path=adr+result.getInputXMLUrl();
				}
			}else{
				path=adr+result.getScoreXMLUrl();
			}
			File file=new File(path);
			if(!file.exists()){
				message.append("个人评分的XML文件不存在");
				path=adr+result.getModel().getXMLurl();
			}
		}
		logger.info("XML路径"+path);
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
						index.add(index3);
					}
				}
				map.put(firstname, index);
			}
			String opinion=new String();
			Set<PerOpinion> opinions=result.getPerBaseInfo().getOpinion();
			for(PerOpinion o : opinions){
				if(o.getIsAdmin()==0&&o.getIsConfirm()==0){
					opinion=o.getOpinion();
				}
			}
			msgMap.put("Name", result.getPerBaseInfo().getName());
			msgMap.put("perID", result.getPerBaseInfo().getUuid());
			msgMap.put("opinion", opinion);
			msgMap.put("indexs", map);
		}
			msgMap.put("msg", message.toString());
		
		return msgMap;
	}
	/**
	 * @title 保存调查页面和编辑页面
	 * @author  孙尚飞  2017-8-1
	 * @throws Exception 
	 * @desc
	 */
	//@Permission(model = "entScore", privilegeValue = "process")
	@RequestMapping("/saveSurveryScore")
	@ResponseBody
	public Map<String, Object> saveSurveryScore(String resultID,IndexMap IndexMap,HttpServletRequest request) throws Exception {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		flag=true;
		message.setLength(0);
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		//String dir=request.getSession().getServletContext().getRealPath("");
		PerResult result=perResultService.find(resultID);
		String xmlpath="";
		if(result==null){
			flag=false;
			message.append("此人信息不存在");
		}else{
			if(result.getScoreXMLUrl()!=null){
				File file=new File(adr+result.getScoreXMLUrl());
				if(file.exists()){
					file.delete();
				}
			}
			if(result.getModel()==null){
				flag=false;
				message.append("此人没有分配模型");
			}else{
				xmlpath=adr+result.getModel().getXMLurl();
				File file =new File(xmlpath); 
				if(!file.exists()){
					flag=false;
					message.append("此人调查的XML文件不存在");
				}
			}
		}
		if(flag){
			Map<String, Option> map=IndexMap.getIndexMap();
			Set<String> keySet = map.keySet();
			Iterator<String> iterator = keySet.iterator();
			String key = "";
			try {
				File file=new File(xmlpath);
//				Document document = new SAXReader().read(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/config/model.xml"));
				Document document = new SAXReader().read(file);
				List<Index> indexs=ModelUtil.getIndex(document);
				while (iterator.hasNext()) {
					key = iterator.next();// key
					Option op= (Option)map.get(key);
					for(Index firstindex : indexs){
						for(Index secondindex : firstindex.getChirds()){
							for(Index thirdindex : secondindex.getChirds()){
								if(thirdindex.getUuid().equals(op.getName())){
									for(Option option : thirdindex.getOptions()){
										if(option.getValue().equals(op.getValue())){
											option.setSelected("true");
										}
									}
								}
							}
						}	
					}
				}
				String scoreXmlName=resultID+".xml";
				String XMLURL=BusinessUtil.getMsg("scoreXMLUrl");
				String scoreXmlUrl=adr+XMLURL;
				File saveFile=new File(scoreXmlUrl);
				//如果文件夹不存在则创建    
				if (!saveFile.exists()&&!saveFile.isDirectory()){
					saveFile.mkdirs();
				}   
				ModelUtil.saveXML(indexs, scoreXmlUrl+scoreXmlName);
				result.setScoreXMLUrl(XMLURL+scoreXmlName);
				result.setScoreXMLTime(new Date());
				result.getPerBaseInfo().getProcess().setScoreState(2);
				perResultService.update(result);
				//跨服务器传递文件
				if(BusinessUtil.getMsg("CrossUpload").equals("1")){
					if(result.getCustomer()!=null){
						if(!("".equals(result.getCustomer().getDomainName())||result.getCustomer().getDomainName()==null)){
							String url=request.getScheme()+"://"+result.getCustomer().getDomainName();
							WebUtil.crossFile(XMLURL+scoreXmlName, scoreXmlUrl+scoreXmlName, url);
						}
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			msgMap.put("success", true);
			msgMap.put("msg", "数据保存成功");
		}else{
			msgMap.put("success", true);
			msgMap.put("msg", message.toString());
		}
		
		return msgMap;
	}
	/**
	 * @title 结束调查
	 * @author  孙尚飞  2017-8-16
	 * @desc
	 */
	//@Permission(model = "entScore", privilegeValue = "process")
	@RequestMapping("/endSurvery")
	@ResponseBody
	public Map<String, Object> endSurvery(String resultID,HttpServletRequest request) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		//String dir=request.getSession().getServletContext().getRealPath("");
		message.setLength(0);
		PerResult result=perResultService.find(resultID);
		if(result==null){
			flag=false;
			message.append("此人信息不存在");
		}
		if(result.getScoreXMLUrl()==null||result.getScoreXMLUrl().equals("")){
			flag=false;
			message.append("此人调查结果不存在");
		}else{
			File file =new File(adr+result.getScoreXMLUrl()); 
			if(!file.exists()){
				flag=false;
				message.append("此人调查的XML文件不存在");
			}
		}
		if(flag){
			try {
				double score=0;
				File file=new File(adr+result.getScoreXMLUrl());
				Document document = new SAXReader().read(file);
				List<Index> indexs=ModelUtil.getIndex(document);
				for(Index firstindex : indexs){
					for(Index secondindex : firstindex.getChirds()){
						for(Index thirdindex : secondindex.getChirds()){
								for(Option option : thirdindex.getOptions()){
									if(option.getSelected()!=null&&option.getSelected().equals("true")){
										double value=Double.parseDouble(option.getValue());
										double weight=Double.parseDouble(thirdindex.getWeight());
										double temp=value*weight;
										score+=temp;
									}
								}
						}
					}	
				}
				String level=WebUtil.getPerLevelByScore(score);
				result.setPreliminaryScore(score);
				result.setPreliminaryLevel(level);
				result.setFinalScore(score);
				result.setFinalLevel(level);
				result.setEncoding(CODEUtil.getCODE((int) perResultService.countByEncoding()));
				result.getPerBaseInfo().getProcess().setScoreState(3);
				perResultService.update(result);
				msgMap.put("msg", "数据保存成功");
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			msgMap.put("msg", message.toString());
		}
		return msgMap;
	}
    /**
     * @title 个人评分页面展示
     * @author  孙尚飞  2017-8-10
     * @desc
     */
	//@Permission(model = "entScore", privilegeValue = "process")
	@RequestMapping("/personOfScore")
	@ResponseBody
	public Map<String, Object> personOfScore(String resultID,HttpServletRequest request) {
		logger.info(moduleName + "[进行个人评分]");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		//String dir=request.getSession().getServletContext().getRealPath("");
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		List<Index> indexs=new ArrayList<Index>();
		flag=true;
		message.setLength(0);
		PerResult result=perResultService.find(resultID);
		String path=new String();
		if(result==null){
			flag=false;
			message.append("此人评分信息不存在");
		}else{
			if(result.getScoreXMLUrl()==null||result.getScoreXMLUrl().equals("")){
				flag=false;
				message.append("此人评分项不存在");
			}else{
				File file=new File(adr+result.getScoreXMLUrl());
				if(!file.exists()){
					flag=false;
					message.append("此人评分的XML文件不存在");
				}else{
					path=adr+result.getScoreXMLUrl();
				}
			}
			if(result.getPerBaseInfo()==null){
				flag=false;
				message.append("此人信息不存在");
			}else{
				msgMap.put("resultid",result.getUuid());
				msgMap.put("uuid",result.getPerBaseInfo().getUuid());
				msgMap.put("name", result.getPerBaseInfo().getName());
				msgMap.put("IDCard", result.getPerBaseInfo().getIDCard());
				msgMap.put("encoding", result.getEncoding());
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
			List<IndexVO> indexvos=new ArrayList<IndexVO>();
			for(Index index1 : indexs){
				for(Index index2 : index1.getChirds()){
					for(Index index3 : index2.getChirds()){
						IndexVO indexvo=new IndexVO();
						indexvo.setUuid(index3.getUuid());
						indexvo.setIndexName(index3.getName());
						if(index3.getWeight()!=null){
							indexvo.setWeight(index3.getWeight());
							for(Option option : index3.getOptions()){
								if(option.getSelected()!=null&&option.getSelected().equals("true")){
									indexvo.setOptionName(option.getName());
									indexvo.setOptionScore(Double.parseDouble(option.getValue()));
									double indexscore=Double.parseDouble(option.getValue())*Double.parseDouble(index3.getWeight());
									indexvo.setIndexScore(indexscore);
								}
							}
							indexvos.add(indexvo);
						}
					}
				}

			}
			msgMap.put("IndexVO", indexvos);
			
			// 调整项
			List<CheckBox> items = new ArrayList<CheckBox>();
			String adjustscore=BusinessUtil.getMsg("perAdjustScores");
			String[] strs=adjustscore.split("#");
			String[] enterpriseOfScoreId = null;
			if (result.getAdjustOption() != null&& !"".equals(result.getAdjustOption().trim())) {
				enterpriseOfScoreId = result.getAdjustOption().split(",");
			}
			for (int i = 0; i < strs.length; i++) {
				String[] adjust=strs[i].split("=");
				CheckBox cb = new CheckBox();
				cb.setBoxLabel(adjust[0] + "("+ adjust[1]+ ")");
				cb.setInputValue(adjust[0]);
				cb.setName("adjustReason");
				cb.setValue(adjust[1]);
				String scoreId = adjust[0];
				if (enterpriseOfScoreId != null) {
					for (int j = 0; j < enterpriseOfScoreId.length; j++) {
						if (scoreId.trim().equals(enterpriseOfScoreId[j].trim())) {
							cb.setChecked(true);
						}
					}
				}
				items.add(cb);
			}
			Set<PerOpinion> opinions=result.getPerBaseInfo().getOpinion();
			String adminOpinion="";
			String customerOpinion="";
			if(!opinions.isEmpty()){
				for(PerOpinion op : opinions){
		        	if(op.getIsAdmin()==1){
		        		adminOpinion=op.getOpinion();
		        	}else{
		        		customerOpinion=op.getOpinion();
		        	}
		        }
			}
			msgMap.put("items", items);
			msgMap.put("adminOpinion", adminOpinion);
			msgMap.put("customerOpinion", customerOpinion);
			msgMap.put("preliminaryScore", result.getPreliminaryScore());
			msgMap.put("preliminaryLevel", result.getPreliminaryLevel());
			msgMap.put("finalScore", result.getFinalScore());
			msgMap.put("finalLevel", result.getFinalLevel());
			msgMap.put("success", true);
			msgMap.put("status", true);
		}else{

			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", message.toString());
		}
		return msgMap;
	}
	/**
	 * @title 保存个人评分
	 * @author  孙尚飞  2017-8-16
	 * @desc
	 */
	//@Permission(model = "entScore", privilegeValue = "process")
	@RequestMapping("/savePersonOfScore")
	@ResponseBody
	public Map<String, Object> savePersonOfScore(HttpServletRequest request,String[] adjustReason ) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		String resultID=request.getParameter("resultID");
		String preliminaryScore=request.getParameter("preliminaryScore");
		String preliminaryLevel=request.getParameter("preliminaryLevel");
		String finalScore=request.getParameter("finalScore");
		String finalLevel=request.getParameter("finalLevel");
		String encoding=request.getParameter("encoding");
		String adjust="";
		if(adjustReason!=null&&adjustReason.length>0){
			for(String str : adjustReason){
				adjust+=str+",";
			}
			adjust=adjust.substring(0, adjust.length()-1);
		}
		PerResult result=perResultService.find(resultID);
		if(result!=null){
			result.setAdjustOption(adjust);
			result.setPreliminaryScore(Double.parseDouble(preliminaryScore));
			result.setPreliminaryLevel(preliminaryLevel);
			result.setFinalScore(Double.parseDouble(finalScore));
			result.setFinalLevel(finalLevel);
			result.setEncoding(encoding);
			perResultService.update(result);

			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "数据保存成功");
		}else{
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "数据保存失败");
		}
		return msgMap;
	}
	/**
	 * @title 确认评分
	 * @author  孙尚飞  2017-8-16
	 * @desc
	 */
	//@Permission(model = "entScore", privilegeValue = "process")
	@RequestMapping("/endScore")
	@ResponseBody
	public Map<String, Object> endScore(String resultID) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		PerResult result=perResultService.find(resultID);
		message.setLength(0);
		flag=true;
		if(result==null){
			flag=false;
			message.append("此人信息不存在");
		}else{
			if(result.getFinalScore()==0||result.getFinalLevel()==null){
				flag=false;
				message.append("此人评分结果为空");
			}
		}
		if(flag){
			message.append("评分更新成功");
			result.getPerBaseInfo().getProcess().setScoreState(5);
			perResultService.update(result);
		}
		msgMap.put("success", true);
		msgMap.put("status", true);
		msgMap.put("msg", message.toString());
		logger.info(moduleName + "[确认评分]");
		return msgMap;
	}
	/**
	 * @title 管理评分报告页面
	 * @author  孙尚飞  2017-8-21
	 * @desc
	 */
	//@Permission(model = "entScore", privilegeValue = "process")
	@RequestMapping("/manageReportView")
	@ResponseBody
	public Map<String, Object> manageReportView(String resultID) throws Exception {
		logger.info(moduleName + "[管理评分报告页面]");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		PerResult result=perResultService.find(resultID);
		List<PerResultVO> perResultVO = new ArrayList<PerResultVO>();
		if (result != null) {
			List<String> list = perResultService.getAdjustOption(result);
			PerResultVO oneEntity = new PerResultVO();
			oneEntity.setName("调整说明");
			oneEntity.setStatus(list.get(0));
			oneEntity.setUuid(result.getUuid());
			oneEntity.setAdjustReason(list.get(1));
			oneEntity.setType("0");
			PerResultVO twoEntity = new PerResultVO();
			twoEntity.setName("优势说明");
			twoEntity.setStatus((result.getAdvantageReason() == null || "".equals(result.getAdvantageReason().trim())) ? "0" : "1");
			twoEntity.setUuid(result.getUuid());
			twoEntity.setAdvantageReason(result.getAdvantageReason());
			twoEntity.setType("1");
			PerResultVO threeEntity = new PerResultVO();
			threeEntity.setName("评分总结");
			threeEntity.setStatus((result.getScoreSummary()) == null || "".equals(result.getScoreSummary().trim()) ? "0" : "1");
			threeEntity.setUuid(result.getUuid());
			threeEntity.setScoreSummary(result.getScoreSummary());
			threeEntity.setType("2");
			/*PerResultVO fourEntity = new PerResultVO();
			fourEntity.setName("舆情信息");
			fourEntity.setStatus((result.getSentiment()== null || "".equals(result.getSentiment().trim())) ? "0" : "1");
			fourEntity.setUuid(result.getUuid());
			fourEntity.setSentiment(result.getSentiment());
			fourEntity.setType("3");*/
			perResultVO.add(oneEntity);
			perResultVO.add(twoEntity);
			perResultVO.add(threeEntity);
			//entResultVO.add(fourEntity);
		}
		msgMap.put("perResultVO", perResultVO);
		msgMap.put("success", true);
		msgMap.put("status", true);

		return msgMap;
	}
	/**
	 * @title 评分报告上的调整项说明及优势项说明
	 * @author  孙尚飞  2017-8-22
	 * @desc
	 */
	//@Permission(model = "entScore", privilegeValue = "process")
	@RequestMapping("/addOrUpdateFillReportOption")
	@ResponseBody
	public Map<String, Object> addOrUpdateFillReportOption(HttpServletRequest request) {
		Map<String, Object> msgMap = new HashMap<String, Object>();
		String resultID=request.getParameter("resultID");
		String adjustReason=request.getParameter("adjustReason");
		String advantageReason=request.getParameter("advantageReason");
		String scoreSummary=request.getParameter("scoreSummary");
		String sentiment=request.getParameter("sentiment");
		PerResult result = perResultService.find(resultID);
		if (adjustReason != null && !"".equals(adjustReason.trim())) {
			if (result != null) {
				result.setAdjustReason(adjustReason);
				perResultService.update(result);
			}
		} else if (advantageReason != null&& !"".equals(advantageReason.trim())) {
			if (result != null) {
				result.setAdvantageReason(advantageReason);
				perResultService.update(result);
			}
		} else if (scoreSummary != null && !"".equals(scoreSummary.trim())) {
			if (result != null) {
				result.setScoreSummary(scoreSummary);
				perResultService.update(result);
			}
		}else if (sentiment != null && !"".equals(sentiment.trim())) {
			if (result != null) {
				result.setSentiment(sentiment);
				perResultService.update(result);
			}
		}
		msgMap.put("success", true);
		msgMap.put("status", true);
		msgMap.put("msg", "数据保存成功");
		return msgMap;
	}
	/**
	 * @title 判断评分报告是否存在
	 * @author  孙尚飞  2017-8-22
	 * @desc
	 */
	//@Permission(model = "entScore", privilegeValue = "process")
	@RequestMapping("/judgeFileExist")
	@ResponseBody
	public Map<String, Object> judgeFileExist(HttpServletRequest request,String resultID) {
		logger.info(moduleName + "[判断评分报告是否存在]");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		boolean judgeReportExist=true;
		PerResult result=perResultService.find(resultID);
		if(result==null){
			judgeReportExist=false;
		}else{
			if(result.getReportUrl()==null){
				judgeReportExist=false;
			}else{
				String path=adr+result.getReportUrl();
				File file = new File(path);
				if (file.exists()) {
					judgeReportExist=true;
				}else{
					judgeReportExist=false;
				}
			}
		}
		msgMap.put("judgeReportExist", judgeReportExist);
		msgMap.put("success", true);
		msgMap.put("status", true);
		return msgMap;
	}
	/**
	 * @title 管理员确认信息时下载附件
	 * @author  孙尚飞  2017-8-10
	 * @desc
	 */
	@Permission(model = "entScore", privilegeValue = "process")
	@RequestMapping("/downLoadUploadFile")
	@ResponseBody
	public String downLoadUploadFile(HttpServletRequest request,HttpServletResponse response) {
		String fileName = (String) request.getParameter("fileName");
		String fileUrl = (String) request.getParameter("fileUrl");
		fileUrl=BusinessUtil.getMsg("adr")+fileUrl;
		String str=fileUrl.substring(fileUrl.lastIndexOf(".") + 1);
		fileName=fileName+"."+str;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream fos = null;
		InputStream fis = null;
		// 进行下载
		try {
			fis = new FileInputStream(fileUrl);
			bis = new BufferedInputStream(fis);
			fos = response.getOutputStream();
			bos = new BufferedOutputStream(fos);
			// 这个就就是弹出下载对话框的关键代码
			fileName = URLEncoder.encode(fileName, "utf-8");
			fileName = URLEncoder.encode(fileName, "utf-8");
			fileName = URLDecoder.decode(fileName, "utf-8");
			fileName = URLDecoder.decode(fileName, "utf-8");
			/* name = new String(name.getBytes("ISO8859-1"), "utf-8"); */
			fileName = new String(fileName.getBytes("gbk"), "ISO8859-1");
			response.setHeader("cache-control", "public");
			response.setHeader("Pragma", "public");
			response.setHeader("Content-disposition", "attachment;filename="
					+ fileName);
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
	 * @title 保存管理员审核意见
	 * @author  孙尚飞  2017-8-14
	 * @desc
	 */
	//@Permission(model = "entScore", privilegeValue = "process")
	@RequestMapping("/adminOpinionUpdateOrAdd")
	@ResponseBody
	public Map<String, Object> adminOpinionUpdateOrAdd(HttpServletRequest request) {
		String perID=request.getParameter("perID");
		String dealContent=request.getParameter("dealContent");
		String dealStatus=request.getParameter("dealStatus");
		String baseInfoState=request.getParameter("baseInfoState");
		String educationState=request.getParameter("educationState");
		String trainState=request.getParameter("trainState");
		String careerState=request.getParameter("careerState");
		String skillState=request.getParameter("skillState");
		String uploadFileState=request.getParameter("uploadFileState");
		User userSession = WebUtil.getUser(request);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		Map<String, String> searchParams = new HashMap<String, String>();
		if (baseInfoState != null && !"".equals(baseInfoState.trim())) {
			searchParams.put("baseInfoState", baseInfoState);
		}
		if (educationState != null && !"".equals(educationState.trim())) {
			searchParams.put("educationState", educationState);
		}
		if (trainState != null && !"".equals(trainState.trim())) {
			searchParams.put("trainState", trainState);
		}
		if (uploadFileState != null && !"".equals(uploadFileState.trim())) {
			searchParams.put("uploadFileState", uploadFileState);
		}
		if (careerState != null && !"".equals(careerState.trim())) {
			searchParams.put("careerState", careerState);
		}
		if (skillState != null && !"".equals(skillState.trim())) {
			searchParams.put("skillState", skillState);
		}
		PerBaseInfo perBaseInfo=perBaseInfoService.find(perID);
		if(perBaseInfo!=null){
			PerOpinion opinion = new PerOpinion();
			opinion.setUuid(UUID.randomUUID().toString().replace("-", ""));
			opinion.setIsAdmin(1);
			opinion.setDealer(userSession.getUserName());
			opinion.setOpinion(dealContent);
			opinion.setPerBaseInfo(perBaseInfo);
			opinion.setUpdateTime(new Date());
			opinion.setIsConfirm(Integer.parseInt(dealStatus));
			try {
				perOpinionService.UpdateOrAdd(opinion, dealStatus,
						perID, searchParams);
				msgMap.put("success", true);
				msgMap.put("status", true);
				logger.info(moduleName + "[处理信息更新成功]");
			} catch (Exception e) {
				logger.info(moduleName + "[处理信息更新失败]");
				e.printStackTrace();
			}
			msgMap.put("status", true);
		}else{
			msgMap.put("status", false);
		}
		
		return msgMap;
	}
	
	/**
	 * @title 查看历史存档信息
	 * @author  孙尚飞  2017-10-11
	 * @desc
	 */
	//@Permission(model = "entScore", privilegeValue = "process")
	@RequestMapping("/lookHistoryView")
	@ResponseBody
	public Map<String, Object> lookHistoryView(HttpServletRequest request) {
		String historyID=request.getParameter("historyID");
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		message.setLength(0);
		flag=true;
		Map<String, Object> msgMap = new HashMap<String, Object>();
		PerHistory history=perHistoryService.find(historyID);
		if(history==null){
			flag=false;
			message.append("没有该次评分的历史信息");
		}else{
			if(history.getHistoricalXMLUrl()==null){
				flag=false;
				message.append("该次评分的历史信息不存在");
			}else{
				File file=new File(adr+history.getHistoricalXMLUrl());
				if(!file.exists()){
					flag=false;
					message.append("该次评分的历史信息文件不存在");
				}
			}
		}
		if(flag){
			msgMap=HistoricalDataUtil.getPerHistory(msgMap,adr+history.getHistoricalXMLUrl());
			File file=new File(adr+history.getSnapshotUrl());
			if(file.exists()){
				msgMap.put("historyExist", adr+history.getSnapshotUrl());
			}else{
				msgMap.put("historyExist", "false");
			}
		}
		msgMap.put("msg", message.toString());
		return msgMap;
	}
	

	/**
	 * @title 下载历史数据
	 * @author  孙尚飞  2017-8-22
	 * @desc
	 */
	//@Permission(model = "resetEntScore", privilegeValue = "history")
	@RequestMapping("/downloadHistory")
	@ResponseBody
	public String downloadHistory(String path,HttpServletResponse response) {
		SimpleDateFormat formate = new SimpleDateFormat("yyyyMMddHHmmss");
		String name=(formate.format(new Date()) + ".pdf").trim();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream fos = null;
		InputStream fis = null;
		// 进行下载
		try {
			fis = new FileInputStream(path);
			bis = new BufferedInputStream(fis);
			fos = response.getOutputStream();
			bos = new BufferedOutputStream(fos);
			// 这个就就是弹出下载对话框的关键代码
			name = URLEncoder.encode(name, "utf-8");
			name = URLEncoder.encode(name, "utf-8");
			name = URLDecoder.decode(name, "utf-8");
			name = URLDecoder.decode(name, "utf-8");
			/* name = new String(name.getBytes("ISO8859-1"), "utf-8"); */
			name = new String(name.getBytes("gbk"), "ISO8859-1");
			response.setHeader("cache-control", "public");
			response.setHeader("Pragma", "public");
			response.setHeader("Content-disposition",
					"attachment;filename=" + name);
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
			// e.printStackTrace();
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
	 * @title 查看附件
	 * @author  孙尚飞  2017-8-22
	 * @desc
	 */
	//@Permission(model = "entScore", privilegeValue = "process")
	@RequestMapping("/lookUploadFile")
	@ResponseBody
	public Map<String, Object> lookUploadFile(HttpServletRequest request,String fileUrl) {
		System.out.println("图片相对路径："+fileUrl);
		Map<String, Object> msgMap = new HashMap<String, Object>();
		String dir=request.getSession().getServletContext().getRealPath("");
		String adr=BusinessUtil.getMsg("adr");
		File file=new File(dir+fileUrl);
		if(file.exists()){
			FileUtil.deleteFile(dir+fileUrl);
		}else{
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
		};
		FileUtil.copyFile(adr+fileUrl, dir+fileUrl);
		msgMap.put("success", true);
		return msgMap;
	}
}
