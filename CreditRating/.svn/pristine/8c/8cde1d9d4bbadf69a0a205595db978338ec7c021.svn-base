package com.credit.controller.interfaces;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.addition.Model;
import com.credit.bean.enterprise.EntBaseInfo;
import com.credit.bean.enterprise.EntResult;
import com.credit.bean.enterprise.HistoricalData;
import com.credit.service.addition.ModelService;
import com.credit.service.enterprise.EntBaseInfoService;
import com.credit.service.enterprise.EntResultService;
import com.credit.service.enterprise.HistoricalDataService;
import com.credit.util.properties.BusinessUtil;


@Controller
@RequestMapping("/ipsm")
public class IpsmInterface {
	/*企业基础信息*/
	@Resource
	private EntBaseInfoService entBaseInfoService;
	/*评分结果*/
	@Resource
	private EntResultService entResultService ;
	/*模型*/
	@Resource
	private ModelService modelService ;
	//历史记录
	@Resource
	private HistoricalDataService historicalService;
	
	@RequestMapping("/getScoreByModel")
	@ResponseBody
	public String getScoreByModel(HttpServletRequest request,HttpServletResponse response,String model,String uscc) throws ParseException {
		boolean flag=true;
		JSONObject json = new JSONObject();
		StringBuffer message=new StringBuffer();
		EntBaseInfo ent=entBaseInfoService.selectByUSCC(uscc);
		Model model2=new Model();
		String score=new String();
		if(model==null){
			flag=false;
			message.append("传参为空");
		}else{
			model2=modelService.find(model);
		}
		if(model2==null){
			flag=false;
			message.append("查询模型不存在");
		}
		if(ent==null){
			flag=false;
			message.append("查询企业不存在");
		}else{
			EntResult result=entResultService.selectByEntAndModel(ent.getUuid(),model);
			if(result==null){
				List<HistoricalData> historys=historicalService.selectByEntId(ent.getUuid());
				if(historys.size()==0||historys.isEmpty()){
					flag=false;
					message.append("查询评分结果不存在");
				}else{
					for(HistoricalData history:historys){
						if(history.getHistoricalXMLUrl() != null ){
							 //获取 盘符
							try {
								 String adr=BusinessUtil.getMsg("adr")+BusinessUtil.getMsg("root");
								 File file = new File(adr+history.getHistoricalXMLUrl());   
								 if(file.exists()){
									 SAXReader reader = new SAXReader();   
								     Document doc = reader.read(file);
								     //获取根节点
								     Element root = doc.getRootElement();  
								     Element entResult =  (Element) root.elementIterator("EntResult").next();
								     Element Model =  (Element) root.elementIterator("Model").next();
								     String modelid=Model.elementText("uuid");
								     if(modelid.equals(model)){
								    	 score=entResult.elementText("finalScore");
								    	 flag=true; 
								    	 break;
								     }else{
								    	 flag=false;
								     }
								 }else{
									 flag=false;
								 }
							} catch (DocumentException e) {
								e.printStackTrace();
							}
						}else{
							flag=false;
						}
					}
					message.append("查询评分结果不存在");
				}
			}else{
				double bd=result.getFinalScore();
				score=String.valueOf(bd);
				flag=true;
			}
		}
		if(flag){
			json.put("success", true);
			json.put("score", score);
		}else{
			json.put("success", false);
			json.put("msg", message.toString());
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
