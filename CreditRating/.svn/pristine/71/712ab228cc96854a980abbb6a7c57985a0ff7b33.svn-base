package com.credit.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.credit.bean.addition.Model;
import com.credit.bean.enterprise.EntBaseInfo;
import com.credit.bean.enterprise.EntResult;
import com.credit.bean.enterprise.Executives;
import com.credit.bean.enterprise.HistoricalData;
import com.credit.bean.enterprise.Shareholder;
import com.credit.bean.person.Career;
import com.credit.bean.person.Education;
import com.credit.bean.person.PerBaseInfo;
import com.credit.bean.person.PerResult;
import com.credit.bean.person.Skills;
import com.credit.bean.person.Train;
import com.credit.bean.vo.enterprise.EntScoreVO;
import com.credit.bean.vo.html2pdf.History;
import com.credit.bean.vo.html2pdf.PerHistory;
import com.credit.bean.vo.person.PerScoreVO;
import com.credit.util.html2pdf.Html2PDFUtil;
import com.credit.util.model.Index;
import com.credit.util.model.IndexVO;
import com.credit.util.model.ModelUtil;
import com.credit.util.model.Option;
import com.credit.util.properties.BusinessUtil;



public class HistoricalDataUtil {
	/**
	 * @title 保存企业历史数据为XML
	 * @author  孙尚飞  2017-8-23
	 * @param map 
	 * @desc
	 */
	public static void saveHistoricalDataXML(EntBaseInfo ent,EntResult result,List<Shareholder> shareholders,List<Executives> executives,String path, Map<String, String> map) throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try{
			Document document = DocumentHelper.createDocument();
			Element Enterprise = document.addElement("Enterprise");
			//企业基础信息
			System.out.println("+++++++++++++++++++企业基本信息导出开始++++++++++++++++++++");
			Element entBaseInfo = Enterprise.addElement("EntBaseInfo");
			Element uuid = entBaseInfo.addElement("uuid");  
			if(ent.getUuid()!=null){
				uuid.addText(ent.getUuid());
			}
			Element createTime = entBaseInfo.addElement("createTime");
			if(ent.getCreateTime()!=null){
				createTime.addText(sdf.format(ent.getCreateTime())); 
			}else{
				createTime.addText("");
			}
			Element updateTime = entBaseInfo.addElement("updateTime");
			if(ent.getUpdateTime()!=null){
				updateTime.addText(sdf.format(ent.getUpdateTime())); 
			}else{
				updateTime.addText("");
			}
			Element name = entBaseInfo.addElement("name");  
			if(ent.getName()!=null){
				name.addText(ent.getName()); 
			}
			Element ename = entBaseInfo.addElement("ename"); 
			if(ent.getEname()!=null){
				ename.addText(ent.getEname()); 
			}
			Element USCC = entBaseInfo.addElement("USCC");  
			if(ent.getUSCC()!=null){
				USCC.addText(ent.getUSCC()); 
			}
			Element setupDate = entBaseInfo.addElement("setupDate");  
			if(ent.getSetupDate()!=null){
				setupDate.addText(sdf.format(ent.getSetupDate())); 
			}else{
				setupDate.addText("");
			}
			Element issueDate = entBaseInfo.addElement("issueDate"); 
			if(ent.getIssueDate()!=null){
				issueDate.addText(sdf.format(ent.getIssueDate())); 
			}else{
				issueDate.addText("");
			}
			Element startDate = entBaseInfo.addElement("startDate"); 
			if(ent.getStartDate()!=null){
				startDate.addText(sdf.format(ent.getStartDate())); 
			}else{
				startDate.addText("");
			}
			Element endDate = entBaseInfo.addElement("endDate");  
			if(ent.getEndDate()!=null){
				endDate.addText(sdf.format(ent.getEndDate())); 
			}else{
				endDate.addText("");
			}
			Element address = entBaseInfo.addElement("address");  
			if(ent.getAddress()!=null){
				address.addText(ent.getAddress()); 
			}
			Element regiCapital = entBaseInfo.addElement("regiCapital");  
			if(ent.getRegiCapital()!=null){
				regiCapital.addText(ent.getRegiCapital()+ent.getCurrencyType()); 
			}
			Element legalPerson = entBaseInfo.addElement("legalPerson");  
			if(ent.getLegalPerson()!=null){
				legalPerson.addText(ent.getLegalPerson()); 
			}
			Element tel = entBaseInfo.addElement("tel");  
			if(ent.getTel()!=null){
				tel.addText(ent.getTel()); 
			}
			Element fax = entBaseInfo.addElement("fax");  
			if(ent.getFax()!=null){
				fax.addText(ent.getFax()); 
			}
			Element zipCode = entBaseInfo.addElement("zipCode");  
			if(ent.getZipCode()!=null){
				zipCode.addText(ent.getZipCode()); 
			}
			Element email = entBaseInfo.addElement("email");  
			if(ent.getEmail()!=null){
				email.addText(ent.getEmail()); 
			}
			Element website = entBaseInfo.addElement("website");  
			if(ent.getWebsite()!=null){
				website.addText(ent.getWebsite()); 
			}
			Element scale = entBaseInfo.addElement("scale");
			if(ent.getScale()!=null){
				scale.addText(ent.getScale()); 
			}
			Element entType = entBaseInfo.addElement("entType");  
			if(ent.getEntType()!=null){
				entType.addText(ent.getEntType()); 
			}
			Element regisOrg = entBaseInfo.addElement("regisOrg");  
			if(ent.getRegisOrg()!=null){
				regisOrg.addText(ent.getRegisOrg()); 
			}
			Element industry = entBaseInfo.addElement("industry");  
			if(ent.getIndustry()!=null){
				industry.addText(ent.getIndustry()+"("+ent.getIndustryCode()+")"); 
			}
			Element areaCode = entBaseInfo.addElement("areaCode");  
			if(ent.getAreaCode()!=null){
				areaCode.addText(ent.getAreaCode()); 
			}
			Element businessScope = entBaseInfo.addElement("businessScope");  
			if(ent.getBusinessScope()!=null){
				businessScope.addText(ent.getBusinessScope()); 
			}
			Element brief = entBaseInfo.addElement("brief");  
			if(ent.getBrief()!=null){
				brief.addText(ent.getBrief()); 
			}
			Element status = entBaseInfo.addElement("status");  
			if(ent.getStatus()==1){
				status.addText("开业"); 
			}else if(ent.getStatus()==2){
				status.addText("存续"); 
			}else if(ent.getStatus()==3){
				status.addText("内资存活"); 
			}else if(ent.getStatus()==4){
				status.addText("注销"); 
			}else{
				status.addText("正常营业"); 
			}
			System.out.println("+++++++++++++++++++企业基本信息导出结束++++++++++++++++++++");
			//企业评分信息
			System.out.println("+++++++++++++++++++企业评分信息导出开始++++++++++++++++++++");
			Element entResult = Enterprise.addElement("EntResult");
			Element resultId = entResult.addElement("uuid");  
			if(result.getUuid()!=null){
				resultId.addText(result.getUuid()); 
			}
			Element preliminaryScore = entResult.addElement("preliminaryScore");
			if(result.getPreliminaryScore()!=null){
				preliminaryScore.addText(String.valueOf(result.getPreliminaryScore())); 
			}else{
				preliminaryScore.addText(""); 
			}
			Element preliminaryLevel = entResult.addElement("preliminaryLevel");  
			if(result.getPreliminaryLevel()!=null){
				preliminaryLevel.addText(result.getPreliminaryLevel()); 
			}
			Element finalScore = entResult.addElement("finalScore");
			if(result.getFinalScore()!=null){
				finalScore.addText(String.valueOf(result.getFinalScore())); 
			}else{
				finalScore.addText("");
			}
			Element finalLevel = entResult.addElement("finalLevel");  
			if(result.getFinalLevel()!=null){
				finalLevel.addText(result.getFinalLevel()); 
			}
			Element adjustOption = entResult.addElement("adjustOption");  
			if(result.getAdjustOption()!=null){
				adjustOption.addText(result.getAdjustOption());
			}
			Element adjustReason = entResult.addElement("adjustReason");  
			if(result.getAdjustReason()!=null){
				adjustReason.addText(result.getAdjustReason()); 
			}
			Element advantageReason = entResult.addElement("advantageReason"); 
			if(result.getAdvantageReason()!=null){
				advantageReason.addText(result.getAdvantageReason()); 
			}
			Element scoreSummary = entResult.addElement("scoreSummary");  
			if(result.getScoreSummary()!=null){
				scoreSummary.addText(result.getScoreSummary()); 
			}
			Element sentiment = entResult.addElement("sentiment");  
			if(result.getSentiment()!=null){
				sentiment.addText(result.getSentiment()); 
			}
			Element gradeTime = entResult.addElement("gradeTime"); 
			if(result.getGradeTime()!=null){
				gradeTime.addText(sdf.format(result.getGradeTime()));
			}else{
				gradeTime.addText("");
			}
			Element encoding = entResult.addElement("encoding");  
			if(result.getEncoding()!=null){
				encoding.addText(result.getEncoding()); 
			}
			Element inputXMLTime = entResult.addElement("inputXMLTime"); 
			if(result.getInputXMLTime()!=null){
				inputXMLTime.addText(sdf.format(result.getInputXMLTime()));
			}else{
				inputXMLTime.addText("");
			} 
			Element scoreXMLTime = entResult.addElement("scoreXMLTime");  
			if(result.getScoreXMLTime()!=null){
				scoreXMLTime.addText(sdf.format(result.getScoreXMLTime()));
			}else{
				scoreXMLTime.addText("");
			} 
			Element inputXMLUrl = entResult.addElement("inputXMLUrl"); 
			if(map.get("input")!=null){
				inputXMLUrl.addText(map.get("input")); 
			}
			Element scoreXMLUrl = entResult.addElement("scoreXMLUrl");  
			if(map.get("score")!=null){
				scoreXMLUrl.addText(map.get("score")); 
			}
			Element reportUrl = entResult.addElement("reportUrl");  
			if(map.get("report")!=null){
				reportUrl.addText(map.get("report")); 
			}
			
			Element financeUrl = entResult.addElement("financeUrl");  
			if(map.get("finance")!=null){
				financeUrl.addText(map.get("finance")); 
			}
			
			Element uploadfileUrl = entResult.addElement("uploadfileUrl");  
			if(map.get("uploadfile")!=null){
				uploadfileUrl.addText(map.get("uploadfile")); 
			}
			
			System.out.println("+++++++++++++++++++企业评分信息导出结束++++++++++++++++++++");
			Model model =result.getModel();
			if(model!=null){
				//企业评分模型
				System.out.println("+++++++++++++++++++企业评分模型信息导出开始++++++++++++++++++++");
				Element models = Enterprise.addElement("Model");
				Element moId = models.addElement("uuid");  
				if(model.getUuid()!=null){
					moId.addText(model.getUuid()); 
				}
				Element version = models.addElement("version");  
				if(model.getVersion()!=null){
					version.addText(model.getVersion()); 
				}
				Element moname = models.addElement("name");  
				if(model.getName()!=null){
					moname.addText(model.getName()); 
				}
				Element XMLurl = models.addElement("XMLurl");  
				if(model.getXMLurl()!=null){
					XMLurl.addText(model.getXMLurl()); 
				}
				Element moupdateTime = models.addElement("updateTime");  
				if(model.getUpdateTime()!=null){
					moupdateTime.addText(sdf.format(model.getUpdateTime())); 
				}
				Element remark = models.addElement("remark");  
				if(model.getRemark()!=null){
					remark.addText(model.getRemark()); 
				}
				Element parentID = models.addElement("parentID");  
				if(model.getParent()!=null){
					parentID.addText(model.getParent().getUuid()); 
				}
				System.out.println("+++++++++++++++++++企业评分模型信息导出结束++++++++++++++++++++");
			}
			//企业股东信息
			System.out.println("+++++++++++++++++++企业股东信息导出开始++++++++++++++++++++");
			Element Shareholders = Enterprise.addElement("Shareholders");
			for(Shareholder shareholder : shareholders){
				Element share = Shareholders.addElement("shareholder");
				Element shareid = share.addElement("uuid"); 
				if(shareholder.getUuid()!=null){
					shareid.addText(shareholder.getUuid()); 
				}
				Element sharename = share.addElement("name");  
				if(shareholder.getName()!=null){
					sharename.addText(shareholder.getName()); 
				}
				Element stockpercent = share.addElement("stockpercent");  
				if(shareholder.getStockpercent()!=null){
					stockpercent.addText(shareholder.getStockpercent()); 
				}
				Element type = share.addElement("type");  
				if(shareholder.getType()!=null){
					type.addText(shareholder.getType()); 
				}
				Element realcapi = share.addElement("realcapi"); 
				if(shareholder.getRealcapi()!=null){
					realcapi.addText(shareholder.getRealcapi()); 
				}
				Element realTime = share.addElement("realTime");  
				if(shareholder.getRealTime()!=null){
					realTime.addText(sdf.format(shareholder.getRealTime()));
				}else{
					realTime.addText("");
				} 
				Element shouldcapi = share.addElement("shouldcapi");  
				if(shareholder.getShouldcapi()!=null){
					shouldcapi.addText(shareholder.getShouldcapi()); 
				}
				Element shouldTime = share.addElement("shouldTime");  
				if(shareholder.getShouldTime()!=null){
					shouldTime.addText(sdf.format(shareholder.getShouldTime()));
				}else{
					shouldTime.addText("");
				} 
			}
			System.out.println("+++++++++++++++++++企业股东信息导出结束++++++++++++++++++++");
			//企业高层信息
			System.out.println("+++++++++++++++++++企业高层信息导出开始++++++++++++++++++++");
			Element Executives = Enterprise.addElement("Executives");
			for(Executives exec : executives){
				Element execute = Executives.addElement("executives");
				Element execid = execute.addElement("uuid");  
				if(exec.getUuid()!=null){
					execid.addText(exec.getUuid()); 
				}
				Element execname= execute.addElement("name"); 
				if(exec.getName()!=null){
					execname.addText(exec.getName()); 
				}
				Element age = execute.addElement("age");  
				if(exec.getAge()!=null){
					age.addText(String.valueOf(exec.getAge())); 
				}else{
					age.addText(""); 
				}
				Element gender = execute.addElement("gender");
				if(exec.getGender()!=null){
					gender.addText(exec.getGender()); 
				}
				Element IDCard = execute.addElement("IDCard");  
				if(exec.getIDCard()!=null){
					IDCard.addText(exec.getIDCard()); 
				}
				Element job = execute.addElement("job");  
				if(exec.getJob()!=null){
					job.addText(exec.getJob()); 
				}
				Element department = execute.addElement("department");  
				if(exec.getDepartment()!=null){
					department.addText(exec.getDepartment()); 
				}
				Element education = execute.addElement("education");  
				if(exec.getEducation()!=null){
					education.addText(exec.getEducation());
				}
				Element workExp = execute.addElement("workExp");
				if(exec.getWorkExp()!=null){
					workExp.addText(exec.getWorkExp()); 
				}
			}
			System.out.println("+++++++++++++++++++企业高层信息导出结束++++++++++++++++++++");
			
			writeXml(document, path);
			System.out.println("保存XML成功");
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("保存XML失败!");
		}
	}
	 /**
     * @title 	输出xml文件
     * @author  孙尚飞  2017-8-3
     * @desc
     */
    public static void writeXml(Document document, String filePath) throws IOException {
        File xmlFile = new File(filePath);
        XMLWriter writer = null;
        try {
            if (xmlFile.exists())
                xmlFile.delete();
            writer = new XMLWriter(new FileOutputStream(xmlFile), OutputFormat.createPrettyPrint());
            writer.write(document);
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }
    
	public static EntScoreVO getEntScore(String path,EntScoreVO esv, HistoricalData history){
		System.out.println("=================开始读取XML==================");
		EntScoreVO entScoreVO=new EntScoreVO();
		entScoreVO.setUserName(esv.getUserName());
		entScoreVO.setCellphone(esv.getCellphone());
		entScoreVO.setEntID(history.getUuid());
		entScoreVO.setApplyReportState(1);
		entScoreVO.setScoreState(6);
		entScoreVO.setState("查看存档");
		try {
			File file=new File(path);
			Document document = new SAXReader().read(file);
			Element Enterprise = document.getRootElement();
			Element EntBaseInfo = Enterprise.element("EntBaseInfo");
			//Element entID = EntBaseInfo.element("uuid");
			Element entName=EntBaseInfo.element("name");
			Element legalPerson=EntBaseInfo.element("legalPerson");
			Element uscc=EntBaseInfo.element("USCC");
			Element updateTime=EntBaseInfo.element("updateTime");
			
			//entScoreVO.setEntID(entID.getText());
			entScoreVO.setEntName(entName.getText());
			entScoreVO.setLegalPerson(legalPerson.getText());
			entScoreVO.setUscc(uscc.getText());
			entScoreVO.setUpdateTime(updateTime.getText());
			
			Element Model = Enterprise.element("Model");
			Element modelName = Model.element("name");
			Element version = Model.element("version");
			
			entScoreVO.setModelName(modelName.getText()+version.getText());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		System.out.println("=================结束读取XML==================");
		return entScoreVO;
	}
	public static boolean saveHistoricalSnapshot(EntBaseInfo entBaseinfo,EntResult result, String path, String string) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dir=request.getSession().getServletContext().getRealPath("");
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		dir=dir+root;
		String Template=BusinessUtil.getMsg("templateUrl");
		String historyTemplate=BusinessUtil.getMsg("historySnapshot");
		History history=new History();
		if(entBaseinfo.getName()!=null)history.setEntName(entBaseinfo.getName());
		if(result.getEncoding()!=null)history.setEncoding(result.getEncoding());
		if(result.getGradeTime()!=null)history.setGradeTime(sdf.format(result.getGradeTime()));
		if(entBaseinfo.getUSCC()!=null)history.setUscc(entBaseinfo.getUSCC());
		if(entBaseinfo.getSetupDate()!=null)history.setSetupDate(sdf.format(entBaseinfo.getSetupDate()));
		if(entBaseinfo.getIssueDate()!=null)history.setIssueDate(sdf.format(entBaseinfo.getIssueDate()));
		if(entBaseinfo.getStartDate()!=null)history.setStartDate(sdf.format(entBaseinfo.getStartDate()));
		if(entBaseinfo.getEndDate()!=null)history.setEndDate(sdf.format(entBaseinfo.getEndDate()));
		if(entBaseinfo.getAddress()!=null)history.setAddress(entBaseinfo.getAddress());
		if(entBaseinfo.getRegiCapital()!=null)history.setRegiCapital(entBaseinfo.getRegiCapital()+entBaseinfo.getCurrencyType());
		if(entBaseinfo.getLegalPerson()!=null)history.setLegalPerson(entBaseinfo.getLegalPerson());
		if(entBaseinfo.getTel()!=null)history.setTel(entBaseinfo.getTel());
		if(entBaseinfo.getEmail()!=null)history.setEmail(entBaseinfo.getEmail());
		if(entBaseinfo.getWebsite()!=null)history.setWebsite(entBaseinfo.getWebsite());
		if(entBaseinfo.getScale()!=null)history.setScale(entBaseinfo.getScale());
		if(entBaseinfo.getEntType()!=null)history.setEntType(entBaseinfo.getEntType());
		if(entBaseinfo.getRegisOrg()!=null)history.setRegisOrg(entBaseinfo.getRegisOrg());
		if(entBaseinfo.getIndustry()!=null)history.setIndustry(entBaseinfo.getIndustry());
		if(entBaseinfo.getBusinessScope()!=null)history.setBusinessScope(entBaseinfo.getBusinessScope());
		if(entBaseinfo.getBrief()!=null)history.setBrief(entBaseinfo.getBrief());
		if(result.getModel()!=null)history.setModel(result.getModel().getName()+result.getModel().getVersion());
		if(result.getFinalScore()!=null)history.setFinalScore(String.valueOf(result.getFinalScore()));
		if(result.getFinalLevel()!=null)history.setFinalLevel(result.getFinalLevel());
		System.out.println(adr+string);
		File file=new File(adr+string);
		List<IndexVO> indexvos=new ArrayList<IndexVO>();
		if(file.exists()){
			List<Index> indexs=new ArrayList<Index>();
			try {
				Document document = new SAXReader().read(file);
				indexs=ModelUtil.getIndex(document);
			} catch (DocumentException e) {
				e.printStackTrace();
			}	
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
									indexvo.setOptionName(StringEscapeUtils.escapeHtml4(option.getName()));
									indexvo.setOptionScore(Double.parseDouble(option.getValue()));
									double indexscore=Double.parseDouble(option.getValue())*Double.parseDouble(index3.getWeight());
									indexvo.setIndexScore(indexscore);
								}
							}
							indexvos.add(indexvo);
							System.out.println(indexvo.toString());
						}
					}
				}
			}
		}else{
			IndexVO indexvo=new IndexVO();
			indexvos.add(indexvo);
		}
		history.setIndexs(indexvos);
		boolean flag=Html2PDFUtil.createPDF(history, path, dir+Template, historyTemplate);
		return flag;
	}
	public static Map<String, Object> getHistory(Map<String, Object> msgMap,String string) {
		System.out.println("=================开始读取XML==================");
		try {
			File file=new File(string);
			Document document = new SAXReader().read(file);
			Element Enterprise = document.getRootElement();
			Element EntBaseInfo = Enterprise.element("EntBaseInfo");
			Element entName=EntBaseInfo.element("name");
			Element uscc=EntBaseInfo.element("USCC");
			Element EntResult = Enterprise.element("EntResult");
			Element finalScore = EntResult.element("finalScore");
			Element finalLevel = EntResult.element("finalLevel");
			msgMap.put("name", entName.getText());
			msgMap.put("uscc", uscc.getText());
			msgMap.put("finalscore", finalScore.getText());
			msgMap.put("finallevel", finalLevel.getText());
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		System.out.println("=================结束读取XML==================");
		return msgMap;
	}
	//保存个人信息
	public static void savePerHistoricalDataXML(PerBaseInfo per,
			PerResult result, List<Career> careers, List<Education> educations,
			List<Skills> skillss, List<Train> trains, String path,
			Map<String, String> map) throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try{
			Document document = DocumentHelper.createDocument();
			Element Person = document.addElement("Person");
			//个人基础信息
			System.out.println("+++++++++++++++++++个人基本信息导出开始++++++++++++++++++++");
			Element PerBaseInfo = Person.addElement("PerBaseInfo");
			Element uuid = PerBaseInfo.addElement("uuid");  
			if(per.getUuid()!=null){
				uuid.addText(per.getUuid());
			}
			Element createTime = PerBaseInfo.addElement("createTime");
			if(per.getCreateTime()!=null){
				createTime.addText(sdf.format(per.getCreateTime())); 
			}else{
				createTime.addText("");
			}
			Element updateTime = PerBaseInfo.addElement("updateTime");
			if(per.getUpdateTime()!=null){
				updateTime.addText(sdf.format(per.getUpdateTime())); 
			}else{
				updateTime.addText("");
			}
			Element name = PerBaseInfo.addElement("name");  
			if(per.getName()!=null){
				name.addText(per.getName()); 
			}
			Element IDCard = PerBaseInfo.addElement("IDCard"); 
			if(per.getIDCard()!=null){
				IDCard.addText(per.getIDCard()); 
			}
			Element usedName = PerBaseInfo.addElement("usedName");  
			if(per.getUsedName()!=null){
				usedName.addText(per.getUsedName()); 
			}
			Element gender = PerBaseInfo.addElement("gender");  
			if(per.getGender()!=null){
				gender.addText(per.getGender()); 
			}
			Element nation = PerBaseInfo.addElement("nation");  
			if(per.getNation()!=null){
				nation.addText(per.getNation()); 
			}
			Element politicalOutlook = PerBaseInfo.addElement("politicalOutlook");  
			if(per.getPoliticalOutlook()!=null){
				politicalOutlook.addText(per.getPoliticalOutlook()); 
			}
			Element nationality = PerBaseInfo.addElement("nationality");  
			if(per.getNationality()!=null){
				nationality.addText(per.getNationality()); 
			}
			Element nativePlace = PerBaseInfo.addElement("nativePlace");  
			if(per.getNativePlace()!=null){
				nativePlace.addText(per.getNativePlace()); 
			}
			Element maritalStatus = PerBaseInfo.addElement("maritalStatus");  
			if(per.getMaritalStatus()!=null){
				maritalStatus.addText(per.getMaritalStatus()); 
			}
			Element fertilityCondition = PerBaseInfo.addElement("fertilityCondition");  
			if(per.getFertilityCondition()!=null){
				fertilityCondition.addText(per.getFertilityCondition()); 
			}
			Element permanentAddress = PerBaseInfo.addElement("permanentAddress");  
			if(per.getPermanentAddress()!=null){
				permanentAddress.addText(per.getPermanentAddress()); 
			}
			Element presentAddress = PerBaseInfo.addElement("presentAddress");
			if(per.getPresentAddress()!=null){
				presentAddress.addText(per.getPresentAddress()); 
			}
			Element IDIssuingAgency = PerBaseInfo.addElement("IDIssuingAgency");  
			if(per.getIDIssuingAgency()!=null){
				IDIssuingAgency.addText(per.getIDIssuingAgency()); 
			}
			Element presentZipCode = PerBaseInfo.addElement("presentZipCode");  
			if(per.getPresentZipCode()!=null){
				presentZipCode.addText(per.getPresentZipCode()); 
			}
			Element IDTermStart = PerBaseInfo.addElement("IDTermStart"); 
			if(per.getIDTermStart()!=null){
				IDTermStart.addText(sdf.format(per.getIDTermStart())); 
			}else{
				IDTermStart.addText("");
			}
			Element IDTermEnd = PerBaseInfo.addElement("IDTermEnd");  
			if(per.getIDTermEnd()!=null){
				IDTermEnd.addText(sdf.format(per.getIDTermEnd())); 
			}else{
				IDTermEnd.addText("");
			}
			System.out.println("+++++++++++++++++++个人基本信息导出结束++++++++++++++++++++");
			//企业评分信息
			System.out.println("+++++++++++++++++++个人评分信息导出开始++++++++++++++++++++");
			Element perResult = Person.addElement("PerResult");
			Element resultId = perResult.addElement("uuid");  
			if(result.getUuid()!=null){
				resultId.addText(result.getUuid()); 
			}
			Element preliminaryScore = perResult.addElement("preliminaryScore");
			if(result.getPreliminaryScore()!=null){
				preliminaryScore.addText(String.valueOf(result.getPreliminaryScore())); 
			}else{
				preliminaryScore.addText(""); 
			}
			Element preliminaryLevel = perResult.addElement("preliminaryLevel");  
			if(result.getPreliminaryLevel()!=null){
				preliminaryLevel.addText(result.getPreliminaryLevel()); 
			}
			Element finalScore = perResult.addElement("finalScore");
			if(result.getFinalScore()!=null){
				finalScore.addText(String.valueOf(result.getFinalScore())); 
			}else{
				finalScore.addText("");
			}
			Element finalLevel = perResult.addElement("finalLevel");  
			if(result.getFinalLevel()!=null){
				finalLevel.addText(result.getFinalLevel()); 
			}
			Element adjustOption = perResult.addElement("adjustOption");  
			if(result.getAdjustOption()!=null){
				adjustOption.addText(result.getAdjustOption());
			}
			Element adjustReason = perResult.addElement("adjustReason");  
			if(result.getAdjustReason()!=null){
				adjustReason.addText(result.getAdjustReason()); 
			}
			Element advantageReason = perResult.addElement("advantageReason"); 
			if(result.getAdvantageReason()!=null){
				advantageReason.addText(result.getAdvantageReason()); 
			}
			Element scoreSummary = perResult.addElement("scoreSummary");  
			if(result.getScoreSummary()!=null){
				scoreSummary.addText(result.getScoreSummary()); 
			}
			Element sentiment = perResult.addElement("sentiment");  
			if(result.getSentiment()!=null){
				sentiment.addText(result.getSentiment()); 
			}
			Element gradeTime = perResult.addElement("gradeTime"); 
			if(result.getGradeTime()!=null){
				gradeTime.addText(sdf.format(result.getGradeTime()));
			}else{
				gradeTime.addText("");
			}
			Element encoding = perResult.addElement("encoding");  
			if(result.getEncoding()!=null){
				encoding.addText(result.getEncoding()); 
			}
			Element inputXMLTime = perResult.addElement("inputXMLTime"); 
			if(result.getInputXMLTime()!=null){
				inputXMLTime.addText(sdf.format(result.getInputXMLTime()));
			}else{
				inputXMLTime.addText("");
			} 
			Element scoreXMLTime = perResult.addElement("scoreXMLTime");  
			if(result.getScoreXMLTime()!=null){
				scoreXMLTime.addText(sdf.format(result.getScoreXMLTime()));
			}else{
				scoreXMLTime.addText("");
			} 
			Element inputXMLUrl = perResult.addElement("inputXMLUrl"); 
			if(map.get("input")!=null){
				inputXMLUrl.addText(map.get("input")); 
			}
			Element scoreXMLUrl = perResult.addElement("scoreXMLUrl");  
			if(map.get("score")!=null){
				scoreXMLUrl.addText(map.get("score")); 
			}
			Element reportUrl = perResult.addElement("reportUrl");  
			if(map.get("report")!=null){
				reportUrl.addText(map.get("report")); 
			}
			
			Element financeUrl = perResult.addElement("financeUrl");  
			if(map.get("finance")!=null){
				financeUrl.addText(map.get("finance")); 
			}
			
			Element uploadfileUrl = perResult.addElement("uploadfileUrl");  
			if(map.get("uploadfile")!=null){
				uploadfileUrl.addText(map.get("uploadfile")); 
			}
			
			System.out.println("+++++++++++++++++++个人评分信息导出结束++++++++++++++++++++");
			Model model =result.getModel();
			if(model!=null){
				//企业评分模型
				System.out.println("+++++++++++++++++++个人评分模型信息导出开始++++++++++++++++++++");
				Element models = Person.addElement("Model");
				Element moId = models.addElement("uuid");  
				if(model.getUuid()!=null){
					moId.addText(model.getUuid()); 
				}
				Element version = models.addElement("version");  
				if(model.getVersion()!=null){
					version.addText(model.getVersion()); 
				}
				Element moname = models.addElement("name");  
				if(model.getName()!=null){
					moname.addText(model.getName()); 
				}
				Element XMLurl = models.addElement("XMLurl");  
				if(model.getXMLurl()!=null){
					XMLurl.addText(model.getXMLurl()); 
				}
				Element moupdateTime = models.addElement("updateTime");  
				if(model.getUpdateTime()!=null){
					moupdateTime.addText(sdf.format(model.getUpdateTime())); 
				}
				Element remark = models.addElement("remark");  
				if(model.getRemark()!=null){
					remark.addText(model.getRemark()); 
				}
				Element parentID = models.addElement("parentID");  
				if(model.getParent()!=null){
					parentID.addText(model.getParent().getUuid()); 
				}
				System.out.println("+++++++++++++++++++个人模型信息导出结束++++++++++++++++++++");
			}
			//职业生涯信息
			System.out.println("+++++++++++++++++++职业生涯信息导出开始++++++++++++++++++++");
			Element Careers = Person.addElement("Careers");
			for(Career percareer : careers){
				Element career = Careers.addElement("career");
				Element careerid = career.addElement("uuid"); 
				if(percareer.getUuid()!=null){
					careerid.addText(percareer.getUuid()); 
				}
				Element startTime = career.addElement("startTime");  
				if(percareer.getStartTime()!=null){
					startTime.addText(sdf.format(percareer.getStartTime()));
				}else{
					startTime.addText("");
				} 
				Element endTime = career.addElement("endTime");  
				if(percareer.getEndTime()!=null){
					endTime.addText(sdf.format(percareer.getEndTime()));
				}else{
					endTime.addText("");
				} 
				Element inauguralUnit = career.addElement("inauguralUnit"); 
				if(percareer.getInauguralUnit()!=null){
					inauguralUnit.addText(percareer.getInauguralUnit()); 
				}
				Element unitScale = career.addElement("unitScale"); 
				if(percareer.getUnitScale()!=null){
					unitScale.addText(percareer.getUnitScale()); 
				}
				Element industry = career.addElement("industry"); 
				if(percareer.getIndustry()!=null){
					industry.addText(percareer.getIndustry()); 
				}
				Element workingLife = career.addElement("workingLife"); 
				if(percareer.getWorkingLife()!=null){
					workingLife.addText(percareer.getWorkingLife()); 
				}
				Element post = career.addElement("post"); 
				if(percareer.getPost()!=null){
					post.addText(percareer.getPost()); 
				}
				Element averageSalary = career.addElement("averageSalary"); 
				if(percareer.getAverageSalary()!=null){
					averageSalary.addText(percareer.getAverageSalary()); 
				}
				Element remarks = career.addElement("remarks"); 
				if(percareer.getRemarks()!=null){
					remarks.addText(percareer.getRemarks()); 
				}
			}
			System.out.println("+++++++++++++++++++职业生涯信息导出结束++++++++++++++++++++");
			//教育信息
			System.out.println("+++++++++++++++++++教育信息导出开始++++++++++++++++++++");
			Element Educations = Person.addElement("Educations");
			for(Education pereducation : educations){
				Element education = Educations.addElement("education");
				Element eduid = education.addElement("uuid"); 
				if(pereducation.getUuid()!=null){
					eduid.addText(pereducation.getUuid()); 
				}
				Element startTime = education.addElement("startTime");  
				if(pereducation.getStartTime()!=null){
					startTime.addText(sdf.format(pereducation.getStartTime()));
				}else{
					startTime.addText("");
				} 
				Element endTime = education.addElement("endTime");  
				if(pereducation.getEndTime()!=null){
					endTime.addText(sdf.format(pereducation.getEndTime()));
				}else{
					endTime.addText("");
				} 
				Element edu = education.addElement("edu"); 
				if(pereducation.getEducation()!=null){
					edu.addText(pereducation.getEducation()); 
				}
				Element university = education.addElement("university"); 
				if(pereducation.getUniversity()!=null){
					university.addText(pereducation.getUniversity()); 
				}
				Element major = education.addElement("major"); 
				if(pereducation.getMajor()!=null){
					major.addText(pereducation.getMajor()); 
				}
				Element diplomaNo = education.addElement("diplomaNo"); 
				if(pereducation.getDiplomaNo()!=null){
					diplomaNo.addText(pereducation.getDiplomaNo()); 
				}
				Element remarks = education.addElement("remarks"); 
				if(pereducation.getRemarks()!=null){
					remarks.addText(pereducation.getRemarks()); 
				}
			}
			System.out.println("+++++++++++++++++++教育信息导出结束++++++++++++++++++++");
			//专业技能信息
			System.out.println("+++++++++++++++++++专业技能信息导出开始++++++++++++++++++++");
			Element Skillss = Person.addElement("Skillss");
			for(Skills perskills : skillss){
				Element skills = Skillss.addElement("skills");
				Element skillsid = skills.addElement("uuid"); 
				if(perskills.getUuid()!=null){
					skillsid.addText(perskills.getUuid()); 
				}
				Element skillName = skills.addElement("skillName"); 
				if(perskills.getSkillName()!=null){
					skillName.addText(perskills.getSkillName()); 
				}
				Element skillProficiency = skills.addElement("skillProficiency"); 
				if(perskills.getSkillProficiency()!=null){
					skillProficiency.addText(perskills.getSkillProficiency()); 
				}
				Element remarks = skills.addElement("remarks"); 
				if(perskills.getRemarks()!=null){
					remarks.addText(perskills.getRemarks()); 
				}
			}
			System.out.println("+++++++++++++++++++专业技能信息导出结束++++++++++++++++++++");
			//培训经历信息
			System.out.println("+++++++++++++++++++培训经历信息导出开始++++++++++++++++++++");
			Element Trains = Person.addElement("Trains");
			for(Train pertrain : trains){
				Element train = Trains.addElement("train");
				Element trainid = train.addElement("uuid"); 
				if(pertrain.getUuid()!=null){
					trainid.addText(pertrain.getUuid()); 
				}
				Element startTime = train.addElement("startTime");  
				if(pertrain.getStartTime()!=null){
					startTime.addText(sdf.format(pertrain.getStartTime()));
				}else{
					startTime.addText("");
				} 
				Element endTime = train.addElement("endTime");  
				if(pertrain.getEndTime()!=null){
					endTime.addText(sdf.format(pertrain.getEndTime()));
				}else{
					endTime.addText("");
				} 
				Element trainOrg = train.addElement("trainOrg"); 
				if(pertrain.getTrainOrg()!=null){
					trainOrg.addText(pertrain.getTrainOrg()); 
				}
				Element trainAddress = train.addElement("trainAddress"); 
				if(pertrain.getTrainAddress()!=null){
					trainAddress.addText(pertrain.getTrainAddress()); 
				}
				Element trainContent = train.addElement("trainContent"); 
				if(pertrain.getTrainContent()!=null){
					trainContent.addText(pertrain.getTrainContent()); 
				}
				Element certificateNo = train.addElement("certificateNo"); 
				if(pertrain.getCertificateNo()!=null){
					certificateNo.addText(pertrain.getCertificateNo()); 
				}
				Element remarks = train.addElement("remarks"); 
				if(pertrain.getRemarks()!=null){
					remarks.addText(pertrain.getRemarks()); 
				}
			}
			System.out.println("+++++++++++++++++++职业生涯信息导出结束++++++++++++++++++++");
			writeXml(document, path);
			System.out.println("保存XML成功");
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("保存XML失败!");
		}
	
	}
	public static boolean savePerHistoricalSnapshot(PerBaseInfo perBaseinfo,
			PerResult result, String snapshotpath, String string) {

		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dir=request.getSession().getServletContext().getRealPath("");
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		adr=adr+root;
		dir=dir+root;
		String Template=BusinessUtil.getMsg("templateUrl");
		String historyTemplate=BusinessUtil.getMsg("perHistorySnapshot");
		PerHistory history=new PerHistory();
		if(perBaseinfo.getName()!=null)history.setName(perBaseinfo.getName());
		if(result.getEncoding()!=null)history.setEncoding(result.getEncoding());
		if(result.getGradeTime()!=null)history.setGradeTime(sdf.format(result.getGradeTime()));
		if(perBaseinfo.getIDCard()!=null)history.setIDCard(perBaseinfo.getIDCard());
		if(perBaseinfo.getIDTermStart()!=null)history.setIDTermStart(sdf.format(perBaseinfo.getIDTermStart()));
		if(perBaseinfo.getIDTermEnd()!=null)history.setIDTermEnd(sdf.format(perBaseinfo.getIDTermEnd()));
		if(perBaseinfo.getUsedName()!=null)history.setUsedName(perBaseinfo.getUsedName());
		if(perBaseinfo.getGender()!=null)history.setGender(perBaseinfo.getGender());
		if(perBaseinfo.getNation()!=null)history.setNation(perBaseinfo.getNation());
		if(perBaseinfo.getPoliticalOutlook()!=null)history.setPoliticalOutlook(perBaseinfo.getPoliticalOutlook());
		if(perBaseinfo.getNationality()!=null)history.setNationality(perBaseinfo.getNationality());
		if(perBaseinfo.getNativePlace()!=null)history.setNativePlace(perBaseinfo.getNativePlace());
		if(perBaseinfo.getMaritalStatus()!=null)history.setMaritalStatus(perBaseinfo.getMaritalStatus());
		if(perBaseinfo.getFertilityCondition()!=null)history.setFertilityCondition(perBaseinfo.getFertilityCondition());
		if(perBaseinfo.getPermanentAddress()!=null)history.setPermanentAddress(perBaseinfo.getPermanentAddress());
		if(perBaseinfo.getPresentAddress()!=null)history.setPresentAddress(perBaseinfo.getPresentAddress());
		if(perBaseinfo.getIDIssuingAgency()!=null)history.setIDIssuingAgency(perBaseinfo.getIDIssuingAgency());
		if(perBaseinfo.getPresentZipCode()!=null)history.setPresentZipCode(perBaseinfo.getPresentZipCode());
		if(result.getModel()!=null)history.setModel(result.getModel().getName()+result.getModel().getVersion());
		if(result.getFinalScore()!=null)history.setFinalScore(String.valueOf(result.getFinalScore()));
		if(result.getFinalLevel()!=null)history.setFinalLevel(result.getFinalLevel());
		System.out.println(adr+string);
		File file=new File(adr+string);
		List<IndexVO> indexvos=new ArrayList<IndexVO>();
		if(file.exists()){
			List<Index> indexs=new ArrayList<Index>();
			try {
				Document document = new SAXReader().read(file);
				indexs=ModelUtil.getIndex(document);
			} catch (DocumentException e) {
				e.printStackTrace();
			}	
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
									indexvo.setOptionName(StringEscapeUtils.escapeHtml4(option.getName()));
									indexvo.setOptionScore(Double.parseDouble(option.getValue()));
									double indexscore=Double.parseDouble(option.getValue())*Double.parseDouble(index3.getWeight());
									indexvo.setIndexScore(indexscore);
								}
							}
							indexvos.add(indexvo);
							System.out.println(indexvo.toString());
						}
					}
				}
			}
		}else{
			IndexVO indexvo=new IndexVO();
			indexvos.add(indexvo);
		}
		history.setIndexs(indexvos);
		boolean flag=Html2PDFUtil.createPDF(history, snapshotpath, dir+Template, historyTemplate);
		return flag;
	
	}
	public static PerScoreVO getPerScore(String path, PerScoreVO psv,
			com.credit.bean.person.PerHistory history) {
		System.out.println("=================开始读取XML==================");
		PerScoreVO perScoreVO=new PerScoreVO();
		perScoreVO.setUserName(psv.getUserName());
		perScoreVO.setCellphone(psv.getCellphone());
		perScoreVO.setPerID(history.getUuid());
		perScoreVO.setApplyReportState(1);
		perScoreVO.setScoreState(6);
		perScoreVO.setState("查看存档");
		try {
			File file=new File(path);
			Document document = new SAXReader().read(file);
			Element Person = document.getRootElement();
			Element PerBaseInfo = Person.element("PerBaseInfo");
			Element name=PerBaseInfo.element("name");
			Element IDCard=PerBaseInfo.element("IDCard");
			Element updateTime=PerBaseInfo.element("updateTime");
			
			perScoreVO.setName(name.getText());
			perScoreVO.setIdcard(IDCard.getText());
			perScoreVO.setUpdateTime(updateTime.getText());
			
			Element Model = Person.element("Model");
			Element modelName = Model.element("name");
			Element version = Model.element("version");
			
			perScoreVO.setModelName(modelName.getText()+version.getText());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		System.out.println("=================结束读取XML==================");
		return perScoreVO;
	}
	public static Map<String, Object> getPerHistory(Map<String, Object> msgMap,String string) {
		System.out.println("=================开始读取XML==================");
		try {
			File file=new File(string);
			Document document = new SAXReader().read(file);
			Element Person = document.getRootElement();
			Element PerBaseInfo = Person.element("PerBaseInfo");
			Element name=PerBaseInfo.element("name");
			Element IDCard=PerBaseInfo.element("IDCard");
			Element PerResult = Person.element("PerResult");
			Element finalScore = PerResult.element("finalScore");
			Element finalLevel = PerResult.element("finalLevel");
			msgMap.put("name", name.getText());
			msgMap.put("IDCard", IDCard.getText());
			msgMap.put("finalscore", finalScore.getText());
			msgMap.put("finallevel", finalLevel.getText());
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		System.out.println("=================结束读取XML==================");
		return msgMap;
	}
}
