package com.credit.controller.perscore;

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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Encoder;

import com.credit.bean.enterprise.EntResult;
import com.credit.bean.enterprise.Executives;
import com.credit.bean.enterprise.Shareholder;
import com.credit.bean.person.Career;
import com.credit.bean.person.Education;
import com.credit.bean.person.PerBaseInfo;
import com.credit.bean.person.PerResult;
import com.credit.bean.person.Skills;
import com.credit.bean.person.Train;
import com.credit.bean.vo.enterprise.FinalRateImageVo;
import com.credit.bean.vo.enterprise.FinalcialImageVo;
import com.credit.bean.vo.html2pdf.CareerChange;
import com.credit.bean.vo.html2pdf.FinancialChange;
import com.credit.bean.vo.html2pdf.FinancialData;
import com.credit.bean.vo.html2pdf.PerReport;
import com.credit.bean.vo.html2pdf.TrainVo;
import com.credit.bean.vo.html2pdf.YearVo;
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.enterprise.ExecutivesService;
import com.credit.service.enterprise.FinanceService;
import com.credit.service.enterprise.ShareholderService;
import com.credit.service.person.CareerService;
import com.credit.service.person.EducationService;
import com.credit.service.person.PerResultService;
import com.credit.service.person.PerUploadFileService;
import com.credit.service.person.SkillsService;
import com.credit.service.person.TrainService;
import com.credit.util.CODEUtil;
import com.credit.util.MD5Code;
import com.credit.util.SaveFile;
import com.credit.util.WebUtil;
import com.credit.util.ZXingUtil;
import com.credit.util.html2pdf.Html2PDFUtil;
import com.credit.util.model.Index;
import com.credit.util.model.IndexRateVo;
import com.credit.util.model.ModelUtil;
import com.credit.util.model.Option;
import com.credit.util.properties.BusinessUtil;

import freemarker.template.Configuration;
/**
 * @title 评分流程
 * @author 孙尚飞   2017-7-31
 * @desc
 */
@Controller
@RequestMapping("/control/perscoreReport")
public class PerScoreReportAction {
	private static final Logger logger = Logger.getLogger(PerScoreReportAction.class);
	private String moduleName = "管理员操作情况";
	private StringBuffer message = new StringBuffer("");
	private Boolean flag = true;
	Map<String, Object> msgMap = new HashMap<String, Object>();

	
	@Resource(name = "perResultServiceBean")
	private PerResultService perResultService;
	
	@Resource(name = "careerServiceBean")
	private CareerService careerService;
	
	@Resource(name = "educationServiceBean")
	private EducationService educationService;
	
	@Resource(name = "skillsServiceBean")
	private SkillsService skillsService;
	
	@Resource(name = "trainServiceBean")
	private TrainService trainService;
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
		PerResult result=perResultService.find(resultID);
		if(result!=null){
			String name = (result.getPerBaseInfo().getName() + formate.format(now) + ".pdf").trim();
			String savePath=reports + resultID+ ".pdf";
				// 要填入模本的数据文件
			PerReport report=reportData(result, request);
			boolean flag=Html2PDFUtil.createPDF(report, adr+savePath, dir+Template, reportTemplate);
			if(flag){
				//将报告路径存入数据库中
				result.setReportUrl(savePath);
				perResultService.update(result);
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
	public PerReport reportData(PerResult result,HttpServletRequest request) {
		PerReport report=new PerReport(); 
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String dir=request.getSession().getServletContext().getRealPath("");
		String adr=BusinessUtil.getMsg("adr");
		String root=BusinessUtil.getMsg("root");
		dir=dir+root;
		adr=adr+root;
		if(result.getPerBaseInfo()!=null){
			PerBaseInfo perbaseinfo=result.getPerBaseInfo();
			report.setName(perbaseinfo.getName());// 公司名字${name!}
			report.setPhone(perbaseinfo.getCustomer().getCellphone());
			report.setEmail(perbaseinfo.getCustomer().getEmail());
			if(perbaseinfo.getIDCard()!=null&&perbaseinfo.getIDCard().length()==18){
				report.setBirthday(this.CutId(perbaseinfo.getIDCard()));
			}
			if(result.getEncoding()==null||"".equals(result.getEncoding())){
				String encode=CODEUtil.getCODE((int) perResultService.countByEncoding());
				//report.setEncoding(encode);// 报告编号
				result.setEncoding(encode);
				perResultService.update(result);
			}else{
				//report.setEncoding(result.getEncoding());// 报告编号
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
				perResultService.update(result);
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
					+ perbaseinfo.getIDCard()
					+ "&id="
					+ md5.getMD5ofStr(perbaseinfo.getUuid()
							+ perbaseinfo.getIDCard());
			String s2 = dir+BusinessUtil.getMsg("templateUrl");
			if (ZXingUtil.encodeQRCodeImage(s1, null, s2 + "/QRcode.png",
					200, 200, null)) {
				String s3 = dir+BusinessUtil.getMsg("templateUrl")+ "/QRcode.png";
				report.setQrcode(s3);
			}
			
			List<IndexRateVo> indexRates = perResultService.getIndexRate(adr+result.getScoreXMLUrl());
			if(indexRates!=null && indexRates.size()>0){//指标得分比重
				report.setIndexRates(indexRates);
			}else{
				IndexRateVo indexRate = new IndexRateVo();
				indexRates.add(indexRate);
				report.setIndexRates(indexRates);
			}
			if(result.getFinalScore()!=null){
				report.setFinalScore(String.valueOf(result.getFinalScore()));//终评分数
			}else{
				report.setFinalScore("0");//终评分数
			}
			
			report.setScoreSummary(result.getScoreSummary()==null?"暂无该项内容":result.getScoreSummary());//评分总结
			//个人基本信息
			report.setIDCard(perbaseinfo.getIDCard());
			report.setUsedName(perbaseinfo.getUsedName());
			report.setGender(perbaseinfo.getGender());
			report.setNation(perbaseinfo.getNation());
			report.setPoliticalOutlook(perbaseinfo.getPoliticalOutlook());
			report.setNationality(perbaseinfo.getNationality());
			report.setNativePlace(perbaseinfo.getNativePlace());
			report.setMaritalStatus(perbaseinfo.getMaritalStatus());
			report.setFertilityCondition(perbaseinfo.getFertilityCondition());
			report.setPermanentAddress(perbaseinfo.getPermanentAddress());
			report.setPresentAddress(perbaseinfo.getPresentAddress());
			report.setIDIssuingAgency(perbaseinfo.getIDIssuingAgency());
			report.setIDTermStart(format.format(perbaseinfo.getIDTermStart()));
			report.setIDTermEnd(format.format(perbaseinfo.getIDTermEnd()));
			report.setPresentZipCode(perbaseinfo.getPresentZipCode());
			//指标信息
			String xmlPath=new String();
			List<Index> indexs=new ArrayList<Index>();
			Map<String, String> map1=new HashMap<String, String>();
			Map<String, String> map2=new HashMap<String, String>();
			if(result.getScoreXMLUrl()!=null){
				xmlPath=adr+result.getScoreXMLUrl();
				File file=new File(xmlPath);
				if(file.exists()){
					try {
						Document document = new SAXReader().read(file);
						indexs=ModelUtil.getIndex(document);
					} catch (DocumentException e) {
						e.printStackTrace();
					}	
					for(Index index:indexs){
						if(!index.getChirds().isEmpty()){
							for(Index secondindex:index.getChirds()){
								if(!secondindex.getChirds().isEmpty()){
									for(Index thirdindex:secondindex.getChirds()){
										for(Option option:thirdindex.getOptions()){
											if(option.getSelected()!=null&&option.getSelected().equals("true")){
												 double value=Double.parseDouble(thirdindex.getWeight())*Double.parseDouble(option.getValue());
												 map1.put(thirdindex.getName(), String.valueOf(value));
												 map2.put(thirdindex.getName(), option.getName());
											}
										}
									}
								}
							}
						}
					}
				}
			}
			List<Education> educations=educationService.findAllByPerID(perbaseinfo.getUuid());
			if(!(educations.isEmpty()||educations.size()==0)){
				report.setEducationRemarks(educations.get(0).getEducation());
				report.setUniversityRemarks(educations.get(0).getUniversity());
				report.setMajorRemarks(educations.get(0).getMajor());
				report.setDiplomaNoRemarks(educations.get(0).getDiplomaNo());
			}
			report.setEducationScore(map1.get("学历")==null?"--":map1.get("学历"));
			report.setUniversityScore(map1.get("毕业院校")==null?"--":map1.get("毕业院校"));
			report.setMajorScore("--");
			report.setDiplomaNoScore("--");
			
			List<Train> trains=trainService.findAllByPerID(perbaseinfo.getUuid());
			List<TrainVo> trainvos=new ArrayList<TrainVo>();
			if(!(trains.isEmpty()||trains.size()==0)){
				report.setTrainRemarks(trains.get(0).getTrainOrg());
				report.setTrainNoRemarks(trains.get(0).getCertificateNo());
				for(Train train:trains){
					TrainVo trainvo = new TrainVo();
					trainvo.setStartTime(format.format(train.getStartTime()));
					trainvo.setEndTime(format.format(train.getEndTime()));
					trainvo.setTrainOrg(train.getTrainOrg());
					trainvo.setTrainContent(train.getTrainContent());
					trainvos.add(trainvo);
				}
			}
			report.setTrainScore(map1.get("是否正在或有自身素质提高的意愿")==null?"--":map1.get("是否正在或有自身素质提高的意愿"));
			report.setTrainNoScore("--");
			report.setTrains(trainvos);
			
			List<Career> careers=careerService.findAllByPerID(perbaseinfo.getUuid());
			List<CareerChange> careerChange1=new ArrayList<CareerChange>();//就职单位性质变化
			List<CareerChange> careerChange2=new ArrayList<CareerChange>();//就职单位规模变化
			List<CareerChange> careerChange3=new ArrayList<CareerChange>();//行业变动
			if(!(careers.isEmpty()||careers.size()==0)){
				report.setPostRemarks(careers.get(0).getPost());
				report.setQualificationsRemarks(careers.get(0).getQualifications());
				for(int i=careers.size()-1;i>=0; i--){
					CareerChange change1=new CareerChange(careers.get(i).getInauguralUnit(), careers.get(i).getUnitProperties(), "无");
					CareerChange change2=new CareerChange(careers.get(i).getInauguralUnit(), careers.get(i).getUnitScale(), "无");
					CareerChange change3=new CareerChange(careers.get(i).getInauguralUnit(), careers.get(i).getIndustry(), "无");
					careerChange1.add(change1);
					careerChange2.add(change2);
					careerChange3.add(change3);
				}
			}
			report.setCareerChange1(careerChange1);
			report.setCareerChange2(careerChange2);
			report.setCareerChange3(careerChange3);
			report.setUnitPropertiesRemarks(map2.get("单位性质")==null?"--":map2.get("单位性质"));
			report.setScaleRemarks(map2.get("单位规模")==null?"--":map2.get("单位规模"));
			report.setPostScore(map1.get("职务")==null?"--":map1.get("职务"));
			report.setQualificationsScore(map1.get("职业资质")==null?"--":map1.get("职业资质"));
			report.setUnitPropertiesScore(map1.get("单位性质")==null?"--":map1.get("单位性质"));
			report.setScaleScore(map1.get("单位规模")==null?"--":map1.get("单位规模"));
			
			report.setSkillsScore(map1.get("专业知识技能")==null?"--":map1.get("专业知识技能"));
			report.setSkillsRemarks(map2.get("专业知识技能")==null?"--":map2.get("专业知识技能"));
			report.setSkillsTrainScore(map1.get("专业技能培训")==null?"--":map1.get("专业技能培训"));
			report.setSkillsTrainRemarks(map2.get("专业技能培训")==null?"--":map2.get("专业技能培训"));
			report.setAllLifeScore(map1.get("总工作年限")==null?"--":map1.get("总工作年限"));
			report.setAllLifeRemarks(map2.get("总工作年限")==null?"--":map2.get("总工作年限"));
			report.setSameLifeScore(map1.get("同行业工作年限")==null?"--":map1.get("同行业工作年限"));
			report.setSameLifeRemarks(map2.get("同行业工作年限")==null?"--":map2.get("同行业工作年限"));
			
			
			
		}
		return report;
	}
	
	
/*	private List<FinancialChange> getImportantFinancialData(String entID) {}
	
	private List<FinancialChange> getImportantFinancialRate(String entID) {}*/
	/**
	 * @title 判断评分报告是否存在
	 * @author  孙尚飞  2017-8-22
	 * @desc
	 */
	//@Permission(model = "reportTemplate", privilegeValue = "report")
	@RequestMapping("/judgeReportExist")
	@ResponseBody
	public Map<String, Object> judgeReportExist(HttpServletRequest request,String resultID) {
		logger.info(moduleName + "[判断评分报告是否存在]");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		boolean flag=true;
		PerResult result=perResultService.find(resultID);
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
	//@Permission(model = "reportTemplate", privilegeValue = "report")
	@RequestMapping("/uploadReport")
	@ResponseBody
	public Map<String, Object> uploadReport(HttpServletRequest request) throws Exception {
		logger.info(moduleName + "[上传修改后的报告]");
		Map<String, Object> msgMap = new HashMap<String, Object>();
		if(SaveFile.judgeSuffix(request, "pdf")){
			String resultID=request.getParameter("resultID");
			PerResult result=perResultService.find(resultID);
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
		PerResult result=perResultService.find(resultID);
		String name = (result.getPerBaseInfo().getName() + formate.format(new Date()) + ".pdf").trim();
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
		PerResult result=perResultService.find(resultID);
		String name = (result.getPerBaseInfo().getName() + formate.format(new Date()) + ".doc").trim();
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
	// 	private Map<String, Object> reportfinalAndIndexData(String entID) {}
		
		/**
		 * @title 导出图片
		 * @author  孙尚飞  2017-8-23
		 * @desc
		 
		@Permission(model = "reportTemplate", privilegeValue = "report")
		@RequestMapping("/finalImage")
		@ResponseBody
		public Map<String, Object> finalImage(String entID) {}*/
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
		//截取生日
		public String CutId(String id){
	        String year = id.substring(6, 10);// 截取年
	        String month = id.substring(10, 12);// 截取月份
	        String day = id.substring(12, 14);// 截取天
			return year + " 年 " + month + " 月 " + day + " 日";
	    }
}
