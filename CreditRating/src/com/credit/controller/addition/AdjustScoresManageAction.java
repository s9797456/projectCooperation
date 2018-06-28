/**
 * 
 *//*
package com.credit.controller.addition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.credit.bean.addition.AdjustScores;
import com.credit.bean.addition.UploadFileCategory;
import com.credit.bean.pagelist.PageView;
import com.credit.bean.pagelist.QueryResult;
import com.credit.bean.vo.privilege.Permission;
import com.credit.service.addition.AdjustScoreService;
import com.credit.util.DateTime;

*//**
 * @Title: AdjustScoresManageAction.java
 * @author Administrator @date 2017-7-28 上午11:03:11
 * @Description: TODO
 *//*
@Controller
@RequestMapping("/control/addition/adjustScores/")
public class AdjustScoresManageAction {
	
	
	boolean flag = true;
	private static final Logger logger = Logger.getLogger(TemplateManageAction.class);
	private String moduleName = "管理员操作情况";
	private StringBuffer message = new StringBuffer("");
	private Map<String, Object> msgMap = new HashMap<String, Object>();
	
	@Resource
	AdjustScoreService adjustScoreService;
	
	
	
	@Permission(model = "adjustScores", privilegeValue = "list")
	@RequestMapping("/listUI")
	@ResponseBody
	public Map<String, Object> scoreList(int page,int start,int limit) {
		logger.info(moduleName + "[查看调整分数项列表]");
		
		PageView<AdjustScores> pageView = null;
		pageView = new PageView<AdjustScores>(limit, page);
		QueryResult<AdjustScores> qr = adjustScoreService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(qr);
		
		msgMap.put("adjustScores", qr.getResultlist());
		msgMap.put("total", qr.getTotalrecord());
		msgMap.put("success", true);
		msgMap.put("status", true);
		return msgMap;
	}
	
	@Permission(model = "adjustScores", privilegeValue = "insert")
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> add(AdjustScores adjustScores) {
		if(adjustScores == null){
			flag=false;
		}else{
			if(adjustScores.getName()==null || "".equals(adjustScores.getName())){
				flag=false;
			}
			if(adjustScoreService.exist("name", adjustScores.getName())){
				flag=false;
			}
		}
		if(flag){
			adjustScores.setUuid(UUID.randomUUID().toString().replace("-", ""));
			adjustScoreService.save(adjustScores);
			msgMap.put("success", true);
			msgMap.put("status", true);
			msgMap.put("msg", "保存成功");
			logger.info(moduleName + "[插入企业加减分项成功]");
		}else{
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "数据库中已有相同的参数项,请重新录入");
			logger.info(moduleName + "[插入企业加减分项失败]");
		}
		return msgMap;
	}
	
	@Permission(model = "adjustScores", privilegeValue = "delete")
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(String uuid) {
		AdjustScores adjustScores = null ;
		if(uuid == null || "".equals(uuid.trim())){
			flag = false;
		}else{
			adjustScores = adjustScoreService.find(uuid);
			if(adjustScores == null ){ 
				flag = false;
			}
		}
		if (flag) {
			adjustScoreService.delete(adjustScores.getUuid());
				msgMap.put("success", true);
				msgMap.put("status", true);
				msgMap.put("msg", "删除成功");
				logger.info(moduleName + "[删除参数成功]");
			
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "数据库中无相关参数,请重新尝试");
			logger.info(moduleName + "[删除参数失败]");
		}
		return msgMap;
	}
	
	@Permission(model = "adjustScores", privilegeValue = "update")
	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> update(AdjustScores adjustScores) {
		if(adjustScores == null){
			flag = false;
		}else{
			if(adjustScores.getUuid() == null || "".equals(adjustScores.getUuid().trim())){ 
				flag = false;
			}
			if(adjustScores.getName() == null || "".equals(adjustScores.getName().trim())){ 
				flag = false;
			}
			if(adjustScoreService.exist("name", adjustScores.getName())){ 
				flag = false;
			}
		}
		if (flag) {
			adjustScoreService.update(adjustScores);
				msgMap.put("success", true);
				msgMap.put("status", true);
				msgMap.put("msg", "保存成功");
				logger.info(moduleName + "[更新参数成功]");
			
		} else {
			msgMap.put("success", true);
			msgMap.put("status", false);
			msgMap.put("msg", "数据库中未查询到相同的指标,请重新录入");
			logger.info(moduleName + "[更新参数失败]");
		}
		return msgMap;
	}
}
*/