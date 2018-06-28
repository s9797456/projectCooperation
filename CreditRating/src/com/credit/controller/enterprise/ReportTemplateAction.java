package com.credit.controller.enterprise;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Encoder;

import com.credit.bean.enterprise.EntBaseInfo;
import com.credit.bean.enterprise.EntResult;
import com.credit.bean.enterprise.Executives;
import com.credit.bean.enterprise.Shareholder;
import com.credit.bean.vo.enterprise.FinalRateImageVo;
import com.credit.bean.vo.enterprise.FinalcialImageVo;
import com.credit.bean.vo.html2pdf.FinancialChange;
import com.credit.bean.vo.html2pdf.FinancialData;
import com.credit.bean.vo.html2pdf.Report;
import com.credit.bean.vo.html2pdf.YearVo;
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.enterprise.EntResultService;
import com.credit.service.enterprise.ExecutivesService;
import com.credit.service.enterprise.FinanceService;
import com.credit.service.enterprise.ShareholderService;
import com.credit.util.CODEUtil;
import com.credit.util.MD5Code;
import com.credit.util.SaveFile;
import com.credit.util.WebUtil;
import com.credit.util.ZXingUtil;
import com.credit.util.html2pdf.Html2PDFUtil;
import com.credit.util.model.IndexRateVo;
import com.credit.util.properties.BusinessUtil;

import freemarker.template.Configuration;
/**
 * @title 评分流程
 * @author 孙尚飞   2017-7-31
 * @desc
 */
@Controller
@RequestMapping("/control/reportTemplate")
public class ReportTemplateAction {
	private static final Logger logger = Logger.getLogger(ReportTemplateAction.class);
	private String moduleName = "管理员操作情况";
	private StringBuffer message = new StringBuffer("");
	private Boolean flag = true;
	Map<String, Object> msgMap = new HashMap<String, Object>();

	
	@Resource(name = "entResultServiceBean")
	private EntResultService entResultService;
	
	@Resource(name = "shareholderServiceBean")
	private ShareholderService shareholderService;
	
	@Resource(name = "executivesServiceBean")
	private ExecutivesService executivesService;
	
	@Resource(name = "financeServiceBean")
	private FinanceService financeService;

	/**
	 * @title 生成报告并下载报告
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "reportTemplate", privilegeValue = "report")
	@RequestMapping("/generateReport")
	@ResponseBody
	public String generateReport(HttpServletRequest request,HttpServletResponse response) {
		Configuration configuration = null;
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		String resultID=request.getParameter("resultID");
		String reportTemplate=request.getParameter("reportTemplate");
		String dir=request.getSession().getServletContext().getRealPath("");
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		dir=dir+root;
		adr=adr+root;
		String reports=BusinessUtil.getMsg("reports");
		String Template=BusinessUtil.getMsg("templateUrl");
		// 路径
		String path =adr+reports;
		// 名字
		Date now = new Date();
		SimpleDateFormat formate = new SimpleDateFormat("yyyyMMddHHmmss");
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		EntResult result=entResultService.find(resultID);
		if(result!=null){
			String name = (result.getEntBaseInfo().getName() + formate.format(now) + ".pdf").trim();
			String savePath=reports + resultID+ ".pdf";
				// 要填入模本的数据文件
			Report report=reportData(result, request);
			boolean flag=Html2PDFUtil.createPDF(report, adr+savePath, dir+Template, reportTemplate);
			if(flag){
				//将报告路径存入数据库中
				result.setReportUrl(savePath);
				entResultService.update(result);
				//下载报告
				downLoad(adr+savePath, name, response);
				//跨服务器传递文件
				if(BusinessUtil.getMsg("CrossUpload").equals("1")){
					if(result.getCustomer()!=null){
						if(!("".equals(result.getCustomer().getDomainName())||result.getCustomer().getDomainName()==null)){
							String url=request.getScheme()+"://"+result.getCustomer().getDomainName();
							WebUtil.crossFile(savePath, adr+savePath, url);
						}
					}
				}
			}		
		}
		return null;
	}
	/**
	 * @title 为模板装载数据
	 * @author  孙尚飞  2017-8-22
	 * @desc
	 */
	
	@SuppressWarnings("static-access")
	public Report reportData(EntResult result,HttpServletRequest request) {
		Report report=new Report(); 
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String dir=request.getSession().getServletContext().getRealPath("");
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		dir=dir+root;
		adr=adr+root;
		if(result.getEntBaseInfo()!=null){
			EntBaseInfo entbaseinfo=result.getEntBaseInfo();
			report.setEntName(entbaseinfo.getName());// 公司名字${name!}
			if(result.getEncoding()==null||"".equals(result.getEncoding())){
				String encode=CODEUtil.getCODE((int) entResultService.countByEncoding());
				report.setEncoding(encode);// 报告编号
				result.setEncoding(encode);
				entResultService.update(result);
			}else{
				report.setEncoding(result.getEncoding());// 报告编号
			}
			
			if (result.getGradeTime() == null) {
				Calendar cal = format.getCalendar();
				cal.setTime(date);
				cal.add(cal.YEAR, 1);
				String valueTime = format.format(cal.getTime());
				String reportProductDate = format.format(date);
				report.setGradeTime(reportProductDate);// 评级日期${reportProductDate!}
				report.setValueTime(valueTime);// 有效期
				result.setGradeTime(date);
				entResultService.update(result);
			} else {
				Calendar cal = format.getCalendar();
				cal.setTime(result.getGradeTime());
				cal.add(cal.YEAR, 1);
				String valueTime = format.format(cal.getTime());
				String reportProductDate = format.format(result.getGradeTime());
				report.setGradeTime(reportProductDate);// 评级日期${reportProductDate!}
				report.setValueTime(valueTime);// 有效期
			}
			MD5Code md5 = new MD5Code();
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":"
					+ request.getServerPort() + path;
			String s1 = basePath
					+ "/user/logon_check.do?uscc="
					+ entbaseinfo.getUSCC()
					+ "&id="
					+ md5.getMD5ofStr(entbaseinfo.getUuid()
							+ entbaseinfo.getUSCC());
			String s2 = dir+BusinessUtil.getMsg("templateUrl");
			if (ZXingUtil.encodeQRCodeImage(s1, null, s2 + "/QRcode.png",
					200, 200, null)) {
				String s3 = dir+BusinessUtil.getMsg("templateUrl")+ "/QRcode.png";
				report.setQrcode(s3);
			}
			
			List<IndexRateVo> indexRates = entResultService.getIndexRate(adr+result.getScoreXMLUrl());
			if(indexRates!=null && indexRates.size()>0){//指标得分比重
				report.setIndexRates(indexRates);
			}else{
				IndexRateVo indexRate = new IndexRateVo();
				indexRates.add(indexRate);
				report.setIndexRates(indexRates);
			}
			report.setFinalLevel(result.getFinalLevel()==null?"-":result.getFinalLevel());//终评等级
			report.setScoreSummary(result.getScoreSummary()==null?"暂无该项内容。":result.getScoreSummary());//评分总结
			//企业基本信息
			report.setEntType(entbaseinfo.getEntType()==null?"-":entbaseinfo.getEntType());
			report.setAddress(entbaseinfo.getAddress()==null?"-":entbaseinfo.getAddress());
			report.setZipCode(entbaseinfo.getZipCode()==null?"-":entbaseinfo.getZipCode());
			report.setLegalPerson(entbaseinfo.getLegalPerson()==null?"-":entbaseinfo.getLegalPerson());
			report.setRegiCapital(entbaseinfo.getRegiCapital()==null?"-":entbaseinfo.getRegiCapital());
			report.setSetupDate(entbaseinfo.getSetupDate()==null?"-":format.format(entbaseinfo.getSetupDate()));
			report.setUscc(entbaseinfo.getUSCC()==null?"-":entbaseinfo.getUSCC());
			report.setRegisOrg(entbaseinfo.getRegisOrg()==null?"-":entbaseinfo.getRegisOrg());
			report.setWebsite(entbaseinfo.getWebsite()==null?"-":entbaseinfo.getWebsite());
			report.setTel(entbaseinfo.getTel()==null?"-":entbaseinfo.getTel());
			report.setFax(entbaseinfo.getFax()==null?"-":entbaseinfo.getFax());
			report.setScale(entbaseinfo.getScale()==null?"-":entbaseinfo.getScale());
			report.setBusinessScope(entbaseinfo.getBusinessScope()==null?"-":entbaseinfo.getBusinessScope());
			//股权结构
			List<Shareholder> shareholders = shareholderService.findAllByEntID(entbaseinfo.getUuid());
			if (shareholders != null && shareholders.size() > 0) {
				report.setShareHolders(shareholders);// 股东信息列表
			} else {
				Shareholder s = new Shareholder();
				shareholders.add(s);
				report.setShareHolders(shareholders);// 股东信息列表
			}
			//高层信息
			List<Executives> executives = executivesService.findAllByEntID(entbaseinfo.getUuid());
			if (executives != null && executives.size() > 0) {
				for(Executives main : executives){
					if(main.getIDCard()!=null){
						if(main.getIDCard().length()>13){
							String id = main.getIDCard();
							main.setIDCard(id.substring(0,id.length()-(id.substring(10)).length())+"****"+id.substring(14));
						}
					}
				}
				report.setExecutives(executives);// 高层信息列表
			} else {
				Executives m = new Executives();
				executives.add(m);
				report.setExecutives(executives);// 高层信息列表
			}
			//资产负债表
			List<FinancialData> list = financeService.balanceSheet(entbaseinfo.getUuid(),0);
			if(list!=null && list.size()>0){
				report.setFinancialDatas(list);
				List<YearVo> years = new ArrayList<YearVo>();
				YearVo yearVo = new YearVo();
				String year1 = list.get(0).getYear1();
				String year2 = list.get(0).getYear2();
				String year3 = list.get(0).getYear3();
				yearVo.setYear1(year1);
				yearVo.setYear2(year2);
				yearVo.setYear3(year3);
				years.add(yearVo);
				report.setYears(years);//年份
				report.setImportantYears(yearVo);
			}else{
				List<FinancialData> financialData = new ArrayList<FinancialData>();
				report.setFinancialDatas(financialData);
				List<YearVo> years = new ArrayList<YearVo>();
				YearVo yearVo = new YearVo();
				String year1 = "-";
				String year2 = "-";
				String year3 = "-";
				yearVo.setYear1(year1);
				yearVo.setYear2(year2);
				yearVo.setYear3(year3);
				years.add(yearVo);
				report.setYears(years);//年份
				report.setImportantYears(yearVo);
			}
			//利润表
			List<FinancialData> list2 = financeService.balanceSheet(entbaseinfo.getUuid(),1);
			if(list2!=null && list.size()>0){
				report.setProfitDatas(list2);
				List<YearVo> profitYears = new ArrayList<YearVo>();
				YearVo yearVo2 = new YearVo();
				String pyear1 = list2.get(0).getYear1();
				String pyear2 = list2.get(0).getYear2();
				String pyear3 = list2.get(0).getYear3();
				yearVo2.setYear1(pyear1);
				yearVo2.setYear2(pyear2);
				yearVo2.setYear3(pyear3);
				profitYears.add(yearVo2);
				report.setProfitYears(profitYears);//年份
			}else{
				List<FinancialData> financialData = new ArrayList<FinancialData>();
				report.setProfitDatas(financialData);
				List<YearVo> profitYears = new ArrayList<YearVo>();
				YearVo yearVo2 = new YearVo();
				String pyear1 = "-";
				String pyear2 = "-";
				String pyear3 = "-";
				yearVo2.setYear1(pyear1);
				yearVo2.setYear2(pyear2);
				yearVo2.setYear3(pyear3);
				profitYears.add(yearVo2);
				report.setProfitYears(profitYears);//年份
			}
			List<FinancialChange> changes = getImportantFinancialData(entbaseinfo.getUuid());
			List<FinancialChange> changes2 = getImportantFinancialRate(entbaseinfo.getUuid());
			if(changes!=null&&changes.size()>0){
				report.setChanges(changes);
			}else{
				FinancialChange fc = new FinancialChange();
				List<FinancialChange> lists = new ArrayList<FinancialChange>();
				lists.add(fc);
				report.setChanges(lists);
			}
			if(changes!=null&&changes.size()>0){
				report.setChanges2(changes2);
			}else{
				FinancialChange fc = new FinancialChange();
				List<FinancialChange> lists = new ArrayList<FinancialChange>();
				lists.add(fc);
				report.setChanges2(lists);
			}
			String path1 = BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root")+BusinessUtil.getMsg("financeImage")
					+entbaseinfo.getUuid()+File.separator+entbaseinfo.getUuid()+"1.png";
			String path2 = BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root")+BusinessUtil.getMsg("financeImage")
					+entbaseinfo.getUuid()+File.separator+entbaseinfo.getUuid()+"2.png";
			report.setImg1(path1);
			report.setImg2(path2);
		}
		return report;
	}
	
	
	private List<FinancialChange> getImportantFinancialData(String entID) {
		List<FinancialChange> list = new ArrayList<FinancialChange>();
		Map<String,Object> dataMap = reportfinalAndIndexData(entID);
		FinancialChange fc1 = new FinancialChange();
		FinancialChange fc2 = new FinancialChange();
		FinancialChange fc3 = new FinancialChange();
		FinancialChange fc4 = new FinancialChange();
		FinancialChange fc5 = new FinancialChange();
		FinancialChange fc6 = new FinancialChange();
		FinancialChange fc7 = new FinancialChange();
		FinancialChange fc8 = new FinancialChange();
		FinancialChange fc9 = new FinancialChange();
		FinancialChange fc10 = new FinancialChange();
		fc1.setName("流动资产");
		fc1.setValue1(dataMap.get("liquidAssets1")==null?"-":dataMap.get("liquidAssets1").toString());
		fc1.setValue2(dataMap.get("liquidAssets2")==null?"-":dataMap.get("liquidAssets2").toString());
		fc1.setValue3(dataMap.get("liquidAssets3")==null?"-":dataMap.get("liquidAssets3").toString());
		fc1.setChange1(dataMap.get("liquidAssets1Rate")==null?"-":dataMap.get("liquidAssets1Rate").toString());
		fc1.setChange2(dataMap.get("liquidAssets2Rate")==null?"-":dataMap.get("liquidAssets2Rate").toString());
		
		fc2.setName("流动负债");
		fc2.setValue1(dataMap.get("liquidLiabilities1")==null?"-":dataMap.get("liquidLiabilities1").toString());
		fc2.setValue2(dataMap.get("liquidLiabilities2")==null?"-":dataMap.get("liquidLiabilities2").toString());
		fc2.setValue3(dataMap.get("liquidLiabilities3")==null?"-":dataMap.get("liquidLiabilities3").toString());
		fc2.setChange1(dataMap.get("liquidLiabilities1Rate")==null?"-":dataMap.get("liquidLiabilities1Rate").toString());
		fc2.setChange2(dataMap.get("liquidLiabilities2Rate")==null?"-":dataMap.get("liquidLiabilities2Rate").toString());
		
		fc3.setName("营运资本");
		fc3.setValue1(dataMap.get("workingCapital1")==null?"-":dataMap.get("workingCapital1").toString());
		fc3.setValue2(dataMap.get("workingCapital2")==null?"-":dataMap.get("workingCapital2").toString());
		fc3.setValue3(dataMap.get("workingCapital3")==null?"-":dataMap.get("workingCapital3").toString());
		fc3.setChange1(dataMap.get("workingCapital1Rate")==null?"-":dataMap.get("workingCapital1Rate").toString());
		fc3.setChange2(dataMap.get("workingCapital2Rate")==null?"-":dataMap.get("workingCapital2Rate").toString());
		
		fc4.setName("有形净值");
		fc4.setValue1(dataMap.get("tangibleNetWorth1")==null?"-":dataMap.get("tangibleNetWorth1").toString());
		fc4.setValue2(dataMap.get("tangibleNetWorth2")==null?"-":dataMap.get("tangibleNetWorth2").toString());
		fc4.setValue3(dataMap.get("tangibleNetWorth3")==null?"-":dataMap.get("tangibleNetWorth3").toString());
		fc4.setChange1(dataMap.get("tangibleNetWorth1Rate")==null?"-":dataMap.get("tangibleNetWorth1Rate").toString());
		fc4.setChange2(dataMap.get("tangibleNetWorth2Rate")==null?"-":dataMap.get("tangibleNetWorth2Rate").toString());
		
		fc5.setName("固定资产");
		fc5.setValue1(dataMap.get("fixedAssets1")==null?"-":dataMap.get("fixedAssets1").toString());
		fc5.setValue2(dataMap.get("fixedAssets2")==null?"-":dataMap.get("fixedAssets2").toString());
		fc5.setValue3(dataMap.get("fixedAssets3")==null?"-":dataMap.get("fixedAssets3").toString());
		fc5.setChange1(dataMap.get("fixedAssets1Rate")==null?"-":dataMap.get("fixedAssets1Rate").toString());
		fc5.setChange2(dataMap.get("fixedAssets2Rate")==null?"-":dataMap.get("fixedAssets2Rate").toString());
		
		fc6.setName("总资产");
		fc6.setValue1(dataMap.get("totalAssets1")==null?"-":dataMap.get("totalAssets1").toString());
		fc6.setValue2(dataMap.get("totalAssets2")==null?"-":dataMap.get("totalAssets2").toString());
		fc6.setValue3(dataMap.get("totalAssets3")==null?"-":dataMap.get("totalAssets3").toString());
		fc6.setChange1(dataMap.get("totalAssets1Rate")==null?"-":dataMap.get("totalAssets1Rate").toString());
		fc6.setChange2(dataMap.get("totalAssets2Rate")==null?"-":dataMap.get("totalAssets2Rate").toString());
		
		fc7.setName("总负债");
		fc7.setValue1(dataMap.get("totalLiabilities1")==null?"-":dataMap.get("totalLiabilities1").toString());
		fc7.setValue2(dataMap.get("totalLiabilities2")==null?"-":dataMap.get("totalLiabilities2").toString());
		fc7.setValue3(dataMap.get("totalLiabilities3")==null?"-":dataMap.get("totalLiabilities3").toString());
		fc7.setChange1(dataMap.get("totalLiabilities1Rate")==null?"-":dataMap.get("totalLiabilities1Rate").toString());
		fc7.setChange2(dataMap.get("totalLiabilities2Rate")==null?"-":dataMap.get("totalLiabilities2Rate").toString());
		
		fc8.setName("股东权益");
		fc8.setValue1(dataMap.get("ownerEquity1")==null?"-":dataMap.get("ownerEquity1").toString());
		fc8.setValue2(dataMap.get("ownerEquity2")==null?"-":dataMap.get("ownerEquity2").toString());
		fc8.setValue3(dataMap.get("ownerEquity3")==null?"-":dataMap.get("ownerEquity3").toString());
		fc8.setChange1(dataMap.get("ownerEquity1Rate")==null?"-":dataMap.get("ownerEquity1Rate").toString());
		fc8.setChange2(dataMap.get("ownerEquity2Rate")==null?"-":dataMap.get("ownerEquity2Rate").toString());
		
		fc9.setName("营业收入");
		fc9.setValue1(dataMap.get("operatingIncome1")==null?"-":dataMap.get("operatingIncome1").toString());
		fc9.setValue2(dataMap.get("operatingIncome2")==null?"-":dataMap.get("operatingIncome2").toString());
		fc9.setValue3(dataMap.get("operatingIncome3")==null?"-":dataMap.get("operatingIncome3").toString());
		fc9.setChange1(dataMap.get("operatingIncome1Rate")==null?"-":dataMap.get("operatingIncome1Rate").toString());
		fc9.setChange2(dataMap.get("operatingIncome2Rate")==null?"-":dataMap.get("operatingIncome2Rate").toString());
		
		fc10.setName("净利润");
		fc10.setValue1(dataMap.get("netProfit1")==null?"-":dataMap.get("netProfit1").toString());
		fc10.setValue2(dataMap.get("netProfit2")==null?"-":dataMap.get("netProfit2").toString());
		fc10.setValue3(dataMap.get("netProfit3")==null?"-":dataMap.get("netProfit3").toString());
		fc10.setChange1(dataMap.get("netProfit1Rate")==null?"-":dataMap.get("netProfit1Rate").toString());
		fc10.setChange2(dataMap.get("netProfit2Rate")==null?"-":dataMap.get("netProfit2Rate").toString());
		list.add(fc1);
		list.add(fc2);
		list.add(fc3);
		list.add(fc4);
		list.add(fc5);
		list.add(fc6);
		list.add(fc7);
		list.add(fc8);
		list.add(fc9);
		list.add(fc10);
		return list;
		
	}
	
	private List<FinancialChange> getImportantFinancialRate(String entID) {
		List<FinancialChange> list = new ArrayList<FinancialChange>();
		Map<String,Object> dataMap = reportfinalAndIndexData(entID);
		FinancialChange fc1 = new FinancialChange();
		FinancialChange fc2 = new FinancialChange();
		FinancialChange fc3 = new FinancialChange();
		FinancialChange fc4 = new FinancialChange();
		FinancialChange fc5 = new FinancialChange();
		FinancialChange fc6 = new FinancialChange();
		FinancialChange fc7 = new FinancialChange();
		FinancialChange fc8 = new FinancialChange();
		FinancialChange fc9 = new FinancialChange();
		FinancialChange fc10 = new FinancialChange();
		FinancialChange fc11 = new FinancialChange();
		fc1.setName("流动比率");
		fc1.setValue1(dataMap.get("currentRatio1")==null?"-":dataMap.get("currentRatio1").toString());
		fc1.setValue2(dataMap.get("currentRatio2")==null?"-":dataMap.get("currentRatio2").toString());
		fc1.setValue3(dataMap.get("currentRatio3")==null?"-":dataMap.get("currentRatio3").toString());
		
		fc2.setName("速动比率");
		fc2.setValue1(dataMap.get("quickRatio1")==null?"-":dataMap.get("quickRatio1").toString());
		fc2.setValue2(dataMap.get("quickRatio2")==null?"-":dataMap.get("quickRatio2").toString());
		fc2.setValue3(dataMap.get("quickRatio3")==null?"-":dataMap.get("quickRatio3").toString());
		
		fc3.setName("负债权益比率");
		fc3.setValue1(dataMap.get("debtequityRatio1")==null?"-":dataMap.get("debtequityRatio1").toString());
		fc3.setValue2(dataMap.get("debtequityRatio2")==null?"-":dataMap.get("debtequityRatio2").toString());
		fc3.setValue3(dataMap.get("debtequityRatio3")==null?"-":dataMap.get("debtequityRatio3").toString());
		
		fc4.setName("资产负债率");
		fc4.setValue1(dataMap.get("assetliabilityRatio1")==null?"-":dataMap.get("assetliabilityRatio1").toString());
		fc4.setValue2(dataMap.get("assetliabilityRatio2")==null?"-":dataMap.get("assetliabilityRatio2").toString());
		fc4.setValue3(dataMap.get("assetliabilityRatio3")==null?"-":dataMap.get("assetliabilityRatio3").toString());
		
		fc5.setName("应收账款回收周期(天)");
		fc5.setValue1(dataMap.get("cycleDate1")==null?"-":dataMap.get("cycleDate1").toString());
		fc5.setValue2(dataMap.get("cycleDate2")==null?"-":dataMap.get("cycleDate2").toString());
		fc5.setValue3(dataMap.get("cycleDate3")==null?"-":dataMap.get("cycleDate3").toString());
		
		fc6.setName("存货周转期 (天)");
		fc6.setValue1(dataMap.get("inventoryProcessingPeriod1")==null?"-":dataMap.get("inventoryProcessingPeriod1").toString());
		fc6.setValue2(dataMap.get("inventoryProcessingPeriod2")==null?"-":dataMap.get("inventoryProcessingPeriod2").toString());
		fc6.setValue3(dataMap.get("inventoryProcessingPeriod3")==null?"-":dataMap.get("inventoryProcessingPeriod3").toString());
		
		fc7.setName("资产周转率");
		fc7.setValue1(dataMap.get("assetTurnover1")==null?"-":dataMap.get("assetTurnover1").toString());
		fc7.setValue2(dataMap.get("assetTurnover2")==null?"-":dataMap.get("assetTurnover2").toString());
		fc7.setValue3(dataMap.get("assetTurnover3")==null?"-":dataMap.get("assetTurnover3").toString());
		
		fc8.setName("毛利润率");
		fc8.setValue1(dataMap.get("grossProfitMargin1")==null?"-":dataMap.get("grossProfitMargin1").toString());
		fc8.setValue2(dataMap.get("grossProfitMargin2")==null?"-":dataMap.get("grossProfitMargin2").toString());
		fc8.setValue3(dataMap.get("grossProfitMargin3")==null?"-":dataMap.get("grossProfitMargin3").toString());
		
		fc9.setName("净利润率");
		fc9.setValue1(dataMap.get("netProfitMargin1")==null?"-":dataMap.get("netProfitMargin1").toString());
		fc9.setValue2(dataMap.get("netProfitMargin2")==null?"-":dataMap.get("netProfitMargin2").toString());
		fc9.setValue3(dataMap.get("netProfitMargin3")==null?"-":dataMap.get("netProfitMargin3").toString());
		
		fc10.setName("股东权益收益率");
		fc10.setValue1(dataMap.get("shareholdersEquity1")==null?"-":dataMap.get("shareholdersEquity1").toString());
		fc10.setValue2(dataMap.get("shareholdersEquity2")==null?"-":dataMap.get("shareholdersEquity2").toString());
		fc10.setValue3(dataMap.get("shareholdersEquity3")==null?"-":dataMap.get("shareholdersEquity3").toString());
		
		fc11.setName("资产收益率");
		fc11.setValue1(dataMap.get("assetEarningRate1")==null?"-":dataMap.get("assetEarningRate1").toString());
		fc11.setValue2(dataMap.get("assetEarningRate2")==null?"-":dataMap.get("assetEarningRate2").toString());
		fc11.setValue3(dataMap.get("assetEarningRate3")==null?"-":dataMap.get("assetEarningRate3").toString());
		
		list.add(fc1);
		list.add(fc2);
		list.add(fc3);
		list.add(fc4);
		list.add(fc5);
		list.add(fc6);
		list.add(fc7);
		list.add(fc8);
		list.add(fc9);
		list.add(fc10);
		list.add(fc11);
		return list;
		
	}
	/**
	 * @title 判断评分报告是否存在
	 * @author  孙尚飞  2017-8-22
	 * @desc
	 */
	@Permission(model = "reportTemplate", privilegeValue = "report")
	@RequestMapping("/judgeReportExist")
	@ResponseBody
	public Map<String, Object> judgeReportExist(HttpServletRequest request,String resultID) {
		logger.info(moduleName + "[判断评分报告是否存在]");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		boolean flag=true;
		EntResult result=entResultService.find(resultID);
		if(result==null){
			flag=false;
		}else{
			if(result.getReportUrl()==null){
				flag=false;
			}else{
				String path=BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root")+result.getReportUrl();
				File file = new File(path);
				if (!file.exists()) {
					flag=false;
				}
			}
		}
		msgMap.put("flag", flag);
		msgMap.put("success", true);
		msgMap.put("status", true);
		return msgMap;
	}
	/**
	 * @Title 上传报告
	 * @author  孙尚飞  @date 2017-8-22 
	 * @throws Exception 
	 * @Description 
	 *
	 */
	@Permission(model = "reportTemplate", privilegeValue = "report")
	@RequestMapping("/uploadReport")
	@ResponseBody
	public Map<String, Object> uploadReport(HttpServletRequest request) throws Exception {
		logger.info(moduleName + "[上传修改后的报告]");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if(SaveFile.judgeSuffix(request, "pdf")){
			String resultID=request.getParameter("resultID");
			EntResult result=entResultService.find(resultID);
			String path=BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root")+BusinessUtil.getMsg("reports");
			String fileName=resultID+".pdf";
			SaveFile.deleteFile(path);
			SaveFile.uploadFile(request, path, fileName);
			//跨服务器传递文件
			if(BusinessUtil.getMsg("CrossUpload").equals("1")){
				if(result.getCustomer()!=null){
					if(!("".equals(result.getCustomer().getDomainName())||result.getCustomer().getDomainName()==null)){
						String url=request.getScheme()+"://"+result.getCustomer().getDomainName();
						WebUtil.crossFile(BusinessUtil.getMsg("reports")+fileName, path+fileName, url);
					}
				}
			}
			msgMap.put("success", true);
			msgMap.put("statue", true);
			msgMap.put("msg", "上传成功");
		}else{
			msgMap.put("success", true);
			msgMap.put("statue", false);
			msgMap.put("msg", "上传失败");
			msgMap.put("result", "上传文件不是PDF文件，请重新上传！");
		}
		return msgMap;
	}
	/**
	 * @title 下载报告
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "reportTemplate", privilegeValue = "report")
	@RequestMapping("/downLoadPdf")
	@ResponseBody
	public void downLoadPdf(HttpServletRequest request,HttpServletResponse response) {
		SimpleDateFormat formate = new SimpleDateFormat("yyyyMMddHHmmss");
		String resultID=request.getParameter("resultID");
		EntResult result=entResultService.find(resultID);
		String name = (result.getEntBaseInfo().getName() + formate.format(new Date()) + ".pdf").trim();
		String path=BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root")+result.getReportUrl();
		downLoad(path, name, response);
	}
	/**
	 * @title 下载Word报告
	 * @author  孙尚飞  2017-7-31
	 * @desc
	 */
	@Permission(model = "reportTemplate", privilegeValue = "report")
	@RequestMapping("/downLoadWord")
	@ResponseBody
	public void downLoadWord(HttpServletRequest request,HttpServletResponse response) {
		SimpleDateFormat formate = new SimpleDateFormat("yyyyMMddHHmmss");
		String resultID=request.getParameter("resultID");
		EntResult result=entResultService.find(resultID);
		String name = (result.getEntBaseInfo().getName() + formate.format(new Date()) + ".doc").trim();
		String word_path=result.getReportUrl().replace(".pdf", ".doc");
		String path=BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root")+word_path;
		downLoad(path, name, response);
	}
	/**
	 * @title 下载方法
	 * @author  孙尚飞  2017-8-22
	 * @desc
	 */
	public String downLoad(String path,String name,HttpServletResponse response) {

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
	private String getImageStr(String url) {
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(url);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}
	
	
	// 财务及指标
		private Map<String, Object> reportfinalAndIndexData(String entID) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			list = financeService.reportFinalData(entID);
			Map<String, String> map1 = new HashMap<String, String>();
			Map<String, String> map2 = new HashMap<String, String>();
			Map<String, String> map3 = new HashMap<String, String>();
			if (list.size() > 2) {
				map1 = list.get(0);
				map2 = list.get(1);
				map3 = list.get(2);
				dataMap.put("fianlDate1", map1.get("年份"));
				dataMap.put("fianlDate2", map2.get("年份"));
				dataMap.put("fianlDate3", map3.get("年份"));
			
			String liquidAssets1 = map1.get("流动资产合计") == null ? "-" : map1
					.get("流动资产合计");
			String liquidAssets2 = map2.get("流动资产合计") == null ? "-" : map2
					.get("流动资产合计");
			String liquidAssets3 = map3.get("流动资产合计") == null ? "-" : map3
					.get("流动资产合计");
			dataMap.put("liquidAssets1", liquidAssets1);
			dataMap.put("liquidAssets2", liquidAssets2);
			dataMap.put("liquidAssets3", liquidAssets3);
			if ("-".equals(liquidAssets1) || "-".equals(liquidAssets2) || "0".equals(liquidAssets2)) {
				dataMap.put("liquidAssets1Rate", "-");
			} else {
					dataMap.put("liquidAssets1Rate",String.format("%.2f",(Double.parseDouble(liquidAssets1) * 100 / Double.parseDouble(liquidAssets2)) - 100)+ "%");
			}
			if ("-".equals(liquidAssets2) || "-".equals(liquidAssets3) || "0".equals(liquidAssets3)) {
				dataMap.put("liquidAssets2Rate", "-");
			} else {
				dataMap.put(
						"liquidAssets2Rate",
						String.format("%.2f",
								(Double.parseDouble(liquidAssets2) * 100 / Double
										.parseDouble(liquidAssets3)) - 100)
								+ "%");
			}

			String liquidLiabilities1 = map1.get("流动负债合计") == null ? "-" : map1
					.get("流动负债合计");
			String liquidLiabilities2 = map2.get("流动负债合计") == null ? "-" : map2
					.get("流动负债合计");
			String liquidLiabilities3 = map3.get("流动负债合计") == null ? "-" : map3
					.get("流动负债合计");
			dataMap.put("liquidLiabilities1", liquidLiabilities1);
			dataMap.put("liquidLiabilities2", liquidLiabilities2);
			dataMap.put("liquidLiabilities3", liquidLiabilities3);
			if ("-".equals(liquidLiabilities1) || "-".equals(liquidLiabilities2) || "0".equals(liquidLiabilities2)) {
				dataMap.put("liquidLiabilities1Rate", "-");
			} else {
				dataMap.put(
						"liquidLiabilities1Rate",
						String.format("%.2f", (Double
								.parseDouble(liquidLiabilities1) * 100 / Double
								.parseDouble(liquidLiabilities2)) - 100)
								+ "%");
			}
			if ("-".equals(liquidLiabilities2) || "-".equals(liquidLiabilities3) || "0".equals(liquidLiabilities3)) {
				dataMap.put("liquidLiabilities2Rate", "-");
			} else {
				dataMap.put(
						"liquidLiabilities2Rate",
						String.format("%.2f", (Double
								.parseDouble(liquidLiabilities2) * 100 / Double
								.parseDouble(liquidLiabilities3)) - 100)
								+ "%");
			}
			// 营运资本workingCapital
			try {
				Double workingCapital1 = Double.parseDouble(map1.get("流动资产合计"))
						- Double.parseDouble(map1.get("流动负债合计"));
				dataMap.put("workingCapital1", workingCapital1);
			} catch (Exception e) {
				dataMap.put("workingCapital1", "-");
			}
			try {
				Double workingCapital2 = Double.parseDouble(map2.get("流动资产合计"))
						- Double.parseDouble(map2.get("流动负债合计"));
				dataMap.put("workingCapital2", workingCapital2);
			} catch (Exception e) {
				dataMap.put("workingCapital2", "-");
			}
			try {
				Double workingCapital3 = Double.parseDouble(map3.get("流动资产合计"))
						- Double.parseDouble(map3.get("流动负债合计"));
				dataMap.put("workingCapital3", workingCapital3);
			} catch (Exception e) {
				dataMap.put("workingCapital3", "-");
			}
			try {
				Double workingCapital1 = Double.parseDouble(map1.get("流动资产合计"))
						- Double.parseDouble(map1.get("流动负债合计"));
				Double workingCapital2 = Double.parseDouble(map2.get("流动资产合计"))
						- Double.parseDouble(map2.get("流动负债合计"));
				if(workingCapital2 != 0){
					dataMap.put(
							"workingCapital1Rate",
							String.format("%.2f",
									(workingCapital1 / workingCapital2) * 100 - 100)
									+ "%");
				}else{
					dataMap.put("workingCapital1Rate", "-");
				}
			} catch (Exception e) {
				dataMap.put("workingCapital1Rate", "-");
			}
			try {
				Double workingCapital2 = Double.parseDouble(map2.get("流动资产合计"))
						- Double.parseDouble(map2.get("流动负债合计"));
				Double workingCapital3 = Double.parseDouble(map3.get("流动资产合计"))
						- Double.parseDouble(map3.get("流动负债合计"));
				if(workingCapital3!=0){
					dataMap.put(
							"workingCapital2Rate",
							String.format("%.2f",
									(workingCapital2 / workingCapital3) * 100 - 100)
									+ "%");
				}else{
					dataMap.put("workingCapital2Rate", "-");
				}
				
			} catch (Exception e) {
				dataMap.put("workingCapital2Rate", "-");
			}
			// 有形净值
			try {
				Double tangibleNetWorth1 = Double.parseDouble(map1.get("资产总计"))
						- Double.parseDouble(map1.get("无形资产"));
				dataMap.put("tangibleNetWorth1", tangibleNetWorth1);// 有形净值
			} catch (Exception e) {
				dataMap.put("tangibleNetWorth1", "-");// 有形净值
			}
			try {
				Double tangibleNetWorth2 = Double.parseDouble(map2.get("资产总计"))
						- Double.parseDouble(map2.get("无形资产"));
				dataMap.put("tangibleNetWorth2", tangibleNetWorth2);// 有形净值
			} catch (Exception e) {
				dataMap.put("tangibleNetWorth2", "-");// 有形净值
			}
			try {
				Double tangibleNetWorth3 = Double.parseDouble(map3.get("资产总计"))
						- Double.parseDouble(map3.get("无形资产"));
				dataMap.put("tangibleNetWorth3", tangibleNetWorth3);// 有形净值
			} catch (Exception e) {
				dataMap.put("tangibleNetWorth3", "-");// 有形净值
			}
			try {
				Double tangibleNetWorth1 = Double.parseDouble(map1.get("资产总计"))
						- Double.parseDouble(map1.get("无形资产"));
				Double tangibleNetWorth2 = Double.parseDouble(map2.get("资产总计"))
						- Double.parseDouble(map2.get("无形资产"));
				if(tangibleNetWorth2!=0){
					dataMap.put(
							"tangibleNetWorth1Rate",
							String.format("%.2f",
									(tangibleNetWorth1 / tangibleNetWorth2) * 100 - 100)
									+ "%");
				}else{
					dataMap.put("tangibleNetWorth1Rate", "-");
				}
			} catch (Exception e) {
				dataMap.put("tangibleNetWorth1Rate", "-");
			}
			try {
				Double tangibleNetWorth2 = Double.parseDouble(map2.get("资产总计"))
						- Double.parseDouble(map2.get("无形资产"));
				Double tangibleNetWorth3 = Double.parseDouble(map3.get("资产总计"))
						- Double.parseDouble(map3.get("无形资产"));
				if(tangibleNetWorth3!=0){
					dataMap.put(
							"tangibleNetWorth2Rate",
							String.format("%.2f",
									(tangibleNetWorth2 / tangibleNetWorth3) * 100 - 100)
									+ "%");
				}else{
					dataMap.put("tangibleNetWorth2Rate", "-");
				}
			} catch (Exception e) {
				dataMap.put("tangibleNetWorth2Rate", "-");
			}
			// 固定资产
			String fixedAssets1 = map1.get("固定资产原价") == null ? "-" : map1
					.get("固定资产原价");
			String fixedAssets2 = map2.get("固定资产原价") == null ? "-" : map2
					.get("固定资产原价");
			String fixedAssets3 = map3.get("固定资产原价") == null ? "-" : map3
					.get("固定资产原价");
			dataMap.put("fixedAssets1", fixedAssets1);
			dataMap.put("fixedAssets2", fixedAssets2);
			dataMap.put("fixedAssets3", fixedAssets3);
			if ("-".equals(fixedAssets1) || "-".equals(fixedAssets2) || "0".equals(fixedAssets2)) {
				dataMap.put("fixedAssets1Rate", "-");
			} else {
				dataMap.put(
						"fixedAssets1Rate",
						String.format("%.2f",
								(Double.parseDouble(fixedAssets1) * 100 / Double
										.parseDouble(fixedAssets2)) - 100)
								+ "%");
			}
			if ("-".equals(fixedAssets2) || "-".equals(fixedAssets3) || "0".equals(fixedAssets3)) {
				dataMap.put("fixedAssets2Rate", "-");
			} else {
				dataMap.put(
						"fixedAssets2Rate",
						String.format("%.2f",
								(Double.parseDouble(fixedAssets2) * 100 / Double
										.parseDouble(fixedAssets3)) - 100)
								+ "%");
			}
			// 资产总计
			String totalAssets1 = map1.get("资产总计") == null ? "-" : map1.get("资产总计");
			String totalAssets2 = map2.get("资产总计") == null ? "-" : map2.get("资产总计");
			String totalAssets3 = map3.get("资产总计") == null ? "-" : map3.get("资产总计");
			dataMap.put("totalAssets1", totalAssets1);
			dataMap.put("totalAssets2", totalAssets2);
			dataMap.put("totalAssets3", totalAssets3);
			if ("-".equals(totalAssets1) || "-".equals(totalAssets2) || "0".equals(totalAssets2)) {
				dataMap.put("totalAssets1Rate", "-");
			} else {
				dataMap.put(
						"totalAssets1Rate",
						String.format("%.2f",
								(Double.parseDouble(totalAssets1) * 100 / Double
										.parseDouble(totalAssets2)) - 100)
								+ "%");
			}
			if ("-".equals(totalAssets2) || "-".equals(totalAssets3) || "0".equals(totalAssets3)) {
				dataMap.put("totalAssets2Rate", "-");
			} else {
				dataMap.put(
						"totalAssets2Rate",
						String.format("%.2f",
								(Double.parseDouble(totalAssets2) * 100 / Double
										.parseDouble(totalAssets3)) - 100)
								+ "%");
			}
			// 负债合计
			String totalLiabilities1 = map1.get("负债合计") == null ? "-" : map1
					.get("负债合计");
			String totalLiabilities2 = map2.get("负债合计") == null ? "-" : map2
					.get("负债合计");
			String totalLiabilities3 = map3.get("负债合计") == null ? "-" : map3
					.get("负债合计");
			dataMap.put("totalLiabilities1", totalLiabilities1);
			dataMap.put("totalLiabilities2", totalLiabilities2);
			dataMap.put("totalLiabilities3", totalLiabilities3);
			if ("-".equals(totalLiabilities1) || "-".equals(totalLiabilities2) || "0".equals(totalLiabilities2)) {
				dataMap.put("totalLiabilities1Rate", "-");
			} else {
				dataMap.put(
						"totalLiabilities1Rate",
						String.format("%.2f", (Double
								.parseDouble(totalLiabilities1) * 100 / Double
								.parseDouble(totalLiabilities2)) - 100)
								+ "%");
			}
			if ("-".equals(totalLiabilities2) || "-".equals(totalLiabilities3) || "0".equals(totalLiabilities3)) {
				dataMap.put("totalLiabilities2Rate", "-");
			} else {
				dataMap.put(
						"totalLiabilities2Rate",
						String.format("%.2f", (Double
								.parseDouble(totalLiabilities2) * 100 / Double
								.parseDouble(totalLiabilities3)) - 100)
								+ "%");
			}
			// 股东权益合计
			String ownerEquity1 = map1.get("股东权益合计") == null ? "-" : map1
					.get("股东权益合计");
			String ownerEquity2 = map2.get("股东权益合计") == null ? "-" : map2
					.get("股东权益合计");
			String ownerEquity3 = map3.get("股东权益合计") == null ? "-" : map3
					.get("股东权益合计");
			dataMap.put("ownerEquity1", ownerEquity1);
			dataMap.put("ownerEquity2", ownerEquity2);
			dataMap.put("ownerEquity3", ownerEquity3);
			if ("-".equals(ownerEquity1) || "-".equals(ownerEquity2) || "0".equals(ownerEquity2)) {
				dataMap.put("ownerEquity1Rate", "-");
			} else {
				dataMap.put(
						"ownerEquity1Rate",
						String.format("%.2f",
								(Double.parseDouble(ownerEquity1) * 100 / Double
										.parseDouble(ownerEquity2)) - 100)
								+ "%");
			}
			if ("-".equals(ownerEquity2) || "-".equals(ownerEquity3) || "0".equals(ownerEquity3)) {
				dataMap.put("ownerEquity2Rate", "-");
			} else {
				dataMap.put(
						"ownerEquity2Rate",
						String.format("%.2f",
								(Double.parseDouble(ownerEquity2) * 100 / Double
										.parseDouble(ownerEquity3)) - 100)
								+ "%");
			}
			// 营业收入
			String operatingIncome1 = map1.get("营业收入") == null ? "-" : map1
					.get("营业收入");
			String operatingIncome2 = map2.get("营业收入") == null ? "-" : map2
					.get("营业收入");
			String operatingIncome3 = map3.get("营业收入") == null ? "-" : map3
					.get("营业收入");
			dataMap.put("operatingIncome1", operatingIncome1);
			dataMap.put("operatingIncome2", operatingIncome2);
			dataMap.put("operatingIncome3", operatingIncome3);
			if ("-".equals(operatingIncome1) || "-".equals(operatingIncome2) || "0".equals(operatingIncome2)) {
				dataMap.put("operatingIncome1Rate", "-");
			} else {
				dataMap.put(
						"operatingIncome1Rate",
						String.format(
								"%.2f",
								(Double.parseDouble(operatingIncome1) * 100 / Double
										.parseDouble(operatingIncome2)) - 100)
								+ "%");
			}
			if ("-".equals(operatingIncome2) || "-".equals(operatingIncome3) || "0".equals(operatingIncome3)) {
				dataMap.put("operatingIncome2Rate", "-");
			} else {
				dataMap.put(
						"operatingIncome2Rate",
						String.format(
								"%.2f",
								(Double.parseDouble(operatingIncome2) * 100 / Double
										.parseDouble(operatingIncome3)) - 100)
								+ "%");
			}
			// 净利润
			String netProfit1 = map1.get("净利润") == null ? "-" : map1.get("净利润");
			String netProfit2 = map2.get("净利润") == null ? "-" : map2.get("净利润");
			String netProfit3 = map3.get("净利润") == null ? "-" : map3.get("净利润");
			dataMap.put("netProfit1", netProfit1);
			dataMap.put("netProfit2", netProfit2);
			dataMap.put("netProfit3", netProfit3);
			if ("-".equals(netProfit1) || "-".equals(netProfit2) || "0".equals(netProfit2)) {
				dataMap.put("netProfit1Rate", "-");
			} else {
				dataMap.put(
						"netProfit1Rate",
						String.format("%.2f",
								(Double.parseDouble(netProfit1) * 100 / Double
										.parseDouble(netProfit2)) - 100)
								+ "%");
			}
			if ("-".equals(netProfit2) || "-".equals(netProfit3) || "0".equals(netProfit3)) {
				dataMap.put("netProfit2Rate", "-");
			} else {
				dataMap.put(
						"netProfit2Rate",
						String.format("%.2f",
								(Double.parseDouble(netProfit2) * 100 / Double
										.parseDouble(netProfit3)) - 100)
								+ "%");
			}
			// 资产负债表
			dataMap.put("monetaryFund1",
					map1.get("货币资金") == null ? "-" : map1.get("货币资金"));
			dataMap.put("monetaryFund2",
					map2.get("货币资金") == null ? "-" : map2.get("货币资金"));
			dataMap.put("monetaryFund3",
					map3.get("货币资金") == null ? "-" : map3.get("货币资金"));

			dataMap.put("accountsReceivable1", map1.get("应收账款") == null ? "-"
					: map1.get("应收账款"));
			dataMap.put("accountsReceivable2", map2.get("应收账款") == null ? "-"
					: map2.get("应收账款"));
			dataMap.put("accountsReceivable3", map3.get("应收账款") == null ? "-"
					: map3.get("应收账款"));

			dataMap.put("otherReceivables1",
					map1.get("其他应收款") == null ? "-" : map1.get("其他应收款"));
			dataMap.put("otherReceivables2",
					map2.get("其他应收款") == null ? "-" : map2.get("其他应收款"));
			dataMap.put("otherReceivables3",
					map3.get("其他应收款") == null ? "-" : map3.get("其他应收款"));

			dataMap.put("inventory1", map1.get("存货") == null ? "-" : map1.get("存货"));
			dataMap.put("inventory2", map2.get("存货") == null ? "-" : map2.get("存货"));
			dataMap.put("inventory3", map3.get("存货") == null ? "-" : map3.get("存货"));

			dataMap.put("otherLiquidAssets1", map1.get("其他流动资产") == null ? "-"
					: map1.get("其他流动资产"));
			dataMap.put("otherLiquidAssets2", map2.get("其他流动资产") == null ? "-"
					: map2.get("其他流动资产"));
			dataMap.put("otherLiquidAssets3", map3.get("其他流动资产") == null ? "-"
					: map3.get("其他流动资产"));
			// 在重要财务数据中
			/*
			 * dataMap.put("liquidAssets1", map1.get("流动资产合计"));
			 * dataMap.put("liquidAssets2", map2.get("流动资产合计"));
			 * dataMap.put("liquidAssets3", map3.get("流动资产合计"));
			 */
			dataMap.put("fixedAssetsCostPrice1", map1.get("固定资产原价") == null ? "-"
					: map1.get("固定资产原价"));
			dataMap.put("fixedAssetsCostPrice2", map2.get("固定资产原价") == null ? "-"
					: map2.get("固定资产原价"));
			dataMap.put("fixedAssetsCostPrice3", map3.get("固定资产原价") == null ? "-"
					: map3.get("固定资产原价"));

			try {
				Double accumulatedDepreciation1 = Double.parseDouble(map1
						.get("固定资产原价")) - Double.parseDouble(map1.get("固定资产净值"));
				dataMap.put("accumulatedDepreciation1", accumulatedDepreciation1);// 累计折旧
			} catch (Exception e) {
				dataMap.put("accumulatedDepreciation1", "-");// 累计折旧
			}

			try {
				Double accumulatedDepreciation2 = Double.parseDouble(map2
						.get("固定资产原价")) - Double.parseDouble(map2.get("固定资产净值"));
				dataMap.put("accumulatedDepreciation2", accumulatedDepreciation2);// 累计折旧
			} catch (Exception e) {
				dataMap.put("accumulatedDepreciation2", "-");// 累计折旧
			}
			try {
				Double accumulatedDepreciation3 = Double.parseDouble(map3
						.get("固定资产原价")) - Double.parseDouble(map3.get("固定资产净值"));
				dataMap.put("accumulatedDepreciation3", accumulatedDepreciation3);
			} catch (Exception e) {
				dataMap.put("accumulatedDepreciation3", "-");// 累计折旧
			}

			// 在重要财务数据中固定资产合计
			/*
			 * dataMap.put("fixedAssets1", map1.get("固定资产原价"));//固定资产
			 * dataMap.put("fixedAssets2", map2.get("固定资产原价"));
			 * dataMap.put("fixedAssets3", map3.get("固定资产原价"));
			 */
			dataMap.put("intangibleAssets1",
					map1.get("无形资产") == null ? "-" : map1.get("无形资产"));// 无形资产合计
			dataMap.put("intangibleAssets2",
					map2.get("无形资产") == null ? "-" : map2.get("无形资产"));
			dataMap.put("intangibleAssets3",
					map3.get("无形资产") == null ? "-" : map3.get("无形资产"));

			try {
				Double otherAssets1 = Double.parseDouble(map1.get("资产总计"))
						- Double.parseDouble(map1.get("无形资产"))
						- Double.parseDouble(map1.get("固定资产净值"));
				dataMap.put("otherAssets1", otherAssets1);// 其它资产
			} catch (Exception e) {
				dataMap.put("otherAssets1", "-");// 其它资产
			}
			try {
				Double otherAssets2 = Double.parseDouble(map2.get("资产总计"))
						- Double.parseDouble(map2.get("无形资产"))
						- Double.parseDouble(map2.get("固定资产净值"));
				dataMap.put("otherAssets2", otherAssets2);// 其它资产
			} catch (Exception e) {
				dataMap.put("otherAssets2", "-");// 其它资产
			}
			try {
				Double otherAssets3 = Double.parseDouble(map3.get("资产总计"))
						- Double.parseDouble(map3.get("无形资产"))
						- Double.parseDouble(map3.get("固定资产净值"));
				dataMap.put("otherAssets3", otherAssets3);// 其它资产
			} catch (Exception e) {
				dataMap.put("otherAssets3", "-");// 其它资产
			}

			dataMap.put("longInvestment1",
					map1.get("长期股权投资") == null ? "-" : map1.get("长期股权投资"));// 总投资及其他资产
			dataMap.put("longInvestment2",
					map2.get("长期股权投资") == null ? "-" : map2.get("长期股权投资"));
			dataMap.put("longInvestment3",
					map3.get("长期股权投资") == null ? "-" : map3.get("长期股权投资"));
			// 在重要财务数据中资产综计
			/*
			 * dataMap.put("totalAssets1", map1.get("资产总计"));//资产总计
			 * dataMap.put("totalAssets2", map2.get("资产总计"));
			 * dataMap.put("totalAssets3", map3.get("资产总计"));
			 */
			dataMap.put("shortBorrowing1",
					map1.get("短期借款") == null ? "-" : map1.get("短期借款"));
			dataMap.put("shortBorrowing2",
					map2.get("短期借款") == null ? "-" : map2.get("短期借款"));
			dataMap.put("shortBorrowing3",
					map3.get("短期借款") == null ? "-" : map3.get("短期借款"));

			dataMap.put("accountsPayable1",
					map1.get("应付账款") == null ? "-" : map1.get("应付账款"));
			dataMap.put("accountsPayable2",
					map2.get("应付账款") == null ? "-" : map2.get("应付账款"));
			dataMap.put("accountsPayable3",
					map3.get("应付账款") == null ? "-" : map3.get("应付账款"));

			dataMap.put("otherPayables1",
					map1.get("其他应付款") == null ? "-" : map1.get("其他应付款"));
			dataMap.put("otherPayables2",
					map2.get("其他应付款") == null ? "-" : map2.get("其他应付款"));
			dataMap.put("otherPayables3",
					map3.get("其他应付款") == null ? "-" : map3.get("其他应付款"));

			dataMap.put("otherCurrentLiabilities1",
					map1.get("其他流动负债") == null ? "-" : map1.get("其他流动负债"));
			dataMap.put("otherCurrentLiabilities2",
					map2.get("其他流动负债") == null ? "-" : map2.get("其他流动负债"));
			dataMap.put("otherCurrentLiabilities3",
					map3.get("其他流动负债") == null ? "-" : map3.get("其他流动负债"));

			// 一年内到期的长期负债
			dataMap.put("shortTermDebt1",
					map1.get("其他流动负债") == null ? "-" : map1.get("其他流动负债"));// 一年内到期的非流动负债
			dataMap.put("shortTermDebt2",
					map2.get("其他流动负债") == null ? "-" : map2.get("其他流动负债"));
			dataMap.put("shortTermDebt3",
					map3.get("其他流动负债") == null ? "-" : map3.get("其他流动负债"));

			dataMap.put("totalCurrentLiabilities1",
					map1.get("流动负债合计") == null ? "-" : map1.get("流动负债合计"));
			dataMap.put("totalCurrentLiabilities2",
					map2.get("流动负债合计") == null ? "-" : map2.get("流动负债合计"));
			dataMap.put("totalCurrentLiabilities3",
					map3.get("流动负债合计") == null ? "-" : map3.get("流动负债合计"));

			// 长期负债
			dataMap.put("totalNoncurrentLiabilities1",
					map1.get("非流动负债合计") == null ? "-" : map1.get("非流动负债合计"));// 非流动负债合计
			dataMap.put("totalNoncurrentLiabilities2",
					map2.get("非流动负债合计") == null ? "-" : map2.get("非流动负债合计"));
			dataMap.put("totalNoncurrentLiabilities3",
					map3.get("非流动负债合计") == null ? "-" : map3.get("非流动负债合计"));

			/*
			 * //长期负债 dataMap.put("totalNoncurrentLiabilities1",
			 * map1.get("非流动负债合计"));//非流动负债合计
			 * dataMap.put("totalNoncurrentLiabilities2", map2.get("非流动负债合计"));
			 * dataMap.put("totalNoncurrentLiabilities3", map3.get("非流动负债合计"));
			 */

			dataMap.put("totalcurrentLiabilities1", map1.get("负债合计") == null ? "-"
					: map1.get("负债合计"));
			dataMap.put("totalcurrentLiabilities2", map2.get("负债合计") == null ? "-"
					: map2.get("负债合计"));
			dataMap.put("totalcurrentLiabilities3", map3.get("负债合计") == null ? "-"
					: map3.get("负债合计"));

			dataMap.put("capitalReserves1",
					map1.get("资本公积") == null ? "-" : map1.get("资本公积"));
			dataMap.put("capitalReserves2",
					map2.get("资本公积") == null ? "-" : map2.get("资本公积"));
			dataMap.put("capitalReserves3",
					map3.get("资本公积") == null ? "-" : map3.get("资本公积"));

			dataMap.put("surplusReserves1",
					map1.get("盈余公积") == null ? "-" : map1.get("盈余公积"));
			dataMap.put("surplusReserves2",
					map2.get("盈余公积") == null ? "-" : map2.get("盈余公积"));
			dataMap.put("surplusReserves3",
					map3.get("盈余公积") == null ? "-" : map3.get("盈余公积"));

			dataMap.put("undistributedProfit1", map1.get("未分配利润") == null ? "-"
					: map1.get("未分配利润"));
			dataMap.put("undistributedProfit2", map2.get("未分配利润") == null ? "-"
					: map2.get("未分配利润"));
			dataMap.put("undistributedProfit3", map3.get("未分配利润") == null ? "-"
					: map3.get("未分配利润"));
			// 在重要数据指标中
			/*
			 * dataMap.put("ownerEquity1", map1.get("股东权益合计"));
			 * dataMap.put("ownerEquity2", map2.get("股东权益合计"));
			 * dataMap.put("ownerEquity3", map3.get("股东权益合计"));
			 */
			// 利润表
			dataMap.put("operatingIncome1",
					map1.get("营业收入") == null ? "-" : map1.get("营业收入"));
			dataMap.put("operatingIncome2",
					map2.get("营业收入") == null ? "-" : map2.get("营业收入"));
			dataMap.put("operatingIncome3",
					map3.get("营业收入") == null ? "-" : map3.get("营业收入"));
			//
			dataMap.put("operatingCost1",
					map1.get("营业成本") == null ? "-" : map1.get("营业成本"));
			dataMap.put("operatingCost2",
					map2.get("营业成本") == null ? "-" : map2.get("营业成本"));
			dataMap.put("operatingCost3",
					map3.get("营业成本") == null ? "-" : map3.get("营业成本"));
			// 毛利润/亏损 税后利润/亏损 净利润/亏损
			dataMap.put("netProfit1",
					map1.get("净利润") == null ? "-" : map1.get("净利润"));
			dataMap.put("netProfit2",
					map2.get("净利润") == null ? "-" : map2.get("净利润"));
			dataMap.put("netProfit3",
					map3.get("净利润") == null ? "-" : map3.get("净利润"));

			dataMap.put("costOfSales1",
					map1.get("销售费用") == null ? "-" : map1.get("销售费用"));
			dataMap.put("costOfSales2",
					map2.get("销售费用") == null ? "-" : map2.get("销售费用"));
			dataMap.put("costOfSales3",
					map3.get("销售费用") == null ? "-" : map3.get("销售费用"));

			dataMap.put("managementFees1",
					map1.get("管理费用") == null ? "-" : map1.get("管理费用"));
			dataMap.put("managementFees2",
					map2.get("管理费用") == null ? "-" : map2.get("管理费用"));
			dataMap.put("managementFees3",
					map3.get("管理费用") == null ? "-" : map3.get("管理费用"));

			dataMap.put("financeCharges1",
					map1.get("财务费用") == null ? "-" : map1.get("财务费用"));
			dataMap.put("financeCharges2",
					map2.get("财务费用") == null ? "-" : map2.get("财务费用"));
			dataMap.put("financeCharges3",
					map3.get("财务费用") == null ? "-" : map3.get("财务费用"));
			// 营业利润/亏损 税前利润/亏损
			dataMap.put("profitTotal1",
					map1.get("利润总额") == null ? "-" : map1.get("利润总额"));
			dataMap.put("profitTotal2",
					map2.get("利润总额") == null ? "-" : map2.get("利润总额"));
			dataMap.put("profitTotal3",
					map3.get("利润总额") == null ? "-" : map3.get("利润总额"));

			dataMap.put("incomeTaxExpenses1", map1.get("所得税费用") == null ? "-"
					: map1.get("所得税费用"));
			dataMap.put("incomeTaxExpenses2", map2.get("所得税费用") == null ? "-"
					: map2.get("所得税费用"));
			dataMap.put("incomeTaxExpenses3", map3.get("所得税费用") == null ? "-"
					: map3.get("所得税费用"));

			// 流动比率
			try {
				Double currentRatio1 = Double.parseDouble(map1.get("流动资产合计"))
						/ Double.parseDouble(map1.get("流动负债合计"));
				dataMap.put("currentRatio1", String.format("%.2f", currentRatio1));
			} catch (Exception e) {
				dataMap.put("currentRatio1", "-");
			}
			try {
				Double currentRatio2 = Double.parseDouble(map2.get("流动资产合计"))
						/ Double.parseDouble(map2.get("流动负债合计"));
				dataMap.put("currentRatio2", String.format("%.2f", currentRatio2));
			} catch (Exception e) {
				dataMap.put("currentRatio2", "-");
			}
			try {
				Double currentRatio3 = Double.parseDouble(map3.get("流动资产合计"))
						/ Double.parseDouble(map3.get("流动负债合计"));
				dataMap.put("currentRatio3", String.format("%.2f", currentRatio3));
			} catch (Exception e) {
				dataMap.put("currentRatio3", "-");
			}

			// 速动比率
			try {
				Double quickRatio1 = (Double.parseDouble(map1.get("流动资产合计")) - Double
						.parseDouble(map1.get("存货")))
						/ Double.parseDouble(map1.get("流动负债合计"));
				dataMap.put("quickRatio1", String.format("%.2f", quickRatio1));
			} catch (Exception e) {
				dataMap.put("quickRatio1", "-");
			}
			try {
				Double quickRatio2 = (Double.parseDouble(map2.get("流动资产合计")) - Double
						.parseDouble(map2.get("存货")))
						/ Double.parseDouble(map2.get("流动负债合计"));
				dataMap.put("quickRatio2", String.format("%.2f", quickRatio2));
			} catch (Exception e) {
				dataMap.put("quickRatio2", "-");
			}
			try {
				Double quickRatio3 = (Double.parseDouble(map3.get("流动资产合计")) - Double
						.parseDouble(map3.get("存货")))
						/ Double.parseDouble(map3.get("流动负债合计"));
				dataMap.put("quickRatio3", String.format("%.2f", quickRatio3));
			} catch (Exception e) {
				dataMap.put("quickRatio3", "-");
			}
			// 负债权益比率
			try {
				Double debtequityRatio1 = Double.parseDouble(map1.get("负债合计"))
						/ Double.parseDouble(map1.get("股东权益合计"));
				dataMap.put("debtequityRatio1",
						String.format("%.2f", debtequityRatio1));
			} catch (Exception e) {
				dataMap.put("debtequityRatio1", "-");
			}
			try {
				Double debtequityRatio2 = Double.parseDouble(map2.get("负债合计"))
						/ Double.parseDouble(map2.get("股东权益合计"));
				dataMap.put("debtequityRatio2",
						String.format("%.2f", debtequityRatio2));
			} catch (Exception e) {
				dataMap.put("debtequityRatio2", "-");
			}
			try {
				Double debtequityRatio3 = Double.parseDouble(map3.get("负债合计"))
						/ Double.parseDouble(map3.get("股东权益合计"));
				dataMap.put("debtequityRatio3",
						String.format("%.2f", debtequityRatio3));
			} catch (Exception e) {
				dataMap.put("debtequityRatio3", "-");
			}
			// 资产负债率
			try {
				Double assetliabilityRatio1 = Double.parseDouble(map1.get("负债合计"))
						/ Double.parseDouble(map1.get("资产总计"));
				dataMap.put("assetliabilityRatio1",
						String.format("%.2f", assetliabilityRatio1));
			} catch (Exception e) {
				dataMap.put("assetliabilityRatio1", "-");
			}
			try {
				Double assetliabilityRatio2 = Double.parseDouble(map2.get("负债合计"))
						/ Double.parseDouble(map2.get("资产总计"));
				dataMap.put("assetliabilityRatio2",
						String.format("%.2f", assetliabilityRatio2));
			} catch (Exception e) {
				dataMap.put("assetliabilityRatio2", "-");
			}
			try {
				Double assetliabilityRatio3 = Double.parseDouble(map3.get("负债合计"))
						/ Double.parseDouble(map3.get("资产总计"));
				dataMap.put("assetliabilityRatio3",
						String.format("%.2f", assetliabilityRatio3));
			} catch (Exception e) {
				dataMap.put("assetliabilityRatio3", "-");
			}
			// 应收账款回收周期cycleDate
			try {
				Double cycleDate1 = Double.parseDouble(map1.get("应收账款"))
						/ Double.parseDouble(map1.get("营业收入")) * 365;
				dataMap.put("cycleDate1", String.format("%.2f", cycleDate1));
			} catch (Exception e) {
				dataMap.put("cycleDate1", "-");
			}
			try {
				Double cycleDate2 = Double.parseDouble(map2.get("应收账款"))
						/ Double.parseDouble(map2.get("营业收入")) * 365;
				dataMap.put("cycleDate2", String.format("%.2f", cycleDate2));
			} catch (Exception e) {
				dataMap.put("cycleDate2", "-");
			}
			try {
				Double cycleDate3 = Double.parseDouble(map3.get("应收账款"))
						/ Double.parseDouble(map3.get("营业收入")) * 365;
				dataMap.put("cycleDate3", String.format("%.2f", cycleDate3));
			} catch (Exception e) {
				dataMap.put("cycleDate3", "-");
			}
			// 存货周转期
			try {
				Double inventoryProcessingPeriod1 = 365 / (Double.parseDouble(map1
						.get("营业成本")) / Double.parseDouble(map1.get("存货")));
				dataMap.put("inventoryProcessingPeriod1",
						inventoryProcessingPeriod1);
			} catch (Exception e) {
				dataMap.put("inventoryProcessingPeriod1", "-");
			}
			try {
				Double inventoryProcessingPeriod2 = 365 / (Double.parseDouble(map2
						.get("营业成本")) / Double.parseDouble(map2.get("存货")));
				dataMap.put("inventoryProcessingPeriod2",
						inventoryProcessingPeriod2);
			} catch (Exception e) {
				dataMap.put("inventoryProcessingPeriod2", "-");
			}
			try {
				Double inventoryProcessingPeriod3 = 365 / (Double.parseDouble(map3
						.get("营业成本")) / Double.parseDouble(map3.get("存货")));
				dataMap.put("inventoryProcessingPeriod3",
						inventoryProcessingPeriod3);
			} catch (Exception e) {
				dataMap.put("inventoryProcessingPeriod3", "-");
			}
			// 资产周转率
			try {
				Double assetTurnover1 = Double.parseDouble(map1.get("营业收入"))
						/ Double.parseDouble(map1.get("资产总计"));
				dataMap.put("assetTurnover1", String.format("%.2f", assetTurnover1));
			} catch (Exception e) {
				dataMap.put("assetTurnover1", "-");
			}
			try {
				Double assetTurnover2 = Double.parseDouble(map2.get("营业收入"))
						/ Double.parseDouble(map2.get("资产总计"));
				dataMap.put("assetTurnover2", String.format("%.2f", assetTurnover2));
			} catch (Exception e) {
				dataMap.put("assetTurnover2", "-");
			}
			try {
				Double assetTurnover3 = Double.parseDouble(map3.get("营业收入"))
						/ Double.parseDouble(map3.get("资产总计"));
				dataMap.put("assetTurnover3", String.format("%.2f", assetTurnover3));
			} catch (Exception e) {
				dataMap.put("assetTurnover3", "-");
			}
			// 毛利润率
			try {
				Double grossProfitMargin1 = (Double.parseDouble(map1.get("营业收入")) - Double
						.parseDouble(map1.get("营业成本")))
						/ Double.parseDouble(map1.get("营业收入"));
				dataMap.put("grossProfitMargin1",
						String.format("%.2f", grossProfitMargin1));
			} catch (Exception e) {
				dataMap.put("grossProfitMargin1", "-");
			}
			try {
				Double grossProfitMargin2 = (Double.parseDouble(map2.get("营业收入")) - Double
						.parseDouble(map2.get("营业成本")))
						/ Double.parseDouble(map2.get("营业收入"));
				dataMap.put("grossProfitMargin2",
						String.format("%.2f", grossProfitMargin2));
			} catch (Exception e) {
				dataMap.put("grossProfitMargin2", "-");
			}
			try {
				Double grossProfitMargin3 = (Double.parseDouble(map3.get("营业收入")) - Double
						.parseDouble(map3.get("营业成本")))
						/ Double.parseDouble(map3.get("营业收入"));
				dataMap.put("grossProfitMargin3",
						String.format("%.2f", grossProfitMargin3));
			} catch (Exception e) {
				dataMap.put("grossProfitMargin3", "-");
			}
			// 净利润率
			try {
				Double netProfitMargin1 = Double.parseDouble(map1.get("净利润"))
						/ Double.parseDouble(map1.get("营业收入"));
				dataMap.put("netProfitMargin1",
						String.format("%.2f", netProfitMargin1));
			} catch (Exception e) {
				dataMap.put("netProfitMargin1", "-");
			}
			try {
				Double netProfitMargin2 = Double.parseDouble(map2.get("净利润"))
						/ Double.parseDouble(map2.get("营业收入"));
				dataMap.put("netProfitMargin2",
						String.format("%.2f", netProfitMargin2));
			} catch (Exception e) {
				dataMap.put("netProfitMargin2", "-");
			}
			try {
				Double netProfitMargin3 = Double.parseDouble(map3.get("净利润"))
						/ Double.parseDouble(map3.get("营业收入"));
				dataMap.put("netProfitMargin3",
						String.format("%.2f", netProfitMargin3));
			} catch (Exception e) {
				dataMap.put("netProfitMargin3", "-");
			}
			// 股东权益收益率
			try {
				Double shareholdersEquity1 = Double.parseDouble(map1.get("净利润"))
						/ Double.parseDouble(map1.get("股东权益合计"));
				dataMap.put("shareholdersEquity1",
						String.format("%.2f", shareholdersEquity1));
			} catch (Exception e) {
				dataMap.put("shareholdersEquity1", "-");
			}
			try {
				Double shareholdersEquity2 = Double.parseDouble(map2.get("净利润"))
						/ Double.parseDouble(map2.get("股东权益合计"));
				dataMap.put("shareholdersEquity2",
						String.format("%.2f", shareholdersEquity2));
			} catch (Exception e) {
				dataMap.put("shareholdersEquity2", "-");
			}
			try {
				Double shareholdersEquity3 = Double.parseDouble(map3.get("净利润"))
						/ Double.parseDouble(map3.get("股东权益合计"));
				dataMap.put("shareholdersEquity3",
						String.format("%.2f", shareholdersEquity3));
			} catch (Exception e) {
				dataMap.put("shareholdersEquity3", "-");
			}
			// 资产收益率
			try {
				Double assetEarningRate1 = Double.parseDouble(map1.get("净利润"))
						* 2
						/ (Double.parseDouble(map1.get("资产总计")) + Double
								.parseDouble(map1.get("资产总计")));
				dataMap.put("assetEarningRate1",
						String.format("%.2f", assetEarningRate1));
			} catch (Exception e) {
				dataMap.put("assetEarningRate1", "-");
			}
			try {
				Double assetEarningRate2 = Double.parseDouble(map2.get("净利润"))
						* 2
						/ (Double.parseDouble(map2.get("资产总计")) + Double
								.parseDouble(map2.get("资产总计")));
				dataMap.put("assetEarningRate2",
						String.format("%.2f", assetEarningRate2));
			} catch (Exception e) {
				dataMap.put("assetEarningRate2", "-");
			}
			try {
				Double assetEarningRate3 = Double.parseDouble(map3.get("净利润"))
						* 2
						/ (Double.parseDouble(map3.get("资产总计")) + Double
								.parseDouble(map3.get("资产总计")));
				dataMap.put("assetEarningRate3",
						String.format("%.2f", assetEarningRate3));
			} catch (Exception e) {
				dataMap.put("assetEarningRate3", "-");
			}
			}
			return dataMap;
		}
		
		/**
		 * @title 导出图片
		 * @author  孙尚飞  2017-8-23
		 * @desc
		 */
		@Permission(model = "reportTemplate", privilegeValue = "report")
		@RequestMapping("/finalImage")
		@ResponseBody
		public Map<String, Object> finalImage(String entID) {
			List<FinalcialImageVo> finalcialImageVos = new ArrayList<FinalcialImageVo>();
			List<FinalRateImageVo> finalRateImageVos = new ArrayList<FinalRateImageVo>();
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			String strs = "";
			list = financeService.reportFinalData(entID);
			Map<String, String> map1 = new HashMap<String, String>();
			Map<String, String> map2 = new HashMap<String, String>();
			Map<String, String> map3 = new HashMap<String, String>();
			if (list.size() > 2) {
				map1 = list.get(0);
				map2 = list.get(1);
				map3 = list.get(2);
				strs = map1.get("年份")+","+map2.get("年份")+","+map3.get("年份");
				FinalcialImageVo finalcialImageVo1 = new FinalcialImageVo();
				finalcialImageVo1.setName("流动资产");
				finalcialImageVo1.setValue1(map1.get("流动资产合计") == null ? "0" : map1
						.get("流动资产合计"));
				finalcialImageVo1.setValue2(map2.get("流动资产合计") == null ? "0" : map2
						.get("流动资产合计"));
				finalcialImageVo1.setValue3(map3.get("流动资产合计") == null ? "0" : map3
						.get("流动资产合计"));
				finalcialImageVos.add(finalcialImageVo1);

				FinalcialImageVo finalcialImageVo2 = new FinalcialImageVo();
				finalcialImageVo2.setName("流动负债");
				finalcialImageVo2.setValue1(map1.get("流动负债合计") == null ? "0" : map1
						.get("流动负债合计"));
				finalcialImageVo2.setValue2(map2.get("流动负债合计") == null ? "0" : map2
						.get("流动负债合计"));
				finalcialImageVo2.setValue3(map3.get("流动负债合计") == null ? "0" : map3
						.get("流动负债合计"));
				finalcialImageVos.add(finalcialImageVo2);

				FinalcialImageVo finalcialImageVo3 = new FinalcialImageVo();
				finalcialImageVo3.setName("营运资本");
				try {
					Double workingCapital1 = Double.parseDouble(map1.get("流动资产合计"))
							- Double.parseDouble(map1.get("流动负债合计"));
					finalcialImageVo3.setValue1(workingCapital1 + "");
				} catch (Exception e) {
					finalcialImageVo3.setValue1("0");
				}
				try {
					Double workingCapital2 = Double.parseDouble(map2.get("流动资产合计"))
							- Double.parseDouble(map2.get("流动负债合计"));
					finalcialImageVo3.setValue1(workingCapital2 + "");
				} catch (Exception e) {
					finalcialImageVo3.setValue2("0");
				}
				try {
					Double workingCapital3 = Double.parseDouble(map3.get("流动资产合计"))
							- Double.parseDouble(map3.get("流动负债合计"));
					finalcialImageVo3.setValue3(workingCapital3 + "");
				} catch (Exception e) {
					finalcialImageVo3.setValue3("0");
				}
				finalcialImageVos.add(finalcialImageVo3);

				FinalcialImageVo finalcialImageVo4 = new FinalcialImageVo();
				finalcialImageVo4.setName("有形净值");
				try {
					Double tangibleNetWorth1 = Double.parseDouble(map1.get("资产总计"))
							- Double.parseDouble(map1.get("无形资产"));
					finalcialImageVo4.setValue1(tangibleNetWorth1 + "");
				} catch (Exception e) {
					finalcialImageVo4.setValue1("0");
				}
				try {
					Double tangibleNetWorth2 = Double.parseDouble(map2.get("资产总计"))
							- Double.parseDouble(map2.get("无形资产"));
					finalcialImageVo4.setValue2(tangibleNetWorth2 + "");
				} catch (Exception e) {
					finalcialImageVo4.setValue2("0");
				}
				try {
					Double tangibleNetWorth3 = Double.parseDouble(map3.get("资产总计"))
							- Double.parseDouble(map3.get("无形资产"));
					finalcialImageVo4.setValue3(tangibleNetWorth3 + "");
				} catch (Exception e) {
					finalcialImageVo4.setValue3("0");
				}
				finalcialImageVos.add(finalcialImageVo4);

				FinalcialImageVo finalcialImageVo5 = new FinalcialImageVo();
				finalcialImageVo5.setName("固定资产");
				finalcialImageVo5.setValue1(map1.get("固定资产原价") == null ? "0" : map1
						.get("固定资产原价"));
				finalcialImageVo5.setValue2(map2.get("固定资产原价") == null ? "0" : map2
						.get("固定资产原价"));
				finalcialImageVo5.setValue3(map3.get("固定资产原价") == null ? "0" : map3
						.get("固定资产原价"));
				finalcialImageVos.add(finalcialImageVo5);

				FinalcialImageVo finalcialImageVo6 = new FinalcialImageVo();
				finalcialImageVo6.setName("总资产");
				finalcialImageVo6.setValue1(map1.get("资产总计") == null ? "0" : map1
						.get("资产总计"));
				finalcialImageVo6.setValue2(map2.get("资产总计") == null ? "0" : map2
						.get("资产总计"));
				finalcialImageVo6.setValue3(map3.get("资产总计") == null ? "0" : map3
						.get("资产总计"));
				finalcialImageVos.add(finalcialImageVo6);

				FinalcialImageVo finalcialImageVo7 = new FinalcialImageVo();
				finalcialImageVo7.setName("总负债");
				finalcialImageVo7.setValue1(map1.get("负债合计") == null ? "0" : map1
						.get("负债合计"));
				finalcialImageVo7.setValue2(map2.get("负债合计") == null ? "0" : map2
						.get("负债合计"));
				finalcialImageVo7.setValue3(map3.get("负债合计") == null ? "0" : map3
						.get("负债合计"));
				finalcialImageVos.add(finalcialImageVo7);

				FinalcialImageVo finalcialImageVo8 = new FinalcialImageVo();
				finalcialImageVo8.setName("总负债");
				finalcialImageVo8.setValue1(map1.get("股东权益合计") == null ? "0" : map1
						.get("股东权益合计"));
				finalcialImageVo8.setValue2(map2.get("股东权益合计") == null ? "0" : map2
						.get("股东权益合计"));
				finalcialImageVo8.setValue3(map3.get("股东权益合计") == null ? "0" : map3
						.get("股东权益合计"));
				finalcialImageVos.add(finalcialImageVo8);

				FinalcialImageVo finalcialImageVo9 = new FinalcialImageVo();
				finalcialImageVo9.setName("营业收入");
				finalcialImageVo9.setValue1(map1.get("营业收入") == null ? "0" : map1
						.get("营业收入"));
				finalcialImageVo9.setValue2(map2.get("营业收入") == null ? "0" : map2
						.get("营业收入"));
				finalcialImageVo9.setValue3(map3.get("营业收入") == null ? "0" : map3
						.get("营业收入"));
				finalcialImageVos.add(finalcialImageVo9);

				FinalcialImageVo finalcialImageVo10 = new FinalcialImageVo();
				finalcialImageVo10.setName("净利润");
				finalcialImageVo10.setValue1(map1.get("净利润") == null ? "0" : map1
						.get("净利润"));
				finalcialImageVo10.setValue2(map2.get("净利润") == null ? "0" : map2
						.get("净利润"));
				finalcialImageVo10.setValue3(map3.get("净利润") == null ? "0" : map3
						.get("净利润"));
				finalcialImageVos.add(finalcialImageVo10);

				FinalRateImageVo finalRateImageVo1 = new FinalRateImageVo();
				finalRateImageVo1.setName("流动比率");
				try {
					Double currentRatio1 = Double.parseDouble(map1.get("流动资产合计"))
							/ Double.parseDouble(map1.get("流动负债合计"));
					finalRateImageVo1.setValue1(currentRatio1 + "");
				} catch (Exception e) {
					finalRateImageVo1.setValue1("0");
				}
				try {
					Double currentRatio2 = Double.parseDouble(map2.get("流动资产合计"))
							/ Double.parseDouble(map2.get("流动负债合计"));
					finalRateImageVo1.setValue2(currentRatio2 + "");
				} catch (Exception e) {
					finalRateImageVo1.setValue2("0");
				}
				try {
					Double currentRatio3 = Double.parseDouble(map3.get("流动资产合计"))
							/ Double.parseDouble(map3.get("流动负债合计"));
					finalRateImageVo1.setValue3(currentRatio3 + "");
				} catch (Exception e) {
					finalRateImageVo1.setValue3("0");
				}
				finalRateImageVos.add(finalRateImageVo1);

				FinalRateImageVo finalRateImageVo2 = new FinalRateImageVo();
				finalRateImageVo2.setName("速动比率");
				//
				try {
					Double quickRatio1 = (Double.parseDouble(map1.get("流动资产合计")) - Double
							.parseDouble(map1.get("存货")))
							/ Double.parseDouble(map1.get("流动负债合计"));
					finalRateImageVo2.setValue1(quickRatio1 + "");
				} catch (Exception e) {
					finalRateImageVo2.setValue1("0");
				}
				try {
					Double quickRatio2 = (Double.parseDouble(map2.get("流动资产合计")) - Double
							.parseDouble(map2.get("存货")))
							/ Double.parseDouble(map2.get("流动负债合计"));
					finalRateImageVo2.setValue1(quickRatio2 + "");
				} catch (Exception e) {
					finalRateImageVo2.setValue2("0");
				}
				try {
					Double quickRatio3 = (Double.parseDouble(map3.get("流动资产合计")) - Double
							.parseDouble(map3.get("存货")))
							/ Double.parseDouble(map3.get("流动负债合计"));
					finalRateImageVo2.setValue3(quickRatio3 + "");
				} catch (Exception e) {
					finalRateImageVo2.setValue3("0");
				}
				finalRateImageVos.add(finalRateImageVo2);

				FinalRateImageVo finalRateImageVo3 = new FinalRateImageVo();
				finalRateImageVo3.setName("负债权益比率");
				try {
					Double debtequityRatio1 = Double.parseDouble(map1.get("负债合计"))
							/ Double.parseDouble(map1.get("股东权益合计"));
					finalRateImageVo3.setValue1(debtequityRatio1 + "");
				} catch (Exception e) {
					finalRateImageVo3.setValue1("0");
				}
				try {
					Double debtequityRatio2 = Double.parseDouble(map2.get("负债合计"))
							/ Double.parseDouble(map2.get("股东权益合计"));
					finalRateImageVo3.setValue2(debtequityRatio2 + "");
				} catch (Exception e) {
					finalRateImageVo3.setValue2("0");
				}
				try {
					Double debtequityRatio3 = Double.parseDouble(map3.get("负债合计"))
							/ Double.parseDouble(map3.get("股东权益合计"));
					finalRateImageVo3.setValue3(debtequityRatio3 + "");
				} catch (Exception e) {
					finalRateImageVo3.setValue3("0");
				}
				finalRateImageVos.add(finalRateImageVo3);

				FinalRateImageVo finalRateImageVo4 = new FinalRateImageVo();
				finalRateImageVo4.setName("负债权益比率");
				try {
					Double assetliabilityRatio1 = Double.parseDouble(map1
							.get("负债合计")) / Double.parseDouble(map1.get("资产总计"));
					finalRateImageVo4.setValue1(assetliabilityRatio1 + "");
				} catch (Exception e) {
					finalRateImageVo4.setValue1("0");
				}
				try {
					Double assetliabilityRatio2 = Double.parseDouble(map2
							.get("负债合计")) / Double.parseDouble(map2.get("资产总计"));
					finalRateImageVo4.setValue2(assetliabilityRatio2 + "");
				} catch (Exception e) {
					finalRateImageVo4.setValue2("0");
				}
				try {
					Double assetliabilityRatio3 = Double.parseDouble(map3
							.get("负债合计")) / Double.parseDouble(map3.get("资产总计"));
					finalRateImageVo4.setValue3(assetliabilityRatio3 + "");
				} catch (Exception e) {
					finalRateImageVo4.setValue3("0");
				}
				finalRateImageVos.add(finalRateImageVo4);

				FinalRateImageVo finalRateImageVo5 = new FinalRateImageVo();
				finalRateImageVo5.setName("资产周转率");
				try {
					Double assetTurnover1 = Double.parseDouble(map1.get("营业收入"))
							/ Double.parseDouble(map1.get("资产总计"));
					finalRateImageVo5.setValue1(assetTurnover1 + "");
				} catch (Exception e) {
					finalRateImageVo5.setValue1("0");
				}
				try {
					Double assetTurnover2 = Double.parseDouble(map2.get("营业收入"))
							/ Double.parseDouble(map2.get("资产总计"));
					finalRateImageVo5.setValue1(assetTurnover2 + "");
				} catch (Exception e) {
					finalRateImageVo5.setValue2("0");
				}
				try {
					Double assetTurnover3 = Double.parseDouble(map3.get("营业收入"))
							/ Double.parseDouble(map3.get("资产总计"));
					finalRateImageVo5.setValue1(assetTurnover3 + "");
				} catch (Exception e) {
					finalRateImageVo5.setValue3("0");
				}
				finalRateImageVos.add(finalRateImageVo5);

				FinalRateImageVo finalRateImageVo6 = new FinalRateImageVo();
				finalRateImageVo6.setName("毛利润率");
				try {
					Double grossProfitMargin1 = (Double.parseDouble(map1
							.get("营业收入")) - Double.parseDouble(map1.get("营业成本")))
							/ Double.parseDouble(map1.get("营业收入"));
					finalRateImageVo6.setValue1(grossProfitMargin1 + "");
				} catch (Exception e) {
					finalRateImageVo6.setValue1("0");
				}
				try {
					Double grossProfitMargin2 = (Double.parseDouble(map2
							.get("营业收入")) - Double.parseDouble(map2.get("营业成本")))
							/ Double.parseDouble(map2.get("营业收入"));
					finalRateImageVo6.setValue2(grossProfitMargin2 + "");
				} catch (Exception e) {
					finalRateImageVo6.setValue2("0");
				}
				try {
					Double grossProfitMargin3 = (Double.parseDouble(map3
							.get("营业收入")) - Double.parseDouble(map3.get("营业成本")))
							/ Double.parseDouble(map3.get("营业收入"));
					finalRateImageVo6.setValue3(grossProfitMargin3 + "");
				} catch (Exception e) {
					finalRateImageVo6.setValue3("0");
				}
				finalRateImageVos.add(finalRateImageVo6);

				FinalRateImageVo finalRateImageVo7 = new FinalRateImageVo();
				finalRateImageVo7.setName("净利润率");
				try {
					Double netProfitMargin1 = Double.parseDouble(map1.get("净利润"))
							/ Double.parseDouble(map1.get("营业收入"));
					finalRateImageVo7.setValue1(netProfitMargin1 + "");
				} catch (Exception e) {
					finalRateImageVo7.setValue1("0");
				}
				try {
					Double netProfitMargin2 = Double.parseDouble(map2.get("净利润"))
							/ Double.parseDouble(map2.get("营业收入"));
					finalRateImageVo7.setValue2(netProfitMargin2 + "");
				} catch (Exception e) {
					finalRateImageVo7.setValue2("0");
				}
				try {
					Double netProfitMargin3 = Double.parseDouble(map3.get("净利润"))
							/ Double.parseDouble(map3.get("营业收入"));
					finalRateImageVo7.setValue3(netProfitMargin3 + "");
				} catch (Exception e) {
					finalRateImageVo7.setValue3("0");
				}
				finalRateImageVos.add(finalRateImageVo7);

				FinalRateImageVo finalRateImageVo8 = new FinalRateImageVo();
				finalRateImageVo8.setName("股东权益收益率");
				try {
					Double shareholdersEquity1 = Double
							.parseDouble(map1.get("净利润"))
							/ Double.parseDouble(map1.get("股东权益合计"));
					finalRateImageVo8.setValue1(shareholdersEquity1 + "");
				} catch (Exception e) {
					finalRateImageVo8.setValue1("0");
				}
				try {
					Double shareholdersEquity2 = Double
							.parseDouble(map2.get("净利润"))
							/ Double.parseDouble(map2.get("股东权益合计"));
					finalRateImageVo8.setValue2(shareholdersEquity2 + "");
				} catch (Exception e) {
					finalRateImageVo8.setValue2("0");
				}
				try {
					Double shareholdersEquity3 = Double
							.parseDouble(map3.get("净利润"))
							/ Double.parseDouble(map3.get("股东权益合计"));
					finalRateImageVo8.setValue3(shareholdersEquity3 + "");
				} catch (Exception e) {
					finalRateImageVo8.setValue3("0");
				}
				finalRateImageVos.add(finalRateImageVo8);

				FinalRateImageVo finalRateImageVo9 = new FinalRateImageVo();
				finalRateImageVo9.setName("资产收益率");
				try {
					Double assetEarningRate1 = Double.parseDouble(map1.get("净利润"))
							* 2
							/ (Double.parseDouble(map1.get("资产总计")) + Double
									.parseDouble(map1.get("资产总计")));
					finalRateImageVo9.setValue1(assetEarningRate1 + "");
				} catch (Exception e) {
					finalRateImageVo9.setValue1("0");
				}
				try {
					Double assetEarningRate2 = Double.parseDouble(map2.get("净利润"))
							* 2
							/ (Double.parseDouble(map2.get("资产总计")) + Double
									.parseDouble(map2.get("资产总计")));
					finalRateImageVo9.setValue1(assetEarningRate2 + "");
				} catch (Exception e) {
					finalRateImageVo9.setValue2("0");
				}
				try {
					Double assetEarningRate3 = Double.parseDouble(map3.get("净利润"))
							* 2
							/ (Double.parseDouble(map3.get("资产总计")) + Double
									.parseDouble(map3.get("资产总计")));
					finalRateImageVo9.setValue3(assetEarningRate3 + "");
				} catch (Exception e) {
					finalRateImageVo9.setValue3("0");
				}
				finalRateImageVos.add(finalRateImageVo9);
			}

			Map<String, Object> msgMap = new HashMap<String, Object>();
			msgMap.put("finalcialImageVos", finalcialImageVos);
			msgMap.put("finalRateImageVos", finalRateImageVos);
			if(strs!=null&&!strs.equals("")){
				msgMap.put("listNames", strs.split(","));
			}else{
				msgMap.put("listNames", "");
			}
			msgMap.put("success", true);
			msgMap.put("status", true);
			return msgMap;
		}
		/**
		 * @title 下载图片
		 * @author  孙尚飞  2017-8-23
		 * @desc
		 */
		@Permission(model = "reportTemplate", privilegeValue = "report")
		@RequestMapping("/downFinalImage")
		@ResponseBody
		public String downFinalImage(HttpServletRequest request,HttpServletResponse response) {
			String svgCode=request.getParameter("svgCode");
			String entID=request.getParameter("entID");
			String svg[] = svgCode.split("_");
			String path[] = new String[svg.length];
			OutputStream out = null;
			try {
				out = response.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<String> imageList = new ArrayList<String>();
			if (svg != null) {
				for (int k = 0; k < svg.length; k++) {
					String picName = entID + (k + 1) + ".png";
					path[k] = BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root")+BusinessUtil.getMsg("financeImage")+entID+File.separator+picName;
					imageList.add(path[k]);
					try {
						convertToPng(svg[k], path[k]);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (TranscoderException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			try {
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		/**
		 * @Description: 将svg字符串转换为png
		 * @Author:
		 * @param svgCode
		 *            svg代码
		 * @param pngFilePath
		 *            保存的路径
		 * @throws IOException
		 *             io异常
		 * @throws TranscoderException
		 *             svg代码异常
		 */
		private void convertToPng(String svgCode, String pngFilePath)
				throws IOException, TranscoderException {
			File file = new File(pngFilePath);
			FileOutputStream outputStream = null;
			try {
				if (!file.getParentFile().exists()) {
					if (!file.getParentFile().mkdirs()) {
					}
				}
				file.createNewFile();
				outputStream = new FileOutputStream(file);
				convertToPng(svgCode, outputStream);
			} finally {
				if (outputStream != null) {
					try {
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		/**
		 * @Description: 将svgCode转换成png文件，直接输出到流中
		 * @param svgCode
		 *            svg代码
		 * @param outputStream
		 *            输出流
		 * @throws TranscoderException
		 *             异常
		 * @throws IOException
		 *             io异常
		 */
		public static void convertToPng(String svgCode, OutputStream outputStream)
				throws TranscoderException, IOException {
			try {
				byte[] bytes = svgCode.getBytes("UTF-8");
				PNGTranscoder t = new PNGTranscoder();
				TranscoderInput input = new TranscoderInput(
						new ByteArrayInputStream(bytes));
				TranscoderOutput output = new TranscoderOutput(outputStream);
				t.transcode(input, output);
				outputStream.flush();
			} finally {
				if (outputStream != null) {
					try {
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
/*		*//**
		 * @title 生成报告并下载报告
		 * @author  孙尚飞  2017-7-31
		 * @desc
		 *//*
		@RequestMapping("/downLoadDoc")
		@ResponseBody
		public String downLoadDoc(HttpServletRequest request,HttpServletResponse response) {
			Configuration configuration = null;
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			String resultID=request.getParameter("resultID");
			String reportTemplate=request.getParameter("reportTemplate");
			String dir=request.getSession().getServletContext().getRealPath("");
			String adr=BusinessUtil.getMsg("adr");
			String root=BusinessUtil.getMsg("root");
			dir=dir+root;
			adr=adr+root;
			String reports=BusinessUtil.getMsg("reports");
			String Template=BusinessUtil.getMsg("templateUrl");
			// 路径
			String path =adr+reports;
			// 名字
			Date now = new Date();
			SimpleDateFormat formate = new SimpleDateFormat("yyyyMMddHHmmss");
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			EntResult result=entResultService.find(resultID);
			if(result!=null){
				String name = (result.getEntBaseInfo().getName() + formate.format(now) + ".doc").trim();
				String savePath=reports + resultID+ ".doc";
					// 要填入模本的数据文件
					Map<String, Object> dataMap = new HashMap<String, Object>();
					reportData(dataMap, result,request);
					// 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
					// 这里我们的模板是放在com.havenliu.document.template包下面
					try {
						configuration.setDirectoryForTemplateLoading(new File(dir+Template));
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					Template t = null;
					try {
						t = configuration.getTemplate(reportTemplate);
					} catch (IOException e) {
						e.printStackTrace();
					}
					// 输出文档路径及名称
					File outFile = new File(adr+savePath);
					Writer out = null;
					try {
						out = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(outFile), "UTF-8"));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					try {
						t.process(dataMap, out);
					} catch (TemplateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					//将报告路径存入数据库中
					result.setReportUrl(savePath);
					entResultService.update(result);
					//下载报告
					downLoad(adr+savePath, name, response);
					// 源文件全路径
					String docfile = outFile.toString();
					// 存放PDF的路径和PDF的文件名
					String toFile = path + resultID + File.separator + "PDF";
					// 用于存放图片的目录
					String outFile1 = path + resultID+ File.separator + "JPG";
					// 如果目录不存在，就创建新的目录
					if (!new File(outFile1).isDirectory()) {
						new File(outFile1).mkdirs();
					}
					// 将world文件转换为PDF文件 并返回PDF文件的全路径 17 表示文件格式为PDF
					String filePath = null;
					try {
						filePath = WordToJPG.wordToPDF(docfile, toFile, 17);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					try {
						WordToJPG.pdfToJPG(filePath, outFile1);
						// new FileManager().deleteFile(filePath);
						CompressBook book = new CompressBook();
						try {
							String inputFileName = path + resultID+ File.separator + "JPG"; // 你要压缩的文件夹
							String zipFileName = path +  resultID + ".zip"; // 压缩后的zip文件
							book.zip(inputFileName, zipFileName);
							
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
			return null;
		}
		*//**
		 * @title 为模板装载数据
		 * @author  孙尚飞  2017-8-22
		 * @desc
		 *//*
		@SuppressWarnings("static-access")
		public void reportData(Map<String, Object> dataMap, EntResult result,HttpServletRequest request) {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
			String dir=request.getSession().getServletContext().getRealPath("");
			String adr=BusinessUtil.getMsg("adr");
			String root=BusinessUtil.getMsg("root");
			dir=dir+root;
			adr=adr+root;
			if(result.getEntBaseInfo()!=null){
				EntBaseInfo entbaseinfo=result.getEntBaseInfo();
				dataMap.put("name", entbaseinfo.getName());// 公司名字${name!}
				dataMap.put("modelName", entbaseinfo.getIndustry());// 公司类型${modelName!}
				dataMap.put("regi_site", entbaseinfo.getAddress());// 注册地址${regi_capital!}
				dataMap.put("postalcode", entbaseinfo.getZipCode());// 邮政编码${postalcode!}
				dataMap.put("lega_rep", entbaseinfo.getLegalPerson());// 法定代表人${lega_rep!}
				dataMap.put("regi_capital", entbaseinfo.getRegiCapital()+entbaseinfo.getCurrencyType());// 注册资本${regi_capital!}
				if(entbaseinfo.getIssueDate()!=null){
					dataMap.put("regi_date",format.format(entbaseinfo.getIssueDate().getTime()));// 注册时间${regi_date!}
				}else{
					dataMap.put("regi_date",null);// 注册时间${regi_date!}
				}
				dataMap.put("business_no", entbaseinfo.getUSCC());// 注册号${business_no!}
				dataMap.put("registAuth", entbaseinfo.getRegisOrg());// 注册登记机关${registAuth!}
				dataMap.put("url", entbaseinfo.getWebsite());// 公司网址${url!}
				dataMap.put("tel", entbaseinfo.getTel());// 联系电话${tele_fax!}
				dataMap.put("fax", entbaseinfo.getFax());// 传真${fax!}
				dataMap.put("scale", entbaseinfo.getScale());// 人员规模${scale!}
				dataMap.put("business_scope",entbaseinfo.getBusinessScope());// 经营范围${business_scope!}
				if(result.getEncoding()==null||"".equals(result.getEncoding())){
					String encode=CODEUtil.getCODE((int) entResultService.countByEncoding());
					dataMap.put("reportNum", encode);// 报告编号
					result.setEncoding(encode);
					entResultService.update(result);
				}else{
					dataMap.put("reportNum", result.getEncoding());// 报告编号
				}
				
				if (result.getGradeTime() == null) {
					Calendar cal = format.getCalendar();
					cal.setTime(date);
					cal.add(cal.YEAR, 1);
					String valueTime = format.format(cal.getTime());
					String reportProductDate = format.format(date);
					dataMap.put("reportProductDate", reportProductDate);// 评级日期${reportProductDate!}
					dataMap.put("valueTime", valueTime);// 有效期
					result.setGradeTime(date);
					entResultService.update(result);
				} else {
					Calendar cal = format.getCalendar();
					cal.setTime(result.getGradeTime());
					cal.add(cal.YEAR, 1);
					String valueTime = format.format(cal.getTime());
					String reportProductDate = format.format(result.getGradeTime());
					dataMap.put("reportProductDate", reportProductDate);// 评级日期${reportProductDate!}
					dataMap.put("valueTime", valueTime);// 有效期
				}
				MD5Code md5 = new MD5Code();
				String path = request.getContextPath();
				String basePath = request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort() + path;
				String s1 = basePath
						+ "/user/logon_check.do?uscc="
						+ entbaseinfo.getUSCC()
						+ "&id="
						+ md5.getMD5ofStr(entbaseinfo.getUuid()
								+ entbaseinfo.getUSCC());
				String s2 = dir+BusinessUtil.getMsg("templateUrl");
				if (ZXingUtil.encodeQRCodeImage(s1, null, s2 + "/QRcode.png",
						200, 200, null)) {
					String s3 = dir+BusinessUtil.getMsg("templateUrl")+ "/QRcode.png";
					dataMap.put("image", getImageStr(s3));
				}
				String s4 = dir+BusinessUtil.getMsg("templateUrl")+ "/EAN13.png";
				if (ZXingUtil.encode(entbaseinfo.getUSCC(), 150, 25,
						s4)) {
					dataMap.put("imageEAN13", getImageStr(s4));
				}
				String s5 = adr+BusinessUtil.getMsg("financeImage")+ entbaseinfo.getUuid() +File.separator+ entbaseinfo.getUuid() + "1.png";
				dataMap.put("imageFinance1", getImageStr(s5));
				String s6 = adr +BusinessUtil.getMsg("financeImage")+ entbaseinfo.getUuid() +File.separator+ entbaseinfo.getUuid() + "2.png";
				dataMap.put("imageFinance2", getImageStr(s6));
				
				dataMap.put("advantageReason",result.getAdvantageReason());// 优势项说明${advantageReason!}
				dataMap.put("firstScore",result.getPreliminaryScore());// 初评得分${firstScore!}
				dataMap.put("firstLevel",result.getPreliminaryLevel());// 初评等级${firstLevel!}
				dataMap.put("finalSocre", result.getFinalScore());// zhong评等级${finalLevel!}
				dataMap.put("finalLevel", result.getFinalLevel());// zhong评等级${finalLevel!}
				dataMap.put("infoInput", result.getScoreSummary());// 评分人员录入企业信息${infoInput!}
				dataMap.put("scoreLevelDesc", ScoreLevelDescUtil.scoreLevelDesc(result.getFinalLevel()));// 评分人员录入企业信息${infoInput!}
				String adStr = "";
				if (result.getAdjustReason() != null) {
					String[] adjustAry = result.getAdjustReason()
							.split(",");
					for (int i = 0; i < adjustAry.length; i++) {
						String str = adjustAry[i];
						adStr += (str.replaceAll("#", "，") + "。");
					}
					dataMap.put("adjustReason", adStr);// 调整项说明${adjustReason!}
				} else {
					dataMap.put("adjustReason", "暂无调整项");// 调整项说明${adjustReason!}
				}
				//股权结构
				List<Shareholder> shareholders = shareholderService.findAllByEntID(entbaseinfo.getUuid());
				if (shareholders != null && shareholders.size() > 0) {
					dataMap.put("shareholderInfos", shareholders);// 股东信息列表
				} else {
					Shareholder s = new Shareholder();
					shareholders.add(s);
					dataMap.put("shareholderInfos", shareholders);// 股东信息列表
				}
				//高层信息
				List<Executives> executives = executivesService.findAllByEntID(entbaseinfo.getUuid());
				if (executives != null && executives.size() > 0) {
					for(Executives main : executives){
						if(main.getIDCard()!=null){
							if(main.getIDCard().length()>13){
								String id = main.getIDCard();
								main.setIDCard(id.substring(0,id.length()-(id.substring(10)).length())+"****"+id.substring(14));
							}
						}
					}
					dataMap.put("mainPersonInfos", executives);// 高层信息列表
				} else {
					Executives m = new Executives();
					executives.add(m);
					dataMap.put("mainPersonInfos", executives);// 高层信息列表
				}

				
				dataMap.put("totalNews", 0);
				dataMap.put("positiveNews", 0);
				dataMap.put("negativeNews", 0);
				dataMap.put("neutralNews", 0);
				dataMap.put("decidedLaw", 0);
				dataMap.put("undecidedLaw", 0);
				List<News> someNews=new ArrayList<News>();
				News e = new News();
				someNews.add(e);
				dataMap.put("someNews", someNews);
				
				// 指标得分比率
				List<IndexRateVo> indexRates = entResultService.getIndexRate(adr+result.getScoreXMLUrl());
				dataMap.put("indexRates", indexRates);
				dataMap.put("indexRatesSize",
						indexRates == null ? 0 : indexRates.size());
				if (indexRates != null && indexRates.size() > 0) {
					dataMap.put("firstIndex", indexRates.get(0).getIndexName());
				}
				reportfinalAndIndexData(dataMap, entbaseinfo.getUuid());
			}
			
		}*/
		
		
}
